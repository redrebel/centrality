package main;

import java.util.HashMap;
import java.util.Map;

public class Node {
	private static Map<String, Node> map = new HashMap<String, Node>();
	private  String key;
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
	
	@Override
	public String toString(){
		return key+"("+level+")";
	}
}
