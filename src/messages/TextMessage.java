package messages;

import java.io.Serializable;

public class TextMessage extends Message implements Serializable{  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String body;
	public TextMessage(String from, String to, String body) {
		super(from, to,"TextMessage");
		this.body = body;
	}
	public String getBody() {
		return body;
	}
	public String getType(){
		return "TextMessage";
	}
	@Override
	public String toString(){
		return  "From: " + getFrom() + "\n" + body;
	}

}