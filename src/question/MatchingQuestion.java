package question;


import java.util.ArrayList;
import java.util.Random;

import com.mysql.jdbc.Connection;

public class MatchingQuestion extends Question {
	
	ArrayList<ResponseQuestion> pairs;
	ArrayList<Answer> answerArray;
	int[] idArray;


	public MatchingQuestion(String instructions) {
		super(instructions, "");
		this.pairs = new ArrayList<ResponseQuestion>();
		this.type = "matching-question";
		this.answerArray = getRandomizedAnswerArray();
	}
	
	public MatchingQuestion(String instructions, ArrayList<ResponseQuestion> pairs){
		super(instructions, "");
		this.pairs = pairs;
		this.type = "matching-question";
		this.answerArray = getRandomizedAnswerArray();
	}
	
	public void addPair(ResponseQuestion pair){
		pairs.add(pair);
		this.answerArray = getRandomizedAnswerArray();
	}
	
	public void addPair(String question, String answer){
		ResponseQuestion pair = new ResponseQuestion(question, answer);
		pairs.add(pair);
		this.answerArray = getRandomizedAnswerArray();
	}
	
	public String getAnswerAt(int index){
		return this.answerArray.get(index).firstAnswer();
	}
	
	public void setIdArray(int[] arr){
		this.idArray = arr;
	}
	
	private ArrayList<Answer> getRandomizedAnswerArray(){
		ArrayList<Answer> result = new ArrayList<Answer>();
		ArrayList<Boolean> isUsed = new ArrayList<Boolean>();
		for(int i = 0; i < numPairs(); i++) isUsed.add(false);
		Random rand = new Random();
		
		while(isUsed.contains(false)){
			int index = rand.nextInt(numPairs());
			if(isUsed.get(index).equals(false)){
				isUsed.set(index, true);
				result.add(getPairAt(index).getAnswer());
			}
		}
		
		return result;
	}
	
	public void randomizeAnswerArray(){
		this.answerArray = getRandomizedAnswerArray();
	}
	
	public ResponseQuestion getPairAt(int index){
		return pairs.get(index);
	}
	
	public int getIndexQuestionExistsAt(String question){
		for (int i = 0; i < numPairs(); i++){
			ResponseQuestion curr = pairs.get(i);
			if(curr.getQuestion().equals(question)) return i;
		}
		return -1;
	}
	
	public String getInstructions(){
		return getQuestion();
	}
	
	public boolean questionExists(String question){
		if (getIndexQuestionExistsAt(question) == -1) return false;
		else return true;
	}
	
	public boolean checkAnswerFor(String question, String answer){
		int index = getIndexQuestionExistsAt(question);
		ResponseQuestion curr = pairs.get(index);
		return curr.isCorrectSingleAnswer(answer);
	}
	
	public int numPairs(){
		return pairs.size();
	}
	
	@Override
	public int numAnswers(){
		return this.answerArray.size();
	}
	
	
	@Override
	public String getAdditional(){
		if(idArray == null) return "";
		String result = "";
		for(int i = 0; i < idArray.length; i++){
			if(i != 0) result += "|";
			result += idArray[i];
		}
		return result;
	}
	
	public static ArrayList<ResponseQuestion> getPairsFromString(String input, Connection con){
		QuestionManager man = new QuestionManager(con);
		ArrayList<ResponseQuestion> result = new ArrayList<ResponseQuestion>();
		String[] inputArray = input.split("\\|");
		for (int i = 0; i < inputArray.length; i++){
			int id = Integer.parseInt(inputArray[i]);
			result.add((ResponseQuestion)man.getQuestion(id));
		}
		return result;
		
	}
	
	@Override
	public String returnHTMLSingleQuestion() {
		String result = "";
		result += "<form action=\"submitAnswer\" method=\"post\"> \r";
		result += getInstructions();
		result += "<br><br> \r";
		for(int i = 0; i < numPairs(); i++){
			result += getPairAt(i).getQuestion() + "\t\t";
			result += "<select name=\"input_" + i +"\"> \r";
			for(int j = 0; j < numPairs(); j++){
				result += "<option value=\"" + getAnswerAt(j) + "\">" + getAnswerAt(j) + "</option>\r";
			}
			result += "</select> <br> \r";
		}
		result += "<br> \r";
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		
		return result;
	}

	@Override
	public String returnHTMLQuestion(int index) {
		String result = "";
		
		result += getInstructions();
		result += "<br><br> \r";
		for(int i = 0; i < numPairs(); i++){
			result += getPairAt(i).getQuestion() + "\t\t";
			result += "<select name=\"input" + index + "_" + i +"\"> \r";
			for(int j = 0; j < numPairs(); j++){
				result += "<option value=\"" + getAnswerAt(j) + "\">" + getAnswerAt(j) + "</option>\r";
			}
			result += "</select> <br>\r";
		}
		result += "<br> \r";

		return result;
	}
	
	public static String returnHTMLBlankTemplate(int numPairs) {
		String result = "";
		result += "<form action=\"SubmitNewQuestionServlet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"matching-question\"> \r";
		result += "<input type=\"hidden\" name=\"numpairs\" value=\"" + numPairs + "\"> \r";
		result += "Enter instructions for question: <br> \r <input name=\"instructions\" type=\"text\"/> <br> \r";
		for(int i = 0; i < numPairs; i++){
			result += "Enter left column: <br> \r <input name=\"question" + i + "\" type=\"text\"/> <br><br>\r";
			result += "Enter right column option: <br> \r <input name=\"answer" + i + "\" type=\"text\"/><br><br> \r";
		}
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}
	
	public static String returnHTMLEditTemplate(MatchingQuestion quest, int index) {
		String result = "";
		result += "<form action=\"SubmitEditedQuestionServlet\" method=\"post\"> \r";
		result += "<input type=\"hidden\" name=\"type\" value=\"matching-question\"> \r";
		result += "<input type=\"hidden\" name=\"index\" value=\"" + index +"\"> \r"; 
		result += "<input type=\"hidden\" name=\"numpairs\" value=\"" + quest.numPairs() + "\"> \r";
		result += "Enter instructions for question: <br> \r <input name=\"instructions\" type=\"text\" value=\"" + quest.getInstructions() + "\"/> <br> \r";
		for(int i = 0; i < quest.numPairs(); i++){
			result += "Enter left column: <br> \r <input name=\"question" + i + "\" type=\"text\" value=\"" + quest.getPairAt(i).getQuestion() + "\"/> <br><br>\r";
			result += "Enter right column option: <br> \r <input name=\"answer" + i + "\" type=\"text\"  value=\"" + quest.getPairAt(i).getAnswer().getAnswerAt(0) + "\"/><br><br> \r";
		}
		result += "<input type=\"submit\" value=\"Submit\"/> \r";
		result += "</form> \r";
		return result;
	}
	
	@Override
	public String returnHTMLDisplayStatic() {
		String result = "";
		for(int i = 0; i < numPairs(); i++){
			ResponseQuestion curr = getPairAt(i);
			result += "Question: " + curr.getQuestion() + "\t";
			result += "Answer: " + curr.getAnswer().convertAnswerToString() + "<br> \r";
		}
		return result;
	}

}
