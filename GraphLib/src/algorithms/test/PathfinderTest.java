package algorithms.test;

import java.util.List;
import java.util.Random;

import algorithms.PathFinder;
import interfaces.WeightedEdge;
import main.WeightedAdjacencyListGraph;
import testutilities.FastGraphBuilder;

public class PathfinderTest {

	public static void main(String args[]) {

		cycle(10000);
		
		
		
	}
	
	
	public static void cycle(int n) {
		WeightedAdjacencyListGraph<Character,Integer> graph;
		List<WeightedEdge<Character,Integer>> path  ;
		Random rando = new Random(System.nanoTime());
		int v, e;
		for (int i = 0 ; i<n ; i++) {
		
			v = rando.nextInt(20)+1;
			e = (int) (v * ((rando.nextDouble())+0.5));
			System.out.println("Cycle# "+i+" "+v+" "+e);
			graph = FastGraphBuilder.buildRandomWeightedGraph(v, e, 1, 10);
			path = PathFinder.lazyDikstras(graph, FastGraphBuilder.tc(rando.nextInt(v)),FastGraphBuilder.tc(rando.nextInt(v)));
			printPath(path);
		}
	}
	
	
	public static <V, W> void printPath(List<WeightedEdge<V,W>> path) {
		for(WeightedEdge<V,W> edge:path) {
			printWeightedEdgeln(edge);
		}
		System.out.println();
		}
	
	public static <V, W> void  printWeightedEdgeln(WeightedEdge<V,W> edge) {
		System.out.println(edge.getVertex(0)+" "+edge.getWeight()+" "+edge.getVertex(1));
	}
	
	
}
