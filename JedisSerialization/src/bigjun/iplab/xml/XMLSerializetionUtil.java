package bigjun.iplab.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import redis.clients.jedis.Jedis;

public class XMLSerializetionUtil {

	public static void main(String[] args) {
		Jedis jedis = null;
		try {
			jedis = new Jedis("192.168.131.130");

			Club club = new Club();

			club.setId(2);
			club.setName("RM");
			club.setInfo("皇马");
			club.setCreateDate(new Date());
			club.setRank(1);

			jedis.set("XML", serialize(club));
			String getString = jedis.get("XML");
			Object getObject = unserizlize(Club.class, getString);
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
		StringWriter stringWriter = null;
		try {
			stringWriter = new StringWriter();
			JAXBContext jContext = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = jContext.createMarshaller();
			marshaller.marshal(object, stringWriter);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return stringWriter.toString();
	}

	private static Object unserizlize(Class<Club> clazz, String xmlString) {

		Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader stringReader = new StringReader(xmlString);
			xmlObject = unmarshaller.unmarshal(stringReader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlObject;
	}

}