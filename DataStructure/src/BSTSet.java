//ordered set
public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private BST<E> bst;
    public BSTSet(){
        bst = new BST<>();
    }

    @Override
    public int getSize(){
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    //O(h) - depends on the height of the tree
    //amortized time - O(logn) if it is a full tree
    //worst case - O(n)
    @Override
    public void add(E e){
        bst.add(e);
    }

    //O(h) - depends on the height of the tree
    //amortized time - O(logn) if it is a full tree
    //worst case - O(n)
    @Override
    public boolean contains(E e){
        return bst.contains(e);
    }

    //O(h) - depends on the height of the tree
    //amortized time - O(logn) if it is a full tree
    //worst case - O(n)
    @Override
    public void remove(E e){
        bst.remove(e);
    }
}