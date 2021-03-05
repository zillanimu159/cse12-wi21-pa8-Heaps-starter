import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<K extends Comparable<? super K>, V> implements PriorityQueue<K, V> {
    public List<Entry<K, V>> entries;
    public Comparator<K> comparator;
    
   
    public Heap(Comparator<K> comparator) {
        // Left child always at 2i, right at 2i+1, parent at i/2
        entries = new ArrayList<Entry<K, V>>();
        // forces real entries to start at 1
        entries.add(null);
        this.comparator = comparator;
    }

    public void add(K k, V v) {
        entries.add(new Entry<K, V>(k, v));
        bubbleUp(entries.size() - 1);
    }

    public Entry<K, V> poll() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Entry<K, V> removedEntry = entries.get(1);
        swap(1, entries.size() - 1);
        entries.remove(entries.size() - 1);
        bubbleDown(1);
        return removedEntry;
    }

    public Entry<K, V> peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return entries.get(1);
    }

    public List<Entry<K, V>> toArray() {
        return entries.subList(1, entries.size());
    }

    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public int parent(int index) {
        return index / 2;
    }

    public int left(int index) {
        return 2 * index;
    }

    public int right(int index) {
        return (2 * index) + 1;
    }

    public void swap(int i1, int i2) {
        Entry<K, V> temp = entries.get(i2);
        entries.set(i2, entries.get(i1));
        entries.set(i1, temp);
    }

    public void bubbleUp(int index) {
        int parent = parent(index);
        if (existsAndGreater(index, parent)) {
            if (comparator.compare(entries.get(index).key, entries.get(parent).key) > 0) {
                swap(index, parent);
                bubbleUp(parent);
            }
        }
        return;
    }

    public void bubbleDown(int index) {
        int left = left(index);
        int right = right(index);
        int highestPriorityChildIndex;
        if (validateIndex(left)) {
            if (validateIndex(right)
                    && comparator.compare(entries.get(left).key, entries.get(right).key) < 0) {
                highestPriorityChildIndex = right;
            } else {
                highestPriorityChildIndex = left;
            }
        } else {
            return;
        }
        if (comparator.compare(entries.get(index).key,
                entries.get(highestPriorityChildIndex).key) < 0) {
            swap(index, highestPriorityChildIndex);
            bubbleDown(highestPriorityChildIndex);
        }
    }

    public boolean existsAndGreater(int index1, int index2) {
        if (!validateIndex(index1) || !validateIndex(index2)) {
            return false;
        }
        if (comparator.compare(entries.get(index1).key, entries.get(index2).key) > 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return entries.size() - 1;
    }

    public String toString() {
        return stringRecursive(1);
    }
    
    private String stringRecursive(int index) {
        String treeString = "\t" + entries.get(index);
        if (validateIndex(left(index))) {
            treeString += "\nChild Left: " + stringRecursive(left(index));
        }
        if (validateIndex(right(index))) {
            treeString += "\nChild Right: " + stringRecursive(right(index));
        }
        return treeString;

    }

    private boolean validateIndex(int index) {
        if (index < 1 || index > entries.size()-1) {
            return false;
        }
        return true;
    }
}

class Entry<K, V> {
    K key; // aka the _priority_
    V value;

    public Entry(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public String toString() {
        return key + ": " + value;
    }
}

