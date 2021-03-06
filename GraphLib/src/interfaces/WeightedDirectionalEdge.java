package interfaces;
/**
 * Combines WeightedEdge<V,E>, DirectionalEdge<V> describes the edge object used to build WeightedDiGraph.
 * 
 * 
 * @author Matthew D. Jeffreys
 * 
 * @param <V> Vertex type
 * @param <W> Weight type
 */
public interface WeightedDirectionalEdge<V,W> extends WeightedEdge<V,W>, DirectionalEdge<V> {}
