public class Flight
{
    private String startCity; //Start city
    private String destinationCity;//Destination city
    private int flightTime;//Time to travel
    private int flightCost;//Cost of travel

    public Flight(String startCity, String destinationCity, int flightTime, int flightCost) //Constructor
    {
        this.startCity=startCity;
        this.destinationCity=destinationCity;
        this.flightTime=flightTime;
        this.flightCost=flightCost;
    }
    //Get Methods to return attributes of the class
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
