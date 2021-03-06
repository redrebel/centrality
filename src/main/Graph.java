package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
  int nodeCnt;
  double[][] adjacencyMatrix;
  private Object mock = new Object();
  Map<String, Set<Node>> adjacencyList = new HashMap<String, Set<Node>>();

  public void setNodeCnt(int nodeCnt) {
    this.nodeCnt = nodeCnt;
    adjacencyMatrix = new double[nodeCnt][nodeCnt];
    for (int i = 0; i < nodeCnt; i++) {
      for (int j = 0; j < nodeCnt; j++) {
        adjacencyMatrix[i][j] = 0.0;
      }
    }
  }

  public void printAdjacencyMatrix() {
    for (int i = 0; i < nodeCnt; i++) {
      for (int j = 0; j < nodeCnt; j++) {
        System.out.print(adjacencyMatrix[i][j] + ", ");
      }
      System.out.println();
    }
  }

  public double[][] getAdjacencyMatrix() {
    return adjacencyMatrix;
  }

  public List<List<String>> findAllShortestPaths(String from, String to) {
    LinkedHashMap<Node, Object> queue = new LinkedHashMap<Node, Object>();
    Set<Node> visited = new HashSet<Node>();
    queue.put(new Node(from, 0), mock);

    Node nodeTo = null;
    while (queue.keySet().size() > 0) {
      Node next = queue.keySet().iterator().next();

      if (next.key.equals(to)) {
        nodeTo = next;
        break;
      }

      for (Node n : adjacents(next.key)) {
        if (!visited.contains(n)) {
          if (queue.get(n) == null) {
            queue.put(n, mock);
          }

          n.addParent(next);
        }
      }

      // removing the node from queue
      queue.remove(next);
      visited.add(next);
    }

    if (nodeTo == null) {
      return Collections.emptyList();
    }

    List<List<String>> result = new ArrayList<List<String>>();
    dfs(nodeTo, result, new LinkedList<String>());

    // 계산이 끝나면 다음 계산을 위하여 초기화 해준다.
    Node.clear();
    return result;
  }

  /**
   * 깊이 우선 탐색을 한다.
   * 
   * @param n
   * @param result
   * @param path
   */
  private void dfs(Node n, List<List<String>> result, LinkedList<String> path) {
    path.addFirst(n.key);
    List<Node> pr = n.getParents();
    if (pr.size() == 0) {
      // base case: we came to target vertex
      result.add(new ArrayList<String>(path));
    }
    for (Node p : pr) {
      dfs(p, result, path);
    }
    // do not forget to remove the processed element from path
    path.removeFirst();
  }

  public void add(String from, String to) {
    // System.out.println("addEdge(from="+from+", to="+ to+")");
    addEdge(from, to);
    // System.out.println("addEdge(to="+to+", from="+ from+")");
    addEdge(to, from);

    adjacencyMatrix[Integer.parseInt(from) - 1][Integer.parseInt(to) - 1] = 1.0;
    adjacencyMatrix[Integer.parseInt(to) - 1][Integer.parseInt(from) - 1] = 1.0;
  }

  private void addEdge(String from, String to) {
    Set<Node> list = adjacencyList.get(from);

    if (list == null) {
      list = new LinkedHashSet<Node>();
      // System.out.println("make list:" + from);
      adjacencyList.put(from, list);
    }

    list.add(Node.get(to));
    // System.out.println(from + "\'s Adjacency List " +
    // adjacencyList.get(from));
  }

  public void printAdjacencyList() {
    System.out.println(adjacencyList);
  }

  public Set<Node> adjacents(String v) {
    Set<Node> nodes = adjacencyList.get(v);
    if (nodes == null) {
      nodes = Collections.emptySet();
    }
    return nodes;
  }
}
