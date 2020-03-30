package dsaa_jk.assignment_3b.task_1;

import java.io.IOException;

public class UnboundedQueue<E> implements Queue<E> {
    private Element head;

    public UnboundedQueue() {
    }

    private class Element {

        private E value;
        private Element next;

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }


        Element(E data) {
            this.value = data;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(E value) {
        Element newElem = new Element(value);
        if (head == null)
            head = newElem;
        else {
            Element tail = head;
            while (tail.getNext() != null)
                tail = tail.getNext();
            tail.setNext(newElem);
        }
    }

    public E dequeue() throws EmptyQueueException {
        if (head == null) throw new EmptyQueueException();
        E retValue = head.getValue();
        head = head.getNext();
        return retValue;
    }

    public E first() throws EmptyQueueException {
        if (head == null) throw new EmptyQueueException();
        return head.getValue();
    }

    public void clear() {
        head = null;
    }

    public int size() {
        int pos = 0;
        Element actElem = head;
        while (actElem != null) {
            pos++;
            actElem = actElem.getNext();
        }
        return pos;
    }


    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("[\n");
        Element e = head;
        while (e != null) {
            buf.append(e.getValue());
            buf.append("\n");
            e = e.getNext();
        }
        buf.append("]\n");
        return buf.toString();
    }

    public void print(){
        System.out.println(this);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
