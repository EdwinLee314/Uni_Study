import java.util.*;
public class GymnasticsScoringSystemQuickTester
{
	public static void main(String [] args) throws Exception
	{
		int test = 1;
		// new system
		System.out.println("...Test " + (test++) + ": Create gymnastic system");
		GymnasticsScoringSystem gss = new GymnasticsScoringSystem();
		System.out.println( gss );
		
		// 1. add clubs
		System.out.println("...Test " + (test++) + ": Add clubs");
		gss.addClub("CLUB-10", "Acrobats", "1010");
		System.out.println( gss );
		
		// 2. add gymnasts
		System.out.println("...Test " + (test++) + ": Add gymnasts");
		gss.addGymnast("GYMNAST-10", "Smith", 'M', 20, "CLUB-10");
		System.out.println( gss );

		// 3. add divisions
		System.out.println("...Test " + (test++) + ": Add divisions");
		gss.addDivision("MJ", "Mens Juniors", 'M', 10, 15);
		gss.addDivision("MS", "Mens Seniors", 'M', 16, 80);
		gss.addDivision("WJ", "Womens Juniors", 'F', 10, 15);
		gss.addDivision("WS", "Womens seniors", 'F', 16, 80);
		System.out.println( gss );

		// 4. add 3 event types
		System.out.println("...Test " + (test++) + ": Add event types");
		gss.addEventType("EVENT-TYPE-10", "floor", true, true);
		gss.addEventType("EVENT-TYPE-20", "bar", true, false);
		gss.addEventType("EVENT-TYPE-30", "beam", false, true);
		System.out.println( gss );
		
		// 5. add judges
		System.out.println("...Test " + (test++) + ": Add judges");
		List<String> eventTypeCodes = new ArrayList<String>();
		eventTypeCodes.add("EVENT-TYPE-10");
		eventTypeCodes.add("EVENT-TYPE-20");
		gss.addJudge("JUDGE-10", "Adams", "1111", eventTypeCodes);
		System.out.println( gss );
		
		// 6. add meets
		System.out.println("...Test " + (test++) + ": Add meets");
		gss.addMeet("MEET-10", "Town Hall 10", "Town Hall", 10);
		System.out.println( gss );
		
		// 7. add events
		System.out.println("...Test " + (test++) + ": Add events");
		gss.addEvent("MEET-10", "MS", "EVENT-TYPE-10");
		System.out.println( gss );
		
		// 8. assign judge to event
		System.out.println("...Test " + (test++) + ": assign judge to event");
		gss.assignJudge("JUDGE-10", "MEET-10", "MS", "EVENT-TYPE-10");
		System.out.println( gss );

		// 9. add teams
		System.out.println("...Test " + (test++) + ": Add teams");
		gss.addteam("CLUB-10", "MEET-10", "MS");
		System.out.println( gss );
		
		// 10. add team members
		System.out.println("...Test " + (test++) + ": Add members");
		gss.addTeamMember("GYMNAST-10", "MEET-10");
		// CLUB-10 and MS (division) can be computed
		System.out.println( gss );
		
		// 11. add scores
		System.out.println("...Test " + (test++) + ": Add scores");
		gss.addScore("GYMNAST-10", "MEET-10", "EVENT-TYPE-10", 100);
		// CLUB-10, MS (division code) can be deduced
		System.out.println( gss );
	
	}
}