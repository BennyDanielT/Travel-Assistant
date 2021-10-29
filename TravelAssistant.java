import java.util.*;

public class TravelAssistant
{
    private ArrayList<City> Cities = new ArrayList<>();
    private ArrayList<Hop> Hops = new ArrayList<>();

    private ArrayList<Flight> Flights = new ArrayList<>();
    private ArrayList<Train> Trains = new ArrayList<>();

    private Graph travel_graph=new Graph();


    //Method to add a City
    boolean addCity( String cityName, boolean testRequired, int timeToTest, int nightlyHotelCost ) throws IllegalArgumentException
        {
            boolean flag=true;
            City c1=new City(cityName,testRequired,timeToTest,nightlyHotelCost); //If the information is valid, add the City to the ArrayList
            for (City c : Cities)
            {
                if (Objects.equals(c.getCity(), c1.getCity())) //Traverse through the ArrayList to check if the City already exists
                {
                    System.out.println(c.getCity() + c.getTestRequired() + c.getTimeToTest() + c.getHotelCost());
                    //Check if the new information Contradicts existing information
                    if (c.getTestRequired() != testRequired || c.getTimeToTest() != timeToTest || c.getHotelCost() != nightlyHotelCost)
                    {
                        System.out.println("contradictory information");
                        return false;// Return False if new information is contradictory
                    }
                    else if (c.getTestRequired() == testRequired && c.getTimeToTest() == timeToTest && c.getHotelCost() == nightlyHotelCost)
                    {
                        System.out.println("City Exists");
                        return true;
                    }
                }
            }

                Cities.add(c1);
                travel_graph.vertices.add(c1); //Add the City as a Vertex in the Graph
                HashSet<String> adjacent_cities = new HashSet<String>(); //Create a List for Adjacent Cities
                travel_graph.adjVertices.put(c1.getCity(), adjacent_cities); //Add the new City and the empty list of adjacent cities to the adjacency List - adjVertices
            return true;
        }

    public void printCities()
    {
        for (City c:Cities)
        {
            System.out.println(c.getCity());
            //System.out.println(c.getCity() + c.getTestRequired() + c.getTimeToTest() + c.getHotelCost());
        }
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
        Flight f1 = new Flight(startCity, destinationCity, flightTime, flightCost); //If the information is valid, add the Flight to the ArrayList
        boolean startflag=false;
        boolean endflag=false;
        for(Flight f : Flights)
        {
            if(Objects.equals(f.getStartCity(), f1.getStartCity()) && Objects.equals(f.getEndCity(), f1.getEndCity()))
            {
                if (f.getflightTime() != flightTime || f.getflightCost() != flightCost)
                {
                    System.out.println("Contradictory information");
                    return false;// Return False if new information is contradictory
                }
                else if (f.getflightTime() == flightTime || f.getflightCost() == flightCost)
                {
                    System.out.println("Flight Exists");
                    return true;
                }
            }
        }

        for(City c : Cities)
        {
            if(Objects.equals(c.getCity(), f1.getStartCity()))
                startflag=true;

            if(Objects.equals(c.getCity(), f1.getEndCity()))
                endflag=true;
        }
        if(startflag && endflag) /*If both start
         and destination Cities exist in the Travel Assistant then include an edge between the cities, with the mode of transport as Flight*/
        {
            //
            Flights.add(f1);//If the information is valid, add the Flight to the ArrayList
            Hop h1 = new Hop(startCity, destinationCity, flightTime, flightCost,"fly");
            Hops.add(h1);

            for(String city : travel_graph.adjVertices.keySet())
            {
                if(city==startCity)
                    travel_graph.adjVertices.get(city).add(destinationCity); //Add an edge between start city and destination city in the adjacency list
            }
            return true;
        }

        else
        {
            System.out.println("Cities do not exist");
            return false;
        }

    }

//    public boolean checkFlights(String startCity, String destinationCity, int flightTime, int flightCost) // Check if a Flight route already exists
//    {
//        boolean flight_exists = false;
//        for (int i = 0; i < Flights.size(); i++)
//        {
//            //Check in information entered contradicts existing information
//            if (Flights.get(i).getStartCity() == startCity && Flights.get(i).getEndCity() == destinationCity && (Flights.get(i).getflightTime()!=flightTime || Flights.get(i).getflightCost()!=flightCost))
//            {//If the new information contradicts existing information then return false
//                flight_exists = true;
//            }
//        }
//        return flight_exists;
//    }

