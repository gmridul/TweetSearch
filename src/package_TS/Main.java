package package_TS;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueryProc engine = new QueryProc();
		List<String> query = new ArrayList<String>();
		query.add("Word1");
		query.add("Word2");
		query.add("Word5");
		query.add("Word8");
		query.add("Word9");
		query.add("Word7");
		PriorityQueue<IntPair> answer = new PriorityQueue<IntPair>(new IntPairComparator());;
		
		
		DummyLoadGen dummy = new DummyLoadGen(1000,10,10,query);
		for(int i=0;i<130;i++) {
			engine.index.update(dummy.sendLoad(i,answer));
		}
		
		int topK=5;
		
		Stack<Integer> s = engine.searchQuery(query,topK);
		
		while(!s.isEmpty()) {
			System.out.print("Bezu ");
			System.out.println(s.pop());
		}
		
		while (!answer.isEmpty() && topK-->0){
			IntPair e = answer.peek();
			answer.poll();
			System.out.print(e.y);
			System.out.print(" ");
			System.out.println(e.x);
		}
		
	}
	
	static class IntPairComparator implements Comparator<IntPair> {
		public int compare(IntPair p1, IntPair p2) {
			if(p1.x<=p2.x) {
				return 1;
			}
			else {
				return -1;
			}
		}
	}

}
