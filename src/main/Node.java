package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
	List<Node> parents = new ArrayList<Node>();
	private static Map<String, Node> map = new HashMap<String, Node>();
	public final String key;
	private int level = -1;
	
	public static Node get(String str){
		Node res = 	map.get(str);
		if(res == null){
			System.out.println("make node:" +str);
			res = new Node(str, -1);
			map.put(str, res);
		}
		
		return res;
	}
	
	public Node(String str, int level){
		key = str;
		this.level = -1;
	}
	
	public void addParent(Node n){
		if(n.level == level){
			return;
		}
		parents.add(n);
		
		level = n.level + 1;
	}
	public boolean equals(Object n){
		return key.equals(((Node)n).key);
	}
	
	@Override
	public String toString(){
		return key+"("+level+")";
	}
}
