package main;

import interfaces.WeightedEdge;
import interfaces.WeightedGraph;

public class WeightedAdjacencyListGraph<V, W> extends AbstractAdjacencyListGraph<V,WeightedEdge<V,W>> implements WeightedGraph<V,WeightedEdge<V,W>,W> {



	@Override
	public WeightedEdge<V, W> createEdge(V vertex1, V vertex2) {
		return new SimpleWeightedEdge<V,W>(vertex1,vertex2);
	}

	@Override
	public void addEdge(V vertex1, V vertex2, W weight) {
		WeightedEdge<V,W> edge = new SimpleWeightedEdge<V,W>(vertex1,vertex2,weight);
		super.addEdge(edge);
	}

}