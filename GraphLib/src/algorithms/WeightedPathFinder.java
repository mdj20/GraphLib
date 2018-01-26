package algorithms;

import java.util.List;

import interfaces.WeightedEdge;
import interfaces.WeightedGraph;

public class WeightedPathFinder<G extends WeightedGraph<V,E,W>, E extends WeightedEdge<V,W>, V, W extends Number & Comparable<W>>
extends AbstractPathFinder<G, V, E> {
	
	protected WeightedPathFinder(G graph) {
		super(graph);
	}
	
	// factory method infers type parameters, and returns appropriate object
	public static <G extends WeightedGraph<V,E,W>, E extends WeightedEdge<V,W>, V, W extends Number & Comparable<W>> WeightedPathFinder<G, E, V, W> getWeightedPathFinder(G graph){
		return new WeightedPathFinder<G,E,V,W>(graph);
	}
	
	
	// bellmanford int 
	
	// bellmanford double
	
	// dikstras int 
	public WeightedPath<V,W> dikstrasIntPath(V source, V sink){
		WeightedPath<V,W> ret = null;
		List<E> edgeList = DikstrasPathChain.findShortestPathInt(super.getGraph(), source, sink);
		WeightedPathBuilder<G,V,E,W> weightedPathBuilder = new WeightedPathBuilder<G,V,E,W>(graph,source);
		if(edgeList!=null) {
			for(E e: edgeList) {
				weightedPathBuilder.addEdge(e);
			}
		}
		ret = weightedPathBuilder.build();
		return ret;
	}
	
	// dikstras double
}
