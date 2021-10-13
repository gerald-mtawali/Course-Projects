/**
 * Name: Gerald Mtawali 
 * Student Number: 15264999
 */

public class OrganisingList {

    public ListNode root = null;

    public OrganisingList() {

    }
    /**
     * Calculate the sum of keys recursively, starting with the given node
     * until the end of the list
     * @return The sum of keys from the current node to the last node in the list
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int sumRec(ListNode node) {
        // Your code here...
    	if(node.next == null){ // means we're at the end of the list 
    		node.data = node.key;
    		return node.key;
    	}
    	else{
    		node.data = sumRec(node.next) + node.key;
    		return node.data;
    	}	
    }

    /**
     * Calculate and set the data for the given node.
     * @return The calculated data for the given node
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int dataRec(ListNode node) {
        // Your code here...
    	if(node.next == null){
    		node.data = node.key;
    		return node.data;
    	}
    	else{
    		node.data = (node.key*sumRec(node) - dataRec(node.next));
    		return node.data;
    	}
    }

    /**
     * Calculate the data field of all nodes in the list using the recursive functions.
     * DO NOT MODIFY!
     */
    public void calculateData() {
        if (root != null) {
            dataRec(root);
        }
    }

    /**
     * Retrieve the data for the node with the specified key and move the
     * accessed node to the front and recalculate data fields.
     * @return The data of the node before it has been moved to the front,
     * otherwise 'null' if the key does not exist.
     */
    public Integer getData(Integer key) {
        // Your code here...
    	Integer foundkey = null;
    	if(root == null){return null;}  // empty tree 
    	else if(root.key.equals(key))	// first element is equal to what we want 
    	{
    		foundkey = root.data;
    		calculateData();
    	}
    	else
    	{
    		ListNode currNode = root;
    		ListNode pred = null;
    		// iterate through the list until we reach the node that has the key that we are looking for; or just go through the list 
    		while(currNode.next != null && !currNode.key.equals(key))
    		{
    			pred = currNode;
    			currNode = currNode.next;
    		}
    		// then we found the key, and send the node to the front then  
    		if(currNode.key.equals(key)){
    			foundkey = currNode.data;
    			ListNode tmp = currNode;		// the current node that becomes the root stored in temp
    			pred.next = currNode.next;		// set the pred's next to the current node next 
    			currNode.next = root;
    			root = tmp;						// the current node becomes the root 
    			//System.out.println("The root node is " + root.key + " And it's next value is  " + root.next.key);
    			calculateData();
    			
    		}
    	}
    	return foundkey;
    }

    /**
     * Delete the node with the given key.
     * calculateData() should be called after deletion.
     * If the key does not exist, do nothing.
     */
    public void delete(Integer key) {
        // Your code here... 
    	if(root == null){return;}	// empty list 
    	if(!contains(key)){return;} // element not in list 
    	else if(root.key.equals(key))	// first element is equal to what we want 
    	{
    		root = root.next;
    		calculateData();
    	}
    	else // one or more elements in the list 
    	{
    		//ListNode found = null;
    		ListNode currNode, pred;
    		currNode = root; 
    		pred = null;
    		while(currNode.next != null && !currNode.key.equals(key))
    		{
    			pred = currNode; 
    			currNode = currNode.next;
    		}

    		if(currNode.key.equals(key))
    		{
    			// means that the current node has the element we're looking for 
    			// make the previous node point to the next node of the current node 
	    		pred.next = currNode.next;
	    		currNode = null; 			// "delete" the node  
	    		calculateData();
    		}
    	}
    }

    /**
     * Insert a new key into the linked list.
     * 
     * New nodes should be inserted to the back
     * Duplicate keys should not be added.
     */
    public void insert(Integer key) {
        // Your code here...
    	if(root == null){root = new ListNode(key);} // empty list 
    	else if(contains(key)){
    		return;}
    	else{
    		ListNode currNode = root;
    		// iterate until we reach the last node and ensure that the key doesn't exist in the list 
    		while(currNode.next != null )
    		{
    			currNode = currNode.next;	
    		}
    		ListNode newNode = new ListNode(key);
    		currNode.next = newNode;
    		
    	}
    	calculateData();
    }

    /**
     * Check if a key is contained in the list
     * @return true if the key is in the list, otherwise false
     */
    public Boolean contains(Integer key) {
        // Your code here...
    	Boolean status = false; 
    	ListNode currNode = root; 
    	// iterate through the list 
    	if(root == null){status = false;}	// empty list 
    	else{
        	while(currNode.next != null && !currNode.key.equals(key))
        	{	
        		currNode = currNode.next;
        	}
        	if(currNode.key.equals(key)){status = true;}
    	}
    	return status;
    }
    
    /**
     * Return a string representation of the Linked List.
     * DO NOT MODIFY!
     */
    public String toString() {
        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result.concat("[K: " + node.key + ", D: " + node.data + "]");

            if (node.next != null) {
                result = result.concat(" ");
            }
        }

        return result;
    }

    /**
     * Return a string representation of the linked list, showing only the keys of nodes.
     * DO NOT MODIFY!
     */
    public String toStringKeysOnly() {

        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result + node.key;

            if (node.next != null) {
                result = result.concat(", ");
            }
        }
        return result;
    }

    
}
