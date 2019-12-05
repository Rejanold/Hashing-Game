
/**
 * Node class for Linked List
 */
public class Node<E> {
	private E element;        // Value for this node
	private Node<E> next;     // reference to next node in list
	
	/** Constructor
	* @param item the element to be stored in Node
	* @param nextVal the next Node that this is pointing to
	*/
	public Node(E item, Node<E> nextval)
	{ 
		element = item;  
		next = nextval; 
	}
	/** Constructor
	* @param item the element to be stored in Node
	*/
	public Node(E item){
		element = item;
		next = null;
	}
	//other constructors
	public Node(){
		element = null;
		next = null;
	}
	
 
	/**
	 *@return the Node that is next to this
	 */
	public Node<E> getNext() { 
		return next; 
	}  
	/**
	 * Sets this next to the given Node
	 * @param nextNal the Node that is to be set to this Node's next
	 */
	public void setNext(Node<E> nextval){
		next = nextval; 
	}     
	
	/** 
	 * returns the element in the Node
	 *@return element in the Node
	 */  
	public E getElement() { 
		return element; 
	}  
	
	/**
	 * sets the element stored in Node to the element given
	 *@param item the element to be stored in Node.
	 */
	public E setElement(E item) {
		return element = item; 
	}
}
