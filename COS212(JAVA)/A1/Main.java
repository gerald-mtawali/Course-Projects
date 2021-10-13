public class Main
{
	/*Use this class to test your implementation.  This file will be overwritten for marking purposes.*/




	public static void main(String[] args)
	{
		//Disclaimer: This file is by no means a perfect test for your program and should not be your only method of testing your code!
		//This main assumes the use of getTime() and getDay() methods in the event class, other implementations are possible, where this main will not work as intended.

		
		boolean outputIndv = true;
		String lLine = "______________________________________________________________________________________________________________________________________";

		System.out.println("/////////////////////////////////////////////////////////////////////////////////\n\t\t\tTesting Inserting Single Events\n");
		Schedule sched1 = new Schedule();
		sched1.addEvent("06:00","Mon", "Task1.00");
		Event temp = sched1.getEvent("06:00","Mon");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());

		sched1.addEvent("08:00","mon", "Task2.00");
		temp = sched1.getEvent("08:00","Mon");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());

		sched1.addEvent("08:00","Wed", "Task3.00");
		temp = sched1.getEvent("08:00","Wed");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
		System.out.println(lLine+"\nCheck if event is placed in the next time slot:");
		sched1.addEvent("08:00","Mon", "Task4.00");
		temp = sched1.getEvent("08:30","Mon");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay()+"\n"+lLine);

		sched1.addEvent("22:00","Mon", "Task5.00");
		temp = sched1.getEvent("22:00","Mon");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());


		sched1.addEvent("22:00","Mon", "Task6.00");
		temp = sched1.getEvent("06:00","Tue");
		System.out.println(lLine+"\nCheck if event is placed in the next time slot on the following day:");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay()+"\n"+lLine);

		sched1.addEvent("22:00","Sun", "Task7.00");
		temp = sched1.getEvent("22:00","Sun");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());

		sched1.addEvent("22:00","Sun", "Task8.00");
		temp = sched1.getEvent("06:30","Mon");
		System.out.println(lLine+"\nCheck if event is placed in the next time slot on the following day(Sunday Overflow):");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay()+"\n"+lLine);


		System.out.println("\n/////////////////////////////////////////////////////////////////////////////////\n\t\t\tTesting Inserting Extended Events\n");
		System.out.println(lLine+"\nChecking insert of standard long event:");
		sched1.addEvent("06:30","Thu", "LongTask1",120);
		temp = sched1.getEvent("06:30","Thu");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
		temp = sched1.getEvent("07:00","Thu");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
		temp = sched1.getEvent("07:30","Thu");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
		temp = sched1.getEvent("08:00","Thu");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
		System.out.println(lLine);

		System.out.println("\n/////////////////////////////////////////////////////////////////////////////////\n\t\t\tTesting Inserting Extended Events\n");
		System.out.println(lLine+"\nChecking insert of long event in occupied slot case1:");
		sched1.addEvent("07:30","Thu", "LongTask2",120);
		temp = sched1.getEvent("08:30","Thu");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
		temp = sched1.getEvent("09:00","Thu");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
		temp = sched1.getEvent("09:30","Thu");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
		temp = sched1.getEvent("10:00","Thu");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
		System.out.println(lLine);

		System.out.println("\n/////////////////////////////////////////////////////////////////////////////////\n\t\t\tTesting Inserting Extended Events\n");
		System.out.println(lLine+"\nChecking insert of long event in occupied slot case2:");
		sched1.addEvent("22:00","Mon", "LongTask3",240);
		temp = sched1.getEvent("06:30","Tue");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
		temp = sched1.getEvent("07:00","Tue");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
		temp = sched1.getEvent("07:30","Tue");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
		temp = sched1.getEvent("08:00","Tue");
		System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
		System.out.println(lLine);
		System.out.println("\n/////////////////////////////////////////////////////////////////////////////////\n\t\t\tTesting Inserting Extended Events\n");

		System.out.println(lLine+"\nChecking insert of long event in occupied slot case3:");
		sched1.addEvent("22:00","Sun", "LongTask4",240);
		if(outputIndv)
		{
			temp = sched1.getEvent("09:00","Mon");
			System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
			temp = sched1.getEvent("09:30","Mon");
			System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
			//System.out.println(sched1.toString());
			for (int i = 0; i < 3; i++) 
			{
				if(sched1.getEvent((10+i)+":00","Mon") != null)
				{
					temp = sched1.getEvent((10+i)+":00","Mon");
					System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
				}
				if(sched1.getEvent((10+i)+":30","Mon") != null)
				{
					temp = sched1.getEvent((10+i)+":30","Mon");
					System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
				}
			}
		}
		if(outputIndv)
		{
			System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
			System.out.println(lLine);


			System.out.println(lLine+"\nChecking insert of long event in occupied slot case4:");
		}
	
		sched1.addEvent("22:00","Sun", "LongTask5",600);
		
		
		
		if(outputIndv)
		{
			for (int i = 0; i < 10; i++) 
			{
				temp = sched1.getEvent((10+i)+":30","Tue");
				System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
				temp = sched1.getEvent((11+i)+":00","Tue");
				System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
			}

			//System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
			//System.out.println("Event found: "+temp.getDescription() + " Time: "+temp.getTime()+" Day: "+temp.getDay());
			System.out.println(lLine);
		}
		sched1.addEvent("06:00", "wEd", "Task8.00");
		sched1.addEvent("06:00", "wEd", "Task2.00");
		sched1.addEvent("20:30", "SUN", "Task2.00");
		
		//sched1.addEvent("20:37", "Fri", "TaskShort1");
		//sched1.addEvent("21:37", "Sat", "Tasklong1",120);
		
		//System.out.println(sched1.printUp());
		
		//System.out.println(sched1.toString());
		
		sched1.clearByTime("10:00");
		
		//System.out.println(sched1.toString());
		
		System.out.println(lLine+"\nChecking Querying using description:");
		temp = sched1.findEvent("LongTask5");
		if(temp != null)
		{
			System.out.println("Event found at "+temp.getTime()+" on "+temp.getDay()+" with description: "+temp.getDescription());
		}
		temp = sched1.findEvent("LongTask4");
		if(temp != null)
		{
			System.out.println("Event found at "+temp.getTime()+" on "+temp.getDay()+" with description: "+temp.getDescription());
		}
		temp = sched1.findEvent("Task5.00");
		if(temp != null)
		{
			System.out.println("Event found at "+temp.getTime()+" on "+temp.getDay()+" with description: "+temp.getDescription());
		}
		temp = sched1.findEvent("Task8.00");
		if(temp != null)
		{
			System.out.println("Event found at "+temp.getTime()+" on "+temp.getDay()+" with description: "+temp.getDescription());
		}
		temp = sched1.findEvent("Task2.00");
		if(temp != null)
		{
			System.out.println("Event found at "+temp.getTime()+" on "+temp.getDay()+" with description: "+temp.getDescription());
		}
		temp = sched1.findEvent("Task7.00");
		if(temp != null)
		{
			System.out.println("Event found at "+temp.getTime()+" on "+temp.getDay()+" with description: "+temp.getDescription());
		}
		System.out.println(lLine);
		
		System.out.println(lLine+"\nChecking Querying using time:");
		temp = sched1.getTimeEvent("08:00");
		if(temp!= null)
			System.out.println("Event found at "+temp.getTime()+" on "+temp.getDay()+" with description: "+temp.getDescription());
		temp = sched1.getTimeEvent("07:00");
		if(temp!= null)
			System.out.println("Event found at "+temp.getTime()+" on "+temp.getDay()+" with description: "+temp.getDescription());
		temp = sched1.getTimeEvent("20:30");
		if(temp!= null)
			System.out.println("Event found at "+temp.getTime()+" on "+temp.getDay()+" with description: "+temp.getDescription());
		
		System.out.println(lLine);
		
		System.out.println(lLine+"\nChecking Querying using day:");
		temp = sched1.getDayEvent("Mon");
		if(temp!= null)
			System.out.println("Event found at "+temp.getTime()+" on "+temp.getDay()+" with description: "+temp.getDescription());
		temp = sched1.getDayEvent("Wed");
		if(temp!= null)
			System.out.println("Event found at "+temp.getTime()+" on "+temp.getDay()+" with description: "+temp.getDescription());
		temp = sched1.getDayEvent("Sun");
		if(temp!= null)
			System.out.println("Event found at "+temp.getTime()+" on "+temp.getDay()+" with description: "+temp.getDescription());
		
		System.out.println(lLine);
		
		
		
		
		//System.out.println(sched1.toString());
		
		
		

		System.out.println(lLine+"\nChecking Delete of long event in occupied slot case1(First Node):");
		System.out.println("Event deleted with Description: "+sched1.deleteEvent("06:00", "Tue"));
		//System.out.println(sched1.toString());
		System.out.println(lLine);
		System.out.println(lLine+"\nChecking Delete of long event in occupied slot case2(Last Node):");
		System.out.println("Event deleted with Description: "+sched1.deleteEvent("11:30", "Tue"));
		//System.out.println(sched1.toString());
		System.out.println(lLine);
		System.out.println(lLine+"\nChecking Delete of long event in occupied slot case2(Last Node):");
		System.out.println("Event deleted with Description: "+sched1.deleteEvent("11:00", "Mon"));
		System.out.println(lLine);


		
		System.out.println(lLine+"\nChecking Delete of all events with description(\"Task3.00\"):");

		sched1.addEvent("11:30","Mon" , "Task3.00");
		sched1.addEvent("13:30","Tue" , "Task3.00");
		sched1.addEvent("12:00","Wed" , "Task3.00");
		sched1.addEvent("22:00","Thu" , "Task3.00");
