import java.util.ArrayList;



public class AVLTree< T extends Comparable<T>> {
	class Node
	{
	   private T data;
	   private Node leftChild;
	   private Node rightChild;
	   private int height;

	   public Node(T data)
	   {
	      this.data = data;
	      leftChild = null;
	      rightChild = null;
	      height = 0;
	   }

	   public void setLeftChild(Node leftChild)
	   {
	      this.leftChild = leftChild;
	   }

	   public void setRightChild(Node rightChild)
	   {
	      this.rightChild = rightChild;
	   }

	   public void setData(T data)
	   {
	      this.data = data;
	   }

	   public void setHeight(int height)
	   {
	      this.height = height;
	   }

	   public Node getLeftChild()
	   {
	      return leftChild;
	   }

	   public Node getRightChild()
	   {
	      return rightChild;
	   }

	   public T getData()
	   {
	      return data;
	   }

	   public int getHeight()
	   {
	      return height;
	   }
   
	}
	
	private Node root;
	
	public AVLTree(){
		root = null;
	}
	
	public void displayElements(){
	      System.out.println("=== Inorder traversal ===");
	      displaySubtreeInOrder(root);
	      System.out.println("===");
	}

	private void displaySubtreeInOrder(Node localRoot){
	      if (localRoot != null) {
	         displaySubtreeInOrder(localRoot.getLeftChild());
	         System.out.println("data: " + localRoot.getData());
	         displaySubtreeInOrder(localRoot.getRightChild());
	      }
	}
	
	public Node search(T key) {
		// Tree is empty
	    if (root == null){
	    	return null;
		}
	    // Tree is not empty
	    Node p = root;
	    while (true){
	    	if(p == null|| key.compareTo((T) p.getData()) == 0){
				break;
			}
	    	else if(key.compareTo((T) p.getData())< 0){
	            p = p.getLeftChild();
	        }
	        else{
	            p = p.getRightChild();
	        }
	    }
	    return p;
	}
	
	public ArrayList<T> toArrayList(){
		 ArrayList<T> list = new ArrayList<T> ();
		 toArrayListInOrder(root,list);
		 return list;
	}
	
	public void toArrayListInOrder(Node localRoot, ArrayList<T> list){
		if(localRoot != null){
			toArrayListInOrder(localRoot.getLeftChild(), list);
			list.add(localRoot.getData());
			toArrayListInOrder(localRoot.getRightChild(), list);
		}
	}
	/*
	 public ArrayList<T> toArrayList(){
    	 ArrayList<T> list = new ArrayList<T> ();
    	 Queue<Node> queue = new ArrayDeque<Node>();
    	 queue.add(root);
    	 while(!queue.isEmpty()){
    		 list.add(queue.peek().getData());
    		 Node twoLinkNode = queue.poll();
    		 if(twoLinkNode.leftChild != null){
    			 queue.add(twoLinkNode.leftChild);
    		 }
    		 if(twoLinkNode.rightChild != null){
    			 queue.add(twoLinkNode.rightChild);
    		 }
    	 }
    	 return list;
     }
	*/
	public void insert(T data){
		// Case: tree is empty
		if (root == null){
			root = new Node(data);
			return;
		}
		// Case: tree is not empty
		Node p = root;
		// move p down as far as we can
		while(true){
			if (data.compareTo((T) p.getData()) <= 0 && p.getLeftChild() != null){
				p = p.getLeftChild();
			}
			else if (data.compareTo((T) p.getData()) > 0 && p.getRightChild() != null){
				p = p.getRightChild();
			}
			else{
				break;
			}
		}
		// insert new node to as a child of node p
		Node newNode= new Node(data);
		if (data.compareTo((T) p.getData()) <= 0 ){
			p.setLeftChild(newNode);
		}
		else{
			p.setRightChild(newNode);
		}

		rebalanceInsertionPath(newNode);
	}
	
