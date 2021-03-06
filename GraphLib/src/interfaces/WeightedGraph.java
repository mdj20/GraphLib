package interfaces;

/**

 * @author Matthew D. Jeffreys
 *
 * @param <V> Vertex type. 
 * @param <E> Edge Type extends WeightedEdge<V,W>.
 * @param <W> Weight type.
 */


public interface WeightedGraph<V,E extends WeightedEdge<V,W>, W> extends Graph<V,E> {

	/**
	 * Creates and adds edge with the weight specified by weight.
	 *  
	 * @param vertex1 First vertex connected by edge.
	 * @param vertex2 Second vertex connected by edge
	 * @param weight Weight of the edge.
	 */
	
	public boolean addEdge(V vertex1, V vertex2, W weight);
}
