package main;

import java.util.Map;

public interface Centrality {
	public Map<String, Double> getCentralityList(Graph graph, int n);
}
