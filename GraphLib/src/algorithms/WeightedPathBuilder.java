package algorithms;

import interfaces.WeightedEdge;
import interfaces.WeightedGraph;

public class WeightedPathBuilder<G extends WeightedGraph<V,E,W>, V, E extends WeightedEdge<V,W>, W> extends PathBuilder<G, V, E> {
	public WeightedPathBuilder(G graph, V source) {
		super(graph, source);
	}
}
