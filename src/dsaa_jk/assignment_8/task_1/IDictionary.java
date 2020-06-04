package dsaa_jk.assignment_8.task_1;

public interface IDictionary<K, V> {
    public int size();

    public boolean isEmpty();

    public void put(K key, V value);

    public void delete(K key);

    public V get(K key);
}
