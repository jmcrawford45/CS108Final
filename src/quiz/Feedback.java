package quiz;

public class Feedback {
	
	public int rev_id;
	public int quiz_id;
	public int user_id;
	public int rating;
	public String review;
	public long time;
	
	public Feedback(int rev_id, int quiz_id, int user_id, int rating, String review, long time) {
		this.rev_id = rev_id;
		this.quiz_id = quiz_id;
		this.user_id = user_id;
		this.rating = rating;
		this.review = review;
		this.time= time;
	}
	
}
