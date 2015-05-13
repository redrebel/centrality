package betweenness;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Graph;

public class BetweennessCentrality {

  private Map<Integer, Float> cl = new HashMap<Integer, Float>();

  private int numShortestPathViaI(List<List<String>> list, int i) {
    int cnt = 0;
    for (List<String> path : list) {
      if (path.contains(Integer.toString(i)))
        cnt++;
    }
    return cnt;
  }

  /**
   * i 에 대한 Centrality를 구한다.
   * 
   * @param graph
   * @param i
   * @param n
   * @return
   */
  private float calCentrality(Graph graph, int i, int cntOfNode) {
    int[][] searched;
    searched = new int[cntOfNode + 1][cntOfNode + 1];

    float f = 0;
    for (int j = 1; j <= cntOfNode; j++) {
      if (i == j) {
        continue;
      }
      for (int k = 1; k <= cntOfNode; k++) {
        if (j == k || i == k || searched[j][k] == 1) {
          continue;
        }
        List<List<String>> list = graph.findAllShortestPaths(
            Integer.toString(j), Integer.toString(k));
        int cntPathViaI = numShortestPathViaI(list, i);
        f = f + (cntPathViaI / list.size());
        /*
         * System.out.println("["+i+"] from : "+ j + " to "+ k +
         * " list.size() :" + list.size() + " list : " + list + "viaI : " +
         * cntPathViaI + " f : " + f );
         */
        searched[k][j] = 1;
      }

    }
    return (float) (2 * f / ((cntOfNode - 1) * (cntOfNode - 2)));
  }

  /**
   * 1부터 n까지의 Centrality 를 구한다.
   * 
   * @param graph
   * @param cntOfNode
   * @return
   */
  public Map<Integer, Float> getCentralityList(Graph graph, int cntOfNode) {
    for (int i = 1; i <= cntOfNode; i++) {
      // System.out.println("\ncalCentrality : ["+i + "]");
      cl.put(i, calCentrality(graph, i, cntOfNode));

    }
    return cl;
  }
}
