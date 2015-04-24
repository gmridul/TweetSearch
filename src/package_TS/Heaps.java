package package_TS;
import java.util.*;

public class Heaps implements Iterable<BinaryHeap> {
	List<BinaryHeap> heapList = new ArrayList<BinaryHeap>();
	Map<Integer, Integer> eventBucket = new HashMap<Integer, Integer>();
	//float[] sortedKeys = new float[100];
	int lastBucket;
	int capLastBucket;
	
	public Heaps(int k){
		heapList.add(new BinaryHeap());
		heapList.get(0).push(new IntPair(-k,0,-1));
		heapList.add(new BinaryHeap());
	}
	

	@Override
	public Iterator<BinaryHeap> iterator() {
		return heapList.iterator();
	}
	
	public void insert(IntPair e){
		float score = e.z;
		if (heapList.get(1).getSize()==0){
			lastBucket=1;
			capLastBucket=1;
			heapList.get(1).push(e);
			eventBucket.put(e.x, 1);
			return;
		}
		int i;
		boolean notInserted = true;
		for (i=1;i<=lastBucket;i++){
			if (score > heapList.get(i).getMin().z) {
				heapList.get(i).push(e);
				eventBucket.put(e.x, i);
				notInserted = false;
				if (heapList.get(i).getSize() <= Math.pow(2, i)){ // modify power function
					return;
				}
				else break;
			}		
		}
		
		if (notInserted){
			if (heapList.get(lastBucket).getSize() < Math.pow(2, lastBucket) ){
				heapList.get(lastBucket).push(e);
				eventBucket.put(e.x,lastBucket );
				return;
			}
			else{
				heapList.add(new BinaryHeap());
				lastBucket++;
				heapList.get(lastBucket).push(e);
				eventBucket.put(e.x,lastBucket );
				return;
			}
		}
		
		IntPair prev = heapList.get(i).getMin();
		heapList.get(i).remove(prev);
		i++;
		while (i<=lastBucket){
			heapList.get(i).push(prev);
			eventBucket.put(prev.x,i );
			if (heapList.get(i).getSize() <= Math.pow(2, i)){
				return;
			}
			prev = heapList.get(i).getMin();
			heapList.get(i).remove(prev);
			i++;
		}
		heapList.add(new BinaryHeap());
		lastBucket++;
		heapList.get(lastBucket).push(prev);
		eventBucket.put(prev.x,lastBucket );
		return;
		
	}
}
