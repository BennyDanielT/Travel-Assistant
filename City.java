public class City
{
    private String cityName;
    private boolean testRequired;
    private int timeToTest;
    private int nightlyHotelCost;

    public City(String cityName, boolean testRequired, int timeToTest, int nightlyHotelCost)
    {
        this.cityName=cityName;
        this.testRequired=testRequired; //Signifies if a Test is required for UNVACCINATED travellers
        this.timeToTest=timeToTest; //Time taken to test (in minutes)
        this.nightlyHotelCost=nightlyHotelCost;
    }

    public String getCity()
    {
        return this.cityName;
    }
}
