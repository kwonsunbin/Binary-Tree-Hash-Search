public class BST { 

  private TNode rootNode;
  int size = 0;
  int totalFC = 0;
  int totalAC = 0;
  
  public BST() 
  {
	  rootNode = null;
  }
  
  public TNode getRoot()
  {
	  return rootNode ;
  }
  
  
  
  public TNode createTNode(String key){
	  TNode newNode = new TNode(key);
	  return newNode;
  }
  
  void insert(String key) {
	  rootNode = insert(rootNode, key);
  }

  public TNode insert(TNode currentNode, String key)

  { 
	  if (currentNode == null) {
		  currentNode = createTNode(key);
		  size ++;
		  totalFC++;
		}
		else if (key.compareTo(currentNode.getKey())<0) {
			currentNode.setLeft(insert(currentNode.getLeft(), key));
		}else if (key.compareTo(currentNode.getKey())>0) {
			currentNode.setRight(insert(currentNode.getRight(), key));
		}else if (key.compareTo(currentNode.getKey())==0) {
			currentNode.updateFC();
			totalFC++;
		}
		
		return currentNode;
  }
 
  
  public boolean find(String key) 

  { 
	 if (find(rootNode,key)==null) {return false;}
	 else {return true;}
  }
  
  public TNode find(TNode currentNode, String key)
  {
	if (currentNode == null) {
		return null;
	}else if(key.compareTo(currentNode.getKey())==0) {
		currentNode.updateAC();
		totalAC++;
		return currentNode;
	}else if (key.compareTo(currentNode.getKey())<0) {
		currentNode.updateAC();
		totalAC++;
		return find(currentNode.getLeft(),key);
	}else {
		currentNode.updateAC();
		totalAC++;
		return find(currentNode.getRight(),key);
	} 
  }
  
  public int size() 

  { 	
	  return size;
  }

  public int sumFreq() 

  {
	  return totalFC;
  }
  public int sumProbes() 

  {
	  return totalAC;
  }
  public void resetCounters()

  { 
	  totalFC = size;
	  totalAC = 0;
	  
	  resetTraversal(rootNode);
  }
  
  public void resetTraversal(TNode currentNode) {
      
	  if (currentNode == null) {
      	return ;
      }
      else {
    	currentNode.resetAC();
    	currentNode.resetFC();
    	resetTraversal(currentNode.getLeft());
    	resetTraversal(currentNode.getRight());
      }
	}
public void printTraversal(TNode currentNode) {
      
	  if (currentNode == null) {
      	return ;
      }
      else {
    	printTraversal(currentNode.getLeft());
    	currentNode.printNodeInfo();
    	printTraversal(currentNode.getRight());
      }
	}

  public void print() 

  {
	  printTraversal(rootNode);
  }
  

}

