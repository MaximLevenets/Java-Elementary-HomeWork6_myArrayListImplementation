package myArrayListImplementation;

import java.util.*;

public class MyList <T> implements List <T> {

    private static int CAPACITY;
    private int LastIndexOfElement;
    Object[] arr;
    private int modCount = 0;
    private int size;

    public MyList() {
        CAPACITY = 10;
        LastIndexOfElement = -1;
        arr = new Object[CAPACITY];
    }
    public MyList(T[]array){
        CAPACITY = array.length + 10;
        LastIndexOfElement = -1;
        T[]copyArray = Arrays.copyOf(array, array.length);
        arr = new Object[CAPACITY];
        for (int i = 0; i < copyArray.length; i++) {
            if (copyArray[i] != null){
                arr[i] = copyArray[i];
                LastIndexOfElement++;
            }
        }
    }
    public void printAll() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            trimToSize();
        }
        System.out.println();
    }

    public void increaseLengthArray() {
        CAPACITY = (int) (CAPACITY * 1.5 + 1);
        Object[] arrTemp = new Object[CAPACITY];
        System.arraycopy(arr, 0, arrTemp, 0, arr.length);
        arr = arrTemp;
    }
    private void increaseLengthArray(int CAPACITY) {
        if(CAPACITY < this.CAPACITY) throw new IllegalArgumentException(
                "New capacity must be larger than current capacity.");
        this.CAPACITY = CAPACITY;
        Object[] arrayTemp = new Object[CAPACITY];
        System.arraycopy(this.arr,0, arrayTemp, 0, this.arr.length);
        this.arr = arrayTemp;
    }


    public void trimToSize() {
        modCount++;
        Object[] arrTrim = new Object[arr.length];
        if (size() < arr.length) {
            arr = (size() == 0) ? arrTrim : Arrays.copyOf(arr, size());
        }
    }
    private boolean isCorrectInputIndex(int index) {
        return (index <= LastIndexOfElement && index >= 0);
    }

    @Override
    public boolean add(T t) {
        if (LastIndexOfElement == CAPACITY - 1)
            increaseLengthArray();
        arr[++LastIndexOfElement] = t;
        return true;
    }

    @Override
    public int size() {
        return LastIndexOfElement + 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;

    }
    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        MyList myList = (MyList) o;
        if(LastIndexOfElement != myList.LastIndexOfElement) return false;
        if(CAPACITY != myList.CAPACITY) return false;
        if(!Arrays.equals(arr, myList.arr)) return false;
        return true;    }

    @Override
    public Iterator<T> iterator() {
        System.out.println("\"public Iterator iterator() {...} - IS NOT REALIZED!\"");
        return null;
    }

    @Override
    public Object[] toArray() {
        if (this.isEmpty())
            return new Object[0];
        else {
            Object[] copyArray = Arrays.copyOf(arr, size());
            return copyArray;
        }
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        System.out.println("\"public <T1> T1[] toArray(T1[] a) {...} - IS NOT REALIZED!\"");
        return null;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0)
            return false;
        else {
            Object[] arrayTemp = new Object[CAPACITY];
            if (index == 0) {
                System.arraycopy(arr, 1, arrayTemp, 0, LastIndexOfElement);
            } else {
                System.arraycopy(arr, 0, arrayTemp, 0, index);
                System.arraycopy(arr, index + 1, arrayTemp, index, LastIndexOfElement - index);
            }
            LastIndexOfElement--;
            arr = arrayTemp;
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        System.out.println("\"public boolean containsAll(Collection c) {...} - IS NOT REALIZED!\"");
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] array = c.toArray();
        if(array.length == 0) return false;
        if((this.size() + array.length) >= this.CAPACITY) {
            increaseLengthArray(arr.length + CAPACITY);
        }
        System.arraycopy(array, 0, arr, this.LastIndexOfElement + 1, array.length);
        this.LastIndexOfElement += array.length;
        return true;
    }


    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Object[] array = c.toArray();
        if (array.length == 0) return false;
        if ((this.size() + array.length) >= this.CAPACITY) {
            increaseLengthArray(array.length + CAPACITY);
        }
        System.arraycopy(array, 0, arr, this.LastIndexOfElement + 1, array.length);
        this.LastIndexOfElement += array.length;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        System.out.println("\"public boolean removeAll(Collection c) {...} - IS NOT REALIZED!\"");
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        System.out.println("\"public boolean retainAll(Collection c) {...} - IS NOT REALIZED!\"");
        return false;
    }

    @Override
    public void clear() {
        CAPACITY = 10;
        LastIndexOfElement = -1;
        arr = new Object[CAPACITY];
    }

    @Override
    public T get(int index) {
        if(isCorrectInputIndex(index)) return (T) arr[index];
        else throw new IllegalArgumentException("Wrong index value.");
    }

    @Override
    public T set(int index, T element) {
        if (isCorrectInputIndex(index)){
            arr[index] = element;
            return (T) this;
        }
        else return null;
    }

    @Override
    public void add(int index, T element) {
        if (LastIndexOfElement == CAPACITY - 1)
            increaseLengthArray();
        Object[]arrTemp = new Object[CAPACITY];
        System.arraycopy(arr, 0, arrTemp, 0, index);
        arrTemp[index] = element;
        System.arraycopy(arr, index, arrTemp, index + 1, size() - index);
        LastIndexOfElement++;
        arr = arrTemp;
    }

    @Override
    public T remove(int index) {
        if(isCorrectInputIndex(index)) {
            Object removedObject = arr[index];
            Object[] arrayTemp = new Object[CAPACITY];
            if (index == 0) {
                System.arraycopy(arr, 1, arrayTemp, 0, LastIndexOfElement);
            } else {
                System.arraycopy(arr, 0, arrayTemp, 0, index);
                System.arraycopy(arr, index + 1, arrayTemp, index, LastIndexOfElement - index);
            }
            LastIndexOfElement--;
            arr = arrayTemp;
            return (T) removedObject;
        } else return null;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++) {
            if (o.equals(arr[i])) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = 0; i < size(); i++){
            if (o.equals(arr[size() - 1 - i]))
                return size() - 1 - i;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        System.out.println("\"public ListIterator<T> listIterator() {...} - IS NOT REALIZED!\"");
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        System.out.println("\"public ListIterator<T> listIterator(int index) {...} - IS NOT REALIZED!\"");
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        System.out.println("\"public List subList(int fromIndex, int toIndex) {...} - IS NOT REALIZED!\"");
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + LastIndexOfElement;
        result = prime * result + CAPACITY;
        result = prime * result + Arrays.hashCode(arr);
        return result;
    }
}
