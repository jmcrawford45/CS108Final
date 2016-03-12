package question;

import question.MCQuestion.InvalidMCException;

public class MCMAQuestion extends Question {
	
	String choices;
	String[] choiceArray;

	public MCMAQuestion(String question, Answer answer, String choices) throws InvalidMCMAException {
		super(question, answer);
		this.type = "mcma-question";
		this.choices = choices;
		this.choiceArray = choices.split("\\|");
		this.answers.setIfOrdered(false);
		if(!isValidChoice()) throw new InvalidMCMAException("At least one answer does not appear in choices.");
	}

	public MCMAQuestion(String question, String answer, String choices) throws InvalidMCMAException {
		super(question, answer);
		this.type = "mcma-question";
		this.choices = choices;
		this.choiceArray = choices.split("\\|");
		this.answers.setIfOrdered(false);
		if(!isValidChoice()) throw new InvalidMCMAException("At least one answer does not appear in choices.");
	}
	
	public int getNumChoices(){
		return choiceArray.length;
	}
	
	public String getChoice(int choiceIndex){
		return choiceArray[choiceIndex];
	}
	
	
	private boolean isValidChoice(){
		if(numAnswers() > choiceArray.length) return false;
		iter: for(int i = 0; i < numAnswers(); i++){
			String curr = this.answers.getAnswerAt(i);
			for(int x = 0; x < choiceArray.length; x++){
				if(curr.equals(choiceArray[x])) continue iter;
			}
			return false;
		}
		return true;
	}

	
	public class InvalidMCMAException extends Exception {
		  public InvalidMCMAException() { super(); }
		  public InvalidMCMAException(String message) { super(message); }
		  public InvalidMCMAException(String message, Throwable cause) { super(message, cause); }
		  public InvalidMCMAException(Throwable cause) { super(cause); }
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
			result += "<input type=\"checkbox\" name=\"input\" value=\"" + getChoice(i) +"\"> " + getChoice(i) + "<br>\r";
		}
		result += "<br> \r";
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
			result += "<input type=\"checkbox\" name=\"input" + index + "\" value=\"" + getChoice(i) +"\"> " + getChoice(i) + "<br>\r";
		}

		return result;
	}
	
	public static String returnHTMLBlankTemplate() {
		String result = "";
		result += "<form action=\"SubmitNewQuestionServlet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"mcma-question\"> \r"; 
		result += "Enter question text: <br> \r <input name=\"question\" type=\"text\"/> <br><br>\r";
		result += "Enter answers (each answer separated by '||'): <br> \r <input name=\"answer\" type=\"text\"/><br><br> \r";
		result += "Enter choices, including answer (each choice separated by '|'): <br> \r <input name=\"choices\" type=\"text\"/><br><br> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}
	
	public static String returnHTMLEditTemplate(String question, String answer, String choices, int index) {
		String result = "";
		result += "<form action=\"SubmitEditedQuestionServlet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"mcma-question\"> \r"; 
		result += "<input type=\"hidden\" name=\"index\" value=\"" + index +"\"> \r"; 
		result += "Enter question text: <br> \r <input name=\"question\" type=\"text\" value=\"" + question + "\"/> <br><br>\r";
		result += "Enter answers (each answer separated by '||': <br> \r <input name=\"answer\" type=\"text\" value=\"" + answer + "\"/><br><br> \r";
		result += "Enter choices, including answer (each choice separated by '|'): <br> \r <input name=\"choices\" type=\"text\" value=\"" + choices + "\"/><br><br> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}
	
	@Override
	public String returnHTMLDisplayStatic() {
		String result = "";
		result += "Question: " + getQuestion() + "<br> \r";
		result += "Answers: " + getAnswer().convertAnswerToString() + "<br> \r";
		result += "Choices: " + this.choices + "<br> \r";
		return result;
	}
	
}
