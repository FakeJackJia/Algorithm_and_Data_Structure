public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity){
        data = (E[])(new Object[capacity+1]);
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1;
    }

    //O(1)
    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    //O(1)
    @Override
    public int getSize(){
        return size;
    }

    //O(1) amortized
    @Override
    public void enqueue(E e){
        if ((tail + 1) % data.length == front)
            resize(getCapacity() * 2);

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    //O(1) amortized
    @Override
    public E dequeue(){
        if (isEmpty())
            throw new IllegalArgumentException("Empty queue");

        E res = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if (size == getCapacity()/4 && getCapacity()/2 != 0)
            resize(getCapacity()/2);

        return res;
    }

    //O(1)
    @Override
    public E getFront(){
        if (isEmpty())
            throw new IllegalArgumentException("Empty queue");

        return data[front];
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity+1];
        for (int i = 0; i < size; i++){
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("LoopQueue: ");
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length){
            res.append(data[i]);
            if ((i + 1)%data.length != tail)
                res.append(", ");
        }

        res.append("] tail");
        return res.toString();
    }
}