package question;

import java.util.ArrayList;

public class PictureResponseQuestion extends ResponseQuestion {
	
	private String picURL;

	public PictureResponseQuestion(String question, Answer answer, String url) {
		super(question, answer);
		picURL = url;
		this.type = "pic-reponse-question";
	}
	
	public PictureResponseQuestion(String question, String answer, String url) {
		super(question, answer);
		picURL = url;
		this.type = "pic-reponse-question";
	}
	
	public String getPicURL(){
		return picURL;
	}
	
	public void changePicURLTo(String url){
		this.picURL = url;
	}
	
	@Override
	public String getAdditional(){
		return this.picURL;
	}
	
	@Override
	public String returnHTMLSingleQuestion() {
		String result = "";
		result += "<form action=\"submitAnswer\" method=\"post\"> \r";
		result += "<img src=\"" + getPicURL() + "\" alt=\"" + getQuestion() + "\"> <br> \r";
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
		result += "<img src=\"" + getPicURL() + "\" alt=\"" + getQuestion() + "\"> <br> \r";
		result += getQuestion();
		result += "<br><br> \r";
		result += "<input name=\"input" + index + "\" type=\"text\"/> \r";
		return result;
	}
	
	public static String returnHTMLBlankTemplate() {
		String result = "";
		result += "<form action=\"SubmitNewQuestionServlet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"pic-response-question\"> \r"; 
		result += "Enter question text: <br> \r <input name=\"question\" type=\"text\"/> <br><br>\r";
		result += "Enter answer (variants on same answer separated by '|'): <br> \r <input name=\"answer\" type=\"text\"/><br><br> \r";
		result += "Enter pic URL: <br> \r <input name=\"url\" type=\"text\"/><br><br> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}
	
	public static String returnHTMLEditTemplate(String question, String answer, String url, int index) {
		String result = "";
		result += "<form action=\"SubmitEditedTemplateSerlvet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"pic-response-question\"> \r";
		result += "<input type=\"hidden\" name=\"index\" value=\"" + index +"\"> \r"; 
		result += "Enter question text: <br> \r <input name=\"question\" type=\"text\" value=\"" + question + "\" /> <br><br>\r";
		result += "Enter answer (variants on same answer separated by '|'): <br> \r <input name=\"answer\" type=\"text\" value=\"" + answer + "\"/><br><br> \r";
		result += "Enter pic URL: <br> \r <input name=\"url\" type=\"text\" value=\"" + url + "\"/><br><br> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}
	
	@Override
	public String returnHTMLDisplayStatic() {
		String result = "";
		result += "Question: " + getQuestion() + "<br> \r";
		result += "Answer: " + getAnswer().getAnswerAt(0) + "<br> \r";
		result += "PicURL: " + getPicURL() + "<br> \r";
		return result;
	}

}
