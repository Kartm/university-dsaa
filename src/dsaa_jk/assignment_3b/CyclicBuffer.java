package dsaa_jk.assignment_3b;

public class CyclicBuffer {
    public Object[] elements = null;

    private int size = 0;
    private int writePosition;
    private int fillCount;

    public CyclicBuffer(int size) {
        this.size = size;
        this.elements = new Object[size];
        this.reset();
    }

    public void reset() {
        this.writePosition = 0;
        this.fillCount = 0;
    }

    public boolean add(Object element) {
        // insert the object only if buffer not full
        if (fillCount < size) {
            if (writePosition >= size) {
                // wrap and start writing from the beginning
                writePosition = 0;
            }
            elements[writePosition] = element;
            writePosition++;
            fillCount++;
            return true;
        }
        return false;
    }

    public Object get() {
        if (fillCount == 0) {
            return null;
        }

        int nextPosition = writePosition - fillCount;

        // wrap back the read position
        if (nextPosition < 0) {
            nextPosition += this.size;
        }
        Object nextObj = elements[nextPosition];
        fillCount--;
        return nextObj;
    }
}
