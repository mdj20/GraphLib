package traverse;
import java.util.List;

import interfaces.Edge;
import interfaces.Graph;
import interfaces.WeightedEdge;
import interfaces.WeightedGraph;
import traverse.algorithm.DepthFirst;

public abstract class AbstractPathFinder<G extends Graph<V,E>,V,E extends Edge<V>> {
	
	protected G graph;

	protected AbstractPathFinder(G graph) {
		this.graph = graph;
	}
	
	public List<E> depthFirstPath(V source, V sink){
		return DepthFirst.depthFirstSearch(graph, source, sink);
	}
	
	public G getGraph() {
		return graph;
	}
	
}
