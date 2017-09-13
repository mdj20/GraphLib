package main;
import java.util.Random;

import interfaces.Edge;

public class SimpleAdjacencyListGraphTest {
	
	int max = 15;
	Random rando = new Random(System.currentTimeMillis());
	
	SimpleAdjacencyListGraph<Character> graph;
	
	SimpleAdjacencyListGraphTest(){
		graph = new SimpleAdjacencyListGraph<Character>();
	}
	
	
	public void smoke() {
		for (int i = 0 ; i<max;i++) {
			graph.addVertex(tc(i));
		}
		for (char c: graph.getVertices()) {
			System.out.println(c);
		}
		
		graph.addEdge(tc(0),tc(1));
		graph.addEdge(tc(0),tc(1));
		
		
		randomEdges(25);
		for(Edge<Character> e: graph.getEdges()) {
			System.out.println(e.getVertices().get(0)+" "+e.getVertices().get(1));
		}
		System.out.println(graph.getEdges().size());
	}
	
	public static void main(String args[]) {
		SimpleAdjacencyListGraphTest test = new SimpleAdjacencyListGraphTest();
		test.smoke();
	}
	
	private static char tc(int i) {
		return (char) (i+65);
	}

	
	private void randomEdges(int n) {
		int s,e;
		for (int i=0;i<n;i++) {
			s = rando.nextInt(max);
			e = rando.nextInt(max);
			graph.addEdge(tc(s), tc(e));
		}
	}
	
	
	
}
