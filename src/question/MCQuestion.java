package question;

import java.util.ArrayList;

public class MCQuestion extends Question {
	
	String choices;
	String[] choiceArray;

	public MCQuestion(String question, Answer answer, String choices) throws InvalidMCException {

		super(question, answer);
		this.choices = choices;
		this.type = "mc-question";
		this.choiceArray = choices.split("\\|");
		if(!isValidChoice()) throw new InvalidMCException("Answer does not appear in choices.");
		}
	
	public MCQuestion(String question, String answer, String choices) throws InvalidMCException {

		super(question, answer);
		this.choices = choices;
		this.type = "mc-question";
		this.choiceArray = choices.split("\\|");
		if(!isValidChoice()) throw new InvalidMCException("Answer does not appear in choices.");

	}
	
	
	public int getNumChoices(){
		return choiceArray.length;
	}
	
	public String getChoice(int choiceIndex){
		return choiceArray[choiceIndex];
	}
	
	
	private boolean isValidChoice(){
		if(this.answers.hasMultipleAnswers()) return false;					//mult choice only has one solution
		for(int i = 0; i < choiceArray.length; i++){
			if(choiceArray[i].equals(answers.firstAnswer())) return true;		//if no options appear in answer, then not a valid MC question
		}
		return false;
	}
	
	public class InvalidMCException extends Exception {
		  public InvalidMCException() { super(); }
		  public InvalidMCException(String message) { super(message); }
		  public InvalidMCException(String message, Throwable cause) { super(message, cause); }
		  public InvalidMCException(Throwable cause) { super(cause); }
	}
	
	@Override
	public String getAdditional(){
		return this.choices;
	}
	
	@Override
	public String returnHTMLSingleQuestion() {
		String result = "";
		result += "<form action=\"submitAnswer\" method=\"post\"> \r";
		result += getQuestion();
		result += "<br><br> \r";
		for(int i = 0; i < getNumChoices(); i++){
			result += "<input type=\"radio\" name=\"input\" value=\"" + getChoice(i) +"\"> " + getChoice(i) + "<br>\r";
		}
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		
		return result;
	}

	@Override
	public String returnHTMLQuestion(int index) {
		String result = "";
		
		result += getQuestion();
		result += "<br><br> \r";
		for(int i = 0; i < getNumChoices(); i++){
			result += "<input type=\"radio\" name=\"input" + index + "\" value=\"" + getChoice(i) +"\"> " + getChoice(i) + "<br>\r";
		}

		return result;
	}
	
	public static String returnHTMLBlankTemplate() {
		String result = "";
		result += "<form action=\"SubmitNewQuestionServlet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"mc-question\"> \r"; 
		result += "Enter question text: <br> \r <input name=\"question\" type=\"text\"/> <br><br>\r";
		result += "Enter answer: <br> \r <input name=\"answer\" type=\"text\"/><br><br> \r";
		result += "Enter choices, including answer (each choice separated by '|'): <br> \r <input name=\"choices\" type=\"text\"/><br><br> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}
	
	public static String returnHTMLEditTemplate(String question, String answer, String choices, int index) {
		String result = "";
		result += "<form action=\"SubmitEditedQuestionServlet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"mc-question\"> \r";
		result += "<input type=\"hidden\" name=\"index\" value=\"" + index + "\"> \r"; 
		result += "Enter question text: <br> \r <input name=\"question\" type=\"text\" value=\"" + question + "\"/> <br><br>\r";
		result += "Enter answer: <br> \r <input name=\"answer\" type=\"text\" value=\"" + answer + "\"/><br><br> \r";
		result += "Enter choices, including answer (each choice separated by '|'): <br> \r <input name=\"choices\" type=\"text\" value=\"" + choices + "\"/><br><br> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}
	
	@Override
	public String returnHTMLDisplayStatic() {
		String result = "";
		result += "Question: " + getQuestion() + "<br> \r";
		result += "Answer: " + getAnswer().getAnswerAt(0) + "<br> \r";
		result += "Choices: " + this.choices + "<br> \r";
		return result;
	}
	
	
}
