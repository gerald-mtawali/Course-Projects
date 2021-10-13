/*You must complete this class such that it can be used as nodes in a sparse table.
Read the comments to determine what each aspect of the class is supposed to do.
You may add any additional features (methods, references) which may aid you in your
task, BUT you are not allowed to remove or change the names or properties of any of 
the features you where given.

Importing Java's built in data structures will result in a mark of 0.*/

public class Event
{
	/*
	 * Events act as the node of the sparse table 
	 * */
	public Event(String descr)
	{
		/*You may implement this constructor to suit your needs, or you may add additional constructors.  This is the constructor which will be used for marking*/ 
		description = descr;
	}	
	

	public Event(String descr, Event d, Event r, Event u )
	{
		description = descr; 
		down = d;
		up = u;
		right = r;
		isMultiple = false;
	}
	
	public void setDescription(String descr)
	{
		/*Implement this method to set the description for this event*/
		description = descr;
	}	
	
	public String getDescription()
	{
		/*This method returns the description of this event*/
		return description;
	}
	
	
	
	// string representation of the Event 
	public String toString(){
		String evName = "Event Name: "+ this.getDescription();
		String evDayTime = "Day: " + this.getDay() + " Time " + this.getTime();
		return "[ " + evDayTime + " " + evName + " ]";
	}
	
	/*The setter functions
	public void setDown(Event d_ptr){down = d_ptr;}
	public void setUp(Event u_ptr){up = u_ptr;}
	public void setRight(Event r_ptr){right = r_ptr;}
	*/
	
	public void setTime(String t){time = t;}
	public void setDay(String dy){day = dy;}
	public void setDuration(int dur){duration = dur;}
	
	public int getDuration(){return duration;}
	public String getTime(){return time;}
	public String getDay(){return day;}
	
	public Event down; //The next event (down) of this event on the same day
	public Event up; //The previous event (up) of this event on the same day.
	public Event right;//The next event (right) of this event at the same time.
	public boolean isMultiple;// keeps track of whether this event is a multiple 
	
	
	private String description;//A description for this event.
	private String day; 
	private String time;
	private int duration;
	
	
}

