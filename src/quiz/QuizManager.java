package quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import question.Question;
import question.QuestionManager;

public class QuizManager {
	public Connection con;
	public static final int NUMSCORES = 5;
	public static final int NUMREVIEWS = 5;
	public static final String[] CATEGORIES = {"History", "Science", "Art", "Current Events"};
	
	public QuizManager(Connection con) {
		this.con = con;
	}
	
	public void addQuiz(Quiz q){
		addQuiz(q.id, q.creator_id, q.category_id, q.description, q.random, q.one_page, q.immediate, q.name, q.practice);
	}
	
	public void addQuiz(int quiz_id, int creator_id, int category_id, String description, boolean random, boolean one_page, boolean immediate, String name, boolean practice) {
		try {
			long time = System.currentTimeMillis();
			PreparedStatement ps = con.prepareStatement("INSERT into "
					+ "quizzes(quiz_id, creator_id, category_id, description, random, onepage, immediate, date_created, name, practice) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, quiz_id);
			ps.setInt(2, creator_id);
			ps.setInt(3, category_id);
			ps.setString(4, description);
			ps.setBoolean(5, random);
			ps.setBoolean(6, one_page);
			ps.setBoolean(7, immediate);
			ps.setLong(8, time);
			ps.setString(9, name);
			ps.setBoolean(10, practice);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteQuiz(int quiz_id) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM quizzes WHERE quiz_id = ?");
			ps.setInt(1, quiz_id);
		    ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void addPerformance(Performance p){
		addPerformance(p.id, p.quiz_id, p.user_id, p.score, p.start, p.time);
	}
	
	public void addPerformance(int performance_id, int quiz_id, int user_id, int score, long start, long time) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT into "
					+ "performances(performance_id, quiz_id, user_id, score, start, time) "
					+ "values(?, ?, ?, ?, ?, ?)");
			ps.setInt(1, performance_id);
			ps.setInt(2, quiz_id);
			ps.setInt(3, user_id);
			ps.setInt(4, score);
			ps.setLong(5, start);
			ps.setLong(6, time);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deletePerformance(int performance_id) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM performances WHERE performance_id = ?");
			ps.setInt(1, performance_id);
		    ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Quiz getQuizByID(int quiz_id) {
		Quiz q = null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quizzes WHERE quiz_id = ?");
			ps.setInt(1, quiz_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int creator_id = rs.getInt(2);
				int category_id = rs.getInt(3);
				String description = rs.getString(4);
				boolean random = rs.getBoolean(5);
				boolean one_page = rs.getBoolean(6);
				boolean immediate = rs.getBoolean(7);
				long time_created = rs.getLong(8);
				String name = rs.getString(9);
				boolean practice = rs.getBoolean(10);
				q = new Quiz(quiz_id, creator_id, category_id, description, random, one_page, immediate, time_created, name, practice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return q;
	}
	
	public Performance getPerformanceByID(int performance_id) {
		Performance p = null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM performances WHERE performance_id = ?");
			ps.setInt(1, performance_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int quiz_id = rs.getInt("quiz_id");
				int user_id = rs.getInt("user_id");
				int score = rs.getInt("score");
				long start = rs.getLong("start");
				long time = rs.getLong("time");
				p = new Performance(performance_id, user_id, quiz_id, score, start, time);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	public ArrayList<Quiz> getAllQuizzes() {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quizzes");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int quiz_id = rs.getInt(1);
				int creator_id = rs.getInt(2);
				int category_id = rs.getInt(3);
				String description = rs.getString(4);
				boolean random = rs.getBoolean(5);
				boolean one_page = rs.getBoolean(6);
				boolean immediate = rs.getBoolean(7);
				long time_created = rs.getLong(8);
				String name = rs.getString(9);
				boolean practice = rs.getBoolean(10);
				quizzes.add(new Quiz(quiz_id, creator_id, category_id, description, random, one_page, immediate, time_created, name, practice));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return quizzes;
	}
	
	
	
	
	public ArrayList<Quiz> getQuizzesByCreator(int creator_id) {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quizzes WHERE creator_id = ?");
			ps.setInt(1, creator_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int quiz_id = rs.getInt(1);
				int category_id = rs.getInt(3);
				String description = rs.getString(4);
				boolean random = rs.getBoolean(5);
				boolean one_page = rs.getBoolean(6);
				boolean immediate = rs.getBoolean(7);
				long time_created = rs.getLong(8);
				String name = rs.getString(9);
				boolean practice = rs.getBoolean(10);
				quizzes.add(new Quiz(quiz_id, creator_id, category_id, description, random, one_page, immediate, time_created, name, practice));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return quizzes;
	}
	
	public ArrayList<Performance> getUserPerformances(int user_id) {
		ArrayList<Performance> performances = new ArrayList<Performance>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM performances WHERE user_id = ?");
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int quiz_id = rs.getInt("quiz_id");
				int performance_id = rs.getInt("performance_id");
				int score = rs.getInt("score");
				long start = rs.getLong("start");
				long time = rs.getLong("time");
				performances.add(new Performance(performance_id, user_id, quiz_id, score, start, time));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return performances;
	}
	
	
	//gets quizzes from the last 24 hours
	public ArrayList<Quiz> getRecentCreatedQuizzes() {
		ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
		try {
			Calendar c  = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			long recent = c.getTimeInMillis();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quizzes WHERE date_created > ?");
			ps.setLong(1, recent);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int quiz_id = rs.getInt(1);
				int creator_id = rs.getInt(2);
				int category_id = rs.getInt(3);
				String description = rs.getString(4);
				boolean random = rs.getBoolean(5);
				boolean one_page = rs.getBoolean(6);
				boolean immediate = rs.getBoolean(7);
				long time_created = rs.getLong(8);
				String name = rs.getString(9);
				boolean practice = rs.getBoolean(10);
				quizzes.add(new Quiz(quiz_id, creator_id, category_id, description, random, one_page, immediate, time_created, name, practice));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Collections.sort(quizzes, new Comparator<Quiz>(){
		     public int compare(Quiz q1, Quiz q2){
		         return (int)(q2.time_created - q1.time_created);
		     }
		});
		
		return quizzes;
	}
	
	public ArrayList<Performance> getRecentPerformances() {
		ArrayList<Performance> performances = new ArrayList<Performance>();
		try {
			Calendar c  = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			long recent = c.getTimeInMillis();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM performances WHERE start > ?");
			ps.setLong(1, recent);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int quiz_id = rs.getInt("quiz_id");
				int user_id = rs.getInt("user_id");
				int performance_id = rs.getInt("performance_id");
				int score = rs.getInt("score");
				long start = rs.getLong("start");
				long time = rs.getLong("time");
				performances.add(new Performance(performance_id, user_id, quiz_id, score, start, time));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Collections.sort(performances, new Comparator<Performance>(){
		     public int compare(Performance p1, Performance p2){
		         return (int)(p2.start - p1.start);
		     }
		});

		return performances;
	}
	
	public ArrayList<Performance> getQuizBestPerformances(int quiz_id) {
		List<Performance> performances = new ArrayList<Performance>();
		try {
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM performances "
					+ "WHERE quiz_id = ? ORDER BY score DESC");
			ps.setInt(1, quiz_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				int performance_id = rs.getInt("performance_id");
				int score = rs.getInt("score");
				long start = rs.getLong("start");
				long time = rs.getLong("time");
				performances.add(new Performance(performance_id, user_id, quiz_id, score, start, time));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Collections.sort(performances);
		
		int n = Math.min(NUMSCORES, performances.size());
		return (new ArrayList<Performance>(performances.subList(0, n)));
	}
	
	
	public ArrayList<Performance> getQuizBestRecentPerformances(int quiz_id) {
		List<Performance> performances = new ArrayList<Performance>();
		try {
			Calendar c  = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			long recent = c.getTimeInMillis();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM performances "
					+ "WHERE quiz_id = ? AND start > ? ORDER BY score DESC");
			ps.setInt(1, quiz_id);
			ps.setLong(2, recent);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				int performance_id = rs.getInt("performance_id");
				int score = rs.getInt("score");
				long start = rs.getLong("start");
				long time = rs.getLong("time");
				performances.add(new Performance(performance_id, user_id, quiz_id, score, start, time));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Collections.sort(performances);
		int n = Math.min(NUMSCORES, performances.size());
		return (new ArrayList<Performance>(performances.subList(0, n)));
	}
	
	
	public ArrayList<Performance> getQuizRecentPerformances(int quiz_id) {
		ArrayList<Performance> performances = new ArrayList<Performance>();
		try {
			Calendar c  = Calendar.getInstance();
			c.add(Calendar.DATE, -1);
			long recent = c.getTimeInMillis();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM performances "
					+ "WHERE quiz_id = ? AND start > ?");
			ps.setInt(1, quiz_id);
			ps.setLong(2, recent);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				int performance_id = rs.getInt("performance_id");
				int score = rs.getInt("score");
				long start = rs.getLong("start");
				long time = rs.getLong("time");
				performances.add(new Performance(performance_id, user_id, quiz_id, score, start, time));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Collections.sort(performances, new Comparator<Performance>(){
		     public int compare(Performance p1, Performance p2){
		         return (int)(p2.start - p1.start);
		     }
		});
		
		return performances;
	}
	
	public ArrayList<Performance> getUserQuizPerformances(int quiz_id, int user_id) {
		ArrayList<Performance> performances = new ArrayList<Performance>();
		try {
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM performances "
					+ "WHERE quiz_id = ? AND user_id = ?");
			ps.setInt(1, quiz_id);
			ps.setInt(2, user_id);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int performance_id = rs.getInt("performance_id");
				int score = rs.getInt("score");
				long start = rs.getLong("start");
				long time = rs.getLong("time");
				performances.add(new Performance(performance_id, user_id, quiz_id, score, start, time));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return performances;
	}
	
	public void addFeedback(Feedback fb) {
		addFeedback(fb.rev_id, fb.quiz_id, fb.user_id, fb.rating, fb.review, fb.time);
	}
	
	public void addFeedback(int rev_id, int quiz_id, int user_id, int rating, String review, long time) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT into "
					+ "reviews(rev_id, quiz_id, user_id, rating, review, time) "
					+ "values(?, ?, ?, ?, ?, ?)");
			ps.setInt(1, rev_id);
			ps.setInt(2, quiz_id);
			ps.setInt(3, user_id);
			ps.setInt(4, rating);
			ps.setString(5, review);
			ps.setLong(6, time);
			ps.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}	
		
	}
	
	public Feedback getFeedbackbyID(int id) {
		Feedback fb = null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM reviews WHERE rev_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				fb = new Feedback(id, rs.getInt("quiz_id"), rs.getInt("user_id"),
						rs.getInt("rating"), rs.getString("review"), rs.getLong("time"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fb;
	}
	
	public ArrayList<Feedback> getQuizFeedback(int quiz_id) {
		ArrayList<Feedback> reviews = new ArrayList<Feedback>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM reviews WHERE quiz_id = ?");
			ps.setInt(1, quiz_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reviews.add(new Feedback(rs.getInt("rev_id"), rs.getInt("quiz_id"), rs.getInt("user_id"),
						rs.getInt("rating"), rs.getString("review"), rs.getLong("time")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		long seed = System.nanoTime();
		Collections.shuffle(reviews, new Random(seed));
		int n = Math.min(NUMREVIEWS, reviews.size());
		return (new ArrayList<Feedback>(reviews.subList(0, n)));
	}
	
	
	public void setQuizQuestions(int quizid, String qids) {
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO "
					+ "quiz_questions(quiz_id, question_ids) "
					+ "values(?,?)");
			ps.setInt(1, quizid);
			ps.setString(2, qids);
		
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public ArrayList<Question> getQuizQuestions(int quizid) {
		ArrayList<Question> questions = new ArrayList<Question>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM quiz_questions WHERE quiz_id = ?");
			ps.setInt(1, quizid);
			ResultSet rs = ps.executeQuery();
			String s = "";
			if(rs.next()){
				s = rs.getString("question_ids");
			}
			String[] ss = s.split("\\|");
			QuestionManager qsm = new QuestionManager(this.con);
			for (int i = 0; i < ss.length; i++) {
				Question q = qsm.getQuestion(Integer.parseInt(ss[i]));
				questions.add(q);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return questions;
	}
	
	
	
}
