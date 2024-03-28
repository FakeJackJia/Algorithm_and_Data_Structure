import java.util.TreeMap;

//amortized time complexity O(1)
//unorder
public class HashTable<K extends Comparable<K>, V> { //Comparable used only for the conflict values

    //to keep the capacity of hash table to be a prime number (more efficient)
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
               49157, 98317, 196613, 393241, 786433, 1572869, 3145739,
               6291469, 12582917, 25165843, 50331653, 100663319, 201326611,
               402653189, 805306457, 1610612741};

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private int initCapacity = 0;

    //separate chaining: handle conflict by having data with the same hash value into a linked list or others
    private TreeMap<K, V>[] hashTable;
    private int M;
    private int size;

    public HashTable(){
        this.M = capacity[initCapacity];
        size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++)
            hashTable[i] = new TreeMap<>();
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize(){
        return size;
    }

    public void add(K key, V value){
        TreeMap<K, V> map = hashTable[hash(key)];

        if (map.containsKey(key))
            map.put(key, value);
        else {
            map.put(key, value);
            size++;

            if (size >= upperTol * M && initCapacity + 1 < capacity.length) {
                initCapacity++;
                resize(capacity[initCapacity]);
            }
        }
    }

    public V remove(K key){
        TreeMap<K, V> map = hashTable[hash(key)];
        V ret = null;

        if (map.containsKey(key)){
            ret = map.remove(key);
            size--;

            if (size < lowerTol * M && initCapacity - 1 >= 0){
                initCapacity--;
                resize(capacity[initCapacity]);
            }
        }

        return ret;
    }

    public void set(K key, V value){
        TreeMap<K, V> map = hashTable[hash(key)];
        if (!map.containsKey(key))
            throw new IllegalArgumentException(key + "does not exist!");

        map.put(key, value);
    }

    public boolean contains(K key){
        return hashTable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashTable[hash(key)].get(key);
    }

    private void resize(int newM){
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++)
            newHashTable[i] = new TreeMap<>();

        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++){
            TreeMap<K, V> map = hashTable[i];
            for (K key: map.keySet())
                newHashTable[hash(key)].put(key, map.get(key));
        }

        this.hashTable = newHashTable;
    }
}