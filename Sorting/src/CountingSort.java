public class CountingSort {
    //O(n + R) if range small, then O(n)
    public static void sort(int[] arr, int num){
        //sort [0, R) depending on the value of R
        int R = num;

        int[] temp = new int[arr.length];
        int[] cnt = new int[R];
        //O(n)
        for (int e: arr)
            cnt[e]++;

        int[] index = new int[R + 1];

        //O(R)
        for (int i = 0; i < R; i++)
            index[i + 1] = index[i] + cnt[i];

        //O(n)
        for (int i = 0; i < arr.length; i++)
            //allow the stability
            temp[index[arr[i]]++] = arr[i];

        //O(n)
        for (int i = 0; i < temp.length; i++)
            arr[i] = temp[i];
    }

    public static void main(String[] args){
        int[] arr = {0, 1, 2, 0, 1, 1, 2, 2, 1, 1, 0, 0};

        CountingSort.sort(arr, 3);

        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}