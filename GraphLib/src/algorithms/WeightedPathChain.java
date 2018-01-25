package algorithms;

import interfaces.Edge;
import interfaces.WeightedEdge;

public class WeightedPathChain<V, E extends WeightedEdge<V,W>,W, I extends  Comparable<I>> extends SimplePathChain<V, E, I> {
	WeightedPathChain(V vertex, E edge, I intermediate) {
		super(vertex, edge, intermediate);
	}

	public WeightedPathChain(V vertex, I value) {
		this(vertex, null , value);
	}
}
