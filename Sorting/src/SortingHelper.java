public class SortingHelper {
    private SortingHelper(){};

    public static <E extends Comparable<E>> boolean isSorted(E[] arr){
        for(int i = 1; i < arr.length; i++){
            if (arr[i-1].compareTo(arr[i])>0)
                return false;
        }

        return true;
    }

    public static <E extends Comparable<E>>void sortTest(String sortname, E[] arr){
        long startTime = System.nanoTime();

        if(sortname.equals("SelectionSort"))
            SelectionSort.selectionSort(arr);
        if(sortname.equals("InsertionSort"))
            InsertionSort.insertionSort(arr);
        if(sortname.equals("ShellSort"))
            ShellSort.sort3(arr);
        if(sortname.equals("BucketSort")) {
            Integer[] intArr = (Integer[])arr;
            BucketSort.sort2(intArr, 200);
        }
        long endTime = System.nanoTime();

        double time = (endTime - startTime)/1000000000.0;

        if(!SortingHelper.isSorted(arr))
            throw new RuntimeException("Sorting Failed");

        System.out.println(time + "s");
    }
}
