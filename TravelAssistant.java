import java.util.*;

public class TravelAssistant
{
    private HashSet<City> Cities = new HashSet<>();
    private ArrayList<Hop> Hops = new ArrayList<>();

    private ArrayList<Flight> Flights = new ArrayList<>();
    private ArrayList<Train> Trains = new ArrayList<>();

    private Graph travel_graph=new Graph();

    //Method to add a City
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
            //System.out.println("City exists in records");
            return false;
        }

        City c1=new City(cityName,testRequired,timeToTest,nightlyHotelCost); //If the information is valid, add the City to the ArrayList
            Cities.add(c1);
        travel_graph.vertices.add(c1); //Add the City as a Vertex in the Graph
        HashSet<City> adjacent_cities=new HashSet<City>(); //Create a List for Adjacent Cities
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
        boolean flight_exists=checkFlights(startCity,destinationCity,flightTime,flightCost); //Check if the flight already exists

        if(startCity==null || startCity.isEmpty() || destinationCity==null || destinationCity.isEmpty() || (flightTime<=0)|| (flightCost<=0)) //Check for Invalid Input
        {
            throw new IllegalArgumentException("Invalid parameters are entered for this Flight!");
        }

        else if(flight_exists) // Check if the information entered contradicts data already known.
        {
            //System.out.println("Flight exists in records");
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
            //System.out.println(("Please enter a valid Flight for travel!"));
            return false;
        }

    }

    public boolean checkFlights(String startCity, String destinationCity, int flightTime, int flightCost) // Check if a Flight route already exists
    {
        boolean flight_exists = false;
        for (int i = 0; i < Flights.size(); i++)
        {
            //Check in information entered contradicts existing information
            if (Flights.get(i).getStartCity() == startCity && Flights.get(i).getEndCity() == destinationCity && (Flights.get(i).getflightTime()!=flightTime || Flights.get(i).getflightCost()!=flightCost))
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
            //System.out.println("Train exists in records");
            return false;
        }
        if(Cities.contains(startCity) && Cities.contains(destinationCity)) /*If both start
         and destination Cities exist in the Travel Assistant then include an edge between the cities, with the mode of transport as Train*/ {
            Train t1 = new Train(startCity, destinationCity, trainTime, trainCost); //If the information is valid, add the City to the ArrayList
            Trains.add(t1);
            City s = returnCity(startCity); //Obtain objects of type-City using the strings "startCity" & "destinationCity"
            City d = returnCity(destinationCity);
            Hop h1 = new Hop(s, d, trainTime, trainCost, "train");
            Hops.add(h1);

            travel_graph.adjVertices.get(s).add(d); //Add an edge between start city and destination city in the adjacency list
            return true;
        }

        else
        {
            return false;
        }

    }


    private boolean checkTrains(String startCity, String destinationCity, int trainTime, int trainCost)
    {
        boolean train_exists = false;
        for (int i = 0; i < Trains.size(); i++)
        {
            //Check in information entered contradicts existing information
            if (Trains.get(i).getStartCity() == startCity && Trains.get(i).getEndCity() == destinationCity && (Trains.get(i).gettrainTime()!=trainTime || Trains.get(i).gettrainCost()!=trainCost))
            {
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
    List<String> planTrip ( String startCity, String destinationCity, boolean isVaccinated, int
            costImportance, int travelTimeImportance, int travelHopImportance ) throws
            IllegalArgumentException
    {
        if(startCity==null || startCity.isEmpty() || destinationCity==null || destinationCity.isEmpty() || (costImportance<0)|| (travelTimeImportance<0) || (travelHopImportance<0)) //Check for Invalid Input
        {
            throw new IllegalArgumentException("Invalid parameters are entered for this Trip!");
        } //Throw an Exception if any Illegal Arguments are entered

        List<String> output=new ArrayList <String>(); //Output List of Strings which contains the Cities visited and the mode of Travel

        City start_city=returnCity(startCity);//Retrieve objects from the List of Cities where City Name is a match.
        City destination_city=returnCity(destinationCity);

        HashSet<City> visited_nodes=new HashSet<City>(); //Initialize a Set of Visited Nodes for Dijkstra's algorithm
        PriorityQueue<City> queue = new PriorityQueue<City>(); //Initialize a Priority Queue for Dijkstra's algorithm

        HashMap<City, Integer> rel_cost = new HashMap<>(); //A Map to store the cost of travel from the source City to every other City in the Travel Assistant
        HashMap<City, Integer> travel_hops = new HashMap<>(); //A Map to store the number of Hops from the source City to every other City in the Travel Assistant

        boolean vacc_status=isVaccinated; /*Store the Vaccination status
         so that if an unvaccinated traveller gets tested in a City, we can change the status to "Vaccinated"*/

        for(City c:Cities)
        {
            rel_cost.put(c,Integer.MAX_VALUE); /*Assigning the cost of travel to each City as INFINITY.
            To simulate infinity, I have assigned the maximum value an integer can take*/
        }

        rel_cost.put(start_city,0); //Set the relative cost of visiting a "Start City" as 0.
        travel_hops.put(start_city,0); //Set the number of travel_hops to visit a "Start City" as 0.
        queue.add(start_city); //Add the City to the Priority quque

        while(visited_nodes.size()!=Cities.size()) //Traverse the Graph till all Nodes are visited
        {
            City nearest_city=queue.remove(); //Retrieve the city that is closest to the current city
            visited_nodes.add(nearest_city); //Add it to the list of processed Cities
            int updated_cost;

            //int j=0;j<travel_graph.adjVertices.get(nearest_city).size();j++
            for(City neighbour_city : travel_graph.adjVertices.get(nearest_city)) //For all Cities that are connected to the current City that was processed
            {
                //City neighbour_city=cy; //Retrieve a neighbour city that's CONNECTED to the processed city

                if(!vacc_status && (neighbour_city.getTimeToTest()<0)) //Unvaccinated & Testing CANNOT be performed in the City
                {
                    continue;
                }

                    if(!visited_nodes.contains(neighbour_city)) //Check if the neighbour city has already been processed
                {
                    travel_hops.put(neighbour_city, travel_hops.get(nearest_city)+1); //#travel_hops(source to current node) = #travel_hops from source to previous node + #travel_hops from previous to current node

                    int mode_relative_cost=Integer.MAX_VALUE;
                    int temp_relative_cost=Integer.MAX_VALUE; //Temporary variable to determine which mode of travel (Flight / Train) is cheaper between 2 cities

                    for(Hop h : Hops) //Between any 2 Cities there may be a maximum of two modes of transport. Determine the cheapest mode of transport between two Cities
                    {
                        if(h.getStart()==nearest_city && h.getDestination()==neighbour_city)
                        {
                            //Vaccinated
                            if(vacc_status)
                            {
                                temp_relative_cost = ((costImportance * h.getCost()) + (travelTimeImportance * h.getTime()) + (travelHopImportance * travel_hops.get(neighbour_city)));
                            }
                            //Unvaccinated & Testing CAN be performed in the City
                            else if(!vacc_status && neighbour_city.getTestRequired())
                            {
                                int total_cost=((neighbour_city.getTimeToTest()*neighbour_city.getHotelCost()) + h.getCost()); /* If an individual is unvaccinated
                                 total cost incurred = Cost_for_Testing + Cost_to_Travel*/
                                temp_relative_cost = ((costImportance * total_cost) + (travelTimeImportance * h.getTime()) + (travelHopImportance * travel_hops.get(neighbour_city)));
                                vacc_status=true;
                            }

                            if(temp_relative_cost<mode_relative_cost) //If one mode is cheaper than another mode, set that mode as the edge between the two cities
                            {
                                mode_relative_cost=temp_relative_cost;
                            }
                        }
                    }

                    updated_cost=rel_cost.get(nearest_city)+mode_relative_cost; /* Update the cost to the neighbour city
                     as the sum of the distance from the source to the nearest_city and the distance from the nearest_city to the neighbour city*/

                    if(updated_cost<rel_cost.get(neighbour_city)) //If the new cost is lesser than the existing cost for the City, replace the cost in the Map
                    {
                        rel_cost.put(neighbour_city,updated_cost);
                    }
                    queue.add(neighbour_city);
                }
            }
        }

        return output;
    }

} // End of Class - Travel Assistant




