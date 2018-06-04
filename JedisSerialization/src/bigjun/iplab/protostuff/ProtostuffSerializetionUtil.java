package bigjun.iplab.protostuff;

import java.util.Date;

import redis.clients.jedis.Jedis;

public class ProtostuffSerializetionUtil {

	public static void main(String[] args) {
		Jedis jedis = null;
		try {
			// 生成Jedis对象
			jedis = new Jedis("192.168.131.130");
			// 生成序列化和反序列化工具类对象
			ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();
			// 定义实体对象
			Club club = new Club(5, "RNG", "皇族", new Date(), 1);
			
			// 序列化
			byte[] clubBytes = protostuffSerializer.serialize(club);
			// 将club对象写入Redis
			jedis.set("Protostuff".getBytes(), clubBytes);
			
			// 从Redis中读取表示club对象的字符数组
			byte[] getBytes = jedis.get("Protostuff".getBytes());
			// 反序列化
			Club getClubObject = protostuffSerializer.deserialize(getBytes); 
			
			if (getClubObject instanceof Club) {
				System.out.println(getClubObject);
				System.out.println(((Club) getClubObject).getId());
				System.out.println(((Club) getClubObject).getName());
				System.out.println(((Club) getClubObject).getInfo());
				System.out.println(((Club) getClubObject).getCreateDate());
				System.out.println(((Club) getClubObject).getRank());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
}
