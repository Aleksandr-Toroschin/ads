package ru.toroschin.ads;

public class HashTableImpl<K, V> implements HashTable<K, V> {

    private final Item<K, V>[] data;
    private int size;

    static class Item<K, V> implements Entry<K, V> {
        private final K key;
        private V value;
        private Item<K, V> next;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item{" + "key=" + key + ", value=" + value + '}';
        }

        public Item<K, V> getNext() {
            return next;
        }

        public void setNext(Item<K, V> next) {
            this.next = next;
        }
    }

    public HashTableImpl(int initialCapacity) {
        this.data = new Item[initialCapacity * 2];
    }

    public HashTableImpl() {
        this(16);
    }

    @Override
    public boolean put(K key, V value) {
        if (size() == data.length) {
            return false;
        }

        int index = hashFunc(key);

        Item<K, V> item = data[index];
        if (item == null) {
            data[index] = new Item<>(key, value);
        } else {

            while (item.getNext() != null && !isKeysEquals(item, key)) {
                item = item.getNext();
            }
            if (isKeysEquals(item, key)) {
                item.setValue(value);
            } else {
                item.setNext(new Item<>(key, value));
            }
        }

        size++;

        return true;
    }

    private boolean isKeysEquals(Item<K, V> item, K key) {
        return (item.getKey() == null) ? (key == null) : item.getKey().equals(key);
    }

    private int hashFunc(K key) {
        return Math.abs(key.hashCode() % data.length);
    }

    @Override
    public V get(K key) {
        int index = indexOf(key);
        Item<K, V> item = data[index];
        while (item != null && !isKeysEquals(item, key)) {
            item = item.getNext();
        }

        return item == null ? null : item.getValue();
    }

    private int indexOf(K key) {
        int index = hashFunc(key);

        return index;
    }

    @Override
    public V remove(K key) {
        int index = indexOf(key);

        Item<K, V> parent = null;
        Item<K, V> removed = data[index];

        while (removed != null && !isKeysEquals(removed, key)) {
            parent = removed;
            removed = removed.getNext();
        }
        if (removed == null) {
            return null;
        } else if (parent == null) {
            data[index] = removed.getNext();
        } else {
            parent.setNext(removed.getNext());
        }

        return removed.getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("------------------------\n");
        for (int i = 0; i < data.length; i++) {
            sb.append(String.format("%s = [%s]", i, data[i]));
            Item<K, V> item = data[i];
            while (item != null) {
                item = item.getNext();
                sb.append(String.format(" -> [%s]", item));
            }
            sb.append("\n");
        }
        sb.append("------------------------\n");
        return sb.toString();
    }
}
