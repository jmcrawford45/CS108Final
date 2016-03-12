package question;

import java.util.ArrayList;

public class FiBQuestion extends Question {
	
	String questionAfterBlank;
	String[] questionParts;

	public FiBQuestion(String question, Answer answer) throws InvalidFiBException{
		super(question, answer);
		questionParts = question.split("\\|");
		if(questionParts.length != 2) throw new InvalidFiBException("Fill-in-Blank question must have two parts, separated by '|'");
		this.type = "fib-question";
	}
	
	public FiBQuestion(String question, String answer) throws InvalidFiBException{
		super(question, answer);
		questionParts = question.split("\\|");
		if(questionParts.length != 2) throw new InvalidFiBException("Fill-in-Blank question must have two parts, separated by '|'");
		this.type = "fib-question";
	}
	
	public String getPreText(){
		return questionParts[0];
	}
	
	public String getPostText(){
		return questionParts[1];
	}

	public class InvalidFiBException extends Exception {
		  public InvalidFiBException() { super(); }
		  public InvalidFiBException(String message) { super(message); }
		  public InvalidFiBException(String message, Throwable cause) { super(message, cause); }
		  public InvalidFiBException(Throwable cause) { super(cause); }
	}
	
	@Override
	public String returnHTMLSingleQuestion() {
		String result = "";
		result += "<form action=\"submitAnswer\" method=\"post\"> \r";
		result += getPreText() + " ";
		result += "<input name=\"input\" type=\"text\"/> " + getPostText() + "\r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		
		return result;
	}

	@Override
	public String returnHTMLQuestion(int index) {
		String result = "";
		result += getPreText() + " ";
		result += "<input name=\"input" + index + "\" type=\"text\"/> " + getPostText() + "\r";
		return result;
	}
	
	public static String returnHTMLBlankTemplate() {
		String result = "";
		result += "<form action=\"SubmitNewQuestionServlet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"fib-question\"> \r"; 
		result += "Enter pre-blank text: <br> \r <input name=\"pre\" type=\"text\"/> <br><br>\r";
		result += "Enter post-blank text: <br> \r <input name=\"post\" type=\"text\"/> <br><br>\r";
		result += "Enter answer (variants on same answer separated by '|'): <br> \r <input name=\"answer\" type=\"text\"/><br><br> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}
	
	public static String returnHTMLEditTemplate(String pretext, String posttext, String answer, int index) {
		String result = "";
		result += "<form action=\"SubmitEditedQuestionServlet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"fib-question\"> \r"; 
		result += "<input type=\"hidden\" name=\"index\" value=\"" + index +"\"> \r"; 
		result += "Enter pre-blank text: <br> \r <input name=\"pre\" type=\"text\" value=\"" + pretext + "\"/> <br><br>\r";
		result += "Enter post-blank text: <br> \r <input name=\"post\" type=\"text\" value=\"" + posttext + "\"/> <br><br>\r";
		result += "Enter answer (variants on same answer separated by '|'): <br> \r <input name=\"answer\" type=\"text\" value=\"" + answer + "\"/><br><br> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}
	
	@Override
	public String returnHTMLDisplayStatic() {
		String result = "";
		result += "Pretext: " + getPreText() + "<br> \r";
		result += "Posttext: " + getPostText() + "<br> \r";
		result += "Answer: " + getAnswer().getAnswerAt(0) + "<br> \r";
		return result;
	}
	
}
