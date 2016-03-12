package question;

import java.util.ArrayList;

public class Answer {

	private ArrayList<String> answers;
	int answerID;
	boolean ordered;
	
	
	public Answer(String answer){
		ArrayList<String> array = new ArrayList<String>();
		array.add(answer);
		this.answers = array;
		this.ordered = true;
	}
	
	public Answer(String answer, boolean ordered){
		ArrayList<String> array = new ArrayList<String>();
		array.add(answer);
		this.answers = array;
		this.ordered = ordered;
	}
	
	public Answer(ArrayList<String> answers){
		this.answers = answers;
		this.ordered = true;
	}
	
	public Answer(ArrayList<String> answers, boolean ordered){
		this.answers = answers;
		this.ordered = ordered;
	}
	
	public Answer(){
		this.answers = new ArrayList<String>();
		this.ordered = true;
	}
	
	public Answer(boolean ordered){
		this.answers = new ArrayList<String>();
		this.ordered = ordered;
	}
	
	public void setIfOrdered(boolean ordered){
		this.ordered = ordered;
	}
	
	public boolean hasMultipleAnswers(){
		return (answers.size() > 1);
	}
	
	public int getID(){
		return this.answerID;
	}
	
	public void setID(int id){
		this.answerID = id;
	}
	
	public String firstAnswer(){
		return answers.get(0);
	}
	
	public int numAnswers(){
		return answers.size();
	}
	
	public String getAnswerAt(int index){
		return answers.get(index);
	}
	
	public String[] getAnswerArrayAt(int index){
		return answers.get(index).split("\\|");
	}
	
	public void addAnswer(String newAnswer){
		this.answers.add(newAnswer);
	}
	
	public boolean isOrdered(){
		return this.ordered;
	}
	
	
	public void replaceAnswer(String switchedAnswer, int index){
		this.answers.set(index, switchedAnswer);
	}
	
	public void removeAnswer(int index){
		this.answers.remove(index);
	}
	
	public boolean isCorrectSingle(String input){
		String[] answer = answers.get(0).split("\\|");
		return isCorrectSingleAnswer(answer, input);
	}
	
	public boolean isCorrect(Answer userInput){
		if(userInput.numAnswers() != numAnswers()) return false;
		if(ordered){
			for(int i = 0; i < userInput.numAnswers(); i++){
				if(!isCorrectSingleAnswer(answers.get(i).split("\\|"), userInput.getAnswerAt(i))) return false;
			}
			return true;
		} else {
			iter: for (int a = 0; a < numAnswers(); a++){
				String[] answer = answers.get(a).split("\\|");
				for (int i = 0; i < userInput.numAnswers(); i++){
					if (isCorrectSingleAnswer(answer, userInput.getAnswerAt(i))) continue iter;
				}
				return false;
			}
			return true;
		}
	}
	
	private boolean isCorrectSingleAnswer(String [] options, String input){
		for (int i = 0; i < options.length; i++){
			if (options[i].toUpperCase().equals(input.toUpperCase())) return true;
		}
		return false;
	}
	
	public String convertAnswerToString(){
		String result = "";
		for (int answer = 0; answer < numAnswers(); answer++){
			if(answer != 0) result += "||";
			result += getAnswerAt(answer);
		}
		return result;
	}
	
	public static Answer convertStringToAnswer(String answerString){
		String[] answersArray =  answerString.split("\\|\\|");
		ArrayList<String> answerList = new ArrayList<String>();
		for(int i = 0; i < answersArray.length; i++){
			answerList.add(answersArray[i]);
		}
		Answer result = new Answer(answerList);
		return result;
	}
	
	public static Answer convertStringToAnswer(String answerString, boolean ordered){
		String[] answersArray =  answerString.split("\\|\\|");
		ArrayList<String> answerList = new ArrayList<String>();
		for(int i = 0; i < answersArray.length; i++){
			answerList.add(answersArray[i]);
		}
		Answer result = new Answer(answerList);
		result.setIfOrdered(ordered);
		return result;
	}
	
	
}
