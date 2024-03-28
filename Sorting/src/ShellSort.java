public class ShellSort {
    private ShellSort(){}

    //O(nlogn) ~ O(n^2)
    public static <E extends Comparable<E>> void sort(E[] arr){
        for (int i = arr.length/2; i >= 1; i = i/2){
            //O(n^2/i)
            for (int start = 0; start < i; start++){
                for (int k = start + i; k < arr.length; k = k + i){
                    E t = arr[k];
                    int h;

                    for (h = k; h > start; h = h - i) {
                        if (arr[h - i].compareTo(t) > 0)
                            arr[h] = arr[h - i];
                        else break;
                    }

                    arr[h] = t;
                }
            }
        }
    }

    //O(nlogn) ~ O(n^2)
    public static <E extends Comparable<E>> void sort2(E[] arr){
        for (int i = arr.length/2; i >= 1; i = i/2){
            //O(n^2/i)
            for (int start = i; start < arr.length; start++){
                E t = arr[start];
                int j;

                for (j = start; j >= i; j = j - i) {
                    if (arr[j - i].compareTo(t) > 0)
                        arr[j] = arr[j - i];
                    else break;
                }

                arr[j] = t;
            }
        }
    }

    //changing gap(distance between elements) (gap as hyperparameter which determines the sorting)
    public static <E extends Comparable<E>> void sort3(E[] arr){
        int h = 1;
        while (h < arr.length) h = h * 3 + 1;

        for (int i = h; i >= 1; i = i/3){
            //O(n^2/i)
            for (int start = i; start < arr.length; start++){
                E t = arr[start];
                int j;

                for (j = start; j >= i; j = j - i) {
                    if (arr[j - i].compareTo(t) > 0)
                        arr[j] = arr[j - i];
                    else break;
                }

                arr[j] = t;
            }
        }
    }

    public static void main(String[] args){
        Integer[] arr = {9, 7, 5, 4, 6, 6, 6, 12, 1, 2};

        ShellSort.sort3(arr);

        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        Integer[] arr1 = ArrayGenerator.generateRandomArray(100000, 10000);
        SortingHelper.sortTest("ShellSort", arr1);
    }
}