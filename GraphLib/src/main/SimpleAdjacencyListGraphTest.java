package main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SimpleAdjacencyListGraphTest {
	
	SimpleAdjacencyListGraph<Integer> testGraph;
	ArrayList<Integer> testValues;
	int nTestValues = 500;
	int nTestEdges = 100;
	//ArrayList<Integer> 
	@Before public void initialize() {
	      testGraph = new SimpleAdjacencyListGraph<Integer>();
	      testValues = new ArrayList<Integer> ();
	      for(int i = 0 ; i < nTestValues ; i++) {
	    	  testValues.add(i);
	      }
	}

	@Test
	public void testAddVertex() {
		
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
	
	
	// method provides a simple procedure for determining the vertices a edge will connect
	private int getEdgeVertex(ArrayList<Integer> testValues, int edge, int skip, boolean source){
		int index = testValues.size()%(edge*skip);
		int ret = 0;
		if(source){
			ret = testValues.get(index);
		}
		else{
			ret = testValues.get(testValues.size()-1-index);
		}
		return ret;
	}

}
