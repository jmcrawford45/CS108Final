package messages;

import java.io.Serializable;

public class FriendRequest extends Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean accepted;
	private String body;
	public FriendRequest(String from, String to){
		super(from, to,"FriendRequest");
		this.accepted = false;
		this.body = from + " would like to be your friend!";
	}
	public String getBody() {
		return body;
	}
	public void accept(){
		this.accepted = true;
	}
	public boolean isAccepted(){
		return this.accepted;
	}
	public String getType(){
		return "FriendRequest";
	}
	@Override
	public String toString(){
		return  "From: " + getFrom() + "\n" + body;
	}
}