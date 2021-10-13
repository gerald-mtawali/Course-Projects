/*Complete this class to implement a fully functional sparse table.  Read the comments to determine what each aspect of the class is supposed to do.
You must add any additional features (methods, references) which may aid you in your
task BUT you are not allowed to remove or change the names or properties of any of the features you where given.

Importing Java's built in data structures will result in a mark of 0.*/

public class Schedule
{
	
	/*
	 * Two separate linked list structures, time and days with days = x-axis, time = y-axis
	 * The elements within list are sentinels; Day Sentinels only have the down pointer working, points to the first event of the day 
	 * Time Sentinels only have right pointer working,points to first event at that specific time 
	 * Elements within days form a circular list; where as the elements within the time form a singly linked list  
	 * 
	 * 
	 * */ 
	public Event days[] = new Event[7];			// store the day sentinels; x-axis 
	public Event times[] = new Event[33];			// store the time sentinels; y-axis
	
	int numOfEvents = 0;						// counter that keeps a number of events within the schedule 
	int maxEvents = 231;					// max number of events, can never exceed this number 
	
	// One multidimensional array 
	//Event[][] sparseTable = new Event[7][32];
	// Mutlidimensional Array for the Sparse Table
	public Schedule()
	{
		/*You may implement this constructor to suit your needs, or you may add additional constructors.  
		This is the constructor which will be used for marking*/ 
		/*
		 * When initiating the schedule class we start with empty x and y axis elements
		 * create the sentinel nodes and add them to the two event arrays 
		 * header nodes of each list are the nodes that sentinels point to 
		 * */
		String[] daysOfWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
		
		String[] timeOfDay = {"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", 
								"11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
								"16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", 
								"21:00", "21:30", "22:00"};		
		for(int i = 0; i < 7; i++){
			Event sentinel = new Event(daysOfWeek[i], null, null, null);			// these nodes have right and up set to null 
			days[i] = sentinel;
		}
		for(int i = 0; i < 33; i++)
		{
			Event sentinel = new Event(timeOfDay[i], null, null, null);		// have up and down set to null
			times[i] = sentinel;
		}
	}
	
