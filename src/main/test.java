package main;


/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * 2 - 4
 * / \    \
 * /   5---- 7
 * 1          /
 * \   8    /
 * \ /  \ /
 * 3 -- 6
 * <p>
 * A-B-C-E-F
 * |     |
 * D------
 *
 */
class test {
    private static Object mock = new Object();

 public static List<List<String>> findAllShortestPaths(Graph graph, String from, String to) {
        LinkedHashMap<Node, Object> queue = new LinkedHashMap<Node, Object>();
        Set<Node> visited = new HashSet<Node>();
        queue.put(new Node(from, 0), mock);

        Node nodeTo = null;
        while (queue.keySet().size() > 0) {
            Node next = queue.keySet().iterator().next();

            if (next.key.equals(to)) {
                // base case: we found the end node and processed all edges to it -> we are done
                nodeTo = next;
                break;
            }

            for (Node n : graph.adjacents(next.key)) {
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

        // Now performing the dfs from target node to gather all paths
        List<List<String>> result = new ArrayList<List<String>>();
        dfs(nodeTo, result, new LinkedList<String>());

        return result;
    }

    private static void dfs(Node n, List<List<String>> result, LinkedList<String> path) {
        path.addFirst(n.key);
        if (n.getParents().size() == 0) {
            // base case: we came to target vertex
            result.add(new ArrayList<String>(path));
        }
        for (Node p : n.getParents()) {
            dfs(p, result, path);
        }
        // do not forget to remove the processed element from path
        path.removeFirst();
    }

    private static class Graph {
        Map<String, Set<Node>> adjacencyList = new HashMap<String, Set<Node>>();

        public void add(String from, String to) {
            addEdge(to, from);
            addEdge(from, to);
        }

        private void addEdge(String from, String to) {
            Set<Node> list = adjacencyList.get(from);

            if (list == null) {
                list = new LinkedHashSet<Node>();
                adjacencyList.put(from, list);
            }
            list.add(Node.get(to));
        }

        public Set<Node> adjacents(String v) {
            Set<Node> nodes = adjacencyList.get(v);
            return (Set<Node>) (nodes == null ? Collections.emptySet() : nodes);
        }
        
        public void printAdjacencyList(){
          System.out.println(adjacencyList);
      }
    }

    private static class Node {
        List<Node> parents = new ArrayList<Node>();
        private static Map<String, Node> map = new HashMap<String, Node>();
        private final String key;
        int level = -1;

        public static Node get(String str) {
            // inner interning of nodes
            Node res = map.get(str);
            if (res == null) {
                res = new Node(str, -1);
                map.put(str, res);
            }
            return res;
        }

        private Node(String str, int level) {
            key = str;
            this.level = level;
        }

        public void addParent(Node n) {
            // forbidding the parent it its level is equal to ours
            if (n.level == level) {
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

        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public String toString() {
          return key+"("+level+")";
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

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
        //graph.printAdjacencyList();
        System.out.println(findAllShortestPaths(graph, "1", "2"));
        System.out.println(findAllShortestPaths(graph, "1", "3"));
        System.out.println(findAllShortestPaths(graph, "1", "4"));
        System.out.println(findAllShortestPaths(graph, "1", "5"));
        System.out.println(findAllShortestPaths(graph, "1", "6"));
        System.out.println(findAllShortestPaths(graph, "1", "7"));
        //graph.printAdjacencyList();
        
        graph = new Graph();

        graph.add("A", "B");
        graph.add("B", "D");
        graph.add("B", "C");
        graph.add("C", "E");
        graph.add("E", "F");
        graph.add("F", "D");

        System.out.println(findAllShortestPaths(graph, "A", "E"));
    }
}