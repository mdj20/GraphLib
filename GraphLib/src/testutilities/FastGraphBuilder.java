package testutilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;

import interfaces.WeightedDirectionalEdge;
import interfaces.WeightedEdge;
import interfaces.WeightedGraph;
import main.WeightedAdjacencyListDiGraph;
import main.WeightedAdjacencyListGraph;

/*
 * testing class, graph builder. 
 */ 

public class FastGraphBuilder {

	
	public static WeightedAdjacencyListDiGraph<Character,Integer> getWeightedDiGraph(){
		return getWeightedDiGraph(TestGraphData.TestGraph0);
	}
	public static WeightedAdjacencyListGraph<Character,Integer> getWeightedGraph(){
		return getWeightedGraph(TestGraphData.TestGraph0);
	}
	public static WeightedAdjacencyListDiGraph<Character,Integer> getWeightedDiGraph(TestGraphData testGraphData){
		return buildWeightedDiGraph(testGraphData.getVerticies(),testGraphData.getSource(),testGraphData.getSink(),testGraphData.getWeights());
	}
	public static WeightedAdjacencyListGraph<Character,Integer> getWeightedGraph(TestGraphData testGraphData){
		return buildWeightedGraph(testGraphData.getVerticies(),testGraphData.getSource(),testGraphData.getSink(),testGraphData.getWeights());
	}

	

	public static <V,W extends Number & Comparable<W>> WeightedAdjacencyListDiGraph<V,W> buildWeightedDiGraph(V vertex[], V edgeS[], V edgeE[], W weights[]){
		if(edgeS.length != edgeS.length && edgeS.length != weights.length) {
			throw new IllegalArgumentException("Lengths of edge and weight arrays, must equal");
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
	
	public static WeightedAdjacencyListGraph<Character,Integer> buildRandomWeightedGraph(int numVetecies, int numEdges,  int lowerBoundWeight, int upperBoundWeight ) {
		int range = 0,offset = 0;
		if (lowerBoundWeight <= upperBoundWeight) {
			range = upperBoundWeight-lowerBoundWeight;
			offset = lowerBoundWeight;
		}
		else if (upperBoundWeight<=lowerBoundWeight) {
			range = lowerBoundWeight-upperBoundWeight;
			offset = upperBoundWeight;
		}
		
		WeightedAdjacencyListGraph<Character,Integer> graph = new WeightedAdjacencyListGraph<Character,Integer>();
		Random rando = new Random(System.nanoTime());
		for(int i = 0 ; i< numVetecies; i++ ) {
			graph.addVertex(tc(i));
		}
		int source, sink, weight;
		for(int i = 0 ; i<numEdges ; i++) {
			source = rando.nextInt(numVetecies);
			sink = rando.nextInt(numVetecies);
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
		
		
		System.out.println("Random Smoke Test");
		graph = buildRandomWeightedGraph(10,15,0,7);
		for(int i =0;i<50;i++) {
			for(Character c : graph.getVertices()) {
				System.out.println("Vertex: "+c);
				for (WeightedEdge<Character,Integer> we:graph.getConnectingEdges(c)){
					System.out.println(we.getVertex(0)+" "+we.getWeight()+" "+we.getVertex(1));
				}
			
			}
		}
		
	}
	
}
