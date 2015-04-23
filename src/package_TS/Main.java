package package_TS;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueryProc engine = new QueryProc();
		DummyLoadGen dummy = new DummyLoadGen(1000,2,10);
		for(int i=0;i<3;i++) {
			engine.index.update(dummy.sendLoad(i));
		}
		
		List<String> query = new ArrayList<String>();
		query.add("Word1");
		query.add("Word2");
		
		Stack<Integer> s = engine.searchQuery(query,5);
		
		while(!s.isEmpty()) {
			System.out.print("Bezu ");
			System.out.println(s.pop());
		}
		
	}

}
