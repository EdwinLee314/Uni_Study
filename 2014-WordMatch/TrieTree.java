import java.io.PrintWriter;
import java.util.LinkedList;


public class TrieTree {
	private int SIZE = 26;//26 letters caseInsensitive
	private TrieNode headRoot; //
	private TrieNode tailRoot;
	
	TrieTree(){
		headRoot = new TrieNode();
	}
	
	private class TrieNode{
		private char val;// the store char
		private int num;//how many words go through this node
		private boolean isEnd;//end of a string
		private boolean visited;// for DFS
		private TrieNode[] son;
		private TrieNode father = null;
		
		TrieNode(){
			num = 1;
			son = new TrieNode[SIZE];
			isEnd = false;
			visited = false;
		}
	}
	
	public void insert(String str){
		if(str == null|| str.length() == 0){
			return;
		}
		TrieNode node = headRoot;
		char[] letters = str.toCharArray();
		for(int i = 0,len = str.length(); i < len ; i++){
			int pos = letters[i] - 'a';
			if(node.son[pos] == null){
				node.son[pos] = new TrieNode();
				node.son[pos].val =letters[i];
				node.son[pos].father = node; //for back search
			}else{
				node.son[pos].num++; // for 'countPrefix(String prefix)
			}
			node = node.son[pos];
		}
		node.isEnd = true;
	}
	
	// DFS.Use stack.Like a 26-nary tree.  
    public void printAllWords(PrintWriter save) {  
        TrieNode rootNode = headRoot;  
        if (headRoot == null){  
            return;  
        }  
        LinkedList<TrieNode> list = new LinkedList<TrieNode>();  
        LinkedList<String> words = new LinkedList<String>();
        for (int i = 0; i < SIZE; i++) {  
            TrieNode node = rootNode.son[i];  
            if (node != null) {  
                list.addLast(node);  
                while (!list.isEmpty()) {  
                    TrieNode curNode = list.getLast();  
                    TrieNode firstChild = firstChildOf(curNode);  
                    while (firstChild != null) {  
                        list.addLast(firstChild);  
                        firstChild = firstChildOf(firstChild);// DFS.  
                    }  
                    TrieNode strEnd = list.getLast();  
                    if (strEnd.isEnd) {  
                        words.addLast(printLinkedList(list));  
                    }  
                    list.removeLast();            
                }                 
            }  
            list.clear();  
        } 
        
        //insertion sort
        String[] wordsList = words.toArray(new String[0]);
        words.clear();
        int left = 0;
        int right = wordsList.length -1;
        for(int i= left +1; i < right;i++){
        	String value = wordsList[i];
        	int j = i;
        	while(j > left && wordsList[j-1].compareTo(value)> 0){
        		wordsList[j] = wordsList[j-1];
        		j--;
        	}
        	wordsList[j] = value;
        }
        for(String t: wordsList){
        	save.println(t);
        }
       
    }  
    
    //print each node in preOrder.  
    public void preTraverse(TrieNode node){  
        if(node!=null){  
            System.out.print(node.val+"-");  
            for(TrieNode child:node.son){  
                preTraverse(child);  
            }  
        }  
          
    }  
    
   
    // get the first unvisited child of a node.  
    private TrieNode firstChildOf(TrieNode node) {  
        if (node == null)  
            return null;  
        for (int i = 0; i < SIZE; i++) {  
            TrieNode tmp = node.son[i];  
            if (tmp != null && !tmp.visited) {  
                tmp.visited = true;  
                return tmp;  
            }  
        }  
        return null;  
    }  
   
