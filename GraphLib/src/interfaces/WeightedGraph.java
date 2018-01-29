package interfaces;

/**
 * 	
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex type. 
 * @param <E> Edge Type extends WeightedEdge<V,W>.
 * @param <W> Weight type.
 */


public interface WeightedGraph<V,E extends WeightedEdge<V,W>, W> extends Graph<V,E> {
	public void addEdge(V vertex1, V vertex2, W weight);
}
