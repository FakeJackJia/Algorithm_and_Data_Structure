public class BTUnionFind implements UnionFind{
    private int[] parent;
    //Size is an optimization allowing the depth to stay about the same
    //letting smaller size tree to connect to larger size tree
    private int[] sz;

    public BTUnionFind(int size){
        parent = new int[size];
        sz = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    //O(h) depends on the depth
    private int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound");

        while (p != parent[p])
            p = parent[p];

        return p;
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

        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else{
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }
}