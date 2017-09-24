import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings("serial")
public class ClientChat extends ChatWindow{
	
	private Socket socket;
	private String text = "";
	private PrintWriter writer;

	public ClientChat() {
		super();
		setWindowTitle("Client Chat Window");
		setup();
	}
	

	public static void main(String[] args) {
		new ClientChat();
	}
	
	
	public void setup() {
		try {
			socket = new Socket(InetAddress.getLocalHost(), 8322);
			setConnection();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setConnection() {
		OutputStream os = null;
		writer = null;
		InputStream is = null;
		BufferedReader reader = null;
		try {

			//Send data to server
			os = socket.getOutputStream();
			writer = new PrintWriter(os, true);
			System.out.println("Network established");

			is = socket.getInputStream();
			InputStreamReader streamReader = new InputStreamReader(is);
			reader = new BufferedReader(streamReader);
			String message = null;
			while(true) {
				if((message = reader.readLine()) != null) {
					if(!message.equals("")) {
						if(message.equals("undo")) {
							String msg = MessageHistory.undoStack1.peek();
							if(msg.indexOf("Server: ") != -1) {
								MessageHistory.undoStack1();
								dialogueArea.setText(MessageHistory.printFirstUndoStack());
							}
							
						}
						else if(message.equals("redo")) {
							String msg = MessageHistory.redoStack1.peek();
							if(msg.indexOf("Server: ") != -1) {
								MessageHistory.redoStack1();
								dialogueArea.setText(MessageHistory.printFirstUndoStack());
							}
							
						}
						
						else {
							System.out.println("The server send the message: " + message);
							String str = "Server: " + message;
							MessageHistory.recordFirstStack(str);
							dialogueArea.setText(MessageHistory.printFirstUndoStack());
						}
						
					}
					
				}
				
				
			}
		} catch (IOException e) {
			System.exit(0);
		}
		finally{
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer.close();
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == messageField) {
			text = messageField.getText();
			if(!text.equals("")) {
				System.out.println(text);
				writer.println(text);
				writer.flush();
				String msg = "Client: " + text;
				MessageHistory.recordFirstStack(msg);
				
				String clientMsg = MessageHistory.printFirstUndoStack();
				dialogueArea.setText(clientMsg);
				messageField.setText("");
			}
		}
		
		else if (e.getSource()== undoButton) {
			if(MessageHistory.canUndoStack1()) {
				String msg = MessageHistory.undoStack1.peek();
				if(msg.indexOf("Client: ") != -1) {
					MessageHistory.undoStack1();
					String str = MessageHistory.printFirstUndoStack();
					dialogueArea.setText(str);
					writer.println("undo");
					writer.flush();
				}
				
			}
		}
		else if (e.getSource() ==redoButton){
			if(MessageHistory.canRedoStack1()) {
				String msg = MessageHistory.redoStack1.peek();
				if(msg.indexOf("Client: ") != -1) {
					MessageHistory.redoStack1();
					String str = MessageHistory.printFirstUndoStack();
					dialogueArea.setText(str);
					writer.println("redo");
					writer.flush();
				}
			}
		}

	}

}