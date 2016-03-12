package messages;

import java.io.Serializable;

public abstract class Message implements Serializable{
	/**
	 *   
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String from, to, type;
	private boolean read;
	public Message(String from, String to,String type){
		this.from = from;
		this.to= to;
		this.type = type;
		read = false;
	}
	public String getFrom() {
		return from;
	}
	public void markAsRead(){
		read = true;
	}
	public boolean read(){
		return read;
	}
	public String getTo() {
		return to;
	}
	public String getType(){
		return type;
	}
}
