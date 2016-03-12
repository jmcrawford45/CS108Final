package quiz;

public class Performance implements Comparable<Performance>{
	public int id;
	public int user_id;
	public int quiz_id;
	public int score;
	public long start;
	public long time;
	

	
	Performance(int id, int user_id, int quiz_id, int score, long start, long time) {
		this.id = id;
		this.user_id = user_id;
		this.quiz_id = quiz_id;
		this.score = score;
		this.start = start;
		this.time = time;
	}

	
	@Override
	public int compareTo(Performance o) {
		if (o.score == this.score) {
			return compVal((int)this.time, (int)o.time);
		}
		return compVal(o.score, this.score);
	}
	private int compVal(int a, int b){
		if(a > b) return 1;
		else if( b > a) return -1;
		return 0;
	}

}
