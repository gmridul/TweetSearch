package package_TS;

import java.util.*;

public class QueryProcessor {
	InvertedIndex index;
	
	// return the list of event ids which are relevant to query
	public Stack<Integer> searchQuery(List<String> query, int topK) {
		
		int n = query.size();
		int numRemainingWords = 0;
		float minScore=0;
		List<Iterator<IntPair>> iterEvents = new ArrayList<Iterator<IntPair>>(); // stores the iterators of the inverted index of words in query 
		Iterator<IntPair> iter;
		IntPair p = new IntPair(), q = new IntPair();
		List<PriorityQueue<IntPair>> minEventWord = new ArrayList<PriorityQueue<IntPair>>(2);
		minEventWord.set(0,new PriorityQueue<IntPair>(new IntPairComparator()));
		minEventWord.set(1,new PriorityQueue<IntPair>(new IntPairComparator()));
		
		int mainPQ=0;
		
		for(String w : query) {
			iter=index.IDIndex.get(w).iterator();
			if(iter.hasNext()) {
				iterEvents.add(iter);
				p.copy(iter.next());
				p.setSecond(numRemainingWords);
				minEventWord.get(0).add(p);
				minScore = Math.min(minScore,p.z);
				numRemainingWords++;
			}
		}
		
		//p.x contains event id, p.y contains array pos, p.z is the score
		
		while(numRemainingWords > 0) {
			if(minEventWord.get(mainPQ).isEmpty()) { mainPQ = (mainPQ+1)%2; }
			p.copy(minEventWord.get(mainPQ).peek());
			
			if(p.isValid() && iterEvents.get(p.y).hasNext()) {
				q.copy(iterEvents.get(p.y).next());
				if(q.getScore()*n < p.getScore()) {
					minEventWord.get((mainPQ+1)%2).add(minEventWord.get(mainPQ).poll());
					p.setValid(false);
					numRemainingWords--;
				}
				else {
					minEventWord.get(mainPQ).add(q);
				}
			}
			else {
				minEventWord.get((mainPQ+1)%2).add(minEventWord.get(mainPQ).poll());
			}
		}
		
		int mainPQsize = minEventWord.get(mainPQ).size();
		
		while(mainPQsize>topK) {
			minEventWord.get(mainPQ).poll();
			mainPQsize--;
		}
		int otherPQsize = minEventWord.get((mainPQ+1)%2).size();
		
		while(mainPQsize!=topK && mainPQsize+otherPQsize>topK) {
			minEventWord.get((mainPQ+1)%2).poll();
		}
		Stack<Integer> finalResult = new Stack<Integer>();
		while(!minEventWord.get((mainPQ+1)%2).isEmpty()) {
			finalResult.push(minEventWord.get((mainPQ+1)%2).poll().x);
		}
		while(!minEventWord.get(mainPQ).isEmpty()) {
			finalResult.push(minEventWord.get(mainPQ).poll().x);
		}
		
		return finalResult;
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
	
}