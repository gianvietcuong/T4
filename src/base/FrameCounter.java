package base;

public class FrameCounter {
    int countMax;
    public int count;

    public FrameCounter(int countMax){
        this.countMax = countMax;
        this.count = 0;
    }

    public boolean run(){
        if(count < countMax){
            count++;
            return false;
        }else{
            return true;
        }
    }

    public void reset(){
        this.count = 0;
    }

}
