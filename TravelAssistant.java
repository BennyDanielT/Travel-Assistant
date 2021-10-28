import java.util.*;

public class TravelAssistant
{
    private HashSet<City> Cities = new HashSet<>();
    private ArrayList<Hop> Hops = new ArrayList<>();

    private ArrayList<Flight> Flights = new ArrayList<>();
    private ArrayList<Train> Trains = new ArrayList<>();

    private Graph travel_graph=new Graph();

    //Method to add a Cityv
    boolean addCity( String cityName, boolean testRequired, int timeToTest, int nightlyHotelCost ) throws IllegalArgumentException
        {
        boolean check =true;
        boolean city_exists=checkCities(returnCity(cityName));

        if(cityName==null || cityName.isEmpty() || (nightlyHotelCost<0)) //Check for Invalid Input
        {
            throw new IllegalArgumentException("Invalid parameters are entered for this City!");
        }

        else if(city_exists) // Check if the information entered contradicts data already known.
        {
            System.out.println("City exists in records");
            return false;
        }

        City c1=new City(cityName,testRequired,timeToTest,nightlyHotelCost); //If the information is valid, add the City to the ArrayList
            Cities.add(c1);
        travel_graph.vertices.add(c1); //Add the City as a Vertex in the Graph
        ArrayList<City> adjacent_cities=new ArrayList<City>(); //Create a List for Adjacent Cities
        travel_graph.adjVertices.put(c1,adjacent_cities); //Add the new City and the empty list of adjacent cities to the adjacency List - adjVertices
        return true;
    }

    public boolean checkCities(City city) // Method to check if a City entered by the user already exists
    {
        boolean city_exists=false;
        for (City c:Cities)
        {
            if(c==city) //Traverse through the ArrayList to check if the City already exists
            {
                //Check if the new information Contradicts existing information
                if(c.getTestRequired()!=city.getTestRequired() || c.getTimeToTest()!=city.getTimeToTest() || c.getHotelCost()!=city.getHotelCost())
                {
                    city_exists=true; // Return True if new information is contradictory
                }

            }
        }
        return city_exists;
    }

    public City returnCity(String cityName) //Method to return an object of type City if the name of the City matches the City name in the object
    {
        for(City c:Cities)
        {
            if(c.getCity()==cityName)
            {
                return c;
            }
        }
        return null;
    }

    boolean addFlight( String startCity, String destinationCity, int flightTime, int flightCost)
            throws IllegalArgumentException//Method to add a Flight between two Cities
    {
        boolean flight_exists=checkFlights(startCity,destinationCity,flightTime,flightCost); //Check if the flight alredy exists

        if(startCity==null || startCity.isEmpty() || destinationCity==null || destinationCity.isEmpty() || (flightTime<=0)|| (flightCost<=0)) //Check for Invalid Input
        {
            throw new IllegalArgumentException("Invalid parameters are entered for this Flight!");
//            check=false;
//            System.out.println("Invalid parameters are entered!");
//            return false;
        }

        else if(flight_exists) // Check if the information entered contradicts data already known.
        {
            System.out.println("Flight exists in records");
            return false;
        }

        if(Cities.contains(startCity) && Cities.contains(destinationCity)) /*If both start
         and destination Cities exist in the Travel Assistant then include an edge between the cities, with the mode of transport as Flight*/
        {
            Flight f1 = new Flight(startCity, destinationCity, flightTime, flightCost); //If the information is valid, add the Flight to the ArrayList
            Flights.add(f1);//If the information is valid, add the Flight to the ArrayList

            City s=returnCity(startCity); //Obtain objects of type-City using the strings "startCity" & "destinationCity"
            City d=returnCity(destinationCity);
            Hop h1 = new Hop(s, d, flightTime, flightCost,"flight");
            Hops.add(h1);

            travel_graph.adjVertices.get(s).add(d); //Add an edge between start city and destination city in the adjacency list

            return true;
        }

        else
        {
            System.out.println(("Please enter a valid City for travel!"));
            return false;
        }

    }

    public boolean checkFlights(String startCity, String destinationCity, int flightTime, int flightCost) // Check if a Flight route already exists
    {
        boolean flight_exists = false;
        for (int i = 0; i < Flights.size(); i++) {
            if (Flights.get(i).getStartCity() == startCity && Flights.get(i).getEndCity() == destinationCity && Flights.get(i).getflightTime()!=flightTime && Flights.get(i).getflightCost()!=flightCost)
            {//If the new information contradicts existing information then return false
                flight_exists = true;
            }
        }
        return flight_exists;
    }

