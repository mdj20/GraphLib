package main;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import interfaces.Edge;
import interfaces.Graph;

/*
 * 	TempSimpleAdjacencyListGraph is an undirected graph, allows for both loops and parallel edges.
 */


public class TempSimpleAdjacencyListGraph<V> implements Graph<V,Edge<V>> {

	private HashMap<V, HashSet<Edge<V>>> graph;
	private HashSet<Edge<V>> edges;
	
	TempSimpleAdjacencyListGraph(){
		graph = new HashMap<V,HashSet<Edge<V>>>();
		edges= new HashSet<Edge<V>>();
	}

	@Override
	public void addVertex(V vertex) {
		if(!graph.containsKey(vertex))
			graph.put(vertex, new HashSet<Edge<V>>());
	}

	@Override
	public void addEdge(Edge<V> edge) {
		if(checkVertices(edge.getVertices().get(0),edge.getVertices().get(1))) {
			addEdgeToGraph(edge.getVertices().get(0),edge);
			addEdgeToGraph(edge.getVertices().get(1),edge);
			edges.add(edge);
		}
	}

	@Override
	public void removeVertex(V vertex) {
		if(graph.containsKey(vertex)) {
			edges.removeAll(graph.get(vertex));	
			graph.remove(vertex);
		}
	}

	@Override
	public void removeEdge(Edge<V> edge) {
		if(checkVertices(edge)) {
			removeEdgeFromGraph(edge.getVertices().get(0),edge);
			removeEdgeFromGraph(edge.getVertices().get(1),edge);
		}
	}

	@Override
	public Set<V> getVertices() {
		// TODO Auto-generated method stub
		return graph.keySet();
	}

	@Override
	public Set<Edge<V>> getEdges() {
		Set<Edge<V>> ret = new HashSet<Edge<V>>();
		ret.addAll(edges);
		return ret;
	}

	@Override
	public Set<V> getAdjacentVertices(V vertex) {
		HashSet<V> ret = new HashSet<V>();
		for(Edge<V> e: graph.get(vertex)) {
			ret.add(e.getOpposingVertex(vertex));
		}
 		return ret;
	}

	@Override
	public Set<Edge<V>> getConnectingEdges(V vertex) {
		return graph.get(vertex);
	}

	@Override
	public void addEdge(V vertex1, V vertex2) {
		if(checkVertices(vertex1,vertex2)){
			addEdge(new SimpleEdge<V>(vertex1,vertex2));
		}
	}

	// checks if an edge or it's reciprocal exits.
	private boolean edgeExist(Edge<V> edge) {
		Edge<V> reciprical = new SimpleEdge<V>(edge.getVertices().get(1),edge.getVertices().get(0));
		return edges.contains(edge) || edges.contains(reciprical);
	}
	
	private boolean checkVertices(Edge<V> edge) {
		return checkVertices(edge.getVertices().get(0),edge.getVertices().get(1));
	}
	private boolean checkVertices(V v, V v1) {
		return (graph.containsKey(v)&&graph.containsKey(v1));
	}
	private boolean addEdgeToGraph(V vertex, Edge<V> edge) {
		 return graph.get(vertex).add(edge);
	}
	private boolean removeEdgeFromGraph(V vertex, Edge<V> edge) {
		return graph.get(vertex).remove(edge);
	}
	
}
