import java.util.*;

public class GymnasticsScoringSystem {

	private List<Club> clubList;
	private List<Gymnast> gymnastList;
	private List<Division> divisionList;
	private List<EventType> eventTypeList;
	private List<Judge> judgeList;
	private List<Meet> meetList;
	private List<Competition> competitionList;
	private List<Event> eventList;
	private List<Team> teamList;
	private List<TeamMember> teamMemberList;
	private List<Score> scoreList;
	

	public GymnasticsScoringSystem(){
		clubList = new ArrayList <Club>();
		gymnastList = new ArrayList <Gymnast>();
		divisionList = new ArrayList<Division>();
		eventTypeList = new ArrayList <EventType>();
		judgeList = new ArrayList <Judge>();
		meetList = new ArrayList<Meet>();
		competitionList = new ArrayList<Competition>();
		eventList = new ArrayList<Event>();
		teamList = new ArrayList<Team>();
		teamMemberList = new ArrayList<TeamMember>();
		scoreList = new ArrayList<Score>();
	}
	
	public String toString(){
		String clubs = new String();
		for(Club c: clubList)
		{
			clubs = clubs + "\n\t" + c;
		}
		String gymnasts = new String();
		for(Gymnast g: gymnastList)
		{
			gymnasts = gymnasts + "\n\t" + g;
		}
		String divisions = new String();
		for(Division d: divisionList)
		{
			divisions = divisions + "\n\t" + d;
		}
		String eventTypes = new String();
		for(EventType e: eventTypeList)
		{
			eventTypes = eventTypes + "\n\t" + e;
		}
		String judges = new String();
		for(Judge j: judgeList)
		{
			judges = judges + "\n\t" + j;
		}
		String meets = new String();
		for(Meet m: meetList)
		{
			meets = meets + "\n\t" + m;
		}
		String competitions = new String();
		for(Competition c: competitionList)
		{
			competitions = competitions + "\n\t" + c;
		}
		String events = new String();
		for(Event e: eventList)
		{
			events = events + "\n\t" + e;
		}
		String teams = new String();
		for(Team t: teamList)
		{
			teams = teams + "\n\t" + t;
		}
		String teamMembers = new String();
		for(TeamMember tm: teamMemberList)
		{
			teamMembers = teamMembers + "\n\t" + tm;
		}
		String scores = new String();
		for(Score s: scoreList)
		{
			scores = scores + "\n\t" + s;
		}
		String desc = "GymnasticsScoringSystem[" 
			+ "\nClubList: " + clubs 
			+ "\nGymnastList: " + gymnasts 
			+ "\nDivisionList: " + divisions 
			+ "\nEventTypeList: " + eventTypes 
			+ "\nJudgeList: " + judges 
			+ "\nMeetList: " + meets 
			+ "\nCompetitionList: " + competitions 
			+ "\nEventList: " + events
			+ "\nTeamList: " + teams
			+ "\nTeamMemberList: " + teamMembers
			+ "\nScoreList: " + scores	+ "\n]";
		return desc;
	}
	
