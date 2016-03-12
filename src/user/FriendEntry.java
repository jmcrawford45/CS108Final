
package user;


import java.io.Serializable;

public class FriendEntry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String body, u1, u2, quiz;
	private boolean isQuiz;
	int quizID;
	public FriendEntry(String u1, String u2){
		this.u1 = u1;
		this.u2 = u2;
		isQuiz = false;
		this.body = " became friends with ";
	}
	public String getU1() {
		return u1;
	}
	public String getU2() {
		return u2;
	}
	public FriendEntry(String u1, String name, boolean created, int id){
		this.u1 = u1;
		this.quiz = name;
		this.quizID = id;
		if(created) this.body = " created ";
		else this.body = " took ";
		this.body += "quiz: ";
		isQuiz = true;
	}
	public String getQuizName(){
		return quiz;
	}
	public int getQuizID(){
		return quizID;
	}
	public boolean quizMessage(){
		return isQuiz;
	}
	@Override
	public String toString(){
		return body;
	}
}