package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import interfaces.WeightedDirectionalEdge;
import main.WeightedAdjacencyListDiGraph;
import test.BuildWeightedDiGraph;

public class Dikstras {
	
	public static <V, W extends Number> List<WeightedDirectionalEdge<V,W>> findShortestPath(WeightedAdjacencyListDiGraph<V,W> graph, V source, V sink){
		PriorityQueue<VertexVal<V,W>> pq = new PriorityQueue<VertexVal<V,W>>();
		HashSet<VertexVal<V,W>> checked = new HashSet<VertexVal<V,W>>();
		HashMap<V,VertexVal<V,W>> map = new HashMap<V,VertexVal<V,W>>();
		VertexVal<V,W> s = addOrUpdate(map,pq,source,0);
		//pq.add(s);		
		while(!pq.isEmpty()&&(!pq.peek().vertex.equals(sink))) {
			VertexVal<V,W> current = pq.poll();
			//System.out.println("THIS "+sink+" "+current.vertex );
			//System.out.println(current.vertex+" "+current.val);
			checked.add(current);
			for(WeightedDirectionalEdge<V,W> de:graph.getOutgoingEdges(current.vertex)) {
				VertexVal<V,W> temp = addOrUpdate(map,pq,
						de.getOpposingVertex(current.vertex),
						de.getWeight().intValue()+current.val,de);	
			}
			
		}
		if(!pq.isEmpty()&&pq.peek().vertex.equals(sink)) {
			ArrayList<V> path = new ArrayList<>();
			path.add(pq.poll().vertex);
			ArrayList<VertexVal<V,W>> sortedValues = new ArrayList<VertexVal<V,W>>(map.values());			
			Collections.sort(sortedValues);
			Set<V> incoming = graph.getIncomingVertices(sink);

			while(!incoming.contains(source)) {
				for(VertexVal<V,W> vv:sortedValues) {
					if(incoming.contains(vv.vertex)) {
						path.add(vv.vertex);
					}
				}
				incoming = graph.getIncomingVertices(path.get(path.size()-1));
			}
			path.add(sortedValues.get(0).vertex);
			Collections.reverse(path);
			System.out.println("PATH!");
			for(V v:path) {
				System.out.println(v);
			}
			
		}

		
		// this is where 
		
		for(V v : map.keySet()) {
			System.out.println(v+" "+map.get(v).val);
		}
		return null;
		
	}
	
	public static <V, W extends Number & Comparable<W>> List<WeightedDirectionalEdge<V,W>> shortestPathDikstra(WeightedAdjacencyListDiGraph<V,W> graph, V source, V sink){
		PriorityQueue<VertexVal<V,W>> pq = new PriorityQueue<VertexVal<V,W>>();
		HashSet<VertexVal<V,W>> checked = new HashSet<VertexVal<V,W>>();
		HashMap<V,VertexVal<V,W>> map = new HashMap<V,VertexVal<V,W>>();
		addOrUpdate(map,pq,source,0);		
		while(!pq.isEmpty()&&(!pq.peek().vertex.equals(sink))) {
			VertexVal<V,W> current = pq.poll();
			//System.out.println("THIS "+sink+" "+current.vertex );
			//System.out.println(current.vertex+" "+current.val);
			checked.add(current);
			for(WeightedDirectionalEdge<V,W> de:graph.getOutgoingEdges(current.vertex)) {
				VertexVal<V,W> temp = addOrUpdate(map,pq,
						de.getOpposingVertex(current.vertex),
						de.getWeight().intValue()+current.val);	
			}
			
		}
		if(!pq.isEmpty()&&pq.peek().vertex.equals(sink)) {
			ArrayList<V> path = new ArrayList<>();
			path.add(pq.poll().vertex);
			ArrayList<VertexVal<V,W>> sortedValues = new ArrayList<VertexVal<V,W>>(map.values());			
			Collections.sort(sortedValues);
			Set<V> incoming = graph.getIncomingVertices(sink);

			while(!incoming.contains(source)) {
				for(VertexVal<V,W> vv:sortedValues) {
					if(incoming.contains(vv.vertex)) {
						path.add(vv.vertex);
					}
				}
				incoming = graph.getIncomingVertices(path.get(path.size()-1));
			}
			path.add(sortedValues.get(0).vertex);
			Collections.reverse(path);
			System.out.println("PATH!");
			for(V v:path) {
				System.out.println(v);
			}
			
		}

		
		// this is where 
		
		for(V v : map.keySet()) {
			System.out.println(v+" "+map.get(v).val);
		}
		return null;
		
	}
	
	
	
	
	
