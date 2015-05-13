package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
  List<Node> parents = new ArrayList<Node>();
  private static Map<String, Node> map = new HashMap<String, Node>();
  public final String key;
  private int level = -1;

  public static Node get(String str) {
    Node res = map.get(str);
    if (res == null) {
      // System.out.println("make node:" +str);
      res = new Node(str, -1);
      map.put(str, res);
    }

    return res;
  }

  public static void clear() {
    // System.out.println(map);
    for (String key : map.keySet()) {
      map.get(key).level = -1;
      map.get(key).clearParent();
      // System.out.println(map.get(key));
    }

  }

  public void clearParent() {
    parents = new ArrayList<Node>();
  }

  public Node(String str, int level) {
    key = str;
    this.level = level;
  }

  public void addParent(Node n) {
    // System.out.println("addParent : " );
    if (n.level == level) {
      // System.out.println("addParent : !! " );
      return;
    }
    parents.add(n);

    level = n.level + 1;

  }

  public List<Node> getParents() {
    return parents;
  }

  public boolean equals(Object n) {
    return key.equals(((Node) n).key);
  }

  @Override
  public String toString() {
    return key + "(" + level + ")";
  }
}
