import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


@SuppressWarnings("serial")
public class ServerChat extends ChatWindow{

	private ServerSocket server;
	private PrintWriter writer;
	private String text = "";
	
	public ServerChat() {
		super();
		setup();
	}

	public static void main(String[] args) {
		new ServerChat();

	}

	public void setup() {
		try {
			server = new ServerSocket(8322);
			System.out.println("successfully initiated");
			setConnection();

		} catch (IOException e) {
			System.out.println("Errors");
		}

	}

	public void setConnection() throws IOException  {
		Socket socket = null;
		InputStream is = null;
		InputStreamReader stream = null;
		BufferedReader reader = null;
		OutputStream os = null;
		writer = null;

		try {
			socket = server.accept();

			is = socket.getInputStream();
			stream = new InputStreamReader(is);
			reader = new BufferedReader(stream);
			
			os = socket.getOutputStream();
			writer = new PrintWriter(os, true);
			
			
			String message = null;
			while(true) {
				if((message = reader.readLine()) != null) {
					if(!message.equals("")) {
						if(message.equals("undo")) {
							String msg = MessageHistory.undoStack2.peek();
							if(msg.indexOf("Client: ") != -1) {
								MessageHistory.undoStack2();
								dialogueArea.setText(MessageHistory.printSecondUndoStack());
							}
							
						}
						else if(message.equals("redo")) {
							String msg = MessageHistory.redoStack2.peek();
							if(msg.indexOf("Client: ") != -1) {
								MessageHistory.redoStack2();
								dialogueArea.setText(MessageHistory.printSecondUndoStack());
							}
							
						}
						else {
							System.out.println("The client send the message: " + message);
							String str = "Client: " + message;
							MessageHistory.recordSecondStack(str);
							dialogueArea.setText(MessageHistory.printSecondUndoStack());
						}
						
					}
					
				}
				
			}

		}
		catch (IOException e) {
			System.exit(0);
		} 
		
		finally{
			writer.close();
			reader.close();
			
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
				String msg = "Server: " + text;
				MessageHistory.recordSecondStack(msg);
				String sm = MessageHistory.printSecondUndoStack();
				System.out.println(sm);
				dialogueArea.setText(sm);
				messageField.setText("");
			}
			
		}
		
		else if (e.getSource()== undoButton) {
			if(MessageHistory.canUndoStack2()) {
				String msg = MessageHistory.undoStack2.peek();
				if(msg.indexOf("Server: ") != -1) {
					MessageHistory.undoStack2();
					String str = MessageHistory.printSecondUndoStack();
					dialogueArea.setText(str);
					writer.println("undo");
					writer.flush();
				}
				
			}
		}
		else if (e.getSource() ==redoButton){
			if(MessageHistory.canRedoStack2()) {
				String msg = MessageHistory.redoStack2.peek();
				if(msg.indexOf("Server: ") != -1) {
					MessageHistory.redoStack2();
					String str = MessageHistory.printSecondUndoStack();
					dialogueArea.setText(str);
					writer.println("redo");
					writer.flush();
				}
			}
		}

	}


}