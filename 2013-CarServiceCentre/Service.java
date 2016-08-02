/**
* Use for Junqi Li 16554835 IOO Assignment
*/

public abstract class Service {
	private int id;
	private String discription;
	
	public Service(int id, String discription) {
		this.id = id;
		this.discription = discription;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDiscription() {
		return discription;
	}
	
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	public String toString() {
		return getClass().getName() + "[" + " Service id: " + getId() + ", Description: " + getDiscription() + " ]";
	}
	
	public abstract double calculateCost(double hour)throws InvalidInputException;

}