public class BTUnionFind3 implements UnionFind{
    private int[] parent;
    //rank is another way of optimization allowing the depth to stay about the same
    //letting the smaller depth tree to connect to the larger depth tree
    private int[] rank;

    public BTUnionFind3(int size){
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    //O(h) depends on the depth (to be more accurate, it is O(log^*n)
    private int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound");

        //path compression
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }

        return parent[p];
    }

    //O(h)
    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    //O(h)
    @Override
    public void unionElements(int p, int q){
        int qRoot = find(q);
        int pRoot = find(p);

        if (pRoot == qRoot)
            return;

        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        }
        else if (rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        }
        else{
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }
}