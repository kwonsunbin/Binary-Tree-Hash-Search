import java.util.*;

public class LinearHash {
	ArrayList<LinkedList<String>> hashTable;
	int M;
	int splitIndex;
	int numOfInsertedWord;

	public LinearHash(int HTinitSize)
	{
		hashTable = hashTableInit(HTinitSize);
		M = HTinitSize;
		splitIndex = 0;
		numOfInsertedWord = 0;
	}

	public ArrayList<LinkedList<String>> hashTableInit(int HTinitSize){
		ArrayList<LinkedList<String>> hashTable = new ArrayList<LinkedList<String>>();
		for (int i = 0; i < HTinitSize; i++) {
			hashTable.add(null);
		}
		return hashTable;
	}
	
	public int insertUnique(String word)
	  {
		int index = getEntryIndex(word);
		if(getContent(index) == null) {
			hashTable.set(index, createNewContentLinkedList(word)) ;
			numOfInsertedWord++;
		}else {
			if (lookup(word)>0){
				return -1;
			}else {
				getContent(index).add(word);
				collision();
				numOfInsertedWord++;
				if (getContent(index)!= null) {
					sortContentLink(getContent(index));
				}
			}

		}
		return index;
	  }
	
	
	public void collision() {
		hashTable.add(null) ;
		rehash();
		splitIndex ++;
		if ( splitIndex == M) {
			M = M*2;
			splitIndex =0;
		}
	}
	
	
	public void rehash() {
		
		LinkedList<String> toOrigin = new LinkedList<String>();
		LinkedList<String> toNew = new LinkedList<String>();
		if (getContent(splitIndex) == null) {
			return;
		}else {
			Iterator<String> it = getContent(splitIndex).iterator();
			while(it.hasNext()) {
				String temp = it.next();
				if ( (int)MyUtil.ELFhash(temp, M*2)<M) {
					toOrigin.add(temp);
					sortContentLink(toOrigin); 
				}else {
					toNew.add(temp);
					sortContentLink(toNew);  
				}
			}
			if(toOrigin.isEmpty()) {
				hashTable.set(splitIndex, null);
			}else {
				hashTable.set(splitIndex, toOrigin) ;
			}
			if(toNew.isEmpty()) {
				hashTable.set(splitIndex+M, null) ;
			}else {
				hashTable.set(splitIndex+M, toNew) ;
			}
		
		}
		
	}

	public LinkedList<String> createNewContentLinkedList(String word) {
		LinkedList<String> content = new LinkedList<String>();
		content.add(word);
		return content;
	}

	public void sortContentLink(LinkedList<String> content) {
		Collections.sort(content);
	}
	
	public LinkedList<String> getContent(int index){
		return  hashTable.get(index);
	}
	
	public int lookup(String word)
	{
		int index = getEntryIndex(word);
		if (getContent(index)==null) {
			return 0;
		}
		if (getContent(index).contains(word)) {
			return getContent(index).size();
		}else {
			return -1* getContent(index).size();
		}
	}

	int getEntryIndex(String word) {
		if ( ((int)MyUtil.ELFhash(word, M)) >= splitIndex ) {
			return ((int)MyUtil.ELFhash(word, M));
		}else {
			return ((int)MyUtil.ELFhash(word, M*2));
		}
	}
	
	

	public int wordCount() {
		return numOfInsertedWord;
	}

	public int emptyCount() {
		Iterator<LinkedList<String>> it = hashTable.iterator();
		int cnt = 0;
		while(it.hasNext()) {
			if( it.next() == null ) {
				cnt++;
			}
		}
		return cnt ;
	}

	public int size() {
		return M + splitIndex;
	} 

	public void print() {
		Iterator<LinkedList<String>> itTable = hashTable.iterator();
		int indexOfTable = 0;
		while(itTable.hasNext()) {
			String words = ""; 
			LinkedList<String> temp = itTable.next();
			if( temp == null) {
				System.out.println("["+indexOfTable + ":"+words+"]");
				indexOfTable ++;
				continue;
			}else {
				Iterator<String> itContent = temp.iterator();
				while(itContent.hasNext()) {
					String tmp = itContent.next();
					if(tmp != null) {
						words+= tmp+" ";
					}
				}
			}
			words= words.substring(0,words.length()-1);
			System.out.println("["+indexOfTable + ": "+words+"]");
			indexOfTable ++;
		}
	} 
}
