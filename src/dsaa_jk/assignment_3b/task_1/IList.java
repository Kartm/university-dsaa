package dsaa_jk.assignment_3b.task_1;

import java.util.Iterator;
import java.util.ListIterator;

public interface IList<E> extends Iterable<E> {
    boolean add(E value);

    boolean add(int index, E value);

    void clear();

    boolean contains(E value);

    E get(int index);

    E set(int index, E value);

    int indexOf(E value);

    boolean isEmpty();

    Iterator<E> iterator();

    ListIterator<E> listIterator();

    E remove(int index);

    boolean remove(E value);

    int size();
}
