import java.util.*;
public class Event implements CompositeKey{
	private Competition competion;
	private EventType eventType;
	private List<Judge> judges;

	public Event(Competition competion, EventType eventType){
		this.competion = competion;
		this.eventType = eventType;
		judges = new ArrayList<Judge>();
	}

	public String toString(){
		String judge = new String();
		for(Judge j: judges)
		{
			judge = judge + " " + j.getId();
		}
		return "Event[competition:" + competion +
				", eventType:" + eventType.getCode() +
				", judges:" + judge + "]";
	}
	
	public Competition getCompetition(){
		return competion;
	}
	
	public EventType getEventType(){
		return eventType;
	}
	
	public List<Judge> getJudges(){
		return judges;
	}
	
	public void addJudge (Judge judge){
		judges.add(judge);
	}
	
	public ArrayList<String> getKey(){
		ArrayList<String> key = new ArrayList<String>();
		Meet meet = competion.getMeet();
		String meetId = meet.getId();
		key.add(meetId);
		Division division = competion.getDivision();
		String divisionCode = division.getCode();
		key.add(divisionCode);
		key.add(eventType.getCode());
		return key;
	}
	
}