package redcardop.searchndstroy.illegalchat.server;

import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerMain {

	private final AtomicInteger	nextId	= new AtomicInteger();
	private final ServerSocket	server;

	public ServerMain() {
		server = null;
	}

	public void start() {

	}

	public int nextUniqueId() {
		return nextId.incrementAndGet();
	}

}
