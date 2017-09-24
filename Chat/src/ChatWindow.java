import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
/**
 * Server Chat window
 * @author Xijie Guo
 *
 */
@SuppressWarnings("serial")
public abstract class ChatWindow extends JFrame implements ActionListener{
	
	protected JButton undoButton, redoButton;
    protected JTextArea dialogueArea;
	protected JTextField messageField;
	
	public ChatWindow() {
		createView();
	}
	
	private void createView() {
		this.setLayout(new BorderLayout());
		add(createDialogueArea(), BorderLayout.CENTER);
		add(createBottom(), BorderLayout.SOUTH);
		
		setSize(800, 600);
		setWindowTitle("Server Chat Window");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JButton createUndoButton() {
		JButton button = new JButton("Undo");
		button.addActionListener(this);
		return button;
	}
	
	private JButton createRedoButton() {
		JButton button = new JButton("Redo");
		button.addActionListener(this);
		return button;
	}
	
	private JPanel createBottom() {
		undoButton = createUndoButton();
		redoButton = createRedoButton();
		messageField = new JTextField(40);
		messageField.addActionListener(this);
		Border border = BorderFactory.createLineBorder(Color.CYAN);
		messageField.setBorder(border);
		JPanel panel = new JPanel();
		panel.add(messageField);
		panel.add(undoButton);
		panel.add(redoButton);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		return panel;
	}
	
	private JTextArea createDialogueArea() {
		dialogueArea = new JTextArea();
		dialogueArea.setLineWrap(true);
		dialogueArea.setWrapStyleWord(true);
		dialogueArea.setEditable(false);
		dialogueArea.setBackground(new Color(238, 252, 220));
		return dialogueArea;
	}
	
	public void setWindowTitle(String title) {
		setTitle(title);
	}

	@Override
	public abstract void actionPerformed(ActionEvent e);

}
