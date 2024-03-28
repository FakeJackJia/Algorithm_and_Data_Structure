public class SelectionSortFromEnd {

    public static <E extends Comparable<E>> void sorting(E[] arr){
        int max;

        for(int i = arr.length - 1; i >= 0; i--){
            max = i;
            for(int j = i - 1; j >= 0; j--){
                if(arr[max].compareTo(arr[j]) < 0)
                    max = j;
            }

            swap(arr, max, i);
        }

    }

    private static <E> void swap(E[]arr, int i, int j){
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] arg){
        Integer[] data = {3, 5, 1, 7, 6, 4};
        SelectionSortFromEnd.sorting(data);

        for (int e : data) {
            System.out.print(e + " ");
        }
    }
}
