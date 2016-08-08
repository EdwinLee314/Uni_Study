import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	static ArrayList<Integer> address = new ArrayList<Integer>(); 
	static int way;
	static int setFrame;
	static int hit = 0;
	static int compulsory = 0;
	static int capacity = 0;
	static int conflict = 0;
	static Set[] sets;
	
	public static void main(String[] args) {
		String trace = args[0];
		String cacheSize = args[1]; //unit is byte
		String blockSize = args[2];	//unit is byte
		String associativity = args[3]; //0 for full associativity, 1 for direct mapped
		String replacement = args[4]; // 0 for Random; 1 for LRU
		
		int cachesize = Integer.parseInt(cacheSize);
		int blocksize = Integer.parseInt(blockSize);
		int blockFrame = cachesize / blocksize; //the number of blocks in the cache
		int associativities = Integer.parseInt(associativity);
		int policy = Integer.parseInt(replacement);

		if(associativities == 0){
			way = 1;
		}
		else if(associativities == 1){
			way = blockFrame;
		}
		else{
			way = associativities;
		}
		
		setFrame = blockFrame / way; // number of blocks in set
		sets = new Set[way];
		for(int i = 0; i < way; i++){
			sets[i] = new Set(setFrame);  //Initial sets 
		}
				
		//read file
		File file = new File(trace);
		BufferedReader inputStream = null;
		try{
			if(file.exists()){
				inputStream = new BufferedReader(new FileReader(file),40*1024*1024);
				String id = "";
				while((id =inputStream.readLine()) != null) {
					//read id line
					String g = id.substring(2);
					long temp = Long.parseLong(g,16);
					int blockNo =  (int)(temp / blocksize);
					address.add(blockNo);
				}
				inputStream.close();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		//read address line
		for(int i: address){
			int blockLocate = i % way ;
			Set s = sets[blockLocate]; //find the map set
			
			if(s.inCache(i)){
				s.LRUcount(i);
				hit++;
			}
			else{
				if(s.visitBefore(i)){
					if(cacheFull())
						capacity++;
					else
						conflict++;
				}
				else{
					compulsory++;
				}
				
				if(s.setFull()){
					s.replace(policy, i);
				}
				else{
					s.emptyBlock(i);
				}
			}		
		}
		//output
		System.out.println("Compulsory Miss is: " + compulsory);
		System.out.println("Conflict Miss is: " + conflict);
		System.out.println("Capacity Miss is: " + capacity);
		System.out.println("Total Miss is: " + (capacity +conflict + compulsory));
		System.out.println("Hit is: " + hit);
		System.out.println("Way is: " + way);
		System.out.println("BlockNo in set  is: " + setFrame );
		System.out.println("BlockNo  is: " + blockFrame);
		
	}
	public static boolean cacheFull(){
		for(Set s: sets){
			if(s.setFull() == false){
				return false;
			}
		}
		int n =0;
		for(Set s:sets){
			System.out.print(n);
			System.out.print(s);
			n++;
		}
		System.out.println("cache is Full");
		return true;
	}
}