	/*Insertion*/
	public void addEvent(String time, String day, String description)
	{
		/*Insert an event at the given time and day combination. 
		Assume the event is 30 minutes long and insert a single Event node.
		Description should be used to initialise the node.
		
		Duplicate events (events with the same description) are allowed.
		
		Time and Day dictate the index for which the event will enter the schedule
		Seeing as how there is no duration every event added with this method is only has a 30 min duration 
		If the table is empty need to insert into both event arrays 
		
		Cases: 
			1. if there is already an event at the desired time then we insert to the next time slot; if the next time 
				slot is filled, search until a slot has been found(keep going down, until the down is the header of that days
				index), then go to the next day(right) and add it at the first slot that is available on that day  
				
			2. the last timeslot value points to the first time slot value for every day
			
			
		Insert Procedure: 
		-First look for the day we want => check if that day is empty => Yes the day is empty, then look for the time slot that was wanted
		 	=> create the event for the day list=> set it's  up and down pointers accordingly => starting from the header of the time slot iterate right until 
		 	we have reached the previous day, does it have a right pointer? => yes it has a right pointer, make the right of the new node = prevnode.right then set prevnode.right = new node
		 	=> No the day is not empty, then start at the head node of that day (Days[idx].down) => keep iterating down until the timeslot, keep track of the previous node 
		 	=> set the pointers accordingly=> set the right pointer accordingly according to time 
		 -Insert at last node? 
		 	=> iterate down to the node at last time slot => set prev node.down to the new node(was previously pointing to the header node), set the down node of newnode to the header of that day 
		 	=> 
		*/
		if(numOfEvents == maxEvents){
			System.out.println("The schedule is full please delete an event");
			return;
		}
		//System.out.println("We are looking to add the event " + description+" at the time slot "+ time+" and the day "+ day);
		//look for the day we want 
		int dayIdx = dayIdx(day);
		// init the event that we are going to add 
		Event newEv = new Event(description);
		newEv.setDay(day);							
		newEv.setTime(time);
		// check if the day is empty
		if(dayIsEmpty(day))
		{
			int timeIdx = timeIdx(time);
			if(timeIsEmpty(time)){// the time slot is empty 
				
				days[dayIdx].down = newEv;					// set the head of the Days[idx] = newEv 
				// set the day pointers accordingly 
				newEv.up = newEv;
				newEv.down = newEv;
				// set the time pointers accordingly; since empty there is no need over complicate 
				times[timeIdx].right = newEv;
				newEv.right = null;
			}
			else{ // the time slot is not empty; there are other elements in the specific time slot 
				// check to see if the right pointer of the times[timeIdx] points to an event that occurs on a day after the one we are inserting to 
				int rightDayIdx = dayIdx(times[timeIdx].right.getDay());
				if(rightDayIdx > dayIdx){// the right pointer occurs after the day we're adding to
					newEv.right = times[timeIdx].right;  // set the new events right pointer to the right of times 
					times[timeIdx].right = newEv;
				}
				else{// the right pointer occurs before the day that we are on
					// iterate until we are at the day that occurs before the one we are entering 
					Event curr = times[timeIdx].right;
					while(curr.right != null)
					{
						if(dayIdx(curr.getDay()) < dayIdx)
							curr = curr.right;
						else 
							break;
					}
					// set the day pointers accordingly 
					days[dayIdx].down = newEv;
					newEv.up = newEv;
					newEv.down = newEv;
					// set the right pointers accordingly 
					newEv.right = curr.right;
					curr.right = newEv;
				}
			}
			numOfEvents+=1;// increment the number of elements 
		}
		else{
			// there are elements within the day 
			// need to scroll down to the element before the time we want to add 
			// 
			Event currEvent = days[dayIdx].down;
			Event prevEvent = null;
			int timeIdx = timeIdx(time);
			//Is there a clash for the first event of the day?
			if(currEvent.getTime().equals(time))
			{
				// need to check for clashes
				if(timeIdx == 32 && dayIdx == 6){// we have reached a Sunday at 22:00 clash case
					System.out.println("A Sunday 22:00 clash");
					this.addEvent(times[0].getDescription(), days[0].getDescription(), description);
					return;
				}
				else if(timeIdx == 32 && dayIdx != 6)
				{
					System.out.println("A 22:00 on any Day clash ");
					this.addEvent(times[0].getDescription(), days[dayIdx+1].getDescription(), description);
					return;
				}
				else
				{// a clash at the same time interval, should be added to the next one 
					System.out.println("Just a regular clash ");
					this.addEvent(times[timeIdx+1].getDescription(), day, description);
					return;
				}
			}
			else
			{// there is no clash for the first event of the day 
				// we iterate until we have reached an index that is before the one we want to add to 
				while(currEvent.down != days[dayIdx].down)
				{
					if(timeIdx(currEvent.getTime()) < timeIdx)
					{// the time index of our current index is less than the one we want 
						prevEvent = currEvent;
						currEvent = currEvent.down;
						//System.out.println("The previous event is " + prevEvent + "The current event is " + currEvent);
					}
					else if(timeIdx(currEvent.getTime()) > timeIdx)
					{
						// the index of the current event is greater than the index with which we want to add 
						// we have gone far one too many, we need to break the loop 
						System.out.println("We've Gone too Far..");
						break;
					}
					else
					{// the indexes perfectly match showing that there is a clash
						break;
					}
				}
				if(currEvent.getTime().equals(time))// there is a clash
				{
					// need to check for clashes
					if(timeIdx == 32 && dayIdx == 6){// we have reached a Sunday at 22:00 clash case
						System.out.println("A Sunday 22:00 clash");
						this.addEvent(times[0].getDescription(), days[0].getDescription(), description);
						return;
						}
					else if(timeIdx == 32 && dayIdx != 6)
					{
						System.out.println("A 22:00 on any Day clash ");
						this.addEvent(times[0].getDescription(), days[dayIdx+1].getDescription(), description);
						return;
					}
					else
					{// a clash at the same time interval, should be added to the next one 
						System.out.println("Just a regular clash ");
						this.addEvent(times[timeIdx+1].getDescription(), day, description);
						return;
					}	
				}
				else
				{// there is no clash can add as is 
					// set up the day pointers 
					//System.out.println("The current event is "+currEvent+" The previous event is "+prevEvent);
					
					// set up the time pointers
					if(timeIsEmpty(time))
					{// the time slot we want to add to is empty 
						//System.out.println("The empty timeslot is "+time + " "+timeIdx);
						//System.out.println("The current events down is "+currEvent.down);
						newEv.down = currEvent.down;
						newEv.up = currEvent;
						currEvent.down = newEv;
						//System.out.println("The new events down is "+newEv.down);
						//System.out.println("The current event's new down is "+ currEvent.down);
						//currEvent.down.up = newEv;
						times[timeIdx].right = newEv;
						//System.out.println("The timeslots new right is "+times[timeIdx].right);
						newEv.right = null;
					}
					else
					{// there are elements within the timeslot 
						// take the first index of the time slot 
						int firstDayIdx = dayIdx(times[timeIdx].right.getDay());
						if(firstDayIdx > dayIdx){// the right pointer occurs after the day we're adding to
							newEv.down = currEvent.down;
							newEv.up = currEvent;
							currEvent.down = newEv;
							//currEvent.down.up = newEv;
							newEv.right = times[timeIdx].right;  // set the new events right pointer to the right of times 
							times[timeIdx].right = newEv;
						}
						else{// the right pointer occurs before the day that we are on
							// iterate until we are at the day that occurs before the one we are entering 
							Event currTime = times[timeIdx].right;
							while(currTime.right != null){
								if(dayIdx(currTime.getDay()) < dayIdx)
									currTime = currTime.right;
								else 
									break;
							}
							newEv.down = currEvent.down;
							newEv.up = currEvent;
							currEvent.down = newEv;
							//currEvent.down.up = newEv;
							// set the right pointers accordingly 
							newEv.right = currTime.right;
							currTime.right = newEv;
						}
					}
				}
			}
			
		}
	}
	
