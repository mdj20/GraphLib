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
import interfaces.WeightedGraph;
import main.WeightedAdjacencyListDiGraph;
import testutilities.FastGraphBuilder;
import testutilities.TestGraphData;
import interfaces.WeightedEdge;

public class Dikstras {
	
	public static <V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> List<V> findShortestPathInt(WeightedGraph<V,E,W> graph, V source, V sink){
		// check graph contains source and sink
		Set<V> vertecies = graph.getVertices();
		if( !(vertecies.contains(source) && vertecies.contains(sink)) ){
			throw new IllegalArgumentException("Graph must contain both source and sink");
		}
		PriorityQueue<VertexVal<V,W,Integer>> pq = new PriorityQueue<VertexVal<V,W,Integer>>(); 
		HashSet<V> checked = new HashSet<V>();
		HashMap<V,VertexVal<V,W,Integer>> map = new HashMap<V,VertexVal<V,W,Integer>>();
		addOrUpdate(map,pq,source,Integer.valueOf(0));
		while(!pq.isEmpty()&&!sink.equals(pq.peek().vertex)){
			VertexVal<V,W,Integer> current = pq.poll();  // poll lowest level vertex
			checked.add(current.vertex);  // add vertex to checked set
			for (WeightedEdge<V,W> edge  :graph.getOutgoingEdges(current.vertex)){	// for each outgoing edge 
				if(!checked.contains(edge.getOpposingVertex(current.vertex))){		// if opposing vertex has not been checked   
					System.out.println(edge.getOpposingVertex(current.vertex)+" "+(map.get(current.vertex).val + edge.getWeight().intValue())
							+" "+edge.getWeight()); 
					addOrUpdate(map,
								pq,
								edge.getOpposingVertex(current.vertex),
								map.get(current.vertex).val+edge.getWeight().intValue(),
								edge);
				}
			}
		}
		ArrayList<V> path = new ArrayList<V>();
		boolean run = true;
		System.out.println(!pq.isEmpty()&&pq.peek().vertex.equals(sink));
		if(!pq.isEmpty()&&pq.peek().vertex.equals(sink)) {
			VertexVal<V,W,Integer> vv = pq.poll();
			path.add(vv.vertex);
			while(run) {
				vv = map.get(vv.edge.getOpposingVertex(vv.vertex));
				if(vv.vertex.equals(source))
					run=false;
					path.add(vv.vertex);
				}
		}
	
		
		
		//return path;
		return path;
	}
	
	
	private static <V, W extends Number & Comparable<W>,I extends Comparable<I>> VertexVal<V,W,I> addOrUpdate(HashMap<V, VertexVal<V,W,I>> discoverMap, PriorityQueue<VertexVal<V,W,I>> pq, V vertex, I value){
		VertexVal<V,W,I> ret;
		if(!discoverMap.containsKey(vertex)){
			discoverMap.put(vertex, new VertexVal<V,W,I>(vertex,value));
			ret = discoverMap.get(vertex);
			pq.add(ret);
		}
		else {
			I temp = discoverMap.get(vertex).val;
			ret = discoverMap.get(vertex);
			if (value.compareTo(temp)<0){
				pq.remove(ret);
				ret.val = value;
				pq.add(ret);
			}
			ret = discoverMap.get(vertex);
		}
		return ret;
	}
	
	private static <V, W extends Number & Comparable<W>,I extends Comparable<I>> VertexVal<V,W,I> addOrUpdate(HashMap<V, VertexVal<V,W,I>> discoverMap, PriorityQueue<VertexVal<V,W,I>> pq, V vertex, I value, WeightedEdge<V,W> edge){
		VertexVal<V,W,I> ret;
		if(!discoverMap.containsKey(vertex)){
			discoverMap.put(vertex, new VertexVal<V,W,I>(vertex,value,edge));
			ret = discoverMap.get(vertex);
			pq.add(ret);
		}
		else {
			I temp = discoverMap.get(vertex).val;
			ret = discoverMap.get(vertex);
			if (value.compareTo(temp)<0){
				pq.remove(ret);
				ret.val = value;
				ret.edge= edge;
				pq.add(ret);
			}
			ret = discoverMap.get(vertex);
		}
		return ret;
	}
	
/*	
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
	*/
	
	// smoketest
	public static void main(String args[]) {
		WeightedAdjacencyListDiGraph<Character,Integer> graph = FastGraphBuilder.getWeightedDiGraph();
		WeightedAdjacencyListDiGraph<Character,Integer> graph1 = FastGraphBuilder.getWeightedDiGraph(TestGraphData.TestGraph1);
		for(Character c: findShortestPathInt(graph,'G','C') ) {
			System.out.println(c);
		};
		System.out.println();
	/*	for(WeightedDirectionalEdge<Character,Integer> wde:graph1.getOutgoingEdges('E')){
			System.out.println(wde.getSource()+" "+wde.getWeight()+" "+wde.getSink());
		}*/
		List<Character> path = findShortestPathInt(graph1,'C','A');
		for(Character c:path) {
			System.out.println(c);
		};
	}
	
	//private boolean (WeightedGraph<>)
	
	private static Integer addInteger(Integer i, Integer j) {
		return i+j;
	}
	private static Integer subInteger(Integer i, Integer j) {
		return i-j;
	}
	private static Double addDouble(Double i, Double j){
		return i+j;
	}
	private static Double subDouble(Double i, Double j) {
		return i-j;
	}
	
	
	static class VertexVal<V,W extends Comparable<W>,I extends Comparable<I>> implements Comparable<VertexVal<V,W,I>>{
		
		V vertex;
		I val;
		WeightedEdge<V,W> edge;
		VertexVal(V vertex, I val){
			this.vertex = vertex;
			this.val = val;
		}
		VertexVal(V vertex, I val, WeightedEdge<V,W> edge) {
			this.vertex = vertex;
			this.val = val;
			this.edge = edge;
		}

		@Override
		public int compareTo(VertexVal<V,W,I> o) {
			return val.compareTo(o.val);
		}
	}

	
}
