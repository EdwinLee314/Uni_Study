import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ReceiptReader {
	public void readTransactionFile(String transactionFile,String errorFile, AVLTree<Receipt> rList, AVLTree<Product> pList){
		try{
			File file = new File(transactionFile);
			if(file.exists()){
				BufferedReader inputStream = new BufferedReader(new FileReader(transactionFile),40*1024*1024);
				int countLine = 1;
				String receipt = null;
				while((receipt =inputStream.readLine()) != null) {
					//read receiptId
					checkFormat01(receipt, countLine,transactionFile);
					int receiptNo = checkPositive01(receipt,countLine,transactionFile);
					countLine++;
					//read number of items
					String items = inputStream.readLine();
					checkFormat02(items, countLine,transactionFile); 
					int itemNum = checkPositive02(items,countLine,transactionFile);
					countLine++;
					
					//store tempt Receipt
					Receipt tReceipt = new Receipt(receiptNo, itemNum);
					
					//read items
					ArrayList<String> itemsList = new ArrayList<String>();
					for(int i = 0; i < itemNum; i++){
						String line = null;
						if((line =inputStream.readLine()) == null || line.length() == 0 || line.substring(0,1).equals("R")){ //end of file or empty or next receipt
							 checkProductList(countLine,transactionFile);
						}
						else{
							checkProductId(line,countLine,transactionFile);
							checkProductIdDuplicate(line,countLine,itemsList,transactionFile);
							checkProductIdExist(line, countLine,transactionFile,pList,receiptNo);//count product salesNo and add related receipt						
						}
						itemsList.add(line);
						countLine++;
					}
					
					//add itemsList
					tReceipt.setItems(itemsList);
					
					//add receipt to receiptList
					rList.insert(tReceipt);
					//System.out.println(tReceipt);
				}
				
				inputStream.close();
			}
			else {
				System.out.println("File is not existed.");
			}
		}catch (Exception e) {
			writeException(e,errorFile);
			System.exit(1);
		}	
	}
	
	public void checkFormat01(String input,int line,String file) throws Exception{
		boolean pre = false;
		if(input.length() > 15)
		{
			String test =input.substring(0,15);
			pre = (test.equals("Receipt number:"));
		}
		if(!pre){
			String msg =	"ERROR: Format mistake\n" + 
							"Line " + line + " in "+ file +"\n" +
							"Check file format 'Receipt number:'\n";
			throw new Exception(msg);				
		}
	}
	
	public void checkFormat02(String input,int line, String file) throws Exception{
		boolean pre = false;
		if(input.length() > 16)
		{
			String test =input.substring(0,16);
			pre = (test.equals("Number of items:"));
		}
		if(!pre){
			String msg =	"ERROR: Format mistake\n" + 
							"Line " + line + " in "+ file +"\n" +
							"Check file format 'Number of items:'\n";
			throw new Exception(msg);				
		}
	}
	
	public int checkPositive01(String input,int line, String file) throws Exception{
		int check = -1;
		if(input.length() > 16)
		{
			String test = input.substring(16,input.length());
			check = Integer.parseInt(test);
		}
		if(check < 1){
			String msg =	"ERROR: Receipt number is not a positive integer\n" + 
							"Line " + line + " in "+ file +"\n" +
							"Check receipt number\n";
			throw new Exception(msg);	
		}
		return check;
	}
	
	public int checkPositive02(String input,int line, String file) throws Exception{
		int check = -1;
		if(input.length() > 17)
		{
			String test = input.substring(17,input.length());
			check = Integer.parseInt(test);
		}
		if(check < 1){
			String msg =	"ERROR: The number of items is not a positive integer\n" + 
							"Line " + line + " in "+ file +"\n" +
							"Check the number of items\n";
			throw new Exception(msg);	
		}
		return check;
	}
	
	public void checkProductIdDuplicate(String input,int line,List<String> tempList, String file) throws Exception{
		for(String e:tempList){
			if(e.equals(input)){
				String msg =	"ERROR: Product Id has duplicates in a sale transaction\n" + 
						"Line " + line + " in "+ file +"\n" +
						"Product" + input + " has duplicates\n" +
						"Check product id\n";
				throw new Exception(msg);
			}
		}
	}
	
	public void checkProductList(int line, String file)throws Exception {
		String msg =	"ERROR: Nor enough Product ID's listed for a transaction\n" + 
						"Line " + line + " in "+ file +"\n" +
						"Change the number of items\n";
		throw new Exception(msg);	
	}
	
	public void checkProductId(String input,int line,String file) throws Exception{
		boolean pre =(input.length() == 13);
		char []num = input.toCharArray();
		for(char e:num){
			if(Character.isDigit(e) == false){
			pre = false;
			}
		}	
		if(pre==false){
			String msg =	"ERROR: Invalid Product ID\n" + 
							"Line " + line + " in "+ file +"\n" +
							"Product" + input + " does not consists of 13 digits\n" +
							"Check product id\n";
			throw new Exception(msg);				
		}
	}
	
	public void checkProductIdExist(String input,int line, String file, AVLTree<Product> pList,int receiptNo)throws Exception {
		Product key = new Product(input);
		if(pList.search(key) == null){
				String msg =	"ERROR: The Product ID does not exist in the product file\n" + 
								"Line " + line +" in "+ file +"\n" +
								"Product id:" + input + " does not exist in the product file\n" +
								"Check the Product ID\n";
				throw new Exception(msg);	
		}
		Product aim = pList.search(key).getData();
		aim.addCount();
		aim.addReceipts(receiptNo);
	}
	
	public void writeException(Exception ex,String errorFile){
		try{
			FileWriter fw = new FileWriter(errorFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(ex.getMessage());
			bw.flush();
			bw.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}