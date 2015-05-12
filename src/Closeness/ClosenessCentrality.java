package Closeness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Graph;

public class ClosenessCentrality {

  private  Map<Integer, Float> cl = new HashMap<Integer, Float>();
  
  /**
   * i ~ j 까지의 Centrality 구한다.
   * @param graph
   * @param i
   * @param n
   * @return
   */
  private float calCentrality(Graph graph, int i, int cntOfNode){

    int sum = 0;
    for(int j=1; j<=cntOfNode; j++){
      if(i != j){
        List<List<String>> list = graph.findAllShortestPaths(Integer.toString(i), Integer.toString(j));
        //System.out.println(list);
        sum = sum + list.get(0).size()-1;;
      }
    }
    System.out.println("["+ i + "] Sum : "+ sum);
    return (float)(2*i / ((cntOfNode-1)*(cntOfNode-2)));
  }
  
  /**
   * 1부터 N까지의 Centrality 를 구한다.
   * @param graph
   * @param cntOfNode
   * @return
   */
  public Map<Integer, Float> getCentralityList(Graph graph, int cntOfNode){
      for(int i = 1; i <= cntOfNode; i++){
        //System.out.println("\ncalCentrality : ["+i + "]");
          cl.put(i, calCentrality(graph,i,cntOfNode));
          
      }
      //cl.put("5", calCentrality(graph,"5",totalNodeNum));
/*    System.out.println(findAllShortestPaths(graph, "1", "2"));
    System.out.println(findAllShortestPaths(graph, "1", "3"));
    System.out.println(findAllShortestPaths(graph, "1", "4"));
    System.out.println(findAllShortestPaths(graph, "1", "5"));
    System.out.println(findAllShortestPaths(graph, "1", "6"));*/
      return cl;
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
