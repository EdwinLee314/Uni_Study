import java.util.HashSet;
import java.util.Random;

public class Set {
	HashSet<Integer> history = new HashSet<Integer>();
	Block[] blocks;
	int blockNo;
	static Random rand = new Random();
	
	public Set(int blockNo){
		this.blockNo = blockNo;
		blocks = new Block[blockNo];
		for(int i = 0; i< blockNo; i++){
			blocks[i] = new Block(-1);
		}
	}
	
	public boolean inCache(int adr){
		for(Block b:blocks){
			if(b != null && b.getAddress() == adr ){
				return true;
			}
		}
		return false;
	}
	
	public void LRUcount(int adr){ 
		int count = 0;
		for(Block b:blocks){
			if(b != null && b.getAddress() == adr ){
				count = b.getCount();
				b.setCount(-1);
			}
		}
		for(Block b: blocks){
			if(b !=null && b.getCount() < count){
				b.addCount();
			}
		}
	}	
	
	public boolean visitBefore(int adr){
		for(int l: history){
			if(l== adr){
				return true;
			}
		}
		return false;
	}
	
	public boolean setFull(){
		for(Block b: blocks){
			if( b.getAddress()== -1 ){
				return false;
			}
		}
		return true;
	}
	
	public void emptyBlock(int adr){
		for(Block b: blocks){
			if(b.getAddress() == -1){
				b.setAddress(adr);
				break;
			}
		}
		toHistory(adr);
	}

	public void replace(int policy, int adr){
	
		if(policy == 1){
			int max = -1;
			for(Block b: blocks){
				if(b.getCount() > max){
					max = b.getCount();
				}
			}
			for(Block b: blocks){
				if(b.getCount() == max){
					b.setAddress(adr);
					break;
				}
			}
		}
		if(policy == 0){
			int a = rand.nextInt(blockNo);
			blocks[a].setAddress(adr);
			
		}
		toHistory(adr);
	}
	
	public void toHistory(int a){
		history.add(a);
	}
	
	public String toString(){
		String s = "";
		int count = 1;
		for(Block a : blocks){
			s = s + count +" : "+ a.toString();
			count++;
		}
		return s;
	}
}
