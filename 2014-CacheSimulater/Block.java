public class Block {
	int address = -1;
	int count = 0;
	
	public Block(){
		address = -1;
		count = 0;
	}
	public Block(int adr){
		this.address = adr;
		count = 0;
	}
	
	public void setAddress(int a){
		this.address = a;
	}
	
	public int getAddress(){
		return address;
	}
	
	public void addCount(){
		count++;
	}
	
	public void minCount(){
		count--;
	}
	
	public int getCount(){
		return count;
	}
	
	public void setCount(int a){
		this.count = a;
	}
	
	public String toString(){
		return	"Address: " + address  + 
				"Count: " + count +"\n";
	}
}