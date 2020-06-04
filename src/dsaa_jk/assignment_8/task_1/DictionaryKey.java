package dsaa_jk.assignment_8.task_1;

class DictionaryKey {
    private final String stringVal;

    public DictionaryKey(String key) {
        this.stringVal = key;
    }

    @Override
    public int hashCode() {
        int sum = 10;

        for (char letter : stringVal.toCharArray()) {
            sum += letter == 'a' ? 1 : 0;
        }

        return sum;
    }

    @Override
    public String toString() {
        return this.stringVal;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (getClass() != other.getClass())
            return false;

        DictionaryKey castedOther = (DictionaryKey) other;
        if (castedOther.toString() == null) {
            return false;
        } else {
            return this.toString().equals(other.toString());
        }
    }
}
