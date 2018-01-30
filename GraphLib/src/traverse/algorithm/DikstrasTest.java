package traverse.algorithm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import interfaces.WeightedDirectionalEdge;
import main.WeightedAdjacencyListDiGraph;
import testutilities.FastGraphBuilder;

public class DikstrasTest {

	
	// creates a series of randomly generated graphs and tests the results of Dijkstra's methods against BelmanFord's 
	
	@Test
	public void testFindShortestPathInt() {
		
		int nTestIterations = 10000;
		int nVerts = 25; 
		int nEdges = 50;
		int lowerBoundWeight = 1;
		int upperBoundWeight = 25;
		Integer sourceIndex = null, sinkIndex = null;
		Random rando = new Random(System.nanoTime());
		for(int i = 0 ; i < nTestIterations ; i ++ ) {
			
		
			WeightedAdjacencyListDiGraph<Character,Integer> graph = FastGraphBuilder.buildRandomDiGraph(nVerts, nEdges, lowerBoundWeight, upperBoundWeight);
			ArrayList<Character> vertices = new ArrayList<Character>(graph.getVertices());
			sourceIndex = rando.nextInt(vertices.size());
			sinkIndex = rando.nextInt(vertices.size());
			Character source = null, sink = null;
			while(sourceIndex.equals(sinkIndex)) {
				sourceIndex = rando.nextInt(vertices.size());
				sinkIndex = rando.nextInt(vertices.size());
			}
			source = vertices.get(sourceIndex);
			sink = vertices.get(sinkIndex);
			List<WeightedDirectionalEdge<Character,Integer>> dEdges = Dikstras.findShortestPathInt(graph, source, sink);
			List<WeightedDirectionalEdge<Character,Integer>> bmEdges = BellmanFord.findShortestPathInt(graph, source, sink);
			boolean exactMatch = checkEdgeListEquality(dEdges,bmEdges);
			if(!exactMatch) {
					exactMatch = checkEdgeListWeightIntTotal(dEdges,bmEdges);
				}
				assertTrue(exactMatch);
			}
	}

	@Test
	public void testFindShortestPathDouble() {
		fail("Not yet implemented");
	}
	
	// tests for List<WeightedDirectedEdge<Character,Integer>> equality returns true if equal
	private boolean checkEdgeListEquality(List<WeightedDirectionalEdge<Character, Integer>> A, List<WeightedDirectionalEdge<Character, Integer>> B) {
		boolean ret = true;
		if(A.size()!=B.size()) {
			return false;
		}
		else {
			for (int i = 0 ; i < A.size() ; i++) {
				if(!A.get(i).equals(B.get(i))) {
					return true;
				}
			}
		}
		return ret;
	}
	
	
	// tests for edgeList integer sum equivalence  returns true if sums are equal.
	private boolean checkEdgeListWeightIntTotal(List<WeightedDirectionalEdge<Character, Integer>> A, List<WeightedDirectionalEdge<Character, Integer>> B) {
		int sumA = 0, sumB = 0;
		for(WeightedDirectionalEdge<Character, Integer> wde: A) {
			sumA += wde.getWeight();
		}
		for(WeightedDirectionalEdge<Character, Integer> wde: B) {
			sumB += wde.getWeight();
		}
		return sumA == sumB;
	}

}
