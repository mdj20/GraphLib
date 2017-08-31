package main;
import java.util.HashSet;
import java.util.Set;

import Interfaces.DiGraph;
import Interfaces.DirectionalEdge;
import Interfaces.Edge;
import Interfaces.Graph;

public class SimpleAdjacencyListDiGraph<V> extends AbstractAdjacencyListDiGraph<V,DirectionalEdge<V>> implements DiGraph<V,DirectionalEdge<V>> {

	@Override
	public DirectionalEdge<V> createEdge(V vertex1, V vertex2) {
		return new DiEdge<V>(vertex1,vertex2);
	}
}
