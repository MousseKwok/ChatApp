/**
 * This class is the a subclass of LinkedListNode class
 * @author Xijie Guo
 *
 * @param <T>
 */
public class DoublyLinkedListNode<T> extends LinkedListNode<T>{
	
	//Previous node
	public DoublyLinkedListNode<T> prev;
	
	public DoublyLinkedListNode(T data, DoublyLinkedListNode<T> next, DoublyLinkedListNode<T> prev) {
		super(data, next);
		this.prev = prev;

	}
	
	/**
	 * Set the previous node
	 * @param node has been set to the previous node
	 */
	public void setPrev(DoublyLinkedListNode<T> node) {
		this.prev = node;
	}
	
	/**
	 * 
	 * @return the previous node
	 */
	public DoublyLinkedListNode<T> getPrev() {
		return prev;
	}
}