//System.out.println(sched1.toString());
		sched1.deleteEvent("Task3.00");
		//System.out.println(sched1.toString());

		if(sched1.getEvent("11:30", "Mon") == null)
			System.out.println("Delete 1 successful!");
		if(sched1.getEvent("13:30", "Tue") == null)
			System.out.println("Delete 2 successful!");
		if(sched1.getEvent("12:00", "Wed") == null)
			System.out.println("Delete 3 successful!");
		if(sched1.getEvent("22:00", "Thu") == null)
			System.out.println("Delete 4 successful!");
		System.out.println(lLine);



		sched1.addEvent("09:30","Mon" , "Task3.00");
		sched1.addEvent("10:00","Mon" , "Task3.00");
		sched1.addEvent("10:30","Mon" , "Task3.00");

		System.out.println(lLine+"\nChecking clearByDay(\"Mon\"):");
		boolean out = true;
		/*
		sched1.clearByDay("Mon");
		
		for (int i = 0; i < 33; i++) {
			if(i < 4)
			{
				if(sched1.getEvent("0"+(6+i)+":00", "Mon") != null)
				{
					System.out.println("Failed to clear event at:"+"0"+(6+i)+":00");
					out = false;
				}
				if(sched1.getEvent("0"+(6+i)+":30", "Mon") != null)
				{
					System.out.println("Failed to clear event at:"+"0"+(6+i)+":30");
					out = false;
				}
			}
			if(i >= 4)
			{
				if(sched1.getEvent((6+i)+":00", "Mon") != null)
				{
					System.out.println("Failed to clear event at:"+(6+i)+":00");
					out = false;
				}
				if(sched1.getEvent((6+i)+":30", "Mon") != null)
				{
					System.out.println("Failed to clear event at:"+(6+i)+":30");
					out = false;
				}
			}
		}
		if(out)
			System.out.println("Clear By Day Successful!");
		System.out.println(lLine);

		*/
		sched1.addEvent("07:30", "Tue", "Cos Prac1");
		sched1.addEvent("07:30", "Fri", "Cos Prac1");
		sched1.addEvent("07:30", "Sun", "Cos Prac1");

		System.out.println(lLine+"\nChecking clearByTime(\"07:30\"):");
		//System.out.println(sched1.toString());
		//System.out.println(sched1.printTime("06:00"));
		//System.out.println(sched1.printTime("07:30"));
		
		sched1.addEvent("08:30", "Wed", "Cos 212As");
		//System.out.println(sched1.toString());
		//System.out.println("Deleted?" +sched1.deleteEvent("06:00", "fri"));
		//System.out.println(sched1.deleteEvent("08:30", "wed"));
		sched1.clearByTime("07:30");
		//System.out.println(sched1.printTime("07:30"));
		//System.out.println(sched1.printTime("06:00"));
		//System.out.println(sched1.printUp());
		//System.out.println(sched1.toString());
		//System.out.println("cleared by time without infinite loop?");
		out = true;
		if(sched1.getEvent("07:30", "Mon") != null)
		{
			System.out.println("Failed to clear event at:"+"07:30 On: "+"Mon");
			out = false;
		}
		if(sched1.getEvent("07:30", "Tue") != null)
		{
			System.out.println("Failed to clear event at:"+"07:30 On: "+"Tue");
			out = false;
		}
		if(sched1.getEvent("07:30", "Wed") != null)
		{
			System.out.println("Failed to clear event at:"+"07:30 On: "+"Wed");
			out = false;
		}
		if(sched1.getEvent("07:30", "Thu") != null)
		{
			System.out.println("Failed to clear event at:"+"07:30 On: "+"Thu");
			out = false;
		}
		if(sched1.getEvent("07:30", "Fri") != null)
		{
			System.out.println("Failed to clear event at:"+"07:30 On: "+"Fri");
			out = false;
		}
		if(sched1.getEvent("07:30", "Sat") != null)
		{
			System.out.println("Failed to clear event at:"+"07:30 On: "+"Sat");
			out = false;
		}
		if(sched1.getEvent("07:30", "Sun") != null)
		{
			System.out.println("Failed to clear event at:"+"07:30 On: "+"Sun");
			out = false;
		}


		if(out)
			System.out.println("Clear By Time Successful!");
		System.out.println(lLine);


		System.out.println(lLine+"\nChecking clearAll");
		String[] days = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};

		sched1.clearAll();
		for (int i = 0; i < 33; i++) 
		{
			for(int j = 0; j < 7;j++)
			{
				if(i < 4)
				{
					if(sched1.getEvent("0"+(6+i)+":00", days[j]) != null)
					{
						System.out.println("Failed to clear event at:"+"0"+(6+i)+":00"+" On: "+days[j]);
						out = false;
					}
					if(sched1.getEvent("0"+(6+i)+":30", days[j]) != null)
					{
						System.out.println("Failed to clear event at:"+"0"+(6+i)+":30"+" On: "+days[j]);
						out = false;
					}
				}
				if(i >= 4)
				{
					if(sched1.getEvent((6+i)+":00", days[j]) != null)
					{
						System.out.println("Failed to clear event at:"+(6+i)+":00"+" On: "+days[j]);
						out = false;
					}
					if(sched1.getEvent((6+i)+":30", days[j]) != null)
					{
						System.out.println("Failed to clear event at:"+(6+i)+":30"+" On: "+days[j]);
						out = false;
					}
				}
			}
		}
		if(out)
			System.out.println("Clear All Successful!");
		System.out.println(lLine);
		
		
		
		/*
//Expected Output
		
/////////////////////////////////////////////////////////////////////////////////
			Testing Inserting Single Events

Event found: Task1.00 Time: 06:00 Day: Mon
Event found: Task2.00 Time: 08:00 Day: Mon
Event found: Task3.00 Time: 08:00 Day: Wed
______________________________________________________________________________________________________________________________________
Check if event is placed in the next time slot:
Event found: Task4.00 Time: 08:30 Day: Mon
______________________________________________________________________________________________________________________________________
Event found: Task5.00 Time: 22:00 Day: Mon
______________________________________________________________________________________________________________________________________
Check if event is placed in the next time slot on the following day:
Event found: Task6.00 Time: 06:00 Day: Tue
______________________________________________________________________________________________________________________________________
Event found: Task7.00 Time: 22:00 Day: Sun
______________________________________________________________________________________________________________________________________
Check if event is placed in the next time slot on the following day(Sunday Overflow):
Event found: Task8.00 Time: 06:30 Day: Mon
______________________________________________________________________________________________________________________________________

/////////////////////////////////////////////////////////////////////////////////
			Testing Inserting Extended Events

______________________________________________________________________________________________________________________________________
Checking insert of standard long event:
Event found: LongTask1 Time: 06:30 Day: Thu
Event found: LongTask1 Time: 07:00 Day: Thu
Event found: LongTask1 Time: 07:30 Day: Thu
Event found: LongTask1 Time: 08:00 Day: Thu
______________________________________________________________________________________________________________________________________

/////////////////////////////////////////////////////////////////////////////////
			Testing Inserting Extended Events

______________________________________________________________________________________________________________________________________
Checking insert of long event in occupied slot case1:
Event found: LongTask2 Time: 08:30 Day: Thu
Event found: LongTask2 Time: 09:00 Day: Thu
Event found: LongTask2 Time: 09:30 Day: Thu
Event found: LongTask2 Time: 10:00 Day: Thu
______________________________________________________________________________________________________________________________________

/////////////////////////////////////////////////////////////////////////////////
			Testing Inserting Extended Events

______________________________________________________________________________________________________________________________________
Checking insert of long event in occupied slot case2:
Event found: LongTask3 Time: 06:30 Day: Tue
Event found: LongTask3 Time: 07:00 Day: Tue
Event found: LongTask3 Time: 07:30 Day: Tue
Event found: LongTask3 Time: 08:00 Day: Tue
______________________________________________________________________________________________________________________________________

/////////////////////////////////////////////////////////////////////////////////
			Testing Inserting Extended Events

______________________________________________________________________________________________________________________________________
Checking insert of long event in occupied slot case3:
Event found: LongTask4 Time: 09:00 Day: Mon
Event found: LongTask4 Time: 09:30 Day: Mon
Event found: LongTask4 Time: 10:00 Day: Mon
Event found: LongTask4 Time: 10:30 Day: Mon
Event found: LongTask4 Time: 11:00 Day: Mon
Event found: LongTask4 Time: 11:30 Day: Mon
Event found: LongTask4 Time: 12:00 Day: Mon
Event found: LongTask4 Time: 12:30 Day: Mon
Event found: LongTask4 Time: 12:30 Day: Mon
______________________________________________________________________________________________________________________________________
______________________________________________________________________________________________________________________________________
Checking insert of long event in occupied slot case4:
Event found: LongTask5 Time: 10:30 Day: Tue
Event found: LongTask5 Time: 11:00 Day: Tue
Event found: LongTask5 Time: 11:30 Day: Tue
Event found: LongTask5 Time: 12:00 Day: Tue
Event found: LongTask5 Time: 12:30 Day: Tue
Event found: LongTask5 Time: 13:00 Day: Tue
Event found: LongTask5 Time: 13:30 Day: Tue
Event found: LongTask5 Time: 14:00 Day: Tue
Event found: LongTask5 Time: 14:30 Day: Tue
Event found: LongTask5 Time: 15:00 Day: Tue
Event found: LongTask5 Time: 15:30 Day: Tue
Event found: LongTask5 Time: 16:00 Day: Tue
Event found: LongTask5 Time: 16:30 Day: Tue
Event found: LongTask5 Time: 17:00 Day: Tue
Event found: LongTask5 Time: 17:30 Day: Tue
Event found: LongTask5 Time: 18:00 Day: Tue
Event found: LongTask5 Time: 18:30 Day: Tue
Event found: LongTask5 Time: 19:00 Day: Tue
Event found: LongTask5 Time: 19:30 Day: Tue
Event found: LongTask5 Time: 20:00 Day: Tue
______________________________________________________________________________________________________________________________________
______________________________________________________________________________________________________________________________________
Checking Querying using description:
Event found at 10:30 on Tue with description: LongTask5
Event found at 22:00 on Mon with description: Task5.00
Event found at 06:30 on Mon with description: Task8.00
Event found at 08:00 on Mon with description: Task2.00
Event found at 22:00 on Sun with description: Task7.00
______________________________________________________________________________________________________________________________________
______________________________________________________________________________________________________________________________________
Checking Querying using time:
Event found at 08:00 on Mon with description: Task2.00
Event found at 07:00 on Thu with description: LongTask1
Event found at 20:30 on Sun with description: Task2.00
______________________________________________________________________________________________________________________________________
______________________________________________________________________________________________________________________________________
Checking Querying using day:
Event found at 06:00 on Mon with description: Task1.00
Event found at 06:00 on Wed with description: Task8.00
Event found at 20:30 on Sun with description: Task2.00
______________________________________________________________________________________________________________________________________
______________________________________________________________________________________________________________________________________
Checking Delete of long event in occupied slot case1(First Node):
Event deleted with Description: Task6.00
______________________________________________________________________________________________________________________________________
______________________________________________________________________________________________________________________________________
Checking Delete of long event in occupied slot case2(Last Node):
Event deleted with Description: LongTask5
______________________________________________________________________________________________________________________________________
______________________________________________________________________________________________________________________________________
Checking Delete of long event in occupied slot case2(Last Node):
Event deleted with Description: null
______________________________________________________________________________________________________________________________________
______________________________________________________________________________________________________________________________________
Checking Delete of all events with description("Task3.00"):
Delete 1 successful!
Delete 2 successful!
Delete 3 successful!
Delete 4 successful!
______________________________________________________________________________________________________________________________________
______________________________________________________________________________________________________________________________________
Checking clearByDay("Mon"):
Clear By Day Successful!
______________________________________________________________________________________________________________________________________
______________________________________________________________________________________________________________________________________
Checking clearByTime("07:30"):
Clear By Time Successful!
______________________________________________________________________________________________________________________________________
______________________________________________________________________________________________________________________________________
Checking clearAll
Clear All Successful!
______________________________________________________________________________________________________________________________________

		*/
		







		//System.out.println(sched1.toString());
	}




}


