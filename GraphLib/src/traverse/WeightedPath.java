package traverse;

import java.util.List;

import interfaces.WeightedEdge;

public interface WeightedPath<V,W> extends Path<V,WeightedEdge<V,W>> {
	public List<W> getWeights();
}
