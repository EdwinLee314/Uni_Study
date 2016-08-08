import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ProductReader {
	public void readProductFile(String productFile,String errorFile, AVLTree<Product> pList){
		
			File file = new File(productFile);
			BufferedReader inputStream = null;
			try{
				if(file.exists()){
					inputStream = new BufferedReader(new FileReader(file),40*1024*1024);
					int countLine = 1;
					String id = "";
						
						while((id =inputStream.readLine()) != null) {
							//read id line
							checkProductId(id, countLine, productFile);
							checkProductIdUnique(id, countLine, productFile,pList);
							countLine++;
							
							//read description line
							String description = inputStream.readLine();
							checkProductDescription(description, countLine, productFile); 
							countLine++;
							
							//add product to productList
							Product tProduct = new Product(id, description);
							pList.insert(tProduct);
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
	
	public void checkProductId(String input,int line,String file) throws Exception {
		boolean pre =(input.length() == 13);
		char []num = input.toCharArray();
		for(char e:num){
			if(Character.isDigit(e) == false){
				pre = false;
			}
		}	
		if(pre == false){
			String msg =	"ERROR: Invalid Product ID\n" + 
							"Line " + line + " in "+ file +"\n" +
							"Product " + input + " does not consists of 13 digits\n" +
							"Check product id\n";
			throw new Exception(msg);				
		}
	}
	
	public void checkProductIdUnique(String input,int line, String file, AVLTree<Product> pList) throws Exception {
		Product key = new Product(input);
		if(pList.search(key) != null){
				String msg =	"ERROR: Product Id is not Unique\n" + 
						"Line " + line + " in "+ file +"\n" +
						"Product " + input + " is not unique\n" +
						"Check product id\n";
				throw new Exception(msg);
		}	
	}
	
	public void checkProductDescription(String input,int line, String file) throws Exception {
		boolean pre =(input.length() == 0);
		if(pre){
			String msg =	"ERROR: Product description is empty\n" + 
							"Line " + line + " in "+ file +"\n" + 
							"Check product description\n";
			throw new Exception(msg);				
		}
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
