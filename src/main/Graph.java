package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
  private  Object mock = new Object();
  Map<String, Set<Node>> adjacencyList = new HashMap<String, Set<Node>>();
	
  public List<List<String>> findAllShortestPaths( String from, String to){
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
	            
	            for(Node n :adjacents(next.key)){
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
	        dfs(nodeTo, result, new LinkedList<String>());
	        
	        //queue = null;
	        Node.clear();
	        return result;
	    }
	    

	    
	    private void dfs(Node n, List<List<String>> result, LinkedList<String> path){
	        path.addFirst(n.key);
	        List<Node> pr = n.getParents();
	        if (pr.size() == 0) {
	            // base case: we came to target vertex
	            result.add(new ArrayList<String>(path));
	        }
	        for (Node p : pr) {
	            dfs(p, result, path);
	        }
	        // do not forget to remove the processed element from path
	        path.removeFirst();
	    }
	    
	public void add(String from, String to){
		//System.out.println("addEdge(from="+from+", to="+ to+")");
		addEdge(from, to);
		//System.out.println("addEdge(to="+to+", from="+ from+")");
		addEdge(to, from);
	}
	
	private void addEdge(String from, String to){
		Set<Node> list = adjacencyList.get(from);
		
		if(list == null){
			list = new LinkedHashSet<Node>();
			//System.out.println("make list:" + from);
			adjacencyList.put(from, list);
		}
		
		list.add(Node.get(to));
		//System.out.println(from + "\'s Adjacency List " + adjacencyList.get(from));
	}
	
	public void printAdjacencyList(){
		System.out.println(adjacencyList);
	}
	
	public Set<Node> adjacents(String v){
		Set<Node> nodes = adjacencyList.get(v);
		if(nodes == null){
			nodes = Collections.emptySet();
		}
		return nodes;
	}
}
