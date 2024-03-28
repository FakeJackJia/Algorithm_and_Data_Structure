//O(sqrt(n))
public class SQRT<E> {

    private E[] data, blocks;
    private int N; //number of all elements
    private int B; //number of elements in each block
    private int Bn;//number of blocks
    private Merger<E> merger;

    public SQRT(E[] arr, Merger<E> merger){
        this.merger = merger;

        N = arr.length;
        if (N == 0)
            return;

        B = (int)Math.sqrt(N);
        Bn = N / B + (N % B > 0 ? 1 : 0);

        data = (E[])new Object[N];

        for (int i = 0; i < N; i++)
            data[i] = arr[i];

        blocks = (E[])new Object[Bn];
        for (int i = 0; i < N; i++)
            if (i % B == 0)
                blocks[i/B] = data[i];
            else
                blocks[i/B] = merger.merge(blocks[i/B], data[i]);
    }

    public void update(int index, E val){
        if (index < 0 || index >= N)
            return;

        int b = index / B;

        data[index] = val;
        blocks[b] = data[b * B];
        for (int i = b * B; i < Math.min((b + 1) * B, N); i++)
            blocks[b] = merger.merge(blocks[b], data[i]);
    }

    public E queryRange(int x, int y){
        if (x < 0 || x >= N || y < 0 || y >= N || x > y)
            return null;

        int bStart = x / B, bEnd = y / B;
        E res = data[x];

        if (bStart == bEnd){
            //O(sqrt(n)) as within one block
            for (int i = x + 1; i <= y; i++)
                res = merger.merge(res, data[i]);

            return res;
        }

        //O(sqrt(n))
        for (int i = x + 1; i < (bStart + 1) * B; i++)
            res = merger.merge(res, data[i]);

        //O(sqrt(n))
        for (int b = bStart + 1; b < bEnd; b++)
            res = merger.merge(res, blocks[b]);

        //O(sqrt(n))
        for (int i = bEnd * B; i <= y; i++)
            res = merger.merge(res, data[i]);

        return res;
    }

    public static void main(String[] args){
        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        SQRT<Integer> sum = new SQRT<>(arr, (a, b) -> Math.max(a, b));
        System.out.println(sum.queryRange(0, 3));
    }
}