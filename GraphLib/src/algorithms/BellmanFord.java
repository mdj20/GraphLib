package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import interfaces.WeightedDirectionalEdge;
import interfaces.WeightedEdge;
import interfaces.WeightedGraph;
import main.WeightedAdjacencyListDiGraph;
import testutilities.FastGraphBuilder;
import testutilities.TestGraphData;

public class BellmanFord {

	public static <V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> Map<V,Integer> findDistancesInt(WeightedGraph<V,E,W> graph, V source){
		ArrayList<V> vertices = new ArrayList<V>(graph.getVertices());
		int nVertex = vertices.size();
		HashMap<V,Integer>  distanceMap = new HashMap<V,Integer>();
		distanceMap.put(source, 0);
		for(int i = 0 ; i < nVertex-1 ; i++) {
			for(V v: vertices) {
				if(distanceMap.containsKey(v)) {
					for(WeightedEdge<V,W> edge: graph.getOutgoingEdges(v)) {
						V opposing = edge.getOpposingVertex(v);
						if(distanceMap.containsKey(opposing)){
							if(distanceMap.get(v)+edge.getWeight().intValue() < distanceMap.get(opposing)){
								distanceMap.put(opposing, distanceMap.get(v).intValue() + edge.getWeight().intValue() );
							}	
						}
						else {
							distanceMap.put(opposing, edge.getWeight().intValue()+distanceMap.get(v));
						}
					}
				}
			}
		}
		return distanceMap;
	}
	
	public static <G extends WeightedGraph<V,E,W>,V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> List<E> findPathInt(G graph, V source,V sink){
		ArrayList<V> vertices = new ArrayList<V>(graph.getVertices());
		ArrayList<E> pathEdgeList = new ArrayList<E>(); 
		int nVertex = vertices.size();
		HashMap<V,WeightedPathChain<V,E,W,Integer>>  distanceMap = new HashMap<V,WeightedPathChain<V,E,W,Integer>>();
		distanceMap.put(source, new WeightedPathChain<V,E,W,Integer>(source,0));
		for(int i = 0 ; i < nVertex-1 ; i++) {
			for(V v: vertices) {
				if(distanceMap.containsKey(v)) {
					for(E edge: graph.getOutgoingEdges(v)) {
						V opposing = edge.getOpposingVertex(v);
						if(distanceMap.containsKey(opposing)){
							if(distanceMap.get(v).getVal()+edge.getWeight().intValue() < distanceMap.get(opposing).getVal()){
								distanceMap.get(opposing).setValue(distanceMap.get(v).getVal() + edge.getWeight().intValue());
							}	
						}
						else {
							distanceMap.put(opposing,
									new WeightedPathChain<V,E,W,Integer>(opposing,
									edge,
									edge.getWeight().intValue()+distanceMap.get(v).getVal())) ;
						}
					}
				}
			}
		}
		
		// construct pathEdgeList
		if(distanceMap.containsKey(sink)){
			V current = sink;
			E edge = distanceMap.get(current).edge;
			pathEdgeList.add(edge);
			V opposing = edge.getOpposingVertex(current);
			while (!opposing.equals(source)){
				current = opposing;
				edge = distanceMap.get(current).edge;
				pathEdgeList.add(edge);
				opposing = edge.getOpposingVertex(current);
			}
		}
	
		// reverse edgeList
		
		Collections.reverse(pathEdgeList);
		return pathEdgeList;
	}
	
	
	public static <G extends WeightedGraph<V,E,W>,V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> List<E> findPathDouble(G graph, V source,V sink){
		ArrayList<V> vertices = new ArrayList<V>(graph.getVertices());
		ArrayList<E> pathEdgeList = new ArrayList<E>(); 
		int nVertex = vertices.size();
		HashMap<V,WeightedPathChain<V,E,W,Double>>  distanceMap = new HashMap<V,WeightedPathChain<V,E,W,Double>>();
		distanceMap.put(source, new WeightedPathChain<V,E,W,Double>(source,0.0));
		for(int i = 0 ; i < nVertex-1 ; i++) {
			for(V v: vertices) {
				if(distanceMap.containsKey(v)) {
					for(E edge: graph.getOutgoingEdges(v)) {
						V opposing = edge.getOpposingVertex(v);
						if(distanceMap.containsKey(opposing)){
							if(distanceMap.get(v).getVal()+edge.getWeight().doubleValue() < distanceMap.get(opposing).getVal()){
								distanceMap.get(opposing).setValue(distanceMap.get(v).getVal() + edge.getWeight().doubleValue());
							}	
						}
						else {
							distanceMap.put(opposing,
									new WeightedPathChain<V,E,W,Double>(opposing,
									edge,
									edge.getWeight().doubleValue()+distanceMap.get(v).getVal())) ;
						}
					}
				}
			}
		}
		
		// construct pathEdgeList
		if(distanceMap.containsKey(sink)){
			V current = sink;
			E edge = distanceMap.get(current).edge;
			pathEdgeList.add(edge);
			V opposing = edge.getOpposingVertex(current);
			while (!opposing.equals(source)){
				current = opposing;
				edge = distanceMap.get(current).edge;
				pathEdgeList.add(edge);
				opposing = edge.getOpposingVertex(current);
			}
		}
	
		// reverse edgeList
		
		Collections.reverse(pathEdgeList);
		return pathEdgeList;
	}
	
	//public static <V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> List<WeightedEdge<V,W>> getPath(WeightedGraph<V,E,W> graph, Map<V,Integer> distanceMap,  )
	
	
	public static void main(String args[]) {
		// smoke test
		WeightedAdjacencyListDiGraph<Character,Integer> graph = FastGraphBuilder.getWeightedDiGraph();
		WeightedAdjacencyListDiGraph<Character,Integer> graph1 = FastGraphBuilder.getWeightedDiGraph(TestGraphData.TestGraph1);
		/*
		 * Map<Character,Integer> map = findDistancesInt(graph,'A');
		Map<Character,Integer> map1 = findDistancesInt(graph1,'A');
		printMap(map);
		System.out.println();
		printMap(map1);*/
		
		List<WeightedDirectionalEdge<Character, Integer>> list1 = findPathInt(graph,'A','C');
		for(WeightedDirectionalEdge<Character,Integer> wde: list1){
			System.out.println(wde.getSource()+" "+wde.getSink()+" W: "+wde.getWeight());
		}
		System.out.println();
		List<WeightedDirectionalEdge<Character, Integer>> dlist1 = findPathDouble(graph,'A','C');
		for(WeightedDirectionalEdge<Character,Integer> wde: dlist1){
			System.out.println(wde.getSource()+" "+wde.getSink()+" W: "+wde.getWeight());
		}
	
	}
	
	private static void printMap(Map<Character,Integer> map) {
		for(Character c : map.keySet()) {
			System.out.println(c+" "+map.get(c));
		}
	}
	
	
}
