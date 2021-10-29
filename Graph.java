import java.util.*;

public class Graph
{
  HashSet<City> vertices;
  Map<String, Set<String>> adjVertices; //Adjacency List to store the edges of Cities which are connected


  public Graph()
    {
        vertices = new HashSet<City>(); //Set of Cities
        adjVertices= new HashMap<String,Set<String>>(); //Adjacency List for each City

    }

    public Set<City> getCities()
    {
      return vertices;
    }
}
