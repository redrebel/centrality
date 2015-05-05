package main;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
	Map<String, Set<Node>> adjacencyList = new HashMap<String, Set<Node>>();
	public void add(String from, String to){
		System.out.println("addEdge(from="+from+", to="+ to+")");
		addEdge(from, to);
		System.out.println("addEdge(to="+to+", from="+ from+")");
		addEdge(to, from);
	}
	
	private void addEdge(String from, String to){
		Set<Node> list = adjacencyList.get(from);
		
		if(list == null){
			list = new LinkedHashSet<Node>();
			System.out.println("make list:" + from);
			adjacencyList.put(from, list);
		}
		
		list.add(Node.get(to));
		System.out.println(from + "\'s Adjacency List " + adjacencyList.get(from));
	}
	
	public void printAdjacencyList(){
		System.out.println(adjacencyList);
	}
	
}
