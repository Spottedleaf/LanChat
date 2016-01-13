package redcardop.searchndstroy.illegalchat.io.stream;

import java.io.IOException;
import java.io.OutputStream;

public final class NetworkSerialOutputStream extends OutputStream {

	private final OutputStream	out;
	private final byte[]		output;

	private int					index;

	public NetworkSerialOutputStream(final OutputStream out, final int bufferSize) {
		this.out = out;
		output = new byte[bufferSize];
	}

	public void writeCharSequence(final CharSequence sequence) {
		final int len = sequence.length();
		writeUnsignedVarInt(len);
	}

	public void writeCharSequences(final CharSequence sequence, final int off, final int len) {
		writeUnsignedVarInt(len);
	}

	public void writeByte(final byte value) {
		output[index++] = value;
	}

	public void writeBytes(final byte[] values) {
		final int len = values.length;
		writeUnsignedVarInt(len);
		System.arraycopy(values, 0, output, 0, len);
		index += len;
	}

	public void writeBytes(final byte[] values, final int off, final int len) {
		writeUnsignedVarInt(len);
		System.arraycopy(values, 0, output, off, len);
		index += len;
	}

	public void writeBoolean(final boolean value) {
		output[index++] = (byte) (value ? 1 : 0);
	}

	public void writeBooleans(final boolean[] values) {
		int len = values.length;
		writeUnsignedVarInt(len);
		// len % 8
		final int remainder = len & (8 - 1);
		if (remainder != 0) {
			len -= remainder;
			for (int i = 0; i < len;) {
				// 1 << x = 2^x
				byte value = (byte) (values[i++] ? 1 : 0);
				if (values[i++]) value |= (1 << 1);
				if (values[i++]) value |= (1 << 2);
				if (values[i++]) value |= (1 << 3);
				if (values[i++]) value |= (1 << 4);
				if (values[i++]) value |= (1 << 5);
				if (values[i++]) value |= (1 << 6);
				if (values[i++]) value |= (1 << 7);
				output[index++] = value;
			}
			byte value = 0;
			for (int i = 0, j = len; i < remainder; i++, j++) {
				if (values[j++]) value |= (1 << i);
			}
			output[index++] = value;
		} else {
			for (int i = 0; i < len;) {
				// 1 << x = 2^x
				byte value = (byte) (values[i++] ? 1 : 0);
				if (values[i++]) value |= (1 << 1);
				if (values[i++]) value |= (1 << 2);
				if (values[i++]) value |= (1 << 3);
				if (values[i++]) value |= (1 << 4);
				if (values[i++]) value |= (1 << 5);
				if (values[i++]) value |= (1 << 6);
				if (values[i++]) value |= (1 << 7);
				output[index++] = value;
			}
		}
	}

	public void writeBooleans(final boolean[] values, final int off, final int len) {

	}

	public void writeShort(final short value) {
		output[index++] = (byte) value;
		output[index++] = (byte) (value >>> 8);
	}

	public void writeShorts(final short[] values) {
		final int len = values.length;
		writeUnsignedVarInt(len);
		for (int i = 0; i < len; i++) {
			writeShort(values[i]);
		}
	}

	public void writeShorts(final short[] values, int off, final int len) {
		writeUnsignedVarInt(len);
		final int end = off + len;
		for (; off < end; off++) {
			writeShort(values[off]);
		}
	}

	public void writeVarShort(final short value) {
		writeUnsignedVarShort((short) ((value << 1) ^ (value >>> 15)));
	}

	public void writeVarShorts(final short[] values) {
		final int len = values.length;
		writeUnsignedVarInt(len);
		for (int i = 0; i < len; i++) {
			final short value = values[i];
			writeUnsignedVarShort((short) ((value << 1) ^ (value >>> 15)));
		}
	}

	public void writeVarShorts(final short[] values, int off, final int len) {
		writeUnsignedVarInt(len);
		final int end = off + len;
		for (; off < end; off++) {
			final short value = values[off];
			writeUnsignedVarShort((short) ((value << 1) ^ (value >>> 15)));
		}
	}

	public void writeUnsignedVarShort(short value) {
		while ((value & 0x7f) != value) {
			output[index++] = (byte) (value | 128);
			value >>>= 7;
		}
		output[index++] = (byte) value;
	}

