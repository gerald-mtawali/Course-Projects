/*
Complete your details...
Name and Surname: Gerald Mtawali
Student/staff Number: 15264999
*/


public class DoubleThreadedBST<T extends Comparable<? super T>>
{
	private DTNode<T> root; // the root node of the tree
	private int numOfNodes;	// keeps track of the number of elements in the BST 

	/*
	TODO: You must complete each of the methods in this class to create
	your own double threaded BST. You may add any additional methods
	or data fields which you might need to accomplish your task. You
	must NOT change the signatures of any methods given to you with this
	class.
	*/
	
	public DoubleThreadedBST()
	{
		/*
		The default constructor
		*/
		root = null;
		numOfNodes = 0;
	}
	
	public DoubleThreadedBST(DoubleThreadedBST<T> other)
	{
		/*
		The copy constructor
		copies all the elements within the tree into another shallow copy 
		*/
		DTNode<T> currNode = other.getRoot();// start at the root of the tree we're copying
		while(currNode != null || (this.numOfNodes < other.numOfNodes))
		{
			//System.out.println("1 The current node is " + currNode.data );
			if(this.contains(currNode.data))
			{
				/*If the new tree does contain the element then we go to the left node first then we go onto the right */
				if(currNode.left != null && !this.contains(currNode.left.data))
					currNode = currNode.left;
				else if(currNode.right != null)
					currNode = currNode.right;
				else 
					break;
			}
			else 
			{
				/*if the new tree doesn't have the new element*/
				this.insert(currNode.data);				// insert the current node
				if(currNode.left != null && !currNode.hasLeftThread)
					currNode = currNode.left;
				else 
					currNode = currNode.right;
			}
		}
		
	}
	
	public DoubleThreadedBST<T> clone()
	{
		/*
		The clone method should return a copy/clone
		of this tree.
		*/
		
		if(this.isEmpty())
			return null;
		DoubleThreadedBST<T> retBST = new DoubleThreadedBST<T>(this);
		return retBST;
	}
	
	public DTNode<T> getRoot()
	{
		/*
		Return the root of the tree.
		*/
		return root;
	}
	

	public boolean insert(T element)
	{
		/*
		The element passed in as a parameter should be
		inserted into this tree. Duplicates are not allowed.
		Left and right threads in the corresponding branch 
		must be updated accordingly, as necessary. 
		If the insert was successfull, the method should 
		return true. If the operation was unsuccessfull, 
		the method should return false.
		
		NB: Do not throw an exception.
		
		Insert Cases: 
			- if the tree is empty only add it to the root 
			- if inserting to a left child (or left of left) set it's right pointer to its parent(current), if current has a left thread 
				then the leftchild.left = current.left, else it is equal to null 
			- if inserting to a right of left node set its left pointer to its parent and set its right pointer to its grandparent(prev)
			- if inserting to a right child(or right of right) set its left pointer to its parent(current), if current has a right thread 
			then rightchild.right = current.right 
			 - if inserting to a left of right node then set its left pointer to the GParent 
				
		insert to left if smaller than node, insert to right if greater than
		*/
		//System.out.println("**Inserting the value** "+ element);
		DTNode<T> newNode = new DTNode<T>(element);
		if(root == null)
		{
			root = newNode;
			numOfNodes++;
			return true;
		}
		if(contains(element))
		{
			return false;
		}
		DTNode<T> currNode = root, prev = null;
		while(currNode != null)
		{
			if(element.compareTo(currNode.data) < 0 && !currNode.hasLeftThread)
			{
				prev = currNode;
				currNode = currNode.left;
			}
			else if(element.compareTo(currNode.data) > 0 && !currNode.hasRightThread)
			{
				prev = currNode;
				currNode = currNode.right;
			}
			else if(element.equals(currNode.data))
			{
				return false;
			}
			else 
			{
				break;
			}
		}
		if(currNode != null)
		{
			// what if prev is null 
			// element is less than current node and current node is less than prev node
			if(element.compareTo(currNode.data) < 0 && currNode.data.compareTo(prev.data) < 0)
				insertLeft(currNode, element);
			// element is less than current node and current node is greater than prev node 
			else if(element.compareTo(currNode.data) < 0 && currNode.data.compareTo(prev.data) > 0)
				insertLR(currNode, prev, element);
			// element is greater than current node and current node is greater than prev node 
			else if(element.compareTo(currNode.data) > 0 && currNode.data.compareTo(prev.data) > 0)
				insertRight(currNode, element);
			// element is greater than current node and current node is less than prev node
			else if(element.compareTo(currNode.data) > 0 && currNode.data.compareTo(prev.data) < 0) 
				insertRL(currNode, prev, element);
			// else the element is equal? prev == null? 
			else 
			{
				//System.out.println("3. Duplicate Key entered");
				return false;
			}
		}
		else // currNode can only be null if it is the first or last element in the list being updated 
		{
			if(element.compareTo(prev.data) < 0)
				insertLeft(prev, element);
			else if(element.compareTo(prev.data) > 0)
				insertRight(prev, element);
			else
			{
				//System.out.println("3. Duplicate Key entered");
				return false;
			}
			
		}
		numOfNodes++;
		return true;
	}
	
