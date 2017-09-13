package test;

import java.util.Random;

import interfaces.WeightedDirectionalEdge;
import main.WeightedAdjacencyListDiGraph;

/*
 * this class is a testing builder of graphs 
 */

public class BuildWeightedDiGraph {
	
	static char chars[]  = {'A','B','C','D','E','F','G','H'};
	
	static char edgeS[]  = {'A','A','A','B','C','D','D','E','F','G','H','H'};
	static char edgeE[] =  {'B','E','D','E','E','H','G','F','C','H','E','F'};
	static int weights[] = { 4,  7,  2,  2,  4,  4,  1,  2,  1,  2,  5,  1 };
	public static WeightedAdjacencyListDiGraph<Character,Integer> getWeightedDiGraph(){
		return getWeightedDiGraph(chars,edgeS,edgeE,weights);
	}

	public static WeightedAdjacencyListDiGraph<Character,Integer> getWeightedDiGraph(char vertex[], char edgeS[], char edgeE[], int weights[]){
		if(edgeS.length != edgeS.length && edgeS.length != weights.length) {
			throw new IllegalArgumentException("Lengths of edge arrays, must equal");
		}
		WeightedAdjacencyListDiGraph<Character,Integer> graph = new WeightedAdjacencyListDiGraph<>();
		for(char c: vertex) {
			graph.addVertex(c);
		}
		for(int i = 0 ; i < weights.length ; i++) {
			graph.addEdge(edgeS[i], edgeE[i], weights[i]);
		}

		return graph;
	}
	
	public static WeightedAdjacencyListDiGraph<Character,Integer> buildRandomGraph(int v, int e, int lowerBoundWeight, int upperBoundWeight ){
		int range = 0,offset = 0;
		if (lowerBoundWeight <= upperBoundWeight) {
			range = upperBoundWeight-lowerBoundWeight;
			offset = lowerBoundWeight;
		}
		else if (upperBoundWeight<=lowerBoundWeight) {
			range = lowerBoundWeight-upperBoundWeight;
			offset = upperBoundWeight;
		}
		
		WeightedAdjacencyListDiGraph<Character,Integer> graph = new WeightedAdjacencyListDiGraph<Character,Integer>();
		Random rando = new Random(System.nanoTime());
		for(int i = 0 ; i< v; i++ ) {
			graph.addVertex(tc(i));
		}
		int source, sink, weight;
		for(int i = 0 ; i<e ; i++) {
			source = rando.nextInt(v);
			sink = rando.nextInt(v);
			weight = rando.nextInt(range)+offset;
			graph.addEdge(tc(source), tc(sink), weight);
		}
		return graph;
	}
	
	private static char tc(int i) {
		return (char) (i+65);
	}
	
	public static void main(String args[]) {
		WeightedAdjacencyListDiGraph<Character,Integer> graph = buildRandomGraph(7,14,1,6);
		for(char c: graph.getVertices()) {
			System.out.println(c+":");
			for(WeightedDirectionalEdge<Character,Integer> de: graph.getOutgoingEdges(c)){
				System.out.println(de.getSource()+" "+de.getSink()+" "+de.getWeight());
			}
			System.out.println();
		}
	}
	
}
