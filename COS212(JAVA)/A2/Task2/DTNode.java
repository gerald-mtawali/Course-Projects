/*
Complete your details...
Name and Surname: Gerald Mtawali
Student/staff Number: 15264999
*/

public class DTNode<T extends Comparable<? super T>>
{
	/*
	TODO: You must implement a node class which would be appropriate to use with your trees.
	Methods and variables can be added.
	Names of the given variables must not be altered. 
	*/
	
	protected T data;
	protected DTNode<T> left, right; // left child and right child
	protected boolean hasLeftThread, hasRightThread; // flags that indicate whether the left and the right pointers are threads
	public DTNode(T dat)
	{
		/*DTNode constructor */
		data = dat;
		left = right = null;
		parent = null;
		hasLeftThread = hasRightThread = false;
	}
	
	/*EXTRAS*/
	protected DTNode<T> parent;
	
	public String toString(Boolean showChilds)
	{
		String output;
		output = " " + data + " ";
		if(showChilds && (left != null || right != null))
		{
			output = "  " + data + " \n" + left.data + "   " + right.data;
		}
		return output;
	}
	
}

