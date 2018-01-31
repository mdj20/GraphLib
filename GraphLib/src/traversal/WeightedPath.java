package traversal;

import java.util.List;

import interfaces.WeightedEdge;

/**
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex Type
 * @param <W> Weight Type
 */

public interface WeightedPath<V,W> extends Path<V,WeightedEdge<V,W>> {
	public List<W> getWeights();
}