	public boolean delete(T element)
	{
		/*
		The element passed in as a parameter should be
		deleted from this tree. If the delete was successfull,
		the method should return true. If the operation was
		unsuccessfull, the method should return false. Eg, if
		the requested element is not found, return false.
		
		You have to implement the mirror case of delete by merging 
		as discussed in the textbook. That is, for a deleted node,
		its right child should replace it in the tree and not its
		left child as in the textbook examples. Relevant left and
		right threads must be updated accordingly.
		
		NB: Do not throw an exception.
		*/
		if(root == null)
			return false;
		if(!contains(element))
			return false;
		else
		{
			//System.out.println("We are deleting the element:"+element);
			DTNode<T> currNode = root, prev = null;
			while(currNode != null && !element.equals(currNode.data))
			{
				//System.out.println("1. The current node is "+ currNode.data);
				//if(prev != null)
					//System.out.println("1. The previous node is "+prev.data);
				if(element.compareTo(currNode.data) < 0 && !currNode.hasLeftThread){
					prev = currNode;
					currNode = currNode.left;
				}
				else if(element.compareTo(currNode.data) > 0 && !currNode.hasRightThread){
					prev = currNode;
					currNode = currNode.right;
				}
				else 
					break;
			}
			if(currNode != null)
			{
				if(currNode.data.equals(element)){
					if(currNode == root)// if the currNode is root then prev == null 
						deleteByMerlot(currNode,null);
					else// all other cases, where prev will be a value
						deleteByMerlot(currNode, prev);
				}
				else 
					return false;
			}
			else
			{
				return false;
			}
			numOfNodes--;
			return true;
		}
	}
		
	public boolean contains(T element)
	{
		/*
		This method searches for the element passed in as a
		parameter. If the element is found, true should be 
		returned. If the element is not in the tree, the
		method should return false.
		*/
		if(root == null)return false;
		else
		{
			DTNode<T> currNode = root;
			while(currNode != null){
				//System.out.println("The current node is "+currNode.data);
				if(element.compareTo(currNode.data) < 0 ) // go to the left
				{
					if(!currNode.hasLeftThread)
						currNode=currNode.left;
					else currNode = null;
				}	
				else if(element.compareTo(currNode.data) > 0)// go to the right 
				{
					if(!currNode.hasRightThread)
						currNode = currNode.right;
					else 
						currNode = null;
				}
				else break;
			}
			if(currNode != null && currNode.data.equals(element))
				return true;
			else 
				return false;
		}
	}
	
