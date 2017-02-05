package epam.java.se;

import java.util.Arrays;
import java.util.NoSuchElementException;

    @SuppressWarnings("Since15")
    public class IntArrayList {
        private int[] data;
        private int size;

        public IntArrayList(int[] data) {
            this.data = Arrays.copyOf(data, data.length);
            size = data.length;
        }

        public IntArrayList() {
            data = new int[10];
            size = 0;
        }

        public void add(int value) {
            ensureCapasity(size + 1);
            data[size] = value;
            size += 1;
        }

        public int get(int i) {
            if (i < 0 || i >= size) {
                throw new IndexOutOfBoundsException();
            }
            return data[i];
        }

        public int getSize() {
            return size;
        }

        public int maxValueInefficient() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
            return maxValueRec(data, 0, size);
        }

        private int maxValueRec(int[] data, int startInclusive, int endExlusive) {
            final int length = endExlusive - startInclusive;

            if (length == 1) {
                return data[startInclusive];
            } else if (length == 0) {
                return Integer.MIN_VALUE;
            }

            final int mid = startInclusive + length/2;
            return Math.max(
                    maxValueRec(data, startInclusive, mid),
                    maxValueRec(data, mid, endExlusive)
            );
        }

        public void sort(){
            mergeSort(data, 0, getSize(), new int[getSize()]);
        }
        public void sort2(){
            mergeSort2(data,0,getSize(),new int[getSize()]);
        }

        /**
         * Expects collection to be sorted.
         *
         * @param value value to find in collection
         * @return index of the value or -indexToInsert - 1
         */
        public int binarySearch(int value) {
            return binarySearch(value, this.data, 0, this.data.length);
        }

        /**
         * Бинарный поиск с использованием циклов
         *
         * @param value значение для поиска
         * @return индекс элемента или  -indexToInsert-1
         */
        public int cycleBinarySearch(int value){
            return cycleBinarySearch(value, this.data,0, this.data.length);
        }
        public static int binarySearch(int key, int[] data, int startInclusive,int endExclusive){
            int mid = (endExclusive-startInclusive)/2+startInclusive;
            if(startInclusive==endExclusive){
                return -startInclusive-1;
            }
            if(data[mid]==key){
                return mid;
            }
            else if(key>data[mid]){
                return binarySearch(key,data,mid+1,endExclusive);
            }
            else{
                return binarySearch(key, data, startInclusive, mid);
            }
        }
        public static int cycleBinarySearch(int value, int[] data, int startInclusive, int endExclusive){
            int index=0;
            int mid;
            for(int i=0; i<endExclusive;i++) {
                mid =startInclusive + (endExclusive - startInclusive) / 2;
                if (data[mid] == value) {
                    index = mid;
                    break;
                } else if (value > data[mid]) {
                    startInclusive = mid + 1;
                    i = mid;
                    continue;
                } else {
                    i = startInclusive-1;
                    endExclusive = mid;
                    continue;
                }
            }
            if (startInclusive == endExclusive) {
                index = -startInclusive - 1;
            }
            return index;
        }


        private static void mergeSort(int[] data, int startInclusive, int endExclusive, int[] free) {
            final int length = endExclusive - startInclusive;
            if (length <= 1) {
                return;
            }

            final int mid = startInclusive + length/2;

            mergeSort(data, startInclusive, mid, free);
            mergeSort(data, mid, endExclusive, free);

            merger(data, startInclusive, mid, endExclusive, free);
        }

        /**
         * Восходящая сортировка слиянием
         *
         * @param data исходный массивы
         * @param startInclusive начало подмассива
         * @param endExclusive конец подмассива
         * @param free выделенный свободный массив
         */
        private static void mergeSort2(int[] data, int startInclusive, int endExclusive, int[] free){
            int right=0;
            for(int size = 1; size <endExclusive;size*=2){
                for (int left = 0; left<endExclusive-size;left+=size*2) {
                    right=left+size;
                        merger(data,left,right,Math.min(right+size,endExclusive),free);
                    }
            }
        }
        private static void merger(int[] data, int startInclusive, int mid, int endExclusive, int[] free) {
            int length = endExclusive-startInclusive;
            System.arraycopy(data, startInclusive, free, startInclusive, endExclusive - startInclusive);
            int i = startInclusive;
            int j = mid;
            for (int k = startInclusive; k < endExclusive; k++) {
                if (i >= mid) data[k] = free[j++];
                else if (j >= endExclusive) data[k] = free[i++];
                else if (free[i] < free[j]) data[k] = free[i++];
                else data[k] = free[j++];
            }

        }

        private void ensureCapasity(int requiredCapacity) {
            if (requiredCapacity <= getCapacity()) {
                return;
            }
            final int newCapasity = Math.max(requiredCapacity, (getCapacity() * 3) / 2 + 1);
            data = Arrays.copyOf(data, newCapasity);
        }

        private int getCapacity() {
            return data.length;
        }


    }


