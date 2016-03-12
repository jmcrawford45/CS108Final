package messages;

import java.io.Serializable;

public class ChallengeMessage extends Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String body, link;
	public ChallengeMessage(String to, String from, String link, double score){
		super(to,from,"ChallengeMessage");
		this.body = from + " sent you a challenge!"
				+ "Visit the link below to try to beat"
				+ "their high score of" + score;
		this.link = link;
	}
	public String getBody() {
		return body;
	}
	public String getLink() {
		return link;
	}
	public String getType(){
		return "ChallengeMessage";
	}
	@Override
	public String toString(){
		return  "From: " + getFrom() + "\n" + body;
	}
}