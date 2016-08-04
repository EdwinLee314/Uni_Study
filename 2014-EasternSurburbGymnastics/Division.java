import java.util.*;

public class Division implements SimpleKey{
	private String code;
	private String name;
	private char gender;
	private int lowerAgeLimit;
	private int upperAgeLimit;

	public Division(String code, String name, char gender, int low, int up){
		this.code = code;
		this.name = name;
		this.gender = gender;
		lowerAgeLimit = low;
		upperAgeLimit = up;
	}

	public String getCode(){
		return code;
	}
	
	public String getName(){
		return name;
	}
	
	public char getGender(){
		return gender;
	}
	
	public int getLowerAgeLimit(){
		return lowerAgeLimit;
	}
	
	public int getUpperAgeLimit(){
		return upperAgeLimit;
	}

	public String toString(){
		return "Division[code:" + code +
				", name:" + name +
				", gender:" + gender +
				", lowerAgeLimit:" + lowerAgeLimit +
				", upperAgeLimit:" + upperAgeLimit	+ "]";
	}

	public String getKey(){
		return code;
	}

}