	/*Insertion*/
	public void addEvent(String time, String day, String description, int duration)
	{
		/*Insert an event at the given time and day combination. 
		Description should be used to initialise the necessary nodes.
		Duration should be used to determine how many nodes with the same description have to be inserted.
		Assume only increments of 30 will be used, eg. 30, 60, 90, 120, etc.
		
		Duplicate events (events with the same description) are allowed.
		
		basically perform addEvent several times
		
		Test cases: 
		Check if there are events at the desired time slots for the length of duration 
			if they are all empty then we add with no regrets
		If there is a single event in one of the timeslots wanted, we go to the next available timeslot starting 
			from timeIdx+numOfEvents and check from there 
		*/
		int numOfEvents = duration/30;
		int timeIndex, dayIndex;
		timeIndex = timeIdx(time);
		dayIndex = dayIdx(day);
		int lastFreeSlot = 0;			// index of the last free time slot 
		boolean addStatus = true;		// indicates whether we can or cannot add to certain time slots 
		// iterate through the number of events, check to see if there is an event on the given day and at the given time 
		for(int i = 0; i < numOfEvents;  i++)
		{
			if(timeIndex+i > 32){// want to add at 22:00 on a certain day 
				addStatus = false;
				break;
			}
			if(this.getEvent(times[timeIndex+i].getDescription(), day) != null)
				addStatus = false;
			else if(this.getEvent(times[timeIndex+i].getDescription(), day) == null && this.getEvent(times[timeIndex+i-1].getDescription(), day) != null)
			{
				
				lastFreeSlot = timeIndex+i;
				//System.out.println("The last free slot is "+times[lastFreeSlot].getDescription());
				
			}
		}
		
		if(addStatus)// we can add to the time slot because there's enough space 
		{
			int i = 0; 
			while(i < numOfEvents)
			{
				// add the time when we want
				this.addEvent(times[timeIndex+i].getDescription(), day, description);
				this.getEvent(times[timeIndex+i].getDescription(), day).setDuration(duration);
				this.getEvent(times[timeIndex+i].getDescription(), day).isMultiple = true;// init the multiple variable 
				i+=1;
			}
		}
		else{// there is no space to add 
			// go to the next available time slot that will allow such and add it there 
			
			// if the time fall after 22:00 then we go to the next day and add on from 06:00
			if(dayIndex == 6 && timeIndex+numOfEvents > 32)
				this.addEvent(times[0].getDescription(), days[0].getDescription(), description, duration);
			// we are on sunday and the number of events goes over 22:00
			else if(timeIndex+numOfEvents > 32)
				this.addEvent(times[0].getDescription(), days[dayIndex+1].getDescription(), description, duration);
			else //just choose an available time that is numOfEvents later than the time we are trying to add to 
				this.addEvent(times[lastFreeSlot].getDescription(), day, description, duration);
		}	
	}
	
