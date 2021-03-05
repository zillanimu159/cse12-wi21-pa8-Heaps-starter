import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

public class HeapTest {
    private Heap<Integer, String> testHeap;
    private static final String[] inputStringValuesArray = new String[] { "Eric", "Nandini",
            "Rebecca", "Greg", "Juan" };
    private static final int[] inputWeightsArray = new int[] { 0, 1, 2, 3, 4 };
    private static final String[] inputValuesArrayAdd = new String[] { "Garo", "Eric", "Nandini",
            "Paul", "Greg", "Rebecca" };
    private static final String[] inputValuesArrayOrganizedMin = new String[] { "Greg", "Paul",
            "Eric", "Garo", "Rebecca", "Nandini" };
    private static final int[] inputVariedWeightsArray = new int[] { 20, 10, 30, 5, 1, 22 };

    @Test
    public void testHeapFunctionality_toArray() {
        testHeap = new Heap<Integer, String>(Integer::compare);
        for (int i = 0; i < inputStringValuesArray.length; i++) {
            testHeap.add(inputWeightsArray[i], inputStringValuesArray[i]);
        }
        assertEquals(testHeap.toArray().size(), 5);
        for (int i = testHeap.size() - 1; i <= 0; i--) {
            assertEquals(inputStringValuesArray[i], testHeap.poll().value);
        }
    }

    @Test
    public void testHeapFunctionality_addMin() {
        testHeap = new Heap<Integer, String>(Collections.reverseOrder(Integer::compare));
        for (int i = 0; i < inputValuesArrayAdd.length; i++) {
            testHeap.add(inputVariedWeightsArray[i], inputValuesArrayAdd[i]);
        }
        assertEquals(testHeap.toArray().size(), 6);
        List<Integer> resultsKeys = new ArrayList<Integer>();
        List<Entry<Integer, String>> resultEntries = testHeap.toArray();
        for (int i = 0; i < resultEntries.size(); i++) {
            resultsKeys.add(resultEntries.get(i).key);
        }
        for (int i = 0; testHeap.right(i+1) < resultsKeys.size(); i++) {
            if (resultsKeys.get(i) > resultsKeys.get(testHeap.left(i+1))
                    || resultsKeys.get(i) > resultsKeys.get(testHeap.right(i+1))) {
                assertTrue(false);
            }
            assertTrue(true);
        }
    }

    @Test
    public void testHeapFunctionality_addMax() {
        testHeap = new Heap<Integer, String>(Integer::compare);
        List<String> expectedValues = new ArrayList<String>();
        for (int i = 0; i < inputValuesArrayAdd.length; i++) {
            testHeap.add(inputVariedWeightsArray[i], inputValuesArrayAdd[i]);
            expectedValues.add(inputValuesArrayAdd[i]);
        }
        assertEquals(testHeap.toArray().size(), 6);
        List<String> results = new ArrayList<String>();
        List<Entry<Integer, String>> resultEntries = testHeap.toArray();
        for (int i = 0; i < resultEntries.size(); i++) {
            results.add(resultEntries.get(i).value);
        }
        assertTrue(expectedValues.containsAll(results));
    }

    @Test
    public void testHeapFunctionality_peek() {
        testHeap = new Heap<Integer, String>(Collections.reverseOrder(Integer::compare));
        for (int i = 0; i < inputStringValuesArray.length; i++) {
            testHeap.add(inputWeightsArray[i], inputStringValuesArray[i]);
        }
        assertEquals(inputStringValuesArray[0], testHeap.peek().value);
        assertEquals(inputWeightsArray[0], (int) testHeap.peek().key);
    }

    @Test
    public void testHeapFunctionality_removeMin() {
        testHeap = new Heap<Integer, String>(Collections.reverseOrder(Integer::compare));
        for (int i = 0; i < inputValuesArrayAdd.length; i++) {
            testHeap.add(inputVariedWeightsArray[i], inputValuesArrayAdd[i]);
        }
        assertEquals(testHeap.toArray().size(), 6);
        for (int i = 0; i < testHeap.size(); i++) {
            assertEquals(inputValuesArrayOrganizedMin[i], testHeap.poll().value);
        }
    }

    @Test
    public void testHeapFunctionality_removeMax() {
        testHeap = new Heap<Integer, String>(Integer::compare);
        for (int i = 0; i < inputValuesArrayAdd.length; i++) {
            testHeap.add(inputVariedWeightsArray[i], inputValuesArrayAdd[i]);
        }
        assertEquals(testHeap.toArray().size(), 6);
        for (int i = testHeap.size() - 1; i < 0; i++) {
            assertEquals(inputValuesArrayOrganizedMin[i], testHeap.poll().value);
        }
    }
}
