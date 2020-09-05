public class TNode {

	//private TNode parent;
	private TNode left;
	private TNode right;
	private String element;
	private int accessCount;
	private int frequencyCount;
	private int height;
	
	public TNode(String key) {

		//parent = null;
		left = null;
		right = null;
		element = key;
		frequencyCount = 1;
		accessCount = 0;
		height =  1;
		
	}

	public TNode(String key, TNode par, TNode left, TNode right) {
		
		element = key;
		//parent = par;
		this.left = left;
		this.right = right;
		frequencyCount = 1;
		accessCount = 0;
		
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setHeight(int h)
	{
		height = h;

	}

	int checkBalance() 
	  {	
		  int diff = left.getHeight() - right.getHeight();
		  return diff;
		  
	  }
	
	public void setKey(String key) {
		
		element = key;
		
	}

	public String getKey() {
		
		return element;
		
	}
	
	public int getFC(){
		
		return frequencyCount;
		
	}
	
	public int getAC() {
		
		return accessCount;
		
	}

	public TNode getLeft() {
		
		return this.left;
		
	}

	public TNode getRight() {
		
		return this.right;
	
	}

	public void setRight(TNode right) {
		
		this.right = right;
		
	}

	public void setLeft(TNode left) {
		
		this.left = left;
		
	}
	
	public void updateFC() {
		
		frequencyCount++;
		
	}
	
	public void updateAC() {
		
		accessCount++;
		
	}
	
	public void resetAC() {
		
		accessCount = 0;
		
	}
	
	public void resetFC() {
		frequencyCount = 1;
	}
	
	public void printNodeInfo() {
	
		System.out.println("["+element+":"+frequencyCount+":"+accessCount+"]");
		
	}

	public String toString() {
		
		return this.getKey().toString();
		
	}
}


