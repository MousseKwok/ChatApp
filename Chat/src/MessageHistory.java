import java.util.ArrayList;

/**
 * Manipulate the message history : recall message
 * @author Xijie Guo
 *
 */
public final class MessageHistory {
	
	public static Stack<String> undoStack1 = new Stack<String>();
	public static Stack<String> undoStack2 = new Stack<String>();
	public static Stack<String> redoStack1 = new Stack<String>();
	public static Stack<String> redoStack2 = new Stack<String>();
	public static ArrayList<String> historyList = new ArrayList<String>();
	
	public static void recordFirstStack(String message) {
		undoStack1.push(message);
	}
	
	public static void recordSecondStack(String message) {
		undoStack2.push(message);
	}
	public static void undoStack1() {
		String str = undoStack1.pop();
		redoStack1.push(str);
	}
	
	public static void undoStack2() {
		String str = undoStack2.pop();
		redoStack2.push(str);
	}
	
	public static boolean canUndoStack1() {
		return !undoStack1.isEmpty();
	}
	
	public static boolean canUndoStack2() {
		return !undoStack2.isEmpty();
	}

	public static int getUndoStack1Size() {
		return undoStack1.size();
	}
	
	public static int getUndoStack2Size() {
		return undoStack2.size();
	}

	public static void redoStack1() {
		String str = redoStack1.pop();
		undoStack1.push(str);
	}
	
	public static void redoStack2() {
		String str = redoStack2.pop();
		undoStack2.push(str);
	}
	
	public static boolean canRedoStack1() {
		return !redoStack1.isEmpty();
	}
	
	public static boolean canRedoStack2() {
		return !redoStack2.isEmpty();
	}
	
	public static String printFirstUndoStack() {
		return undoStack1.toString();
	}
	
	public static String printSecondUndoStack() {
		return undoStack2.toString();
	}
}