package package_TS;
import java.util.*;

public class Heaps implements Iterable<BinaryHeap> {
	Vector<BinaryHeap> heapList = new Vector<BinaryHeap>();
	Map<Integer, Integer> eventBucket = new HashMap<Integer, Integer>();
	//float[] sortedKeys = new float[100];
	int lastBucket;
	int capLastBucket;
	
	public Heaps(){
		heapList.add(new BinaryHeap());
	}
	

	@Override
	public Iterator<BinaryHeap> iterator() {
		return heapList.iterator();
	}
	
	public void insert(IntPair e){
		float score = e.z;
		if (heapList.get(0).getSize()==0){
			lastBucket=0;
			capLastBucket=1;
			heapList.get(0).push(e);
			eventBucket.put(e.x, 0);
			return;
		}
		int i;
		boolean notInserted = true;
		for (i=0;i<=lastBucket;i++){
			if (score> heapList.get(i).getMin().z){
				heapList.get(i).push(e);
				eventBucket.put(e.x, i);
				notInserted = false;
				if (heapList.get(i).getSize() <= Math.pow(2, i)){
					return;
				}
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
		
		IntPair prev = heapList.get(i).minS;
		heapList.get(i).remove(prev);
		i++;
		while (i<=lastBucket){
			heapList.get(i).push(prev);
			eventBucket.put(prev.x,i );
			if (heapList.get(i).getSize() <= Math.pow(2, i)){
				return;
			}
			prev = heapList.get(i).minS;
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
