import java.util.Arrays;

public class LSDSort {
    private LSDSort(){}

    //O(W *(n + R)) => O(n) as W and R are constant
    public static void sort(String[] arr, int W){
        for (String s: arr)
            if (s.length() != W)
                throw new IllegalArgumentException("All string length must be the same");
        int R = 256;
        int[] cnt = new int[R];
        int[] index = new int[R + 1];
        String[] temp = new String[arr.length];

        //couting sort on rth postion of the string
        for (int r = W - 1; r >= 0; r--){
            Arrays.fill(cnt, 0);

            for(String s: arr)
                cnt[s.charAt(r)]++;

            for (int i = 0; i < R; i++)
                index[i + 1] = index[i] + cnt[i];

            for (String s: arr)
                temp[index[s.charAt(r)]++] = s;

            for (int i = 0; i < arr.length; i++)
                arr[i] = temp[i];
         }
    }

    public static void main(String[] args){
        String[] arr = {"BCA", "CAB", "ACB", "BAC", "ABC", "CBA"};
        LSDSort.sort(arr, 3);
        for (String s: arr)
            System.out.println(s);
        System.out.println();
    }
}