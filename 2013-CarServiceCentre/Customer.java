/**
 * Use for Junqi Li 16554835 IOO Assignment
 */

import java.util.* ;
public class Customer {
	private int id;
	private String name;
	
	private int numberOfCar;
	private final int MAXCAR = 10;
	private Car[] carArray = new Car[MAXCAR];
	
	public Customer(int id, String name)throws InvalidInputException {
		checkIsValidName(name);
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void checkIsValidName(String name)throws InvalidInputException {
		if(name.length() == 0) {
			throw new InvalidInputException("Invalid customer name length.You must enter at least 1 character.");
		}
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
   
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		String toString = getClass().getName() + "[" + " Customer ID: " + id + " Customer Name: " + name + "]";
		if (numberOfCar > 0) {
			toString = toString + "\n\nThis customer has these cars:";
			for(int i = 0; i < numberOfCar; i++){
				toString = toString + "\n" + carArray[i].toString();
			}
		}
		return toString;	
	}
	
	public void addCar(Car car)throws InvalidInputException {
		checkIsValidCar(car);
		carArray[numberOfCar] = car;
		numberOfCar++;
	}
	
	public void checkIsValidCar(Car car)throws InvalidInputException {
		if( numberOfCar == MAXCAR ) {
			throw new InvalidInputException("No more space!!!Can not add this car to Record System.");
		}
		if( searchCar(car.getRegistration()) != null ) {
			throw new InvalidInputException("This car exist in this customer!!!Can not add again.");
		}
	}
	
	public Car searchCar(String registration) {
		Car aimCar = null;    
		for(int i =0; i< numberOfCar; i++) {
			if (registration.equals(carArray[i].getRegistration())) {
				aimCar = carArray[i];
				break;
			}
        }  
		return aimCar;
	}		
	
	public Car findCar()throws InvalidInputException {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the registration of the Car");
		String registration = keyboard.nextLine();
		if ("".equals(registration)){
			throw new InvalidInputException("No registration entered.");
		}	
		if( searchCar(registration) == null ) {
			throw new InvalidInputException("This car is not exist in this customer!!!");
		}
		Car aimCar = searchCar(registration);
		return aimCar;
	}
	
}
