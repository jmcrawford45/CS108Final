package question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import question.FiBQuestion.InvalidFiBException;
import question.MCMAQuestion.InvalidMCMAException;
import question.MCQuestion.InvalidMCException;
import tableabstraction.TableAbstraction;


public class QuestionManager {

	public Connection con;
	
	public QuestionManager(Connection con) {
		this.con = con;
	}
	
	
	public Question getQuestion(int id) {
		Question result = null;
		
		try {
			String type;
			String question;
			String answerString;
			String additional;

			PreparedStatement ps = con.prepareStatement("SELECT * FROM questions WHERE question_id =?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				type = rs.getString(2);
				question = rs.getString(3);
				answerString = rs.getString(4);
				additional = rs.getString(5);

				Answer answer = Answer.convertStringToAnswer(answerString);
				
				if(type.equals("response-question")){
					result = new ResponseQuestion(question, answer);
				} else if (type.equals("fib-question")){
					result = new FiBQuestion(question, answer);
				} else if (type.equals("maresponse-question")){
					result = new MAResponseQuestion(question, answer);
					result.getAnswer().setIfOrdered(Boolean.parseBoolean(additional));
					
					
				} else if (type.equals("matching-question")){
					MatchingQuestion mqresult = new MatchingQuestion(question);
					String[] stringidArray = additional.split("\\|");
					int[] idArray = new int[stringidArray.length];
					for(int i = 0; i < stringidArray.length; i++){
						idArray[i] = Integer.parseInt(stringidArray[i]);
					}
					for(int i = 0; i < idArray.length; i ++){
						PreparedStatement ps2 = con.prepareStatement("SELECT * FROM questions WHERE question_id=?");
						ps2.setInt(1, idArray[i]);
						ResultSet rs2 = ps2.executeQuery();
						if(rs.next()){
							String question2 = rs2.getString(3);
							String answerString2 = rs2.getString(4);
							ResponseQuestion currResponse = new ResponseQuestion(question2, answerString2);
							mqresult.addPair(currResponse);
						}
					}
					return mqresult;
				} 
				
				
				else if (type.equals("mcma-question")){
					result = new MCMAQuestion(question, answer, additional);
				} else if(type.equals("mc-question")){
					result = new MCQuestion(question, answer, additional);
				} else if(type.equals("pic-reponse-question")){
					result = new PictureResponseQuestion(question, answer, additional);
				} else {
					System.out.println("UNACCOUNTED FOR QUESTION");
				}
				//result.setQuizID(quizid);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int addQuestion(Question q) {
		int questionid = -1;
		if(q.getType().equals("matching-question")){
			MatchingQuestion mq = (MatchingQuestion) q;
			int[] ids = new int[mq.numPairs()];
			for(int i = 0; i < mq.numPairs(); i++){
				ResponseQuestion currRQ = new ResponseQuestion(mq.getPairAt(i).getQuestion(), mq.getPairAt(i).getAnswer());
				try {
					PreparedStatement ps = con.prepareStatement("INSERT into "
							+ "questions(question_id, type, question, answer, additional) "
							+ "values(?, ?, ?, ?, ?)");
					int id = TableAbstraction.getID(con);
					ids[i] = id;
					ps.setInt(1, id);
					ps.setString(2, currRQ.getType());
					ps.setString(3, currRQ.getQuestion());
					ps.setString(4, currRQ.getAnswer().convertAnswerToString());
					ps.setString(5, currRQ.getAdditional());

					ps.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				PreparedStatement ps = con.prepareStatement("INSERT into "
						+ "questions(question_id, type, question, answer, additional) "
						+ "values(?, ?, ?, ?, ?)");
				questionid = TableAbstraction.getID(con);
				ps.setInt(1, questionid);
				ps.setString(2, mq.getType());
				ps.setString(3, mq.getQuestion());
				ps.setString(4, mq.getAnswer().convertAnswerToString());
				ps.setString(5, mq.getAdditional());

				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				PreparedStatement ps = con.prepareStatement("INSERT into "
						+ "questions(question_id, type, question, answer, additional) "
						+ "values(?, ?, ?, ?, ?)");
				questionid = TableAbstraction.getID(con);
				ps.setInt(1, questionid);
				ps.setString(2, q.getType());
				ps.setString(3, q.getQuestion());
				ps.setString(4, q.getAnswer().convertAnswerToString());
				ps.setString(5, q.getAdditional());

				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return questionid;
	}
	
	public void editQuestion(Question q){
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE questions SET question='?' answer='?' additional='?' "
					+ "WHERE question_id=?");
			ps.setString(1, q.getQuestion());
			ps.setString(2, q.getAnswer().convertAnswerToString());
			ps.setString(3, q.getAdditional());
			ps.setInt(4, q.getID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void deleteQuestion(Question q){
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM questions WHERE id=?");
			ps.setInt(1, q.getID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteQuestion(int id){
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM questions WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
