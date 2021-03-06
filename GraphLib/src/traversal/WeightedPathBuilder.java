package traversal;

import interfaces.WeightedEdge;
import interfaces.WeightedGraph;
import traversal.algorithm.AlgorithmUtility;

/**
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <G> Graph type.
 * @param <V> Vertex type.
 * @param <E> Edge type.
 * @param <W> Weight type.
 */

public class WeightedPathBuilder<G extends WeightedGraph<V,E,W>, V, E extends WeightedEdge<V,W>, W extends Number> extends PathBuilder<G, V, E> {
	
	public WeightedPathBuilder(G graph, V source) {
		super(graph, source);
	}
	
	public Path<V,E> dikstrasInt(V source, V sink){
		Path<V,E> ret = null;
		if(!AlgorithmUtility.hasNegativeEdge(graph)) {
			
		}
		return ret;
	}
	
}
