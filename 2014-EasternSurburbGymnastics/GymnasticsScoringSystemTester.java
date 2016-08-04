import java.util.*;
public class GymnasticsScoringSystemTester
{
	static GymnasticsScoringSystem gss = new GymnasticsScoringSystem();
	
	public static void main(String [] args) 
	{
		// new system
		System.out.println("...Test 0 : Create gymnastic system");
		System.out.println( gss );
		
		System.out.println("...Test 1 : Add clubs");
		//successful cases
		UC01Test01();
		//unsuccessful cases : id is not new
		UC01Test02();
		//unsuccessful cases : name is not new
		UC01Test03();
		
		System.out.println("...Test 2 : Add gymnasts");
		//successful cases
		UC02Test01();
		//unsuccessful cases : club is not exists
		UC02Test02();
		//unsuccessful cases : id is not new
		UC02Test03();
		
		System.out.println("...Test 3 : Add divisions");
		//successful cases
		UC03Test01();
		//unsuccessful cases : code is not new
		UC03Test02();
		//unsuccessful cases : name is not new
		UC03Test03();
		//unsuccessful cases : lowerAgeLimit? < upperAgeLimit?
		UC03Test04();
		
		System.out.println("...Test 4 : Add event types");
		//successful cases
		UC04Test01();
		//unsuccessful cases : code is not new
		UC04Test02();
		//unsuccessful cases : name is not new
		UC04Test03();
		
		System.out.println("...Test 5 : Add judges");
		//successful cases
		UC05Test01();
		//unsuccessful cases : id is not new
		UC05Test02();
		//unsuccessful cases : some eventTypes  are not exist
		UC05Test03();
		//unsuccessful cases : eventTypes code are not distinct
		UC05Test04();
		
		System.out.println("...Test 6 : Add meets");
		//successful cases
		UC06Test01();
		//unsuccessful cases : id is not new
		UC06Test02();
		
		System.out.println("...Test 7 : Add events");
		//successful cases
		UC07Test01();
		//unsuccessful cases : competition is not exists
		UC07Test02();
		//unsuccessful cases : eventType is not exists
		UC07Test03();
		//unsuccessful cases : event is not new
		UC07Test04();
		
		System.out.println("...Test 8 : assign judge to event");
		//successful cases
		UC08Test01();
		//unsuccessful cases : competition is not exists
		UC08Test02();
		//unsuccessful cases : eventType is not exists
		UC08Test03();
		//unsuccessful cases : Judge is already in the event
		UC08Test04();
		
		System.out.println("...Test 9 : Register a team");
		//successful cases
		UC09Test01();
		//unsuccessful cases : club is not exists
		UC09Test02();
		//unsuccessful cases : competition is not exists
		UC09Test03();
		//unsuccessful cases : team is already in the list
		UC09Test04();
		
		System.out.println("...Test 10 : Register a member for a team");
		//successful cases
		UC10Test01();
		//unsuccessful cases : gymnast is not exists
		UC10Test02();
		//unsuccessful cases : team is not exists
		UC10Test03();
		//unsuccessful cases : teamMember is already in the list
		UC10Test04();
		
		System.out.println("...Test 11 :Add scores");
		//successful cases
		UC11Test01();
		//unsuccessful cases : teamMember is not exists
		UC11Test02();
		//unsuccessful cases : event is not exists
		UC11Test03();
		//unsuccessful cases : score is already in the list
		UC11Test04();	
	}
	
