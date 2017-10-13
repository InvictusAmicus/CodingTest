import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SeedDataGenerator
{
	static int noOfEvents = 0;
	static int[] xPositions;
	static int[] yPositions;
	
	public static void main(String [] args)
	{
		int x, y, counter, amountOfEvents, amountOfTickets;
		float price;
		File eventFile = new File("events.txt");
		File ticketFile = new File("tickets.txt");
		
		// uses the current time as the seed for the randomizer
		Random random = new Random(System.currentTimeMillis());
		BufferedWriter eventWriter;
		BufferedWriter ticketWriter;
		
		amountOfEvents = random.nextInt(15);
		amountOfTickets = random.nextInt(25);
		
		xPositions = new int[amountOfEvents];
		yPositions = new int[amountOfEvents];
		
		try 
		{
			eventWriter = new BufferedWriter(new FileWriter(eventFile));
		
			eventWriter.write(amountOfEvents + ",");
			
			for(counter = 0; counter < amountOfEvents; counter++)
			{
				x = random.nextInt(21);
				y = random.nextInt(21);
				x -= 10;
				y -= 10;
				
				if(isLocationFree(x,y))
				{
					eventWriter.write(counter + "," + x + "," + y + ",");
				}
				else
				{
					counter--;
				}
			}
			
			eventWriter.close();
			
			ticketWriter = new BufferedWriter(new FileWriter(ticketFile));
			
			ticketWriter.write(amountOfTickets + ",");
			int event;
		
			for(counter = 0; counter < amountOfTickets; counter++)
			{
				event = random.nextInt(amountOfEvents);
				price = random.nextFloat() * random.nextInt(100);
				
				ticketWriter.write(event + "," + String.format("%.2f", price) + ",");
			}
			
			ticketWriter.close();
			System.out.println("Complete");
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	
	}

	private static boolean isLocationFree(int x, int y)
	{
		for(int i = 0; i < noOfEvents; i++)
		{
			if(x == xPositions[i] && y == xPositions[i])
			{
				return false;
			}
		}
		xPositions[noOfEvents] = x;
		yPositions[noOfEvents] = y;
		noOfEvents++;
		return true;
	}
}
