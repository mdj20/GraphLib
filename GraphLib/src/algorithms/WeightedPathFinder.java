package algorithms;

import interfaces.WeightedEdge;
import interfaces.WeightedGraph;

public class WeightedPathFinder<G extends WeightedGraph<V,E,W>, E extends WeightedEdge<V,W>, V, W> extends AbstractPathFinder<G, V, E> {

	protected WeightedPathFinder(G graph) {
		super(graph);
	}
	
	public static <G extends WeightedGraph<V,E,W>, E extends WeightedEdge<V,W>, V, W> WeightedPathFinder<G, E, V, W> getWeightedPathFinder(G graph){
		return new WeightedPathFinder<G,E,V,W>(graph);
	}
}
