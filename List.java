

/** List ADT */
public interface List<E> {
	
  /** Remove all contents from the list, so it is once again
   * empty. 
   */
  public void clear();
  
	
	
  /** Insert an element at the given location. 
	* allows you to insert after the tail
    * @param item The element to be inserted. 
	*/
    public void insert(int index, E item);
  
    
  /** Append an element at the end of the list.
   *  @param item The element to be appended.
   */
  public void add(E item);
  
  
  /** 
  * Remove the  element at the given location.
  */
  public void remove(int index);
  
  
  
  /** 
  * Get the element in the position to one step left. 
  * @return element in the node to the left of the node at the index, 
  * null if at the head. 
  */  
  public E prev(int index);
  
  
  /** Get the element in the position one step right. 
  * @return the element in the node to the right of 
  * the node at the index, null if at the end. 
  */
  public E next(int index);
  
  
  /** @return The number of elements in the list. */
  public int length();
    
    
   /** Turn the contents of the Nodes to a string in order from head to end.
   * @return The String representation of the 
   * elements in the list from head to end. 
   */
   public String toString();
  
   /** Reverse the content of the list.
	* if list is A => B => C it becomes C => B => A
	*/
  public void reverse();
  
   
   /** @return The  element at given position. */
   public E getValue(int index);
   
 
}

