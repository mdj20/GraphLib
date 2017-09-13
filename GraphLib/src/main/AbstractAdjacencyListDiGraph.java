package main;
import java.util.HashSet;
import java.util.Set;

import interfaces.DiGraph;
import interfaces.DirectionalEdge;
import interfaces.Edge;

public abstract class AbstractAdjacencyListDiGraph<V,E extends DirectionalEdge<V>> extends AbstractAdjacencyListGraph<V,E> implements DiGraph<V,E> {

	@Override
	public void addEdge(E edge) {
		if (checkVertices(edge)) {
			addEdgeToGraph(edge.getSource(),edge);
			edges.add(edge);
		}
	}
	
	@Override
	public void removeEdge(E edge) {
		if (checkVertices(edge)) {
			removeEdgeFromGraph(edge.getSource(),edge);
			edges.remove(edge);
		}
	}

	@Override
	public Set<V> getOutgoingVertices(V vertex) {
		HashSet<V> ret = new HashSet<V>();
		if(graph.containsKey(vertex)) {
			for(Edge<V> e: graph.get(vertex)) {
				ret.add( e.getOpposingVertex(vertex));
			}
		}
		return ret;
	}

	@Override
	public Set<V> getIncomingVertices(V vertex) {
		HashSet<V> ret = new HashSet<V>();
		if(graph.containsKey(vertex)) {
			for(DirectionalEdge<V> d: edges){
				if(vertex.equals(d.getSink())) {
					ret.add(d.getSource());
				}
			}
		}
		return ret;
	}

	@Override
	public Set<E> getOutgoingEdges(V vertex) {
		if(graph.containsKey(vertex)) {
			return graph.get(vertex);
		}
		else 
			return null;
	}

	@Override
	public Set<E> getIncomingEdges(V vertex) {
		HashSet<E> ret = new HashSet<E>();
		if(graph.containsKey(vertex)) {
			for(E d: edges){
				if(vertex.equals(d.getSink())) {
					ret.add(d);
				}
			}
		}
		return ret;
	}

}