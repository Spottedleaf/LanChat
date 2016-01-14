package redcardop.searchndstroy.illegalchat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import redcardop.searchndstroy.illegalchat.client.ClientMain;
import redcardop.searchndstroy.illegalchat.server.ServerMain;

public final class MainStarterMenu {

	public static void main(final String[] args)
			throws Throwable, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// test
		final JFrame starterMenuFrame = new JFrame();
		final Object[] options = { "Server", "Client" };
		final int n = JOptionPane.showOptionDialog(starterMenuFrame, "Launch as server or client?", "Launch Type",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (n == 0) {
			new ServerMain().start();
		} else{
			new ClientMain().start();
		}
	}

}