	private static <V, W extends Number> VertexVal<V,W> addOrUpdate(HashMap<V, VertexVal<V,W>> discoverMap, PriorityQueue<VertexVal<V,W>> pq, V vertex, int value){
		VertexVal<V,W> ret;
		if(!discoverMap.containsKey(vertex)){
			discoverMap.put(vertex, new VertexVal<V,W>(vertex,value));
			ret = discoverMap.get(vertex);
			pq.add(ret);
		}
		else {
			
			int temp = discoverMap.get(vertex).val;
			ret = discoverMap.get(vertex);
			if (value < temp){
				pq.remove(ret);
				ret.val = value;
				pq.add(ret);
			}
			ret = discoverMap.get(vertex);
		}
		return ret;
	}
	
	private static <V, W extends Number> VertexVal<V,W> addOrUpdate(HashMap<V, VertexVal<V,W>> discoverMap, PriorityQueue<VertexVal<V,W>> pq, V vertex, int value, WeightedDirectionalEdge<V,W> edge){
		VertexVal<V,W> ret;
		if(!discoverMap.containsKey(vertex)){
			discoverMap.put(vertex, new VertexVal<V,W>(vertex,value,edge));
			ret = discoverMap.get(vertex);
			pq.add(ret);
		}
		else {
			int temp = discoverMap.get(vertex).val;
			ret = discoverMap.get(vertex);
			if (value < temp){
				pq.remove(ret);
				ret.val = value;
				ret.edge= edge;
				pq.add(ret);
			}
			ret = discoverMap.get(vertex);
		}
		return ret;
	}
	
	
	public static <V, W extends Number> HashMap<V,Integer> buildIntegerDistanceMap(WeightedAdjacencyListDiGraph<V,W> graph, V source, V sink){
		PriorityQueue<VertexVal<V,W>> pq = new PriorityQueue<VertexVal<V,W>>();
		HashSet<VertexVal<V,W>> checked = new HashSet<VertexVal<V,W>>();
		HashMap<V,VertexVal<V,W>> map = new HashMap<V,VertexVal<V,W>>();
		VertexVal<V,W> s = addOrUpdate(map,pq,source,0);		
		while(!pq.isEmpty()) {
			VertexVal<V,W> current = pq.poll();
			checked.add(current);
			for(WeightedDirectionalEdge<V,W> de:graph.getOutgoingEdges(current.vertex)) {
				VertexVal<V,W> temp = addOrUpdate(map,pq,de.getOpposingVertex(current.vertex),de.getWeight().intValue()+current.val);	
				if(!checked.contains(temp)){
					pq.add(temp);
				}
			}
		}
		HashMap<V,Integer> distanceMap = new HashMap<V,Integer>();
		for(V v: map.keySet()) {
			distanceMap.put(v,map.get(v).val);
		}
		return distanceMap;
	}
	

	

	
	
	
	// smoketest
	public static void main(String args[]) {
		WeightedAdjacencyListDiGraph<Character,Integer> graph = BuildWeightedDiGraph.getWeightedDiGraph();
		findShortestPath(graph,'A','F');
		System.out.println();
		findShortestPath(graph,'C','A');
		System.out.println();
		findShortestPath(graph,'G','C');
	}
	
	static class VertexVal<V,W> implements Comparable<VertexVal<V,W>>{
		
		V vertex;
		Integer val;
		WeightedDirectionalEdge<V,W> edge;
		VertexVal(V vertex, Integer val){
			this.vertex = vertex;
			this.val = val;
		}
		VertexVal(V vertex, Integer val,WeightedDirectionalEdge<V,W> edge) {
			this.vertex = vertex;
			this.val = val;
			this.edge = edge;
		}
		
		

		@Override
		public int compareTo(VertexVal<V,W> o) {
			// TODO Auto-generated method stub
			return this.val - o.val;
		}
	}

	
}
