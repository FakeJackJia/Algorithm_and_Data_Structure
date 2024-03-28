public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    public Array(){
        this(10);
    }

    public Array(E[] arr){
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];
        size = data.length;
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean empty(){
        return size == 0;
    }

    //O(1)
    public void addLast(E e){
        insert(size, e);
    }

    //O(n)
    public void addFirst(E e){
        insert(0, e);
    }

    //O(n)
    public void insert(int index, E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("Insert not allowed");
        }

        if (size == data.length){
            resize(2 * data.length);
        }

        for(int i = size - 1; i >= index; i--){
            data[i+1] = data[i];
        }

        data[index] = e;
        size++;
    }

    //O(1)
    E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Illegal attempt");
        return data[index];
    }

    public E getLast(){
        return get(size - 1);
    }

    public E getFirst(){
        return get(0);
    }

    //O(1)
    void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Illegal attempt");
        data[index] = e;
    }

    //O(n)
    public boolean contain(E e){
        for (int i = 0; i < size; i++){
            if (data[i].equals(e)){
                return true;
            }
        }

        return false;
    }

    //O(n)
    public int find(E e){
        for (int i = 0; i < size; i++){
            if(data[i].equals(e)){
                return i;
            }
        }

        return -1;
    }

    //O(n)
    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Illegal attempt");

        E ret = data[index];
        for (int i = index + 1; i < size; i++){
            data[i-1] = data[i];
        }

        size--;
        data[size] = null; //loitering objects != memory leak

        if (size == data.length/4 && data.length/2 != 0)
            resize(data.length/2);

        return ret;
    }

    //O(n)
    public E removeFirst(){
        return remove(0);
    }

    //O(1)
    public E removeLast(){
        return remove(size - 1);
    }

    //O(n)
    public boolean removeElement(E e){
        int index = find(e);

        if (index != -1){
            remove(index);
            return true;
        }

        return false;
    }

    public void swap(int i, int j){
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Illegal index");
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        res.append(String.format("Array size = %d, capacity = %d\n", size, data.length));
        res.append('[');

        for (int i = 0; i < size; i++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(',');
        }
        res.append(']');
        return res.toString();
    }

    //O(n)
    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0; i < size; i ++){
            newData[i] = data[i];
        }

        data = newData;
    }
}