	public static void UC01Test01()
	{
		try{
			gss.addClub("CLUB-10", "Acrobats", "1010");
			gss.addClub("CLUB-20", "Tiger", "0101");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC01Test02() 
	{
		try{
			gss.addClub("CLUB-10", "Wood", "1010");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC01Test03() 
	{
		try{
		
			gss.addClub("CLUB-30", "Tiger", "1010");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC02Test01()
	{
		try{
			gss.addGymnast("GYMNAST-10", "Smith", 'M', 20, "CLUB-10");
			gss.addGymnast("GYMNAST-20", "John", 'M', 23, "CLUB-20");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC02Test02()
	{
		try{
			gss.addGymnast("GYMNAST-30", "Hank", 'M', 20, "CLUB-30");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC02Test03()
	{
		try{
			gss.addGymnast("GYMNAST-10", "Jack", 'M', 20, "CLUB-10");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC03Test01()
	{
		try{
			gss.addDivision("MJ", "Mens Juniors", 'M', 10, 15);
			gss.addDivision("MS", "Mens Seniors", 'M', 16, 80);
			gss.addDivision("WJ", "Womens Juniors", 'F', 10, 15);
			gss.addDivision("WS", "Womens seniors", 'F', 16, 80);
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC03Test02()
	{
		try{
			gss.addDivision("MJ", "Mens fdgdf", 'M', 10, 15);
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC03Test03()
	{
		try{
			gss.addDivision("MN",  "Mens Juniors", 'M', 10, 15);
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC03Test04()
	{
		try{
			gss.addDivision("MN",  "Mens Jundfg", 'M', 23, 15);
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC04Test01()
	{
		try{
			gss.addEventType("EVENT-TYPE-10", "floor", true, true);
			gss.addEventType("EVENT-TYPE-20", "bar", true, false);
			gss.addEventType("EVENT-TYPE-30", "beam", false, true);
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC04Test02()
	{
		try{
			gss.addEventType("EVENT-TYPE-10", "zxczxv", true, true);
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC04Test03()
	{
		try{
			gss.addEventType("EVENT-TYPE-40", "bar", true, true);
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC05Test01()
	{
		try{
			List<String> eventTypeCodes = new ArrayList<String>();
			eventTypeCodes.add("EVENT-TYPE-10");
			eventTypeCodes.add("EVENT-TYPE-20");
			gss.addJudge("JUDGE-10", "Adams", "1111", eventTypeCodes);
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC05Test02()
	{
		try{
			List<String> eventTypeCodes = new ArrayList<String>();
			eventTypeCodes.add("EVENT-TYPE-10");
			eventTypeCodes.add("EVENT-TYPE-20");
			gss.addJudge("JUDGE-10", "Bobs", "1222", eventTypeCodes);
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC05Test03()
	{
		try{
			List<String> eventTypeCodes = new ArrayList<String>();
			eventTypeCodes.add("EVENT-TYPE-10");
			eventTypeCodes.add("EVENT-TYPE-60");
			gss.addJudge("JUDGE-20", "Bobs", "1222", eventTypeCodes);
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC05Test04()
	{
		try{
			List<String> eventTypeCodes = new ArrayList<String>();
			eventTypeCodes.add("EVENT-TYPE-10");
			eventTypeCodes.add("EVENT-TYPE-10");
			gss.addJudge("JUDGE-30", "John", "1222", eventTypeCodes);
			System.out.println( gss );
		}
		catch(Exception e) {}
	}
	
	public static void UC06Test01()
	{
		try{
			gss.addMeet("MEET-10", "Town Hall 10", "Town Hall", 10);
			System.out.println( gss );
		}
		catch(Exception e) {}
	}
	
	public static void UC06Test02()
	{
		try{
			gss.addMeet("MEET-10", "City Hall 10", "City Hall", 22);
			System.out.println( gss );
		}
		catch(Exception e) {}
	}
	
	public static void UC07Test01()
	{
		try{
			gss.addEvent("MEET-10", "MS", "EVENT-TYPE-10");
			System.out.println( gss );
		}
		catch(Exception e) {}
	}
	
	public static void UC07Test02()
	{
		try{
			gss.addEvent("MEET-20", "MS", "EVENT-TYPE-10");
			System.out.println( gss );
		}
		catch(Exception e) {}
	}
	
	public static void UC07Test03()
	{
		try{
			gss.addEvent("MEET-10", "MS", "EVENT-TYPE-60");
			System.out.println( gss );
		}
		catch(Exception e) {}
	}
	
	public static void UC07Test04()
	{
		try{
			gss.addEvent("MEET-10", "MS", "EVENT-TYPE-10");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC08Test01()
	{
		try{
			gss.assignJudge("JUDGE-10", "MEET-10", "MS", "EVENT-TYPE-10");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC08Test02()
	{
		try{
			gss.assignJudge("JUDGE-50", "MEET-10", "MS", "EVENT-TYPE-10");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC08Test03()
	{
		try{
			gss.assignJudge("JUDGE-10", "MEET-40", "MS", "EVENT-TYPE-10");
			gss.assignJudge("JUDGE-10", "MEET-10", "MS", "EVENT-TYPE-20");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC08Test04()
	{
		try{
			gss.assignJudge("JUDGE-10", "MEET-10", "MS", "EVENT-TYPE-10");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC09Test01()
	{
		try{
			gss.addTeam("CLUB-10", "MEET-10", "MS");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC09Test02()
	{
		try{
			gss.addTeam("CLUB-80", "MEET-10", "MS");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC09Test03()
	{
		try{
			gss.addTeam("CLUB-10", "MEET-90", "MS");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC09Test04()
	{
		try{
			gss.addTeam("CLUB-10", "MEET-10", "MS");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC10Test01()
	{
		try{
			gss.addTeamMember("GYMNAST-10", "MEET-10");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC10Test02()
	{
		try{
			gss.addTeamMember("GYMNAST-80", "MEET-10");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC10Test03()
	{
		try{
			gss.addTeamMember("GYMNAST-10", "MEET-50");
			System.out.println( gss );}
		catch(Exception e) {}
	}
		
	public static void UC10Test04()
	{
		try{
			gss.addTeamMember("GYMNAST-10", "MEET-10");
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC11Test01()
	{
		try{
			gss.addScore("GYMNAST-10", "MEET-10", "EVENT-TYPE-10", 100);
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC11Test02()
	{
		try{
			gss.addScore("GYMNAST-10", "MEET-50", "EVENT-TYPE-10", 100);
			System.out.println( gss );}
		catch(Exception e) {}
	}
		
	public static void UC11Test03()
	{
		try{
			gss.addScore("GYMNAST-10", "MEET-10", "EVENT-TYPE-50", 100);
			System.out.println( gss );}
		catch(Exception e) {}
	}
	
	public static void UC11Test04()
	{
		try{
			gss.addScore("GYMNAST-10", "MEET-10", "EVENT-TYPE-10", 100);
			System.out.println( gss );}
		catch(Exception e) {}
	}
}	