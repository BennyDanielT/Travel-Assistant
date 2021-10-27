import java.util.ArrayList;

public class TravelAssistant
{
    private ArrayList<City> Cities = new ArrayList<>();

    private ArrayList<Flight> Flights = new ArrayList<>();
    private ArrayList<Train> Trains = new ArrayList<>();

    private Graph travel_graph=new Graph();

    //Method to add a Cityv
    boolean addCity( String cityName, boolean testRequired, int timeToTest, int nightlyHotelCost ) throws IllegalArgumentException
        {
        boolean check =true;
        boolean city_exists=checkCities(cityName);

        if(cityName==null || cityName.isEmpty() || (nightlyHotelCost<0)) //Check for Invalid Input
        {
            check=false;
            System.out.println("Invalid parameters are entered!");
            return false;
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

    public boolean checkCities(String cityName) // Method to check if a City entered by the user already exists
    {
        boolean city_exists=false;
        for (int i=0;i<Cities.size();i++)
        {
            if(Cities.get(i).getCity()==cityName) //Traverse through the ArrayList to check if the City already exists
            {
                city_exists=true; // Return True if City Exists
            }
        }
        return city_exists;
    }

    boolean addFlight( String startCity, String destinationCity, int flightTime, int flightCost)
            throws IllegalArgumentException
    {
        boolean check =true;
        boolean flight_exists=checkFlights(startCity,destinationCity,flightTime,flightCost);

        if(startCity==null || startCity.isEmpty() || destinationCity==null || destinationCity.isEmpty() || (flightTime<=0)|| (flightCost<=0)) //Check for Invalid Input
        {
            check=false;
            System.out.println("Invalid parameters are entered!");
            return false;
        }

        else if(flight_exists) // Check if the information entered contradicts data already known.
        {
            System.out.println("City exists in records");
            return false;
        }

        if(travel_graph.adjVertices.containsKey(startCity) && travel_graph.adjVertices.containsKey(destinationCity)) /*If both start
         and destination Cities exist in the Travel Assistant then include an edge between the cities, with the mode of transport as Flight*/
        {
            Flight f1 = new Flight(startCity, destinationCity, flightTime, flightCost); //If the information is valid, add the City to the ArrayList
            //Flights.add(f1);

            travel_graph.adjVertices.get(startCity).add(destinationCity); //Add
            return true;
        }

    }

    public boolean checkFlights(String startCity, String destinationCity, int flightTime, int flightCost) // Check if a City entered already exists
    {
        boolean flight_exists = false;
        for (int i = 0; i < Flights.size(); i++) {
            if (Flights.get(i).getStartCity() == startCity && Flights.get(i).getEndCity() == destinationCity) {
                flight_exists = true;
            }
        }
        return flight_exists;
    }

    //Add a Train route to the Travel Assistant
    boolean addTrain( String startCity, String destinationCity, int trainTime, int trainCost) throws IllegalArgumentException
    {
        boolean check =true;
        boolean train_exists=checkTrains(startCity,destinationCity,trainTime,trainCost);

        if(startCity==null || startCity.isEmpty() || destinationCity==null || destinationCity.isEmpty() || (trainTime<=0)|| (trainCost<=0)) //Check for Invalid Input
        {
            check=false;
            System.out.println("Invalid parameters are entered!");
            return false;
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
        ArrayList<String> output=new ArrayList <String>();
        /*
        0- Add cities to a list
        0 (B)- Create a network with Cities as Vertices
        (C) - Create Edges between cities (Flights / Trains)
        1- Observe the list of options under Cities
        2- Determine the factors that're more important to the user
        3- Return a route and th
         */
        return output;
    }

} // End of Class - Travel Assistant
