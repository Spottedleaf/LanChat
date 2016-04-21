package redcardop.searchndstroy.illegalchat.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import redcardop.searchndstroy.illegalchat.io.stream.NetworkSerialOutputStream;

public class AbstractChannel {

	protected final ConcurrentLinkedQueue<Packet>	packetQueue	= new ConcurrentLinkedQueue<>();
	protected final Socket							connection;
	protected volatile boolean						run			= true;

	protected final OutputThread					packetWriter;
	protected final InputThread						packetReader;

	protected AbstractChannel(final Socket socket) {
		connection = socket;
		try {
			packetWriter = new OutputThread();
			packetReader = new InputThread();
		} catch (final IOException ex) {
			throw new InternalError(ex);
		}
		packetReader.start();
		packetWriter.start();
	}

	public void sendPacket(final Packet packet) {
		packetQueue.add(packet);
	}

	protected final class InputThread extends Thread {

		private final DataInputStream in;

		public InputThread() throws IOException {
			in = new DataInputStream(new BufferedInputStream(connection.getInputStream(), 8192));
		}

		@Override
		public void run() {
			try {
				final Object[] input = new Object[] { in };
				while (run) {

				}
			} finally {
				try {
					connection.close();
				} catch (final IOException ignore) {}
			}
		}

	}

	protected final class OutputThread extends Thread {

		private final NetworkSerialOutputStream out;

		public OutputThread() throws IOException {
			out = new NetworkSerialOutputStream(connection.getOutputStream(), 8192);
		}

		@Override
		public void run() {
			try {
				while (run) {
					Packet packet;
					while ((packet = packetQueue.poll()) == null) {
						try {
							Thread.sleep(2);
						} catch (final InterruptedException ex) {
							ex.printStackTrace();
						}
					}
					try {
						packet.writeTo(out);
						out.flush();
					} catch (final IOException ex) {
						// log event and disconnect client
						run = false;
					}
				}
			} finally {
				try {
					connection.close();
				} catch (final IOException ignore) {}
			}
		}
	}
}