    //Add a Train route to the Travel Assistant
    boolean addTrain( String startCity, String destinationCity, int trainTime, int trainCost) throws IllegalArgumentException
    {
        boolean train_exists=checkTrains(startCity,destinationCity,trainTime,trainCost);

        if(startCity==null || startCity.isEmpty() || destinationCity==null || destinationCity.isEmpty() || (trainTime<=0)|| (trainCost<=0)) //Check for Invalid Input
        {
            throw new IllegalArgumentException("Invalid parameters are entered for this Train!");
        }

        else if(train_exists) // Check if the information entered contradicts data already known.
        {
            System.out.println("Train exists in records");
            return false;
        }

        Train t1=new Train(startCity, destinationCity, trainTime,trainCost); //If the information is valid, add the City to the ArrayList
        Trains.add(t1);
        return true;

    }

    private boolean checkTrains(String startCity, String destinationCity, int trainTime, int trainCost)
    {
        boolean train_exists = false;
        for (int i = 0; i < Trains.size(); i++) {
            if (Trains.get(i).getStartCity() == startCity && Trains.get(i).getEndCity() == destinationCity) {
                train_exists = true;
            }
        }
        return train_exists;
    }

    /*Accepts the user's choice of city for Arrival and Departure and plans a
    trip based on the list of cities that are available under the Travel Assistant
    The assistant considers the following factors:
    --Vaccination Status
    --Travel Time
    --Travel Cost
    --Number of Transits */
    ArrayList<String> planTrip ( String startCity, String destinationCity, boolean isVaccinated, int
            costImportance, int travelTimeImportance, int travelHopImportance ) throws
            IllegalArgumentException
    {
        if(startCity==null || startCity.isEmpty() || destinationCity==null || destinationCity.isEmpty() || (costImportance<0)|| (travelTimeImportance<0) || (travelHopImportance<0)) //Check for Invalid Input
        {
            throw new IllegalArgumentException("Invalid parameters are entered for this Trip!");
        }
        //Retrieve objects from the List of Cities where City Name is a match.
        City start_city=returnCity(startCity);
        City destination_city=returnCity(destinationCity);

        ArrayList<String> output=new ArrayList <String>(); //Output List of Strings which contains the Cities visited and the mode of Travel

        HashSet<City> visited_nodes=new HashSet<City>(); //Initialize a Set of Visited Nodes

        PriorityQueue<City> queue = new PriorityQueue<City>(); //Initialize a Priority Queue

        HashMap<City, Integer> rel_cost = new HashMap<>();
        HashMap<City, Integer> hops = new HashMap<>();

        for(City c:Cities)
        {
            rel_cost.put(c,Integer.MAX_VALUE); /*Assigning the cost of travel to each City as INFINITY.
            To simulate infinity, I have assigned the maximum value an integer can take*/
        }

        rel_cost.put(start_city,0); //Set the relative cost of visiting a "Start City" as 0.
        hops.put(start_city,0); //Set the number of hops to visit a "Start City" as 0.
        queue.add(start_city); //Add the City to the Priority quque

        while(visited_nodes.size()!=Cities.size()) //Traverse the Graph till all Nodes are visited
        {
            City nearest_city=queue.remove(); //Retrieve the city that is closest to the current city
            visited_nodes.add(nearest_city); //Add it to the list of processed Cities


            int relative_cost=-1;
            int updated_min_dist=-1;

            for(int j=0;j<travel_graph.adjVertices.get(nearest_city).size();j++) //For all Cities that are connected to the current City that was processed
            {
                City neighbour_city=travel_graph.adjVertices.get(nearest_city).get(j); //Retrieve a neighbour city that's CONNECTED to the processed city
                if(!visited_nodes.contains(neighbour_city)) //Check if the neighbour city has been processed
                {
                    //#hops(source to current node)=#hops from source to previous node + #hops from previous to current node
                    hops.put(neighbour_city, hops.get(nearest_city)+1);

                    relative_cost=((costImportance*1)+(travelTimeImportance*1)+(travelHopImportance*hops.get(neighbour_city)));
                    updated_min_dist=rel_cost.get(nearest_city)+relative_cost; /* Update the distance to the neighbour city
                     as the sum of the distance from the source to the nearest_city and the distance from the nearest_city to the neighbour city*/

                    if(updated_min_dist<rel_cost.get(neighbour_city))
                    {
                        rel_cost.put(neighbour_city,updated_min_dist);
                    }
                    queue.add(neighbour_city);
                }
            }
        }

        return output;
    }

} // End of Class - Travel Assistant

/*
Vacc_Status=isVACCINATED

IF Vacc_Status==false && City.testRequired==true && City.timetotest<=0

IF Vacc_Status==false && City.testRequired==true && City.timetotest>0
    THEN: Cost_testing = City.timeToTest * City.nightlyHotelCost
          Cost_Travel= Flight/Train.cost
          Cost_Total=Cost_testing+Cost_Travel;
     Travel_time=Flight/Train.time;

     relative_cost=((costImportance*Cost_Totalv)+(travelTimeImportance*Travel_time)+(travelHopImportance*1));=
 */