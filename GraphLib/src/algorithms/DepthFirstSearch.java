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
import testutilities.FastGraphBuilder;
import testutilities.UnivAvengersGraphData;

public class DepthFirstSearch {
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
						stack.pop();
					}
				}
			}
		}
		else {
			ret =true;
		}
		return ret;
	}
	
	
	
	// SMOKE TESTING MAIN
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
		List<Integer> path = DFS(graph,low,high);
		ArrayList<Integer> pathSize = new ArrayList<>();
		
		Random rando = new Random(System.nanoTime());
		System.out.println("LOW"+low+" "+high);
		for(int i = 0 ; i< 5000; i++) {
			low = verts.get(rando.nextInt(verts.size()));
			high = verts.get(rando.nextInt(verts.size()));
			path = DFS(graph,low,high);
			pathSize.add(path.size());
			System.out.println("Verts: "+low+" "+high);
			for(int j = 0 ; j<path.size();j++) {
				System.out.print(path.get(j)+" ");
				if(j%30==0&&j>0) {
					System.out.println();
				}
			}
			System.out.println("\n");
		}
		Integer sum=0;
		for (Integer i:pathSize) {
			sum+=i;
		}
		if(sum!=null) {
			System.out.println("AVG SIZE: "+(sum/pathSize.size()));
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