	private void rebalanceInsertionPath(Node node){
		// Get the path nodes from the root to the node
		ArrayList<Node> nodes = getAllNodesOnPath(node.getData());

		// Reverse the list to get the path from the node to the root
		nodes = reverse(nodes);


		// Set the heights of the nodes along the path
		for(int i = 0; i < nodes.size(); i++)
		{
			setHeight(nodes.get(i));
		}

		// Rebalance all the nodes (subtrees) on path from inserted node
		// to the root
		for(int i = 1; i < nodes.size(); i++)
		{
			Node pp = nodes.get(i);
			Node pc = nodes.get(i-1);
			if (pp.getLeftChild() == pc)
			{
				Node localRoot = rebalance(pc);
				pp.setLeftChild(localRoot);
			}
			else
			{
				Node localRoot = rebalance(pc);
				pp.setRightChild(localRoot);
			}
		}

		// .. This is done outside the loop because root does not
		// .. have a parent
		root = rebalance(root);
	}
	
	private Node rebalance(Node node){
		setHeight(node);
	   int diff = getHeightDifference(node);

	   if(diff == 2){
		   if(getHeightDifference(node.getLeftChild()) == -1){
	            node = leftRightRotation(node);
	       }
	       else{
	            node = rightRotation(node);
	       }
	   }
	   else if(diff == -2){
	       if(getHeightDifference(node.getRightChild()) == 1){
	            node = rightLeftRotation(node);
	       }
	       else{
	            node = leftRotation(node);
	       }
	   }

	   return node;
	}
	
	private ArrayList <Node> getAllNodesOnPath(T key){
	// The tree is not empty and the key is in the tree
		ArrayList <Node> nodes = new ArrayList<Node>();
		// Add root as the first node
		// Otherwise, it will not be included
		nodes.add(root);

		Node p = root;
		while (p != null &&  key.compareTo(p.getData()) != 0){
			if (key.compareTo((T) p.getData())< 0){
				p = p.getLeftChild();
			}
			else{
				p = p.getRightChild();
			}
			nodes.add(p);
		}

		return nodes;
	}
	
	private int getHeightDifference(Node node){
	      Node leftChild = node.getLeftChild();
	      Node rightChild = node.getRightChild();
	      int leftHeight = leftChild == null? -1: leftChild.getHeight();
	      int rightHeight = rightChild == null? -1: rightChild.getHeight();

	      return leftHeight - rightHeight;
	}

	private Node  rightRotation(Node g){
		// Message to let know about the rotation
		//System.out.println(">> right rotate at: " + g.getData());

	    Node p = g.getLeftChild();
	    Node x = p.getRightChild();   // x is right child of p
	    p.setRightChild(g);
	    g.setLeftChild(x);
	    setHeight(g);
	    setHeight(p);
	    return p;
	}

	private Node leftRotation(Node g){
	    // System.out.println(">> left rotate at: " + g.getData());

	     Node p = g.getRightChild();
	     Node x = p.getLeftChild();    // x is left child of p
	     p.setLeftChild(g);
	     g.setRightChild(x);
	     setHeight(g);
	     setHeight(p);
	     return p;
	}

	private Node rightLeftRotation(Node g){
	     //System.out.println(">> right-left rotate at: " + g.getData());

	     Node p = g.getRightChild();
	     g.setRightChild(rightRotation(p));
	     return leftRotation(g);
	}

	private Node leftRightRotation(Node g){
	     //System.out.println(">> left-right rotate at: " + g.getData());

	     Node p = g.getLeftChild();
	     g.setLeftChild(leftRotation(p));
	     return rightRotation(g);
	}

	private void setHeight(Node node){
	     Node leftChild = node.getLeftChild();
	     Node rightChild = node.getRightChild();
	     int leftHeight = leftChild == null? -1: leftChild.getHeight();
	     int rightHeight = rightChild == null? -1: rightChild.getHeight();

	     if (leftHeight >= rightHeight){
	         node.setHeight(leftHeight + 1);
	     }
	     else{
	         node.setHeight(rightHeight + 1);
	     }
	}	

