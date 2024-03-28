import java.util.Random;

//O(n)
public class KnuthShuffle {
    private int[] nums;
    private Random rnd;

    public KnuthShuffle(int[] nums){
        this.nums = nums.clone();
        rnd = new Random();
    }

    public int[] reset(){
        return nums.clone();
    }

    public int[] shuffle(){
        int[] data = nums.clone();
        for (int i = data.length - 1; i >= 0; i--){
            int j = rnd.nextInt(i + 1);
            swap(data, i, j);
        }

        return data;
    }

    private void swap(int[] arr, int i, int j){
        int t = arr[j];
        arr[j] = arr[i];
        arr[i] = t;
    }
}