package package_TS;

import java.util.*;

public class BinaryHeap {
	
	Comparator<IntPair> minCompare = new Comparator<IntPair>() {
		//@Override
		public int compare(IntPair elem1, IntPair elem2) {
			if (elem1.z>elem2.z) return -1;
			else return 1;
		}
	};
	
	Comparator<IntPair> maxCompare = new Comparator<IntPair>() {
		//@Override
		public int compare(IntPair elem1, IntPair elem2) {
			if (elem1.z>elem2.z) return 1;
			else return -1;
		}
	};
	
	PriorityQueue<IntPair> minHeap = new PriorityQueue<IntPair>(minCompare);
	PriorityQueue<IntPair> maxHeap = new PriorityQueue<IntPair>(maxCompare);
	IntPair minS,maxS;
	
	public BinaryHeap(){
		
	}
	public int getSize(){
		return minHeap.size();
	}
	
	public void push(IntPair e){
		minHeap.add(e);
		maxHeap.add(e);
	}
	
	public void remove(IntPair e){
		minHeap.remove(e);
		maxHeap.remove(e);
	}
	
	public IntPair getMin(){
		return minHeap.peek();
	}
	
	public IntPair getMax(){
		return maxHeap.peek();
	}
	
}
