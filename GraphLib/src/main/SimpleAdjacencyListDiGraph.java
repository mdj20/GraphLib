package main;
import java.util.HashSet;
import java.util.Set;

import interfaces.DiGraph;
import interfaces.DirectionalEdge;
import interfaces.Edge;
import interfaces.Graph;

public class SimpleAdjacencyListDiGraph<V> extends AbstractAdjacencyListDiGraph<V,DirectionalEdge<V>> implements DiGraph<V,DirectionalEdge<V>> {

	@Override
	public DirectionalEdge<V> createEdge(V vertex1, V vertex2) {
		return new SimpleDirectionalEdge<V>(vertex1,vertex2);
	}
}
