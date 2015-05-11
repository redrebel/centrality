package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import betweenness.BetweennessCentrality;


public class CentralityMain {
  static Graph graph = new Graph();
  
  public static int setGraphByFile(String filePath){
    int nodeNum = 0;
    try{
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      String s;
      s = br.readLine();
      nodeNum = Integer.parseInt(s);
      while((s = br.readLine()) != null)
      {
          String[] v = s.split(" ");
          System.out.println("\ninput key:"+v[0]+", value:"+v[1]);
          graph.add(v[0],v[1]);
          
      }
      br.close();
    }catch(Exception e){
      e.printStackTrace();
    }
    return nodeNum;
  }
  
  public static int setGraphByInline(){
    //int nodeNum = 0;
    
    graph.add("1", "2");
    graph.add("1", "3");
    graph.add("2", "4");
  graph.add("2", "5");
        graph.add("4", "7");
    graph.add("5", "7");
    graph.add("3", "6");
    graph.add("3", "8");
    graph.add("8", "6");
    graph.add("6", "7");
    graph.add("7", "5");
    
    return 5;
  }
	
	public static void main(String[] args) {
		
		
		try{
			// 
		  int nodeCnt;
		  //nodeCnt = setGraphByFile( "/Users/red/input.txt");
		  nodeCnt = setGraphByInline();
			
			//System.out.println(Node.map);
			graph.printAdjacencyList();
			
			BetweennessCentrality bc = new BetweennessCentrality();
			Map<String,Float> map = bc.getCentralityList(graph, nodeCnt);
			System.out.println(map);
			//graph.printAdjacencyList();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
