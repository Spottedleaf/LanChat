package redcardop.searchndstroy.illegalchat.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class ServerSetPasswordGUI {

	public JFrame frame;
	private JTextField textField;

	/**
	 * Create the application.
	 */
	public ServerSetPasswordGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		frame.getContentPane().add(chckbxNewCheckBox, BorderLayout.SOUTH);
	}

}
