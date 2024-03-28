public class ArrayUnionFind implements UnionFind{
    private int[] id;

    public ArrayUnionFind(int size){
        id = new int[size];

        for (int i = 0; i < id.length; i++)
            id[i] = i;
    }

    @Override
    public int getSize(){
        return id.length;
    }

    private int find(int p){
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("P is out of bound");
        return id[p];
    }

    //O(1)
    @Override
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    //O(n)
    @Override
    public void unionElements(int p, int q){
        int pID = find(p);
        int qID = find(q);

        if (pID == qID)
            return;

        for (int i = 0; i < id.length; i++){
            if (id[i] == pID)
                id[i] = qID;
        }
    }

}