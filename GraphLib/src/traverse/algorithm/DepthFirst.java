package traverse.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import interfaces.Edge;
import interfaces.Graph;

public class DepthFirst {

	public static <V,E extends Edge<V>> List<E> depthFirstSearch(Graph<V,E> graph, V source, V sink){
		List<E> ret = new ArrayList<E>();
		if(graph.getVertices().contains(source)&&graph.getVertices().contains(sink)) {
		LinkedList<SimplePathChain<V,E,?>> pathChain = new LinkedList<SimplePathChain<V,E,?>>();
		
		
		}
		return ret;
	}
	
	private static <V,E extends Edge<V>> boolean 
	DepthfirstRecursive(Graph<V,E> graph, V source, V sink, LinkedList<SimplePathChain<V,E,?>> pathChain, HashSet<V> checked ) {
		boolean ret = false;
		if(pathChain.peekLast()!=null) {
			V current =  pathChain.peekLast().vertex;
			checked.add(current);
			Set<E> nextEdges = graph.getOutgoingEdges(current);
			for(E e : nextEdges) {
				if(!checked.contains(e.getOpposingVertex(current))) {
					if(e.getOpposingVertex(current).equals(sink)) {
						ret = true; 
						pathChain.addLast(new SimplePathChain<V,E,Integer>(e.getOpposingVertex(current),e,null));
						break;
					}
					else {
						pathChain.addLast(new SimplePathChain<V,E,Integer>(e.getOpposingVertex(current),e,null));
						if(DepthfirstRecursive(graph,source,sink,pathChain,checked)) {
							ret = true;
							break;
						}
						else {
							pathChain.pollLast();
						}
					}
				}
			}
			checked.remove(current);
		}
		return ret;	
	}
}
