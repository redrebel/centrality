package ccloseness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Graph;

public class ClosenessCentrality {

  /**
   * i 에 대한 Centrality를 구한다.
   * 
   * @param graph
   * @param i
   * @param n
   * @return
   */
  private double calCentrality(Graph graph, int i, int cntOfNode) {

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
    // System.out.println("[" + i + "] Sum : " + sum);

    return ((cntOfNode - 1) / sum);
  }

  /**
   * 1부터 N까지의 Centrality 를 구한다.
   * 
   * @param graph
   * @param cntOfNode
   * @return
   */
  public Map<String, Double> getCentralityList(Graph graph, int cntOfNode) {
    Map<String, Double> cl = new HashMap<String, Double>();
    for (int i = 1; i <= cntOfNode; i++) {
      cl.put(Integer.toString(i), calCentrality(graph, i, cntOfNode));
    }

    return cl;
  }

}
