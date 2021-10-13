import java.util.Queue;
import java.util.LinkedList;

public class Tester<T extends Comparable<?super T >>
{
	
	// perfrom a BFS traversal of the tree
	public static void printTree(DoubleThreadedBST<Integer> dekuTree)
	{
		if(dekuTree == null)
			return;
		if(dekuTree.getNumberOfNodes() == 0)
		{
			System.out.println("The tree is empty");
			return;
		}
		//DTNode<T> currNode = dekuTree.getRoot();
		Queue<DTNode<Integer>> dekuQ = new LinkedList<DTNode<Integer>>();
		dekuQ.add(dekuTree.getRoot());
		//int count = 0;
		while(!dekuQ.isEmpty())
		{
			DTNode<Integer> currNode = dekuQ.poll();
			System.out.println("\n----------------------------------------------------------------------------------------");
			dekuTree.printNodeDataStruct(currNode);
			dekuTree.printNodeDataDetail(currNode);
			System.out.println("----------------------------------------------------------------------------------------\n");
			if(currNode.left != null && !currNode.hasLeftThread)
				dekuQ.add(currNode.left);
			if(currNode.right != null && !currNode.hasRightThread)
				dekuQ.add(currNode.right);
		}
	}
	
	public static void printCharTree(DoubleThreadedBST<Character> dekuTree)
	{
		if(dekuTree == null)
			return;
		if(dekuTree.getNumberOfNodes() == 0)
		{
			System.out.println("The tree is empty");
			return;
		}
		//DTNode<T> currNode = dekuTree.getRoot();
		Queue<DTNode<Character>> dekuQ = new LinkedList<DTNode<Character>>();
		dekuQ.add(dekuTree.getRoot());
		//int count = 0;
		while(!dekuQ.isEmpty())
		{
			DTNode<Character> currNode = dekuQ.poll();
			System.out.println("\n----------------------------------------------------------------------------------------");
			dekuTree.printNodeDataStruct(currNode);
			dekuTree.printNodeDataDetail(currNode);
			System.out.println("----------------------------------------------------------------------------------------\n");
			if(currNode.left != null && !currNode.hasLeftThread)
				dekuQ.add(currNode.left);
			if(currNode.right != null && !currNode.hasRightThread)
				dekuQ.add(currNode.right);
		}
	}
	public static void main(String[] args) throws Exception
	{
		/*
		TODO: Write your code to test your implementation here.
		
		This file will be overwritten for marking purposes
		*/
		
		/*BASICS*/
		System.out.println("***********************************************************");
		System.out.println("Basic Node Operations");
		System.out.println("***********************************************************");
		
		DTNode<Integer> firstNode = new DTNode<Integer>(2);
		DTNode<Integer> secNode = new DTNode<Integer>(1);
		DTNode<Character> thirdNode = new DTNode<Character>('A');
		
		firstNode.right = new DTNode<Integer>(3);
		firstNode.left = new DTNode<Integer>(5);
		secNode.left = new DTNode<Integer>(4);
		secNode.right = new DTNode<Integer>(6);
		System.out.println(firstNode.toString(true));
		System.out.println(secNode.toString(true));
		System.out.println(thirdNode.toString(false));
		
		/*TREE INSERTS*/
		System.out.println("\n\n***********************************************************");
		System.out.println("Tree Insert  Operations");
		System.out.println("***********************************************************");
		DoubleThreadedBST<Integer>  firstBST = new DoubleThreadedBST<Integer>();
		
		firstBST.insert(20);
		firstBST.insert(10); 
		firstBST.insert(22);
		firstBST.insert(21);
		firstBST.insert(9);
		firstBST.insert(12);
		firstBST.insert(11);
		firstBST.insert(4);
		firstBST.insert(5);
		firstBST.insert(13);
		firstBST.insert(31);
		firstBST.insert(25);
		firstBST.insert(45);
		firstBST.insert(9);// Duplicate Key
		firstBST.insert(15);
		System.out.println("Current number of nodes in the tree "+firstBST.getNumberOfNodes());
		System.out.println("The inorder reverse of the first tree \n"+firstBST.inorderReverse());
		System.out.println("The inorder reverse detailed of the first tree \n"+firstBST.inorderReverseDetailed());
		System.out.println("The preorder of the first tree:\n "+firstBST.preOrder());
		System.out.println("The preorder of the first tree:\n "+firstBST.preorderDetailed());
			
		System.out.println("Contains 23?: "+firstBST.contains(23));
		System.out.println("Contains 20?: "+firstBST.contains(20));
		System.out.println("Contains 31?: "+firstBST.contains(31));
		System.out.println("Contains 45?: "+firstBST.contains(45));
		System.out.println("Contains 25?: "+firstBST.contains(25));
		System.out.println("Contains 21?: "+firstBST.contains(21));
		System.out.println("Contains 11?: "+firstBST.contains(11));
		System.out.println("Contains 10?: "+firstBST.contains(10));
		System.out.println("Contains 9?: " +firstBST.contains(9));
		System.out.println("Contains 12?: "+firstBST.contains(12));
		System.out.println("Contains 4?: " +firstBST.contains(4));
		System.out.println("Contains 6?: " +firstBST.contains(6));
		System.out.println("Contains 5?: " +firstBST.contains(5));
		System.out.println("Contains 13?: "+firstBST.contains(13));
		System.out.println("Contains 15?: "+firstBST.contains(15));
		//printTree(firstBST);
		
		System.out.println("The height of the tree is "+firstBST.getHeight());
		/*TREE DELETE*/	
		System.out.println("\n\n***********************************************************");
		System.out.println("Tree Delete Operations");
		System.out.println("***********************************************************");
		
		System.out.println("Delete 15 Successful?: "+firstBST.delete(15));
		//printTree(firstBST);
		System.out.println("Delete 9 Successful?: "+firstBST.delete(9));
		System.out.println("Delete 10 Successful?: "+firstBST.delete(10));
		System.out.println("Current number of nodes in the tree "+firstBST.getNumberOfNodes());
		System.out.println("The height of the tree is "+ firstBST.getHeight());
		System.out.println("The inorder reverse of the first tree \n"+firstBST.inorderReverse());
		System.out.println("The inorder reverse detailed of the first tree \n"+firstBST.inorderReverseDetailed());
		System.out.println("The preorder of the first tree:\n "+firstBST.preOrder());
		System.out.println("The preorder of the first tree:\n "+firstBST.preorderDetailed());
		System.out.println("___________________________________________________________________________________________");
		System.out.println("Print Tree Test 1.");
		//printTree(firstBST);
		System.out.println("\n\nDelete 23 Successful?: "+firstBST.delete(23));
		System.out.println("Contains 15?:"+firstBST.contains(15));
		System.out.println("Contains 9?: "+firstBST.contains(9));
		System.out.println("Contains 10?: "+firstBST.contains(10));
		System.out.println("The left of the root is "+ firstBST.getRoot().left.data);
		System.out.println("Successful Root Delete?:"+firstBST.delete(20));
		System.out.println("Contains 20?: "+firstBST.contains(20));
		System.out.println("___________________________________________________________________________________________");
		System.out.println("Print Tree Test 2.");
		//printTree(firstBST);
		
		System.out.println("The current number of nodes in the tree "+firstBST.getNumberOfNodes());
		System.out.println("------------------------------------------------------");
		System.out.println("Deleting all elements from the first BST....");
		System.out.println("Delete 22 Successful?: "+firstBST.delete(22));
		System.out.println("Delete 13 Successful?: "+firstBST.delete(13));
		System.out.println("Delete 4 Successful?: "+firstBST.delete(4));
		System.out.println("___________________________________________________________________________________________");
		System.out.println("Print Tree Test 3.");
		//printTree(firstBST);
		System.out.println("Delete 5 Successful?: "+firstBST.delete(5));
		System.out.println("Delete 26 Successful?: "+firstBST.delete(26));
		System.out.println("Delete 31 Successful?: "+firstBST.delete(31));
		System.out.println("___________________________________________________________________________________________");
		System.out.println("Print Tree Test 4.");
		//printTree(firstBST);
		System.out.println("Delete 45 Successful?: "+firstBST.delete(45));
		System.out.println("Delete 6 Successful?: "+firstBST.delete(6));
		System.out.println("Delete 11 Successful?: "+firstBST.delete(11));
		System.out.println("___________________________________________________________________________________________");
		System.out.println("Print Tree Test 5.");
		//printTree(firstBST);
		System.out.println("Delete 12 Successful?: "+firstBST.delete(12));
		System.out.println("Delete 25 Successful?: "+firstBST.delete(25));
		System.out.println("Delete 21 Successful?: "+firstBST.delete(21));
		System.out.println("___________________________________________________________________________________________");
		System.out.println("Print Tree Test 6.");
		//printTree(firstBST);
		//System.out.println("The new root node is: "+firstBST.getRoot().data);
		System.out.println("The current number of nodes in the tree "+firstBST.getNumberOfNodes());
	
		
		/*PRINT A TREE */	
		System.out.println("\n\n***********************************************************");
		System.out.println("Tree Print Test");
		System.out.println("***********************************************************");
		DoubleThreadedBST<Integer> secondBST = new DoubleThreadedBST<Integer>();
		System.out.println("Delete Empty Tree "+ secondBST.delete(10));
		secondBST.insert(16);
		secondBST.insert(2);
		System.out.println("The number of nodes in a tree with only root "+secondBST.getNumberOfNodes());
		secondBST.insert(25);
		secondBST.insert(9);
		secondBST.insert(17);
		secondBST.insert(8);
		secondBST.insert(15);
		secondBST.insert(27);
		
		printTree(secondBST);
		System.out.println("Current number of nodes in the tree "+secondBST.getNumberOfNodes());
		System.out.println("The height of the second tree is "+ secondBST.getHeight());
		System.out.println("The inorder reverse of the second tree \n"+secondBST.inorderReverse());
		System.out.println("The inorder reverse detailed of the second tree \n"+secondBST.inorderReverseDetailed());
		System.out.println("The preorder of the second tree:\n "+secondBST.preOrder());
		System.out.println("The preorder of the second tree:\n "+secondBST.preorderDetailed());
		
		System.out.println("\n\nMaking a Copy of the second tree.... ");
		DoubleThreadedBST<Integer> secondCopy = new DoubleThreadedBST<Integer>(secondBST);
		System.out.println("Current number of nodes in the copy of the tree "+secondCopy.getNumberOfNodes());
		System.out.println("The height of the copy of the second tree is "+ secondCopy.getHeight());
		System.out.println("The inorder reverse of the copy of the second tree \n"+secondCopy.inorderReverse());
		System.out.println("The inorder reverse detailed of the copy of the second tree \n"+secondCopy.inorderReverseDetailed());
		System.out.println("The preorder of the copy of the second tree:\n "+secondCopy.preOrder());
		System.out.println("The preorder of the copy of the  second tree:\n "+secondCopy.preorderDetailed());
		
		System.out.println("\n\nDeleting all nodes in the Copy.... ");
		secondCopy.delete(16);
		secondCopy.delete(2);
		secondCopy.delete(25);
		secondCopy.delete(9);
		secondCopy.delete(17);
		secondCopy.delete(8);
		secondCopy.delete(15);
		secondCopy.delete(27);
		
		System.out.println("The current number of nodes in the copy of second tree : "+ secondCopy.getNumberOfNodes());
		System.out.println("The current number of nodes in the second tree: "+ secondBST.getNumberOfNodes());
			
		/*TREE TRAVERSAL*/	
		System.out.println("\n\n***********************************************************");
		System.out.println("Tree Traversal Operations");
		System.out.println("***********************************************************");
		DoubleThreadedBST<Integer> thirdBST = new DoubleThreadedBST<Integer>();
		thirdBST.insert(54);
		thirdBST.insert(91);
		thirdBST.insert(9);
		thirdBST.insert(92);
		thirdBST.insert(77);
		thirdBST.insert(54);// Duplicate Key
		thirdBST.insert(36);
		thirdBST.insert(96);
		thirdBST.insert(68);
		thirdBST.insert(9);// Duplicate Key 
		thirdBST.insert(62);
		thirdBST.insert(80);
		thirdBST.insert(20);
		thirdBST.insert(74);
		thirdBST.insert(68);// Duplicate Key
		thirdBST.insert(28);
		thirdBST.insert(6);
		thirdBST.insert(32);
		thirdBST.insert(17);
		thirdBST.insert(66);
		thirdBST.insert(20);// Duplicate Key
		thirdBST.insert(13);
		thirdBST.insert(83);
		thirdBST.insert(98);
		thirdBST.insert(66);// Duplicate Key 
		
		System.out.println("The current number of nodes in tree 3 "+thirdBST.getNumberOfNodes());
		System.out.println("___________________________________________________________________________________________");
		System.out.println("Tree 2 Traversal Test");
		//printTree(secondBST);
		System.out.println(secondBST.inorderReverse());
		System.out.println("Detailed Tree 2 Traversal: \n"+secondBST.inorderReverseDetailed());
		System.out.println("___________________________________________________________________________________________");
		System.out.println("Tree 3 Traversal Test");
		System.out.println(thirdBST.inorderReverse());
		System.out.println("Detailed Tree 3 Traversal: \n"+thirdBST.inorderReverseDetailed());
		System.out.println(thirdBST.getNumberOfNodes());
		//printTree(thirdBST);
		
		DoubleThreadedBST<Integer> fourthBST = new DoubleThreadedBST<Integer>();
		fourthBST.insert(9);
		fourthBST.insert(6);
		fourthBST.insert(12);
		fourthBST.insert(4);
		fourthBST.insert(8);
		fourthBST.insert(10);
		fourthBST.insert(11);
		fourthBST.insert(16);
		fourthBST.insert(2);
		fourthBST.insert(5);
		fourthBST.insert(14);
		System.out.println("___________________________________________________________________________________________");
		System.out.println("Tree 4 Print Test");
		//printTree(fourthBST);
		System.out.println("The height of the fourth BST is "+ fourthBST.getHeight());
		System.out.println(fourthBST.preOrder());
		System.out.println(secondBST.preOrder());
		System.out.println("Detailed inorder Tree 4 Traversal: \n"+fourthBST.inorderReverseDetailed());
		System.out.println("inOrder Tree 4 Traversal: \n"+fourthBST.inorderReverse());
		System.out.println("preOrder Tree 4 Traversal: \n"+fourthBST.preOrder());
		System.out.println("The preorder detailed tree 4 traversal:\n"+fourthBST.preorderDetailed());
		
		/*TREE TRAVERSAL*/	
		System.out.println("\n\n***********************************************************");
		System.out.println("Tree Clone and Copy Operations");
		System.out.println("***********************************************************");
		System.out.println("___________________________________________________________________________________________");
		System.out.println("Tree 4 Print Test");
		//printTree(fourthBST);
		System.out.println("\n\n___________________________________________________________________________________________");
		System.out.println("Tree 4 Copy Print Test");
		DoubleThreadedBST<Integer> fourthCopy = new DoubleThreadedBST<Integer>(fourthBST);
		//printTree(fourthCopy);
		System.out.println("The number of nodes in the fourth tree is :"+fourthBST.getNumberOfNodes()+
				"\nThe number of nodes in the copy of the fourth tree is: "+fourthCopy.getNumberOfNodes());
		System.out.println("\n\n___________________________________________________________________________________________");
		System.out.println("Tree 4 Copy Print Test");
		DoubleThreadedBST<Integer> fourthClone = new DoubleThreadedBST<Integer>();
		fourthClone = fourthBST.clone();
		//printTree(fourthClone);
		System.out.println("The number of nodes in the fourth tree is :"+fourthBST.getNumberOfNodes()+
				"\nThe number of nodes in the copy of the fourth tree is: "+fourthCopy.getNumberOfNodes());
		System.out.println("Deleteing Elements from the original fourth BST....");
		//System.out.println("Delete 8?: "+ fourthBST.delete(8));
		//System.out.println("Delete 9? "+ fourthBST.delete(9));
		//System.out.println("Delete 10?"+fourthBST.delete(10));
		System.out.println("\nDeleteing Elements from the copy of the fourth BST....");
		System.out.println("Delete 4?: "+ fourthCopy.delete(4));
		System.out.println("Delete 5? "+ fourthCopy.delete(5));
		System.out.println("\nDeleteing Elements from the clone of  fourth BST....");
		System.out.println("Delete 12?"+fourthClone.delete(12));
		System.out.println("The number of nodes in the fourth orginal tree: "+fourthBST.getNumberOfNodes()+
				"\nThe number of nodes in the fourth copy tree: "+ fourthCopy.getNumberOfNodes()
				+"\nThe number of nodes in the fourth clone tree is "+fourthClone.getNumberOfNodes());
		System.out.println("___________________________________________________________________________________________");
		System.out.println("Tree 4 Print Test");
		//printTree(fourthBST);
		System.out.println("\n\n___________________________________________________________________________________________");
		System.out.println("Tree 4 Copy Print Test");
		//printTree(fourthCopy);
		System.out.println("\n\n___________________________________________________________________________________________");
		System.out.println("Tree 4 Clone Print Test");
		//printTree(fourthClone);
		/*ALL TREE OPERATIONS */	
		System.out.println("\n\n****************************************************************************");
		System.out.println("ALL Tree Operations");
		System.out.println("****************************************************************************");
		/*	Attempt a delete of an empty tree
		Insert into a tree
		Create a Copy of the Tree using the copy construct
		Create a clone of the tree using the clone function 
		delete a number of elements from the original tree
		compare the inOrder, preOrder, and those traversals 
		delete all elements from tree 
		calculate height of different trees 
		Delete elements from the Clone and then perform preOrderDet 
		 */
		DoubleThreadedBST<Character> wildBST = new DoubleThreadedBST<Character>();
		System.out.println("Attempt to delete the first tree wildBST?: "+wildBST.delete('A'));
		System.out.println("The number of nodes in the wild tree "+ wildBST.getNumberOfNodes());
		wildBST.insert('G');
		wildBST.insert('F');
		wildBST.insert('K');
		System.out.println("The root of the wild tree is "+ wildBST.getRoot().data+"\nThe number of nodes in the tree is "+
						wildBST.getNumberOfNodes());
		printCharTree(wildBST);
		
		DoubleThreadedBST<Integer> fifthBST = new DoubleThreadedBST<Integer>();
		fifthBST.insert(3);
		fifthBST.insert(1);
		fifthBST.insert(5);
		fifthBST.insert(2);
		fifthBST.insert(4);
		fifthBST.insert(6);
		System.out.println("___________________________________________________________________________________________");
		System.out.println("Tree 5 Print Test");
		//printTree(fifthBST);
		System.out.println("The number of nodes in the tree is "+ fifthBST.getNumberOfNodes());
		System.out.println("The height of the tree is "+ fifthBST.getHeight());
		System.out.println("The inOrder Reverse of the Tree: \n"+ fifthBST.inorderReverse());
		System.out.println("The inOrder Reverse Detailed of the Tree:\n "+ fifthBST.inorderReverseDetailed());
		System.out.println("The preOrder of the Tree:\n"+fifthBST.preOrder());
		System.out.println("The preOrder Detailed of the Tree: \n" +fifthBST.preorderDetailed());
		
		System.out.println("\n\nMaking a Copy of the fifth tree.... ");
		DoubleThreadedBST<Integer> fifthCopy = new DoubleThreadedBST<Integer>(fifthBST);
		//printTree(fifthCopy);
		
		System.out.println("The number of nodes in the fifth copy is "+fifthCopy.getNumberOfNodes());
		System.out.println("The height of the fifth copy is "+ fifthCopy.getHeight());
		System.out.println("The inOrder Reverse of the copy: \n"+fifthCopy.inorderReverse());
		System.out.println("The inOrder Reverse detailed of the copy: \n"+ fifthCopy.inorderReverseDetailed());
		System.out.println("The preOrder of the copy: \n"+ fifthCopy.preOrder());
		System.out.println("The preOrder detailed of the copy: \n"+fifthCopy.preorderDetailed());
		
		System.out.println("\n\nMaking a Clone of the fifth tree.... ");
		DoubleThreadedBST<Integer> fifthClone = new DoubleThreadedBST<Integer>();
		fifthClone = fifthBST.clone();
		//printTree(fifthCopy);
		
		System.out.println("The number of nodes in the fifth copy is "+fifthClone.getNumberOfNodes());
		System.out.println("The height of the fifth copy is "+ fifthClone.getHeight());
		System.out.println("The inOrder Reverse of the copy: \n"+fifthClone.inorderReverse());
		System.out.println("The inOrder Reverse detailed of the copy: \n"+ fifthClone.inorderReverseDetailed());
		System.out.println("The preOrder of the copy: \n"+ fifthClone.preOrder());
		System.out.println("The preOrder detailed of the copy: \n"+fifthClone.preorderDetailed());
		
		System.out.println("\n\nDeleting all the elements in the original tree....");
		fifthBST.delete(3);
		fifthBST.delete(4);
		fifthBST.delete(5);
		fifthBST.delete(2);
		fifthBST.delete(1);
		fifthBST.delete(6);
		System.out.println("The number of nodes in the fifth BST after deletion...\n"+fifthBST.getNumberOfNodes());
		System.out.println("The height of the fift BST after deletion....\n"+fifthBST.getHeight());
		
		System.out.println("\n\nDeleting 50% of elements in the original tree....");
		fifthCopy.delete(5);
		fifthCopy.delete(2);
		fifthCopy.delete(6);
		System.out.println("The number of nodes in the Copy of the fith is "+fifthCopy.getNumberOfNodes());
		System.out.println("The height of the Copy of the fifth is "+ fifthCopy.getHeight());
		
		System.out.println("\n\n___________________________________________________________________________________________");
		System.out.println("Tree 6 Print Test");
		DoubleThreadedBST<Integer> sixBST = new DoubleThreadedBST<Integer>();
		sixBST.insert(100);
		sixBST.insert(50);
		sixBST.insert(200);
		sixBST.insert(40);
		sixBST.insert(70);
		sixBST.insert(135);
		sixBST.insert(210);
		sixBST.insert(60);
		sixBST.insert(80);
		sixBST.insert(145);
		sixBST.insert(165);
		sixBST.insert(90);
		sixBST.insert(85);
		sixBST.insert(81);
		//printTree(sixBST);
		System.out.println("The number of nodes in the tree is "+ sixBST.getNumberOfNodes());
		System.out.println("The height of the tree is "+ sixBST.getHeight());
		System.out.println("The inOrder Reverse of the Tree: \n"+ sixBST.inorderReverse());
		System.out.println("The inOrder Reverse Detailed of the Tree:\n "+ sixBST.inorderReverseDetailed());
		System.out.println("The preOrder of the Tree:\n"+sixBST.preOrder());
		System.out.println("The preOrder Detailed of the Tree: " +sixBST.preorderDetailed());
		
		System.out.println("\n\nMaking a Copy of the fifth tree.... ");
		DoubleThreadedBST<Integer> sixCopy = new DoubleThreadedBST<Integer>(sixBST);
		//printTree(sixCopy);
		
		System.out.println("The number of nodes in the sixth copy is "+sixCopy.getNumberOfNodes());
		System.out.println("The height of the sixth copy is "+ sixCopy.getHeight());
		System.out.println("The inOrder Reverse of the copy: \n"+sixCopy.inorderReverse());
		System.out.println("The inOrder Reverse detailed of the copy: \n"+ sixCopy.inorderReverseDetailed());
		System.out.println("The preOrder of the copy: \n"+ sixCopy.preOrder());
		System.out.println("The preOrder detailed of the copy: \n"+sixCopy.preorderDetailed());
		
		System.out.println("\n\nMaking a Clone of the fifth tree.... ");
		DoubleThreadedBST<Integer> sixClone = new DoubleThreadedBST<Integer>();
		sixClone = sixBST.clone();
		//printTree(sixCopy);
		
		System.out.println("The number of nodes in the sixth copy is "+sixClone.getNumberOfNodes());
		System.out.println("The height of the sixth copy is "+ sixClone.getHeight());
		System.out.println("The inOrder Reverse of the copy: \n"+sixClone.inorderReverse());
		System.out.println("The inOrder Reverse detailed of the copy: \n"+ sixClone.inorderReverseDetailed());
		System.out.println("The preOrder of the copy: \n"+ sixClone.preOrder());
		System.out.println("The preOrder detailed of the copy: \n"+sixClone.preorderDetailed());
		
		System.out.println("\n\nDeleting all the elements in the original tree....");
		sixBST.delete(100);
		sixBST.delete(50);
		sixBST.delete(200);
		sixBST.delete(165);
		sixBST.delete(40);
		sixBST.delete(60);
		sixBST.delete(210);
		sixBST.delete(135);
		sixBST.delete(90);
		sixBST.delete(145);
		sixBST.delete(70);
		sixBST.delete(80);
		sixBST.delete(85);
		sixBST.delete(81);
		
		System.out.println("The number of nodes in the sixth BST after deletion...\n"+sixBST.getNumberOfNodes());
		System.out.println("The height of the sixth BST after deletion....\n"+sixBST.getHeight());
		System.out.println("Does the original tree contain the element 81?: "+ sixBST.contains(81));
		System.out.println("Does the original tree contain the element 100?: "+ sixBST.contains(100));
		System.out.println("Does the original tree contain the element 145?: "+ sixBST.contains(145));
		System.out.println("Does the original tree contain the element 135?: "+ sixBST.contains(135));
		System.out.println("Does the original tree contain the element 150?: "+ sixBST.contains(150));
		System.out.println("Attempting preorder on an empty tree \n"+sixBST.preOrder());
		System.out.println("Attempting preorder detailed on an empty tree \n"+sixBST.preorderDetailed());
		System.out.println("Attempting inorder reverse on an empty tree \n"+sixBST.inorderReverse());
		System.out.println("Attempting inorder reverse detailed on an empty tree \n"+sixBST.inorderReverseDetailed());
		
		System.out.println("\nDeleting 50% of elements in the original tree....");
		sixCopy.delete(100);
		sixCopy.delete(210);
		sixCopy.delete(200);
		sixCopy.delete(81);
		sixCopy.delete(85);
		sixCopy.delete(40);
		sixCopy.delete(70);
		//printTree(sixCopy);
		System.out.println("The number of nodes in the copy of the sixth is "+sixCopy.getNumberOfNodes());
		System.out.println("The height of the copy of the sixth is "+ sixCopy.getHeight());
		System.out.println("The inOrder Reverse of the copy: \n"+sixCopy.inorderReverse());
		System.out.println("The inOrder Reverse detailed of the copy: \n"+ sixCopy.inorderReverseDetailed());
		System.out.println("The preOrder of the copy: \n"+ sixCopy.preOrder());
		System.out.println("The preOrder detailed of the copy: \n"+sixCopy.preorderDetailed());
		
		System.out.println("The number of nodes in the sixth clone is "+sixClone.getNumberOfNodes());
		System.out.println("The height of the sixth clone is "+ sixClone.getHeight());
		System.out.println("The inOrder Reverse of the clone: \n"+sixClone.inorderReverse());
		System.out.println("The inOrder Reverse detailed of the clone: \n"+ sixClone.inorderReverseDetailed());
		System.out.println("The preOrder of the clone: \n"+ sixClone.preOrder());
		System.out.println("The preOrder detailed of the clone: \n"+sixClone.preorderDetailed());
	}
}