	public String inorderReverse()
	{
		/*
		This method must return a string representation
		of the elements in the tree inorder, right to left. 
		This function must not be recursive. Instead, left 
		threads must be utilised to perform a depth-first 
		inorder traversal.
		
		Note that there are no spaces in the string, and
		the elements are comma-separated.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		The following string must be returned:
		
		E,D,C,B,A
		
		Perform an inorder search on the tree and then return a string array that is just the reverse of the in order traversal 
		*/
		if(isEmpty())
			return "";
		if(getNumberOfNodes() == 1)// only one element in the tree and thats the root 
			return (String)root.data;
		
		DTNode<T> currNode = getRightMost(root);
		String returnString, tempString;													// returnstring is what the program returns 
		returnString = "";
		tempString = " ";
		
		//tempString += currNode.data + " ";
		while(currNode != null )
		{
			//System.out.println("1. The current Node is "+currNode.data);
			
			if(currNode.right != null && !currNode.hasRightThread && !checkNode(currNode.right.data, tempString, false))
			{
				// the current node has a right child that has not been added to the string 
				currNode = currNode.right;
				continue;
			}
			else if(currNode.left != null && !checkNode(currNode.left.data, tempString, false))
			{
				// there is a left thread to follow,
				// if the left thread hasn't been added to the string
				tempString += currNode.data + " ";
				currNode = currNode.left;
				continue;
			}
			else if(currNode.left == null && !checkNode(currNode.data, tempString, false))
			{
				tempString += currNode.data+" ";
				currNode = currNode.left;
				continue;
			}
			else if(currNode.right != null && !currNode.hasRightThread && checkNode(currNode.right.data, tempString, false))
			{
				tempString += currNode.data + " ";
				currNode = currNode.left;
				continue;
			}
			else 
				break;
		}
		char [] retArray = tempString.toCharArray();
		//System.out.println("The temp string for inorder is "+tempString);
		for(int i = 1; i < retArray.length-1;i++)
		{
			if(retArray[i] == ' ')
				returnString += ",";
			else if(retArray[i] == ' ' && retArray[i-1] == ' ')
				returnString += "";
			else 
				returnString += retArray[i];
		}
		
		return returnString;
	}
	
	public String inorderReverseDetailed()
	{
		/*
		This method must return a string representation
		of the elements in the tree inorder, right to left. 
		This function must not be recursive. Instead, left 
		threads must be utilised to perform a depth-first 
		inorder traversal.
		
		Note that there are no spaces in the string, and
		the elements are comma-separated. Additionally,
		whenever a thread is followed during the
		traversal, a pipe symbol should be printed
		instead of a comma.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		In this tree, there is a thread linking the left
		branch of node E to node D, and a thread linking
		the left branch of node C to node B.
		
		This means the following string must be returned:
		
		E|D,C|B,A
		*/
		
		/*Similar to previous one: 
		 * 	- start at the root node 
		 * 	- go to the rightmost node
		 * 	- check to see if it has a right node if not then follow the left thread, since a thread was used to get there
		 * 			we print data+"|" 
		 * 	- whenever a left thread is followed then a pipe should be used
		 * 	- going to another child then we use data + " "
		 * */
		if(isEmpty())
			return "";
		if(getNumberOfNodes() == 1)// only one element in the tree and thats the root 
			return (String)root.data;
		/*
		 * Start at the root 
		 * Go to the rightmost node if there are any right or left nodes 
		 * 
		 * */ 
		DTNode<T> currNode = getRightMost(root);
		String returnString, tempString;													// returnstring is what the program returns 
		returnString = "";
		tempString = " ";
		
		//tempString += currNode.data + " ";
		while(currNode != null )
		{
			//System.out.println("1.5. The current Node is "+currNode.data);
			
			if(currNode.right != null && !currNode.hasRightThread && !checkNode(currNode.right.data, tempString, true))
			{
				// the current node has a right child that has not been added to the string 
				currNode = currNode.right;
				continue;
			}
			else if(currNode.left != null && currNode.hasLeftThread && !checkNode(currNode.left.data, tempString, true))
			{
				// there is a left thread to follow,
				// if the left thread hasn't been added to the string
				tempString += currNode.data + " | ";
				currNode = currNode.left;
				continue;
			}
			else if(currNode.left != null && !currNode.hasLeftThread && !checkNode(currNode.left.data, tempString, true))
			{
				// there is a left child to follow, that hasn't been added to the string 
				tempString += currNode.data + " ";
				currNode = currNode.left;
				continue;
			}
			else if(currNode.left == null && !checkNode(currNode.data, tempString, true))
			{
				tempString += currNode.data+" ";
				currNode = currNode.left;
			}
			else if(currNode.right != null && !currNode.hasRightThread && checkNode(currNode.right.data, tempString, true))
			{
				tempString += currNode.data + " ";
				currNode = currNode.left;
			}
			else 
				break;
		}
		char [] retArray = tempString.toCharArray();
		//System.out.println("The temp string for inorder detailed"+tempString);
		// can never end on a pipe so length -2
		for(int i = 1; i < retArray.length-1;i++)
		{
			if(retArray[i] == ' ')
			{
				if(retArray[i-1] == ' '){
					returnString += "";
					continue;
				}
				else if(retArray[i-1] != '|' && retArray[i+1] != '|')
					returnString += ",";
				else 
					returnString += "";
			}
			else 
				returnString += retArray[i];
		}
		return returnString;
	}
	
