import java.util.Random;

public class ArrayGenerator {
    private ArrayGenerator(){}

    public static Integer[] generateRandomArray(int n, int bound){
        Integer[] arr = new Integer[n];
        Random rand = new Random();
        for(int i = 0; i < n; i++){
            arr[i] = rand.nextInt(bound);
        }

        return arr;
    }

    public static Integer[] generateSpecialArray(int n){
        Integer[] arr = new Integer[n];

        generateSpecialArray(arr, 0, arr.length - 1, 0);
        return arr;
    }

    private  static void generateSpecialArray(Integer[] arr, int l, int r, int value){
        if (l > r)
            return;

        int mid = l + (r - l)/2;
        arr[mid] = value;

        swap(arr, mid, l);

        generateSpecialArray(arr, l + 1, r, value + 1);
        swap(arr, mid, l);
    }

    private static void swap(Integer[] arr, int i, int j){
        Integer temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
