package bigjun.iplab.protostuff;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * 序列化和反序列化工具
 */
public class ProtostuffSerializer {
	private Schema<Club> schema = RuntimeSchema.createFrom(Club.class);

	// 序列化工具
	public byte[] serialize(final Club club) {
		final LinkedBuffer linkedBuffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try {
			return serializeInternal(club, schema, linkedBuffer);
		} catch (final Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		} finally {
			linkedBuffer.clear();
		}
	}

	// 实际序列化工具
	private <T> byte[] serializeInternal(final T source, final Schema<T> schema, final LinkedBuffer linkedBuffer) {
		return ProtostuffIOUtil.toByteArray(source, schema, linkedBuffer);
	}

	// 反序列化工具
	public Club deserialize(final byte[] bytes) {
		try {
			Club club = deserializeInternal(bytes, schema.newMessage(), schema);
			if (club != null) {
				return club;
			}
		} catch (final Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
		return null;
	}

	// 实际反序列化工具
	private <T> T deserializeInternal(final byte[] bytes, final T result, final Schema<T> schema) {
		ProtostuffIOUtil.mergeFrom(bytes, result, schema);
		return result;
	}
}
