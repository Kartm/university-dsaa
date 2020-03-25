package dsaa.lab_3;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class TwoWayLinkedListWithHead<E> implements IList<E> {

    private class Element {
        public Element(E e) {
            this.object = e;
        }

        public Element(E e, Element next, Element prev) {
            this.object = e;
            this.next = next;
            this.prev = prev;
        }

        E object;
        Element next = null;
        Element prev = null;
    }

    Element head;
    Element tail;
    // can be realization with the field size or without
    // int size;

    private class InnerIterator implements Iterator<E> {
        Element element;

        public InnerIterator() {
            element = head;
        }

        @Override
        public boolean hasNext() {
            return element.next.object != null;
        }

        @Override
        public E next() {
            if (hasNext()) {
                element = element.next;
                return element.object;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    private class InnerListIterator implements ListIterator<E> {
        // rename p to element
        Element current = head;

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return current.next.object != null;
        }

        @Override
        public boolean hasPrevious() {
            return current.prev != head && current.prev != null;
        }

        @Override
        public E next() {
            if (hasNext()) {
                current = current.next;
                return current.object;
            } else {
                throw new NullPointerException();
            }
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            if (hasPrevious()) {
                current = current.prev;
                return current.object;
            } else {
                throw new NullPointerException();
            }
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

        @Override
        public void set(E e) {
            if (current != head && current != tail) {
                current.object = e;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    public TwoWayLinkedListWithHead() {
        clear();
    }

    @Override
    public void add(int index, E element) {
        if (!isIndexCorrect(index)) {
            throw new NoSuchElementException();
        }

        int i = 0;
        Element temp = head;

        while (i < index) {
            temp = temp.next;
            i++;
        }

        Element newElement = new Element(element, temp.next, temp.next.prev);

        temp.next = newElement;
        temp.next.next.prev = newElement;
    }

    @Override
    public boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override
    public void clear() {
        head = new Element(null);
        tail = new Element(null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public E get(int index) {
        if (!isIndexCorrect(index) || size() == 0 || index == size()) {
            throw new NoSuchElementException();
        }
        int i = 0;
        Iterator<E> it = iterator();

        while (it.hasNext()) {
            if (i == index) {
                return it.next();
            }
            it.next();
            i++;
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        if (!isIndexCorrect(index)) {
            throw new NoSuchElementException();
        }

        E temp = remove(index);
        add(index, element);
        return temp;
    }

    @Override
    public int indexOf(E element) {
        Iterator<E> it = this.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().equals(element)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        if (!isIndexCorrect(index)) {
            throw new NoSuchElementException();
        }

        int i = 0;
        Element current = head;
        while (i <= index) {
            current = current.next;
            i++;
        }
        if (current.next == null) {
            throw new NoSuchElementException();
        }
        E oldValue = current.object;
        current.prev.next = current.next;
        current.next.prev = current.prev;
        return oldValue;
    }

    @Override
    public boolean remove(E e) {
        int indexOfE = indexOf(e);
        if (indexOfE == -1) {
            return false;
        }
        remove(indexOfE);
        return true;
    }

    @Override
    public int size() {
        int i = 0;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            i++;
            it.next();
        }
        return i;
    }

    public String toString() {
        ListIterator<E> it = new InnerListIterator();
        StringBuilder result = new StringBuilder("\n");
        while (it.hasNext()) {
            result.append(it.next()).append("\n");
        }

        return result.toString().trim();
    }

    public String toStringReverse() {
        ListIterator<E> it = new InnerListIterator();

        // move iterator to the end
        E lastItem = null;
        while (it.hasNext()) {
            lastItem = it.next();
        }

        StringBuilder result;
        if(lastItem != null) {
            result = new StringBuilder(lastItem.toString() + "\n");
        } else {
            result = new StringBuilder("");
        }

        while (it.hasPrevious()) {
            result.append(it.previous()).append("\n");
        }

        return result.toString().trim();
    }

    public void add(TwoWayLinkedListWithHead<E> other) {
        if(this.equals(other)) {
            return;
        }

        tail.prev.next = other.head.next;
        other.head.next.prev = tail.prev;
        tail = other.tail;

        // clear references to our list
        // to preserve the other list
        other.clear();
    }

    private boolean isIndexCorrect(int index) {
        return index >= 0 && index <= size();
    }
}
