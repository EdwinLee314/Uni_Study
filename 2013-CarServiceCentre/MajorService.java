/**
* Use for Junqi Li 16554835 IOO Assignment
*/

public class MajorService extends Service {
	private double hour;
	private double estimateCost;
	
	public MajorService(int id,String discription) {
		super(id,discription);
		hour = 0.0;
		estimateCost = 0.0;
	}
	
	public MajorService(int id, String discription, double hour) {
		super(id, discription);
		this.hour = hour;
	}
	
	public void checkIsValidHour(double hour)throws InvalidInputException {
		if(hour <= 0.0) {
			throw new InvalidInputException("Time for Major Service should be greater than 0.0 hours.");
		}
	}
	
	public double calculateCost(double hour)throws InvalidInputException {
		checkIsValidHour(hour);
		this.hour = hour;
		estimateCost = 100 + 50 * hour;
		return estimateCost;
	}

	public String toString() {
		String toString = "";
		if( hour == 0.0) {
			toString = getClass().getName() + "[" + "Service ID: " + getId() + ", Description: " + getDiscription() + " ]";
		}
		else {
			toString = getClass().getName() + "[" + "Service ID: " + getId() + ", Description: " + getDiscription() + ", Hours: " + hour + ", Cost: " + estimateCost + " ]";
		}
		return toString;
    }
}
