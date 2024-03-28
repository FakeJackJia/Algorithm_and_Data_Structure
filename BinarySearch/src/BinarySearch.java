public class BinarySearch {
    private BinarySearch(){}

    //Using recursion
    public static<E extends Comparable<E>> int binarySearchR(E[] data, E target){
        return searchR(data, target, 0, data.length - 1);
    }

    private static <E extends Comparable<E>> int searchR(E[] data, E target, int l, int r){
        if (l > r) return -1;

        int mid = l + (r - l)/2;
        if (data[mid].compareTo(target) == 0){
            return mid;
        }
        else if (data[mid].compareTo(target) > 0){
            return searchR(data, target, l, mid - 1);
        } else {
            return searchR(data, target, mid + 1, r);
        }
    }

    //Using loop
    public static<E extends Comparable<E>> int binarySearchLoop(E[] data, E target){
        int l = 0;
        int r = data.length - 1;

        while (r >= l){
            int mid = l + (r - l)/2;
            if (data[mid].compareTo(target) == 0)
                return mid;
            else if (data[mid].compareTo(target) > 0)
                r = mid - 1;
            else
                l = mid + 1;
        }

        return -1;
    }

    //Upper binary search - return the smallest index that is great than target
    public static<E extends Comparable<E>> int upper(E[] data, E target){
        int l = 0;
        int r = data.length;

        while (l != r){
            int mid = l + (r - l)/2;

            if (data[mid].compareTo(target) > 0)
                r = mid;
            else
                l = mid + 1;
        }

        return r;
    }

    //Upper Ceil
    public static<E extends Comparable<E>> int upperCeil(E[] data, E target){
        int u = upper(data, target);

        if (u - 1 >= 0 && data[u - 1].compareTo(target) == 0)
            return u - 1;

        return u;
    }

    //Lower Ceil
    public static<E extends Comparable<E>> int lowerCeil(E[] data, E target){
        int u = upper(data, target);

        while (u - 1 >= 0 && data[u - 1].compareTo(target) == 0)
            u--;

        return u;
    }

    //Lower binary search - return the largest index that is smaller than target
    public static<E extends Comparable<E>> int lower(E[] data, E target){
        int l = -1;
        int r = data.length - 1;

        while (l != r){
            //be careful about this, since it is lower division which works for others above
            int mid = l + (r + 1 - l)/2;

            if (data[mid].compareTo(target) >= 0)
                r = mid - 1;
            else
                l = mid;
        }

        return r;
    }

    //Lower Floor
    public static<E extends Comparable<E>> int lowerFloor(E[] data, E target){
        int l = lower(data, target);

        if (l + 1 < data.length && data[l + 1] == target)
            return l + 1;

        return l;
    }

    //Upper Floor
    public static<E extends Comparable<E>> int upperFloor(E[] data, E target){
        int l = lower(data, target);

        while (l + 1 < data.length && data[l + 1] == target)
            l++;

        return l;
    }

    public static void main(String[] arg){
        Integer[] arr = {1, 1, 3, 3, 5, 5, 7, 7, 9};

        System.out.println(upperFloor(arr, 4));
    }
}