	/*Deletion methods*/
	public String deleteEvent(String time, String day)
	{
		/*Delete the event at the given time and day combination and return the description of the 
		deleted event. Note: all adjacent (up and down) events with the same description must also be deleted.
		
		If no such event exists, return null.
		*/
		if(dayIsEmpty(day)) return null;
		if(getEvent(time, day) == null) return null;
		else // there is an event 
		{
			
			Event deleteEvent = getEvent(time, day); // get the day that we want to delete 
			String returnString = deleteEvent.getDescription();
			System.out.println(returnString);
			// need to select the specific day 
			int dayIndex = dayIdx(day);
			int timeIndex = timeIdx(time);
			//Event currEvent = days[dayIndex].down;
			//Event prevEvent = null;
			// set the previous events 
			if(deleteEvent.isMultiple)// there are multiple events 
			{
				System.out.println("Entering the delete multiple events process....");
				// delete 30/duration of the delete event 
				int deleteCount = deleteEvent.getDuration()/30;
				// perform deletion deleteCount amount of times 
				for(int i = 0; i < deleteCount; i++){
					deleteEventPrivate(day, times[timeIndex+i].getDescription());
					System.out.println("The time we are deleting from is " + times[timeIndex].getDescription()+
							" The number of multiples is "+ deleteCount+" we are currently at "+times[timeIndex+i].getDescription());
				}
				return returnString;
			}
			else
			{
				System.out.println("Entering the delete a single event process....");
				deleteEventPrivate(day,time);
				return returnString;
			}
		}
	}
	
	public void deleteEvent(String description)
	{
		/*Delete all events that match the given description.*/
		
		// iterate through all the time slots 
		// if the description matches the one we have then we delete the element 
		System.out.println("Entering the Delete Event function ");
		if(isEmpty())return;
		if(findEvent(description) == null)return;
		else
		{
			for(int i =0; i < 7; i++)
			{
				//if(times[i].right.getDescription().equals(description))
					//deleteEventPrivat(times[i].rigt);
				if(dayIsEmpty(days[i].getDescription())) continue;
				Event currEvent = days[i].down;
				//System.out.println("The current event is "+currEvent.getDescription()+ " We are trying to delete "+description);
				Event prevEvent = null;
				if(currEvent.getDescription().equals(description)){
					deleteEventPrivate(currEvent.getDay(), currEvent.getTime());
					return;
				}
				while(currEvent.down != days[i].down){
					prevEvent = currEvent;
					currEvent = currEvent.down;
					if(prevEvent.getDescription().equals(description))
						deleteEventPrivate(prevEvent.getDay(), prevEvent.getTime());
				}
			}
		}
				
	}
	
	/*Clearing Methods*/
	public void clearByDay(String day)
	{
		/*All events for the given day should be deleted.
		If the day has no events, simply do nothing.*/
		System.out.println("Entering the Clear By Day function ");
		
		if(dayIsEmpty(day)){return;}
		
		int dayIndex = dayIdx(day);
		// iterate through the day column, does it have a right pointer
		Event currEvent = days[dayIndex].down;
		Event prevEvent=null;
		if(currEvent.down == currEvent)
		{
			deleteEventPrivate(day, currEvent.getTime());
			return;
		}
		while(currEvent.down != days[dayIndex].down){
			System.out.println(days[dayIndex].down);
			prevEvent = currEvent;
			currEvent = currEvent.down;
			System.out.println("The previous event to be deleted is "+prevEvent.toString());
			System.out.println("The current event is "+currEvent.toString());
			deleteEventPrivate(day, prevEvent.getTime());
		}
	}
	
