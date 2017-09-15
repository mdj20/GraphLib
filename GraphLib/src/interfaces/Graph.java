package interfaces;
import java.util.Set;

public interface Graph<V,E extends Edge<V>> {
	public void addVertex(V vertex);
	public void addEdge(E edge);
	public void addEdge(V vertex1, V vertex2);
	public void removeVertex(V vertex);
	public void removeEdge(E edge);
	public Set<V> getVertices();
	public Set<E> getEdges();
	public Set<V> getAdjacentVertices(V vertex);
	public Set<E> getConnectingEdges(V vertex);
	public Set<V> getOutgoingVertices(V vertex);
	public Set<V> getIncomingVertices(V vertex);
	public Set<E> getOutgoingEdges(V vertex);
	public Set<E> getIncomingEdges(V vertex);
}
