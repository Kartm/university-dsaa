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
            if(hasNext()) {
                element = element.next;
                return element.object;
            } else {
                throw new NullPointerException();
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
            return current.prev != null && current.prev.object != null;
        }

        @Override
        public E next() {
            if(hasNext()) {
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
            if(hasPrevious()) {
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
            if(current != head && current != tail) {
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
        if(!isIndexCorrect(index)) {
            throw new IndexOutOfBoundsException();
        }

        int i = 0;
        Element temp = head;

        while(i <= index) {
            temp = temp.next;
            if(i == index) {
                break;
            }
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
        if(!isIndexCorrect(index)) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        Iterator<E> it = iterator();

        while(it.hasNext()) {
            if(i == index) {
                return it.next();
            }
            it.next();
            i++;
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        if(!isIndexCorrect(index)) {
            throw new IndexOutOfBoundsException();
        }

        E temp = remove(index);
        add(index, element);
        return temp;
    }

    @Override
    public int indexOf(E element) {
        Iterator<E> it = this.iterator();
        int i = 0;
        while(it.hasNext()) {
            if(it.next().equals(element)) {
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
        int i = 0;
        Iterator<E> it = iterator();
        Element current = head;
        while(it.hasNext()) {
            if(i == index) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                return current.object;
            }
        }
        return null;
    }

    @Override
    public boolean remove(E e) {
        int indexOfE = indexOf(e);
        if(indexOfE == -1) {
            // todo not sure which to return
            // throw new NoSuchElementException();
            return false;
        }
        remove(indexOfE);
        return true;
    }

    @Override
    public int size() {
        int i = 0;
        Iterator<E> it = iterator();
        while(it.hasNext()) {
            i++;
            it.next();
        }
        return i;
    }

    public String toStringReverse() {
        StringBuilder retStr = new StringBuilder();

        ListIterator<E> iter = new InnerListIterator();
        while (iter.hasPrevious())
        {
            retStr.append(iter.previous().toString());
        }

        return retStr.toString();
    }

    public void add(TwoWayLinkedListWithHead<E> other) {
        // todo check if the same list
        tail.next = other.head.next;
        head.prev = other.tail.prev;
    }

    private boolean isIndexCorrect(int index) {
        return index >= 0 && index <= size();
    }
}
