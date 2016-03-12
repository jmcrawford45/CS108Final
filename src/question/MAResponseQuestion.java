package question;

public class MAResponseQuestion extends Question {
	

	public MAResponseQuestion(String question, Answer answers) {
		super(question, answers);
		this.type = "maresponse-question";
	}
	
	
	public int numAnswers(){
		return this.answers.numAnswers();
	}
	
	@Override
	public String getAdditional(){
		return Boolean.toString(this.answers.isOrdered());
	}
	
	@Override
	public String returnHTMLSingleQuestion() {
		String result = "";
		result += "<form action=\"submitAnswer\" method=\"post\"> \r";
		result += getQuestion();
		result += "<br><br> \r";
		for(int i = 0; i < numAnswers(); i++){
			result += "<input type=\"text\" name=\"input_" + i + "\">  <br>\r";
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
		for(int i = 0; i < numAnswers(); i++){
			result += "<input type=\"text\" name=\"input" + index + "_" + i + "\"> <br>\r";
		}

		return result;
	}
	
	public static String returnHTMLBlankTemplate() {
		String result = "";
		result += "<form action=\"SubmitNewQuestionServlet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"maresponse-question\"> \r"; 
		result += "Enter question text: <br> \r <input name=\"question\" type=\"text\"/> <br><br>\r";
		result += "Enter answers (each answer separated by '||', each acceptable variant by '|'): <br> \r <input name=\"answer\" type=\"text\"/><br><br> \r";
		result += "Should answers be given in the order listed?";
		result += "<input type=\"checkbox\" name=\"ordered\" value=\"ordered\"><br>\r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}
	
	public static String returnHTMLEditTemplate(String question, String answer, int index) {
		String result = "";
		result += "<form action=\"SubmitEditedQuestionServlet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"maresponse-question\"> \r"; 
		result += "<input type=\"hidden\" name=\"index\" value=\"" + index +"\"> \r"; 
		result += "Enter question text: <br> \r <input name=\"question\" type=\"text\" value=\"" + question + "\"/> <br><br>\r";
		result += "Enter answers (each answer separated by '||', each acceptable variant by '|'): <br> \r <input name=\"answer\" type=\"text\"  value=\"" + answer + "\"/><br><br> \r";
		result += "Should answers be given in the order listed?";
		result += "<input type=\"checkbox\" name=\"ordered\" value=\"yes\"><br>\r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}
	
	@Override
	public String returnHTMLDisplayStatic() {
		String result = "";
		result += "Question: " + getQuestion() + "<br> \r";
		result += "Answers: " + getAnswer().convertAnswerToString() + "<br> \r";
		return result;
	}
	

}
