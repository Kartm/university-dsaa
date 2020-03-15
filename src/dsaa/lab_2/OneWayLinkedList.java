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

    Element sentinel;

    private class InnerIterator implements Iterator<E>{
        // TODO
        public InnerIterator() {
            // TODO
        }
        @Override
        public boolean hasNext() {
            // TODO
            return false;
        }

        @Override
        public E next() {
            // TODO
            return null;
        }
    }

    public OneWayLinkedList() {
        // make a sentinel
        // TODO
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
    public boolean add(E e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        // TODO Auto-generated method stub

    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean contains(E element) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public E get(int index) throws NoSuchElementException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int indexOf(E element) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(E e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

}


