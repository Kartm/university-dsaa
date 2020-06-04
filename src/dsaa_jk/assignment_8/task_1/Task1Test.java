package dsaa_jk.assignment_8.task_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class Task1Test {
    DictionaryKey keyA = new DictionaryKey("abc");
    DictionaryKey keyB = new DictionaryKey("alongword");
    DictionaryKey keyC = new DictionaryKey("alongwordandevenlonger");

    @Test
    void testChainingHashTable() {
        IDictionary<DictionaryKey, String> dictionaryChaining = new ChainingHashTable<>();

        dictionaryChaining.put(keyA, "abc");
        dictionaryChaining.put(keyB, "abcdef");
        dictionaryChaining.put(keyC, "abcdefghij");

        assertEquals(dictionaryChaining.get(keyA), "abc");
        assertEquals(dictionaryChaining.get(keyB), "abcdef");
        assertEquals(dictionaryChaining.get(keyC), "abcdefghij");

        dictionaryChaining.delete(keyA);

        assertNull(dictionaryChaining.get(keyA));
        assertEquals(dictionaryChaining.get(keyB), "abcdef");
        assertEquals(dictionaryChaining.get(keyC), "abcdefghij");

        dictionaryChaining.delete(keyB);
        dictionaryChaining.delete(keyC);

        assertNull(dictionaryChaining.get(keyA));
        assertNull(dictionaryChaining.get(keyB));
        assertNull(dictionaryChaining.get(keyC));
    }

    @Test
    void testLinearProbingHashTable() {
        IDictionary<DictionaryKey, String> dictionaryLinearProbing = new LinearProbingHashTable<>();

        dictionaryLinearProbing.put(keyA, "abc");
        dictionaryLinearProbing.put(keyB, "abcdef");
        dictionaryLinearProbing.put(keyC, "abcdefghij");

        assertEquals(dictionaryLinearProbing.get(keyA), "abc");
        assertEquals(dictionaryLinearProbing.get(keyB), "abcdef");
        assertEquals(dictionaryLinearProbing.get(keyC), "abcdefghij");

        dictionaryLinearProbing.delete(keyA);

        assertNull(dictionaryLinearProbing.get(keyA));
        assertEquals(dictionaryLinearProbing.get(keyB), "abcdef");
        assertEquals(dictionaryLinearProbing.get(keyC), "abcdefghij");

        dictionaryLinearProbing.delete(keyB);
        dictionaryLinearProbing.delete(keyC);

        assertNull(dictionaryLinearProbing.get(keyA));
        assertNull(dictionaryLinearProbing.get(keyB));
        assertNull(dictionaryLinearProbing.get(keyC));
    }
}