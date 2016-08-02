/*
	Student name Junqi Li
	Student No 16554835
	Assignment for CSE1OOF
*/
import java.util.*;
import java.io.*;
public class LondonGames
{
	private static MedalTally London = new MedalTally();
	
	public static void main(String[] args)throws IOException
	{
		load();
		mainMenu();
	}
	
	public static void load()throws IOException
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the name of the input text file.");
		String inputFile = keyboard.nextLine();
		File file = new File(inputFile + ".txt" );
		if (file.exists())
		{
		Scanner inputStream = null;
		inputStream = new Scanner(new FileInputStream(inputFile + ".txt"));
			while(inputStream.hasNextLine())
			{
				String line = inputStream.nextLine();
				String[] sArray;
				sArray = line.split(" ");
				//System.out.println(sArray.length);
				if (sArray.length > 4)
				{
					int total = Integer.parseInt(sArray[sArray.length - 1]);
					int bronze = Integer.parseInt(sArray[sArray.length - 2]);
					int silver = Integer.parseInt(sArray[sArray.length - 3]);	
					int gold = Integer.parseInt(sArray[sArray.length - 4]);
					if (total != bronze + silver + gold)
					{
						total = bronze + silver + gold;
					}
					String name = "";
						for (int i = 0; i < sArray.length - 4;i++)
						{
							if( i == 0)
							{
								name = sArray[i];
							}
							else
							{
								name = name + " " + sArray[i] ; 
							}
						}	
					Country p = new Country(name, gold, silver, bronze, total);
					London.addCountry(p);
				}
			}
			inputStream.close();
			System.out.println(London);
			System.out.println("The file had been succeed read.");
		}
		else
		{
			System.out.println("File is not existed.");
		}	
	}
	
	public static void mainMenu()
	{
		System.out.println("    Welcome to the London Olympic Games Medal Tally");
		System.out.println("                        Main Menu");
		System.out.println("1.Add Country");
		System.out.println("2.Delete Country");
		System.out.println("3.Add Medal(s)");
		System.out.println("4.Display Medal Tally");
		System.out.println("5.Save to file");
		System.out.println("6.Exit");
		System.out.println("Enter choice >>");
		Scanner keyboard = new Scanner(System.in);
		int step = keyboard.nextInt();
		switch (step)
		{
			case 1:
					addMenu();
					break;	
			case 2:
					deleteMenu();
					break;
			case 3:
					addMedalMenu();
					break;
			case 4:
					displayMenu();
					break;
			case 5:
					saveMenu();
					break;
			case 6:
					break;
			default:
					System.out.println("Not a valid choice.");		
		}
	}	
	
	public static void addMenu()
	{
		System.out.println("1. Add Country");
		System.out.println("        Add Menu");
		System.out.println("  		1.Country Name");
		System.out.println("		2.Exit");
		System.out.println("		Enter choice >>");
		Scanner keyboard = new Scanner(System.in);
		int step = keyboard.nextInt();
		keyboard.nextLine();
		switch (step)
		{
			case 1:
					System.out.println("Please enter the Country Name.");
					String input = keyboard.nextLine();
					Country a = new Country(input);
					London.addCountry(a);
					//System.out.println(London.toString());
					System.out.println("Succeed save the Country."); 
					mainMenu();
					break;	
			case 2:
					mainMenu();
					break;
			default:
					System.out.println("Not a valid choice.");		
		}
	}	
	
	public static void deleteMenu()
	{
		System.out.println("2. Delete Country");
		System.out.println("        Delete Menu");
		System.out.println("  		1.Country Name");
		System.out.println("		2.Exit");
		System.out.println("		Enter choice >>");
		Scanner keyboard = new Scanner(System.in);
		int step = keyboard.nextInt();
		keyboard.nextLine();
		switch (step)
		{
			case 1:
					System.out.println("Please enter the Country Name.");
					String input = keyboard.nextLine();
					London.deleteCountry(input);
					//System.out.println(London.toString());
					System.out.println("Succeed delete the Country."); 
					mainMenu();
					break;	
			case 2:
					mainMenu();
					break;
			default:
					System.out.println("Not a valid choice.");		
		}
	}
	
	public static void addMedalMenu()
	{
		System.out.println("3. Add Medal(s)");
		System.out.println("        Add Menu");
		System.out.println("  		1.Country Name");
		System.out.println("		2.Exit");
		System.out.println("		Enter choice >>");
		Scanner keyboard = new Scanner(System.in);
		int step = keyboard.nextInt();
		keyboard.nextLine();
		switch (step)
		{
			case 1:
					System.out.println("Please enter the Country Name.");
					String input = keyboard.nextLine();
					System.out.println("Please enter the Gold Medal you want to add.");
					int g = keyboard.nextInt();
					London.search(input).addGold(g);
					keyboard.nextLine();
					System.out.println("The Gold Medal had been succeed recorded.");
					System.out.println("Please enter the Silver Medal you want to add.");
					int s = keyboard.nextInt();
					London.search(input).addSilver(s);
					keyboard.nextLine();
					System.out.println("The Silver Medal had been succeed recorded.");
					System.out.println("Please enter the Bronze Medal you want to add.");
					int b = keyboard.nextInt();
					London.search(input).addBronze(b);
					keyboard.nextLine();
					London.search(input).addTotal();
					System.out.println("The Bronze Medal had been succeed recorded.");
					mainMenu();
					break;	
			case 2:
					mainMenu();
					break;
			default:
					System.out.println("Not a valid choice.");		
		}
	}
	
	public static void displayMenu()
	{
		System.out.println("4. Display Medal Tally");
		System.out.println("		Display Menu");
		System.out.println("		1.Display by Total");
		System.out.println("		2.Display by Gold");
		System.out.println("		3.Display by Silver");
		System.out.println("		4.Display by Bronze");
		System.out.println("		5.Exit");
		System.out.println("		Enter choice >>");
		Scanner keyboard = new Scanner(System.in);
		int step = keyboard.nextInt();
		keyboard.nextLine();
		switch (step)
		{
			case 1:
					London.totalTally();
					System.out.println(London);
					mainMenu();
					break;	
			case 2:
					London.goldTally();
					System.out.println(London);
					mainMenu();
					break;
			case 3:
					London.silverTally();
					System.out.println(London);
					mainMenu();
					break;
			case 4:
					London.bronzeTally();
					System.out.println(London);
					mainMenu();
					break;
			case 5:
					mainMenu();
					break;
			default:
					System.out.println("Not a valid choice.");		
		}
	}
	
	public static void saveMenu()
	{
		System.out.println("5. Save to file");
		System.out.println("        Save Menu");
		System.out.println("  		1.The text File Name");
		System.out.println("		2.Exit");
		System.out.println("		Enter choice >>");
		Scanner keyboard = new Scanner(System.in);
		int step = keyboard.nextInt();
		keyboard.nextLine();
		switch (step)
		{
			case 1:
					System.out.println("Please enter the name of the output text file.");
					String outputFile = keyboard.nextLine();
					PrintWriter outputStream = null;
					try
					{
					outputStream = new PrintWriter(new FileOutputStream(outputFile + ".txt"));
					}
					catch(FileNotFoundException e)
					{
						System.out.println("Error to create the file.");
						System.exit(0);
					}	
					System.out.println("Start Writing.");
					outputStream.println(London.toString());
					outputStream.close();
					System.out.println("End Writing.");
					System.out.println("The file had been succeed stored.");
					mainMenu();
					break;	
			case 2:
					mainMenu();
					break;
			default:
					System.out.println("Not a valid choice.");		
		}
	}	
}	
/*
	Student name Junqi Li
	Student No 16554835
	Assignment for CSE1OOF
*/	