import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SeedDataGenerator
{
	static int noOfEvents = 0;
	static int[] xPositions = new int[10];
	static int[] yPositions = new int[10];
	
	public static void main(String [] args)
	{
		int x, y, counter;
		float price;
		File eventFile = new File("events.txt");
		File ticketFile = new File("tickets.txt");
		
		// uses the current time as the seed for the randomizer
		Random random = new Random(System.currentTimeMillis());
		BufferedWriter eventWriter;
		BufferedWriter ticketWriter;
		
		try 
		{
			eventWriter = new BufferedWriter(new FileWriter(eventFile));
		
			for(counter = 0; counter < 10; counter++)
			{
				x = random.nextInt(20);
				y = random.nextInt(20);
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
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		try 
		{
			ticketWriter = new BufferedWriter(new FileWriter(ticketFile));
			int event;
		
			for(counter = 0; counter < 15; counter++)
			{
				event = random.nextInt(10);
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
