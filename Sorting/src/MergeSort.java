import java.util.Arrays;

public class MergeSort {
    private MergeSort(){}

    //top-down
    public static <E extends Comparable<E>> void mergeSort(E[] arr){
        sort(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r){
        if (l >= r)
            return;

        int mid = l + (r - l)/2;
        sort(arr, l, mid);
        sort(arr, mid + 1, r);

        merge(arr, l, mid, r);
    }

    private static <E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r){
        E[] tempArr = Arrays.copyOfRange(arr, l, r + 1);
        int i = l;
        int j = mid + 1;

        for (int k = l; k <= r; k++){
            if (i > mid){
                arr[k] = tempArr[j - l];
                j++;
            }
            else if (j > r){
                arr[k] = tempArr[i - l];
                i++;
            }
            else if (tempArr[i - l].compareTo(tempArr[j - l]) <= 0){
                arr[k] = tempArr[i - l];
                i++;
            } else {
                arr[k] = tempArr[j - l];
                j++;
            }
        }
    }

    //bottom-up
    public static <E extends Comparable<E>> void mergeSortBU(E[] arr){
        sortBU(arr);
    }

    public static <E extends Comparable<E>> void sortBU(E[] arr){
        for (int i = 1; i < arr.length; i += i){
            for (int j = 0; j + i < arr.length; j = 2 * i + j){
                if (arr[j+i-1].compareTo(arr[j+i]) > 0)
                    merge(arr, j,j + i - 1, Math.min(j + 2 * i - 1, arr.length - 1));
            }
        }
    }

    //
    public static <E extends Comparable<E>> void mergeSort2(E[] arr){
        sort2(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort2(E[] arr, int l, int r){
        if (l >= r)
            return;

        int mid = l + (r - l)/2;
        sort2(arr, l, mid);
        sort2(arr, mid + 1, r);

        //don't call merge if it is already sorted
        //in this case, for sorted array, time complexity is O(n)
        if(arr[mid].compareTo(arr[mid+1]) > 0)
            merge(arr, l, mid, r);
    }

    //
    public static <E extends Comparable<E>> void mergeSort3(E[] arr){
        sort3(arr, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort3(E[] arr, int l, int r){
        //insertion sort can be faster than merge sort if the size is small
        if (r - l <= 15){
            InsertionSort.sort(arr, l, r );
            return;
        }

        int mid = l + (r - l)/2;
        sort3(arr, l, mid);
        sort3(arr, mid + 1, r);

        if(arr[mid].compareTo(arr[mid+1]) > 0)
            merge(arr, l, mid, r);
    }

    //
    public static <E extends Comparable<E>> void mergeSort4(E[] arr){
        //avoid keeping initiate a new array in merge
        E[] tempArr = Arrays.copyOf(arr, arr.length);
        sort4(arr, 0, arr.length - 1, tempArr);
    }

    private static <E extends Comparable<E>> void sort4(E[] arr, int l, int r, E[] tempArr){
        if (l >= r)
            return;

        int mid = l + (r - l)/2;
        sort4(arr, l, mid, tempArr);
        sort4(arr, mid + 1, r, tempArr);

        if(arr[mid].compareTo(arr[mid+1]) > 0)
            merge4(arr, l, mid, r, tempArr);
    }

    private static <E extends Comparable<E>> void merge4(E[] arr, int l, int mid, int r, E[] tempArr){
        //to update, just copy the value in arr to tempArr
        System.arraycopy(arr, l, tempArr, l, r - l + 1);

        int i = l;
        int j = mid + 1;

        for (int k = l; k <= r; k++){
            if (i > mid){
                arr[k] = tempArr[j];
                j++;
            }
            else if (j > r){
                arr[k] = tempArr[i];
                i++;
            }
            else if (tempArr[i - l].compareTo(tempArr[j - l]) <= 0){
                arr[k] = tempArr[i];
                i++;
            } else {
                arr[k] = tempArr[j];
                j++;
            }
        }
    }

    public static void main(String[] args){
        Integer[] arr = {9, 7, 5, 4, 6, 12, 1};

        MergeSort.mergeSortBU(arr);

        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}