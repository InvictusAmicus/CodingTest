public class Event
{
	public int id, x, y;
	public float cheapestTicket;
	
	public Event(int id, int x, int y)
	{
		this.id = id;
		this.x = x;
		this.y = y;
		this.cheapestTicket = 0;
	}
	
	public String toString()
	{
		return ("Event: " + id + " at: " + x + ", " + y);
	}
}
