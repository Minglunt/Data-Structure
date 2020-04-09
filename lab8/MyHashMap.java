import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private int count = 0;
    private int buckets;
    private double loadFactor;
    private Set<K> keys = new HashSet<>();

    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        buckets = initialSize;
        this.loadFactor = loadFactor;
        hashMap = new ArrayList<>(buckets);
        hashMap.addAll (Collections.nCopies(buckets, null));
    }

    @Override
    public void clear() {
        count = 0;
        hashMap = new ArrayList<>(buckets);
        keys = new HashSet<>();
    }

    @Override
    public boolean containsKey(K key) {
        return this.keys.contains(key);
    }

    @Override
    public V get(K key) {
        Entry e = find(key);
        if (e == null) {
            return null;
        } else {
            return e.value;
        }
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void put(K key, V value) {
        int hCode = hash(key);

        if (!containsKey(key)){
            keys.add(key);
            count += 1;
            LinkedList<Entry> nBucket = hashMap.get(hCode);

            if (nBucket == null) {
                LinkedList<Entry> l = new LinkedList<>();
                l.add(new Entry(key, value));
                hashMap.set(hCode, l);
            }
            else {
                nBucket.add(new Entry(key, value));
            }
        }
        else {
            Entry e = find(key);
            e.value = value;
        }

        double factor = count/buckets;
        if (factor >= loadFactor) {
            resize();
        }
    }

    private int hash(K key) {
         return (key.hashCode() & 0x7FFFFFFF) % buckets;
    }

    private void resize() {
        MyHashMap newmap = new MyHashMap(2*buckets);
        for (K key : keys) {
            this.get(key);
            newmap.put(key, this.get(key));
        }
        this.buckets = newmap.buckets;
        this.loadFactor = newmap.loadFactor;
        this.hashMap = newmap.hashMap;
    }

    private Entry find(K key) {
        int hCode = hash(key);
        if (hCode >= hashMap.size()) {
            return null;
        }
        LinkedList<Entry> ll = hashMap.get(hCode);

        int i = 0;
        Entry e;
        while (ll!=null && i < ll.size()) {
            e = ll.get(i);
            if (e.key.equals(key)) {
                return e;
            }
            i += 1;
        }
        return null;
    }

    private class Entry{
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList<LinkedList<Entry>> hashMap;

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public Iterator<K> iterator(){
        return new MapIterator();
    }

    private class MapIterator implements Iterator<K> {

        int index;
        ArrayList<K> keyList;


        public MapIterator() {
            index = 0;
            keyList = new ArrayList<>(keys);
        }

        @Override
        public boolean hasNext() {
            return index + 1 < count;
        }

        @Override
        public K next() {
            index += 1;
            return keyList.get(index);
        }
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