/*
public class Main
{
	
	/*Use this class to test your implementation.  This file will be overwritten for marking purposes.
	public static void printDays(Event [] days)
	{
		for(int i = 0; i < 7; i++)
		{
			// print each day description 
			System.out.print(days[i].getDescription() + "         ");
		}
		System.out.print("\n");
	}
	
	public static void printTime(Event[] time)
	{
		for(int i = 0; i< 33; i++){
			System.out.println(time[i].getDescription()+"\n");
		}
	}
		
	public static void main(String[] args)
	{
		//Write code to test your implementation here.
		
		/*Event Class Testing 
		Event lunchEvent = new Event("lunchtime");
		Event breakEvent = new Event("smoke break");
		Event studyEvent = new Event("study time ");
		
		Event classEvent = new Event("class time", lunchEvent, breakEvent, studyEvent);
		
		//System.out.println("The next event on the same day is "+ classEvent.down.getDescription());
		//System.out.println("The previous event on the same day is "+classEvent.up.getDescription());
		//System.out.println("The event at the same time and on the next day is "+classEvent.right.getDescription());
		
		// Event array testing 
		String[] daysOfWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
		String[] timeOfDay = {"06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", 
								"11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
								"16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", 
								"21:00", "21:30", "22:00"};
		
		Event[] evArray = new Event[7];
		Event monEvent = new Event("monday event 1");
		Event tueEvent = new Event("tuesday event 1");
		Event wedEvent = new Event("wed event 1");
		Event thurEvent = new Event("thur event 1 ");
		
		/*
		// initialize the array
		for(int i = 0; i < 7; i++){
			Event sentinel = new Event(daysOfWeek[i]);
			evArray[i] = sentinel;
		}
		//printDays(evArray);
		Event [] timeArray =new Event[33];
		for(int i = 0; i < 33; i++)
		{
			Event sentinel = new Event(timeOfDay[i]);
			timeArray[i] = sentinel;
		}
		for(int i =0; i< timeOfDay.length/2; i++){
			String tmp = timeOfDay[i];
			timeOfDay[i] = timeOfDay[timeOfDay.length-i-1];
			timeOfDay[timeOfDay.length-i-1] = tmp;
		}S
		for(int i =0;i < timeOfDay.length; i++)
			System.out.print("\"" + timeOfDay[i] + "\", ");
			
		//printTime(timeArray);
		
		
		/*Schedule Testing 
		Schedule mySched = new Schedule();
		System.out.println("The unitialized Schedule Sparse Table \n");
		printTime(mySched.times);	
		System.out.print("      ");
		printDays(mySched.days);
		
		//Event klass = new Event("Mon class");
		
		
		
		/*Insert Tests:
		 * Fill all time slots for a day 
		 * Add to a full sunday 
		 * Insert into a full day at 22:00
		 * 
		
		System.out.println("*************************************************");
		System.out.println("INSERT FUNCTIONS TEST");
		System.out.println("*************************************************");
		System.out.println("Inserting Individual Events....");
		mySched.addEvent("07:00", "Mon", "Wake Up");
		mySched.addEvent("11:00", "Mon", "Monday Lunch");
		mySched.addEvent("10:00", "Mon", "Mon Class");
		
		
		mySched.addEvent("16:00", "Tue", "Tue Workout");
		for(int i = 0; i< timeOfDay.length; i++){
			String daily_task = "Wed Task #"+Integer.toString(i+1);
			mySched.addEvent(timeOfDay[i], "Wed", daily_task);
		}
		System.out.println("Inserting At the End of Sunday.....");
		mySched.addEvent("22:00", "Sun", "late sunday task");
		mySched.addEvent("22:00", "Sun", "Early Mon Task");
		
		System.out.println("Inserting Multiple Events.... ");
		mySched.addEvent("06:00", "Fri", "Gym", 120);
		System.out.println("Inserting at a clashing event......");
		mySched.addEvent("11:00", "Mon", "Mon Study");
		//mySched.addEvent("19:00", "Wed", "Stretch");
		
		
		/*Get Tests 
		 * Get the head event of all days 
		 * Get the events of specific days 
		 * 
		System.out.println("*************************************************");
		System.out.println("GET FUNCTIONS TEST");
		System.out.println("*************************************************");
		System.out.println("Test 1. Get Event:");
		Event monday_wake = mySched.getEvent("07:00", "Mon");
		System.out.println(monday_wake.toString());
		
		Event monday_lunch = mySched.getEvent("11:00", "Mon");
		System.out.println(monday_lunch.toString());
		
		System.out.println(mySched.getEvent("10:00","Mon").toString());
		
		System.out.println(mySched.getEvent("07:00", "Wed").getDescription());
		System.out.println(mySched.getEvent("22:00", "Wed").getDescription());
		
		System.out.println(mySched.getEvent("22:00","Sun").toString());
		System.out.println(mySched.getEvent("06:00", "Mon").toString());
		System.out.println(mySched.getEvent("22:00", "Wed").toString());
		System.out.println(mySched.getEvent("07:00", "Thu"));
		System.out.println(mySched.getEvent("22:00", "Mon"));
		
		// print the multiple events 
		System.out.println(mySched.getEvent("06:00", "Fri").toString());
		System.out.println(mySched.getEvent("06:30", "Fri").toString());
		System.out.println(mySched.getEvent("07:00", "Fri").toString());
		System.out.println(mySched.getEvent("07:30", "Fri").toString());
		
		System.out.println("Test 2. Get Time Event");
		System.out.println(mySched.getTimeEvent("06:00").toString());
		System.out.println(mySched.getTimeEvent("07:00").toString());
		System.out.println(mySched.getTimeEvent("08:00").toString());
		System.out.println(mySched.getTimeEvent("09:00").toString());
		System.out.println(mySched.getTimeEvent("10:00").toString());
		System.out.println(mySched.getTimeEvent("11:00").toString());
		System.out.println(mySched.getTimeEvent("12:00").toString());
		System.out.println(mySched.getTimeEvent("22:00").toString());
		
		System.out.println("Test 3. Find Event:");
		System.out.println(mySched.findEvent("Wake Up").toString());
		System.out.println(mySched.findEvent("Mon Class").toString());
		System.out.println(mySched.findEvent("Gym").toString());
		//System.out.println(mySched.findEvent("Stretch").toString());
		
		System.out.println("Test 4. Get Day Event:");
		System.out.println(mySched.getDayEvent("Tue"));
		System.out.println(mySched.getDayEvent("Mon"));
		System.out.println(mySched.getDayEvent("Sun"));
		
		System.out.println(mySched.getEvent("22:00", "Wed").down);
		/*Test the Delete/Clear Functions 
		System.out.println("*************************************************");
		System.out.println("DELETE AND CLEAR FUNCTIONS TEST");
		System.out.println("*************************************************");
		System.out.println("Delete a single event ");
		System.out.println(mySched.deleteEvent("07:00", "Mon"));
		mySched.deleteEvent("late sunday task");
		System.out.println(mySched.findEvent("late sunday task"));
		System.out.println(mySched.findEvent("Wake Up"));
		System.out.println("Delete Multiple Events");
		System.out.println(mySched.deleteEvent("06:00", "Fri"));
		System.out.println(mySched.findEvent("Gym"));
		System.out.println("Clearing a specific time/day slot.....");
		mySched.clearByTime("10:00");
		mySched.clearByTime("06:00");
		System.out.println(mySched.getTimeEvent("10:00"));
		System.out.println(mySched.getTimeEvent("06:00"));
		//mySched.clearByDay("Wed");
		//System.out.println(mySched.getDayEvent("Wed"));
		//mySched.clearAll();
		//System.out.println(mySched.days[0].down);
		System.out.println(mySched.getEvent("06:00", "Mon"));
		
		
		
	}
	
}

*/