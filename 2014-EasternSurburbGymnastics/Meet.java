public class Meet implements SimpleKey{

	private String id;
	private String name;
	private String place;
	private int date;

	public Meet(String id, String name, String place, int date){
		this.id = id;
		this.name = name;
		this.date = date;
		this.place = place;
	}

	public String getId(){
		return id;
	}

	public String toString(){
		return "Meet[id:" + id +
				", name:" + name +
				", place:" + place +
				", date:" + date + "]";
	}

	public String getKey(){
		return id;
	}

}