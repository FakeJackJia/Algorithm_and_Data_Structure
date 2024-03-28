public class LinkedList<E> {
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

    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead = new Node(null, null);
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

        Node pre = dummyHead;

        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        pre.next = new Node(e, pre.next);
        size++;
    }

    //O(1)
    public void addFirst(E e){
        add(0, e);
    }

    //O(n)
    public void addLast(E e){
        add(size, e);
    }

    //O(n)
    //practice use since it is not usual in reality
    public E get(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Invalid index");

        Node cur = dummyHead.next;

        for (int i = 0; i < index; i++)
            cur = cur.next;

        return cur.e;
    }

    //O(1)
    public E getFirst(){
        return get(0);
    }

    //O(n)
    public E getLast(){
        return get(size-1);
    }

    //O(n)
    public void set(int index, E e){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Invalid index");

        Node cur = dummyHead.next;

        for (int i = 0; i < index; i++)
            cur = cur.next;

        cur.e = e;
    }

    //O(n)
    public boolean contains(E e){
        Node cur = dummyHead.next;

        while (cur != null){
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }

        return false;
    }

    //O(n)
    public E remove(int index){
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Invalid index");

        Node pre = dummyHead;

        for(int i = 0; i < index; i++)
            pre = pre.next;

        Node retNode = pre.next;
        pre.next = retNode.next;
        retNode.next = null;
        size--;

        return retNode.e;
    }

    //O(1)
    public E removeFirst(){
        return remove(0);
    }

    //O(n)
    public E removeLast(){
        return remove(size-1);
    }

    //O(n)
    public void removeElement(E e){
        Node pre = dummyHead;

        while (pre.next != null){
            if(pre.next.e.equals(e))
                break;
            pre = pre.next;
        }

        if (pre.next != null){
            Node delNode = pre.next;
            pre.next = delNode.next;
            delNode.next = null;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while (cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }

        res.append("NULL");

        return res.toString();
    }
}