    //print a word
    private String printLinkedList(LinkedList<TrieNode> list) {  
        if (list == null || list.size() == 0){  
            return null;  
        }  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0, len = list.size(); i < len; i++) {  
            sb.append(list.get(i).val);  
        }  
        return sb.toString();  
    } 
    
   private boolean wildCardMatch(String pattern, String str){
    	if (pattern == null || str == null){
    		return false;
    	}
    	if(pattern.length() != str.length()){
    		return false;
    	}
    	boolean match = false;
    	for(int i= 0; i < str.length(); i++){
    		if(pattern.charAt(i) != '?'){
    			if(pattern.charAt(i) == str.charAt(i)){
    				match = true;
    			}
    			else{
    				return false;
    			}
    		}
    	}
    	return match;
   }
   
   public void search(String pattern){
	   int len = pattern.length();
	     
	   TrieNode rootNode = headRoot;  
       if (headRoot == null){  
           return;  
       }   
       LinkedList<TrieNode> list = new LinkedList<TrieNode>();  
       LinkedList<String> words = new LinkedList<String>();
       
       for (int i = 0; i < SIZE; i++) {  
           TrieNode node = rootNode.son[i];  
           if (node != null) {  
               list.addLast(node);  
               while (!list.isEmpty()) {  
                   TrieNode curNode = list.getLast();  
                   TrieNode firstChild = firstChildOf(curNode);  
                   while (firstChild != null) {  
                       list.addLast(firstChild);  
                       firstChild = firstChildOf(firstChild);// DFS.  
                   }  
                   TrieNode strEnd = list.getLast();  
                   if (strEnd.isEnd) {  
                	   if(printLinkedList(list).length() == len){
                		   words.addLast(printLinkedList(list)); 
                	   }
                   }  
                   list.removeLast();            
               }                 
           }  
           list.clear();  
       } 
       LinkedList<String> wordList = new LinkedList<String>();
       for(String t: words){
    	   if(wildCardMatch(pattern, t)){
    		   wordList.addLast(t);
    	   }
       }
       words.clear();
       if(wordList.size() == 0){
    	   System.out.println("No words in the lexicon matched your input");
       }
       else{
	       //insertion sort
	       String[] wordsOrderList = wordList.toArray(new String[0]);
	       words.clear();
	       int left = 0;
	       int right = wordsOrderList.length -1;
	       for(int i= left +1; i < right;i++){
	       		String value = wordsOrderList[i];
	       		int j = i;
	       		while(j > left && wordsOrderList[j-1].compareTo(value)> 0){
	       			wordsOrderList[j] = wordsOrderList[j-1];
	       			j--;
	       		}
	       		wordsOrderList[j] = value;
	       }
	       for(String t: wordsOrderList){
	       		System.out.println(t);
	       }
       }
       
   }
	//complete matching search
 	public boolean accurateSearch(String str){
		if(str == null|| str.length() == 0){
			return false;
		}
		TrieNode node = headRoot;
		char[] letters = str.toCharArray();
		for(int i =0,len = str.length(); i < len; i++){
			int pos= letters[i]-'a';
			if(node.son[pos]!=null){
				node= node.son[pos];
			}else{
				return false;
			}
		}
		return node.isEnd;
	}
	
	//suffix wildcard search
	public void suffixSearch(String str){
		if(str == null|| str.length() == 0){
			return;
		}
		TrieNode node = headRoot; //to find the last accurate word
		char[] letters = str.toCharArray(); //to store search key
		int count = 0; //count the wildcard number
		String word = "";//to store the accurate part of the word
		for(int i =0,len = str.length(); i < len; i++){
			if(letters[i] == '?'){
				count++;
			}
			else{
				int pos= letters[i]-'a';
				word = word + letters[i];
				if(node.son[pos]!=null){
					node= node.son[pos];
				}else{
					System.out.println("No words in the lexicon matched your input.");
					return;
				}
			}
		}
		findMatch(count,node,word);
	}
	public String printWord(LinkedList<TrieNode> list) {  
	        if (list == null || list.size() == 0){  
	            return "";  
	        }  
	        StringBuilder sb = new StringBuilder();  
	        for (int i = 0, len = list.size(); i < len; i++) {  
	            sb.append(list.get(i).val);  
	        }  
	        return sb.toString();  
	}  
	 
	private void findMatch(int limit, TrieNode startNode,String word ){
		if(startNode == null){
			return;
		}
		LinkedList<TrieNode> list = new LinkedList<TrieNode>();
		System.out.println("wildcard is:" + limit);
		if(limit > 0){ //has wildcard
			for (int i = 0; i < SIZE; i++) {  
				
	            TrieNode node = startNode.son[i]; //used one wildcard
	                     
	            if (node != null) {  
	                list.addLast(node);
	                
	                while (!list.isEmpty()) {  
	                	if(limit> 1){
		                    TrieNode curNode = list.getLast();  
		                    TrieNode firstChild = firstChildOf(curNode);  //used two wildcard
		                    list.addLast(firstChild);  
		                    
		                    int count = 2; //
		                    while (firstChild != null && count < limit ) {  
		                    	System.out.println("C is:" + count);
		                    	firstChild = firstChildOf(firstChild);// DFS.  
		                        list.addLast(firstChild); 
		                        count++;
		                    } 
	                	}
	                	
	                    TrieNode strEnd = list.getLast();  
	                    if (strEnd.isEnd) {  
	                        System.out.println(word+ printWord(list));  
	                    }  
	                    list.removeLast();  
	                }  
	            } 
	            
	            list.clear();  
	        }  
		}
		System.out.println(word);  
	}
}

