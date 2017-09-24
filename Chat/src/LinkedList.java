/**
 * This class manages the activities we made to the linked list
 * @author Xijie Guo
 *
 */
public class LinkedList<T> {
	
	//Represents the first node in the linked list
	protected LinkedListNode<T> head;
	
	/**
	 * Constructor of LinkedList
	 */
	public LinkedList() {
		//First set the first head to null
		this.head = null;
	}
	
	/**
	 * Get data stored in head node of list.
	 */
	public T getFirst() {
		return head.getData();
	}
	 
	/**
	 * Get the head node of the list.
	 */
	public LinkedListNode<T> getFirstNode() {
		return head;
	}
	 
	/**
	 * Get data stored in last node of list.
	 */
	public T getLast() {
		//Create a new LinkedListNode<T> object and initiate it as head
		LinkedListNode<T> temp = head;
			
		//If the next node is not null
		while(temp.getNext()!= null) {
			//The new object becomes the next node
		    temp = temp.getNext();
		}
			
		//Return the data in the last node of list
		return temp.getData();
	}
	 
	/**
	 * Get the tail node of the list.
	 */
	public LinkedListNode<T> getLastNode() {
		
		//Create a new LinkedListNode<T> object and initiate it as head
		LinkedListNode<T> temp = head;
		
		//If the next node is not null
		while(temp.getNext() != null) {
			
			//The new object becomes the next node
			temp = temp.getNext();
		}
		
		//Return the last node
		return temp;
	}
	 
	/**
	 * Insert a new node with data at the head of the list.
	 * @param data for the data of the node
	 */
	public void insertFirst( T data ) {
		head = new LinkedListNode<T>(data, head);
	}
	 
	/**
	 * Insert a new node with data after currentNode
	 * @param currentNode for the current node
	 * @param data for the data of the node
	 */
	public void insertAfter( LinkedListNode<T> currentNode, T data ) {
	
		//Get the next node of the current node
		LinkedListNode<T> afterCurrent = currentNode.getNext();
		
		//Create a new node after current node
		afterCurrent = new LinkedListNode<T>(data, afterCurrent);
		
		//Make the current node point to the new node
		currentNode.setNext(afterCurrent);
		
	}
	 
	/**
	 * Insert a new node with data at the end of the list.
	 * @param data for the data of the node
	 */
	public void insertLast( T data ) {
		
		//Make sure the list is not empty
		if(!isEmpty()) {
			//Get the last node in the linked list
			LinkedListNode<T> last = getLastNode();
		    
			//Insert a new node after the last node
			insertAfter(last, data);
		}
	
	}
	 
	/**
	 * Remove the first node
	 */
	public void deleteFirst() {
		//Reset the head to the node after it
		head = head.getNext();
	}
	 
	/**
	 * Remove the last node
	 */
	public void deleteLast() {
		
		//Initialize current node as head
		LinkedListNode<T> current = head;
	    
		//If the node behind the current does not exist, set the head to null
	    if(current.getNext() == null) {
			head = null;
		}
			
		else {
			//Make sure the last node we are looking for is not null
			while(current.getNext().getNext() != null) {
	            
			//Change the current node to the second last node
			current = current.getNext();
	        }
		    
		    //Set the last node to null to delete it
	        current.setNext(null);
		}
	}
			
	    
	/**
	 * Remove node following currentNode
	 * If no node exists (i.e., currentNode is the tail), do nothing
	 * @param currentNode for the current node in the list
	 */
	public void deleteNext( LinkedListNode<T> currentNode ) {
		
		//Get the node after current node
		LinkedListNode<T> afterCurrent = currentNode.getNext();
		
		//If it exists, set it the node after it to remove it
		if(afterCurrent != null) {
			afterCurrent = afterCurrent.getNext();
			currentNode.setNext(afterCurrent);
		}
	}
	 
	/**
	 * Return the number of nodes in this list.
	 */
	public int size() {
		
		//Initialize the number of nodes in the list to 0
		int num = 0;
		
		//Create a LinkedListNode<T> object cursor
		LinkedListNode<T> cursor;
		
		//Initialize cursor to head and check if the cursor is null, then update cursor the the next node in the list
		for(cursor = head; cursor != null; cursor = cursor.getNext()) {
			
			//If the cursor is not null, increase the number of nodes by 1
			num++;
		}
		
		//Return the number of nodes in the list
		return num;
	}
	 
	/**
	 * Check if list is empty.
	 * @return true if list contains no items.
	 */
	public boolean isEmpty() {
		
		//If head is null, then it's an empty list
		if(head == null) {
			return true;
		}
		
		return false;
	}
	 
	/**
	 * Return a String representation of the list.
	 */
	public String toString() {
		
		//Assign the LinkedListNode<T> object as head
		LinkedListNode<T> current = head;
		
		//Create the string of the list
		String str = "";
		
		//While the current node does exists
		while(current != null) {
			
			if(current.getNext() != null) {
				
				//Set the string of the list
				str += (current.getData()).toString() + "\n";
				
				//Update current node
				current = current.getNext();
			}

			else {
				
				//Set the string of the list 
				str += (current.getData()).toString();
				
				//Update current node
				current = current.getNext();
			}
			
		}
		//return the string of the list
		return str;
	}

}