    //Add a Train route to the Travel Assistant
    boolean addTrain( String startCity, String destinationCity, int trainTime, int trainCost) throws IllegalArgumentException
    {
        Train t1 = new Train(startCity, destinationCity, trainTime, trainCost); //If the information is valid, add the Flight to the ArrayList
        boolean startflag=false;
        boolean endflag=false;
        for(Train t : Trains)
        {
            if(Objects.equals(t.getStartCity(), t1.getStartCity()) && Objects.equals(t.getEndCity(), t1.getEndCity()))
            {
                if (t.gettrainTime() != trainTime || t.gettrainCost() != trainCost)
                {
                    System.out.println("Contradictory information");
                    return false;// Return False if new information is contradictory
                }
                else if (t.gettrainTime() == trainTime || t.gettrainCost() == trainCost)
                {
                    System.out.println("Flight Exists");
                    return true;
                }
            }
        }
        for(City c : Cities)
        {
            if(Objects.equals(c.getCity(), t1.getStartCity()))
                startflag=true;

            if(Objects.equals(c.getCity(), t1.getEndCity()))
                endflag=true;
        }
        if(startflag && endflag) /*If both start
         and destination Cities exist in the Travel Assistant then include an edge between the cities, with the mode of transport as Flight*/
        {
            //
            Trains.add(t1);//If the information is valid, add the Flight to the ArrayList
            Hop h1 = new Hop(startCity, destinationCity, trainTime, trainCost,"train");
            Hops.add(h1);

            for(String city : travel_graph.adjVertices.keySet())
            {
                if(city==startCity)
                    travel_graph.adjVertices.get(city).add(destinationCity); //Add an edge between start city and destination city in the adjacency list
            }
            return true;
        }

        else
        {
            System.out.println("Cities do not exist");
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

//    List<String> planTrip ( String startCity, String destinationCity, boolean isVaccinated, int costImportance, int travelTimeImportance, int travelHopImportance ) throws
//            IllegalArgumentException
//    {
//        if(startCity==null || startCity.isEmpty() || !Cities.contains(returnCity(startCity)) || destinationCity==null || destinationCity.isEmpty() || !Cities.contains(returnCity(destinationCity)) || (costImportance<0)|| (travelTimeImportance<0) || (travelHopImportance<0)) //Check for Invalid Input
//        {
//            throw new IllegalArgumentException("Invalid parameters are entered for this Trip!");
//        } //Throw an Exception if any Illegal Arguments are entered
//
//        List<String> output=new ArrayList <String>(); //Output List of Strings which contains the Cities visited and the mode of Travel
//
//        City start_city=returnCity(startCity);//Retrieve objects from the List of Cities where City Name is a match.
//        City destination_city=returnCity(destinationCity);
//
//        HashSet<City> visited_nodes=new HashSet<City>(); //Initialize a Set of Visited Nodes for Dijkstra's algorithm
//        PriorityQueue<City> queue = new PriorityQueue<City>(); //Initialize a Priority Queue for Dijkstra's algorithm
//
//        HashMap<City, Hop> travel_path = new HashMap<>(); //A Map to store the cost of travel from the source City to every other City in the Travel Assistant
//        HashMap<City, Integer> travel_hops = new HashMap<>(); //A Map to store the number of Hops from the source City to every other City in the Travel Assistant
//        //HashMap<City, Integer> rel_cost = new HashMap<>(); //A Map to store the cost of travel from the source City to every other City in the Travel Assistant
//
//
//        boolean vacc_status=isVaccinated; /*Store the Vaccination status
//         so that if an unvaccinated traveller gets tested in a City, we can change the status to "Vaccinated"*/
//
//        for(City c:Cities)
//        {
//            //rel_cost.put(c,Integer.MAX_VALUE);
//            c.setCostFromSource(Integer.MAX_VALUE);/*Assigning the cost of travel to each City as INFINITY.
//            To simulate infinity, I have assigned the maximum value an integer can take*/
//        }
//
//        //rel_cost.put(start_city,0); //Set the relative cost of visiting a "Start City" as 0.
//        start_city.setCostFromSource(0);
//
//        travel_hops.put(start_city,0); //Set the number of travel_hops to visit a "Start City" as 0.
//        queue.add(start_city); //Add the City to the Priority queue
//
//        while(visited_nodes.size()!=Cities.size()) //Traverse the Graph till all Nodes are visited
//        {
//            City nearest_city=queue.remove(); //Retrieve the city that is closest to the current city
//            visited_nodes.add(nearest_city); //Add it to the list of processed Cities
//            int updated_cost;
//
//            //int j=0;j<travel_graph.adjVertices.get(nearest_city).size();j++
//            for(String neighbour_city : travel_graph.adjVertices.get(nearest_city)) //For all Cities that are connected to the current City that was processed
//            {
//                //City neighbour_city=cy; //Retrieve a neighbour city that's CONNECTED to the processed city
//
//                if(!vacc_status && (neighbour_city.getTimeToTest()<0) && (neighbour_city.getTestRequired()==true)) //Unvaccinated & a Test is required but Testing CANNOT be performed in the City
//                {
//                    continue;
//                }
//
//                    if(!visited_nodes.contains(neighbour_city)) //Check if the neighbour city has already been processed
//                {
//                    travel_hops.put(neighbour_city, travel_hops.get(nearest_city)+1); //#travel_hops(source to current node) = #travel_hops from source to previous node + #travel_hops from previous to current node
//
//                    int mode_relative_cost=Integer.MAX_VALUE;
//                    int temp_relative_cost=Integer.MAX_VALUE; //Temporary variable to determine which mode of travel (Flight / Train) is cheaper between 2 cities
////                    String mode_travel;
////                    String city_travel;
//                    Hop optimal_hop=null;
//
//                    for(Hop h : Hops) //Between any 2 Cities there may be a maximum of two modes of transport. Determine the cheapest mode of transport between two Cities
//                    {
//                        if(h.getStart()==nearest_city && h.getDestination()==neighbour_city)
//                        {
//                            //Vaccinated OR (Unvaccinated BUT a test isn't required)
//                            if(vacc_status || (!vacc_status && !(neighbour_city.getTestRequired())))
//                            {
//                                temp_relative_cost = ((costImportance * h.getCost()) + (travelTimeImportance * h.getTime()) + (travelHopImportance * travel_hops.get(neighbour_city)));
//                            }
//                            //Unvaccinated & Testing CAN be performed in the City
//                            else if(!vacc_status && neighbour_city.getTestRequired() && (neighbour_city.getTimeToTest()<0))
//                            {
//                                int total_cost=((neighbour_city.getTimeToTest()*neighbour_city.getHotelCost()) + h.getCost()); /* If an individual is unvaccinated
//                                 total cost incurred = Cost_for_Testing + Cost_to_Travel*/
//                                temp_relative_cost = ((costImportance * total_cost) + (travelTimeImportance * h.getTime()) + (travelHopImportance * travel_hops.get(neighbour_city)));
//                                vacc_status=true;
//                            }
//
//                            if(temp_relative_cost<mode_relative_cost) //If one mode is cheaper than another mode, set that mode as the edge between the two cities
//                            {
//                                mode_relative_cost=temp_relative_cost;
////                                mode_travel=h.getMode(); //Retrieve the Optimal mode of transport
////                                city_travel=neighbour_city.getCity(); //Retrieve the City name for the output
//                                optimal_hop=h; //Retrieve the Optimal mode of transport between two cities
//                            }
//                        }
//                    }
//
//                    //updated_cost=rel_cost.get(nearest_city)+mode_relative_cost;
//
//                    updated_cost=nearest_city.getCostFromSource()+mode_relative_cost;/* Update the cost to the neighbour city
//                     as the sum of the distance from the source to the nearest_city and the distance from the nearest_city to the neighbour city*/
//
//                    //if(updated_cost<rel_cost.get(neighbour_city))
//                    if(updated_cost<neighbour_city.getCostFromSource()) //If the new cost is lesser than the existing cost for the City, replace the cost in the Map
//                    {
//                        //rel_cost.put(neighbour_city,updated_cost);
//                        neighbour_city.setCostFromSource(updated_cost);
//                        travel_path.put(neighbour_city,optimal_hop);
//                    }
//                    queue.add(neighbour_city);
//                }
//            }
//        }
//
//        if(destination_city.getCostFromSource()==Integer.MAX_VALUE) //If there's no way to travel between the given cities then return null as the list
//        {
//            return null;
//        }
//
//        output.add("start " + startCity);
//        City temp_city=destination_city;
//        while(temp_city!=start_city)
//        {
//            Hop path_edge=travel_path.get(temp_city);//Retrieve the hop between two Cities
//            output.add(0,path_edge.getMode() + " " + path_edge.getDestination().getCity()); //Store the Mode of transport and the destination
//            temp_city=path_edge.getStart();
//        }
//        return output;
//    }

} // End of Class - Travel Assistant

//while(neighbour!=start_city)
//        {
//            hop hoppp=map.get(neighbour)//Retrieve the hope
//            list.add(hoppp.getMode() + " " + hoppp.getdestination().getCity())
//            neighbour=hoppp.start;
//        }



