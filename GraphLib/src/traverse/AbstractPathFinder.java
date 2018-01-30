package traverse;
import java.util.List;

import interfaces.Edge;
import interfaces.Graph;
import interfaces.WeightedEdge;
import interfaces.WeightedGraph;

public abstract class AbstractPathFinder<G extends Graph<V,E>,V,E extends Edge<V>> {
	
	protected G graph;

	protected AbstractPathFinder(G graph) {
		this.graph = graph;
	}
	
	
	public G getGraph() {
		return graph;
	}
	
	//public static Ab
	
}
