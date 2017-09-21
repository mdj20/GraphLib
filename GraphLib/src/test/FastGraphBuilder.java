package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import interfaces.WeightedDirectionalEdge;
import interfaces.WeightedEdge;
import interfaces.WeightedGraph;
import main.WeightedAdjacencyListDiGraph;
import main.WeightedAdjacencyListGraph;

/*
 * this class is a testing builder of graphs 
 */ 

public class FastGraphBuilder {

	public final static Character VERTEX_CHARS[]  = {'A','B','C','D','E','F','G','H'};
	public final static Character SOURCE[]  = {'A','A','A','B','C','D','D','E','F','G','H','H'};
	public final static Character SINK[] =  {'B','E','D','E','E','H','G','F','C','H','E','F'};
	public final static Integer WEIGHTS[] = { 4,  7,  2,  2,  4,  4,  1,  2,  1,  2,  5,  1 };
	
	public static WeightedAdjacencyListDiGraph<Character,Integer> getWeightedDiGraph(){
		return buildWeightedDiGraph(VERTEX_CHARS,SOURCE,SINK,WEIGHTS);
	}
	public static WeightedAdjacencyListGraph<Character,Integer> getWeightedGraph(){
		return buildWeightedGraph(VERTEX_CHARS,SOURCE,SINK,WEIGHTS);
	}

	public static <V,W extends Number & Comparable<W>> WeightedAdjacencyListDiGraph<V,W> buildWeightedDiGraph(V vertex[], V edgeS[], V edgeE[], W weights[]){
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
	
	public static WeightedAdjacencyListDiGraph<Character,Integer> buildRandomDiGraph(int v, int e, int lowerBoundWeight, int upperBoundWeight ){
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
	
	public static <V, W> WeightedAdjacencyListGraph<V,W> buildWeightedGraph(V vert[], V source[], V sink[], W weight[]){
		WeightedAdjacencyListGraph<V,W> graph = new WeightedAdjacencyListGraph<V,W>();
		for(V v:vert) {
			graph.addVertex(v);
		}
		for(int i=0;i<source.length;i++) {
			graph.addEdge(source[i], sink[i], weight[i]);
		}
		return graph;
	}
	
	// int to char convenience method
	public static char tc(int i) {
		if (i > 25){
			throw new IllegalArgumentException("i must be between 0 and 25");
		}
		return (char) (i+65);
	}
	public static int[] fillArrayRandom(int array[], int upperBound, int offset){
		Random rando = new Random(System.nanoTime());
		for(int i = 0 ; i < array.length; i++){
			array[i] = rando.nextInt(upperBound)+offset;
		}
		return array;
	}
	public static char[] fillArray(char array[]) {
		for (int i =0; i<array.length ; i++) {
			array[i] = tc(i);
		}
		return array;
	}
	public static int[] fillArray(int array[], int offset) {
		for(int i = 0; i< array.length; i++) {
			array[i] = i+offset;
		}
		return array;
	}
	
	

	
	// smoke test main
	public static void main(String args[]) {
		char chars[] = new char[10];
		WeightedAdjacencyListGraph<Character,Integer> graph = getWeightedGraph();
		for(Character c : graph.getVertices()) {
			System.out.println(c);
		}
		for(WeightedEdge<Character,Integer> c : graph.getEdges()) {
			System.out.println(c.getVertex(0)+" "+c.getWeight()+" "+c.getVertex(1));
		}
	}
	
}
