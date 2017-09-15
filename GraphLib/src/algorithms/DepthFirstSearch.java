package algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import interfaces.DiGraph;
import interfaces.DirectionalEdge;
import main.WeightedAdjacencyListDiGraph;
import test.BuildWeightedDiGraph;

public class DepthFirstSearch {
	/*
	public static boolean hasCycle(DiGraph graph, V source) {
		return false;
	}
	*/
	
	public static <V, E extends DirectionalEdge<V>> List<V> DFS(DiGraph<V,E> graph, V source, V sink) {
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
	
	private static <V, E extends DirectionalEdge<V>> boolean dfsRec(DiGraph<V,E> graph, Deque<V> stack, Set<V> set, V sink) {
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
		WeightedAdjacencyListDiGraph<Character,Integer> graph = BuildWeightedDiGraph.getWeightedDiGraph();
		for(Character c: DFS(graph,'A','F')) {
			System.out.println(c);
		}
		for(int i = 0 ; i<50 ; i++) {
			int j = i%3 ,k = i%7;
			System.out.println(i+": "+BuildWeightedDiGraph.tc(j)+" to "+BuildWeightedDiGraph.tc(k));
			graph = BuildWeightedDiGraph.buildRandomGraph(7, 10, 0, 6);
			for(Character c: DFS(graph,BuildWeightedDiGraph.tc(j),BuildWeightedDiGraph.tc(k))) {
				System.out.println(c);
			}
			System.out.println();
		}
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
