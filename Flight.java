public class Flight
{
    private String startCity;
    private String destinationCity;
    private int flightTime;
    private int flightCost;

    public Flight(String startCity, String destinationCity, int flightTime, int flightCost)
    {
        this.startCity=startCity;
        this.destinationCity=destinationCity;
        this.flightTime=flightTime;
        this.flightCost=flightCost;
    }

    public String getStartCity()
    {
        return this.startCity;
    }

    public String getEndCity()
    {
        return this.destinationCity;
    }

    public int getflightTime()
    {
        return this.flightTime;
    }

    public int getflightCost()
    {
        return this.flightCost;
    }


}
