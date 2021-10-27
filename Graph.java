import java.util.*;

public class Graph
{
    //int dist[];
  HashSet<City> vertices;

  Map<City, List<City>> adjVertices; //adjacency List to store the edges of Cities which are connected

//    String city_name;
//    Set<String> visited;
//
//    PriorityQueue<City> pqueue;
//    List<List<City> > adj_list;

    public Graph()
    {
        vertices = new HashSet<City>();
        adjVertices= new HashMap<City,List<City>>();
        //visited = new HashSet<String>();
//        pqueue = new PriorityQueue<City>(V, new City());
    }
}
