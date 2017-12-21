package main;

import java.util.Set;

public class SimpleAdjacencyListGraph<V> extends AbstractAdjacencyListGraph<V, SimpleEdge<V>> {
	@Override
	public SimpleEdge<V> createEdge(V vertex1, V vertex2) {
		return new SimpleEdge<V>(vertex1, vertex2);
	}

}
