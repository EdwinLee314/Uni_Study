import java.util.AbstractList;
import java.util.ArrayList;

public class Analyst {
	public static ArrayList<Item> analyzeRelatedProduct(Product p,AVLTree<Product> pList,AVLTree<Receipt> rList){
		ArrayList<Receipt> receipts = findReceipts(p,rList);
		
		ArrayList<Item> countItems = countItems(receipts);
	
		ArrayList<Item> topFive = topFive(countItems, p);
		
		ArrayList<Item> items = setDescriptionItemList(pList,topFive);
		sortItemsBySalesCount(items);
		return items;
		
	}
	
	public static ArrayList<Receipt> findReceipts(Product product, AVLTree<Receipt> rList){
		ArrayList<Integer> receipts = product.getReceipts();
		ArrayList<Receipt> related = new ArrayList<Receipt>();
		for(Integer i:receipts){
			Receipt key = new Receipt(i);
			//System.out.println( rList.search(key).getData());
			Receipt index = rList.search(key).getData();
			related.add(index);
		}
		return related;
	}
	
	
	public static ArrayList<Item> countItems(ArrayList<Receipt> receipts){
		AVLTree<Item> items = new AVLTree<Item>();
		for(Receipt r :receipts){
			for(String s: r.getItems()){
				Item tItem = new Item(s);
				if(items.search(tItem) == null){
					items.insert(tItem);
				}
				else{
					items.search(tItem).getData().addCount();
				}
			}
		}
		ArrayList<Item> tList = items.toArrayList();
		sortItemsBySalesCount(tList);
		return tList;
	}
	
	public static ArrayList<Item> topFive(ArrayList<Item> items,Product p){
		String id = p.getId();
		ArrayList<Item> topFive = new ArrayList<Item>();
		int tie = 0;
		int counter = 0;
		for(Item i:items){
			if(id.equals(i.getId()) == false){
				if(counter > 4){
					if(i.getCount() == tie){
						//if it has same tie
						topFive.add(i);
						counter++;
					}
					else{
						return topFive;
					}
				}
				else{
					//the 5st is the final tie
					topFive.add(i);
					tie = i.getCount();
					counter++;
				}
			}
		}
		return topFive;
	}
	
	public static ArrayList<Item> setDescriptionItemList(AVLTree<Product> pList, ArrayList<Item> items){
		for(Item i: items){
			String id = i.getId();
			Product key = new Product(id);
			Product aim = pList.search(key).getData();
			String description = aim.getDescription();
			i.setDescription(description);
			key = null;
			aim = null;
			description = null;
		}
		return items;
	}
	
	public static void sortItemsBySalesCount(ArrayList<Item> items){
		int left = 0;
		int right = items.size() -1;
		quickSortSub(items, left, right);
	}
	
	public static void quickSortSub(ArrayList<Item> items,int left,int right){
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
	
	public static int partition(ArrayList<Item> items,int left,int right){
		int mid = (left + right) /2;
		Item pivot = items.get(mid);
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
	
	private static void swap (AbstractList<Item> container, int i, int j){
		Item temp = container.get(i);
		container.set(i, container.get(j));
		container.set(j, temp);
	}
}

