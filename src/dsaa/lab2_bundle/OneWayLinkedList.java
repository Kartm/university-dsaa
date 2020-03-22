package dsaa.lab2_bundle;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface IList<E> extends Iterable<E> {
    boolean add(E e); // add element to the end of list
    void add(int index, E element) throws NoSuchElementException; // add element on position index
    void clear(); // delete all elements
    boolean contains(E element); // is list containing an element (equals())
    E get(int index) throws NoSuchElementException; //get element from position
    E set(int index, E element) throws NoSuchElementException; // set new value on position
    int indexOf(E element); // where is element (equals())
    boolean isEmpty();
    Iterator<E> iterator();
    ListIterator<E> listIterator() throws UnsupportedOperationException; // for ListIterator
    E remove(int index) throws NoSuchElementException; // remove element from position index
    boolean remove(E e); // remove element
    int size();
}

class OneWayLinkedList<E> implements IList<E> {
    private class Element{
        public Element(E e) {
            this.object=e;
        }
        E object;
        Element next=null;
    }

    protected final Element sentinel;

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
        int i = 0;

        for (E next : this) {
            if (next.equals(element)) {
                return i;
            }
            i++;
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

class Link{
    public final String ref;

    public Link(String ref) {
        this.ref=ref;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Link other = (Link) obj;
        if(other.ref == null) {
            return false;
        }
        return this.ref.contentEquals(other.ref);
    }
}

class Document{
    public final String name;
    public final OneWayLinkedList<Link> links;
    private static final Pattern linkPattern = Pattern.compile("link=[a-z]\\w*", Pattern.CASE_INSENSITIVE);

    public Document(String name, Scanner scan) {
        this.name = name;
        links = new OneWayLinkedList<>();
        load(scan);
    }

    public void load(Scanner scanner) {
        while(scanner.hasNext()) {
            String token = scanner.next();
            if(token.contains("eod")) {
                break;
            } else if (correctLink(token)) {
                String[] splittedLine = token.split("=");
                String link = "";
                if(splittedLine.length > 1) {
                    link = token.split("=")[1];
                }
                links.add(new Link(link));
            }
        }
    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    private static boolean correctLink(String link) {
        Matcher matcher = linkPattern.matcher(link);
        return matcher.matches();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Document: " + this.name);
        for (Link link : links) {
            result.append("\n").append(link.ref);
        }

        return result.toString().trim();
    }

}