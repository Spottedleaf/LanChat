package redcardop.searchndstroy.illegalchat.gui;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Font;

public class ServerGUI {

	public JFrame frame;
	@SuppressWarnings("rawtypes")
	public JList list;
	public JTextArea chat;
	public DefaultListModel listModel;
	/**
	 * Create the application.
	 */
	public ServerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		chat = new JTextArea();
		chat.setEditable(false);
		chat.setText("Test");
		frame.getContentPane().add(chat, BorderLayout.CENTER);
		
		listModel = new DefaultListModel();
		listModel.addElement("test1");
		listModel.addElement("test2");
		listModel.addElement("test3");
		listModel.addElement("===");
		listModel.addElement("   ");
		list = new JList(listModel);
		list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListHandler());
		list.setSelectedIndex(listModel.size()-1);
		frame.getContentPane().add(list, BorderLayout.EAST);
	}
	
	public void addChat(String string){
		chat.append("\n" + string);
	}
	
	public void addClientToList(String client){
		listModel.addElement(client);
	}
	
	public void removeClientFromList(String client){
		for(int i = 0; i < listModel.size(); i++){
			if(listModel.getElementAt(i).equals(client)){
				listModel.remove(i);
				break;
			}
		}
	}
	
	class ListHandler implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(list.getSelectedIndex() != -1 && !listModel.get(list.getSelectedIndex()).equals("   ")){
				//Server Operations
				if(listModel.get(list.getSelectedIndex()).equals("===")){
					Object[] options = { "Stop", "Set Password" };
					int n = JOptionPane.showOptionDialog(frame, "What server operation would like to execute", "Server Operations",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(n == 0){
						//STOP
					}else{
						//SET PASSWORD
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									ServerSetPasswordGUI window = new ServerSetPasswordGUI();
									window.frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						if(listModel.size() > 0)
							list.setSelectedIndex(listModel.size()-1);
					}
				}
				//If Client
				else{
					Object[] options = { "Mute", "Kick", "Ban" };
					int n = JOptionPane.showOptionDialog(frame, "What would you like to to do to: " + listModel.get(list.getSelectedIndex()), "Client Control Options",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(n == 0){
						//MUTE
						if(listModel.size() > 0)
							list.setSelectedIndex(listModel.size()-1);
					}else if(n == 1){
						//KICK
						addChat(listModel.get(list.getSelectedIndex()) + " has been kicked");
						listModel.remove(list.getSelectedIndex());
						if(listModel.size() > 0)
							list.setSelectedIndex(listModel.size()-1);
					}else{
						//BAN
						if(listModel.size() > 0)
							list.setSelectedIndex(listModel.size()-1);
					}
				}
			}
		}
	}
	
}