	public String preOrder()
	{
		/*
		This method must return a string representation
		of the elements in the tree in preorder, left to right. 
		This function must not be recursive. Instead, right 
		threads must be utilised to perform a depth-first 
		preorder traversal.
		
		Note that there are no spaces in the string, and
		the elements are comma-separated.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		The following string must be returned:
		
		B,A,D,C,E
		*/
		if(isEmpty()) return "";
		// check to see if root has any children
		if(root.left==null && root.right==null)
			return (String)root.data;
		
		// Starting at root we go to the rightmost node in the tree 
		DTNode<T> currNode = root;
		String returnString = "";
		String tempString = "";
		
		tempString += currNode.data+" ";
		while(currNode != null)
		{
			/*Pre order: 
			 * 	start at the currNode 
			 * 	if the currNode has a left child that is not a thread and is not in the tempString then go to the left 
			 *  add the left child to the tempString
			 *  if the left is a thread or is null and the right node is not a thread or null then go to the right, add that node 
			 *  and perform the step again 
			 * */
			if(currNode.left != null && !currNode.hasLeftThread && !checkNode(currNode.left.data, tempString, false))
			{
				currNode = currNode.left;
				tempString += currNode.data +" ";
				continue;
			}
			else if(currNode.right!= null && !currNode.hasRightThread && !checkNode(currNode.right.data, tempString, false))
			{
				currNode = currNode.right;
				tempString += currNode.data+" ";
				continue;
			}
			else
				currNode = currNode.right;
		}
		char [] retArray = tempString.toCharArray();
		
		for(int i = 0; i < retArray.length-1;i++)
		{
			if(retArray[i] == ' ')
				returnString += ",";
			else 
				returnString += retArray[i];
		}
		
		return returnString;
	}
	
	public String preorderDetailed()
	{
		/*
		This method must return a string representation
		of the elements in the tree in preorder, right to left. 
		This function must not be recursive. Instead, right 
		threads must be utilised to perform a depth-first 
		preorder traversal (see the last paragraph on page 240
		of the textbook for more detail on this procedure).
		
		Note that there are no spaces in the string, and
		the elements are comma-separated. Additionally,
		whenever a thread is followed during the
		traversal, a pipe symbol should be printed
		instead of a comma.
		
		If the tree looks like:
		
		   B
		  / \
		 A   D
		    / \
		   C   E
		
		In this tree, there is a thread linking the right
		branch of node A to node B, and a thread linking
		the right branch of node C to node D.
		
		This means the following string must be returned:
		
		B,A|D,C|E
		*/
		if(isEmpty())
			return "";
		if(getNumberOfNodes() == 1)
			return (String)root.data;
		
		DTNode<T> currNode = root;
		String tempString = " ";
		String returnString = "";
		tempString += currNode.data + " ";
		while(currNode != null)
		{
			// add the current string if we can go left then 
			if(currNode.left != null && !currNode.hasLeftThread && !checkNode(currNode.left.data, tempString,true))
			{
				// only go left if it is not in the tempString, first add the node then go to the left child
				currNode = currNode.left;
				tempString += currNode.data +" ";
				continue;
			}
			else if(currNode.right != null && !currNode.hasRightThread && !checkNode(currNode.right.data, tempString, true))
			{
				// only print the current node and then go right if the node has not been added to the list
				currNode = currNode.right;
				tempString+= currNode.data+ " ";
				//continue;
				
			}
			else if(currNode.right != null && checkNode(currNode.right.data, tempString, true) && currNode.hasRightThread)
			{
				// if we go up via the right thread to a previously visited node 
				tempString += "| ";
				currNode = currNode.right;
				continue;
			}
			else 
			{
				currNode = currNode.right;
			}
		}
		
		char [] retArray = tempString.toCharArray();
		//System.out.println("The temp string for preorder detailed"+tempString);
		// can never end on a pipe so length -2
		for(int i = 1; i < retArray.length-1;i++)
		{
			if(retArray[i] == ' ')
			{
				if(retArray[i-1] != '|' && retArray[i+1] != '|')
					returnString += ",";
				else 
					returnString += "";
			}
			else 
				returnString += retArray[i];
		}
		return returnString;
	}
	
