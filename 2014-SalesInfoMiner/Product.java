import java.util.ArrayList;

public class Product implements Comparable<Product> {
	private String id;
	private String description;
	private int salesCount = 0;
	private ArrayList<Integer> receipts = new ArrayList<Integer>();
	private ArrayList<Item> items;
	
	public Product(String id, String description){
		this.id = id;
		this.description = description;
	}
	
	public Product(String id){
		this.id = id;
		this.description = "";
	}
	
	public String getId(){
		return id;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getSalesCount(){
		return salesCount;
	}
	
	public String toString(){
		String a = "";
		int count = 1;
		if(items != null){
			for(Item i: items){
				a = a + count + ": " + i.toString();
				count++;
			}
		}
		return  "Product: " + description + "\n" +
				"ID: " + id + "\n" + 
				"Sales Count: " + salesCount +"\n" +
				"This product was purchased most often with: \n" + a;
	}
	
	public void addCount(){
		salesCount= salesCount + 1;
	}
	
	public ArrayList<Integer> getReceipts() {
		return receipts;
	}

	public void addReceipts(int receipt) {
		this.receipts.add(receipt);
	}
	
	public boolean equals(Object o){
		Product other = (Product) o;
		if(this.id.equals(other.getId()))
			return true;
		else
			return false;	
	}
	
	public int compareTo(Product other){
		return this.id.compareTo(other.getId());
	}
	
	public int sortCompareTo(Product other){
		if(this.salesCount >other.getSalesCount())
			return -1;
		if(this.salesCount <other.getSalesCount())
			return 1;
		
		if(this.description.compareTo(other.getDescription()) > 0)
			return 1;
		if(this.description.compareTo(other.getDescription()) < 0)
			return -1;
		
		return 0;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

		
}