	public void clearByTime(String time)
	{
		/*All events for the given time should be deleted.
		If the time has no events, simply do nothing.*/
		System.out.println("Now entering the clear by time function ");
		if(timeIsEmpty(time)) return;
		int timeIndex = timeIdx(time);
		Event currEvent = times[timeIndex].right;
		Event prevEvent = null;
		//System.out.println("The current time is "+time);
		if(currEvent.right == null)
		{
			//System.out.println("Enter Here");
			deleteEventPrivate(currEvent.getDay(),time);
			return;
		}	
		while(currEvent != null)
		{
			//System.out.println("Enter THere");
			prevEvent = currEvent;
			currEvent = currEvent.right;
			deleteEventPrivate(prevEvent.getDay(), time);
		}
	}
	
	public void clearAll()
	{
		/*Delete all events from the schedule.*/
		System.out.println("Entering the Clear All events function");
		if(isEmpty()) return;
		for(int i = 0; i < 33; i++)
		{
			clearByTime(times[i].getDescription());
			/*
			Event currEvent = days[i].down;
			Event prevEvent = null;
			if(currEvent != null && currEvent.down.equals(currEvent))// if there is one element for the day 
			{
				//currEvent = null;
				deleteEventPrivate(currEvent.getDay(), currEvent.getTime());
				continue;
			}
			int j =0;
			while(j < this.dayCount(days[i].getDescription()))
			{
				prevEvent = currEvent; 
				currEvent = currEvent.down;
				deleteEventPrivate(prevEvent.getDay(), prevEvent.getTime());
				j+=1;
				
			}
			*/
		}
	}
	
	
	/*Query methods*/
	public Event getEvent(String time, String day)
	{
		/*Return the first event for the given time and day combination.  
		If no such event exists, return null*/
		int dayIndex = dayIdx(day);
		if(dayIsEmpty(day)) return null;
		if(days[dayIndex].down.getTime().equals(time)) // the first element is what we are looking for 
			return days[dayIndex].down;
		else{
			Event currEv = days[dayIndex].down;
			//System.out.println("The current event in the get Event "+currEv);
			while(currEv.down != days[dayIndex].down){
				//System.out.println("The day right now is "+ day +" The event right now is "+currEv.getDescription()+ " at time " + currEv.getTime());
				//System.out.println("The next event on this day is "+currEv.down.getDescription()+" at "+currEv.down.getTime());
				if(currEv.getTime().equals(time))
					return currEv;
				else
					currEv = currEv.down;
			}
			//System.out.println("The current event we about to get is "+currEv.getDescription());
			if(currEv.getTime().equals(time))
				return currEv;
			return null;
		}
	}

	public Event findEvent(String description)
	{
		/*Return the first event that matches the given description.  
		If no such event exists, return null*/
		
		// approach on a day by day step through 
		// start on the Monday, check all the events for that day(currEc.down), if we reach the last day in the index traverse to the next day 
		// repeat process again
		if(isEmpty()) return null;
		else
		{
			/*
			for(int i = 0; i < 7; i++)
			{
				System.out.println("The description we are looking for is "+ description);
				if(dayIsEmpty(days[i].getDescription()))
					continue;
				Event currEvent = days[i].down;
				
				// while we haven't found the event we're looking for and we havent reached the last event in the day
				while(currEvent.down != days[i].down)
				{
					System.out.println("The description of the current event is "+currEvent.getDescription());
					if(currEvent.getDescription().equals(description))
						return currEvent;
					currEvent = currEvent.down;
				}	
			}
			*/
			
			for(int i = 0; i < 33; i++)
			{
				//System.out.println("The description we are looking for is "+ description);
				if(timeIsEmpty(times[i].getDescription()))
					continue;
				Event currEvent = times[i].right;
				while(currEvent != null){
					//System.out.println("The description of the current event is "+currEvent.getDescription());
					if(currEvent.getDescription().equals(description))
						return currEvent;
					currEvent = currEvent.right;
				}
			}
			
			return null;
		}
	}
	
