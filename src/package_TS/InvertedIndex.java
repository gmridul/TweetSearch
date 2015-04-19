package package_TS;

import java.util.*;

public class InvertedIndex {
	
	Map<String, Heaps> IDIndex = new HashMap<String,Heaps >(); // word to Event ID
	Map<Integer, List<Tweet>> threadIndex = new HashMap<Integer,List<Tweet>>(); //eventId to event
	
	public void insert(int id, int score){
		
	}
	
	
	public void update(Tweet tweet) {
		List<Tweet> tempTweet = threadIndex.get(tweet.EventID);
		tempTweet.add(tweet);
		threadIndex.put(tweet.EventID,tempTweet);
		
		for(String st : tweet.Content) {
			if(IDIndex.containsKey(st)) {
				if(IDIndex.get(st).eventBucket.get(tweet.EventID)==null) {
					IDIndex.get(st).insert(new IntPair(tweet.EventID,0,tweet.Score));
				}
			}
		}
		
		
	}
	
	
	
	/*put event id type, event class type in Pair template parameters
	 * Current Assumption : event id type : Integer		event thread type : String[]
	 * InvertedIndex are stored as Map.
	 */
	/*public Pair<Map<String,Integer>,Map<Integer,String[]>> CreateIndex(Pair<Integer,String[]>[] events) {
		Map<String,Integer> IDIndex = new HashMap<String,Integer>();
		Map<Integer, String[]> ThreadIndex = new HashMap<Integer,String[]>();
		
		for(Pair<Integer,String[]> e : events) {
			ThreadIndex.put(e.getFirst(), e.getSecond());
			
			for(String st : e.getSecond()) {
				IDIndex.put(st, e.getFirst());
			}
			
		}
		
		Pair<Map<String,Integer>,Map<Integer,String[]>> p = new Pair<Map<String,Integer>,Map<Integer,String[]>>(IDIndex,ThreadIndex);
		return p;
	}*/
}
