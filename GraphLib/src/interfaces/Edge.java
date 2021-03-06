package interfaces;
import java.util.List;

/**
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <V> Type of vertex that the edge connects.
 */

public interface Edge<V> {
	
	/**
	 * Returns a list containing 2 vertices
	 *
	 *  
	 * @return List<V> of vertices 
	 */
	
	public List<V> getVertices();
	/**
	 * 
	 * @param index must be either 0 or 1 
	 * @return V one of 2 vertices, or null if caller passes int !(1 || 0)
	 */
	
	public V getVertex(int index);
	
	/**
	 * Returns the vertex that opposes the vertex being passed.
	 * 
	 * 
	 * 
	 * @param vertex 
	 * @return V opposite vertex
	 */
	public V getOpposingVertex(V vertex);
	
	/** 
	 * Convenience method determines if the passed edge is the reciprocal of this edge.
	 * <p>
	 * Used to determine edge equivalence in an undirected graph.
	 * e.g. edge0 connects A->B, if edge1 B->A is passed to isReciprical method it will return true, 
	 * this holds true even if the edges are directed. 
	 * @param edge reference to edge who's reciprocity is to be determined.
	 * @return true if passed edge is reciprocal of object edge.
	 */
	public boolean isReciprical(Edge<V> edge);
}
