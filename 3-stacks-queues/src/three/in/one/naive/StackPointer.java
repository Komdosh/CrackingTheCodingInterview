package three.in.one.naive;

public class StackPointer {

    public int start = -1;
    public int end = -1;

    public int getSize(){
        if(isInitiated()) {
            return end - start;
        }
        return 0;
    }

    public void init(int start){
        this.start = start;
        this.end = start;
    }

    public boolean isInitiated(){
        return start != -1;
    }
}
