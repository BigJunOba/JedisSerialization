package bigjun.iplab.json;

import java.util.Date;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import redis.clients.jedis.Jedis;

public class JsonlibSerializetionUtil {

	public static void main(String[] args) {
		Jedis jedis = null;
		try {
			jedis = new Jedis("192.168.131.130");

			Club club = new Club();

			club.setId(3);
			club.setName("CLE");
			club.setInfo("骑士");
			club.setCreateDate(new Date());
			club.setRank(2);

			jedis.set("JSON", serialize(club));
			String getByte = jedis.get("JSON");
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
		JSONObject jsonObject = JSONObject.fromObject(object);
		String getString = jsonObject.toString();
		return getString;
	}

	private static Object unserizlize(String jsonString) {
		new JSONObject();
		JSONObject jObject = JSONObject.fromObject(jsonString);
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "MM/dd/yyyy HH:mm:ss" }));
		Object jsonObject = JSONObject.toBean(jObject, Club.class);
		return jsonObject;
	}

}
