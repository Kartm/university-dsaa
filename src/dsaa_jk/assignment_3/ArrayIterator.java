package dsaa_jk.assignment_3;

public class ArrayIterator<T> implements Iterator<T> {
    private T[] array;
    private int pos;

    public ArrayIterator(T[] array){
        this.array = array;
        this.pos = 0;
    }

    @Override
    public T next() {
        return hasNext() ? array[pos++] : null;
    }

    @Override
    public boolean hasNext() {
        return pos < this.array.length;
    }

    public void first() {
        this.pos = 0;
    }
}
