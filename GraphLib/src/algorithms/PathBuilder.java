package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import interfaces.Edge;
import interfaces.Graph;
import interfaces.WeightedEdge;
import interfaces.WeightedGraph;
import main.WeightedAdjacencyListDiGraph;
import testutilities.FastGraphBuilder;
import testutilities.TestGraphData;

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
		Set<E> outEdges = graph.getOutgoingEdges(edgeSource);
		if(outEdges.contains(edge)){
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
			if (e.isReciprical(edge)) 
				ret=true;
				break;
		}
		return ret;
	} 
	
	protected boolean checkPath() {
		boolean ret = true;
		V current = source, next= null;
		for(int i = 0 ; i < edgeList.size() ; i++ ) {
			next = edgeList.get(i).getOpposingVertex(current);
			if(next==null) {
				ret = false;
				break;
			} 
			else {
				current = next;
			}
		}
		return ret;
	}
	
	public Path<V,E> build(){
		if(checkPath()) {
			path = new SimplePath<V,E>(source,tail,edgeList);
		}
		return path; 
	}
	
	
	
	public static <G extends Graph<V,E>,V,E extends Edge<V>> PathBuilder<G,V,E> getPathBuilder(G graph, V source){
		return new PathBuilder<G,V,E>(graph,source);
	}

	// Smoke test
	public static void main() {
		WeightedAdjacencyListDiGraph<Character,Integer> graph = FastGraphBuilder.getWeightedDiGraph(TestGraphData.TestGraph0);
		Dikstras.findShortestPathInt(graph, "A", "C");
		
		
	}
	
	
}
