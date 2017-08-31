import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Interfaces.Edge;
import Interfaces.Graph;

public abstract class AbstractAdjacencyListGraph<V,E extends Edge<V>> implements Graph<V,E>{
	protected HashMap<V, HashSet<E>> graph;
	protected HashSet<E> edges;
	
	AbstractAdjacencyListGraph(){
		graph = new HashMap<V,HashSet<E>>();
		edges= new HashSet<E>();
	}

	@Override
	public void addVertex(V vertex) {
		if(!graph.containsKey(vertex))
			graph.put(vertex, new HashSet<E>());
	}

	@Override
	public void addEdge(E edge) {
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
	public void removeEdge(E edge) {
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
	public Set<E> getEdges() {
		Set<E> ret = new HashSet<E>();
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
	public Set<E> getConnectingEdges(V vertex) {
		return graph.get(vertex);
	}

	@Override
	public void addEdge(V vertex1, V vertex2) {
		if(checkVertices(vertex1,vertex2)){
			addEdge(createEdge(vertex1,vertex2));
		}
	}

	public abstract E createEdge(V vertex1, V vertex2);

	// checks if an edge or it's reciprocal exits.
	private boolean edgeExist(E edge) {
		Edge<V> reciprical = new SimpleEdge<V>(edge.getVertices().get(1),edge.getVertices().get(0));
		return edges.contains(edge) || edges.contains(reciprical);
	}
	
	protected boolean checkVertices(E edge) {
		return checkVertices(edge.getVertices().get(0),edge.getVertices().get(1));
	}
	protected boolean checkVertices(V v, V v1) {
		return (graph.containsKey(v)&&graph.containsKey(v1));
	}
	protected boolean addEdgeToGraph(V vertex, E edge) {
		 return graph.get(vertex).add(edge);
	}
	protected boolean removeEdgeFromGraph(V vertex, E edge) {
		return graph.get(vertex).remove(edge);
	}
	
}
