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
	/*	WeightedAdjacencyListDiGraph<Character,Integer> graph = FastGraphBuilder.getWeightedDiGraph();
		for(Character c: DFS(graph,'A','F')) {
			System.out.println(c);
		}
		for(int i = 0 ; i<50 ; i++) {
			int j = i%3 ,k = i%7;
			System.out.println(i+": "+FastGraphBuilder.tc(j)+" to "+FastGraphBuilder.tc(k));
			graph = FastGraphBuilder.buildRandomDiGraph(7, 10, 0, 6);
			for(Character c: DFS(graph,FastGraphBuilder.tc(j),FastGraphBuilder.tc(k))) {
				System.out.println(c);
			}
			System.out.println();
		}
		
		*/
		List<Edge<Integer>> edgeList = UnivAvengersGraphData.getEdgeList(UnivAvengersGraphData.dsjc5001col);
		SimpleAdjacencyListGraph<Integer> graph = new SimpleAdjacencyListGraph<Integer>();
		
		Integer high = null , low = null;
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
		ArrayList<Integer> pathSize = new ArrayList<>();
		for(int i =0;i<500;i++) {
			low = verts.get(rando.nextInt(verts.size()));
			high = verts.get(rando.nextInt(verts.size()));
			List<Integer> path = DFS(graph,low,high);
			for(Integer c: path) {
				//System.out.println(c);
			}
			System.out.println(i+":");
			pathSize.add(path.size());
			System.gc();
		}
		long sum = 0;
		for(Integer i:pathSize) {
			sum+=i;
		}
		System.out.println("AVG Path: "+(sum/pathSize.size()));
		
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
