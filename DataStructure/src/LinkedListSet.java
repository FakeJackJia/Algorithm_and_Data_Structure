//unordered set
public class LinkedListSet<E> implements Set<E>{
    private LinkedList<E> list;

    public LinkedListSet(){
        list = new LinkedList<>();
    }

    @Override
    public int getSize(){
        return list.getSize();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    //O(n)
    @Override
    public boolean contains(E e){
        return list.contains(e);
    }

    //O(n)
    @Override
    public void add(E e){
        if (!list.contains(e))
            list.addFirst(e);
    }

    //O(n)
    @Override
    public void remove(E e){
        list.removeElement(e);
    }
}