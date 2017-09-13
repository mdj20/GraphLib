package algorithms;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import interfaces.DiGraph;
import interfaces.DirectionalEdge;

public class DepthFirstSearch {
	
	public static boolean hasCycle(DiGraph graph, Vertex source)
	
	public static <V, E extends DirectionalEdge<V>> boolean DFS(DiGraph<V,E> graph, V source, V sink) {
		Deque<V> stack = new ArrayDeque<V>();
		HashSet<V> set = new HashSet<V>();
		push(source,stack,set);
		if(dfsRec(graph,stack,set,sink)) {
			
		}
	}
	
	private static <V, E extends DirectionalEdge<V>> boolean dfsRec(DiGraph<V,E> graph, Deque<V> stack, Set<V> set, V sink) {
		boolean ret = false;
		if(!sink.equals(stack.peek())) {
			for(V v: graph.getOutgoingVertices(stack.peek())) {
				if(!set.contains(v)) {
					push(v,stack,set);
					if (dfsRec(graph,stack,set,sink)) {
						ret = true;
						break;
					}
					pop(stack,set);
				}
			}
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
