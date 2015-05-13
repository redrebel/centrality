package Closeness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Graph;

public class ClosenessCentrality {

  private Map<Integer, Float> cl = new HashMap<Integer, Float>();

  /**
   * i 에 대한 Centrality를 구한다.
   * 
   * @param graph
   * @param i
   * @param n
   * @return
   */
  private float calCentrality(Graph graph, int i, int cntOfNode) {

    float sum = 0;
    for (int j = 1; j <= cntOfNode; j++) {
      if (i != j) {
        List<List<String>> list = graph.findAllShortestPaths(
            Integer.toString(i), Integer.toString(j));
        // System.out.println(list);
        sum = sum + list.get(0).size() - 1;
        ;
      }
    }
    System.out.println("[" + i + "] Sum : " + sum);

    return (float) ((cntOfNode - 1) / sum);
  }

  /**
   * 1부터 N까지의 Centrality 를 구한다.
   * 
   * @param graph
   * @param cntOfNode
   * @return
   */
  public Map<Integer, Float> getCentralityList(Graph graph, int cntOfNode) {
    for (int i = 1; i <= cntOfNode; i++) {
      cl.put(i, calCentrality(graph, i, cntOfNode));
    }

    return cl;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
