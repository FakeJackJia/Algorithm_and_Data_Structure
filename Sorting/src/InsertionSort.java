public class InsertionSort {
    private InsertionSort(){}

    public static <E extends Comparable<E>> void insertionSort(E[] arr){
        for(int i = 1; i < arr.length; i++){
            for(int j = i; j > 0; j--){
                if(arr[j-1].compareTo(arr[j]) > 0) {
                    swap(arr, j, j-1);
                }
            }

        }
    }

    public static <E extends Comparable<E>> void insertionSort3(E[] arr){
        for(int i = arr.length - 2; i >= 0; i--){
            for(int j = i; j < arr.length - 1; j++){
                if(arr[j+1].compareTo(arr[j]) < 0) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    public static <E extends Comparable<E>> void insertionSort2(E[] arr){
        for(int i = 1; i < arr.length; i++){
            E temp = arr[i];
            int j;
            for(j = i; j > 0; j--){
                if(arr[j-1].compareTo(temp) > 0) {
                    arr[j] = arr[j-1];
                }
                else break;
            }

            arr[j] = temp;
        }
    }

    public static <E extends Comparable<E>> void sort(E[] arr, int l, int r){
        for(int i = l; i <= r; i++){
            E temp = arr[i];
            int j;
            for(j = i; j - 1 >= l; j--){
                if(arr[j-1].compareTo(temp) > 0) {
                    arr[j] = arr[j-1];
                }
                else break;
            }

            arr[j] = temp;
        }
    }

    private static <E> void swap(E[] data, int i, int j){
        E temp = data[j];
        data[j] = data[i];
        data[i] = temp;
    }

    public static void main(String[] args){
        Integer[] data = {3, 5, 1, 7, 6, 4};
        InsertionSort.insertionSort3(data);

        for (int e : data) {
            System.out.print(e + " ");
        }

        System.out.println();

        Integer[] arr = ArrayGenerator.generateRandomArray(10000, 10000);
        SortingHelper.sortTest("InsertionSort", arr);


    }
}
