package traverse;

import interfaces.WeightedEdge;
import interfaces.WeightedGraph;

/**
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <G> Graph type.
 * @param <V> Vertex type.
 * @param <E> Edge type.
 * @param <W> Weight type.
 */

public class WeightedPathBuilder<G extends WeightedGraph<V,E,W>, V, E extends WeightedEdge<V,W>, W> extends PathBuilder<G, V, E> {
	public WeightedPathBuilder(G graph, V source) {
		super(graph, source);
	}
	
}