	public Event getTimeEvent(String time)
	{
		/*Return the head event for the time passed as a parameter.
		If no such event exists, return null*/
		if(timeIsEmpty(time)) return null;
		int timeIndex = timeIdx(time);
		if(times[timeIndex].right != null)
			return times[timeIndex].right;
		else
			return null;
	}
	
	public Event getDayEvent(String day)
	{
		/*Return the head event for the day passed as a parameter.
		If no such event exists, return null*/
		if(dayIsEmpty(day)) return null;
		int dayIndex = dayIdx(day);
		if(days[dayIndex].down != null)
			return days[dayIndex].down;
		else
			return null;
	}
	
	/*Helper Functions*/
	
	/*A delete function that is able to delete an event recursively */
	private void deleteEventPrivate(String day, String time)
	{
		Event deleteEvent = getEvent(time, day); // get the day that we want to delete 
		System.out.println("The event to be deleted is "+deleteEvent);
		// need to select the specific day 
		int dayIndex = dayIdx(day);
		Event currEvent = days[dayIndex].down;// take the header of the specific day 
		Event prevEvent = null;
		
		int timeIndex = timeIdx(time);
		Event currTime = times[timeIndex].right;// take the header for that timeslot 
		System.out.println(currTime);
		Event prevTime = null;
		if(deleteEvent == null)return;
		//We know that the specific event is at a specific time of the day 
		if(currEvent.equals(deleteEvent))
		{// this occurs when the head of that day is the event being deleted 
			
			//System.out.println("the current event is "+ currEvent.getDescription()+ " at the time "+ currEvent.getTime());
			if(currTime == null)
			{
				days[dayIndex].down = currEvent.down;
				currEvent.down.up = currEvent.up;
				System.out.println("We in here ");
				currEvent = null;
				deleteEvent = null;
				return;
			}
			else if(currTime.equals(deleteEvent))// first time slot equals the event we want
			{
				days[dayIndex].down = currEvent.down;
				currEvent.down.up = currEvent.up;
				System.out.println("The event that we are deleting is "+currEvent.getDescription()+ " it occurs at time "+ currEvent.getTime()+" on the day "+currEvent.getDay());
				// apply the time pointers accordingly 
				times[timeIndex].right = currEvent.right;
				currEvent = null;// set the event to null
				currTime = null;
				deleteEvent = null;
				return;
			}
			else// the time slot lies somewhere in the list
			{
				
				System.out.println("The current time is "+currTime);
				while(currTime != null)
				{
					if(currTime.equals(deleteEvent))
						break;
					prevTime = currTime;
					currTime = currTime.right;
				}
				System.out.println(prevTime);
				days[dayIndex].down = currEvent.down;
				currEvent.down.up = currEvent.up;
				prevTime.right = currEvent.right;
				currEvent = null;
				currTime = null;
				deleteEvent = null;
				return;
			}
		}
		else
		{// if the event is somewhere down the days list 
			while(currEvent.down != days[dayIndex].down){
				if(currEvent.equals(deleteEvent))
					break;
				prevEvent = currEvent;
				currEvent = currEvent.down;
				//System.out.println("The prev event is "+ prevEvent.getDescription()+"The current event is "+ currEvent.getDescription());
			}
			if(currTime == null)return;
			if(currTime.equals(deleteEvent))// first time slot equals the event we want
			{
				prevEvent.down = currEvent.down;		// set the previous node to current nodes down 
				currEvent.down.up = currEvent.up;		// set the event below's up pointer to current events one
				
				// apply the time pointers accordingly 
				times[timeIndex].right = currTime.right;
				currEvent = null;// set the event to null
				currTime = null;
				deleteEvent = null;
				return;
			}
			else// the time slot lies somewhere in the list
			{
				while(currTime != null)
				{
					if(currTime.equals(deleteEvent))
						break;
					prevTime = currTime;
					currTime = currTime.right;
				}
				
				prevEvent.down = currEvent.down;		// set the previous node to current nodes down 
				currEvent.down.up = currEvent.up;		// set the event below's up pointer to current events one
				prevTime.right = currTime.right;
				currEvent = null;
				currTime = null;
				return;
				//deleteEvent = null;
			}
		}
	}
	public boolean isEmpty()
	{
		/*
		 * iterate through both lists, if there is a an element then it is not empty 
		 * other wise it's empty
		 * */
		boolean status = true;
		for(int i = 0; i < 7; i++){
			if(days[i].down != null) status = false;
		}
		for(int j = 0; j < 33; j++){
			if(times[j].right != null) status = false;
		}
		return status;
	}
	private boolean dayIsEmpty(String day)
	{
		/*
		 * Returns true if the day related to the index is empty 
		 * */
		int idx = dayIdx(day);
		if(idx < 7)
			return days[idx].down == null;
		else 
			return false;
	}
	
