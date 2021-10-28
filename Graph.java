import java.util.*;

public class Graph
{
  HashSet<City> vertices;
  private List<Hop> hops;

  Map<City, List<City>> adjVertices; //adjacency List to store the edges of Cities which are connected

    public Graph()
    {
        vertices = new HashSet<City>();
        //hops = new ArrayList<Hop>();
        adjVertices= new HashMap<City,List<City>>();
        //visited = new HashSet<String>();
//        pqueue = new PriorityQueue<City>(V, new City());
    }

    public Set<City> getCities()
    {
      return vertices;
    }

  public List<Hop> getRoutes()
  {
    return hops;
  }

//    public void addEdge(String start,String destination,int time,int cost)
//    {
//
//    }
}
