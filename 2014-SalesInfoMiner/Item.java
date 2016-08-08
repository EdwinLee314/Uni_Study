public class Item  implements Comparable<Item>{
	private String id;
	private String description ="";
	private int count = 0;
	
	public Item(String id){
		this.id = id;
		count++;
	}
	
	public Item(String id,int count){
		this.id = id;
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCount() {
		return count;
	}

	public void addCount() {
		this.count++;
	}
	
	public String toString() {
		return "(" + id + ") " + description + " [" + count +"]\n";
	}
	
	public boolean equals(Object o){
		Item other = (Item) o;
		if(this.id.equals(other.getId()))
			return true;
		else
			return false;
		
	}
	
	public int compareTo(Item other){
		return this.id.compareTo(other.getId());
	}
	
	public int sortCompareTo(Item other){
		if(this.count > other.getCount())
			return -1;
		if(this.count < other.getCount())
			return 1;
		
		if(this.description.compareTo(other.getDescription()) > 0)
			return 1;
		if(this.description.compareTo(other.getDescription()) < 0)
			return -1;
		
		return 0;
	}

	
}