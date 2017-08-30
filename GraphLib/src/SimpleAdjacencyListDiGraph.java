import java.util.HashSet;
import java.util.Set;

import Interfaces.DiGraph;
import Interfaces.DirectionalEdge;
import Interfaces.Edge;
import Interfaces.Graph;

public class SimpleAdjacencyListDiGraph<V> extends AbstractAdjacencyListGraph<V,DiEdge<V>> implements DiGraph<V,DiEdge<V>> {

	@Override
	public void addEdge(DiEdge<V> edge) {
		if (checkVertices(edge)) {
			addEdgeToGraph(edge.getSource(),edge);
			edges.add(edge);
		}
	}

	@Override
	public void removeEdge(DiEdge<V> edge) {
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
		return null;
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
		return null;
	}

	@Override
	public HashSet<DiEdge<V>> getOutgoingEdges(V vertex) {
		if(graph.containsKey(vertex)) {
			return graph.get(vertex);
		}
		else 
			return null;
	}

	@Override
	public Set<DiEdge<V>> getIncomingEdges(V vertex) {
		HashSet<DiEdge<V>> ret = new HashSet<DiEdge<V>>();
		if(graph.containsKey(vertex)) {
			for(DiEdge<V> d: edges){
				if(vertex.equals(d.getSink())) {
					ret.add(d);
				}
			}
		}
		return ret;
	}

	@Override
	public DiEdge<V> createEdge(V vertex1, V vertex2) {
		return new DiEdge<V>(vertex1,vertex2);
	}
}
