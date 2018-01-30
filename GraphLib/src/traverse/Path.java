package traverse;

import java.util.List;

import interfaces.Edge;

public interface Path<V,E extends Edge<V>> {
	public List<E> getEdgeList();
	public V getSource();
	public V getSink();
	public V getVertexAt(int i);
}
