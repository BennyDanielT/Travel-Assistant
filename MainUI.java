import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainUI
{
    public static void main(String[] args)
    {

        Scanner scnr;
        String userCommand="";
        scnr = new Scanner(System.in);

        //Display the commands that're available to users
        String addCityCommand = "city";
        String addFlightCommand = "flight";
        String addTrainCommand = "train";
        String planTripCommand = "plan";

        System.out.println("Commands available:");
        System.out.println("  " + addCityCommand + " <City> <Test Required?> <Time to Test> <Nightly Hotel Cost>");
        System.out.println("  " + addFlightCommand + "<Start City> <Destination City> <Flight Time> <Flight Cost>");
        System.out.println("  " + addTrainCommand + "<Start City> <Destination City> <Train Time> <Train Cost>");
        System.out.println("  " + planTripCommand + "<Start City> <Destination City> <Vaccinated?> <Cost Importance> <Time Importance> <Hop Importance>");
        TravelAssistant TA = new TravelAssistant();

        do {
            userCommand = scnr.next();

            if (userCommand.equalsIgnoreCase(addCityCommand))//If user wants to add a City to the Travel Assistant
            {
                //Retrieve the input from the user
                String city_name=scnr.next();
                boolean test_required= scnr.nextBoolean();
                int test_time=scnr.nextInt();
                int hotel_cost=scnr.nextInt();

                try
                {
                    if(city_name==null || city_name.isEmpty() || (hotel_cost<=0)) //Check for Invalid Input
                    {
                        throw new IllegalArgumentException("Invalid parameters are entered for this City!");
                    }
                    boolean status = TA.addCity(city_name, test_required, test_time, hotel_cost); //Invoke the function addCity() and attempt to add a City to the Travel Assistant
                    if(status)
                    {
                        System.out.println("City "+city_name+" is now available in the Travel Assistant");
                        TA.printCities();
                    }
                    else
                    {
                        System.out.println("Failed to add city to travel assistant!");
                    }
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println("Illegal Argument encountered, please enter valid details!");
                }

            }


            else if (userCommand.equalsIgnoreCase(addFlightCommand)) //If user wants to add a Flight to the Travel Assistant
            {
                String start_city=scnr.next();
                String dest_city= scnr.next();
                int flight_time=scnr.nextInt();
                int flight_cost=scnr.nextInt();

                //TravelAssistant TA = new TravelAssistant();
                try
                {
                    if(start_city==null || start_city.isEmpty() || dest_city==null || dest_city.isEmpty() || (flight_time<=0)|| (flight_cost<=0)) //Check for Invalid Input
                    {
                        throw new IllegalArgumentException("Invalid parameters are entered for this Flight!");
                    }
                    boolean status = TA.addFlight(start_city, dest_city, flight_time, flight_cost); //Invoke the function addFlight() and attempt to add a Flight to the Travel Assistant
                    if(status)
                    {
                        System.out.println("Flight "+start_city+"--->"+dest_city+" is now available in the Travel Assistant");
                    }
                    else
                    {
                        System.out.println("Failed to add flight to travel assistant!");
                    }
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("Illegal argument encountered, please enter valid details!");
                }
            }


            else if (userCommand.equalsIgnoreCase(addTrainCommand))//If user wants to add a Train to the Travel Assistant
            {
                String start_city=scnr.next();
                String dest_city= scnr.next();
                int train_time=scnr.nextInt();
                int train_cost=scnr.nextInt();

                //TravelAssistant TA = new TravelAssistant();

                try
                {
                    if(start_city==null || start_city.isEmpty() || dest_city==null || dest_city.isEmpty() || (train_time<=0)|| (train_cost<=0)) //Check for Invalid Input
                    {
                        throw new IllegalArgumentException("Invalid parameters are entered for this Train!");
                    }
                    boolean status = TA.addTrain(start_city, dest_city, train_time, train_cost);//Invoke the function addTrain() and attempt to add a Train to the Travel Assistant
                    if(status)
                    {
                        System.out.println("Train "+start_city+"--->"+dest_city+" is now available in the Travel Assistant");
                    }
                    else
                    {
                        System.out.println("Failed to add Train to travel assistant!");
                    }
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("Illegal argument encountered, please enter valid details!");
                }
            }

            else if (userCommand.equalsIgnoreCase(planTripCommand)) //If user wants to plan a trip from start_city to dest_city
            {
                String start_city=scnr.next();
                String dest_city= scnr.next();
                boolean vacc_status=scnr.nextBoolean();//Vaccination status of the traveller
                int cost_imp=scnr.nextInt(); //User's priority value for cost
                int time_imp=scnr.nextInt();//User's priority value for Travel Time
                int hop_imp=scnr.nextInt();//User's priority value for Number of hops

                //TravelAssistant TA = new TravelAssistant();

                try
                {
                    if(start_city==null || start_city.isEmpty() || dest_city==null || dest_city.isEmpty() || (cost_imp<0)|| (time_imp<0) || (hop_imp<0)) //Check for Invalid Input
                    {
                        throw new IllegalArgumentException("Invalid parameters are entered for this Trip!");
                    } //Throw an Exception if any Illegal Arguments are entered
                    List<String> itinerary = TA.planTrip(start_city, dest_city, vacc_status, cost_imp, time_imp,hop_imp);

                    for(String str:itinerary) //Print the Mode of Travel and City visited in the itinerary
                    {
                        System.out.println(str);
                    }

                }
                catch (IllegalArgumentException e)
                {
                    e.printStackTrace();
                }
            }

            else if (userCommand.equalsIgnoreCase("quit"))
            {
                System.out.println("Quit");
            }

            else
            {
                System.out.println("Bad command: " + userCommand);
            }
        }while(!userCommand.equalsIgnoreCase("quit"));
    }
}


