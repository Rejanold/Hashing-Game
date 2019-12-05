import java.lang.*;
import java.util.*;

/**
 * This class creates a quelist of linked nodes. First in first out.
 * @param <E> the type of the queuelist
 */
public class QueueList<E> implements Queue<E> {
	private Node<E> front; // Reference to front.
	private Node<E> rear;	//Reference to rear
	private int size = 0;// Size of queue

	/**
	 * Constructor creates a queue list with one item in it
	 * @param it the item in the queue list
	 */
	public QueueList(E it) {	
		front= rear = new Node<E>(it); // Create top that stores it
		size++; 
	}

	/**
	 * Constructor that creates an empty queuelist
	 */
	public QueueList() {	
		front = rear = null;//Create an empty front & rear
		size = 0; 
	
	}
	/**
	 * Returns whether the queuelist is full or not.
	 * @return true if it is empty, false if not empty
	 */
	public boolean isEmpty(){
		return size == 0;	
	}
	
	
	/** Returns the object at the front of the queue without removing it
		@return the object at the front of the queue
		@throws NoSuchElementException
	*/		
	public E front(){
		if(isEmpty()){
			System.out.println("Queue is Empty");
			throw new NoSuchElementException();
		}
		else{
			return front.getElement();
		}
	}
	
	/** Returns the object at the front of the queue and removes it
		so stack is one smaller
		@return the object at the front of the queue
		@throws NoSuchElementException
	*/
	public E dequeue(){
		if(isEmpty()){
			System.out.println("Queue is Empty");
			throw new NoSuchElementException();
		}
		else{
			E frontElement = front.getElement();
			front = front.getNext();
			size--;
			return frontElement;
		}
	}
	
	
	/** Pushes an item onto the rear of the queue
		@param it The object to be inserted at the rear
	*/
	public void enqueue(E it){
		if(size == 0){
			front= rear = new Node<E>(it);
		}
		else{
			Node<E> node = new Node<E>(it);
			rear.setNext(node);
			rear = node;
		}
		size++;
	}
	
	
	/** Dumps the queue - clears it of its contents
	*/
	public void clear(){
		front = rear = null;//Create an empty front & rear
		size = 0;
	}
	
	
	/** Returns the number of elements in the queue
		@return size - the number of elements on the queue
	*/
	public int size(){
		return size;
	}
}


