public class Hop //Class to Record each travel hop
{
    private String start; //Start city
    private String destination;//Destination city
    private float relative_cost; //Relative cost of visiting city from the source
    private int time; //Time to travel
    private int cost; //Cost of travel
    private String mode; //Mode of travel (Fly / Train)


    public Hop(String start, String destination, int time, int cost, String mode) //Constructor
    {
        this.start = start;
        this.destination = destination;
        this.time = time;
        this.cost=cost;
        this.relative_cost = 0;
        this.mode=mode;
    }
//Get Methods to return attributes of the class
    public String getStart()
    {
        return start;
    }

    public String getDestination()
    {
        return destination;
    }

    public int getTime()
    {
        return time;
    }

    public int getCost()
    {
        return cost;
    }

    public String getMode()
    {
        return mode;
    }

}
