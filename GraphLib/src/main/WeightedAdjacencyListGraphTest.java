package main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

import interfaces.WeightedEdge;
import testutilities.FastGraphBuilder;

public class WeightedAdjacencyListGraphTest {

	@Test
	public void testCreateEdgeVV() {
		WeightedAdjacencyListGraph<Character,Integer> graph = new WeightedAdjacencyListGraph<Character,Integer>(); 
		HashSet<Character> refrence = new HashSet<Character>();
	}

	@Test
	public void testAddEdgeVVW() {
		int nEdge = 5;
		Random rando = new Random(System.nanoTime());
		ArrayList<Character> sourceList = new ArrayList<Character>();
		ArrayList<Character> sinkList = new ArrayList<Character>();
		ArrayList<Integer> weightList = new ArrayList<Integer>();
		Set<WeightedEdge<Character,Integer>> edgeSetBuild = new HashSet<WeightedEdge<Character,Integer>>();
		WeightedAdjacencyListGraph<Character,Integer> graph = FastGraphBuilder.buildRandomWeightedGraph(10, 0, 0, 0);
		ArrayList<Character> verts = new ArrayList<Character>(graph.getVertices());
		Character source = null , sink = null;
		int weight=0;
		for(int i = 0 ; i < nEdge ; i++) {
			source = verts.get(rando.nextInt(verts.size()));
			sink = verts.get(rando.nextInt(verts.size()));
			weight = rando.nextInt(25);
			if(graph.addEdge(source, sink, weight)) {
				sourceList.add(source);
				sinkList.add(sink);
				weightList.add(weight);
			}
		}
		
		Set<WeightedEdge<Character,Integer>> edgeSet = graph.getEdges(); 
		for(WeightedEdge<Character,Integer> edge: edgeSet) {
			
		}
		
		
		
	}

	@Test
	public void testAddVertex() {
		WeightedAdjacencyListGraph<Character,Integer> graph = new WeightedAdjacencyListGraph<Character,Integer>(); 
		HashSet<Character> refrence = new HashSet<Character>();
		Random rando = new Random(System.nanoTime());
		int nVerts = 15;
		int nSuccess = 0;
		while (nSuccess<nVerts) {
			Character c = FastGraphBuilder.tc(rando.nextInt(25));
			if(graph.addVertex(c)) {
				nSuccess++;
				refrence.add(c);
			}
			else {
				assertTrue(graph.getVertices().contains(c));  // check graph returns null if try to add vertex that already exists 
			}
		}
		for(Character c: refrence ) {
			assertTrue(graph.getVertices().contains(c)); // check if all verts that were successfully inserted, are returned in the list.
		}
		assertTrue(nSuccess==graph.getVertices().size());
	}

	@Test
	public void testAddEdgeE() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveVertex() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveEdge() {
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

	@Test
	public void testAddEdgeVV() {
		fail("Not yet implemented");
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
	public void testIsWeighted() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDirected() {
		fail("Not yet implemented");
	}

}