	//uc01
	//Add a club
	public void addClub(String clubId,String name, String phone) throws Exception {
		// pre1 id? is new
		Club club = (Club) Helper.search(clubList, clubId);
		boolean pre1 = (club == null);
		if(!pre1)
		{
			String msg = "ERROR: Club ID is not new!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//pre2 name? is new
		boolean pre2 = true;
		for(Club c: clubList)
		{
			if(c.getName().equals(name))
			{
				pre2 = false;
				break;
			}
		}
		if(!pre2)
		{
			String msg = "ERROR: Club name is not new!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//post
		club = new Club(clubId,name,phone);
		clubList.add(club);
	}

	//uc02
	//Add a gymnast
	public void addGymnast(String id, String name, char gender, int age, String clubId) throws Exception {
		//pre1 club exists
		Club club = (Club) Helper.search(clubList, clubId);
		boolean pre1 = (club != null);
		if(!pre1)
		{
			String msg = "ERROR: Club is not exists!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//pre2 id? is new
		Gymnast gymnast = (Gymnast) Helper.search(gymnastList, id);
		boolean pre2 = (gymnast == null);
		if(!pre2)
		{
			String msg = "ERROR: Gymnast id is not new!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//post
		gymnast = new Gymnast(id, name, gender, age, club);
		gymnastList.add(gymnast);
	}

	//uc03
	//Add a division
	public void addDivision(String code, String name, char gender, int lowerAgeLimit, int upperAgeLimit) throws Exception {
		//pre1 code? is new
		Division division = (Division) Helper.search(divisionList, code);
		boolean pre1 = (division == null);
		if(!pre1)
		{
			String msg = "ERROR: Division code is not new!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//pre2 name? is new
		boolean pre2 = true;
		for(Division d: divisionList)
		{
			if(d.getName().equals(name))
			{
				pre2 = false;
				break;
			}
		}
		if(!pre2)
		{
			String msg = "ERROR: Division name is not new!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//pre3 lowerAgeLimit? < upperAgeLimit?
		boolean pre3 = (lowerAgeLimit < upperAgeLimit);
		if(!pre3)
		{
			String msg = "ERROR: lower age limit must be smaller than upper age limit!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//post
		division = new Division(code,name,gender,lowerAgeLimit,upperAgeLimit);
		divisionList.add(division);
	}
	
	//UC04
	//Add an event type
	public void addEventType(String code, String name, boolean menEvent, boolean womenEvent) throws Exception {
		//pre1 code? is new
		EventType eventType = (EventType) Helper.search(eventTypeList, code);
		boolean pre1 = (eventType == null);
		if(!pre1)
		{
			String msg = "ERROR: event type code is not new!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//pre2 name? is new
		boolean pre2 = true;
		for(EventType e: eventTypeList)
		{
			if(e.getName().equals(name))
			{
				pre2 = false;
				break;
			}
		}
		if(!pre2)
		{
			String msg = "ERROR: event type name is not new!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		
		//post
		eventType = new EventType(code, name, menEvent, womenEvent);
		eventTypeList.add(eventType);
	}

	//UC05
	//Add a judge
	public void addJudge(String id,String name,String phone,List<String> eventTypeCodes) throws Exception {
		//pre1 id? is new
		Judge judge = (Judge) Helper.search(judgeList, id);
		boolean pre1 = (judge == null);
		if(!pre1)
		{
			String msg = "ERROR: judge id is not new!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//check all eventTypes exist
		boolean pre2 = true;
		for(String eventTypeCode: eventTypeCodes)
		{
			EventType eventType = (EventType) Helper.search(eventTypeList, eventTypeCode);
			if (eventType == null)
			{
				pre2 = false;
				break;
			}
		}
		if(!pre2)
		{
			String msg = "ERROR: some Event Type is not exists!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//check all eventTypes code are distinct
		boolean pre3 = true;
		for(int i = 0;i < eventTypeCodes.size() && pre3;i++)
		{
			for(int j = 0;j < eventTypeCodes.size() && pre3;j++)
			{
				if(i != j && eventTypeCodes.get(i).equals(eventTypeCodes.get(j)))
				{
					pre3 = false;
				}
			}
		}
		if(!pre3)
		{
			String msg = "ERROR: Event Types are not distinct!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//post
		List<EventType> eventTypes = new ArrayList<EventType>();
		for(String eventTypeCode: eventTypeCodes)
		{
			EventType eventType = (EventType) Helper.search(eventTypeList, eventTypeCode);
			eventTypes.add(eventType);
		}
		judge = new Judge(id, name, phone, eventTypes);
		judgeList.add(judge);
	}	
	
	//UC06
	//Add a meet (and the competitions for the meet)
	public void addMeet(String id,String name,String place,int date) throws Exception {
		//pre1 id? is new
		Meet meet = (Meet) Helper.search(meetList, id);
		boolean pre1 = (meet == null);
		if(!pre1)
		{
			String msg = "ERROR: meet id is not new!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//pre2 has more than zero division
		boolean pre2 = true;
		pre2 = (divisionList.size() > 0);
		if(!pre2)
		{
			String msg = "ERROR: There are no divisions!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//post
		meet = new Meet(id, name, place, date);
		meetList.add(meet);
		for(Division division : divisionList)
		{
			Competition competition = new Competition(meet, division);
			competitionList.add(competition);
		}
	}
	
	//UC07
	//Add an event for a competition
	public void addEvent(String meetId, String divisionCode, String eventTypeCode) throws Exception {
		//pre1 competition exists
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(meetId);
		temp.add(divisionCode);
		Competition competition = (Competition) Helper.search(competitionList, temp);
		boolean pre1 = (competition != null);
		if(!pre1)
		{
			String msg = "ERROR: Competition is not exists!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}	
		//pre2 eventType exist
		EventType eventType = (EventType) Helper.search(eventTypeList,eventTypeCode);
		boolean pre2 = (eventType != null);
		if(!pre2)
		{
			String msg = "ERROR: EventType is not exists!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}	
		//pre3 event is new
		ArrayList<String> eKey = new ArrayList<String>();
		eKey.add(meetId);
		eKey.add(divisionCode);
		eKey.add(eventTypeCode);
		Event event = (Event) Helper.search(eventList, eKey);
		boolean pre3 = (event == null);
		if(!pre3)
		{
			String msg = "ERROR: Event is not new!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}	
		//post
		event = new Event(competition, eventType);
		eventList.add(event);	
	}
	
	//UC08
	//Assign a judge to an event (in a competition)
	public void assignJudge(String judgeId, String meetId, String divisionCode, String eventTypeCode) throws Exception {
		//pre1 judge exists
		Judge judge = (Judge) Helper.search(judgeList, judgeId);
		boolean pre1 = (judge != null);
		if(!pre1)
		{
			String msg = "ERROR: judge is not exists!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//pre2 event exists
		ArrayList<String> eKey = new ArrayList<String>();
		eKey.add(meetId);
		eKey.add(divisionCode);
		eKey.add(eventTypeCode);
		Event event = (Event) Helper.search(eventList, eKey);
		boolean pre2 = (event != null);
		if(!pre2)
		{
			String msg = "ERROR: Event is not exists!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}	
		//pre3 judge not in the event
		boolean pre3 = true;
		List<Judge> judges = event.getJudges();
		for(Judge j:judges)
		{
			if(j.getId() == judgeId)
			{
				pre3 = false;
				break;
			}
		}
		if(!pre3)
		{
			String msg = "ERROR: Judge is already in the event!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}	
		//post
		event.addJudge(judge);
	}

	//UC09
	//Register a team
	public void addTeam(String clubId, String meetId, String divisionCode) throws Exception {
		// pre1 club exists
		Club club = (Club) Helper.search(clubList, clubId);
		boolean pre1 = (club != null);
		if(!pre1)
		{
			String msg = "ERROR: Club is not exists!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//pre2 competition exist
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(meetId);
		temp.add(divisionCode);
		Competition competition = (Competition) Helper.search(competitionList, temp);
		boolean pre2 = (competition != null);
		if(!pre2)
		{
			String msg = "ERROR: Competition is not exists!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}	
		//pre3 check team is new
		ArrayList<String> cKey = new ArrayList<String>();
		cKey.add(clubId);
		cKey.add(meetId);
		cKey.add(divisionCode);
		Team team = (Team) Helper.search(teamList, cKey);
		boolean pre3 = (team == null);
		if(!pre3)
		{
			String msg = "ERROR: Team is not new!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}	
		//post
		team = new Team(club, competition);
		teamList.add(team);
	}
	
	//UC10  Register a member for a team
	public void addTeamMember(String gymnastId, String meetId) throws Exception {
		//pre1 gymnast exists
		Gymnast gymnast = (Gymnast) Helper.search(gymnastList, gymnastId);
		boolean pre1 = (gymnast != null);
		if(!pre1)
		{
			String msg = "ERROR: Gymnast is not exists!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//pre2 team exists
		Club club = gymnast.getClub();
		String clubId = club.getId();
		char gender = gymnast.getGender();
		int age = gymnast.getAge();
		String divisionCode = new String();
		for(Division d:divisionList)
		{
			if(gender == d.getGender() && age > d.getLowerAgeLimit() && age < d.getUpperAgeLimit())
			{
				divisionCode = d.getCode();
			}
		}
		ArrayList<String> tKey = new ArrayList<String>();
		tKey.add(clubId);
		tKey.add(meetId);
		tKey.add(divisionCode);
		Team team = (Team) Helper.search(teamList, tKey);
		boolean pre2 = (team != null);
		if(!pre2)
		{
			String msg = "ERROR: Team is not exists!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}	
		//pre3 teamMember is new
		ArrayList<String> key = new ArrayList<String>();
		key.add(gymnastId);
		key.add(clubId);
		key.add(meetId);
		key.add(divisionCode);
		TeamMember teamMember = (TeamMember) Helper.search(teamMemberList, key);
		boolean pre3 = (teamMember == null);
		if(!pre3)
		{
			String msg = "ERROR: Team member is not new!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}	
		//post
		teamMember = new TeamMember(gymnast,team);
		teamMemberList.add(teamMember);
	}
	
	//UC11
	//Enter the score for a gymnast (member of a team) for an event
	public void addScore(String gymnastId, String meetId, String eventTypeCode, double value) throws Exception {
		//pre1 teamMember exists
		Gymnast gymnast = (Gymnast) Helper.search(gymnastList, gymnastId);
		boolean temp = (gymnast != null);
		Club club = gymnast.getClub();
		String clubId = club.getId();
		char gender = gymnast.getGender();
		int age = gymnast.getAge();
		String divisionCode = new String();
		for(Division d:divisionList)
		{
			if(gender == d.getGender() && age > d.getLowerAgeLimit() && age < d.getUpperAgeLimit())
			{
				divisionCode = d.getCode();
			}
		}
		ArrayList<String> key = new ArrayList<String>();
		key.add(gymnastId);
		key.add(clubId);
		key.add(meetId);
		key.add(divisionCode);
		TeamMember teamMember = (TeamMember) Helper.search(teamMemberList, key);
		boolean pre1 = (teamMember != null);
		if(!pre1 && temp)
		{
			String msg = "ERROR: Team member is not exist!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}	
		//pre2 event exists
		ArrayList<String> ekey = new ArrayList<String>();
		ekey.add(meetId);
		ekey.add(divisionCode);
		ekey.add(eventTypeCode);
		Event event = (Event) Helper.search(eventList, ekey);
		boolean pre2 = (event != null);
		if(!pre2)
		{
			String msg = "ERROR: Event is not exist!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//pre3 score not exists
		ArrayList<String> skey = new ArrayList<String>();
		skey.add(gymnastId);
		skey.add(meetId);
		skey.add(eventTypeCode);
		Score score = (Score) Helper.search(scoreList, skey);
		boolean pre3 = (score == null);
		if(!pre3)
		{
			String msg = "ERROR: Score is not new!\n";
			System.out.println(msg);
			throw new Exception(msg);
		}
		//post
		score = new Score(teamMember,event,value);
		scoreList.add(score);
	}
	
}	