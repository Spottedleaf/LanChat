package redcardop.searchndstroy.illegalchat.server;

import java.awt.EventQueue;
import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicInteger;

import redcardop.searchndstroy.illegalchat.gui.ServerGUI;

public class ServerMain {

	private final AtomicInteger	nextId	= new AtomicInteger();
	private final ServerSocket	server;

	public ServerMain() {
		server = null;
	}

	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI window = new ServerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}

	public int nextUniqueId() {
		return nextId.incrementAndGet();
	}

}
