import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

//no repeating elements in this BST
public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left, right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(E e){
        root = add(root, e);

    }

    private Node add(Node node, E e){
        if (node == null){
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);
        else if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);

        return node;
    }

    public boolean contains(E e){
        return contains(root, e);
    }

    private boolean contains(Node node, E e){
        if (node == null)
            return false;

        if (node.e.compareTo(e) > 0)
            return contains(node.left, e);
        else if (node.e.compareTo(e) < 0)
            return contains(node.right, e);

        return true;
    }

    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //using stack in preOrderLoop
    public void preOrderLoop(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //using stack in inOrderLoop
    public void inOrderLoop(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node left = root.left;
        while(left != null){
            stack.push(left);
            left = left.left;
        }

        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
                cur = cur.right;

                while (cur.left != null){
                    cur = cur.left;
                    stack.push(cur);
                }
            }
        }
    }

    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //using stack in postOrderLoop
    public void postOrderLoop(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node left = root.left;
        while(left != null){
            stack.push(left);
            left = left.left;
        }

        while (!stack.isEmpty()){
            Node cur = stack.peek();

            if (cur.right != null) {
                stack.push(cur.right);
                cur = cur.right;

                while (cur.left != null){
                    cur = cur.left;
                    stack.push(cur);
                }
            } else{
                System.out.println(stack.pop().e);
            }
        }
    }

    //using queue in levelOrder
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e);

            if (cur.left  != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }

    }

    public E maximum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty.");

        return maximum(root).e;
    }

    public Node maximum(Node node){
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    public E minimum(){
        if (size == 0)
            throw new IllegalArgumentException("BST is empty.");

        return minimum(root).e;
    }

    public Node minimum(Node node){
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
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

    public E removeMax(){
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node){
        if (node.right == null){
            Node rightNode = node.left;
            node.left = null;
            size--;

            return rightNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e){
        root = remove(root, e);
    }

    private Node remove(Node node, E e){
        if (node == null)
            return null;

        if (node.e.compareTo(e) == 0){
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

        if (node.e.compareTo(e) > 0)
            node.left = remove(node.left, e);
        else if (node.e.compareTo(e) < 0)
            node.right = remove(node.right, e);

        return node;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res){
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");

        return res.toString();
    }


    public static void main(String[] arg){
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num: nums)
            bst.add(num);

        bst.preOrder();
        System.out.println();
        //System.out.println(bst);
        bst.inOrder();
        System.out.println();
        bst.postOrder();
        System.out.println();
        bst.preOrderLoop();
        System.out.println();
        bst.inOrderLoop();
        System.out.println();
        bst.postOrder();
        System.out.println();
        bst.levelOrder();
        System.out.println();

        System.out.println(bst.minimum());
        System.out.println(bst.maximum());
        System.out.println(bst.removeMax());
        System.out.println(bst.removeMin());
        System.out.println();

        bst.remove(6);
        bst.inOrder();
        System.out.println(bst.size());
    }
}