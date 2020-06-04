package dsaa_jk.assignment_8.task_1;

import java.util.LinkedList;

public class ChainingHashTable<K, V> implements IDictionary<K, V> {
    private static final int DEFAULT_CAPACITY = 4;

    private int numberOfElements;
    private int capacity;
    private LinkedList<Node>[] array;

    public ChainingHashTable() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ChainingHashTable(int capacity) {
        this.capacity = capacity;
        array = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            this.array[i] = new LinkedList<>();
        }
    }

    private void resize(int newCapacity) {
        ChainingHashTable<K, V> newArray = new ChainingHashTable<K, V>(newCapacity);
        for (int i = 0; i < capacity; i++) {
            var currentBucket = array[i];

            for (Node bucketNode : currentBucket) {
                newArray.put(bucketNode.getKey(), bucketNode.getValue());
            }
        }

        this.capacity = newArray.capacity;
        this.numberOfElements = newArray.numberOfElements;
        this.array = newArray.array;
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public int size() {
        return numberOfElements;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public V get(K key) {
        int i = getBucketIndex(key);
        var bucket = array[i];
        for (Node item : bucket) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public void put(K key, V value) {
        int i = getBucketIndex(key);
        array[i].add(new Node(key, value));

        numberOfElements++;

        // double the capacity if needed
        if (getLoadFactor() > 0.5) {
            this.resize(this.capacity * 2);
        }
    }

    public double getLoadFactor() {
        // (number of entries)/(number of buckets)
        return 1.0 * numberOfElements / capacity;
    }

    public void delete(K key) {
        int i = getBucketIndex(key);
        var bucket = array[i];

        for (Node item : bucket) {
            if (item.getKey().equals(key)) {
                array[i].remove(item);
                numberOfElements--;
            }
        }

        // halves size of array if it's 12.5% full or less
        if (getLoadFactor() < 0.125) {
            this.resize(this.capacity / 2);
        }
    }

    private class Node {
        private final K key;
        private final V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }
    }
}