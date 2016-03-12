package question;


public class ResponseQuestion extends Question {

	public ResponseQuestion(String question, Answer answer) {
		super(question, answer);
		this.type = "response-question";
	}
	
	public ResponseQuestion(String question, String answer) {
		super(question, answer);
		this.type = "response-question";
	}

	@Override
	public String returnHTMLSingleQuestion() {
		String result = "";
		result += "<form action=\"submitAnswer\" method=\"post\"> \r";
		result += getQuestion();
		result += "<br><br> \r";
		result += "<input name=\"input\" type=\"text\"/> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		
		return result;
	}

	@Override
	public String returnHTMLQuestion(int index) {
		String result = "";
		result += getQuestion();
		result += "<br><br> \r";
		result += "<input name=\"input" + index + "\" type=\"text\"/> \r";
		return result;
	}

	
	public static String returnHTMLBlankTemplate() {
		String result = "";
		result += "<form action=\"SubmitNewQuestionServlet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"response-question\"> \r"; 
		result += "Enter question text: <br> \r <input name=\"question\" type=\"text\"/> <br><br>\r";
		result += "Enter answer (variants on same answer separated by '|'): <br> \r <input name=\"answer\" type=\"text\"/><br><br> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}
	
	public static String returnHTMLEditTemplate(String question, String answer, int index) {
		String result = "";
		result += "<form action=\"SubmitEditedQuestionServlet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"response-question\"> \r";
		result += "<input type=\"hidden\" name=\"index\" value=\"" + index + "\"> \r"; 
		result += "Enter question text: <br> \r <input name=\"question\" type=\"text\" value=\"" + question + "\"/> <br><br>\r";
		result += "Enter answer (variants on same answer separated by '|'): <br> \r <input name=\"answer\" type=\"text\" value=\"" + answer + "\"/><br><br> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}

	@Override
	public String returnHTMLDisplayStatic() {
		String result = "";
		result += "Question: " + getQuestion() + "<br> \r";
		result += "Answer: " + getAnswer().getAnswerAt(0) + "<br> \r";
		return result;
	}
	
	
	
	
}
