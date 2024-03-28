public class SelectionSort {
    private SelectionSort(){}

    public static <E extends Comparable<E>> void selectionSort(E[] data){
        int min;

        for(int i = 0; i < data.length; i++){
            min = i;
            for(int j = i + 1; j < data.length; j++){
                if (data[j].compareTo(data[min]) < 0){
                    min = j;
                }
            }

            swap(data, i, min);
        }
    }

    private static <E> void swap(E[] data, int i, int j){
        E temp = data[j];
        data[j] = data[i];
        data[i] = temp;
    }

    public static void main(String[] args){
        Integer[] data = {3, 5, 1, 7, 6, 4};
        SelectionSort.selectionSort(data);

        for (int e : data) {
            System.out.print(e + " ");
        }

        System.out.println();

        Student[] students = {new Student("Alice", 98), new Student("Bobo", 100),
                              new Student("Charles", 66)};

        SelectionSort.selectionSort(students);
        for (Student s : students){
            System.out.print(s.toString() + " ");
        }
        System.out.println();

        Integer[] arr = ArrayGenerator.generateRandomArray(10000, 10000);

        SortingHelper.sortTest("SelectionSort", arr);
    }
}
