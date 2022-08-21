# Travel-Assistant
The objective of this problem is to generate a travel path between two or more cities while considering various factors such as cost, time and ease of transit (the number of hops). 

The objective of this problem is to generate a travel path between two or more cities while considering various factors such as cost, time and ease of transit (the number of hops). The general idea is as follows: The path from a City – SOURCE to a City – DESTINATION is calculated through an adaption of Dijkstra’s algorithm. The adaption integrates various factors that are of importance to travelers and generates an optimal route corresponding to a traveler’s priorities.
The program functions as follows. Users can make use of commands to add a City, add a Flight path between two cities and add a Train path between two cities. Subsequently they can plan a trip between two cities, utilizing the information that was previously entered.
The relative cost of travel is used to determine the shortest path from SOURCE to DESTINATION. The relative cost of travel can be termed as the weight of each edge (path) between two vertices (Cities).


The approach used to implement Dijkstra’s algorithm primarily utilizes 
1.	Priority Queue - To sort Cities by ascending order of relative cost of travel
2.	HashMaps - To store relative costs for each City, to store the departure city for each destination and to store the number of hops from SOURCE to DESTINATION 
3.	ArrayLists - To store Cities, Flights & Trains
4.	HashSet – To store Vertices that’ve been visited (in Dijkstra’s algorithm)

## Classes:

City.java
This class represents Cities:
Each object of type City is associated with the following attributes:
Variables:
 cityName - City name
 testRequired - States if a test is Required for Unvaccinated individuals to the city
 timeToTest - Time to Take a test
 nightlyHotelCost - Hotel Cost for 1 night
 cost_from_source - Cost to Travel from source to this City

Flight.java
This class represents Flights between cities.
Each object of type Flight is associated with the following attributes:
Variables:
 startCity - Start city
 destinationCity - Destination city
 flightTime - Time to travel
 flightCost - Cost of travel

Train.java
This class represents Trains between cities.
Each object of type Train is associated with the following attributes:
Variables:
 startCity - Start city
 destinationCity - Destination city
 flightTime - Time to travel
 flightCost - Cost of travel

Hop.java
This class represents a path/edge between two cities.
Each object of type Hop is associated with the following attributes:
Variables:
start - Start city
destination - Destination city
relative_cost - Relative cost of visiting city from the source
time - Time to travel
cost - Cost of travel
mode - Mode of travel (Fly / Train)

Graph.java
This class represents a path/edge between two cities.
Each object of type Hop is associated with the following attributes:
Variables:
Vertices – A HashSet which stores all vertices (Cities)
adjVertices – A HashMap which stores a set of Cities that are connected to  each City. This serves as the adjacency list.

TravelAssistant.java
This class represents a path/edge between two cities.
Each object of type Hop is associated with the following attributes:
Variables:
Cities – Stores a unique list of Cities
Hops  - Stores a unique list of Travel Paths between Cities 
Flights – Stores a unique list Flight paths between Cities
Trains – Stores a unique list Train paths between Cities

Methods:
encode():
This method accepts an input file from the standard input, parses the contents of the file, encodes it and stores the output in another text file.
Parameters:
String input_filename -  Path to the file which’s to be encoded
int level – The maximum number of characters which can be parsed in the last cycle is determined by 2^level.
boolean reset – If reset is true, frequency counts are reset at the beginning of every cycle.
String output_filename -  The path of the file, in which the output is stored.
