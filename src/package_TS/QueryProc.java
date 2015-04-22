package package_TS;

import java.util.*;

import package_TS.QueryProcessor.IntPairComparator;

public class QueryProc {
	InvertedIndex index;
	
	public Stack<Integer> searchQuery(List<String> query, int topK) {
		
		int query_length = query.size();
		Iterator<BinaryHeap> iterForBH;
		
		//It stores the iterator of BinaryHeap for each word in query 
		List<Iterator<BinaryHeap>> listOfIterBH = new ArrayList<Iterator<BinaryHeap>>();
		
		//It stores the iterator of Events in the current BinaryHeap considered
		List<Iterator<IntPair>> listOfIterOfEventsInBH = new ArrayList<Iterator<IntPair>>();
		
		int numRemainingWords = 0;
		IntPair peekIntPair = new IntPair() , nextIntPair = new IntPair();
		PriorityQueue<IntPair> topEvents = new PriorityQueue<IntPair>(new IntPairComparator());
		
		float minScore=101;
		for(String word : query) {
			iterForBH = index.IDIndex.get(word).iterator();
			if(iterForBH.hasNext()) {
				BinaryHeap tempBH = iterForBH.next();
				if(tempBH.getSize()>0) {
					minScore = Math.min(minScore, tempBH.getMax().getScore());
					//tempBH.posQuery = numRemainingWords; 
					listOfIterBH.add(iterForBH);
					tempBH.iterator().next().y = numRemainingWords; //position in the array of iterator, same position in both arrays
					listOfIterOfEventsInBH.add(tempBH.iterator());
					topEvents.add(tempBH.getMax());
					numRemainingWords++;
				}
				
			}
		}
		
		if(topEvents.isEmpty()) {
			return new Stack<Integer>();
		}
		
		while(numRemainingWords>0) {
			peekIntPair.copy(topEvents.peek());
			if(peekIntPair.isValid() && listOfIterOfEventsInBH.get(peekIntPair.y).hasNext()) {
				nextIntPair = listOfIterOfEventsInBH.get(peekIntPair.y).next();
				if(nextIntPair.getScore()*query_length < peekIntPair.getScore()) {
					peekIntPair.setValid(false);
					numRemainingWords--;
				}
			}
		}
		
		
		
	}
}
