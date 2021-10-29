public class City //Class to store the attributes of a City
{
    private String cityName; //City name
    private boolean testRequired; //States if a test is Required for Unvaccinated individuals to enter the city
    private int timeToTest; //Time to Take a test
    private int nightlyHotelCost; //Hotel Cost for 1 night
    private int cost_from_source; //Cost to Travel from source to this City


    public City(String cityName, boolean testRequired, int timeToTest, int nightlyHotelCost)
    {
        this.cityName=cityName;
        this.testRequired=testRequired; //Signifies if a Test is required for UNVACCINATED travellers
        this.timeToTest=timeToTest; //Time taken to test (in minutes)
        this.nightlyHotelCost=nightlyHotelCost;
        this.cost_from_source=Integer.MAX_VALUE;
    }

    public City() {} //Constructor
    //Get Methods to return attributes of the class and a set method to update the distance of this city from the source city
    public void setCostFromSource(int cost)
    {
        this.cost_from_source=cost;
    }

    public String getCity()
    {
        return this.cityName;
    }

    public boolean getTestRequired()
    {
        return this.testRequired;
    }

    public int getTimeToTest()
    {
        return this.timeToTest;
    }

    public int getHotelCost()
    {
        return this.nightlyHotelCost;
    }

    public int getCostFromSource()
    {
        return this.cost_from_source;
    }

//    @Override
//    public int compare(City o1, City o2)
//    {
//        return o1.cost_from_source-o2.cost_from_source;
//    }
}
