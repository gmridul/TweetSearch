package package_TS;

//source : stackoverflow.com/questions/10234487/storing-number-pairs-in-java

public class IntPair {
    int x; 
    int y; 
    
    public IntPair() {
  
    }
    
    public IntPair(int first, int second) {
        this.x = first;
        this.y = second;
    }

    public void setFirst(int first) {
        this.x = first;
    }

    public void setSecond(int second) {
        this.y = second;
    }

    public int getFirst() {
        return x;
    }

    public int getSecond() {
        return y;
    }
}
