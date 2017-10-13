import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Main
{
	private static Event[] e;// = new Event[10];
	private static Ticket[] t;// = new Ticket[15];
	
	public static void main(String [] args)
	{
		// Enters the pre-made data into the two arrays
		readData(args[0], args[1]);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter valid co-ordinates in the form (x,y).");
		String coordinates = scan.nextLine();
		scan.close();
		
		String x = "0";
		String y = "0";
		for(int i = 0; i < coordinates.length(); i++)
		{
			if(coordinates.charAt(i) == ',')
			{
				x = coordinates.substring(0, i);
				y = coordinates.substring(i+1);
				i = 7;
			}
		}
		
		int xPos = Integer.parseInt(x);
		int yPos = Integer.parseInt(y);
		int[] distances = new int[e.length];
		int[] closestEvents = {1,-1,-1,-1,-1};
		
		for(int i = 0; i < e.length; i++)
		{
			distances[i] = Math.abs(xPos - e[i].x) + Math.abs(yPos - e[i].y);
		}
		
		// compares first two events
		if (distances[1] < distances[0])
		{
			closestEvents[1] = 0;
			closestEvents[0] = 1;
		}
		else
		{
			closestEvents[1] = 1;
		}
		
		// compares third event to first two
		if(distances[2] < distances[closestEvents[0]])
		{
			closestEvents[2] = closestEvents[1];
			closestEvents[1] = closestEvents[0];
			closestEvents[0] = 2;
		}
		else if(distances[2] < distances[closestEvents[1]])
		{
			closestEvents[2] = closestEvents[1];
			closestEvents[1] = 2;
		}
		else
		{
			closestEvents[2] = 2;
		}
		
		// compares fourth event to first three
		if(distances[3] < distances[closestEvents[0]])
		{
			closestEvents[3] = closestEvents[2];
			closestEvents[2] = closestEvents[1];
			closestEvents[1] = closestEvents[0];
			closestEvents[0] = 3;
		}
		else if(distances[3] < distances[closestEvents[1]])
		{
			closestEvents[3] = closestEvents[2];
			closestEvents[2] = closestEvents[1];
			closestEvents[1] = 3;
		}
		else if(distances[3] < distances[closestEvents[2]])
		{
			closestEvents[3] = closestEvents[2];
			closestEvents[2] = 3;
		}
		else
		{
			closestEvents[3] = 3;
		}
		
		// compares fifth event to first four
		if(distances[4] < distances[closestEvents[0]])
		{
			closestEvents[4] = closestEvents[3];
			closestEvents[3] = closestEvents[2];
			closestEvents[2] = closestEvents[1];
			closestEvents[1] = closestEvents[0];
			closestEvents[0] = 4;
		}
		else if(distances[4] < distances[closestEvents[1]])
		{
			closestEvents[4] = closestEvents[3];
			closestEvents[3] = closestEvents[2];
			closestEvents[2] = closestEvents[1];
			closestEvents[1] = 4;
		}
		else if(distances[4] < distances[closestEvents[2]])
		{
			closestEvents[4] = closestEvents[3];
			closestEvents[3] = closestEvents[2];
			closestEvents[2] = 4;
		}
		else if(distances[4] < distances[closestEvents[3]])
		{
			closestEvents[4] = closestEvents[3];
			closestEvents[3] = 4;
		}
		else
		{
			closestEvents[4] = 4;
		}
		
		//compare all the other events against the closest 5
		for(int i = 5; i < distances.length; i++)
		{
			if(distances[i] < distances[closestEvents[0]])
			{
				closestEvents[4] = closestEvents[3];
				closestEvents[3] = closestEvents[2];
				closestEvents[2] = closestEvents[1];
				closestEvents[1] = closestEvents[0];
				closestEvents[0] = i;
			}
			else if(distances[i] < distances[closestEvents[1]])
			{
				closestEvents[4] = closestEvents[3];
				closestEvents[3] = closestEvents[2];
				closestEvents[2] = closestEvents[1];
				closestEvents[1] = i;
			}
			else if(distances[i] < distances[closestEvents[2]])
			{
				closestEvents[4] = closestEvents[3];
				closestEvents[3] = closestEvents[2];
				closestEvents[2] = i;
			}
			else if(distances[i] < distances[closestEvents[3]])
			{
				closestEvents[4] = closestEvents[3];
				closestEvents[3] = i;
			}
			else if(distances[i] < distances[closestEvents[4]])
			{
				closestEvents[4] = i;
			}
		}
		
		float currentCheapestPrice[] = {101.0f, 101.0f, 101.0f, 101.0f, 101.0f};
		
		for(int i = 0; i < closestEvents.length; i++)
		{
			for(int j = 0; j < t.length; j++)
			{
				if(t[j].eventId == closestEvents[i] && t[j].price < currentCheapestPrice[i])
				{
					currentCheapestPrice[i] = t[j].price; 
				}
			}
		}
		
		System.out.println("Closest Events to you:");
		for(int i = 0; i < 5; i++)
		{
			if(currentCheapestPrice[i] == 101.0f)
			{
				System.out.println("Event:\t" + closestEvents[i] + " - Distance:\t" + distances[closestEvents[i]]
						+ ":\tNo tickets available.");
			}
			else
			{
				System.out.println("Event:\t" + closestEvents[i] + " - Distance:\t" + distances[closestEvents[i]]
						+ ":\t$" + currentCheapestPrice[i]);
			}
		}
		
	}
	
	private static void readData(String eFile, String tFile)
	{
		File eventsFile = new File(eFile);
		File ticketsFile = new File(tFile);
		try
		{
			FileReader eReader = new FileReader(eventsFile);
			FileReader tReader = new FileReader(ticketsFile);
			
			
			boolean eof = false;
			String data = "";
			int check = 0;
			int count = 0;
			
			int eventId = 0, xPos = 0, yPos = 0;
			float price = 0.0f;
			
			boolean numOfEventsFound = false, numOfTicketsFound = false;
			
			while(!eof)
			{
				int c = eReader.read();
				char character = (char)c;
				if(c != -1)
				{
					if(character != ',')
					{
							data += character;
					}
					else
					{
						if(numOfEventsFound)
						{
							if(check == 0)
							{
								eventId = Integer.parseInt(data);
							}
							if(check == 1)
							{
								xPos = Integer.parseInt(data);
							}
							if(check == 2)
							{
								yPos = Integer.parseInt(data);
								e[count] = new Event(eventId, xPos, yPos);
								count++;
							}
							
							check++;
							check%=3;
							
							data = "";
						}
						else
						{
							e = new Event[Integer.parseInt(data)];
							System.out.println("Event count:" + e.length);
							numOfEventsFound = true;
							data = "";
						}
					}
				}
				else
				{
					eof = true;
				}
				
			}
			eReader.close();
			
			eof = false;
			check = 0;
			count = 0;
			
			while(!eof)
			{
				int c = tReader.read();
				char character = (char)c;
				if(c != -1)
				{
					if(character != ',')
					{
							data += character;
					}
					else
					{
						if(numOfTicketsFound)
						{
							if(check == 0)
							{
								eventId = Integer.parseInt(data);
							}
							if(check == 1)
							{
								price = Float.parseFloat(data);
								t[count] = new Ticket(eventId, price);
								count++;
							}
							
							check++;
							check%=2;
							
							data = "";
						}
						else
						{
							t = new Ticket[Integer.parseInt(data)];
							numOfTicketsFound = true;
							data = "";
						}
					}
				}
				else
				{
					eof = true;
				}
			}
			
			tReader.close();
			
		}
		catch(IOException e1)
		{
			System.out.println("Error. Cannot find data.");
			e1.printStackTrace();
		}
		
/*		for(int i = 0; i < e.length; i++)
			System.out.println(e[i]);
		for(int i = 0; i < t.length; i++)
			System.out.println(t[i]);
*/	
	}
}