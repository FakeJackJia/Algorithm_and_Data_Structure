public class Deque<E>{
    private E[] data;
    private int front, tail;

    public Deque(int capacity){
        data = (E[])(new Object[capacity+1]);
        front = 0;
        tail = 0;
    }

    public Deque(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1;
    }

    //O(1)
    public boolean isEmpty(){
        return front == tail;
    }

    //O(1)
    public int getSize(){
        if (tail >= front)
            return tail - front;
        return data.length - front + tail;
    }

    //O(1) amortized
    public void addLast(E e){
        if (getSize() == getCapacity())
            resize(getCapacity() * 2);

        data[tail] = e;
        tail = (tail + 1) % data.length;
    }

    public void addFirst(E e){
        if (getSize() == getCapacity())
            resize(getCapacity() * 2);
        if (front - 1 < 0)
            front = data.length - 1;
        else front--;

        data[front] = e;
    }

    public E removeLast(){
        if (isEmpty())
            throw new IllegalArgumentException("Empty queue");
        if (tail - 1 < 0)
            tail = data.length - 1;
        else tail--;
        E res = data[tail];
        data[tail] = null;

        if (getSize() == getCapacity()/4 && getCapacity()/2 != 0)
            resize(getCapacity()/2);

        return res;
    }

    //O(1) amortized
    public E removeFirst(){
        if (isEmpty())
            throw new IllegalArgumentException("Empty queue");

        E res = data[front];
        data[front] = null;
        front = (front + 1) % data.length;

        if (getSize() == getCapacity()/4 && getCapacity()/2 != 0)
            resize(getCapacity()/2);

        return res;
    }

    //O(1)
    public E getFront(){
        if (isEmpty())
            throw new IllegalArgumentException("Empty queue");

        return data[front];
    }

    public E getLast(){
        if (isEmpty())
            throw new IllegalArgumentException("Emptry queue");
        int index = tail;
        if (index - 1 < 0)
            index = data.length - 1;
        else index--;

        return data[index];
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity+1];
        for (int i = 0; i < getSize(); i++){
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        tail = getSize();
        front = 0;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("LoopQueue: ");
        res.append("front [");
        for (int i = 0; i < getSize(); i++){
            res.append(data[(i + front) % data.length]);
            if (i != getSize() - 1)
                res.append(", ");
        }

        res.append("] tail");
        return res.toString();
    }
}