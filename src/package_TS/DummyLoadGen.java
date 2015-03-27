package package_TS;

import java.util.*;
import java.lang.Math.*;

public class DummyLoadGen {
	int NumUsers;
	int NumWords;
	int MaxEventID;
	
	public DummyLoadGen() {
		this.NumUsers = 50000;
		this.NumWords = 5000;
		this.MaxEventID = 0;
	}
	
	public DummyLoadGen(int NumUsers , int NumWords) {
		this.NumUsers = NumUsers;
		this.NumWords = NumWords;
		this.MaxEventID = 0;
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
		int TweetLen = randInt(3,15);
		String[] Content = new String[TweetLen];
		
		for(int i=0 ; i < TweetLen ; i++) {
			Content[i] = "Word"+Integer.toString(randInt(1,NumWords));
		}
		
		String User = "User"+Integer.toString(randInt(1,NumWords));
		Integer eventID = new Integer(randInt(1,MaxEventID+1));
		MaxEventID = Math.max(eventID.intValue(),MaxEventID); 
	}
	
	
}
