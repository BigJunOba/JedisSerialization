package bigjun.iplab.jedisConnectTest;

import redis.clients.jedis.Jedis;

public class JedisConnectTest {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Jedis jedis = new Jedis("192.168.131.130");
		jedis.set("JedisConnectTest", "pass");
		String getResult = jedis.get("JedisConnectTest");
		System.out.println(getResult);
		
	}
}
