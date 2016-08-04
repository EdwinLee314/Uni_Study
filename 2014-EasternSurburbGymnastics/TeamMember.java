import java.util.*;

public class TeamMember implements CompositeKey{

	private Gymnast gymnast;
	private Team team;

	public TeamMember(Gymnast gymnast, Team team){
		this.gymnast = gymnast;
		this.team = team;
	}

	public Team getTeam(){
		return team;
	}

	public Gymnast getGymnast(){
		return gymnast;
	}

	public String toString(){
		return "TeamMember[gymnast:" + gymnast.getId() + " " + gymnast.getName() +
				", team:" + team + "]";
	}
	
	public ArrayList<String> getKey(){
		ArrayList<String> key = new ArrayList<String>();
		key.add(gymnast.getId());
		Club club = team.getClub();
		key.add(club.getId());
		Competition competition = team.getCompetition();
		Meet meet = competition.getMeet();
		String meetId = meet.getId();
		key.add(meetId);
		Division division = competition.getDivision();
		String divisionCode = division.getCode();
		key.add(divisionCode);
		return key;
	}
}