	private boolean timeIsEmpty(String time){
		int idx = timeIdx(time);
		if(idx < 33)
			return times[idx].right == null;
		else 
			return false;
	}
	
	private int dayIdx(String Day)
	{
		/*returns the index corresponding to the specific day */
		int idx;
		switch(Day.toUpperCase()){
			case "MON": 
				idx = 0; 
				break;
			case "TUE": 
				idx = 1; 
				break;
			case "WED": 
				idx = 2; 
				break;
			case "THU": 
				idx = 3; 
				break;
			case "FRI": 
				idx = 4;
				break;
			case "SAT": 
				idx = 5;
				break;
			case "SUN": 
				idx = 6; 
				break;
			default: 
				idx = -1;
		}
		return idx;
	}
	
	
	private int timeIdx(String time)
	{
		int idx;
		switch(time.toUpperCase()){
			case "06:00": 
				idx = 0;
				break;
			case "06:30": 
				idx = 1;
				break;
			case "07:00": 
				idx = 2;
				break;
			case "07:30": 
				idx = 3;
				break;
			case "08:00":
				idx = 4;
				break;
			case "08:30": 
				idx = 5;
				break;
			case "09:00":
				idx = 6;
				break;
			case "09:30":
				idx = 7;
				break;
			case "10:00":
				idx = 8;
				break;
			case "10:30":
				idx = 9;
				break;
			case "11:00":
				idx = 10;
				break;
			case "11:30":
				idx = 11;
				break;
			case "12:00":
				idx = 12;
				break;
			case "12:30":
				idx = 13;
				break;
			case "13:00":
				idx = 14;
				break;
			case "13:30":
				idx = 15;
				break;
			case "14:00":
				idx = 16;
				break;
			case "14:30":
				idx = 17;
				break;
			case "15:00":
				idx = 18;
				break;
			case "15:30":
				idx = 19;
				break;
			case "16:00":
				idx = 20;
				break;
			case "16:30":
				idx = 21;
				break;
			case "17:00":
				idx = 22;
				break;
			case "17:30":
				idx = 23;
				break;
			case "18:00":
				idx = 24;
				break;
			case "18:30":
				idx = 25;
				break;
			case "19:00":
				idx = 26;
				break;
			case "19:30":
				idx = 27;
				break;
			case "20:00":
				idx = 28;
				break;
			case "20:30":
				idx = 29;
				break;
			case "21:00":
				idx = 30;
				break;
			case "21:30":
				idx = 31;
				break;
			case "22:00":
				idx = 32;
				break;
			default: 
				idx = -1;
		}
		return idx;
	}
	
	/*returns the number of event for a specific day */
	private int dayCount(String day){
		int dayIndex = dayIdx(day);
		int count =0;
		Event currEv = days[dayIndex].down;
		while(currEv.down.down != days[dayIndex].down){
			count +=1;
			currEv = currEv.down;
		}
		return count;
	}

	/*returns the number of events that week for a specific timeslot*/
	private int timeCount(String time_){
		int timeIndex = timeIdx(time_);
		int count =0;
		Event currEv = times[timeIndex];
		while(currEv.right != null){
			count+=1;
			currEv = currEv.right;
		}
		return count;
	}
}

