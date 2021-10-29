public class Hop
{
    private String start;
    private String destination;
    private float relative_cost;
    private int time;
    private int cost;
    private String mode;


    public Hop(String start, String destination, int time, int cost, String mode)
    {
        this.start = start;
        this.destination = destination;
        this.time = time;
        this.cost=cost;
        this.relative_cost = 0;
        this.mode=mode;
    }

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
