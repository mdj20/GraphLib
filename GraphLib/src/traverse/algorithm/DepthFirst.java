package traverse.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import interfaces.Edge;
import interfaces.Graph;
import interfaces.WeightedGraph;
import main.WeightedAdjacencyListDiGraph;
import testutilities.FastGraphBuilder;
import testutilities.TestGraphData;

public class DepthFirst {

	public static <V,E extends Edge<V>> List<E> depthFirstSearch(Graph<V,E> graph, V source, V sink){
		List<E> ret = new ArrayList<E>();
		if(graph.getVertices().contains(source)&&graph.getVertices().contains(sink)) {
		LinkedList<SimplePathChain<V,E,?>> pathChain = new LinkedList<SimplePathChain<V,E,?>>();
		HashSet<V> checked = new HashSet<V>();
		pathChain.add(new SimplePathChain<V,E,Integer>(source,null,null));
		if(DepthFirstRecursive(graph,source,sink,pathChain,checked));
			System.out.println("Success!");
		
		}
		return ret;
	}
	
	
	private static <V,E extends Edge<V>> boolean 
	DepthFirstRecursive(Graph<V,E> graph, V source, V sink, LinkedList<SimplePathChain<V,E,?>> pathChain, HashSet<V> checked ) {
		boolean ret = false;
		if(pathChain.peekLast()!=null) {
			V current =  pathChain.peekLast().vertex;
			checked.add(current);
			Set<E> nextEdges = graph.getOutgoingEdges(current);
			for(E e : nextEdges) {
				if(!checked.contains(e.getOpposingVertex(current))) {
					if(e.getOpposingVertex(current).equals(sink)) {
						ret = true; 
						pathChain.addLast(new SimplePathChain<V,E,Integer>(e.getOpposingVertex(current),e,null)); // Note integer isn't used for anything in this context
						break;
					}
					else {
						pathChain.addLast(new SimplePathChain<V,E,Integer>(e.getOpposingVertex(current),e,null));
						if(DepthFirstRecursive(graph,source,sink,pathChain,checked)) {
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
	
	
	private static void main(String args[]){
		// smoke test
		WeightedAdjacencyListDiGraph<Character,Integer> graph = FastGraphBuilder.getWeightedDiGraph(TestGraphData.TestGraph0);
		depthFirstSearch(graph,'A','C');
		
	}
}
