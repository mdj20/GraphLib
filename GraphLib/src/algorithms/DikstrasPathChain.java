package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import interfaces.WeightedEdge;
import interfaces.WeightedGraph;
import main.WeightedAdjacencyListDiGraph;
import testutilities.FastGraphBuilder;
import testutilities.TestGraphData;

public class DikstrasPathChain {
	
	public static <G extends WeightedGraph<V,E,W>,V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> List<E> findShortestPathInt(G graph, V source, V sink){
		// check graph contains source and sink
		Set<V> vertecies = graph.getVertices();
		if( !(vertecies.contains(source) && vertecies.contains(sink)) ){
			throw new IllegalArgumentException("Graph must contain both source and sink");
		}
		
		
		PriorityQueue<WeightedPathChain<V,E,W,Integer>> pq = new PriorityQueue<WeightedPathChain<V,E,W,Integer>>(); 
		HashSet<V> checked = new HashSet<V>();
		HashMap<V,WeightedPathChain<V,E,W,Integer>> map = new HashMap<V,WeightedPathChain<V,E,W,Integer>>();
		addOrUpdate(map,pq,source,Integer.valueOf(0));
		while(!pq.isEmpty()&&!sink.equals(pq.peek().vertex)){
			WeightedPathChain<V,E,W,Integer> current = pq.poll();  // poll lowest level vertex
			checked.add(current.vertex);  // add vertex to checked set
			for (E  edge  :graph.getOutgoingEdges(current.vertex)){	// for each outgoing edge 
				if(!checked.contains(edge.getOpposingVertex(current.vertex))){		// if opposing vertex has not been checked   
					addOrUpdate(map,
								pq,
								edge.getOpposingVertex(current.vertex),
								Integer.valueOf(map.get(current.vertex).val+edge.getWeight().intValue() ) ,
								edge);
				}
			}
		}
		ArrayList<V> path = new ArrayList<V>();  // vertex path 
		ArrayList<E> edgePath = new ArrayList<E>(); // edge path
		boolean run = true;  
		if(!pq.isEmpty()&&pq.peek().vertex.equals(sink)) {  // 
			WeightedPathChain<V,E,W,Integer> vv = pq.poll();
			path.add(vv.vertex);
			edgePath.add(vv.edge);
			while(run) {
				vv = map.get(vv.edge.getOpposingVertex(vv.vertex));
				if(vv.vertex.equals(source)) {
					run=false;
					path.add(vv.vertex);
				}
				else {	
					edgePath.add(vv.edge);
		
				}
			}
		}
		//return path;
		WeightedPathBuilder<G,V,E,W> weightedPathBuilder = new WeightedPathBuilder<G,V,E,W>(graph,source);
		for(int i = 0; i < edgePath.size(); i++ ) {
			weightedPathBuilder.addEdge(edgePath.get((edgePath.size()-1)-i));
		}
		
		Path<V,E> builtPath = weightedPathBuilder.build();
		
		for(E we:builtPath.getEdgeList()) {
			System.out.println("EDGEPATH:"+" "+we.getVertices().get(0)+" "+we.getWeight()+" "+we.getVertices().get(1));
		}
		
		for(WeightedEdge<V,W> we: edgePath) {
			System.out.println("EDGE:"+" "+we.getVertices().get(0)+" "+we.getWeight()+" "+we.getVertices().get(1));
		}
		return edgePath;
	}
	
	
	private static <V, E extends WeightedEdge<V,W>, W extends Number & Comparable<W>,I extends Comparable<I>> WeightedPathChain<V,E,W,I> addOrUpdate(HashMap<V, WeightedPathChain<V,E,W,I>> discoverMap, PriorityQueue<WeightedPathChain<V,E,W,I>> pq, V vertex, I value, E edge){
		WeightedPathChain<V,E,W,I> ret;
		if(!discoverMap.containsKey(vertex)){
			discoverMap.put(vertex, new WeightedPathChain<V,E,W,I>(vertex,edge,value));
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
	
	private static <V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>,I extends Comparable<I>> WeightedPathChain<V,E,W,I> addOrUpdate(HashMap<V, WeightedPathChain<V,E,W,I>> discoverMap, PriorityQueue<WeightedPathChain<V,E,W,I>> pq, V vertex, I value){
		WeightedPathChain<V,E,W,I> ret;
		if(!discoverMap.containsKey(vertex)){
			discoverMap.put(vertex, new WeightedPathChain<V,E,W,I>(vertex,value));
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

	// smoketest
	public static void main(String args[]) {
		WeightedAdjacencyListDiGraph<Character,Integer> graph = FastGraphBuilder.getWeightedDiGraph();
		WeightedAdjacencyListDiGraph<Character,Integer> graph1 = FastGraphBuilder.getWeightedDiGraph(TestGraphData.TestGraph1);
		for(WeightedEdge<Character,Integer> c: findShortestPathInt(graph,'G','C') ) {
			System.out.println(S);
		};
		System.out.println();
	/*	for(WeightedDirectionalEdge<Character,Integer> wde:graph1.getOutgoingEdges('E')){
			System.out.println(wde.getSource()+" "+wde.getWeight()+" "+wde.getSink());
		}*/
		List<? extends WeightedEdge<Character,Integer>> path = findShortestPathInt(graph1,'C','A');
		for(WeightedEdge<Character,Integer> c:path) {
			System.out.println(c);
		};
	}
	
}
