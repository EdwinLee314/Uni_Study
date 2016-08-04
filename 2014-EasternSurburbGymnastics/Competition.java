import java.util.*;

public class Competition implements CompositeKey{

	private Meet meet;
	private Division division;

	public Competition(Meet meet, Division division){
		this.meet = meet;
		this.division = division;
	}

	public Meet getMeet(){
		return meet;
	}

	public Division getDivision(){
		return division;
	}

	public String toString(){
		return "Competition[Meet:" + meet.getId() +
				", division:" + division.getCode() + "]";
	}
	
	public ArrayList<String> getKey(){
		ArrayList<String> key = new ArrayList<String>();
		key.add(meet.getId());
		key.add(division.getCode());
		return key;
	}
}