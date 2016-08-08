import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class WordMatch {	
	private static Scanner keyboard = new Scanner(System.in);
	private static TrieTree lexicon = new TrieTree();
	public static void main(String[] args){
		mainMenu();
	}
	
	public static void mainMenu(){
		System.out.println( "r) Read in a text file and add the words in it to the lexicon\n"
		+ "s) Search for a word\n"
		+ "w) Write the lexicon to a new file\n"
		+ "q) Quit\n"
		+ "Please enter an option(r, s, w or q):");
		
		try {
			String token = keyboard.nextLine();
			char option = 'v';
			if(token.length() == 1){
				option = token.charAt(0);	
			}
			
			switch (option) {
				case 'r':
				case 'R':
					readMenu();
					break;	
				case 's':
				case 'S':		
					searchMenu();
					break;
				case 'w':
				case 'W':
					writeMenu();
					break;
				case 'q':
				case 'Q':
					System.out.println("Exiting System......");
					break;		
				default:
					System.out.println("Not a valid choice.\n");	
					mainMenu();
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Selection can not be found.\n");
			mainMenu();
		}  
	}
	
	public static void readMenu(){		 		  
		System.out.println("Please enter the name of the text file:");
		String fileName = keyboard.nextLine();
		File file = new File(fileName);
		BufferedReader input = null;
		int b;
		String word = "";
		try{
			if(file.exists()){
				input = new BufferedReader(new FileReader(file));
				while((b = input.read()) != -1){
					if(b >=65 && b <= 90){ //upper letter
						b = b + 32;
						//String t = String.valueOf((char)b);
						word = word + (char)b;
					}else if(b >=97 && b <=122){ //lower letter
						//String t = String.valueOf((char)b);
						word = word + (char)b; 
					}else if(b != 39 && b != 44){
						if(word.length() != 0){
						//System.out.println(word);
						lexicon.insert(word);
						word = "";
						}
					}
				}
				if(word.length() != 0){
					//System.out.println(word);
					lexicon.insert(word);
					word = "";
				}
				input.close();
				System.out.println("Finished reading, all words are added to the lexicon");
			}
			else {
				System.out.println("File is not existed.");
			}
		}catch (Exception e) {
			System.exit(1);
		}					
		System.out.println("You can read more text files by Enter R in Main Menu");	 
		mainMenu();
	}
	
	public static void searchMenu(){
		System.out.println("Please enter the pattern:");
		String key = keyboard.nextLine();
		lexicon.search(key);
		mainMenu();
	}
	
	public static void writeMenu(){
		System.out.println("Please enter the name of the saving file:");
		String file = keyboard.nextLine();
		PrintWriter save = null;
		try {
			save = new PrintWriter(new FileOutputStream(file));
		}
		catch(FileNotFoundException e){
			System.out.println("Error to create the file.");
			mainMenu();
		}
		lexicon.printAllWords(save);
		save.close();
		System.out.println("Finish saving the Lexicon.");
		mainMenu();
	}
}
