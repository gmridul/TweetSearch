package package_TS;

import java.util.*;

public class DummyLoadGen {
	int numUsers;
	int numWords;
	int numEvents;
	int maxEventID;
	Map<String,Integer> mappy;
	
	public DummyLoadGen() {
		this.numUsers = 50000;
		this.numWords = 5000;
		this.numEvents = 50;
		this.maxEventID = 0;
	}
	
	public DummyLoadGen(int NumUsers , int NumWords , int NumEvents, List<String> query) {
		this.numUsers = NumUsers;
		this.numWords = NumWords;
		this.numEvents = NumEvents;
		this.maxEventID = 0;
		mappy = new HashMap<String,Integer>();
		for (String s :query){
			mappy.put(s, 1);
		}
	}
	
	
	//source : stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
	private int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public Tweet sendLoad(int k, PriorityQueue<IntPair> answer) {
		int tweetLen = randInt(2,5);
//		tweetLen=2;
//		if (k==0)tweetLen=5;
//		if (k==1)tweetLen=1;
//		if (k==2)tweetLen=2;
		String[] content = new String[tweetLen];
		Set<String> hack = new HashSet<String>();
		for(int i=0 ; i < tweetLen ; i++) {
			
			content[i] = "Word"+Integer.toString(randInt(1,numWords));
		//	content[i] = "Word" + Integer.toString(i);
		}
//		if (k==0)content[2] = "Word0";
//		if (k==0)content[3] = "Word0";
//		if (k==0)content[4] = "Word1";
//		if (k==1)content[0] = "Word2";
//		if (k==2)content[0] = "Word2";
//		if (k==2)content[1] = "Word1";
		//content[tweetLen-1] = "Word";
	//	String user = "User"+Integer.toString(randInt(1,numWords));
		int eventID;
		if(maxEventID < numEvents) {
			eventID = randInt(1,maxEventID+1);
			maxEventID = Math.max(eventID,maxEventID);
		}
		
		else {
			eventID = randInt(1,maxEventID);
		}
		int score = randInt(50,100); 
//		if (k==0)score = 53;
//		if (k==1)score = 73;
//		if (k==2)score = 57;
		eventID = k;
		Tweet tweet = new Tweet(eventID , content , score);
		System.out.println(tweet.EventID);
		int temps = 0;
		for(String c: tweet.Content) {
			System.out.println(c);
			hack.add(c);
		}
		for (String c: hack){
			if (mappy.get(c)!=null)
				temps += score;
		}
		
		answer.add(new IntPair(temps,eventID,0));
		System.out.println(tweet.Score);
		return tweet;
	}
	
	
}
