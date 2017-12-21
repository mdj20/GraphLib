package main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.Test;

import interfaces.Edge;
import interfaces.WeightedDirectionalEdge;
import interfaces.WeightedEdge;

public class WeightedAdjacencyListDiGraphTest {

	@Test
	public void testCreateEdgeVV() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddEdgeVVW() {
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = new WeightedAdjacencyListDiGraph<Integer,Integer>();
		testGraph.addVertex(0);  
		testGraph.addVertex(1);
		testGraph.addEdge(0,1,1);
		
		Set<WeightedDirectionalEdge<Integer,Integer>> edgeSet =  testGraph.getEdges();
		Set<WeightedDirectionalEdge<Integer,Integer>> edgeSetAdjacent0 =  testGraph.getConnectingEdges(0);
		Set<WeightedDirectionalEdge<Integer,Integer>> edgeSetAdjacent1 =  testGraph.getConnectingEdges(1);
		ArrayList<WeightedDirectionalEdge<Integer,Integer>> edgeList = new ArrayList<WeightedDirectionalEdge<Integer,Integer>>(testGraph.getEdges());
		assertTrue(edgeSet.size()==1);  // check edges size == 0
		assertTrue(edgeSetAdjacent0.contains(edgeList.get(0)));  // check vertex 0 
		assertTrue(edgeSetAdjacent1.contains(edgeList.get(0))); // check vertex 1
	}

	@Test
	public void testAddEdgeE() {
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = new WeightedAdjacencyListDiGraph<Integer,Integer>();
		testGraph.addVertex(0);  
		testGraph.addVertex(1);
		testGraph.addEdge(new SimpleWeightedDirectionalEdge<Integer,Integer>(0,1,1));
		
		Set<WeightedDirectionalEdge<Integer,Integer>> edgeSet =  testGraph.getEdges();
		Set<WeightedDirectionalEdge<Integer,Integer>> edgeSetAdjacent0 =  testGraph.getConnectingEdges(0);
		Set<WeightedDirectionalEdge<Integer,Integer>> edgeSetAdjacent1 =  testGraph.getConnectingEdges(1);
		ArrayList<WeightedDirectionalEdge<Integer,Integer>> edgeList = new ArrayList<WeightedDirectionalEdge<Integer,Integer>>(testGraph.getEdges());
		assertTrue(edgeSet.size()==1);  // check edges size == 0
		assertTrue(edgeSetAdjacent0.contains(edgeList.get(0)));  // check vertex 0 
		assertTrue(edgeSetAdjacent1.contains(edgeList.get(0))); // check vertex 1
	}

	@Test
	public void testRemoveEdge() {
		WeightedAdjacencyListDiGraph<Integer,Integer> testGraph = createSaturatedGraph(4);
		ArrayList<WeightedDirectionalEdge<Integer,Integer>> edgeList = new ArrayList<WeightedDirectionalEdge<Integer,Integer>>(testGraph.getEdges());
		testGraph.removeEdge(edgeList.get(0));
		assertTrue(!testGraph.getEdges().contains(edgeList.get(0)));
	}

	@Test
	public void testGetOutgoingVertices() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIncomingVertices() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOutgoingEdges() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIncomingEdges() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddVertex() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveVertex() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVertices() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEdges() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAdjacentVertices() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetConnectingEdges() {
		fail("Not yet implemented");
	}

	
	// Utility method that creates a saturated test graph according to a specified number of vertices. 
	// The edge weights are determined by the sum of the vertex values.
	private static WeightedAdjacencyListDiGraph<Integer,Integer> createSaturatedGraph(int nVertices){
		WeightedAdjacencyListDiGraph<Integer,Integer> graph = new WeightedAdjacencyListDiGraph<Integer,Integer>();
		for (int i = 0; i < nVertices ; i++) {
			graph.addVertex(i);
		}
		
		for (int i = 0; i < nVertices ; i++) {
			for (int j = 0; j < nVertices ; j++) {
				if(i!=j) {
					graph.addEdge(i,j,i+j);
				}
			}
		}
		return graph;
	}
	
}
