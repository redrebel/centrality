package betweenness;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.Centrality;
import main.Graph;
import main.Node;

public class BetweennessCentrality {
	
	private  Object mock = new Object();
	private  Map<String, Float> cl = new HashMap<String, Float>();
	
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
			
			for(Node n :graph.adjacents(next.key)){
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
	
	private float calCentrality(Graph graph, String i, int n){
		Integer j = new Integer(i)+1;
		if(j > n)
			j=1;
		
		float f;
		
		List<List<String>> list = findAllShortestPaths(graph, i, j.toString() );
		System.out.println("list.size() :" + list.size());
		System.out.println("list : " + list);
		f = (float)(2.0*list.size() / ((n-1)*(n-2)));
		System.out.println("calCentrality : "+i + " to "+ j + "("+f+")");
		return f;
	}
	
	public Map<String, Float> getCentralityList(Graph graph, int totalNodeNum){
		for(int i = 1; i <= totalNodeNum; i++){
			Node.clear();
			String str = String.valueOf(i);
			cl.put(str, calCentrality(graph,str,totalNodeNum));
		}
		//cl.put("8", calCentrality(graph,"8",totalNodeNum));
		return cl;
	}
}
