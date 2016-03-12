



package user;

import java.util.ArrayList;
import messages.Message;
import tableabstraction.TableAbstraction;

public class User implements java.io.Serializable{
	public static final int AMATEUR = 1;
	public static final int PROLIFIC = 2;
	public static final int PRODIGIOUS = 4;
	public static final int MACHINE = 8;
	public static final int GREATEST = 16;
	public static final int PRACTICE = 32;
	public static final int[] ACHIEVEMENTS = 
	{AMATEUR,PROLIFIC,PRODIGIOUS,MACHINE,GREATEST,PRACTICE};
	public static String [] ACHIEVE_STRINGS = {
		"Amateur Author—The user created a quiz.",
		"Prolific Author—The user created five quizzes.",
		"Prodigious Author—The user created ten quizzes.",
		"Quiz Machine—The user took ten quizzes.",
		"I am the Greatest—The user had the highest score on a quiz.",
		"Practice Makes Perfect—The user took a quiz in practice mode."
	};
	private int achieved;
	private int uid;
	public void setID(int id){
		uid = id;
	}
	public int getID(){return uid;}
		public String describeAchievement(int i){
			return ACHIEVE_STRINGS[i];
		}
		public boolean hasAchieved(int a){
			return (this.achieved & a) != 0;
		}
		public void achieved(int a){
			this.achieved |= a;
		}
		
		private ArrayList<FriendEntry> friendActivity;
		
		public void updateLogs(FriendEntry s, java.sql.Connection con, ArrayList<String>exclude){
			for(String friend: getFriends()){
				User f = TableAbstraction.getUser(friend, con);
				if(f!=null && !exclude.contains(friend)){
				f.friendLog(s);
				TableAbstraction.updateUser(friend, f, con);
				}
			}
		}
		public void friendLog(FriendEntry s){
			if(friendActivity.size() == 3) friendActivity.remove(2);
			friendActivity.add(0, s);
		}
		
	private static final long serialVersionUID = 1L;    
	static int BIO_CHAR_LIMIT = 150;
	static int FRIEND_LIM = 5;              
	private String hashPassword, displayName, profileImageString;    
	private String bio, currentStatus;
	private ArrayList<String> previousStatuses;
	private ArrayList<String> friends;//display top five//
	private ArrayList<FriendEntry> quizHistory;//all quizzes
	private ArrayList<FriendEntry> recentActivity;
   	private int careerScore;    
	private int friendCount;
	private boolean adminStatus = false;
	private ArrayList<Message> messages;
	private boolean isPrivate;
	private String salt;
	private int requestCount, challengeCount, messageCount;
	
	private int quizzesTaken, quizzesAuthored;
	public void takeQuiz(){
		quizzesTaken++;
		switch(quizzesTaken){
		case 10: achieved(MACHINE);break;
		}
	}
	public void makeQuiz(){
		quizzesAuthored++;
		switch(quizzesAuthored){
		case 1: achieved(AMATEUR);break;
		case 5: achieved(PROLIFIC);break;
		case 10: achieved(PRODIGIOUS);break;
		}
	}
	public void practiced(){
		achieved(PRACTICE);
	}
	public int getTaken(){
		return quizzesTaken;
	}
	public int getAuthored(){
		return quizzesAuthored;
	}

	public ArrayList<FriendEntry> getFriendLog(){
		return friendActivity;
	}


	public User(String uniqueUserId,String hashPassword, String salt) {
		this.quizzesTaken = 0;
		this.quizzesAuthored = 0;
		friendActivity = new ArrayList<FriendEntry>();
		this.displayName = uniqueUserId;
		this.friendCount = 0;
		this.careerScore = 0;
		this.requestCount = this.challengeCount = this.requestCount = 0;
		friends = new ArrayList<String>();
		previousStatuses = new ArrayList<String>();
		quizHistory = new ArrayList<FriendEntry>();
		profileImageString = "defaultImg.png";
		messages = new ArrayList<Message>();
		recentActivity = new ArrayList<FriendEntry>();
		this.hashPassword = hashPassword;
		this.salt = salt;
		this.achieved = 0;
		
	}
	
