
public class Ticket
{
	public int eventId;
	public float price;
	
	public Ticket(int id, float price)
	{
		this.eventId = id;
		this.price = price;
	}
	
	public String toString()
	{
		return ("Ticket for event: " + eventId + " for: $" + price);
	}
}
