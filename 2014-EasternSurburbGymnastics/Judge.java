import java.util.*;

public class Judge implements SimpleKey{

	private String id;
	private String name;
	private String phone;
	private List<EventType> eventTypes;

	public Judge(String id, String name, String phone,List<EventType> eventTypes){
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.eventTypes = eventTypes;
	}

	public String getId(){
		return id;
	}

	public String toString(){
		String es = new String();
		for(EventType e: eventTypes)
		{
			es = es + " " + e.getCode();
		}
		return "Judge[id:" + id +
				", name:" + name +
				", phone:" + phone +
				", eventTypes:" + es + "]";
	}

	public String getKey(){
		return id;
	}

}