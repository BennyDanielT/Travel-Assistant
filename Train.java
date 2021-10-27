public class Train
{
    private String startCity;
    private String destinationCity;
    private int trainTime;
    private int trainCost;

    public Train(String startCity, String destinationCity, int trainTime, int trainCost)
    {
        this.startCity=startCity;
        this.destinationCity=destinationCity;
        this.trainTime=trainTime;
        this.trainCost=trainCost;
    }

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
