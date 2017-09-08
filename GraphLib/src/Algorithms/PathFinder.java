package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import Algorithms.Dikstras.VertexVal;
import Interfaces.WeightedDirectionalEdge;
import main.WeightedAdjacencyListDiGraph;
import test.BuildWeightedDiGraph;

public class PathFinder {

	public static <V,W extends Number & Comparable<W>> List<WeightedDirectionalEdge<V,W>> lazyDikstras(WeightedAdjacencyListDiGraph<V,W> graph, V source, V sink){
		ArrayList<WeightedDirectionalEdge<V,W>> path = new ArrayList<>();  // return path;
		PriorityQueue<VertexVal<V,W>> queue = new PriorityQueue<VertexVal<V,W>>();  // priority queue used to determine the lowest tentative value of vertices' distance
		HashSet<V> checked = new HashSet<V>();  // set of vertices that have been checked 
		HashMap<V,VertexVal<V,W>> map = new HashMap<V,VertexVal<V,W>>();  
		VertexVal<V, W> sourceInfo = new VertexVal<V,W>(source,0);
		
		map.put(source, sourceInfo);
		queue.add(sourceInfo);
		while(!queue.isEmpty() && !sink.equals(queue.peek().vertex)) {
			sourceInfo = queue.poll(); //pop next vertexInfo
			checked.add(sourceInfo.vertex);  // add vertex to checked list
			for(WeightedDirectionalEdge<V,W> edge: graph.getOutgoingEdges(sourceInfo.vertex)) {  // for each outgoing edge
				V nextV = edge.getOpposingVertex(sourceInfo.vertex);  // get opposing vertex
				if(!map.containsKey(nextV)){ //if vertex hasn't been discovered
					VertexVal<V,W> next = new VertexVal<V,W>(nextV,(sourceInfo.val+edge.getWeight().intValue()),edge); // create new record with updated weights and edge
					map.put(nextV,next); // put in map
					queue.add(next);	// add to queue
				}
				else if(!checked.contains(nextV)) {  // if vertex has been discoverd and it hasn't been checked
					VertexVal<V,W> next = map.get(nextV);  // get record from map
					if((sourceInfo.val+edge.getWeight().intValue()) <next.val ) {  // and if new value is less than established value
						queue.remove(next);  // remove from queue (must reinsert)
						next.edge = edge;  // update info 
						next.val = sourceInfo.val + edge.getWeight().intValue();
						queue.add(next);  // add back to queue
					}
				}
			}
		}
		if(!queue.isEmpty()&&sink.equals(queue.peek().vertex)) { //a ggregate results
			V current = queue.poll().vertex;
			while (!source.equals(current)) {
				path.add(map.get(current).edge);
				current = path.get(path.size()-1).getOpposingVertex(current);
			}
			Collections.reverse(path);
		}
		
		
		for(WeightedDirectionalEdge<V,W> edge: path) {
			System.out.println(edge.getSource()+" "+edge.getWeight()+" "+edge.getSink());
		}
		
		
		return null;
	}
	
	
	public static void main(String args[]) {
		WeightedAdjacencyListDiGraph<Character,Integer> graph = BuildWeightedDiGraph.getWeightedDiGraph();
		lazyDikstras(graph,'A','F');
		System.out.println();
		lazyDikstras(graph,'C','A');
		System.out.println();
		lazyDikstras(graph,'G','C');
	}
	
	static class VertexVal<V,W> implements Comparable<VertexVal<V,W>>{
		
		V vertex;
		Integer val;
		WeightedDirectionalEdge<V,W> edge;
		VertexVal(V vertex, Integer val){
			this.vertex = vertex;
			this.val = val;
			this.edge = null;
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
