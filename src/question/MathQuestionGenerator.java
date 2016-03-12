package question;

import java.util.Random;

public class MathQuestionGenerator {
	
	int difficulty;		//max number of digits in question + 3 (enter val between 1 - 7)
	int type;			//1 if addition, 2 if subtraction, 3 if multiplication, 4 if division, random is any other number

	public MathQuestionGenerator(int difficulty, int type) {
		this.difficulty = difficulty;
		if(difficulty > 7) this.difficulty = 7;
		if(difficulty < 1) this.difficulty = 1;
		this.type = type;
		if(type > 5) this.type = 5;
		if(type < 1) this.type = 5;
	}
	
	public ResponseQuestion generateQuestion(){
		int currtype = type;
		int firstNum;
		int secondNum;
		int digitsLeft = difficulty + 1;
		Random rand = new Random();
		if(type == 5){
			currtype = rand.nextInt(4) + 1;
		}
		
		int firstDigits = rand.nextInt(digitsLeft) + 1;
		digitsLeft = digitsLeft - firstDigits;
		firstNum = rand.nextInt(maxNumber(firstDigits+1));
		secondNum = rand.nextInt(maxNumber(digitsLeft+1));
		
		if(currtype == 1){
			Integer first = firstNum;
			Integer second = secondNum;
			Integer answer = first + second;
			String question = first.toString() + " + " + second.toString() + " = ?";
			return new ResponseQuestion(question, answer.toString());
		} else if (currtype == 2){
			Integer first = firstNum;
			Integer second = secondNum;
			if(difficulty < 5){
				if (first < second){
					first = secondNum;
					second = firstNum;
				}
			}
			Integer answer = first - second;
			String question = first.toString() + " - " + second.toString() + " = ?";
			return new ResponseQuestion(question, answer.toString());
		} else if (currtype == 3){
			Integer first = firstNum;
			Integer second = secondNum;
			Integer answer = first * second;
			String question = first.toString() + " x " + second.toString() + " = ?";
			return new ResponseQuestion(question, answer.toString());
		} else {
			
			double first = firstNum;
			double second = secondNum;
			if (first < second){
				first = secondNum;
				second = firstNum;
			}
			if(second == 0) second++;	//avoid dividing by 0
			double answer = first / second;
			String question = first + " / " + second + " = ?";
			String newAnswer = "" + answer;
			return new ResponseQuestion(question, newAnswer);
		}
		
		
	}
	
	private int maxNumber(int digits){
		String preresult = "";
		for (int i = 0; i < digits; i++){
			preresult += "9";
		}
		return Integer.valueOf(preresult);
	}

}
