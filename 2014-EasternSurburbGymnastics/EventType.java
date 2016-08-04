public class EventType implements SimpleKey {

	private String code;
	private String name;
	private boolean menEvent;
	private boolean womenEvent;

	public EventType(String code, String name, boolean men, boolean women){
		this.code = code;
		this.name = name;
		menEvent = men;
		womenEvent = women;
	}

	public String getCode(){
		return code;
	}

	public String getName(){
		return name;
	}
	
	public String toString(){
		return "EventType[code:" + code +
				", name:" + name +
				", menEvent:" + menEvent +
				", womenEvent:" + womenEvent + "]";
	}

	public String getKey(){
		return code;
	}

}