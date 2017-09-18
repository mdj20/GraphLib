package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import interfaces.WeightedDirectionalEdge;
import main.WeightedAdjacencyListDiGraph;

/*
 * this class is a testing builder of graphs 
 */ 

public class BuildWeightedDiGraph {

	public final static Character VERTEX_CHARS[]  = {'A','B','C','D','E','F','G','H'};
	public final static Character SOURCE[]  = {'A','A','A','B','C','D','D','E','F','G','H','H'};
	public final static Character SINK[] =  {'B','E','D','E','E','H','G','F','C','H','E','F'};
	public final static Integer WEIGHTS[] = { 4,  7,  2,  2,  4,  4,  1,  2,  1,  2,  5,  1 };
	
	public static WeightedAdjacencyListDiGraph<Character,Integer> getWeightedDiGraph(){
	

		return getWeightedDiGraph(VERTEX_CHARS,SOURCE,SINK,WEIGHTS);
	}

	public static <V,W extends Number & Comparable<W>> WeightedAdjacencyListDiGraph<V,W> getWeightedDiGraph(V vertex[], V edgeS[], V edgeE[], W weights[]){
		if(edgeS.length != edgeS.length && edgeS.length != weights.length) {
			throw new IllegalArgumentException("Lengths of edge arrays, must equal");
		}
		WeightedAdjacencyListDiGraph<V,W> graph = new WeightedAdjacencyListDiGraph<>();
		for(V c: vertex) {
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
	
	// int to char convenience method
	public static char tc(int i) {
		return (char) (i+65);
	}
	

	public static void main(String args[]) {
		//Character chars[] = Arrays.stream
		ArrayList<Double> doubles = new ArrayList<>();
		for(int i = 0 ; i< WEIGHTS.length;i++) {
			doubles.add(new Double(WEIGHTS[i]));
		}
		WeightedAdjacencyListDiGraph<Character,Double> graph2 =  getWeightedDiGraph(VERTEX_CHARS,SOURCE,SINK,doubles.toArray(new Double[doubles.size()]));
		WeightedAdjacencyListDiGraph<Character,Integer> graph = buildRandomGraph(7,14,1,6);
		for(char c: graph2.getVertices()) {
			System.out.println(c+":");
			for(WeightedDirectionalEdge<Character,Double> de: graph2.getOutgoingEdges(c)){
				System.out.println(de.getSource()+" "+de.getSink()+" "+de.getWeight());
			}
			System.out.println();
		}
	}
	
}
