package eigenvector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import main.Graph;

public class EigenvectorCentrality {
  EigenDecomposition ed;

  /**
   * i 에 대한 Centrality를 구한다.
   * 
   * @param graph
   * @param i
   * @param n
   * @return
   */
  private float calCentrality(Graph graph, int i, int cntOfNode) {

    RealMatrix m = MatrixUtils.createRealMatrix(graph.getAdjacencyMatrix());

    EigenDecomposition ed = new EigenDecomposition(m);
    // int sum = (int)ed.getEigenvector(i).getMaxValue();
    double sum = ed.getRealEigenvalue(i - 1);
    System.out.println("[" + i + "] Sum : " + sum);

    return (float) sum;
  }

  private int getMaxEigenvectorN(Graph graph, int cntOfNode) {
    RealMatrix m = MatrixUtils.createRealMatrix(graph.getAdjacencyMatrix());
    ed = new EigenDecomposition(m);

    double maxValue = 0;
    int n = 0;
    for (int i = 0; i < cntOfNode; i++) {
      double value = ed.getRealEigenvalue(i);
      if (maxValue < value) {
        n = i;
        maxValue = value;
      }
    }
    return n;
  }

  public Map<String, Double> getCentralityList(Graph graph, int cntOfNode) {
    Map<String, Double> cl = new HashMap<String, Double>();
    int n = getMaxEigenvectorN(graph, cntOfNode);
    for (int i = 1; i <= cntOfNode; i++) {
      // System.out.println("\ncalCentrality : ["+i + "]");
      double a = (float) ed.getEigenvector(n).getEntry(i - 1) * (-1);
      a = Math.round(a * 1000d) / 1000d;
      cl.put(Integer.toString(i), a);

    }
    return cl;
  }
}
