/**
 * Name: Gerald Mtawali
 * 
 * Student number: 15264999
 */

public class SplayTree<T extends Comparable<? super T>> {

    public TreeNode<T> root;

    public SplayTree() {
        // Your code here...
    	root = null;
    }

    /**
     * Returns true if the key exists in the tree, otherwise false
     */
    public Boolean contains(T key) {
        // Your code here... 
    	if(root == null){return false;}
    	else{
    		TreeNode<T> curr = root;
    		
    		while(curr != null){
    			if(curr.key.equals(key)){return true;}
    			if(curr.key.compareTo(key) < 0)
    				curr = curr.right;
    			else 
    				curr = curr.left;
    		}
    		return false;
    	}
    }

    /**
     * Insert the given key into the tree.
     * Duplicate keys should be ignored.
     * No Splaying should take place.
     */
    public void insert(T key) {
        // Your code here...
    	// check if the tree is empty
    	if(root == null){
    		root = new TreeNode<T>(key);
    	}
    	else{
    		TreeNode<T> currNode = root, prev = null;
    		while(currNode != null){
    			prev = currNode;
    			if(key.compareTo(currNode.key) < 0)
    				currNode = currNode.left;
    			else if(key.compareTo(currNode.key) > 0)
    				currNode = currNode.right;
    			else 
    				return;
    		}
    		if(key.compareTo(prev.key) < 0){
    			
    			prev.left = new TreeNode<T>(key);
    			prev.left.parent = prev;
    		}
    		else{
    			prev.right = new TreeNode<T>(key);
    			prev.right.parent = prev;
    		}
    	}
    }

    /**
     * Return the successor of the given key.
     * If the given key does not exist return null.
     * If the given key does not have a successor, return null.
     */
    public T findSuccessor(T key) {
        // Your code here...
    	// tree is empty
    	T returnKey = null;
    	if(root == null)return null;
    	else{
    		if(!contains(key)) return null;
    		// successor is the next key(right key ) in the list
    		TreeNode<T> curr = root;
    		while(curr != null && !curr.key.equals(key)){
    			if(curr.key.compareTo(key)<0)
    				curr = curr.right;
    			else 
    				curr = curr.left;
    		}
    		if(curr.key.equals(key)){
    			if(curr.right != null){
    				//returnKey = curr.right.key;
    				TreeNode<T> leftMost = curr.right;
    				while(leftMost.left != null)
    					leftMost = leftMost.left;
    				returnKey = leftMost.key;
    			}
    			else
    				return null;
    		}
    	}
    	return returnKey;
    }

    /**
     * Move the accessed key closer to the root using the semi-splaying strategy.
     * If the key does not exist, insert it without splaying
     * 
     * a move to root function if you will 
     */
    public void access(T key) {
        // Your code here...
    	if(root==null)return;
    	else if(!contains(key)){
    		insert(key);
    	}
    	else{
    		splay(key);
    	}
    }
    /*Helper Functions */
    //private 
    private TreeNode<T> temp = new TreeNode<T>(null);
    
    private void semisplay(T key){
    	/*A variation of the move to root strategy */
    	TreeNode<T> currNode, left, right;
    	left = right = temp;
    	temp.left = temp.right = null;
    	currNode = root;
    	while(currNode != null)
    	{
    		if(key.compareTo(currNode.key) < 0){
    			if(currNode.left == null)
    				break;
    			right.left = currNode;
    			right = currNode;
    			currNode = currNode.left;
    		}
    		else if(key.compareTo(currNode.key) > 0){
    			left.right = currNode;
    			left = currNode;
    			currNode = currNode.right;
    			 
    		}
    		else break;
    	}
    	left.right =  currNode.left;
    	right.left = currNode.right;
    	currNode.left = temp.right;
    	currNode.right = temp.left;
    	root = currNode;
    }
    
    private void splay(T key)
    {
    	TreeNode<T> currNode, left, right, pred;
    	currNode = root;
    	left = right = temp;
    	temp.left = temp.right = null;
    	while(currNode != null){
    		if(key.compareTo(currNode.key) < 0)
    		{
    			if(currNode.left == null) break;
    			if(key.compareTo(currNode.left.key) < 0){
    				pred = currNode.left;
    				currNode.left = pred.right;
    				pred.right = currNode; 
    				currNode = pred;
    				if(currNode.left == null) break;
    			}
    			right.left = currNode;
    			right = currNode;
    			currNode = currNode.left;
    		}
    		else if(key.compareTo(currNode.key) > 0){
    			if(currNode.right == null) break;
    			if(key.compareTo(currNode.right.key) > 0)
    			{
    				pred = currNode.right;
    				currNode.right = pred.left;
    				pred.left = currNode;
    				currNode = pred;
    				if(currNode.right == null) break;
    			}
    			left.right = currNode;
    			left = currNode;
    			currNode = currNode.right;
    		}
    		else break;
    	}
    	left.right = currNode.left;
    	right.left = currNode.right;
    	currNode.left = temp.right;
    	currNode.right = temp.left;
    	root = currNode;
    }
    
   
}