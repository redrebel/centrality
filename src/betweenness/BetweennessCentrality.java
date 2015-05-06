package betweenness;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.Centrality;
import main.Graph;
import main.Node;

public class BetweennessCentrality implements Centrality{
	private static Object mock = new Object();
	private Map<String, Float> cl = new HashMap<String, Float>();
	public List<List<String>> findAllShortestPaths(Graph graph, String from, String to){
		LinkedHashMap<Node, Object> queue = new LinkedHashMap<Node, Object>();
		Set<Node> visited = new HashSet<Node>();
		queue.put(new Node(from, 0), mock);
		
		Node nodeTo = null;
		while(queue.keySet().size()  > 0){
			Node next = queue.keySet().iterator().next();
			
			if(next.key.equals(to)){
				nodeTo = next;
				break;
			}
			
			for(Node n:graph.adjacents(next.key)){
				if(!visited.contains(n)){
					if(queue.get(n) == null){
						queue.put(n,mock);
					}
					n.addParent(next);
				}
			}
			
			// removing the node from queue
			queue.remove(next);
			visited.add(next);
		}
		
		if(nodeTo == null){
			return Collections.emptyList();
		}
		
		// Now 
		List<List<String>> result = new ArrayList<List<String>>();
		dfs(nodeTo, result, new LinkedList<>()())
	}
	
	private float calCentrality(Graph graph, String i, int n){
		float f;
		f = (2*10 / ((n-1)*(n-2)));
		return f;
	}
	public Map<String, Float> getCentralityList(Graph graph, int n){
		for(int i = 1; i <= n; i++){
			String str = String.valueOf(i);
			cl.put(str, calCentrality(graph,str,n));
		}
		return cl;
	}
}
