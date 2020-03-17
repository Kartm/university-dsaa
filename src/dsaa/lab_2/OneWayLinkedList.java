package dsaa.lab_2;
import java.util.*;

class OneWayLinkedList<E> implements IList<E>{
    private class Element{
        public Element(E e) {
            this.object=e;
        }
        E object;
        Element next=null;
    }

    protected Element sentinel;

    private class InnerIterator implements Iterator<E>{
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

    public OneWayLinkedList() {
        this.sentinel = new Element(null);
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
    public void add(int index, E element) throws NoSuchElementException {
        if(index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }

        Element curr = sentinel;

        int i = 0;
        while(i <= this.size()) {
            if(index == i) {
                Element temp = curr.next;
                curr.next = new Element(element);
                curr.next.next = temp;
                return;
            } else {
                curr = curr.next;
            }
            i++;
        }
    }

    @Override
    public boolean add(E e) {
        add(this.size(), e);
        return true;
    }

    @Override
    public void clear() {
        sentinel.object = null;
        sentinel.next = null;
    }

    @Override
    public boolean contains(E element) {
        InnerIterator it = new InnerIterator();
        while(it.hasNext()) {
            if(it.next().equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) throws NoSuchElementException {
        if(index > this.size()) {
            throw new NoSuchElementException();
        }
        int i = 0;
        InnerIterator it = new InnerIterator();
        while(it.hasNext()) {
            E element = it.next();
            if(i == index) {
                return element;
            }
            i++;
        }

        throw new NoSuchElementException();
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException {
        if(index > this.size()) {
            throw new NoSuchElementException();
        }

        E replacedElement = remove(index);
        add(index, element);
        return replacedElement;
    }

    @Override
    public int indexOf(E element) {
        if(contains(element)) {
            int i = 0;

            for (E next : this) {
                if (next.equals(element)) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.object == null && sentinel.next == null;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {
        if(index >= this.size()) {
            throw new NoSuchElementException();
        }

        Element e = sentinel;
        int i = 0;
        while(e.next != null) {
            if(i == index) {
                E oldValue = e.next.object;
                e.next = e.next.next;
                return oldValue;
            }
            e = e.next;
            i++;
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean remove(E e) {
        int indexOfE = indexOf(e);
        if(indexOfE == -1) {
            return false;
        }
        remove(indexOfE);
        return true;
    }

    @Override
    public int size() {
        Iterator<E> iterator = this.iterator();
        int size = 0;
        while(iterator.hasNext()) {
            size++;
            iterator.next();
        }
        return size;
    }
}