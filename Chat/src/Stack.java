/**
 * Stack class. This class can ask if a stack is empty, implement push, pop, peek, toString method
 */
public class Stack<T> {
	private DoublyLinkedList<T> stack;
	
	/**
	 * Constructor of StackLL
	 */
	public Stack() {
		stack = new DoublyLinkedList<T>();
	}
	
	/**
	 * Pushes an element onto the top of the stack.
	 */
	public void push( T data ) {
		stack.insertFirst(data);
	}
 
	/**
	 * Removes the top of the stack and returns it.
	 * @return the popped data
	 */
	public T pop() {
		T lastData = stack.getFirst();
		stack.deleteFirst();
		return lastData;
	}
 
	/** 
	 * Gets the element at the top of the stack without removing it.
	 * @return the peeked data
	 */
	public T peek() {
		return stack.getFirst();
	}
 
	/** 
	 * Tests if the stack is empty.
	 * @return true if the stack is empty
	 */
	public boolean isEmpty() {
		if(stack.isEmpty()) {
			return true;
		}
		
		return false;
	}
 
	/** 
	 * Returns a String representation of the stack.
	 * @return stack as String
	 */
	public String toString() {
		DoublyLinkedListNode<T> current = stack.getLastNode();
		String str = "";
		while(current != null) {
			if(current.getPrev() != null) {
				str += (current.getData()).toString() + "\n";
			}
			else {
				System.out.println("xxxxx");
				str += (current.getData()).toString();
			}
			current = current.getPrev();
		}
		return str;
	  
	}

	/**
	 * Get the size of the stack
	 * @return the size 
	 */
	public int size() {
		return stack.size();
	}
}

