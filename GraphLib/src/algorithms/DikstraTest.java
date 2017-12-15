package algorithms;

import java.util.Collection;
import java.util.Random;
import java.util.Set;

import interfaces.WeightedGraph;
import main.WeightedAdjacencyListDiGraph;
import main.WeightedAdjacencyListGraph;
import test.FastGraphBuilder;

public class DikstraTest {

	WeightedAdjacencyListDiGraph<Character,Integer> graph; 
	
	
	public static void main(String args[]){
		for(int i = 0 ; i < 25000000; i++) {
			System.out.print(i+": ");
			smoke();
			System.out.println();
		}
	}

	private static void smoke() {
		weightedTest();
	}
	
	private static void weightedTest() {
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.buildRandomWeightedGraph(10, 15, 1, 10);
		Set<Character> verts = graph.getVertices(); 
		Character[] vertArray = verts.toArray(new Character[verts.size()]);
		Character source = null, sink= null;
		Random rando = new Random(System.currentTimeMillis());
		int sourceIndex = rando.nextInt(verts.size());
		int sinkIndex = sourceIndex;
		while(sourceIndex == sinkIndex) {
			sinkIndex = rando.nextInt(verts.size());
		}
		source = vertArray[sourceIndex];
		sink = vertArray[sinkIndex];
		Collection<Character> path = Dikstras.findShortestPath(graph, source, sink);
		for(Character c: path ) {
			System.out.print(c+" ");
		}
	}
	
	/* this needs to 
	public WeightedAdjacencyListDiGraph<Character,Integer> buildGraph(int verts[], int source[], int sink[], int weights[]){
		WeightedAdjacencyListDiGraph<Character,Integer> build = new WeightedAdjacencyListDiGraph<Character,Integer>();
		if(source.length == sink.length && source.length == weights.length){
			for (int i = 0 ;i < 7 ; i++){
				build.addVertex(tc(i));
			}
			for(int i = 0 ; i < source.length ; i++){
				build.addEdge(tc(source[i]), tc(sink[i]), weights[i]);
			}
		}
		else {
			throw new IllegalArgumentException("edges and weights must be equal length");
		}
	
		
	}
	public char tc(int i){
		return (char) (i+65);
	}
	*/
	
}
