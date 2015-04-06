package package_TS;

import java.util.*;

public class QueryProcessor {
	InvertedIndex index;
	
	// return the list of event ids which are relevant to query
	public List<Integer> searchQuery(List<String> query) {
		
		List<Integer> eventList = new LinkedList<Integer>(); // stores the relevant events
		
		int n = query.size(), R = 2, candidateEvent = 0, numOccurCandidateEvent=0;
		List<Iterator<Integer>> iterEvents = new ArrayList<Iterator<Integer>>(); // stores the iterators of the inverted index of words in query 
		Iterator<Integer> iter;
		IntPair p = new IntPair();
		PriorityQueue<IntPair> minEventWord = new PriorityQueue<IntPair>(n,new IntPairComparator()); 
		int iterEventPos=0;
		
		for(String q : query) {
			iter=index.IDIndex.get(q).iterator();
			if(iter.hasNext()) {
				iterEvents.add(iter);
				p.setFirst(iter.next().intValue());
				p.setSecond(iterEventPos);
				minEventWord.add(p);
				iterEventPos++;
			}
		}
		
		while(!minEventWord.isEmpty()) {
			p = minEventWord.poll();
			if(p.x == candidateEvent) {
				numOccurCandidateEvent++;
				if(numOccurCandidateEvent==n/R) {
					eventList.add(candidateEvent);
				}
			}
			else {
				candidateEvent = p.x; 
				numOccurCandidateEvent=1;
			}
			if(iterEvents.get(p.y).hasNext()) {
				p.x = iterEvents.get(p.y).next();
				minEventWord.add(p);
			}
		}
		return eventList;
	}

	static class IntPairComparator implements Comparator<IntPair> {
		public int compare(IntPair p1, IntPair p2) {
			if(p1.getFirst()<p2.getFirst()) {
				return 1;
			}
			else if(p2.getFirst()<p1.getFirst()) {
				return -1;
			}
			else if(p1.getSecond()<=p2.getSecond()) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}
	
}