	public int getNumberOfNodes()
	{
		/*
		This method should count and return the number of nodes 
		currently in the tree.
		*/
		return numOfNodes;
	}
	
	public int getHeight()
	{
		/*
		This method should return the height of the tree. The height 
		of an empty tree is 0; the height of a tree with nothing but
		the root is 1.
		*/
		if(isEmpty()) return 0; 
		if(getNumberOfNodes() == 1) return 1;
		else return getHeightRec(root);
		
		//return 0;
		
	}
	
	/*Helper Functions*/
	private boolean isEmpty()
	{
		return numOfNodes == 0;
	}
	
	private void insertRight(DTNode<T> parent, T elem)
	{
		/*
		 * Adds a child to a right node, 
		 * */
		//System.out.println("Entering a RIGHT INSERT");
		if(parent.hasRightThread)
		{
			DTNode<T> newNode = new DTNode<T>(elem);
			newNode.left = parent;
			newNode.right = parent.right;
			newNode.hasRightThread = true;
			newNode.hasLeftThread = true;
			parent.right = newNode;
			parent.hasRightThread = false;
		}
		else
		{
			DTNode<T> newNode = new DTNode<T>(elem);
			newNode.left = parent;
			newNode.hasLeftThread = true;
			parent.right = newNode;
		}
	}
	
	private void insertLeft(DTNode<T> parent, T elem)
	{
		/*
		 * We are adding a left child to a node; is only called when left child or left of left
		 * */
		//System.out.println("Entering a LEFT INSERT");
		if(parent.hasLeftThread)
		{
			DTNode<T> newNode = new DTNode<T>(elem);
			newNode.left = parent.left;
			newNode.right = parent;
			newNode.hasLeftThread = true;
			newNode.hasRightThread = true;
			parent.left = newNode;
			parent.hasLeftThread = false;
		}
		else // parent doesn't have a left thread
		{
			DTNode<T> newNode = new DTNode<T>(elem);
			newNode.right = parent;
			newNode.hasRightThread = true;
			parent.left = newNode;
			//parent.hasLeftThread = false;
		}
	}
	
	//INSERT LEFT OF RIGHT 
	private void insertLR(DTNode<T> parent, DTNode<T> gParent, T elem)
	{
		/*Inserting to the left child of a right child 
		 * 
		 * */
		//System.out.println("Entering a LEFT OF RIGHT INSERT");
		if(parent.hasLeftThread)
		{
			//System.out.println("The parent and gParent of the new node "+ elem + " is "+parent.data + " "+ gParent.data);
			DTNode<T> newNode = new DTNode<T>(elem);
			
			newNode.left = parent.left;
			newNode.right = parent;
			parent.left = newNode;
			newNode.hasLeftThread = true;
			newNode.hasRightThread = true;
			parent.hasLeftThread = false;
		}
		else
		{
			DTNode<T> newNode = new DTNode<T>(elem);
			newNode.right = parent;
			newNode.left = gParent;
			parent.left = newNode;
			newNode.hasLeftThread = true;
			newNode.hasRightThread = true;
		}
	}
	// INSERT RIGHT OF LEFT 
	private void insertRL(DTNode<T> parent, DTNode<T> gParent, T elem)
	{
		//System.out.println("Entering a RIGHT OF LEFT INSERT");
		if(parent.hasRightThread)
		{
			//System.out.println("The parent and gParent of the new node "+ elem + " is "+parent.data + " "+ gParent.data);
			DTNode<T> newNode = new DTNode<T>(elem);
			newNode.right = parent.right;
			newNode.left = parent;
			parent.right = newNode;
			newNode.hasLeftThread = true;
			newNode.hasRightThread = true;
			parent.hasRightThread = false;
		}
		else
		{
			DTNode<T> newNode = new DTNode<T>(elem);
			newNode.left = parent;
			newNode.right = gParent;			// set the right thread to the parent of the parent node 
			parent.right = newNode;			
			newNode.hasLeftThread = true;
			newNode.hasRightThread = true;
		}
	}
	
