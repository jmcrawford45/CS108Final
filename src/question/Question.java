package question;

import java.util.ArrayList;
import java.util.List;

public abstract class Question {
	
	protected Answer answers;
	protected String question;
	protected String type;
	protected int questionid;
	protected int quizid;
	
	
	public Question(String question, Answer answers){
		this.question = question;
		this.answers = answers;
		this.type = "question";
	}
	
	public Question(String question, String answer){
		this.question = question;
		this.answers = new Answer(answer);
	}
	
	public void overrideAnswers(Answer newAnswer){
		this.answers = newAnswer;
	}
	
	public boolean isCorrectSingleAnswer(String input){
		return this.answers.isCorrectSingle(input);
	}
	
	public boolean isCorrectAnswer(Answer input){
		return this.answers.isCorrect(input);
	}
	
	public String getType(){
		return type;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public boolean isOrdered(){
		return this.answers.isOrdered();
	}
	
	public int numAnswers(){
		return answers.numAnswers();
	}
	
	public String answersToString(){
		String result = "";
		for(int i = 0; i < numAnswers(); i++){
			result += this.answers.getAnswerAt(i);
			result += "\r";
		}
		return result;
		
	}
	
	public int getID(){
		return this.questionid;
	}
	
	public void setID(int id){
		this.questionid = id;
	}
	
	public int getQuizID(){
		return this.quizid;
	}
	
	public void setQuizID(int quizid){
		this.quizid = quizid;
	}
	
	public Answer getAnswer(){
		return this.answers;
	}
	
	public String getAdditional(){
		String result = "";
		return result;
	}
	
	public abstract String returnHTMLQuestion(int index);
	
	public abstract String returnHTMLSingleQuestion();
	
	public abstract String returnHTMLDisplayStatic();
	
	
}
