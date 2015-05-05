package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import betweenness.BetweennessCentrality;


public class CentralityMain {
	
	
	public static void main(String[] args) {
		
		Graph graph = new Graph();
		try{
			// 파일을 읽는다.
			BufferedReader br = new BufferedReader(new FileReader("/Users/red/input.txt"));
			String s;
			s = br.readLine();
			int nodeNum = Integer.parseInt(s);
			while((s = br.readLine()) != null)
			{
				String[] v = s.split(" ");
				System.out.println("\ninput key:"+v[0]+", value:"+v[1]);
				graph.add(v[0],v[1]);
				
			}
			//System.out.println(Node.map);
			graph.printAdjacencyList();
			
			BetweennessCentrality bc = new BetweennessCentrality();
			bc.getCentralityList(graph, nodeNum);
			br.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
