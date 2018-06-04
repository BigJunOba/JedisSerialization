package bigjun.iplab.jdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import redis.clients.jedis.Jedis;

public class JDKSerializetionUtil {

	public static void main(String[] args) {
		Jedis jedis = null;
		try {
			jedis = new Jedis("192.168.131.130");

			Club club = new Club();

			club.setId(1);
			club.setName("AC");
			club.setInfo("米兰");
			club.setCreateDate(new Date());
			club.setRank(2);

			jedis.set("JDK".getBytes(), serialize(club));
			byte[] getByte = jedis.get("JDK".getBytes());
			Object getObject = unserizlize(getByte);
			if (getObject instanceof Club) {
				System.out.println(getObject);
				System.out.println(((Club) getObject).getId());
				System.out.println(((Club) getObject).getName());
				System.out.println(((Club) getObject).getInfo());
				System.out.println(((Club) getObject).getCreateDate());
				System.out.println(((Club) getObject).getRank());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	private static byte[] serialize(Object object) {
		ObjectOutputStream objectOutputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(object);
			byte[] getByte = byteArrayOutputStream.toByteArray();
			return getByte;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Object unserizlize(byte[] binaryByte) {
		ObjectInputStream objectInputStream = null;
		ByteArrayInputStream byteArrayInputStream = null;
		byteArrayInputStream = new ByteArrayInputStream(binaryByte);
		try {
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			Object obj = objectInputStream.readObject();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
