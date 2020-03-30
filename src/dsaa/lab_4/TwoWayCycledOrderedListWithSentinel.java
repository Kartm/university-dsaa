package dsaa.lab_4;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

class TwoWayCycledOrderedListWithSentinel<E> implements IList<E> {
    private class Element{
        public Element(E e) {
            this.object = e;
        }
        public Element(E e, Element next, Element prev) {
            this.object = e;
            this.next = next;
            this.prev = prev;
        }

        // add element e after this
        public void addAfter(Element other) {
            other.next = this.next;
            this.next.prev = other;
            this.next = other;
            other.prev = this;
        }

        // add element e after this
        public void addBefore(Element other) {
            other.next = this;
            other.prev = this.prev;
            this.prev.next = other;
            this.prev = other;
        }

        // assert it is NOT a sentinel
        public void remove() {
            this.prev.next = this.next;
            this.next.prev = this.prev;
        }
        E object;
        Element next=null;
        Element prev=null;
    }

    Element sentinel;
    int size;

    private class InnerIterator implements Iterator<E> {
        private Element element;

        public InnerIterator() {
            element = sentinel;
        }
        @Override
        public boolean hasNext() {
            return element.next != null;
        }

        @Override
        public E next() throws NullPointerException {
            if(hasNext()) {
                element = element.next;
                return element.object;
            } else {
                throw new NullPointerException();
            }
        }
    }

    private class InnerListIterator implements ListIterator<E> {
        private Element element;

        public InnerListIterator() {
            element = sentinel;
        }
        @Override
        public boolean hasNext() {
            return this.element.next != sentinel;
        }

        @Override
        public E next() {
            this.element = this.element.next;
            return this.element.object;
        }
        @Override
        public void add(E arg0) {
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean hasPrevious() {
            return this.element != sentinel;
        }
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }
        @Override
        public E previous() {
            E currentValue = this.element.object;
            this.element = this.element.prev;
            return currentValue;
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
        this.sentinel = new Element(null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
    }

    //@SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        Element currentElement = this.sentinel.next;

        if (this.isEmpty())
        {
            currentElement.addAfter(new Element(e));
            return true;
        }
        else {
            while (currentElement != this.sentinel)
            {
                // find the first appropriate point to insert new element
                if (((Comparable<E>) currentElement.object).compareTo(e) > 0)
                {
                    currentElement.prev.addAfter(new Element(e));
                    return true;
                }
                currentElement = currentElement.next;
            }

        }
        // reached the end of the list, append
        sentinel.prev.addAfter(new Element(e));
        return true;
//        Element currentElement = this.sentinel.next;
//
//        if (this.isEmpty()) {
//            // find the first appropriate point to insert new element
//            // OR reach the end of the list
//            while(currentElement != sentinel) {
//                // if smaller than current
//                if(((Comparable<E>) currentElement.object).compareTo(e) > 0) {
//                    currentElement.prev.addAfter(new Element(e));
//                    return true;
//                }
//                currentElement = currentElement.next;
//            }
//        }
//
//        // the list is empty - just insert it after sentinel
//        currentElement.prev.addAfter(new Element(e));
//        return true;
    }

    private Element getElement(int index) {
        if(index < 0) {
            throw new NoSuchElementException();
        }
        Element currentElement = this.sentinel.next;
        int i = 0;
        while(currentElement != sentinel && i < index) {
            currentElement = currentElement.next;
            i++;
        }
        if(currentElement == sentinel) {
            throw new NoSuchElementException();
        }
        return currentElement;
    }

    private Element getElement(E obj) {
        Element currentElement = this.sentinel.next;
        while(currentElement != sentinel && (!obj.equals(currentElement.object))) {
            currentElement = currentElement.next;
        }
        if(currentElement == this.sentinel) {
            return null;
        }
        return currentElement;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public E get(int index) {
        Element element = getElement(index);
        return element.object;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(E element) {
        Element currentElement = this.sentinel.next;
        int i = 0;
        while(currentElement != sentinel && !currentElement.object.equals(element)) {
            currentElement = currentElement.next;
            i++;
        }
        if(currentElement == this.sentinel) {
            return -1;
        }
        return i;
    }

    @Override
    public boolean isEmpty() {
        return this.sentinel.next == sentinel;
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
        Element removedElement = getElement(index);
        removedElement.remove();
        return removedElement.object;
    }

    @Override
    public boolean remove(E e) {
        Element removedElement = getElement(e);
        if(removedElement == null) {
            return false;
        }
        removedElement.remove();
        return true;
    }

    @Override
    public int size() {
        Element currentElement = this.sentinel.next;
        int i = 0;
        while(currentElement != sentinel) {
            currentElement = currentElement.next;
            i++;
        }
        return i;
    }

    @Override
    public String toString() {
        String result = "";
        Iterator<E> it = listIterator();
        while(it.hasNext()) {
            result += "\n" + it.next();
        }

        return result;
    }

    //@SuppressWarnings("unchecked")
    public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
        if(this.equals(other)) {
            return;
        }

        while(other.size() > 0) {
            this.add(other.remove(0));
        }
    }

    //@SuppressWarnings({ "unchecked", "rawtypes" })
    public void removeAll(E e) {
        while(this.contains(e)) {
            this.remove(e);
        }
    }

}
