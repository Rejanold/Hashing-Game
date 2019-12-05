import java.util.*;
import java.lang.*;

public class SLList<E> implements List<E>{
	private Node<E> head;//reference to the head
	private int size = 0;
	
	/**Constructor
	* @param item - the item we are adding to the head of the list
	*/
	public SLList(E item){
		head = new Node<E>(item);
		size++;
	}
	/**Default constructor
	*/
	public SLList(){
		head = null;
		size = 0;
	}
	
	/** Remove all contents from the list, so it is once again
	* empty. 
	*/
	public void clear(){
		head = null;
		size = 0;
	}
  
	
	
  /** Insert an element at the given location. 
	* allows you to insert after the tail
    * @param item The element to be inserted. 
	*/
    public void insert(int index, E item){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(index == 0){//want a new head
			addFirst(item);//helper method
		}
		else{
			Node<E> node = getNode(index - 1); //the node before the index
			addAfter(node, item); //helper method
		}
	}
	
	/**Helper method that makes a new head
	* @param item to insert into the new head*/
	private void addFirst(E item){
		head = new Node<E>(item, head);
		size++;
	}
    
	/**Helper method to add node after a given node
	* @param node to add after
	* @param item to add into node
	*/
	private void addAfter(Node<E> node, E item){
		node.setNext(new Node<E>(item, node.getNext()));
		size++;
	}
	
	/** helper method to get a node at a given index
	* @param index - the index of the node to retieve
	*/
	private Node<E> getNode(int index){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = head;
		for(int i =0; i < index; i++){
			node = node.getNext();
		}
		return node;
	}
  /** Append an element at the end of the list.
   *  @param item The element to be appended.
   */
  public void add(E item){
	  if(size == 0){//nothing in the list so we add a new head
		  addFirst(item);
	  }
	  else{
		  Node<E> node = getNode(size - 1);
		  addAfter(node, item);
	  }
  }
  
  
  /** 
  * Remove the  element at the given location.
  *@param index - index of node to remove
  */
  public void remove(int index){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(index == 0){//remove head
			removeFirst();
		}
		else{
			Node<E> node = getNode(index-1);
			removeAfter(node);//helper
		}
  }
  
  /**helper method to remove the head*/
  private void removeFirst(){
	  if(head != null){
		  head = head.getNext();
		  size--;
	  }
  }
  /**Helper to remove after a given node
  * @param node the node to remove after
  */
  private void removeAfter(Node<E> node){
	  Node<E> temp = node.getNext();
	  if(temp != null){
		  node.setNext(temp.getNext());
		  size--;
	  }
  }
  
  /** 
  * Get the element in the position to one step left. 
  * @return element in the node to the left of the node at the index, 
  * null if at the head. 
  */  
  public E prev(int index){
	  if(index < 0 || index > size){
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(index == 0){
			return null;
		}
		else{
			return getNode(index-1).getElement();
		}
  }
  
  
  /** Get the element in the position one step right. 
  * @return the element in the node to the right of 
  * the node at the index, null if at the end. 
  */
  public E next(int index){
	  if(index < 0 || index > size){
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(index == size-1){
			return null;
		}
		else{
			return getNode(index+1).getElement();
		}
  }
  
  
  /** @return The number of elements in the list. */
  public int length(){
	 return size;
		
  }
    
    
   /** Turn the contents of the Nodes to a string in order from head to end.
   * @return The String representation of the 
   * elements in the list from head to end. 
   */
   public String toString(){
	   Node<E> nodeRef = head;
	   String result = "";
	   while(nodeRef != null){
		   result = result + nodeRef.getElement().toString();
		   
		   if(nodeRef.getNext() != null){
			   result = result + " ==> ";
		   }
		   nodeRef = nodeRef.getNext();
	   }
	   return result;
   }
  
   /** Reverse the content of the list.
	* if list is A => B => C it becomes C => B => A
	*/
  public void reverse(){
	  Node<E> node = head;
	  if(node == null || node.getNext() == null){//empty or one node
		  return;
	  }
	  Node<E> prev = node.getNext();
	  Node<E> curr = prev.getNext();
	  prev.setNext(node);
	  node.setNext(null);
	  while(curr != null){
		  Node<E> next = curr.getNext();
		  curr.setNext(prev);
		  prev = curr;
		  curr = next;
	  }
	  head = prev;
  }
  
   
   /** @return The  element at given position. */
   public E getValue(int index){
	   if(index < 0 || index > size){
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = head;
		for(int i =0; i < index; i++){
			node = node.getNext();
		}
		return node.getElement();
   }
   
   
	/**
	* insert a list after given index
	* @param list the list to be inserted
	* @param index the index of where the list should go after
	*/
	public void insertList (SLList list, int index){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if(list.length() > 0){
			Node<E> toAddAfter = getNode(index);//the index we are adding our list after
			Node<E> addTailTo = toAddAfter.getNext();//the index that the adding list tail will add to
			Node<E> theHead = list.getHead(); //the head of the list we are adding
			Node<E> theTail = list.getLast();//the tail of the list we are adding
			
				toAddAfter.setNext(theHead);
				theTail.setNext(addTailTo);
				size += list.length();
			
			
			
		}
		
		
	}

	/** @return the head of the list*/
	public Node<E> getHead(){
		return head;
	}
	/** @return the last node in the list*/
	public Node<E> getLast(){
		return getNode(size-1);
	}


	
}