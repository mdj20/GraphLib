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
		boolean ret = false;
		if(edgeList.size()==0) {
			if(edge.getVertices().contains(source)) {
				if(checkEdge(edge,source)){
					edgeList.add(edge);
					tail = edge.getOpposingVertex(source);
					ret = true;
				}
			}
		}
		else if (edgeList.size() > 0 && tail!= null) {
			if(edge.getVertices().contains(tail)) {
				if (checkEdge(edge,tail)) {
					edgeList.add(edge);
					tail = edge.getOpposingVertex(tail);
					ret = true;
				}
			}
		}
		return ret;
	}
	
	protected boolean checkEdge(E edge, V edgeSource) {
		boolean ret = false;
		Set<E> edges = graph.getOutgoingEdges(edgeSource);
		if(edges.contains(edge)){
			ret = true;
		}
		else {
			ret = recipricalExists(edge,edgeSource);
		}
		return ret;
	}
	
	protected boolean recipricalExists(E edge, V edgeSource) {
		boolean ret = false;
		Set<E> edges = graph.getConnectingEdges(edgeSource);
		for(E e:edges) {
			if(e.isReciprical(edge)) 
			ret=true;
			break;
		}
		return ret;
	}
	
	public static <G extends Graph<V,E>,V,E extends Edge<V>> PathBuilder<G,V,E> getPathBuilder(G graph, V source){
		return new PathBuilder<G,V,E>(graph,source);
	}
	
	//public static <> getWeightedPathBuilder
	
	
}
