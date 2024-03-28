import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {
    private BucketSort(){}

    public static void sort(Integer[] arr, int B){
        if (B <= 1)
            throw new IllegalArgumentException("B must be > 1");

        Integer[] temp = new Integer[arr.length];
        sort(arr, 0, arr.length - 1, B, temp);
    }

    private static void sort(Integer[] arr, int l, int r, int B, Integer[] temp){
        if (l >= r) return;

        int maxV = Integer.MIN_VALUE, minV = Integer.MAX_VALUE;
        for (int i = l; i <= r; i++){
            maxV = Math.max(maxV, arr[i]);
            minV = Math.min(minV, arr[i]);
        }

        if (maxV == minV) return;

        int d = (maxV - minV + 1) / B + ((maxV - minV + 1) % B > 0 ? 1 : 0);//number of elements in each bucket

        int[] cnt = new int[B];
        int[] index = new int[B + 1];

        for (int i = l; i <= r; i++)
            cnt[(arr[i] - minV) / d]++;

        for (int i = 0; i < B; i++)
            index[i + 1] = index[i] + cnt[i];

        for (int i = l; i <= r; i++)
            temp[l + index[(arr[i] - minV) / d]++] = arr[i];

        for (int i = l; i <= r; i++)
            arr[i] = temp[i];

        sort(arr, l, l + index[0] - 1, B, temp);
        for (int i = 0; i < B - 1; i++)
            sort(arr, l + index[i], l + index[i + 1] - 1, B, temp);
    }

    //O(n)
    //suitable for evenly distributed data
    //if not evenly distributed, may degenerate
    public static void sort2(Integer[] arr, int c){
        if (c <= 0)
            throw new IllegalArgumentException("c must be > 0");

        int B = arr.length / c + (arr.length % c > 1 ? 1 : 0);
        LinkedList<Integer>[] buckets = new LinkedList[B];

        for (int i = 0; i < B; i++)
            buckets[i] = new LinkedList<>();

        int maxV = Integer.MIN_VALUE, minV = Integer.MAX_VALUE;
        for (int e: arr){
            maxV = Math.max(maxV, e);
            minV = Math.min(minV, e);
        }

        for (int e: arr)
            buckets[(e - minV) / c].add(e);

        for (int i = 0; i < B; i++)
            Collections.sort(buckets[i]);

        int index = 0;
        for (int i = 0; i < B; i++)
            for (int e: buckets[i])
                arr[index++] = e;
    }

    public static void main(String[] args){
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("BucketSort", arr);
    }
}