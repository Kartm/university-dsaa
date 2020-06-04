package dsaa_jk.assignment_8.task_1;

public class Task1 {

    public static void main(String[] args) {
        IDictionary<DictionaryKey, String> dictionaryChaining = new ChainingHashTable<>();
        IDictionary<DictionaryKey, String> dictionaryLinearProbing = new LinearProbingHashTable<>();

        DictionaryKey keyA = new DictionaryKey("abc");
        DictionaryKey keyB = new DictionaryKey("alongword");

        dictionaryChaining.put(keyA, "some value with key abc");
        dictionaryChaining.put(keyB, "some value with key alongword");
        dictionaryLinearProbing.put(keyA, "some value with key abc");
        dictionaryLinearProbing.put(keyB, "some value with key alongword");

        // keyA and keyB are generating the same hashcodes
        System.out.println(keyA.hashCode());
        System.out.println(keyB.hashCode());

        System.out.println("--- Dictionary with chained hashtable ---");

        System.out.println(dictionaryChaining.get(keyA));
        System.out.println(dictionaryChaining.get(keyB));
        dictionaryChaining.delete(keyA);
        System.out.println(dictionaryChaining.get(keyA));
        System.out.println(dictionaryChaining.get(keyB));

        System.out.println("--- Dictionary with linear probing hashtable ---");

        System.out.println(dictionaryLinearProbing.get(keyA));
        System.out.println(dictionaryLinearProbing.get(keyB));
        dictionaryLinearProbing.delete(keyA);
        System.out.println(dictionaryLinearProbing.get(keyA));
        System.out.println(dictionaryLinearProbing.get(keyB));
    }
}
