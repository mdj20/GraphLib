package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import interfaces.Edge;
import interfaces.Graph;
import interfaces.WeightedGraph;

	/*
	 * Constructs Path Objects, with verification 
	 */

public class PathBuilder<G extends Graph<V,E>,V,E extends Edge<V>> {
	
	protected List<E> edgeList;
	protected G graph;
	protected Path<V,E> path;
	protected V source;
	protected V tail;
	
	public PathBuilder(G graph, V source){
		this.graph=graph;
		this.source= source;
		this.edgeList = new ArrayList<E>();
	}
	
	
	public boolean addEdge(E edge) {
		if(edgeList.size()==0) {
			if(edge.getVertices().contains(source)) {
				
			}
		}
	}
	
	//protected
	
	protected boolean checkEdge(E edge, V edgeSource) {
		boolean ret = false;
		Set<E> edges = graph.getOutgoingEdges(edgeSource);
		if(edges.contains(edge)){
			ret = true;
		}
		else {
			for(E e:edges) {
				if(e.isReciprical(edge)) {
					ret = true;
					break;
				}
			}
		}
		return ret;
	}
	
	public static <G extends Graph<V,E>,V,E extends Edge<V>> PathBuilder<G,V,E> getPathBuilder(G graph){
		return new PathBuilder<G,V,E>(graph);
	}
	
	//public static <> getWeightedPathBuilder
	
	
}
