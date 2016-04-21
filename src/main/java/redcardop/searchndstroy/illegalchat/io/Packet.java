package redcardop.searchndstroy.illegalchat.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import redcardop.searchndstroy.illegalchat.io.stream.NetworkSerialOutputStream;

public abstract class Packet {

	private static final PacketConstructor[] packets = new PacketConstructor[255];

	public static final Packet nextPacket(final DataInputStream input) throws IOException {
		return nextPacket(new Object[] { input }, input.readByte());
	}

	public static final Packet nextPacket(final Object[] input, final byte id) {
		try {
			return packets[id & 0xFF].constructor.newInstance(input);
		} catch (final InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException ex) {
			throw new InternalError(ex);
		} catch (final NullPointerException ex) {
			throw new IllegalArgumentException("No packet with ID " + id + " exists!");
		}
	}

	public static final void register(final Class<Packet> packetClass, final byte id) {
		if (packets[id] != null) { throw new IllegalStateException("Packet with id " + id + " already exists!"); }
		packets[id] = new PacketConstructor(packetClass);
	}

	public abstract byte getId();

	public abstract void writeTo(final NetworkSerialOutputStream out);

	public abstract void onReadClient(final ClientChannel channel);

	public abstract void onReadServer(final ServerChannel channel);

	@Override
	public final String toString() {
		return toString(new StringBuilder()).toString();
	}

	// debug
	public abstract StringBuilder toString(final StringBuilder builder);

	static final class PacketConstructor {
		public final Constructor<Packet> constructor;

		public PacketConstructor(final Class<Packet> packetClass) {
			if (!Packet.class.isAssignableFrom(
					packetClass)) { throw new IllegalArgumentException("Specified class must extend Packet!"); }
			try {
				constructor = packetClass.getDeclaredConstructor(DataInputStream.class);
				constructor.setAccessible(true);
			} catch (NoSuchMethodException | SecurityException ex) {
				throw new IllegalArgumentException("Packet class must have a deserialization constructor!");
			}
		}
	}

}