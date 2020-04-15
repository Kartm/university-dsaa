package dsaa.lab_5;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class TwoWayCycledOrderedListWithSentinel<E> implements IList<E> {

    private class Element {
        public Element(E e) {
            this.object = e;
            this.next = this;
            this.prev = this;
        }

        public Element(E e, Element next, Element prev) {
            this.object = e;
            this.next = next;
            this.prev = prev;
        }

        // add element e after this
        public void addAfter(Element elem) {
            elem.next = this.next;
            elem.prev = this;
            this.next.prev = elem;
            this.next = elem;
        }

        // assert it is NOT a last element in a list
        public void remove() {
            this.next.prev = this.prev;
            this.prev.next = this.next;
        }

        E object;
        Element next = null;
        Element prev = null;
    }


    Element sentinel;
    int size;

    private class InnerIterator implements Iterator<E> {
        Element element;
        int pos;

        public InnerIterator() {
            pos = 0;
            element = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public E next() {
            E elem = element.object;
            element = element.next;
            pos++;
            return elem;
        }
    }

    private class InnerListIterator implements ListIterator<E> {
        Element p;
        int pos = 0;

        public InnerListIterator() {
            pos = 0;
            p = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public E next() {
            E elem = p.object;
            p = p.next;
            pos++;
            return elem;
        }

        @Override
        public void add(E arg0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasPrevious() {
            return pos > 0;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            pos--;
            p = p.prev;
            return p.object;
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
        public void set(E arg0) {
            throw new UnsupportedOperationException();
        }
    }

    public TwoWayCycledOrderedListWithSentinel() {
        sentinel = new Element(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        Element p = sentinel.next;
        while (p != sentinel && ((Comparable<E>) p.object).compareTo(e) <= 0)
            p = p.next;
        p.prev.addAfter(new Element(e));
        size++;
        return true;
    }

    private Element getElement(int index) {
        checkIndex(index);
        Element p = sentinel.next;
        while (index > 0) {
            index--;
            p = p.next;
        }
        return p;
    }

    private Element getElement(E obj) {
        Element p = sentinel.next;
        while (p != sentinel) {
            if (p.object.equals(obj))
                return p;
            p = p.next;
        }
        return null;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();

    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new NoSuchElementException();
    }

    @Override
    public void clear() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public E get(int index) {
        return getElement(index).object;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(E element) {
        Element p = sentinel.next;
        int counter = 0;
        counter++;
        while (p != sentinel) {
            if (p.object.equals(element))
                return counter;
            else {
                counter++;
                p = p.next;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new InnerListIterator();
    }

    @Override
    public E remove(int index) {
        Element p = getElement(index);
        E retValue = p.object;
        p.remove();
        size--;
        return retValue;
    }

    @Override
    public boolean remove(E e) {
        Element p = getElement(e);
        if (p == null)
            return false;
        p.remove();
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    public String toStringReverse() {
        //TODO
        ListIterator<E> iter = new InnerListIterator();
        while (iter.hasNext())
            iter.next();
        String retStr = "";
        while (iter.hasPrevious())
            retStr += "\n" + iter.previous().toString();
        return retStr;
    }

    @SuppressWarnings("unchecked")
    public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
        if (this == other)
            return;
        if (other.isEmpty())
            return;
        // this empty, but other not
        if (isEmpty()) {
            sentinel.next = other.sentinel.next;
            sentinel.prev = other.sentinel.prev;
            size = other.size;
            other.clear();
        }
        // both not empty
        else {
            Element p1 = sentinel.next;
            Element p2 = other.sentinel.next;
            while (p1 != sentinel && p2 != other.sentinel) {
                if (((Comparable<E>) p1.object).compareTo(p2.object) <= 0) {
                    p1 = p1.next;
                } else {
                    p2 = p2.next;
                    p1.prev.addAfter(p2.prev);
                }
            }
            while (p2 != other.sentinel) {
                p2 = p2.next;
                p1.prev.addAfter(p2.prev);
            }
            size += other.size;
            other.clear();
        }

    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void removeAll(E e) {
        Element p = getElement(e);
        if (p == null)
            return;
        while (p != sentinel && ((Comparable) p.object).compareTo(e) == 0) {
            p.remove();
            p = p.next;
            size--;
        }
    }

}
