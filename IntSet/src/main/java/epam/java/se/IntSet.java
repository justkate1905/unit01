package epam.java.se;

/** Класс для хранения битовых полей
 *
 * */
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

    /** Добавление элемента в множество
     *
     * @param value Значение добавляемого элемента
     */
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

    /** Проверка множества на содержание элемента
     *
     * @param value значение элемента
     * @return результат проверки
     */
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

    /** Удаление элемента из множества
     *
     * @param value значение элемента
     */
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

    /** Операция объединения множеств
     *
     * @param other второе множество
     * @return результат операции объединения в виде множества
     */
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

    /** Операция пересечения множества
     *
     * @param other второе множество
     * @return результат операции пересечения в виде множества
     */
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

    /** Операция разности множеств
     *
     * @param other второе множество
     * @return результат операции разности в виде множества
     */
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

    /** Проверка, является ли текущее множество подмножеством
     *
     * @param other множество для проверки на содержание текущего подмножества
     * @return результат проверки
     */
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
