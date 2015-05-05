package main;

import java.util.Map;

public interface Centrality {
	public Map<String, Float> getCentralityList(Graph graph, int n);
}
