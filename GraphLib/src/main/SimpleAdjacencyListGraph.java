package main;

import java.util.Set;

public class SimpleAdjacencyListGraph<V> extends AbstractAdjacencyListGraph<V, SimpleEdge<V>> {
	@Override
	public SimpleEdge<V> createEdge(V vertex1, V vertex2) {
		return new SimpleEdge<V>(vertex1,vertex2);
	}

	@Override
	public Set<V> getOutgoingVertices(V vertex) {
		return super.getAdjacentVertices(vertex);
	}

	@Override
	public Set<V> getIncomingVertices(V vertex) {
		return super.getAdjacentVertices(vertex);
	}

	@Override
	public Set<SimpleEdge<V>> getOutgoingEdges(V vertex) {
		return super.getConnectingEdges(vertex);
	}

	@Override
	public Set<SimpleEdge<V>> getIncomingEdges(V vertex) {
		return super.getConnectingEdges(vertex);
	}
}
