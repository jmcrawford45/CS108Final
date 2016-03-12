
package tableabstraction;
import java.io.Serializable;
import java.util.ArrayList;

public class Stats implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private int users, quizzes, prevID;
		private ArrayList<String> msgs;
		public int getUsers() {
			return users;
		}
		public void setUsers(int users) {
			this.users = users;
		}
		public int getQuizzes() {
			return quizzes;
		}
		public void setQuizzes(int quizzes) {
			this.quizzes = quizzes;
		}
		public Stats(int users, int quizzes, ArrayList<String> msgs){
			this.users=users;
			this.quizzes=quizzes;
			this.msgs = msgs;
			this.prevID = 0;
		}
		public int issueID(){
			prevID++;
			return prevID;
		}
		public void addMsg(String msg){
			msgs.add(msg);
		}
		public ArrayList<String> getMsgs(){
			return msgs;
		}
	}