	private int getHeightRec(DTNode<T> rNode)
	{
		if(rNode == null)// there is no node to increase 
			return 0;
		if(rNode.hasLeftThread && rNode.hasRightThread)// still a node but its just the last one in that subtree
			return 1;
		if(rNode.hasLeftThread && rNode.right == null)
			return 1;
		else {
			int countRight,countLeft;
			countLeft = countRight = 0;
			if(rNode.right != null && !rNode.hasRightThread)
				countRight = getHeightRec(rNode.right);
			if(rNode.left != null && !rNode.hasLeftThread)
				countLeft = getHeightRec(rNode.left);
			//System.out.println("The right count "+countRight+"The left count "+ countLeft);
			return 1+ Math.max(countLeft,  countRight);
		}
	}
	
	private boolean deleteByMerlot(DTNode<T> deleteNode, DTNode<T> parent)
	{
		/* Three General cases that can be expanded:
		 * 1. deleteNode has no left or right subtrees
		 * 2. deleteNode doesn't have a right subtree, but has a left subtree 
		 * 		a. left subtree has a rightmost 
		 * 		b. left subtree has no rightmost(rightmost = thread or null)
		 * 3. deleteNode has a right Subtree
		 * 		a. rightSubtree has leftmost 
		 * 		b. rightSubtree has no leftmost (leftmost = thread or null )
		 * */
		if(deleteNode.hasLeftThread && deleteNode.hasRightThread)
		{
			/*CASE 1: HAS NO LEFT OR RIGHT SUBTREES, but has threads
			 * only ever occurs at a leaf node  
			 * */
			//System.out.println("ENTERING DELETE CASE 1: NO RIGHT AND LEFT SUBTREES (A LEAF NODE)");
			if(deleteNode.data.compareTo(parent.data) < 0) //the node to be deleted is less than the parent node 
			{
				parent.hasLeftThread = deleteNode.hasLeftThread;
				parent.left = deleteNode.left;
				//deleteNode = null;
			}
			else
			{
				parent.hasRightThread = deleteNode.hasRightThread;
				parent.right = deleteNode.right;
			}
			deleteNode = null;
			return true;
		}
		else if(deleteNode.right == null || deleteNode.hasRightThread)
		{
			/*CASE 2: NO RIGHT SUBTREE, MAY HAVE A  LEFT SUBTREE OR A THREAD OR NULL   
			 * no rightchild so the leftchild will replace the node to be deleted 
			 * need to find the rightmost child of the left child 
			 * this rightmost will point to the node replacing delete node
			 * */
			//System.out.println("ENTERING DELETE CASE 2: NO RIGHT SUBTREE");
			if(deleteNode.left != null && !deleteNode.hasLeftThread)
			{
				//System.out.println("CASE 2: HAS A LEFT SUBTREE");
				/*HAS A LEFT SUBTREE */
				DTNode<T> leftChild = deleteNode.left, rightMost = null;
				rightMost= getRightMost(leftChild); // if there is a rightMost with the child then it will never be null
				if(deleteNode == root || parent == null)
				{
					
					/*Inserting into the root*/
					// if it is inserting to root then the right of root is null(if there is no right subtree)
					rightMost.right = deleteNode.right; 
					rightMost.hasRightThread = false;
					root = leftChild;
				}
				else
				{
					rightMost.right = deleteNode.right;
					rightMost.hasRightThread = deleteNode.hasRightThread;
					/*has a parent node/prev*/
					if(deleteNode.data.compareTo(parent.data) < 0) // delete node is less than the parent 
						parent.left = leftChild;
					else// the value is greater than 
						parent.right = leftChild;
				}
			}
			else if(deleteNode.left != null && deleteNode.hasLeftThread)
			{
				// will never have a left thread if it is the root
				// the node has a left thread pointing up, we know that the node exists on the right hand side of its parent 
				if(parent == root)
					root.right = deleteNode.right;	
				else if(parent != null)
				{
					if(deleteNode.data.compareTo(parent.data) < 0 )// if the value is less than the parent 
					{
						parent.left = deleteNode.left;
						parent.hasLeftThread = deleteNode.hasLeftThread;
					}
					else 
					{
						parent.right = deleteNode.right;
						parent.hasRightThread = deleteNode.hasRightThread;
					}
					//deleteNode = null;
				}
			}
			else{ // left is a null value, only has a thread value to the right
				//System.out.println("CASE 2: DOES NOT HAVE LEFT SUBTREE(ROOT BY DEFAULT)");
				if(deleteNode != root || parent != null){	
					//System.out.println("CASE 2: NOT ROOT");
					if(deleteNode.data.compareTo(parent.data) < 0) // delete node is less than the parent 
						parent.left = deleteNode.left;
					else// the value is greater than 
						parent.right = deleteNode.left;
				}
				deleteNode.right = null;
				deleteNode.hasRightThread = false;
				if(this.getNumberOfNodes()-1 == 0)
					root = null;
			}
			deleteNode = null;
			return true;
		}
		else if(deleteNode.right != null)
		{
			/*CASE 3: RIGHT SUBTREE 
			 * the deleted node has a right child 
			 * look for the leftmost of the right child and make it point to the new node
			 * */
			//System.out.println("ENTERING DELETE CASE 3: HAS A RIGHT SUBTREE");
			DTNode<T> rightChild = deleteNode.right, leftMost;
			leftMost = getLeftMost(rightChild); // if this is within a right subtree, leftMost will never be null
	
			if(deleteNode.left != null && !deleteNode.hasLeftThread)
			{
				//System.out.println("CASE 3: HAS A LEFT SUBTREE");
				DTNode<T> leftChild = deleteNode.left, rightMost;
				rightMost = getRightMost(leftChild); 
				rightMost.right = leftMost; // thread of the rightMost element in the left subtree should now point to the leftmost of the right tree
			}
			if(deleteNode == root || parent ==null)
			{
				//System.out.println("CASE 3: ROOT CASE");
				// this is the root node
				leftMost.left = deleteNode.left;
				leftMost.hasLeftThread = false;
				root = rightChild;
			}
			else
			{
				// a nonterminal node 
				leftMost.left = deleteNode.left;
				leftMost.hasLeftThread = deleteNode.hasLeftThread;
				if(deleteNode.data.compareTo(parent.data) < 0)// less than the parent 
					parent.left = rightChild;
				else 
					parent.right = rightChild;
			}
			deleteNode = null;
			return true;
		}
		return false;
	}
	
