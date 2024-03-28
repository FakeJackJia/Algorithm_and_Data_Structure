//ordered map
public class BSTMap <K extends Comparable<K>, V> implements Map<K, V>{
    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value){
            this.key  = key;
            this.value = value;
            left = null;
            right = null;
        }
    }
    private Node root;
    private int size;

    public BSTMap(){
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

    //O(h)
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

    //O(h)
    @Override
    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    //O(h)
    @Override
    public V get(K key){
        Node node = getNode(root, key);
        return node == null? null : node.value;
    }

    //O(h)
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

    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;

            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    //O(h)
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

        if (node.key.compareTo(key) == 0){
            Node s;
            if (node.right != null && node.left != null){
                s = minimum(node.right);
                s.right = removeMin(node.right);
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

            return s;
        }

        if (node.key.compareTo(key) > 0)
            node.left = remove(node.left, key);
        else if (node.key.compareTo(key) < 0)
            node.right = remove(node.right, key);

        return node;
    }
}