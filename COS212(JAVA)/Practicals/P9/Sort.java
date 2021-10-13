import java.lang.reflect.Array;
// Name:Gerald Mtawali 
// Student number: u15264999
import java.util.Arrays;

public class Sort
{
	
	////// Implement the functions below this line //////
	
	/********** MERGE **********/
	public static <T extends Comparable<? super T>> void mergesort(T[] data, int first, int last, boolean debug)
	{

		// Your code here
		/*After every iteration of the merge sort need to print the data array that's being sorted 
		 * */
		//System.out.println("The last(or mid) value of the merge sort is "+last+" \nthe first value is "+first);
		if( first < last)
		{
			int mid = first + (last-first)/2;
			mergesort(data,first,mid,debug);
			mergesort(data,mid+1,last,debug);
			merge(data,first,last,debug);
		}
	}
     
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> void merge(T[] data, int first, int last, boolean debug)
	{

		// Your code here
		T[] temp = (T[])Array.newInstance(Comparable.class, last+1);
		for(int i = first; i < last+1; i++)
			temp[i] = data[i];
		int mid = first+(last-first)/2;
		int idx1,idx2, idx3;
		idx1 = first;
		idx2 =first;
		idx3=mid+1;
		while(idx2 <= mid && idx3 <=last)
		{	
			if(temp[idx2].compareTo(temp[idx3]) < 1 ){
				data[idx1] = temp[idx2];
				idx1++;
				idx2++;
			}
			else{ 
				data[idx1] = temp[idx3];
				idx1++;
				idx3++;
			}
			//idx1++;
		}
		while(idx2 <= mid){
			data[idx1] = temp[idx2];
			//System.out.println("updating the rest of the data terms "+data[idx1]);
			idx1++;
			idx2++;
		}
		
		//DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}
     
	/********** COUNTING **********/
	public static void countingsort(int[] data, boolean debug)
	{

		// Your code here
		int m_value = data[0], d_size = data.length;
		int [] tmp = new int[d_size];
		for(int i = 1; i < d_size; i++)
			if(m_value < data[i])
				m_value = data[i];
		int [] count = new int[m_value+1];
		for(int i = 0; i < m_value; i++)
			count[i]=0;
		for(int i =0; i < d_size; i++)				// count the numbers in data 
			count[data[i]]++;
		for(int i = 1; i <= m_value; i++)
			count[i] = count[i-1]+count[i];
		for(int i = d_size-1; i >=0; i--){
			tmp[count[data[i]]-1]=data[i];
			count[data[i]]--;
		}
		for(int i = 0 ; i < d_size; i++)
			data[i] = tmp[i];
		//DO NOT MOVE OR REMOVE!
		if (debug)
			System.out.println(Arrays.toString(data));
	}

}