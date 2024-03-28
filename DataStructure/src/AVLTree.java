import java.util.ArrayList;

public class AVLTree <K extends Comparable<K>, V> implements Map<K, V>{
    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value){
            this.key  = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }
    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);

        for (int i = 1; i < keys.size(); i++){
            if (keys.get(i).compareTo(keys.get(i - 1)) < 0)
                return false;
        }

        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys){
        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if (node == null)
            return true;

        int balancedFactor = getBalancedFactor(node);
        if (Math.abs(balancedFactor) > 1)
            return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }

    private int getHeight(Node node){
        if (node == null)
            return 0;
        return node.height;
    }

    private int getBalancedFactor(Node node){
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;
        x.right = y;
        y.left = T3;

        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;
        x.left = y;
        y.right = T2;

        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    //O(logn)
    @Override
    public void add(K key, V value){
        root = add(root, key, value);

    }

    private Node add(Node node, K key, V value){
        if (node == null){
            size++;
            return new Node(key ,value);
        }

        if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else
            node.value = value;

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balanceFactor = getBalancedFactor(node);

        //LL
        if (balanceFactor > 1 && getBalancedFactor(node.left) >= 0)
            return rightRotate(node);

        //RR
        if (balanceFactor < -1 && getBalancedFactor(node.right) <= 0)
            return leftRotate(node);

        //LR
        if (balanceFactor > 1 && getBalancedFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //RL
        if (balanceFactor < -1 && getBalancedFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node getNode(Node node, K key){
        if (node == null)
            return null;

        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else
            return getNode(node.right, key);
    }

    //O(logn)
    @Override
    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    //O(logn)
    @Override
    public V get(K key){
        Node node = getNode(root, key);
        return node == null? null : node.value;
    }

    //O(logn)
    @Override
    public void set(K key, V newValue){
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + "doesn't exist");

        node.value = newValue;
    }

    public Node minimum(Node node){
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    //O(logn)
    @Override
    public V remove(K key){
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }

        return null;
    }

    private Node remove(Node node, K key){
        if (node == null)
            return null;

        Node retNode;

        if (node.key.compareTo(key) == 0){
            Node s;
            if (node.right != null && node.left != null){
                s = minimum(node.right);
                s.right = remove(node.right, s.key);
                s.left = node.left;
            }
            else if (node.right == null){
                s = node.left;
                size--;
            }
            else {
                s = node.right;
                size--;
            }
            node.left = null;
            node.right = null;

            retNode = s;
        }
        else if (node.key.compareTo(key) > 0) {
            node.left = remove(node.left, key);
            retNode = node;
        }
        else {
            node.right = remove(node.right, key);
            retNode = node;
        }

        if (retNode == null)
            return null;

        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        int balanceFactor = getBalancedFactor(node);

        //LL
        if (balanceFactor > 1 && getBalancedFactor(retNode.left) >= 0)
            return rightRotate(retNode);

        //RR
        if (balanceFactor < -1 && getBalancedFactor(retNode.right) <= 0)
            return leftRotate(retNode);

        //LR
        if (balanceFactor > 1 && getBalancedFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        //RL
        if (balanceFactor < -1 && getBalancedFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    public static void main(String[] args){
        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        avlTree.add(5, 0);
        avlTree.add(4, 0);
        avlTree.add(3, 0);
        avlTree.add(2, 0);
        avlTree.add(1, 0);
        avlTree.add(0, 0);

        System.out.println(avlTree.isBalanced());
        System.out.println(avlTree.isBST());

        avlTree.remove(0);
        avlTree.remove(1);

        System.out.println(avlTree.isBalanced());
        System.out.println(avlTree.isBST());
    }
}