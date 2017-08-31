package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import Interfaces.WeightedDirectionalEdge;
import main.WeightedAdjacencyListDiGraph;

public class Dikstras {
	
	public static <V, W extends Number> List<WeightedDirectionalEdge<V,W>> findShortestPath(WeightedAdjacencyListDiGraph<V,W> graph, V source, V sink){
		PriorityQueue<VertexVal<V>> pq = new PriorityQueue<VertexVal<V>>();
		HashSet<VertexVal<V>> checked = new HashSet<VertexVal<V>>();
		HashMap<V,VertexVal<V>> map = new HashMap<V,VertexVal<V>>();
		VertexVal<V> s = new VertexVal<V>(source,0);
		map.put(source, s);
		pq.add(s);		
		while(!pq.isEmpty()) {
			VertexVal<V> current = pq.poll();
			V currentVertex = current.vertex;
			for(WeightedDirectionalEdge<V,W> de:graph.getOutgoingEdges(currentVertex)) {
				if(!map.containsKey(de.getOpposingVertex(currentVertex))) {
					VertexVal<V> temp = new VertexVal<V>(de.getOpposingVertex(currentVertex),de.getWeight().intValue());
					map.put(de.getOpposingVertex(currentVertex),temp);
					pq.add(temp);					
				}
				else {
					if(!checked.contains(map.get(de.getOpposingVertex(currentVertex)))) {
						
					}
				}
				
				
			}
		}
	
	}
	

	
	
	private static <V, W extends Number> boolean recursiveShortest(PriorityQueue<VertexVal<V>> pq, HashSet<VertexVal<V>> checked,
			HashMap<V,VertexVal<V>> map, WeightedAdjacencyListDiGraph<V,W> graph, V sink ) {
		boolean ret = false;
		VertexVal<V> current = pq.poll();
		checked.add(current);
		for(WeightedDirectionalEdge<V,W> de : graph.getOutgoingEdges(current.vertex)) {
			if (map.containsKey(de.getOpposingVertex(current.vertex))) {
				
			}
			VertexVal<V> temp = new VertexVal<V>(de.getOpposingVertex(current.vertex),de.getWeight().intValue()); 
			map.put(temp.vertex, temp);
		}
		return false;
	}
	
	// smoketest
	public static void main(String args[]) {
		Double d = 145.00;
		Integer i = 145;
		System.out.println(d.doubleValue());

		System.out.println(i.doubleValue());
	}
	
	static class VertexVal<V> implements Comparable<VertexVal<V>>{
		
		V vertex;
		Integer val;
		VertexVal(V vertex, Integer val){
			this.vertex = vertex;
			this.val = val;
		}

		@Override
		public int compareTo(VertexVal<V> o) {
			// TODO Auto-generated method stub
			return this.val - o.val;;
		}
	}

	
}
