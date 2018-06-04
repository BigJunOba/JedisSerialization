package bigjun.iplab.json;

import java.util.Date;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;

public class FastJsonSerializetionUtil {

	public static void main(String[] args) {
		Jedis jedis = null;
		try {
			jedis = new Jedis("192.168.131.130");

			Club club = new Club();

			club.setId(4);
			club.setName("GS");
			club.setInfo("勇士");
			club.setCreateDate(new Date());
			club.setRank(1);

			jedis.set("FastJson", serialize(club));
			String getByte = jedis.get("FastJson");
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

	private static String serialize(Object object) {
		String getString = JSON.toJSONString(object);
		return getString;
	}

	private static Object unserizlize(String jsonString) {
		Object jsonObject = JSON.parseObject(jsonString, Club.class);
		return jsonObject;
	}

}
