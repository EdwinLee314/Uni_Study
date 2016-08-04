import java.util.*;

public class Score implements CompositeKey{

	private TeamMember teamMember;
	private Event event;
	private double value;

	public Score(TeamMember teamMember, Event event, double value){
		this.teamMember = teamMember;
		this.event = event;
		this.value = value;
	}

	public Event getEvent(){
		return event;
	}
	
	public TeamMember getTeamMember(){
		return teamMember;
	}
	
	public double getValue(){
		return value;
	}
		
	public String toString(){
		return "Score[teamMember:" + teamMember +
				", event:" + event +
				", value:" + value + "]";
	}
	
	public ArrayList<String> getKey(){
		ArrayList<String> key = new ArrayList<String>();
		Gymnast gymnast = teamMember.getGymnast();
		key.add(gymnast.getId());
		Competition competition = event.getCompetition();
		Meet meet = competition.getMeet();
		String meetId = meet.getId();
		key.add(meetId);
		EventType eventType = event.getEventType();
		key.add(eventType.getCode());
		return key;
	}
}