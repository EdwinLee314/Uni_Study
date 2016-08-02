/**
* Use for Junqi Li 16554835 IOO Assignment
*/

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class CarServiceBooking {
	private static CustomerRecordSystem recordSystem = new CustomerRecordSystem();
	private static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {
		mainMenu();
	}
	
	public static void mainMenu() {
		System.out.print("    == Car Service Booking System ==\n" 
		+ "1. Add a customer\n"
		+ "2. Add a car\n"
		+ "3. Make a booking\n"
		+ "4. Retrieve detail of a booking\n"
		+ "5. Modify a booking\n"
		+ "6. Cancel a booking\n"
		+ "7. Calculate service cost\n"
		+ "8. Retrieve a customer record by the id\n"
		+ "9. Save all the data to a text file\n"
		+ "E. Exit\n"
		+ "Please enter an option(1-9 or E):");
		try {
			String token = keyboard.nextLine();
			int option = 0;
			if (token.equalsIgnoreCase("E")){
				option = 10;
			}
			else {
				option = Integer.parseInt(token);
			}	
			switch (option) {
				case 1:
					addCustomerMenu();
					break;	
				case 2:
					addCarMenu();
					break;
				case 3:
					makeBookingMenu();
					break;
				case 4:
					retrieveBookingMenu();
					break;
				case 5:
					modifyBookingMenu();
					break;
				case 6:
					cancelBookingMenu();
					break;
				case 7:
					calculateCostMenu();
					break;
				case 8:
					retrieveCustomerMenu();
					break;
				case 9:
					saveDataMenu();
					break;
				case 10:
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
	
	public static void addCustomerMenu() {
		System.out.print("  == Add Customer Menu == \n"
			+"1. Add Customer\n"
			+"2. Exit to MainMenu\n"
			+"Please enter an option(1 or 2):");
		try {
			String token = keyboard.nextLine();
			int option = 0;
			option = Integer.parseInt(token);	
			switch (option) {
				case 1:
					System.out.println("Please input the Customer Name.");
					String name = keyboard.nextLine();
					System.out.println("Please input the Customer ID.");
					String input = keyboard.nextLine();
					int id = Integer.parseInt(input);
					Customer newCustomer = new Customer(id, name);
					recordSystem.addCustomer(newCustomer);
					System.out.println(recordSystem.toString()); 
					System.out.println("The customer is recorded."); 
					mainMenu();
					break;	
				case 2:
					mainMenu();
					break;
				default:
					System.out.println("Not a valid choice.\n");	
					addCustomerMenu();
			}
		}	
		catch(NumberFormatException e) {
			System.out.println("Selection not found.\n");
			addCustomerMenu();
		}  
		catch(InvalidInputException e) {
			System.out.println(e.getMessage());
			addCustomerMenu();
		}
	}	
	
	public static void retrieveCustomerMenu() {
		System.out.print("  == Retrieve Customer Menu == \n"
			+"1. Retrieve Customer\n"
			+"2. Exit to MainMenu\n"
			+"Please enter an option(1 or 2):");
		try {
			String token = keyboard.nextLine();
			int option = 0;
			option = Integer.parseInt(token);	
			switch (option) {
				case 1:
					Customer aimCustomer = recordSystem.findCustomer();
					System.out.println(aimCustomer.toString());
					mainMenu();
					break;	
				case 2:
					mainMenu();
					break;
				default:
					System.out.println("Not a valid choice.\n");	
					retrieveCustomerMenu();
			}
		}	
		catch(NumberFormatException e) {
			System.out.println("Selection not found.\n");
			retrieveCustomerMenu();
		}  
		catch(InvalidInputException e) {
			System.out.println(e.getMessage());
			retrieveCustomerMenu();
		}
	}
	
	public static void addCarMenu() {
		System.out.print("  == Add Car Menu == \n"
			+"1. Add Car\n"
			+"2. Exit to MainMenu\n"
			+"Please enter an option(1 or 2):");
		try {
			String token = keyboard.nextLine();
			int option = 0;
			option = Integer.parseInt(token);	
			switch (option) {
				case 1:
					Customer aimCustomer = recordSystem.findCustomer();
					System.out.println("Please enter the registration of the Car");
					String input = keyboard.nextLine();
					recordSystem.checkIsValidCarRegistration(input);
					Car newCar = new Car(input);
					aimCustomer.addCar(newCar);
					System.out.println(recordSystem.toString()); 
					System.out.println("The car is recorded."); 
					mainMenu();
					break;	
				case 2:
					mainMenu();
					break;
				default:
					System.out.println("Not a valid choice.\n");	
					addCarMenu();
			}
		}	
		catch(NumberFormatException e) {
			System.out.println("Selection not found.\n");
			addCarMenu();
		}  
		catch(InvalidInputException e) {
			System.out.println(e.getMessage());
			addCarMenu();
		}			
	}
	
	public static void makeBookingMenu() {
		System.out.print("  == Make Booking Menu == \n"
			+"1. Make Booking\n"
			+"2. Exit to MainMenu\n"
			+"Please enter an option(1 or 2):");
		try {
			String token = keyboard.nextLine();
			int option = 0;
			option = Integer.parseInt(token);	
			switch (option) {
				case 1:
					Customer aimCustomer = recordSystem.findCustomer();
					Car aimCar = aimCustomer.findCar();
					System.out.println("Please enter the description of booking");
					String date = keyboard.nextLine();
					System.out.println("Please enter the type of service(Express or Major)");
					String type = keyboard.nextLine();
					System.out.println("Please enter the Booking Id");
					String serviceID = keyboard.nextLine();
					int serviceId = Integer.parseInt(serviceID);
					if(type.equalsIgnoreCase("Express")) {
						Service service = new ExpressService(serviceId, date);
						aimCar.addService(service);
					}
					else if(type.equalsIgnoreCase("Major")) {
						Service service = new MajorService(serviceId, date);
						aimCar.addService(service);
					}
					else {
						System.out.println("Please enter Express or Major.\n");
						makeBookingMenu();
					}
					System.out.println(recordSystem.toString());
					System.out.println("The booking is recorded."); 
					mainMenu();	
					break;
				case 2:
					mainMenu();
					break;
				default:
					System.out.println("Not a valid choice.\n");	
					makeBookingMenu();
			}
		}	
		catch(NumberFormatException e) {
			System.out.println("Selection not found.\n");
			makeBookingMenu();
		}  
		catch(InvalidInputException e) {
			System.out.println(e.getMessage());
			makeBookingMenu();
		}	
	}
	
	public static void modifyBookingMenu() {
		System.out.print("  == Modify Booking Menu == \n"
			+"1. Modify booking\n"
			+"2. Exit to MainMenu\n"
			+"Please enter an option(1 or 2):");
		try {
			String token = keyboard.nextLine();
			int option = 0;
			option = Integer.parseInt(token);	
			switch (option) {
				case 1:
					Customer aimCustomer = recordSystem.findCustomer();
					Car aimCar = aimCustomer.findCar();
					System.out.println("Please enter the Booking ID");
					String serviceID = keyboard.nextLine();
					int serviceId = Integer.parseInt(serviceID);
					aimCar.removeService(serviceId);
					System.out.println("Please enter the description of booking");
					String date = keyboard.nextLine();
					System.out.println("Please enter the type of service(Express or Major)");
					String type = keyboard.nextLine();
					if(type.equalsIgnoreCase("Express")) {
						Service service = new ExpressService(serviceId, date);
						aimCar.addService(service);
					}
					else if(type.equalsIgnoreCase("Major")) {
						Service service = new MajorService(serviceId, date);
						aimCar.addService(service);
					}
					else {
						System.out.println("Please enter Express or Major.\n");
						modifyBookingMenu();
					}	
					System.out.println(recordSystem.toString());
					System.out.println("The booking is modified.");
					mainMenu();
					break;
				case 2:
					mainMenu();
					break;
				default:
					System.out.println("Not a valid choice.\n");	
					modifyBookingMenu();
			}
		}	
		catch(NumberFormatException e) {
			System.out.println("Selection not found.\n");
			modifyBookingMenu();
		}  
		catch(InvalidInputException e) {
			System.out.println(e.getMessage());
			modifyBookingMenu();
		}		
	}
	
	public static void retrieveBookingMenu() {
		System.out.print("  == Retrieve Booking Menu == \n"
			+"1. Retrieve Booking\n"
			+"2. Exit to MainMenu\n"
			+"Please enter an option(1 or 2):");
		try {
			String token = keyboard.nextLine();
			int option = 0;
			option = Integer.parseInt(token);	
			switch (option) {
				case 1:
					Customer aimCustomer = recordSystem.findCustomer();
					Car aimCar = aimCustomer.findCar();
					System.out.println("Please enter the Booking ID");
					String serviceID = keyboard.nextLine();
					int serviceId = Integer.parseInt(serviceID);
					Service booking = aimCar.searchService(serviceId);
					System.out.println("The booking car registration is: " + aimCar.getRegistration()); 
					System.out.println("The booking description is: " + booking.getDiscription()+ "\n");
					mainMenu();
					break;
				case 2:
					mainMenu();
					break;
				default:
					System.out.println("Not a valid choice.\n");	
					retrieveBookingMenu();
			}
		}	
		catch(NumberFormatException e) {
			System.out.println("Selection not found.\n");
			retrieveBookingMenu();
		}  
		catch(InvalidInputException e) {
			System.out.println(e.getMessage());
			retrieveBookingMenu();
		}		
	}
	
	public static void cancelBookingMenu() {
		System.out.print("  == Cancel Booking Menu == \n"
			+"1. Cancel Booking\n"
			+"2. Exit to MainMenu\n"
			+"Please enter an option(1 or 2):");
		try {
			String token = keyboard.nextLine();
			int option = 0;
			option = Integer.parseInt(token);	
			switch (option) {
				case 1:
					Customer aimCustomer = recordSystem.findCustomer();
					Car aimCar = aimCustomer.findCar();
					System.out.println("Please enter the Booking ID");
					String serviceID = keyboard.nextLine();
					int serviceId = Integer.parseInt(serviceID);
					aimCar.removeService(serviceId);
					System.out.println("The booking is cancelled.");
					mainMenu();
					break;	
				case 2:
					mainMenu();
					break;
				default:
					System.out.println("Not a valid choice.\n");	
					cancelBookingMenu();
			}
		}	
		catch(NumberFormatException e) {
			System.out.println("Selection not found.\n");
			cancelBookingMenu();
		}  
		catch(InvalidInputException e) {
			System.out.println(e.getMessage());
			cancelBookingMenu();
		}	
	}	
	
	public static void calculateCostMenu() {
		System.out.print("  == Calculate Cost Menu == \n"
			+"1. Calculate Cost\n"
			+"2. Exit to MainMenu\n"
			+"Please enter an option(1 or 2):");
		try {
			String token = keyboard.nextLine();
			int option = 0;
			option = Integer.parseInt(token);	
			switch (option) {
				case 1:
					Customer aimCustomer = recordSystem.findCustomer();
					Car aimCar = aimCustomer.findCar();
					System.out.println("Please enter the Booking ID");
					String serviceID = keyboard.nextLine();
					int serviceId = Integer.parseInt(serviceID);
					Service booking = aimCar.searchService(serviceId);
					System.out.println("Please enter the hours it spent");
					String hour1 = keyboard.nextLine();
					double hour = Double.parseDouble(hour1);
					System.out.println("The cost of the service is " + booking.calculateCost(hour));
					System.out.println("Finish calculate the cost.");
					mainMenu();
					break;	
				case 2:
					mainMenu();
					break;
				default:
					System.out.println("Not a valid choice.\n");	
					calculateCostMenu();
			}
		}	
		catch(NumberFormatException e) {
			System.out.println("Selection not found.\n");
			calculateCostMenu();
		}  
		catch(InvalidInputException e) {
			System.out.println(e.getMessage());
			calculateCostMenu();
		}	
	}
	
	public static void saveDataMenu() {
		System.out.println("Please enter the name of the text file.");
		String saveFile = keyboard.nextLine();
		PrintWriter save = null;
		try {
			save = new PrintWriter(new FileOutputStream(saveFile));
		}
		catch(FileNotFoundException e){
			System.out.println("Error to create the file.");
			mainMenu();
		}	
		save.println(recordSystem.toString());
		save.close();
		System.out.println("Finish saving data.");
		mainMenu();
	}	
		
}