	public void setDisplayName(String newDisplayName){
		this.displayName = newDisplayName;
	}

	public String getDisplayName(){
		return this.displayName;
	}
	public String getPassword(){
		return hashPassword;
	}
	public String getSalt(){
		return salt;
	}
	public void setImage(String newImageString){
		this.profileImageString = newImageString;
	}

	public String getImage(){
			return this.profileImageString;
	}

	public boolean setBio(String str){
		if(str.length() > BIO_CHAR_LIMIT){
			return false;
		}
		this.bio = str;
		return true;
	}

	public String getBio(){
		if(this.bio != null) {
			return this.bio;
		}
		return "";
	}

	public void setStatus(String newStatus){
		this.currentStatus = newStatus;
		previousStatuses.add(newStatus);  
	}  

	public String getStatus(){
		if(this.currentStatus != null){
			return this.currentStatus;
		}
		return "";
	}

	public void addFriend(String friendUniqueUserID){
		if(!friends.contains(friendUniqueUserID)){
			friends.add(friendUniqueUserID);
			this.friendCount++;
		}
	}

	public void removeFriend(String friendUniqueUserID){
		if(friends.contains(friendUniqueUserID)){
			friends.remove(friendUniqueUserID);
			this.friendCount--;
		}
	}
	
	public ArrayList<String> getFriends(){ 
		return friends;
	}
	
	
	
	public void addQuiz(String quizname, int id, boolean created){
		if(created) makeQuiz();
		else takeQuiz();
		FriendEntry entry = new FriendEntry("You", quizname,created,id);
		addRecent(entry);
		quizHistory.add(0,entry);
	}
	public ArrayList<FriendEntry> getRecent(){
		return recentActivity;
	}
	public void addRecent(FriendEntry e){
		if(recentActivity.size() == 3) recentActivity.remove(2);
		recentActivity.add(0, e);
	}
	
	public ArrayList<FriendEntry> getQuizzes(){
		return quizHistory;
	}
	
	public void setScore(int newScore){
		this.careerScore = newScore;
	}
	

	public int getScore(){
		return this.careerScore;
	}

	public void setFriendCount(){
		this.friendCount = friends.size();
	}

	public int getFriendCount(){
		return this.friendCount;   
	}
	
	public boolean containsFriend(String friendUniqueId){
		for(int i = 0; i < friends.size(); i++){
			if(friends.get(i).equals(friendUniqueId)) return true;
		}
		return false;
	}

	public void promoteToAdmin(){
		adminStatus = true;
	}

	public boolean getAdminStatus(){
		return adminStatus;
	}
	public void addMessageToInbox(Message msg){
		messages.add(msg);
		String type = msg.getType();
		if(type.equals("TextMessage")) messageCount++;
		else if(type.equals("FriendRequest")) requestCount++;
		else challengeCount++;
	}
	public int messageCount(){
		return messageCount + requestCount + challengeCount;
	}
	public int fCount(){
		return requestCount;
	}
	public int mCount(){
		return messageCount;
	}
	public int cCount(){
		return challengeCount;
	}
	
	public void deleteMessage(int index){
		if(index >= messages.size()) return;
		readMessage(index);
		messages.remove(index);
	}
	public void readMessage(int index){
		if(index >= messages.size()) return;
		Message m = messages.get(index);
		if(!m.read()){
			m.markAsRead();
			String type = m.getType();
			if(type.equals("TextMessage")) messageCount--;
			else if(type.equals("FriendRequest")) requestCount--;
			else challengeCount--;
		}
	}
	
	public ArrayList<Message> getMessages(){
		return messages;
	}
	
	public int getMessageCount(){
		return messages.size();
	}
	
	public void setPrivate(boolean status){
		this.isPrivate = status;
	}
	
	public boolean isPrivate(){
		return isPrivate;
	}
}
