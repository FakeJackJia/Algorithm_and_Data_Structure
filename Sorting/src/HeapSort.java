public class HeapSort {
    private HeapSort() {}

    //O(nlogn)
    public static <E extends Comparable<E>> void sort2(E[] data) {
        if (data.length <= 1)
            return;

        for (int i = (data.length - 2) / 2; i >= 0; i--)
            siftDown(i, data, data.length);

        for (int i = data.length - 1; i >= 0; i--) {
            swap(data, 0, i);
            siftDown(0, data, i);
        }
    }

    //[0,n) max heap
    private static <E extends Comparable<E>> void siftDown(int k, E[] data, int n) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && data[j + 1].compareTo(data[j]) > 0)
                j++;
            //data[j] is the largest among left and right child
            if (data[k].compareTo(data[j]) >= 0)
                break;

            swap(data, k, j);
            k = j;
        }
    }

    private static <E> void swap(E[] data, int i, int j) {
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }
}