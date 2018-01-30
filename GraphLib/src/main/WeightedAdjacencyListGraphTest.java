package main;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Random;

import org.junit.Test;

import testutilities.FastGraphBuilder;

public class WeightedAdjacencyListGraphTest {

	@Test
	public void testCreateEdgeVV() {
		WeightedAdjacencyListGraph<Character,Integer> graph = new WeightedAdjacencyListGraph<Character,Integer>(); 
		HashSet<Character> refrence = new HashSet<Character>();
	}

	@Test
	public void testAddEdgeVVW() {
		fail("Not yet implemented");
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
			assertTrue(graph.getVertices().contains(c));
		}
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
