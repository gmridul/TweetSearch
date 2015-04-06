package package_TS;

import java.util.*;
import java.lang.Math.*;

public class DummyLoadGen {
	int numUsers;
	int numWords;
	int numEvents;
	int maxEventID;
	
	public DummyLoadGen() {
		this.numUsers = 50000;
		this.numWords = 5000;
		this.numEvents = 50;
		this.maxEventID = 0;
	}
	
	public DummyLoadGen(int NumUsers , int NumWords , int NumEvents) {
		this.numUsers = NumUsers;
		this.numWords = NumWords;
		this.numEvents = NumEvents;
		this.maxEventID = 0;
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
	
	public void sendLoad() {
		int tweetLen = randInt(3,15);
		String[] content = new String[tweetLen];
		
		for(int i=0 ; i < tweetLen ; i++) {
			content[i] = "Word"+Integer.toString(randInt(1,numWords));
		}
		
		String user = "User"+Integer.toString(randInt(1,numWords));
		int eventID;
		if(maxEventID < numEvents) {
			eventID = randInt(1,maxEventID+1);
			maxEventID = Math.max(eventID,maxEventID);
		}
		
		else {
			eventID = randInt(1,maxEventID);
		}
		int score = randInt(50,100); 
		
		Tweet tweet = new Tweet(eventID , content , score);
		
	}
	
	
}
