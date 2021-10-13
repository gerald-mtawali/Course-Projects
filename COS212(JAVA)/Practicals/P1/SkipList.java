import java.util.Random;

// level maxLevel is the bottom level and is the one with the full linked list 

@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<? super T>>
{

	public int maxLevel;						// max height of any element 
	public SkipListNode<T>[] root;
	private int[] powers;						//determines the level of the node being added 
	private Random rd = new Random();

	SkipList(int i)
	{
		maxLevel = i;
		root = new SkipListNode[maxLevel];
		powers = new int[maxLevel];
		for (int j = 0; j < i; j++)
			root[j] = null;
		choosePowers();
		rd.setSeed(1230456789);
	}

	SkipList()
	{
		this(4);
	}

	public void choosePowers()
	{
		powers[maxLevel-1] = (2 << (maxLevel-1)) - 1;
		for (int i = maxLevel - 2, j = 0; i >= 0; i--, j++)
			powers[i] = powers[i+1] - (2 << j);
	}

	public int chooseLevel()
	{
		int i, r = Math.abs(rd.nextInt()) % powers[maxLevel-1] + 1;
		for (i = 1; i < maxLevel; i++)
		{
			if(r < powers[i])
				return i-1;
		}
		return i-1;
	}

	public boolean isEmpty()
	{
		//Your code goes here
		return root[0] == null;
		
	}

	/* insert function*/
	public void insert(T key)
	{
		//Your code goes here
		SkipListNode<T>[] curr = new SkipListNode[maxLevel];
		SkipListNode<T>[] pred = new SkipListNode[maxLevel];
		SkipListNode<T> newNode;
		int lvl, i;
		curr[maxLevel-1] = root[maxLevel-1];
		pred[maxLevel-1] = null;
		for(lvl=maxLevel-1; lvl>=0; lvl--){
			while(curr[lvl] != null && curr[lvl].key.compareTo(key) <0){
				pred[lvl] = curr[lvl];
				curr[lvl] = curr[lvl].next[lvl];			// go to the next node if smaller 
			}
			if(curr[lvl] != null && curr[lvl].key.equals(key))
				return;										// do not include duplicates 
			if(lvl > 0)
				if(pred[lvl] == null){					
					curr[lvl-1] = root[lvl-1];
					pred[lvl-1] =null;
				}
				else{
					curr[lvl-1] = pred[lvl].next[lvl-1];
					pred[lvl-1] =pred[lvl];
				} 
		}
		lvl = chooseLevel();
		newNode = new SkipListNode<T>(key, lvl+1);
		for(i =0; i <= lvl;i++){
			newNode.next[i] = curr[i];
			if(pred[i] == null)
				root[i] = newNode;
			else pred[i].next[i] = newNode;
		}
	}	

	public T first()
	{
		
		//Your code goes here
		//if(isEmpty()) throw new IllegalStateException("There are no elements in the Skip List");
		return root[0].key;
	}
	
	
	public T last()
	{
		//Your code goes here 
		SkipListNode<T> curr = root[0];
		// the last element is the last node/element on level 0 
		while(curr.next[0] !=null){
			curr = curr.next[0];
		}
		return curr.key;
	}	
	

	/*
	 * iterate through keys in the list, starting on the first level 
	 * if node.key > key then go down a level lvl--
	 * else if node.key <= key then go to the next node on the same level 
	 * */
	public T search(T key)
	{
		//Your code goes here
		int level;
		SkipListNode<T> pred, curr;						// predecessor and current nodes of the skip list 
		/* search for the level to go on start on the max level, and then check to see if each level 
		 * is */ 
		for(level = maxLevel-1; level >=0 && root[level] == null; level--);
		pred = curr= root[level];
		while(true)
		{
			if(key.equals(curr.key))
				return curr.key;
			else if(key.compareTo(curr.key)< 0){		// if smaller go down 
				if(level == 0)
				{
					return null;
				}
				else if(curr == root[level])
					curr = root[--level];			// go down a level 
				else curr = pred.next[--level];
				
			}
			else{									// go to the next element on the current level 
				pred = curr;
				if(curr.next[level] != null)		// go to the next nonnull node 
					curr = curr.next[level];
				else{								// or list on a lower level 
					for(level--; level >= 0 && curr.next[level]==null;level--);
					if(level >= 0)
						curr = curr.next[level];
					else return null;
				}	
			}
		}
	}
}
