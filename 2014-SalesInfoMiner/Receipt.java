import java.util.ArrayList;

public class Receipt implements Comparable<Receipt> {
	private int receiptNo;
	private int itemNum;
	private ArrayList<String> items = new ArrayList<String>();
	
	public Receipt(int receiptNo, int itemNum){
		this.receiptNo = receiptNo;
		this.itemNum = itemNum;	
	}
	
	public Receipt(int receiptNo){
		this.receiptNo = receiptNo;
		this.itemNum = 0;	
	}
	public void setItems(ArrayList<String> items) {
		this.items = items;
	}
	
	public int getReceiptNo(){
		return receiptNo;
	}
	
	public int getItemNum(){
		return itemNum;
	}
	
	public ArrayList<String> getItems(){
		return items;
	}
	
	public String toString(){
		String a="";
		for(String e:items){
			a = a + e + "\n";
		}
		return receiptNo + "\n" + itemNum + "\n" + a;
	}
	
	public boolean equals(Object o){
		Receipt other = (Receipt) o;
		if(this.receiptNo == other.getReceiptNo())
			return true;
		else
			return false;
		
	}
	
	public int compareTo(Receipt other){
		if(this.receiptNo > other.getReceiptNo())
			return 1;
		if(this.receiptNo < other.getReceiptNo())
			return -1;
		return 0;
	}
}