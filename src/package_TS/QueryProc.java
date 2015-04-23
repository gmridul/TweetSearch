package package_TS;

import java.util.*;

public class QueryProc {
	InvertedIndex index;
	
	public Stack<Integer> searchQuery(List<String> query, int topK) {
		
		int query_length = query.size();
		Iterator<BinaryHeap> iterForBH;
		Map<Integer,Float> totalEventScore = new HashMap<Integer,Float>();
		//It stores the iterator of BinaryHeap for each word in query 
		List<Iterator<BinaryHeap>> listOfIterBH = new ArrayList<Iterator<BinaryHeap>>(query_length);
		List<PriorityQueue<IntPair>> remainingEventsfromBuckets = new ArrayList<PriorityQueue<IntPair>>(query_length);
		//It stores the iterator of Events in the current BinaryHeap considered
		//List<Iterator<IntPair>> listOfIterOfEventsInBH = new ArrayList<Iterator<IntPair>>();
		
		int numRemainingWords = 0;
		IntPair peekIntPair = new IntPair() , nextIntPair = new IntPair();
		PriorityQueue<IntPair> topEvents = new PriorityQueue<IntPair>(new IntPairComparator());
		
		float minScore=101;
		for(String word : query) {
			iterForBH = index.IDIndex.get(word).iterator();
			if(iterForBH.hasNext()) {
				remainingEventsfromBuckets.add(iterForBH.next().maxHeap);
				peekIntPair.copy(remainingEventsfromBuckets.get(numRemainingWords).peek());
				peekIntPair.y = numRemainingWords;
				minScore = Math.min(minScore, peekIntPair.getScore());
				//tempBH.posQuery = numRemainingWords; 
				listOfIterBH.add(iterForBH);
				//tempBH.iterator().next().y = numRemainingWords; //position in the array of iterator, same position in both arrays
				//listOfIterOfEventsInBH.add(tempBH.iterator());
				totalEventScore.put(peekIntPair.x, peekIntPair.getScore());
				topEvents.add(remainingEventsfromBuckets.get(numRemainingWords).poll());
				
				//remainingEventsfromBuckets.get(numRemainingWords).push(tempBH.getMax());
				numRemainingWords++;
			
				
			}
		}
		
		if(topEvents.isEmpty()) {
			return new Stack<Integer>();
		}
		List<IntPair> redundantEvents = new ArrayList<IntPair>();
		
		while(numRemainingWords>0) {
			peekIntPair.copy(topEvents.peek());

			if(remainingEventsfromBuckets.get(peekIntPair.y).isEmpty()) {
				if(listOfIterBH.get(peekIntPair.y).hasNext()) {
					remainingEventsfromBuckets.add(peekIntPair.y,listOfIterBH.get(peekIntPair.y).next().maxHeap);
				}
				else {
					redundantEvents.add(topEvents.poll());
					numRemainingWords--;
				}
			}
			else {
				nextIntPair.copy(remainingEventsfromBuckets.get(peekIntPair.y).poll());
				if(topEvents.contains(nextIntPair))  {
					topEvents.remove(nextIntPair);
					nextIntPair.setScore(nextIntPair.getScore()+totalEventScore.get(nextIntPair.x));
					totalEventScore.put(nextIntPair.x,nextIntPair.getScore());
					topEvents.add(nextIntPair);
					minScore = Math.min(minScore, topEvents.peek().getScore());
				}
				else if(!topEvents.contains(nextIntPair) && totalEventScore.containsKey(nextIntPair.x)) {
					totalEventScore.put(nextIntPair.x, nextIntPair.getScore()+totalEventScore.get(nextIntPair.x));
				}
				else if(topEvents.size()+redundantEvents.size()<topK || peekIntPair.getScore() <= nextIntPair.getScore()*query_length) {
					topEvents.add(nextIntPair);
					totalEventScore.put(nextIntPair.x, nextIntPair.getScore());
					minScore = Math.min(minScore, nextIntPair.getScore());
				}
				else if(peekIntPair.getScore() > nextIntPair.getScore()*query_length) {
					redundantEvents.add(topEvents.poll());
					numRemainingWords--;
				}
			}
			
		}
		
		Stack<Integer> returnEvents = new Stack<Integer>();
		
		for(IntPair event : redundantEvents) {
			event.setScore(totalEventScore.get(event.x));
			topEvents.add(event);
		}
		
		PriorityQueue<IntPair> returnEventsPQ = new PriorityQueue<IntPair>(new maxIntPairComparator());
		returnEventsPQ.addAll(topEvents);
		while(!returnEventsPQ.isEmpty()) {
			returnEvents.push(returnEventsPQ.poll().x);
		}
		//Arrays.sort(topEvents.toArray(), new maxIntPairComparator());
		
		return returnEvents;
		
	}
	
	static class IntPairComparator implements Comparator<IntPair> {
		public int compare(IntPair p1, IntPair p2) {
			if(p1.getScore()<=p2.getScore()) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
	
	static class maxIntPairComparator implements Comparator<IntPair> {
		public int compare(IntPair p1, IntPair p2) {
			if(p1.getScore()>p2.getScore()) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
}
