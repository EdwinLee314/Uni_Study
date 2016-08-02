/**
* Use for Junqi Li 16554835 IOO Assignment
*/

public class ExpressService extends Service {
	private double hour;
	private double cost;
	
	public ExpressService(int id, String discription) {
		super(id, discription);
	}
	
	public ExpressService(int id, String discription, double hour) {
		super(id, discription);
		this.hour = hour;
	}
	
	public void checkIsValidHour(double hour)throws InvalidInputException {
		if(hour <= 0.0 && hour >2.0) {
			throw new InvalidInputException("Time for expressService should be within 2.0 hours and can not be negative or 0.");
		}
	}
	
	public double calculateCost(double hour)throws InvalidInputException{
		this.hour = hour;
		checkIsValidHour(hour);
		if (hour <= 1.0) {
			cost = 100.0;
		}
		else{
			cost = 150.0;
		}
		return cost;
	}
	
	public String toString() {
		String toString = "";
		if( hour == 0.0) {
			toString = getClass().getName() + "[" + "Service ID: " + getId() + ", Description: " + getDiscription() + " ]";
		}
		else {
			toString = getClass().getName() + "[" + "Service ID: " + getId() + ", Description: " + getDiscription() + ", Hour: " + hour + ", Cost: " + cost + " ]";
		}
		return toString;
    }
}
