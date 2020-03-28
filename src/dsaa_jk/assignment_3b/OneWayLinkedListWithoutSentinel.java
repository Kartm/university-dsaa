package dsaa_jk.assignment_3b;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OneWayLinkedListWithoutSentinel<E> {
    Node<E> head;

    private class InnerIterator implements Iterator<E> {
        Node<E> node;

        public InnerIterator() {
            node = head;
        }

        @Override
        public boolean hasNext() {
            return node.next != null;
        }

        @Override
        public E next() {
            if (hasNext()) {
                node = node.next;
                return node.object;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    public OneWayLinkedListWithoutSentinel() {
        this.head = null;
    }

    public OneWayLinkedListWithoutSentinel(E object) {
        this.head = new Node<>(object);
    }

    public InnerIterator iterator() {
        return new InnerIterator();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(isEmpty()) {
            stringBuilder.append("LIST IS EMPTY").append("\n");
        } else {
            Iterator<E> it = iterator();
            stringBuilder.append(head.object.toString()).append("\n");
            while(it.hasNext()) {
                stringBuilder.append(it.next().toString()).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public void add(E object) {
        Node<E> newNode = new Node<>(object);

        if(this.head == null) {
            this.head = newNode;
            return;
        }

        newNode.next = null;
        Node<E> lastNode = head;
        Iterator<E> it = iterator();
        while(it.hasNext()) {
            lastNode = lastNode.next;
            it.next();
        }

        lastNode.next = newNode;
    }
}
