public class Train //Class to store attributes of an Object - "Train"
{
    private String startCity; //Start city
    private String destinationCity;//Destination city
    private int trainTime;//Time to travel
    private int trainCost;//Cost of travel

    public Train(String startCity, String destinationCity, int trainTime, int trainCost) //Constructor
    {
        this.startCity=startCity;
        this.destinationCity=destinationCity;
        this.trainTime=trainTime;
        this.trainCost=trainCost;
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

    public int gettrainTime()
    {
        return this.trainTime;
    }

    public int gettrainCost()
    {
        return this.trainCost;
    }


}
