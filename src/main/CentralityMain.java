package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import Closeness.ClosenessCentrality;
import betweenness.BetweennessCentrality;

public class CentralityMain {
  static Graph graph = new Graph();

  public static int setGraphByFile(String filePath) {
    int cntOfNode = 0;
    try {
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      String s;
      s = br.readLine();
      cntOfNode = Integer.parseInt(s);
      while ((s = br.readLine()) != null) {
        String[] v = s.split(" ");
        // System.out.println("input key:"+v[0]+", value:"+v[1]);
        graph.add(v[0], v[1]);

      }
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return cntOfNode;
  }

  /**
   * 개발시 테스트를 위하여 소스에서 정보를 입력받는다.
   * @return
   */
  public static int setGraphByInline() {

    graph.add("1", "2");
    graph.add("1", "3");
    graph.add("2", "3");
    graph.add("2", "4");
    graph.add("2", "5");

    return 5;
  }

  /**
   * 최대값을 출력한다.
   * @param map
   */
  public static void printMaxValue(Map<Integer, Float> map) {
    float maxBC = 0;
    for (Integer key : map.keySet()) {
      if (maxBC < map.get(key))
        maxBC = map.get(key);
    }
    System.out.format("Max of Betweenness Centrality : %.2f\n", maxBC);
  }

  public static void main(String[] args) {

    try {
      //
      int nodeCnt;
      nodeCnt = setGraphByFile("C:\\input.txt");
      // nodeCnt = setGraphByInline();

      Map<Integer, Float> map;

      BetweennessCentrality bc = new BetweennessCentrality();
      map = bc.getCentralityList(graph, nodeCnt);
      System.out.println(map);
      printMaxValue(map);

      ClosenessCentrality cc = new ClosenessCentrality();
      map = cc.getCentralityList(graph, nodeCnt);
      System.out.println(map);
      printMaxValue(map);

    } catch (Exception e) {
      e.printStackTrace();
    }
    System.exit(0);
  }

}
