/**
* Use for Junqi Li 16554835 IOO Assignment
*/

import java.util.* ;
public class CustomerRecordSystem {
	private int numberOfCustomer;
	private int MAXCUSTOMER = 100;
	private Customer[] customerArray = new Customer[MAXCUSTOMER];
	
	public CustomerRecordSystem() {
		numberOfCustomer = 0;
	}
	
	public void addCustomer(Customer customer)throws InvalidInputException {
		checkIsValidCustomer(customer);
		customerArray[numberOfCustomer] = customer;
		numberOfCustomer++;
	}	
	
	public Customer searchCustomer(int Id) {
		Customer aimCustomer = null;    
		for(int i =0; i< numberOfCustomer; i++) {
			if (Id == customerArray[i].getId()) {
				aimCustomer = customerArray[i];
				break;
			}
        }  
		return aimCustomer;
	}	
	
	public void checkIsValidCustomer(Customer customer)throws InvalidInputException {
		if( numberOfCustomer == MAXCUSTOMER ) {
			throw new InvalidInputException("No more space!!!Can not add this Customer to Record System.");
		}
		if( searchCustomer(customer.getId()) != null ) {
			throw new InvalidInputException("This customer exist in Record System!!!Can not add again.");
		}
	}
	
	public void checkIsExistCustomer(int id)throws InvalidInputException {
		if( searchCustomer(id) == null ) {
			throw new InvalidInputException("This customer is not exist in Record System!!!");
		}
	}	
	
	public String toString() {
		String toString = "Customer record system:";
		for (int i = 0; i < numberOfCustomer; i++) {
			toString =  toString + "\n---------------------------------------------------------\n" + customerArray[i].toString();
		}
		return toString;
	}	

	public void checkIsValidCarRegistration(String registration)throws InvalidInputException {
		for(int i = 0;i < numberOfCustomer; i++) {
			if (customerArray[i].searchCar(registration) != null) {
				throw new InvalidInputException("This car registration exist in Record System!!!Can not be used again.");
			}	
		}	
	}
	
	public Customer findCustomer()throws InvalidInputException {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the Customer ID.");
		String token = keyboard.nextLine();
		if ("".equals(token)){
			throw new InvalidInputException("No ID entered.\n");
		}	
		int id = Integer.parseInt(token);
		checkIsExistCustomer(id);
		Customer aimCustomer = searchCustomer(id);
		return aimCustomer;
	}
	
}	
	
	
	
