package algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import interfaces.DiGraph;
import interfaces.DirectionalEdge;
import interfaces.Edge;
import interfaces.Graph;
import main.SimpleAdjacencyListGraph;
import main.WeightedAdjacencyListDiGraph;
import test.FastGraphBuilder;
import test.UnivAvengersGraphData;

public class DepthFirstSearch {
	/*
	public static boolean hasCycle(DiGraph graph, V source) {
		return false;
	}
	*/
	
	public static <V, E extends Edge<V>> List<V> DFS(Graph<V,E> graph, V source, V sink) {
		ArrayList<V> ret = new ArrayList<>();
		Deque<V> stack = new ArrayDeque<V>();
		HashSet<V> set = new HashSet<V>();
		push(source,stack,set);
		if(dfsRec(graph,stack,set,sink)) {
			ret.addAll(stack);
			Collections.reverse(ret);
		}
		return ret;
	}
	
	private static <V, E extends Edge<V>> boolean dfsRec(Graph<V,E> graph, Deque<V> stack, Set<V> set, V sink) {
		boolean ret = false;	
		if(!sink.equals(stack.peek())) {

			for(V v: graph.getOutgoingVertices(stack.peek())) {

				if(!set.contains(v)) {
					push(v,stack,set);
					if (dfsRec(graph,stack,set,sink)) {
						ret = true;
						break;  // break from for-all
					}
					else {
						pop(stack,set);
					}
				}
			}
		}
		else {

			ret =true;
		}
		return ret;
	}
	
	public static void main(String args[]) {

		List<Edge<Integer>> edgeList = UnivAvengersGraphData.getEdgeList(UnivAvengersGraphData.dsjc5001col);
		SimpleAdjacencyListGraph<Integer> graph = new SimpleAdjacencyListGraph<Integer>();
		System.out.println("edgelist Size "+edgeList.size());
		Integer high = 111 , low = 106;
		for(Edge<Integer> e:edgeList) {
			for(Integer i:e.getVertices()) {
				if(!graph.getVertices().contains(i)){
					graph.addVertex(i);
				}
			}
			graph.addEdge(e.getVertex(0), e.getVertex(1));
		}
		ArrayList<Integer> verts = new ArrayList<Integer>(graph.getVertices());
		
		Random rando = new Random(System.nanoTime());
		System.out.println("LOW"+low+" "+high);
		//List<Integer> path = DFS(graph,low,high);
		ArrayList<Integer> pathSize = new ArrayList<>();

	}
	
	private static <V> void push(V value, Deque<V> stack, Set<V> set) {
		stack.push(value);
		set.add(value);
	}
	private static <V> V pop( Deque<V> stack, Set<V> set) {
		set.remove(stack.peek());
		return stack.pop();
	}

}
