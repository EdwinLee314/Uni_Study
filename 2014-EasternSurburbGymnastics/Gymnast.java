public class Gymnast implements SimpleKey{

	private String id;
	private String name;
	private char gender;
	private int age;
	private Club club;

	public Gymnast(String id, String name, char gender, int age, Club club){
		this.id = id;
		this.name = name;
		this.age =age;
		this.gender = gender;
		this.club = club;
	}

	public String getId(){
		return id;
	}

	public String getName(){
		return name;
	}
		
	public Club getClub(){
		return club;
	}
	
	public char getGender(){
		return gender;
	}
	
	public int getAge(){
		return age;
	}
	
	public String toString(){
		String clubId = club.getId();
		return "Gymnast[ID:" + id +
				", name:" + name +
				", gender:" + gender +
				", age:" + age + 
				", belongClub:" + clubId +"]";
	}

	public String getKey(){
		return id;
	}

}