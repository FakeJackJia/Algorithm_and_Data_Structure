public class ArrayStack<E> implements Stack<E> {
    Array<E> array;

    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayStack(){
        array = new Array<>();
    }

    //O(1)
    @Override
    public int getSize(){
        return array.getSize();
    }

    //O(1)
    @Override
    public boolean isEmpty(){
        return array.empty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    //O(1) amortized
    @Override
    public void push(E e){
        array.addLast(e);
    }

    //O(1) amortized
    @Override
    public E pop(){
        return array.removeLast();
    }

    //O(1)
    @Override
    public E peek(){
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++){
            res.append(array.get(i));
            if (i != array.getSize() - 1){
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }

}