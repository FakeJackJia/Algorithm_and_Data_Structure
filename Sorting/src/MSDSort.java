public class MSDSort {
    private MSDSort(){}

    //O(W*n) for W to be the length of the longest string => O(n)
    //Much faster than LSD as MSD would usually end earlier in recursion
    public static void sort(String[] arr){
        String[] temp = new String[arr.length];
        sort(arr, 0, arr.length - 1 , 0, temp);
    }

    private static void sort(String[] arr, int l, int r, int pos, String[] temp){
        if (l >= r) return;

        int R = 257; //including the empty (put at the pos 0)
        int[] cnt = new int[R];
        int[] index = new int[R + 1];

        for (int i = l; i <= r; i++) {
            if (pos >= arr[i].length())
                cnt[0]++;
            else
                cnt[arr[i].charAt(pos) + 1]++;
        }

        for (int i = 0; i < R; i++)
            index[i + 1] = index[i] + cnt[i];

        for (int i = l; i <= r; i++) {
            if (pos >= arr[i].length())
                temp[l + index[0]++] = arr[i];
            else
                temp[l + index[arr[i].charAt(pos) + 1]++] = arr[i];
        }

        for (int i = l; i <= r; i++)
            arr[i] = temp[i];

        for (int i = 0; i < R - 1; i++)
            //no need to do recursion for the pos of the empty string
            //index array changed after assign value to arr
            sort(arr, l + index[i], l + index[i + 1] - 1, pos + 1, temp);
    }

    public static void main(String[] args){
        String[] arr = {"BCA", "CBAA", "AC", "BADFE", "ABC", "CBA"};
        MSDSort.sort(arr);
        for (String s: arr)
            System.out.println(s);
        System.out.println();
    }
}