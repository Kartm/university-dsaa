package dsaa.lab_7;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;

class HashTable {
    LinkedList<Object>[] arr; // use pure array
    private final static int defaultInitSize = 8;
    private final static double defaultMaxLoadFactor = 0.7;
    private int size;
    private int numberOfEntries;
    private final double maxLoadFactor;

    public HashTable() {
        this(defaultInitSize);
    }

    public HashTable(int size) {
        this(size, defaultMaxLoadFactor);
    }

    public HashTable(int initCapacity, double maxLF) {
        this.size = initCapacity;
        this.maxLoadFactor = maxLF;
        this.numberOfEntries = 0;
        this.arr = new LinkedList[initCapacity];

        for(int i = 0; i < this.arr.length; i++) {
            this.arr[i] = new LinkedList<Object>();
        }
    }

    public boolean add(Object elem) {
        // cannot add a document with a name which is still in
        // the hash table. In this case it has to return false.
        if(this.get(elem) != null) {
            return false;
        }

        int insertPosition = getInsertPosition(elem);

        // It is assumed that add() operation for a list in this
        // array add a new link on the end of the list
        this.arr[insertPosition].add(elem);
        numberOfEntries++;

        if(getLoadFactor() > this.maxLoadFactor) {
            this.doubleArray();
        }
        return true;
    }

    public double getLoadFactor() {
        // (number of entries)/(number of buckets)
        return 1.0 * numberOfEntries/size;
    }

    private int getInsertPosition(Object obj) {
        BigInteger hash = ((Document)obj).hashCodeBigInt();
        return hash.mod(BigInteger.valueOf(this.size)).intValueExact();
    }

    private void doubleArray() {
        /*If the number of elements exceed the load factor, the array capacity have to be
        doubled using function doubleArray() and element have to be moved to proper
        list base on the hashCode().*/
        this.size *= 2;

        LinkedList<Object>[] newArr = new LinkedList[this.size];
        for(int i = 0; i < this.size; i++) {
            newArr[i] = new LinkedList<Object>();
        }

        for (LinkedList<Object> objects : this.arr) {
            for (Object element : objects) {
                int insertPosition = getInsertPosition(element);
                newArr[insertPosition].add(element);
            }
        }

        this.arr = newArr;
    }

    @Override
    public String toString() {
        /*
        The toString() function for a HashTable will print an array of list in the form:
        index of an array, colon. If the list is not empty print one space, then the name of the
        first document in a list. If there is a next document print semicolon, one space and the
        name for the second document etc. Every position in the array in separated line. To
        have an access to a name of the document cast the type of element to the type
        IWithName
         */
        StringBuilder strBuilder = new StringBuilder();

        for (int i = 0; i < this.arr.length; i++) {
            strBuilder.append(i).append(":");

            for (Object object : this.arr[i]) {
                IWithName elementWithName = (IWithName) object;
                strBuilder.append(" ").append(elementWithName.getName()).append(",");
            }
            if (this.arr[i].size() > 0) {
                // remove trailing comma
                strBuilder.deleteCharAt(strBuilder.lastIndexOf(","));
            }
            strBuilder.append("\n");
        }
        return strBuilder.toString();
    }

    public Object get(Object toFind) {
        int searchPosition = getInsertPosition(toFind);
        for(Object candidate: this.arr[searchPosition]) {
            if(candidate.equals(toFind)) {
                return candidate;
            }
        }
        return null;
    }

}
