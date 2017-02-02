package epam.java.se;

/**
 * Created by Katerina on 01.02.2017.
 */
public class IntSet {
    private long[] data = new long[1];

    public IntSet(){

    }
    public IntSet(long[] value){
        this.data = value;
    }

    public long[] getData() {
        return data;
    }

    public void setData(long[] data) {
        this.data = data;
    }

    public void add(int value) {
        int index = value/64;
        if(index==-1){
            return;
        }
        else{
            if(index!=-1 && index>=data.length){
                long[] newSet = new long[index+1];
                System.arraycopy(data,0,newSet,0,data.length);
                data = newSet;
            }
            int offset = value%64;
            long mask = 1L <<offset;
            data[index] |=mask;
        }
    }

    public boolean contains(int value) {
        int index = value/64;
        if(index==-1 || index>=data.length){
            return false;
        }
        else {
            int offset = value%64;
            long mask = 1L << offset;
            long res = data[index] & 1L <<offset;
            return res!=0;
        }
    }

    public void remove(int value) {
        int index = value/64;
        if(index==-1 || index>=data.length){
            return;
        }
        else {
            int offset = value%64;
            long mask = ~(1L << offset);
            data[index] &= mask;
        }

    }

    public IntSet union(IntSet other) {
        int thisLen = this.data.length;
        int otherLen = other.data.length;
        long[] temp;
        if(thisLen<=otherLen){
            temp = new long[otherLen];
            System.arraycopy(this.data,0,temp,0,thisLen);
            for (int i = 0; i <otherLen; i++){
                temp[i]|=other.data[i];
            }
        }
        else{
            temp = new long[thisLen];
            System.arraycopy(other.data,0,temp,0,otherLen);
            for (int i = 0; i <thisLen; i++){
                temp[i]|= this.data[i];
            }
        }
        return new IntSet(temp);
    }

    public IntSet intersection(IntSet other) {
        int thisLen = this.data.length;
        int otherLen = other.data.length;
        long[] temp;
        if(thisLen>=otherLen){
            temp = new long[otherLen];
            System.arraycopy(other.data,0,temp,0,otherLen);
            for (int i = 0; i <otherLen; i++){
                temp[i]&=this.data[i];
            }
        }
        else{
            temp = new long[thisLen];
            System.arraycopy(this.data,0,temp,0,thisLen);
            for (int i = 0; i <thisLen; i++){
                temp[i]&=this.data[i];
            }
        }
        return new IntSet(temp);
    }

    public IntSet difference(IntSet other) {
        int thisLen = this.data.length;
        int otherLen = other.data.length;
        long[] temp;
            temp = new long[thisLen];
            System.arraycopy(this.data,0,temp,0,thisLen);
            for (int i = 0; i <thisLen; i++){
                temp[i] &= ~other.data[i];
            }
        return new IntSet(temp);
    }

    public boolean isSubsetOf(IntSet other) {
        int thisLen = this.data.length;
        int otherLen = other.data.length;
        if (thisLen > otherLen){
            return false;
        }
        else{
            for (int i = 0; i <thisLen; i++){
                if((this.data[i]& ~other.data[i])!=0){
                    return false;
                }
            }
        }

        return true;
    }
}
