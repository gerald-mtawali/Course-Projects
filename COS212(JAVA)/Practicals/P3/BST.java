@SuppressWarnings("unchecked")
public class BST<T extends Comparable<? super T>> {
    
	protected BSTNode<T> root = null;
	protected static int count = 0;

	public BST() 
	{
    	}
    
	public void clear() 
	{
		root = null;
	}

	public String inorder(BSTNode<T> node) 
	{
		Boolean verbose = true;
		if (node != null) {
			String result = "";
			result += inorder(node.left);
			if (verbose) {
				result += node.toString()+"\n";
			} else {
				result += node.element.toString() + " ";
			}
			result += inorder(node.right);
			return result;
		}
		return "";
	}

	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	public boolean isEmpty() 
	{
		//Your code goes here
		return root == null;
	}

	public T minValue() 
	{
		//Your code goes here
		// the smallest value in the tree is the leftmost value 
		T min;
		if(isEmpty()){return null;}
		else
		{
			BSTNode<T> currNode = root;
			while(currNode.left != null) 	// keep going until we have reached the leftmost node 
			{
				currNode = currNode.left;
			}
			min = currNode.element;	
		}
		return min;
	}

	public T maxValue() 
	{
		//Your code goes here
		T max;
		if(isEmpty()){return null;}
		else
		{
			BSTNode<T> currNode = root;
			while(currNode.right != null) 	// keep going until we have reached the leftmost node 
			{
				currNode = currNode.right;
			}
			max = currNode.element;	
		}
		return max;
	}

	public void printPostorder() 
	{
		//Your code goes here
		if(isEmpty()){System.out.println("The tree is empty");} 
		else
		{
			postorder(root);
			System.out.print("\n");	
		}
	}
	public void postorder(BSTNode<T> node)
	{
		if(node != null)
		{
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.element +" ");
		}
	}

	public void insert(T element) 
	{
		//Your code goes here
		if(isEmpty()){root = new BSTNode<T>(element);}
		else{
			BSTNode<T> currNode = root, prev = null;		// prev acts as the parent node 
			while(currNode != null)
			{
				prev = currNode;
				if(element.compareTo(currNode.element) < 0)
					currNode = currNode.left;
				else
					currNode = currNode.right;
			}
			if(element.compareTo(prev.element) < 0)
				prev.left = new BSTNode<T>(element);
			else
				prev.right = new BSTNode<T>(element);
		}
	}

	public boolean deleteByCopy(T element) 
	{
		//Your code goes here
		if (isEmpty()){
			System.out.println("The tree is empty");
			return false;
		}
		else{
			BSTNode<T> node, currNode = root, pred = null;
			while(currNode != null && !currNode.element.equals(element))
			{
				pred = currNode;
				if(element.compareTo(currNode.element) < 0)
					currNode = currNode.left;
				else 
					currNode = currNode.right;
			}
			node =currNode;
			if(currNode != null){
				if(node.right == null)
					node = node.left;
				else if(node.left == null)
					node = node.right;
				else{
					BSTNode<T> tmp = node.left;
					BSTNode<T> prev = node;
					while(tmp.right != null){
						prev = tmp;
						tmp = tmp.right;
					}
					node.element = tmp.element;
					if(prev == node)
						prev.left = tmp.left;
					else prev.right = tmp.right;
				}
				if(currNode == root) root = node;
				else if(pred.left == currNode) pred.left = node;
				else pred.right = node;
				return true;
			}
			else {
				System.out.println("The key " + element + " is not in the tree");
				return false;
			}
		}
	}

	public T search(T element) 
	{
		//Your code goes here
		if(isEmpty()){return null;}
		else
		{
			BSTNode<T> currNode = root;
			while(currNode != null)
			{
				if(element.equals(currNode.element))
					return currNode.element;
				else if(element.compareTo(currNode.element) < 0)
					currNode = currNode.left;
				else
					currNode = currNode.right;
			}
			return null;
		}
	}

}
