import java.util.Random;

public class QuickSort {
    private QuickSort(){}

    //single pivot quicksort
    public static <E extends Comparable<E>> void quickSort(E[] arr){
        Random random = new Random();
        sort(arr, 0, arr.length - 1, random);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int l, int r, Random random){
        if (l >= r)
            return;

        int p = partition(arr, l, r, random);
        sort(arr, l, p - 1, random);
        sort(arr, p + 1, r, random);
    }

    //if pivot is the first element in the range, time complexity would be O(n^2) for sorted array
    //to solve above issue, simply randomize the pivot
    private static <E extends Comparable<E>> int partition(E[] arr, int l, int r, Random random){
        int p = l + random.nextInt(r - l + 1);
        swap(arr, l, p);

        int j = l;

        for (int i = l + 1; i <= r; i++){
            if (arr[i].compareTo(arr[l]) < 0){
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, l, j);

        return j;
    }

    //Dual Pivot Quicksort
    public static <E extends Comparable<E>> void quickSortDual(E[] arr){
        Random random = new Random();
        sort(arr, 0, arr.length - 1, random);
    }

    private static <E extends Comparable<E>> void sortDual(E[] arr, int l, int r, Random random){
        if (l >= r)
            return;

        int p = partition(arr, l, r, random);
        sort(arr, l, p - 1, random);
        sort(arr, p + 1, r, random);
    }

    private static <E extends Comparable<E>> int partitionDual(E[] arr, int l, int r, Random random){
        int p = l + random.nextInt(r - l + 1);
        swap(arr, l, p);

        int j = r;
        int i = l + 1;

        while (j >= i){
            if (arr[i].compareTo(arr[l]) > 0 && arr[j].compareTo(arr[l]) < 0){
                swap(arr, i, j);
                i++;
                j--;
            } else {
                if (arr[i].compareTo(arr[l]) <= 0)
                    i++;
                if (arr[j].compareTo(arr[l]) >= 0)
                    j--;
            }
        }
        swap(arr, l, j);

        return j;
    }

    //3-way Pivot Quicksort
    public static <E extends Comparable<E>> void quickSortT(E[] arr){
        Random random = new Random();
        sortT(arr, 0, arr.length - 1, random);
    }

    private static <E extends Comparable<E>> void sortT(E[] arr, int l, int r, Random random){
        if (l >= r)
            return;

        int p = l + random.nextInt(r - l + 1);
        swap(arr, l, p);

        int lt = l;
        int gt = r;
        int eq = l + 1;

        while (eq <= gt){
            if (arr[eq].compareTo(arr[l]) < 0){
                lt++;
                swap(arr, lt, eq);
                eq++;
            }
            else if (arr[eq].compareTo(arr[l]) > 0) {
                swap(arr, gt, eq);
                gt--;
            }
            else{
                eq++;
            }
        }

        swap(arr, lt, l);


        sort(arr, l, lt - 1, random);
        sort(arr, gt + 1, r, random);
    }

    private static <E> void swap(E[] arr, int i, int j){
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args){
        Integer[] arr = {9, 7, 5, 4, 6, 6, 6, 12, 1, 2, 3};

        QuickSort.quickSortT(arr);

        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();

//        Integer[] arr1 = ArrayGenerator.generateSpecialArray(10);

//        for (int i = 0; i < arr1.length; i++){
//            System.out.print(arr1[i] + " ");
//        }
//        System.out.println();

    }
}
