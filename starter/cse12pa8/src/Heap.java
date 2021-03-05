import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;
public class Heap<K extends Comparable<? super K>, V> /*implements PriorityQueue<K, V>*/{
    public List<Entry<K, V>> entries;
    public Comparator<K> comparator;
    
    static class Entry<K, V> {
        K key; // aka the _priority_
        V value;
        public Entry(K k, V v) { this.key = k; this.value = v; }
        public String toString() {
            return key + ": " + value;
        }
    }
    
    public Heap(Comparator<K> comparator) {
        this.comparator = comparator;
    }
}
