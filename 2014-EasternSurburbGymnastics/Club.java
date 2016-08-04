import java.util.*;

public class Club implements SimpleKey {

	private String id;
	private String name;
	private String phone;

	public Club(String id, String name, String phone){
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	public String getId(){
		return id;
	}

	public String getName(){
		return name;
	}
	public String toString(){
		return "Club[id:" + id +
				", name:" + name +
				", phone:" + phone + "]";
	}

	public String getKey(){
		return id;
	}

}