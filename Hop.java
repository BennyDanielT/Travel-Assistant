public class Hop
{
    private City start;
    private City destination;
    private float relative_cost;
    private int time;
    private int cost;
    private String mode;


    public Hop(City start, City destination, int time, int cost, String mode)
    {
        this.start = start;
        this.destination = destination;
        this.time = time;
        this.cost=cost;
        this.relative_cost = 0;
        this.mode=mode;
    }

    public City getStart()
    {
        return start;
    }

    public City getDestination()
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

}
