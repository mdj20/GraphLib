package interfaces;

import java.util.Set;

public interface DiGraph<V,E extends DirectionalEdge<V>> extends Graph<V,E> {
	public Set<V> getOutgoingVertices(V vertex);
	public Set<V> getIncomingVertices(V vertex);
	public Set<E> getOutgoingEdges(V vertex);
	public Set<E> getIncomingEdges(V vertex);
}
