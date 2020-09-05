public class AVL extends BST
{
  private TNode rootNode;
  int size = 0;
  int totalFC = 0;
  int totalAC = 0;
  
  public AVL() 
  {	
	  rootNode = null;
  }
  
  public TNode getRoot()
  {
	  return rootNode ;
  }
  
  public void insert(String key)
  {
	  rootNode = insert(rootNode, key);
  }
  
  public TNode insert(TNode currentNode, String key)
  {
	  if (currentNode == null) {
		  size ++;
		  totalFC++;
		  return createTNode(key);
	  }else if (key.compareTo(currentNode.getKey())<0) {
			currentNode.setLeft(insert(currentNode.getLeft(), key));
			
	  }else if (key.compareTo(currentNode.getKey())>0) {
			currentNode.setRight(insert(currentNode.getRight(), key));
			
	  }else if (key.compareTo(currentNode.getKey())==0) {
			currentNode.updateFC();
			totalFC++;
	  }
	  
	  
	  int balance = checkBalance(currentNode.getLeft(), currentNode.getRight());
		if (balance > 1) {
			if (checkBalance(currentNode.getLeft().getLeft(), currentNode.getLeft().getRight()) > 0) {
				currentNode = rightRotate(currentNode);// LL Condition
			} else {
				currentNode.setLeft(leftRotate(currentNode.getLeft())); // LR Condition
				currentNode = rightRotate(currentNode);
			}
		} else if (balance < -1) {
			if (checkBalance(currentNode.getRight().getLeft(), currentNode.getRight().getRight()) < 0) {
				currentNode = leftRotate(currentNode);// RR Condition
			} else {
				currentNode.setRight(rightRotate(currentNode.getRight()));// RL Condition
				currentNode = leftRotate(currentNode);

			}
		}
		
		if (currentNode.getLeft() != null) {
			currentNode.getLeft().setHeight(calHeight(currentNode.getLeft()));
		}
		if (currentNode.getRight() != null) {
			currentNode.getRight().setHeight(calHeight(currentNode.getRight()));
		}
		currentNode.setHeight(calHeight(currentNode));
		
	  return currentNode;
  }
  
  private int calHeight(TNode currentNode) {
	  int ans = 1;
	  if (currentNode.getLeft() == null && currentNode.getRight()==null) {
		  ans = 1;
	  } else if (currentNode.getLeft() == null) {
		  ans = currentNode.getRight().getHeight()+1;
	  } else if (currentNode.getRight() == null) {
		  ans =  currentNode.getLeft().getHeight()+1;
	  } else {
		  ans =  1+Math.max(currentNode.getRight().getHeight(),currentNode.getLeft().getHeight());
	  }
	  
	  return ans;
  }
  
  private int checkBalance(TNode leftNode, TNode rightNode) {
		if((leftNode == null) && (rightNode == null)) {	return 0;}
		else if (leftNode == null) {return -1 * (rightNode.getHeight() ); } 
		else if (rightNode == null) { return leftNode.getHeight() ;} 
		else { return leftNode.getHeight() - rightNode.getHeight(); }
  }
  

  private TNode leftRotate(TNode currentNode) {
	  TNode newRootNode = currentNode.getRight();
	  currentNode.setRight(currentNode.getRight().getLeft());
	  newRootNode.setLeft(currentNode);
	  return newRootNode;
  }

  private TNode rightRotate(TNode currentNode) {
	  TNode newRootNode = currentNode.getLeft();
	  currentNode.setLeft(currentNode.getLeft().getRight());
	  newRootNode.setRight(currentNode);
	  return newRootNode;
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

