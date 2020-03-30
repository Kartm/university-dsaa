package dsaa_jk.assignment_3b.task_1;

public interface Queue<E> {
    void enqueue(E value);
    E dequeue() throws EmptyQueueException;
    void clear();
    int size();
    boolean isEmpty();
}
