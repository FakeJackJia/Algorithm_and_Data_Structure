import java.util.Random;

public class RandomPickWithWeight {

        private int[] preSum;
        private int sum;
        private Random rnd;

    public RandomPickWithWeight(int[] w) {
        preSum = new int[w.length];
        for (int i = 1; i < w.length; i++)
            preSum[i] = preSum[i - 1] + w[i - 1];

        sum = w[w.length - 1] + preSum[w.length - 1];
        rnd = new Random();
    }

    public int pickIndex() {
        int x = rnd.nextInt(sum);

        //max(preSum[res] <= x)
        int l = 0, r = preSum.length - 1;

        while (l < r){
            int mid = l + (r - l + 1) / 2;

            if (preSum[mid] <= x)
                l = mid;
            else
                r = mid - 1;
        }

        return l;
    }
}