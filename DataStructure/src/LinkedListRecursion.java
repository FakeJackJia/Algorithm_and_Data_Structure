import java.util.concurrent.LinkedBlockingDeque;

public class LinkedListRecursion<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null, null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node head;
    private int size;

    public LinkedListRecursion(){
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //O(n)
//practice use since it is not usual in reality
    public void add(int index, E e){
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Invalid index");
        head = addRecursion(index, e, head);
        size++;
    }

    private Node addRecursion(int index, E e, Node head){
        if (index == 0){
            return new Node(e, head);
        }

        head.next = addRecursion(index - 1, e, head.next);
        return head;
    }

    //O(1)
    public void addFirst(E e){
        add(0, e);
    }

    //O(n)
    public void addLast(E e){
        add(size, e);
    }

//    //O(n)
////practice use since it is not usual in reality
    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Invalid index");

        return getRecursion(index, head);
    }

    private E getRecursion(int index, Node head){
        if (index == 0)
            return head.e;

        return getRecursion(index - 1, head.next);
    }

    //O(1)
    public E getFirst(){
        return get(0);
    }

    //O(n)
    public E getLast(){
        return get(size-1);
    }

//    //O(n)
    public void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Invalid index");

        setRecursion(index, e, head);
    }

    private void setRecursion(int index, E e, Node head){
        if (index == 0) {
            head.e = e;
            return;
        }

        setRecursion(index - 1, e, head.next);
    }

//    //O(n)
    public boolean contains(E e){
        return containsRecursion(head, e);
    }

    private boolean containsRecursion(Node head, E e){
        if (head == null)
            return false;
        if (head.e == e)
            return true;

        return containsRecursion(head.next, e);
    }

//    //O(n)
    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Invalid index");
        size--;

        return removeRecursion(index, head);
    }

    private E removeRecursion(int index, Node pre){
        if (index == 0){
            Node retNode = head;
            head = head.next;
            return retNode.e;
        }

        if (index == 1){
            Node retNode = pre.next;
            pre.next = retNode.next;
            retNode.next = null;

            return retNode.e;
        }

        return removeRecursion(index - 1, pre.next);
    }

    //O(1)
    public E removeFirst(){
        return remove(0);
    }

    //O(n)
    public E removeLast(){
        return remove(size-1);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = head;
        while (cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }

        res.append("NULL");

        return res.toString();
    }
}