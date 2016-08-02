/**
* Use for Junqi Li 16554835 IOO Assignment
*/

public class Car {
	private String registration;
	
	private int numberOfService;
	private final int MAXSERVICE= 50;
	private Service[] serviceArray = new Service[MAXSERVICE];
	
	public Car(String registration)throws InvalidInputException {
		checkIsValidRegistration(registration);
		this.registration = registration;
	}

	public void checkIsValidRegistration(String registration)throws InvalidInputException{
		if(registration.length() != 6) {
			throw new InvalidInputException("Invalid registration length.You must enter 6 characters.");
		}
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}
	
	public String toString() {
		String toString = getClass().getName() + "[" + " Registration: " + registration + "]";
		if(numberOfService > 0) {
			toString = toString + "\n\nThis car has these services:";
			for(int i = 0;i < numberOfService; i++) {
				toString = toString + "\n" + serviceArray[i].toString();
			}
		}
		return toString + "\n**************************\n";	
	}
	
	public void addService(Service service) throws InvalidInputException {
		if ( numberOfService == MAXSERVICE ) {
			for(int i = 0;i < numberOfService; i++) {
				serviceArray[i] = serviceArray[i+1];
			}
			serviceArray[numberOfService] = null;
			numberOfService--;
		}	
		checkIsValidService(service);
		serviceArray[numberOfService] = service;
		numberOfService++;
	}	
	
	public void removeService(int id)throws InvalidInputException {
		for(int i = 0; i< numberOfService; i++) {
			if(id == serviceArray[i].getId()){
				for(int j = i;j < numberOfService; j++) {
					serviceArray[i] = serviceArray[i+1];
				}
				serviceArray[numberOfService] = null;
				numberOfService--;
				break;
			}
		}	
	}
	
	public void checkIsValidService(Service service)throws InvalidInputException {
		if ( searchService(service.getId()) != null ) {
			throw new InvalidInputException("This service id exist in this car!!!Can not use same ID.");
		}
	}
	
	public Service searchService(int id) {
		Service aimService = null;
		for(int i = 0; i< numberOfService; i++) {
			if(id == serviceArray[i].getId()){
				aimService = serviceArray[i];
				break;
			}
		}
		return aimService;
	}		

}
