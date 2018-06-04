package bigjun.iplab.xml;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

// Xml文件中的根标识，必须要表明这个元素，可以让对象和Xml之间方便转换
@XmlRootElement(name = "Club") 
public class Club implements Serializable {
	
	/**
	 * 其实序列化的作用是能转化成Byte流，然后又能反序列化成原始的类。能
	 * 在网络进行传输，也可以保存在磁盘中，
	 * 有了SUID之后，那么如果序列化的类已经保存了在本地中，
	 * 中途你更改了类后，SUID变了，那么反序列化的时候就不会变成原始的类了，
	 * 还会抛异常，主要就是用于版本控制。
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String info;
	private Date createDate;
	private int rank;
	
	public Club() {
		
	}
	
	public Club(int id, String name, String info, Date createDate, int rank) {
		super();
		this.id = id;
		this.name = name;
		this.info = info;
		this.createDate = createDate;
		this.rank = rank;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
}
