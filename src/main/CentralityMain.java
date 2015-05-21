package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import ccloseness.ClosenessCentrality;
import eigenvector.EigenvectorCentrality;
import betweenness.BetweennessCentrality;

public class CentralityMain {
  static Graph graph = new Graph();

  /**
   * 파일에서 그래프 정보를 얻어온다.
   * @param filePath
   * @return
   */
  public static int setGraphByFile(String filePath) {
    int cntOfNode = 0;  // 그래프의 노드 수(n)
    try {
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      String s;
      s = br.readLine();
      cntOfNode = Integer.parseInt(s);   
      graph.setNodeCnt(cntOfNode);
      while ((s = br.readLine()) != null) {
        String[] v = s.split(" ");
        // System.out.println("input key:"+v[0]+", value:"+v[1]);
        graph.add(v[0], v[1]);  // network의 선분(edge)를 입력한다.

      }

      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return cntOfNode;
  }

  /**
   * 개발시 테스트를 위하여 소스에서 정보를 입력받는다.
   * 
   * @return
   */
  public static int setGraphByInline() {

    /*
     * graph.setNodeCnt(5); graph.add("1", "2"); graph.add("1", "3");
     * graph.add("2", "3"); graph.add("2", "4"); graph.add("2", "5");
     * graph.printAdjacencyMatrix(); return 5;
     */

    graph.setNodeCnt(8);
    graph.add("1", "2");
    graph.add("1", "3");
    graph.add("1", "4");
    graph.add("4", "5");
    graph.add("5", "6");
    graph.add("4", "7");
    graph.add("7", "8");
    // graph.printAdjacencyMatrix();
    return 8;
  }

  /**
   * 가장 큰 Centrality 플 출력한다. 
   * 
   * @param map
   */
  public static void printMaxValue(Map<String, Double> map) {
    double maxBC = 0;
    for (String key : map.keySet()) {
      if (maxBC < map.get(key))
        maxBC = map.get(key);
    }
    // System.out.format("Max of Centrality : %.2f\n", maxBC);
    System.out.format("%.2f ", maxBC);
  }

  public static void main(String[] args) {

    try {
      //
      int nodeCnt;
      // nodeCnt = setGraphByFile("C:\\input.txt");
      nodeCnt = setGraphByInline();

      Map<String, Double> map;

      // Betweenness Centrality 를 구한다.
      BetweennessCentrality bc = new BetweennessCentrality();
      map = bc.getCentralityList(graph, nodeCnt);
      // System.out.println(map);
      printMaxValue(map);

      // Closeness Centrality 를 구한다.
      ClosenessCentrality cc = new ClosenessCentrality();
      map = cc.getCentralityList(graph, nodeCnt);
      // System.out.println(map);
      printMaxValue(map);

      // Eigenvector Centrality 를 구한다.
      EigenvectorCentrality ec = new EigenvectorCentrality();
      map = ec.getCentralityList(graph, nodeCnt);
      // System.out.println(map);
      printMaxValue(map);

    } catch (Exception e) {
      e.printStackTrace();
    }
    System.exit(0);
  }

}
