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

        do {
            userCommand = scnr.next();
            if (userCommand.equalsIgnoreCase(addCityCommand))//If user wants to add a City to the Travel Assistant
            {
                //Retrieve the input from the user
                String city_name=scnr.next();
                boolean test_required= scnr.nextBoolean();
                int test_time=scnr.nextInt();
                int hotel_cost=scnr.nextInt();

                TravelAssistant TA = new TravelAssistant();

                try
                {
                    boolean status = TA.addCity(city_name, test_required, test_time, hotel_cost); //Invoke the function addCity() and attempt to add a City to the Travel Assistant
                    if(status)
                    {
                        System.out.println("City "+city_name+" is now added to the Travel Assistant");
                    }
                    else
                    {
                        System.out.println("Failed to add city to travel assistant!");
                    }
                }
                catch(IllegalArgumentException e)
                {

                }

            }


            else if (userCommand.equalsIgnoreCase(addFlightCommand)) //If user wants to add a Flight to the Travel Assistant
            {
                String start_city=scnr.next();
                String dest_city= scnr.next();
                int flight_time=scnr.nextInt();
                int flight_cost=scnr.nextInt();

                TravelAssistant TA = new TravelAssistant();

                try
                {
                    boolean status = TA.addFlight(start_city, dest_city, flight_time, flight_cost); //Invoke the function addFlight() and attempt to add a Flight to the Travel Assistant
                    if(status)
                    {
                        System.out.println("Flight "+start_city+"--->"+dest_city+" is now added to the Travel Assistant");
                    }
                    else
                    {
                        System.out.println("Failed to add flight to travel assistant!");
                    }
                }
                catch (IllegalArgumentException e)
                {
                    e.printStackTrace();
                }
            }


            else if (userCommand.equalsIgnoreCase(addTrainCommand))//If user wants to add a Train to the Travel Assistant
            {
                String start_city=scnr.next();
                String dest_city= scnr.next();
                int flight_time=scnr.nextInt();
                int flight_cost=scnr.nextInt();

                TravelAssistant TA = new TravelAssistant();

                try
                {
                    boolean status = TA.addTrain(start_city, dest_city, flight_time, flight_cost);//Invoke the function addTrain() and attempt to add a Train to the Travel Assistant
                    if(status)
                    {
                        System.out.println("Train "+start_city+"--->"+dest_city+" is now added to the Travel Assistant");
                    }
                    else
                    {
                        System.out.println("Failed to add Train to travel assistant!");
                    }
                }
                catch (IllegalArgumentException e)
                {
                    e.printStackTrace();
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

                TravelAssistant TA = new TravelAssistant();

                try
                {
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


            else if (userCommand.equalsIgnoreCase("quit")) {
                System.out.println("Quit");
            }
            else
            {
                System.out.println("Bad command: " + userCommand);
            }
        }while(!userCommand.equalsIgnoreCase("quit"));
    }
}


