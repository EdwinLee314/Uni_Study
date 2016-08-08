import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SalesInfoMiner {

	static AVLTree<Product> productList = new AVLTree<Product>();
	static AVLTree<Receipt> receiptList = new AVLTree<Receipt>();
	
	public static void main(String[] args) {
		String productFile = args[0];
		String transactionFile = args[1];
		String outputFile = args[2];
		String errorFile = args[3];
		//read product
		ProductReader pReader= new ProductReader();
		pReader.readProductFile(productFile,errorFile,productList);
		
		//read Receipt
		ReceiptReader rReader= new ReceiptReader();
		rReader.readTransactionFile(transactionFile, errorFile,receiptList, productList);

		//data mining
		ArrayList<Product> pList = productList.toArrayList();
		for(Product p:pList){
			ArrayList<Item> items = Analyst.analyzeRelatedProduct(p,productList,receiptList);
			p.setItems(items);
			
		}
		
		sortProductBySalesCount(pList);
		//output
		for(Product p:pList){
			try{
				FileWriter fw=new FileWriter(outputFile,true);  
		        PrintWriter pw=new PrintWriter(fw); 
		        pw.println(p);
				fw.close();
			} catch (IOException e) {  
	            e.printStackTrace();  
	        } 
		}
		
	}
	
	public static void sortProductBySalesCount(ArrayList<Product> items){
		int left = 0;
		int right = items.size() -1;
		quickSortSub(items, left, right);
	}
	
	public static void quickSortSub(ArrayList<Product> items,int left,int right){
		if(left >= right){
			return;
		}
		else
		{
			int pivotIndex = partition(items,left,right);
			quickSortSub(items,left,pivotIndex -1);
			quickSortSub(items, pivotIndex + 1, right);
		}
	}
	
	public static int partition(ArrayList<Product> items,int left,int right){
		int mid = (left + right) /2;
		Product pivot = items.get(mid);
		swap(items, mid, right);
		while(left < right){
			while(left < right && items.get(left).sortCompareTo(pivot) < 0){
				left++;
			}
			
			if(left < right){
				swap(items , left, right);
				right--;
			}
			while(right > left && items.get(right).sortCompareTo(pivot) > 0){
				right--;
			}
			
			if(right > left){
				swap(items, left, right);
				left++;
			}
		}
		return left;
	}
	
	private static void swap (ArrayList<Product> container, int i, int j){
		Product temp = container.get(i);
		container.set(i, container.get(j));
		container.set(j, temp);
	}
}