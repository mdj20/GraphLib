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
import test.BuildWeightedDiGraph;

public class Dikstras {
	
	public static <V, W extends Number> List<WeightedDirectionalEdge<V,W>> findShortestPath(WeightedAdjacencyListDiGraph<V,W> graph, V source, V sink){
		PriorityQueue<VertexVal<V>> pq = new PriorityQueue<VertexVal<V>>();
		HashSet<VertexVal<V>> checked = new HashSet<VertexVal<V>>();
		HashMap<V,VertexVal<V>> map = new HashMap<V,VertexVal<V>>();
		VertexVal<V> s = addOrUpdate(map,source,0);
		pq.add(s);		
		while(!pq.isEmpty()) {
			VertexVal<V> current = pq.poll();
			checked.add(current);
			for(WeightedDirectionalEdge<V,W> de:graph.getOutgoingEdges(current.vertex)) {
				VertexVal<V> temp = addOrUpdate(map,de.getOpposingVertex(current.vertex),de.getWeight().intValue()+current.val);	
				if(!checked.contains(temp)){
					pq.add(temp);
				}
			}
		}
		
		// this is where 
		
		for(V v : map.keySet()) {
			System.out.println(v+" "+map.get(v).val);
		}
		return null;
		
	}
	
	private static <V, W extends Number> VertexVal<V> addOrUpdate(HashMap<V, VertexVal<V>> discoverMap, V vertex, int value){
		VertexVal<V> ret;
		if(!discoverMap.containsKey(vertex)){
			discoverMap.put(vertex, new VertexVal<V>(vertex,value));
			ret = discoverMap.get(vertex);
		}
		else{
			int temp = discoverMap.get(vertex).val;
			ret = discoverMap.get(vertex);
			if (value < temp){
				ret.val = value;
			}
			ret = discoverMap.get(vertex);
		}
		return ret;
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
		WeightedAdjacencyListDiGraph<Character,Integer> graph = BuildWeightedDiGraph.getWeightedDiGraph();
		findShortestPath(graph,'A','C');
		System.out.println();
		findShortestPath(graph,'C','A');
		System.out.println();
		findShortestPath(graph,'G','C');
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
			return this.val - o.val;
		}
	}

	
}
