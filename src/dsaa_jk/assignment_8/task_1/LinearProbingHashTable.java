package dsaa_jk.assignment_8.task_1;

public class LinearProbingHashTable<K, V> implements IDictionary<K, V> {
    private static final int DEFAULT_CAPACITY = 4;

    private int numberOfElements;
    private int capacity;
    private K[] keys;
    private V[] values;

    public LinearProbingHashTable() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public LinearProbingHashTable(int capacity) {
        this.capacity = capacity;
        numberOfElements = 0;

        keys = (K[]) new Object[this.capacity];
        values = (V[]) new Object[this.capacity];
    }

    public int size() {
        return this.numberOfElements;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean containsKey(K K) {
        return get(K) != null;
    }

    public int getCapacity() {
        return this.capacity;
    }

    private int getBucket(K key) {
        return Math.abs(key.hashCode() % getCapacity());
    }

    // resizes the hash table to the given capacity by re-hashing all of the keys
    private void resize(int capacity) {
        LinearProbingHashTable<K, V> temp = new LinearProbingHashTable<>(capacity);
        for (int i = 0; i < this.capacity; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], values[i]);
            }
        }

        keys = temp.keys;
        values = temp.values;
        this.capacity = temp.capacity;
    }

    public void put(K key, V value) {
        if (value == null) {
            delete(key);
            return;
        }

        int i;
        for (i = getBucket(key); keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        numberOfElements++;

        // doubles the size of the array if needed
        if (getLoadFactor() > 0.5) {
            this.resize(this.capacity * 2);
        }
    }

    public V get(K key) {
        for (int i = getBucket(key); keys[i] != null; i = (i + 1) % capacity)
            if (keys[i].equals(key))
                return values[i];
        return null;
    }

    public double getLoadFactor() {
        // (number of entries)/(number of buckets)
        return 1.0 * numberOfElements / capacity;
    }

    public void delete(K key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!containsKey(key)) return;

        // find position i of K
        int i = getBucket(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % capacity;
        }

        // delete K and associated V
        keys[i] = null;
        values[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % capacity;
        while (keys[i] != null) {
            // delete keys[i] an values[i] and reinsert
            K KToRehash = keys[i];
            V valToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            numberOfElements--;
            put(KToRehash, valToRehash);
            i = (i + 1) % capacity;
        }

        numberOfElements--;

        // halves size of array if it's 12.5% full or less
        if (getLoadFactor() < 0.125) {
            this.resize(this.capacity / 2);
        }
    }
}