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

        keys = (K[]) new Object[capacity];
        values = (V[]) new Object[capacity];
    }

    public int size() {
        return this.numberOfElements;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int getCapacity() {
        return this.capacity;
    }

    private int getBucket(K key) {
        return Math.abs(key.hashCode() % getCapacity());
    }

    private void resize(int capacity) {
        LinearProbingHashTable<K, V> newHashTable = new LinearProbingHashTable<>(capacity);

        for (int i = 0; i < this.capacity; i++) {
            if (keys[i] != null) {
                newHashTable.put(keys[i], values[i]);
            }
        }

        keys = newHashTable.keys;
        values = newHashTable.values;
        this.capacity = newHashTable.capacity;
    }

    public void put(K key, V value) {
        int i;
        for (i = getBucket(key); keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(key)) {
                // replace the value since the key already exists
                values[i] = value;
                return;
            }
        }

        // found next "unoccupied" spot
        // put the element there
        keys[i] = key;
        values[i] = value;
        numberOfElements++;

        // doubles the size of the array if needed
        if (getLoadFactor() > 0.5) {
            this.resize(this.capacity * 2);
        }
    }

    public V get(K key) {
        for (int i = getBucket(key); keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }

        return null;
    }

    public double getLoadFactor() {
        // (number of entries)/(number of buckets)
        return 1.0 * numberOfElements / capacity;
    }

    public void delete(K key) {
        // do nothing if the key is absent
        if (get(key) == null) {
            return;
        }

        int i = getBucket(key);
        // find the occurrence of key
        while (!key.equals(keys[i])) {
            i = (i + 1) % capacity;
        }

        // remove key and value
        keys[i] = null;
        values[i] = null;

        // rehashing all keys at the front
        i = (i + 1) % capacity;
        while (keys[i] != null) {
            K tempKey = keys[i];
            V tempValue = values[i];

            // remove and insert again
            keys[i] = null;
            values[i] = null;
            numberOfElements--;

            put(tempKey, tempValue);
            i = (i + 1) % capacity;
        }

        numberOfElements--;

        // halves size of array if it's 12.5% full or less
        if (getLoadFactor() < 0.125) {
            this.resize(this.capacity / 2);
        }
    }
}