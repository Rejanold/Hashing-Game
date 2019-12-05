/**
 * Used to group hash keys and values together
 */
public class HashNode<K, V> {
	private K key; 
	private V value;  // Value for this node
	// Constructors
	public HashNode(K k, V v){ 
	  	key = k;
		value = v;  
	}
	     
	public HashNode(){
		key = null;
		value = null; 
	}
	/** @return key
	*/
	public K getKey() { 
	 	return key; 
	}  
	/**@return value
	*/	
	public V getValue() {
		return value; 
	}
	/**@return the string representation of the value
	*/
	public String toString(){
		return value.toString();
	}
}
