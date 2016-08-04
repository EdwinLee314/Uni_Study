import java.util.*;

public class Team implements CompositeKey{

	private Club club;
	private Competition competition;

	public Team(Club club, Competition competition){
		this.club = club;
		this.competition = competition;
	}


	public Club getClub(){
		return club;
	}

	public Competition getCompetition(){
		return competition;
	}
	
	public String toString(){
		return "Team[club:" + club.getId() + " " +club.getName() +
				", competition:" + competition + "]";
	}
	
	public ArrayList<String> getKey(){
		ArrayList<String> key = new ArrayList<String>();
		key.add(club.getId());
		Meet meet = competition.getMeet();
		String meetId = meet.getId();
		key.add(meetId);
		Division division = competition.getDivision();
		String divisionCode = division.getCode();
		key.add(divisionCode);
		return key;
	}

}