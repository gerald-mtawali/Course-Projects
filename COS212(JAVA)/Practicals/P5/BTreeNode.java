@SuppressWarnings("unchecked")
class BTreeNode<T extends Comparable<T>> {
	boolean leaf;
	int keyTally;										// current number of keys 
	Comparable<T> keys[];								// an array of keys 
	BTreeNode<T> references[];							// an array of children 
	int m;
	static int level = 0;

	// Constructor for BTreeNode class
	public BTreeNode(int order, boolean leaf1)
	{
    	// Copy the given order and leaf property
		m = order;
    	leaf = leaf1;
 
    	// Allocate memory for maximum number of possible keys
    	// and child pointers
    	keys =  new Comparable[2*m-1];
   		references = new BTreeNode[2*m];
 
   		// Initialize the number of keys as 0
   		keyTally = 0;
	}

	// Function to print all nodes in a subtree rooted with this node
	public void print()
	{
		level++;
		if (this != null) {
			System.out.print("Level " + level + " ");
			this.printKeys();
			System.out.println();

			// If this node is not a leaf, then 
        		// print all the subtrees rooted with this node.
        	if (!this.leaf)
			{	
				for (int j = 0; j < 2*m; j++)
    			{
        			if (this.references[j] != null)
					this.references[j].print();
    			}
			}
		}
		level--;
	}

	// A utility function to print all the keys in this node
	private void printKeys()
	{
		System.out.print("[");
    	for (int i = 0; i < this.keyTally; i++)
    	{
        	System.out.print(" " + this.keys[i]);
    	}
 		System.out.print("]");
	}

	////// You may not change any code above this line //////

	////// Implement the functions below this line //////
	
	int numOfChilds = 2*m;
	int numOfKeys = (2*m-1);
	// Function to insert the given key in tree rooted with this node
	public BTreeNode<T> insert(T key)
	{
        // Your code goes here
		/*Insert cases: 
		 * 	- Tree is empty only one key is placed within the node 
		 * 	- a Node is full => split the node by ceil(m/2) so that there are two new separate nodes 
		 * 		the key that is being inserted then becomes the first key in the root node, with the other 
		 * 		one moving up by one, this new head key has two pointers from it <|6|^|12|>  
		 * 	- splitting a node=> order the elements including the element that we're adding 
		 * 		e.g. insert 15 |12|29|41|57| => 12,15,29,41,57 => |12|15|29||(ceil(m/2)) & |41|57|| 
		 * 		then take the last key in the first BTreeNode and create the new node with the single 
		 * 		key(29)
		 * 
		 * 	- when splitting  a node with a root that still has room, create the two split nodes 
		 * 		similar to the way it was described above, then depending on which keys it is between 
		 * 		the last key of the first split is inserted into the root at certain position between 
		 * 		whichever keys hold that reference 
		 * */
		
		// empty node, just add to the head key 
		if(keyTally == 0)
		{
			keys[0] = key;
			this.leaf = true;
			keyTally += 1;
			return this;
		}
		// fill up the nodes for the tree 
		else
		{
			if(this.isFull())
			{
				// create a tmp variable 
				BTreeNode<T> tmp1 = new BTreeNode<T>(m, false);
				tmp1.references[0] = this;
				tmp1.split(0, this);
				
				// the new root has two children, due to the split
				// choose the child that will become the new root
				int i =0;
				if(tmp1.keys[0].compareTo(key) <0)
					i++;
				tmp1.references[i].insertNotFull(key);
				//System.out.println("The number of keys is: "+keyTally);
				return tmp1;
			}
			else
			{
				this.insertNotFull(key);
				//System.out.println("The number of keys is: "+keyTally);
				return this;
			}	
		}
	}

	// Function to traverse all nodes in a subtree rooted with this node
	public void traverse()
	{
    	// Your code goes here
		/*A glorified search of all the nodes in the tree 
		 * start at the root 
		 * */	
		int i = 0;
		for(i = 0; i < keyTally ;i++){
			// if it is not the leaf then before print(), traverse the subtree root with
			// child we are currently on 
			if(leaf == false)
				this.references[i].traverse();
			System.out.print("  "+ keys[i]);
			//printKeys();
		}
		// print subtree rooted with last child 
		if(leaf == false)
			references[i].traverse();
	}
	
	/*Helper Function */
	// split child node 
	private void split(int i, BTreeNode<T> node)
	{
		BTreeNode<T> tmp = new BTreeNode<T>(node.m, node.leaf);
		tmp.keyTally = m-1;
		
		// copy the last 
		for(int j = 0; j < m-1; j++)
			tmp.keys[j] = node.keys[j+m];
		
		// copy last m children of node to tmp1
		if(node.leaf == false){
			for(int j = 0; j < m; j++)
				tmp.references[j] = node.references[j+m];
		}
		// reduce the number of keys in node 
		node.keyTally = m-1;
		
		for(int j = keyTally; j >= i+1; j--)
			references[j+1] = references[j];
		
		// link the new child to this node
		references[i+1] = tmp;
		
		// a key from node will move to this node, find the location of the 
		// new key and move all greater keys by one space ahead 
		for(int j = keyTally-1; j >= i; j--)
			keys[j+1] =  keys[j];
		
		// copy middle key to this node 
		keys[i] = node.keys[m-1];
		keyTally++;
		//System.out.println("The number of keys is: "+keyTally);
		
	}
	// insert non full node 
	private void insertNotFull(T Key)
	{
		int i = keyTally-1;
		if(leaf == true)				// if we're inserting into a leaf 
		{
			// find the location for the new key to be inserted 
			// move all keys that are larger than by one to the right 
			while(i >= 0 && keys[i].compareTo(Key) > 0){
				keys[i+1] = keys[i];
				i--;
			}
			// insert the key at the new found location 
			keys[i+1] = Key;
			keyTally += 1;
		}
		else							// if we are inserting into a nonleaf 
		{
			while(i >= 0 && keys[i].compareTo(Key) > 0)
				i--;
			// check if the child node is full 
			if(references[i+1].isFull())
			{
				// split the child node if it is full 
				split(i+1, references[i+1]);
				// after the split, the last key in the first new child node goes up 
				if(keys[i+1].compareTo(Key) < 0)
					i++;
			}
			references[i+1].insertNotFull(Key);
		}
	}
	protected boolean isFull()
	{
		return (this.keyTally == (2*m-1));
	}
}