	public boolean remove(T key){
	      // Find the node to be deleted
	    Node deleted = root;
	    Node parent = null;
	    while (true){
			if (deleted != null && key == deleted.getData()){
				break;
			}
	      	else if (key.compareTo((T) deleted.getData())< 0){
	            parent = deleted;
	            deleted = deleted.getLeftChild();
	        }
	        else if (key.compareTo((T) deleted.getData())> 0){
	            parent = deleted;
	            deleted = deleted.getRightChild();
	        }
	    }

	    if (deleted == null){ // The node is not found
	         return false;
	    }
	    else if (deleted.getLeftChild() == null && deleted.getRightChild() == null){
	         deleteLeafNode(deleted, parent);
	         return true;
	    }
	    else if ( (deleted.getLeftChild() != null && deleted.getRightChild() == null)
	                || (deleted.getLeftChild() == null && deleted.getRightChild() != null)){
	         deleteNodeWithOneChild(deleted, parent);
	         return true;
	    }
	    else{
	         deleteNodeWith2Children(deleted, parent);
	         return true;
	    }
	}
	
	private void deleteLeafNode(Node deleted, Node parent){
	      if (deleted == root){
	         root = null;
	      }
	      else if (parent.getLeftChild() == deleted)
	      {
	         parent.setLeftChild(null);
	         rebalanceDeletionPath(parent);
	      }
	      else
	      {
	         parent.setRightChild(null);
	         rebalanceDeletionPath(parent);
	      }
	}

	private void deleteNodeWithOneChild(Node deleted, Node parent){
	      // Get the child of the deleted node
	      Node child = null;
	      if (deleted.getLeftChild() != null){
	         child = deleted.getLeftChild();
	      }
	      else{
	         child = deleted.getRightChild();
	      }

	      // Let root or parent of deleted node point to the child
	      if (deleted == root){
	         root = child;
	      }
	      else if(deleted == parent.getLeftChild()){
	         parent.setLeftChild(child);
	         rebalanceDeletionPath(parent);
	      }
	      else{
	         parent.setRightChild(child);
	         rebalanceDeletionPath(parent);
	      }
	   }

	   private void deleteNodeWith2Children(Node deleted, Node parent){
	      Node largest = deleted.getLeftChild(); // find largest in left subtree
	      Node parentOfLargest = deleted;
	      while (largest.getRightChild() != null){
	         parentOfLargest = largest;
	         largest = largest.getRightChild();
	      }

	      deleted.setData(largest.getData());    // copy largest data to "deleted node"

	      if (largest.getLeftChild() == null) {   // delete largest
	         deleteLeafNode(largest, parentOfLargest);
	      }
	      else{
	         deleteNodeWithOneChild(largest, parentOfLargest);
	      }
	   }
	   
	   private void rebalanceDeletionPath(Node parent){

	      // Get the path nodes from the root to the parent
	      ArrayList<Node> nodes = getAllNodesOnPath(parent.getData());

	      // Reverse the list to get the path from parent to root
	      nodes = reverse(nodes);

	      // Set the heighs of the nodes along the path
	      for(int i = 0; i < nodes.size(); i++){
	         setHeight(nodes.get(i));
	      }

	      // Rebalance the nodes along the path
	      for(int i = 1; i < nodes.size(); i++){
				Node pp = nodes.get(i);
				Node pc = nodes.get(i-1);

	         if (pp.getLeftChild() == pc){
	            pp.setLeftChild(rebalance(pc));
	         }
	         else{
	            pp.setRightChild(rebalance(pc));
	         }
	      }

	      // .. This is done outside the loop because root does not
	      // .. have a parent
	      root = rebalance(root);
	   }
	   
	   private ArrayList<Node> reverse(ArrayList<Node> nodes){
		   ArrayList<Node> temp = new ArrayList<Node>();
		   int i = nodes.size() -1;
		   int j = i;
		   for(;j>-1;j--){
			  temp.add(nodes.get(j)); 
		   }
		   return temp;
	   }
}