	public void printNodeDataStruct(DTNode<T> rNode)
	{
		/*Printing the Node subtree structure 
		 * - print the current node
		 * - print the current node we are on, check to see if it has threads print like so ( left thread node <-) [current Node] (-> right thread node) 
		 * - print the line breaks "_______________|______________"
		 * - 					   "|							  |"
		 * - print the subsequent children
		 * 
		 * */
		if(isEmpty())
		{
			System.out.println("The tree is empty");
			return;
		}
		if(rNode == null)
			return;
		System.out.println("\t\t\t   [ "+rNode.data+" ]");
		System.out.println("\t\t\t     |");
		System.out.println("       ___________________________________________");
		System.out.println("       |\t\t\t\t\t |");
		if(rNode.hasLeftThread || rNode.hasRightThread)
		{
		
			// prints only when there is a thread, or two threads
			if(!rNode.hasLeftThread){
				if(rNode.left == null)// only has a right thread 
					System.out.println("\t\t\t\t\t       [---> "+rNode.right.data+" ]");
				else // has a right thread and a left child
					System.out.println("     [ "+rNode.left.data+" ] \t\t\t\t       [---> "+ rNode.right.data+ " ]");
			}
			else if(!rNode.hasRightThread){
				if(rNode.right == null ) // has a left thread only
					System.out.println("     [ "+rNode.left.data+" <--- ]");
				else // has a left thread and a right child 
					System.out.println("     [ "+rNode.left.data+" <--- ]\t\t\t       [ "+ rNode.right.data+ " ]");
			}
			else{ 
				System.out.println("   [ "+rNode.left.data+" <---] \t\t\t\t    [---> "+rNode.right.data+ " ]"); 
				return;
			}
		}
		// it doesn't have a thread and only has children or null 
		else
		{
			if(rNode.left == null) // only has a right child
				System.out.println("\t\t\t\t\t       [ "+rNode.right.data + " ]");
			else if(rNode.right == null) // only has a left child
				System.out.println("     [ " + rNode.left.data + " ]");
			else
				System.out.println("     [ " + rNode.left.data + " ] \t\t\t\t      [ "+rNode.right.data + " ]");
		}
		return;
	}
	
