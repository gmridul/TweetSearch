package package_TS;

import java.util.*;

public class InvertedIndex {
	
	Map<String, List<Integer>> IDIndex = new HashMap<String,List<Integer>>();
	Map<Integer, List<Tweet>> threadIndex = new HashMap<Integer,List<Tweet>>();
	int totalEvents;
	
	public void update(Tweet tweet) {
		List<Tweet> tempTweet = threadIndex.get(tweet.EventID);
		tempTweet.add(tweet);
		threadIndex.put(tweet.EventID,tempTweet);
		
		for(String st : tweet.Content) {
			if(IDIndex.containsKey(st)) {
				if(!IDIndex.get(st).contains(tweet.EventID)) {
					List<Integer> tempInt = IDIndex.get(st);
					tempInt.add(tweet.EventID);
					Collections.sort(tempInt); // fill the parameters of sort function
				}
			}
		}
	}
	
	public int getTotalEvents() {
		return totalEvents;
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
