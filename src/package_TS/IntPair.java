package package_TS;

//source : stackoverflow.com/questions/10234487/storing-number-pairs-in-java

public class IntPair {
    int x; 
    int y; 
    float z;
    boolean valid;
    
    public IntPair() {
    	valid=true;
    }
    
    public IntPair(int first, int second, float third) {
        this.x = first;
        this.y = second;
        this.z = third;
        this.valid = true;
    }

    public void setFirst(int first) {
        this.x = first;
    }

    public void setSecond(int second) {
        this.y = second;
    }

    public void setScore(float third) {
    	this.z = third;
    }
    
    public void setValid(boolean b) {
    	this.valid = b;
    }
    
    public void copy(IntPair p) {
    	this.x = p.x;
    	this.y = p.y;
    	this.z = p.z;
    	this.valid = p.valid;
    }
    
    public int getFirst() {
        return x;
    }

    public int getSecond() {
        return y;
    }
    
    public float getScore() {
    	return z;
    }
    
    public boolean isValid() {
    	return valid;
    }
}

