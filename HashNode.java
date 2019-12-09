/**
 * Author: Mason Waters
 * Date: 12/8/2019
 * Hashing Assignment
 * This is the HashCNode class
 * In collaboration with: Blake Furlano and Robert Hable
 */
public class HashNode<K, V> {
    private K key;
    private V value;  // Value for this node

    // Constructors
    public HashNode(K k, V v) {
        key = k;
        value = v;
    }

    //Another Constructor
    public HashNode() {
        key = null;
        value = null;
    }

    /**
     * @return key returns the key passed
     */
    public K getKey() {
        return key;
    }

    /**
     * @return value returns the calue passed
     */
    public V getValue() {
        return value;
    }

    /**
     * @return the string representation of the value
     */
    public String toString() {
        return value.toString();
    }
}