	public void printNodeDataDetail(DTNode<T> rNode)
	{
		if(isEmpty()){
			System.out.println("The tree is empty");
			return;
		}
		if(rNode == null)
			return;
		System.out.println("The current Node is "+rNode.data);
		if(rNode.hasLeftThread ||rNode.hasRightThread)
		{
			if(!rNode.hasLeftThread){
				if(rNode.left == null )
					System.out.println("Only has a Right Thread pointing to   ---> "+rNode.right.data);
				else 
					System.out.println("The Node has a left child " + rNode.left.data+"\nHas a right thread pointing to--> "+rNode.right.data);
			}
			else if(!rNode.hasRightThread){
				if(rNode.right == null )
					System.out.println("Only has a Left Thread pointing to ---> " +rNode.left.data);
				else
					System.out.println("Has a right child "+rNode.right.data + "\nAlso has a left thread pointing to ---> "+ rNode.left.data);
			}
			else
				System.out.println("The Node has both threads(a leaf node ) \nHas left thread pointing to --> "+rNode.left.data+"\nHas a right thread pointing to ---> "+rNode.right.data);
		}
		else
		{
			if(rNode.left == null )
				System.out.println("Has a single right child " + rNode.right.data);
			else if(rNode.right == null)
				System.out.println("Has a single left child " + rNode.left.data);
			else 
				System.out.println("Has both children \nHas a left child "+ rNode.left.data + "\nHas a right child "+ rNode.right.data);
		}
	}
	
	private DTNode<T> getLeftMost(DTNode<T> link)
	{
		if(link == null )
			return null;
		DTNode<T> leftMost = link;
		if(link.left != null && !link.hasLeftThread){
			leftMost = link.left;
		
			while(leftMost != null)
			{
				if(leftMost.left != null && !leftMost.hasLeftThread)
					leftMost = leftMost.left;
				else 
					break;
			}
			//return leftMost;
		}
		return leftMost;
	}
	
	private DTNode<T> getRightMost(DTNode<T> link)
	{
		if(link == null)
			return null;
		DTNode<T> rightMost = link;
		if(link.right != null && !link.hasRightThread)
		{
			rightMost = link.right;
			while(rightMost != null)
			{
				if(rightMost.right != null && !rightMost.hasRightThread)
					rightMost = rightMost.right;
				else// breaks out of the while loop when rightMost.right has a rightThread or it is null
					break;
			}
			//return rightMost;
		}
		return rightMost;
	}

	private boolean checkNode(T zelda, String link, boolean pipeFlag)
	{
		if(pipeFlag)
		{
			String checkerTwo = " "+ zelda + " ";
			String checkerThree = " "+ zelda + " | ";
			if(link.contains(checkerTwo))
				return true;
			else if(link.contains(checkerThree)){
				return true;
			}
			else
				return false;
		}
		
		String checker = " "+zelda + " ";
		if(link.contains(checker))
			return true;
		return false;
	}
}

