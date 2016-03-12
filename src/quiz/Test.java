package quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import question.Question;
import question.ResponseQuestion;

public class Test {
	
	public static ArrayList<Question> questions = qs();
	public static Quiz quiz = q();
			
	static Quiz q(){
		Quiz q = new Quiz(555, 444, 333, "desc", false, false, false, 0, "name", false);
		return q;
	}
			
	static ArrayList<Question> qs() {
		ArrayList<Question> qs = new ArrayList<Question>();
		for (int i = 0; i< 5; i++) {
			Question q = null;
			q = new ResponseQuestion("Question "+ i, "a"+i);
			qs.add(q);
		}
		return qs;
	}
	
	public static void createQidsTable(){
		Connection con = DBConnection.connect();
		try {
			PreparedStatement ps = con.prepareStatement("CREATE TABLE quiz_questions"
					+ " (quiz_id INT, question_ids VARCHAR(5000))");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertIDS(){
		Connection con = DBConnection.connect();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO quiz_questions"
					+ "(quiz_id, question_ids)"
					+ " values(?, ?)");
			ps.setInt(1, 9090);
			ps.setString(2, "23|56|89");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void getCheck() {
		Connection con = DBConnection.connect();
		int id = -1;
		String s = "";
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quiz_questions WHERE quiz_id = ?");
			ps.setInt(1, 9090);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt(1);
				s = rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(id);
		System.out.println(s);
	}

	
}
