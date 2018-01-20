package algorithms;

import interfaces.Edge;
import interfaces.Graph;
import interfaces.WeightedGraph;

	/*
	 * Constructs Path Objects, with verification 
	 */

public class PathBuilder<G extends Graph<V,E>,V,E extends Edge<V>> {
	
	protected G graph;
	Path<V,E> path;

	public PathBuilder(G graph){
		
	}
	
	
	//public addEdge(E edge)
	
	
	public static <G extends Graph<V,E>,V,E extends Edge<V>> PathBuilder<G,V,E> getPathBuilder(G graph){
		return new PathBuilder<G,V,E>(graph);
	}
	
	//public static <> getWeightedPathBuilder
	
	
}
