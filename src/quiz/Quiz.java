package quiz;

import java.util.ArrayList;

import question.Answer;
import question.Question;

public class Quiz {


	
	
	public int id;
	public int creator_id;
	public int category_id;
	public String name;
	public String description;
	public boolean random = false;
	public boolean one_page = false;
	public boolean immediate = false;
	public ArrayList<Question> questions=null;
	public ArrayList<Performance> scoreboard=null;
	public long time_created;
	public boolean practice = false;
	public String questionsIDS = "";

	
	public Quiz(int id, int creator_id, int category_id, String description, boolean random, boolean one_page, boolean immediate, long time_created, String name, boolean practice) {
		
		this.id = id;
		this.name = name;
		this.creator_id = creator_id;
		this.category_id = category_id;
		this.description = description;
		this.random = random;
		this.one_page = one_page;
		this.immediate = immediate;
		this.time_created = time_created;
		this.practice = practice;
		questions = new ArrayList<Question>();
		scoreboard = new ArrayList<Performance>();
		
	}
	
	public void setTime(long time) {
		this.time_created = time;
	}
	
	public void setQuestions(ArrayList<Question> qs) {
		this.questions = qs;
	}

	
	
	public void setScores(ArrayList<Performance> ps) {
		this.scoreboard = ps;
	}
	
	public void addQuestion(Question q) {
		this.questions.add(q);
	}
	
	public Question getQuestionbyIndex(int i) {
		if (this.questions.size() <= i) return null;
		return this.questions.get(i);
	}
	
	public void setQuestionAtIndex(int index, Question q){
		this.questions.set(index, q);
	}
	
	public void deleteQuestionAtIndex(int index){
		this.questions.remove(index);
	}

	public int getNumQuestions(){
		return questions.size();
	}
	
	public void addScore(Performance p) {
		this.scoreboard.add(p);
	}
	
	public void setOnePage() {
		this.one_page = true;
	}
	
	public void setImmediate() {
		this.immediate = true;
	}
	
	public void setRandom() {
		this.random = true;
	}
	
	public void setPractice() {
		this.practice = true;
	}
	public Performance gradeQuiz(int performance_id, int user_id, long start, ArrayList<Question> questions, ArrayList<Answer> userAnswers) {
		Performance p;
		long end = System.currentTimeMillis();
		int correct = 0;
		int total = questions.size();
		for (int i = 0; i < questions.size(); i++) {
			if(questions.get(i).isCorrectAnswer(userAnswers.get(i))) {
				correct++;
			}
		}
		long time = end - start;
		int score = (int)(((float)correct/(float)total) * 100);
		p = new Performance(performance_id, user_id, this.id, score, start, time);
		return p;
	}

}
