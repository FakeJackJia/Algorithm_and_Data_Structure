public class BubbleSort {
    private BubbleSort(){}

    //O(n^2)
    public static<E extends  Comparable<E>> void sort(E[] arr){
        for (int i = arr.length - 1; i > 0; i--){
            for (int j = 0; j < i; j++){
                if (arr[j].compareTo(arr[j+1]) > 0)
                    swap(arr, j, j + 1);
            }
        }
    }

    //O(n) for ordered array
    public static<E extends  Comparable<E>> void sort2(E[] arr){
        for (int i = arr.length - 1; i > 0; i--){
            boolean isSwapped = false;

            for (int j = 0; j < i; j++){
                if (arr[j].compareTo(arr[j+1]) > 0) {
                    swap(arr, j, j + 1);
                    isSwapped = true;
                }
            }

            if (!isSwapped)
                break;
        }
    }

    //Optimization by noting the position of swapping
    public static<E extends  Comparable<E>> void sort3(E[] arr){
        for (int i = arr.length - 1; i > 0; ){
            int lastSwappedIndex = 0;

            for (int j = 0; j < i; j++){
                if (arr[j].compareTo(arr[j+1]) > 0) {
                    swap(arr, j, j + 1);
                    lastSwappedIndex = j;
                }
            }

            i = lastSwappedIndex;
        }
    }

    private static<E> void swap(E[] arr, int i, int j){
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args){
        Integer[] arr = {9, 7, 5, 4, 6, 6, 6, 12, 1, 2};

        BubbleSort.sort3(arr);

        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}