	public void writeUnsignedVarShorts(final short[] values) {
		final int len = values.length;
		writeUnsignedVarInt(len);
		for (int i = 0; i < len; i++) {
			writeUnsignedVarShort(values[i]);
		}
	}

	public void writeUnsignedVarShorts(final short[] values, int off, final int len) {
		writeUnsignedVarInt(len);
		final int end = off + len;
		for (; off < end; off++) {
			writeUnsignedVarShort(values[off]);
		}
	}

	public void writeChar(final char value) {
		output[index++] = (byte) value;
		output[index++] = (byte) (value >>> 8);
	}

	public void writeChars(final char[] values) {
		final int len = values.length;
		writeUnsignedVarInt(len);

	}

	public void writeChars(final char[] values, final int off, final int len) {
		// TODO Auto-generated method stub

	}

	public void writeVarChar(final char value) {
		// TODO Auto-generated method stub

	}

	public void writeVarChars(final char[] values) {
		// TODO Auto-generated method stub

	}

	public void writeVarChars(final char[] values, final int off, final int len) {
		// TODO Auto-generated method stub

	}

	public void writeInt(final int value) {
		// TODO Auto-generated method stub

	}

	public void writeInts(final int[] values) {
		// TODO Auto-generated method stub

	}

	public void writeInts(final int[] values, final int off, final int len) {
		// TODO Auto-generated method stub

	}

	public void writeVarInt(final int value) {
		// TODO Auto-generated method stub

	}

	public void writeVarInts(final int[] values) {
		// TODO Auto-generated method stub

	}

	public void writeVarInts(final int[] values, final int off, final int len) {
		// TODO Auto-generated method stub

	}

	public void writeUnsignedVarInt(final int value) {
		// TODO Auto-generated method stub

	}

	public void writeUnsignedVarInts(final int[] values) {
		// TODO Auto-generated method stub

	}

	public void writeUnsignedVarInts(final int[] values, final int off, final int len) {
		// TODO Auto-generated method stub

	}

	public void writeLong(final long value) {
		// TODO Auto-generated method stub

	}

	public void writeLongs(final long[] values) {
		// TODO Auto-generated method stub

	}

	public void writeLongs(final long[] values, final int off, final int len) {
		// TODO Auto-generated method stub

	}

	public void writeVarLong(final long value) {
		// TODO Auto-generated method stub

	}

	public void writeVarLongs(final long[] values) {
		// TODO Auto-generated method stub

	}

	public void writeVarLongs(final long[] values, final int off, final int len) {
		// TODO Auto-generated method stub

	}

	public void writeUnsignedVarLong(final long value) {
		// TODO Auto-generated method stub

	}

	public void writeUnsignedVarLongs(final long[] values) {
		// TODO Auto-generated method stub

	}

	public void writeUnsignedVarLongs(final long[] values, final int off, final int len) {
		// TODO Auto-generated method stub

	}

	public void writeFloat(final float value) {
		// TODO Auto-generated method stub

	}

	public void writeFloats(final float[] values) {
		// TODO Auto-generated method stub

	}

	public void writeFloats(final float[] values, final int off, final int len) {
		// TODO Auto-generated method stub

	}

	public void writeDouble(final double value) {
		// TODO Auto-generated method stub

	}

	public void writeDoubles(final double[] values) {
		// TODO Auto-generated method stub

	}

	public void writeDoubles(final double[] values, final int off, final int len) {

	}

	@Override
	public void flush() throws IOException {
		if (index == -1) { throw new IOException("Stream is closed!"); }
		if (index == 0) return; // Nothing to write!
		// write length as unsigned varint
		// TODO: encrypt
		int index = this.index;
		while (index > 127) {
			out.write(index | 128);
			index >>>= 7;
		}
		out.write(index);
		out.write(output, 0, this.index);
		out.flush();
		this.index = 0;
	}

	@Override
	public void close() throws IOException {
		if (index == -1) { throw new IOException("Stream is already closed!"); }
		flush();
		index = -1;
	}

	@Override
	public void write(final int b) {
		output[index++] = (byte) b;
	}

	@Override
	public void write(final byte[] b) {
		writeBytes(b);
	}

	@Override
	public void write(final byte[] b, final int off, final int len) {
		writeBytes(b, off, len);
	}

	public int length() {
		return index;
	}

	public int capacity() {
		return output.length;
	}

}