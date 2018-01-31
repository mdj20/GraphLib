package traverse;
import java.util.List;

import interfaces.Edge;
import interfaces.Graph;
import interfaces.WeightedEdge;
import interfaces.WeightedGraph;
import traverse.algorithm.BreadthFirst;
import traverse.algorithm.DepthFirst;

public abstract class AbstractPathFinder<G extends Graph<V,E>,V,E extends Edge<V>> {
	protected G graph;

	protected AbstractPathFinder(G graph) {
		if(graph == null) {
			throw new IllegalArgumentException("Argument graph cannot be null");
		}
		this.graph = graph;
	}
	
	public List<E> depthFirstEdgeList(V source, V sink){
		return DepthFirst.depthFirstSearch(graph, source, sink);
	}
	
	public List<E> breadthFirstEdgeList(V source, V sink){
		return BreadthFirst.breadthFirstSearch(graph, source, sink);
	}
	
	public Path<V,E> depthFirstPath(V source, V sink){
		Path<V,E> ret = null;
		PathBuilder<G,V,E> pBuilder = new PathBuilder<G,V,E>(graph,source);
		List<E> edgeList =  depthFirstEdgeList(source,sink);
		if(edgeList != null) {
			pBuilder.addEdges(edgeList);
			ret = pBuilder.build();
		}
		return ret;
	}
	
	public Path<V,E> breadthFirstPath(V source, V sink){
		Path<V,E> ret = null;
		PathBuilder<G,V,E> pBuilder = new PathBuilder<G,V,E>(graph,source);
		List<E> edgeList =  breadthFirstEdgeList(source,sink);
		if(edgeList != null) {
			pBuilder.addEdges(edgeList);
			ret = pBuilder.build();
		}
		return ret;
	}
	
	public G getGraph() {
		return graph;
	}
	
}
