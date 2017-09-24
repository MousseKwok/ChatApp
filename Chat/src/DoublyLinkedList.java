/**
 * This class is a subclass of LinkedList class
 * @author Xijie Guo
 *
 * @param <T>
 */
public class DoublyLinkedList<T> extends LinkedList<T>{

	//Last node in the doubly linked list
	private DoublyLinkedListNode<T> tail;

	public DoublyLinkedList() {
		super();
		this.tail = null;
	}
	
	/**
	 * Get the data stored in the last node of the linked list
	 */
	@Override
	public T getLast() {
		return tail.getData();
	}
	
	/**
	 * Return the last node in the doubly linked list
	 */
	@Override
    public DoublyLinkedListNode<T> getLastNode() {
    	return tail;
	}
	
	/**
	 * Override insertFirst method 
	 */
	@Override
    public void insertFirst(T data) {
		DoublyLinkedListNode<T> node = new DoublyLinkedListNode<T>(data, null, null);
		//Set the head to be the inserted node and tail the same if the doubly linked list is empty
    	if(head == null) {
    		head = node;
    		tail = (DoublyLinkedListNode<T>) head;
    	}
    	//else place the node before the head and let them point to each other, and set the inserted node to be the new head
    	else {
    		((DoublyLinkedListNode<T>) head).setPrev(node);
    		node.setNext(head);
    		head = node;
    		
    	}
	}
	
	/**
	 * Override the insertAfter method 
	 */
	@Override
	public void insertAfter(LinkedListNode<T> currentNode, T data) { 
		//If the doubly linked list is empty, insertAfter is the same as insertFirst
		if(head == null) {
			insertFirst(data);
		}
		
		else {
			//If we want to insert a node after the last node in the list, it's the same as insertLast
			DoublyLinkedListNode<T> nextNode = (DoublyLinkedListNode<T>) currentNode.getNext();
			if(nextNode == null) {
				insertLast(data);
			}
			else {
				//Insert a node in the list, let it and the node before it point to each other
				DoublyLinkedListNode<T> insertNode = new DoublyLinkedListNode<T>(data, null, null);
				currentNode.setNext(insertNode);
				insertNode.setPrev((DoublyLinkedListNode<T>) currentNode);
				//Let the inserted node and the node after it point to each other
				insertNode.setNext(nextNode);
				nextNode.setPrev(insertNode);
			}
		}
	}
	
	/**
	 * Override the insertLast method
	 */
	@Override
	public void insertLast(T data) {
		//If the doubly linked list is empty, it's the same as insertFirst
		if(head == null) {
			insertFirst(data);
		} 
		else {
			//Insert a node after the last node in the list and let them point to each other
			DoublyLinkedListNode<T> currentNode = getLastNode();
			DoublyLinkedListNode<T> nextNode = new DoublyLinkedListNode<T>(data, null, null);
			currentNode.setNext(nextNode);
			nextNode.setPrev(currentNode);
			//Set the tail to the inserted node 
			tail = nextNode;
		}
	}
    
	/**
	 * Override the deleteFirst method
	 */
	@Override
    public void deleteFirst() {
		//If the list only has a head, then set the head to null and tail to head to delete it
    	if(head.getNext() == null) {
    		head = null;
    		tail = (DoublyLinkedListNode<T>) head;
    	}
    	
    	else {
    		((DoublyLinkedListNode<T>) head.getNext()).setPrev(null);
    		head = head.getNext();
    		
    	}
    }
    
	/**
	 * Override the deleteLast method 
	 */
	@Override
    public void deleteLast() {
    	DoublyLinkedListNode<T> currentNode = (DoublyLinkedListNode<T>) head;
    	//If there is only head in the list, deleteLast is the same as deleteFirst
        if(currentNode.getNext() == null) {
        	deleteFirst();
        }
        
        else {
        	//delete the last node in the list and let the second to last node to be the last node
        	DoublyLinkedListNode<T> secondToLastNode = tail.getPrev();
        	secondToLastNode.setNext(null);
        	tail = secondToLastNode;
        } 
    }
	
	/**
	 * Override the deleteNext method
	 */
	@Override
	public void deleteNext(LinkedListNode<T> currentNode) {
		DoublyLinkedListNode<T> node = (DoublyLinkedListNode<T>) currentNode.getNext();
		//Make sure the node we want to delete exists, avoid the null pointer exception
		if(node != null) {
			node = (DoublyLinkedListNode<T>) node.getNext();
			if(node !=null) {
				currentNode.setNext(node);
				node.setPrev((DoublyLinkedListNode<T>) currentNode);
			}
			else {
				//If the node we want to delete is the last node in the list, it's the same as deleteLast method
				deleteLast();
			}
		} 
		
	}
	
	/**
	 * Override isEmpty method
	 */
	@Override
	public boolean isEmpty() {
		if(head == tail && head == null) {
			return true;
		}
		return false;
	}
			
}
