package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import algorithms.Dikstras.VertexVal;
import interfaces.Graph;
import interfaces.WeightedDirectionalEdge;
import interfaces.WeightedEdge;
import interfaces.WeightedGraph;
import main.WeightedAdjacencyListDiGraph;
import main.WeightedAdjacencyListGraph;
import test.FastGraphBuilder;

public class PathFinder {

	public static <V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> List<E>	
								lazyDikstras(WeightedGraph<V,E,W> graph, V source, V sink){
		
		ArrayList<E> path = new ArrayList<>();  // return path;
		PriorityQueue<VertexInt<V,E,W,Integer>> queue = new PriorityQueue<VertexInt<V,E,W,Integer>>();  // priority queue used to determine the lowest tentative value of vertices' distance
		HashSet<V> checked = new HashSet<V>();  // set of vertices that have been checked 
		HashMap<V,VertexInt<V,E,W,Integer>> map = new HashMap<V,VertexInt<V,E,W,Integer>>();  
		VertexInt<V,E,W,Integer> sourceInfo = new  VertexInt<V,E,W,Integer>(source,0);
		map.put(source, sourceInfo);
		queue.add(sourceInfo);
		while(!queue.isEmpty() && !sink.equals(queue.peek().vertex)) {
			sourceInfo = queue.poll(); //pop next vertexInfo
			checked.add(sourceInfo.vertex);  // add vertex to checked list
			for(E edge: graph.getOutgoingEdges(sourceInfo.vertex)) {  // for each outgoing edge
				V nextV = edge.getOpposingVertex(sourceInfo.vertex);  // get opposing vertex
				if(!map.containsKey(nextV)){ //if vertex hasn't been discovered
					VertexInt<V,E,W,Integer> next = new VertexInt<V,E,W,Integer>(nextV,(sourceInfo.val+edge.getWeight().intValue()),edge); // create new record with updated weights and edge
					map.put(nextV,next); // put in map
					queue.add(next);	// add to queue
				}
				else if(!checked.contains(nextV)) {  // if vertex has been discoverd and it hasn't been checked
					VertexInt<V,E,W,Integer> next = map.get(nextV);  // get record from map
					if((sourceInfo.val+edge.getWeight().intValue()) <next.val ) {  // and if new value is less than established value
						queue.remove(next);  // remove from queue (must reinsert)
						next.edge = edge;  // update info 
						next.val = sourceInfo.val + edge.getWeight().intValue();
						queue.add(next);  // add back to queue
					}
				}
			}
		}
		if(!queue.isEmpty()&&sink.equals(queue.peek().vertex)) { //aggregate results
			V current = queue.poll().vertex;
			while (!source.equals(current)) {
				path.add(map.get(current).edge);
				current = path.get(path.size()-1).getOpposingVertex(current);
			}
			Collections.reverse(path);
		}
		return path;
	}
	
	
	public static <V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> List<WeightedEdge<V,W>> 
											lazyDikstrasDouble(WeightedGraph<V,E,W> graph, V source, V sink){
	
		ArrayList<WeightedEdge<V,W>> path = new ArrayList<>();  // return path;
		PriorityQueue<VertexInt<V,E,W,Double>> queue = new PriorityQueue<VertexInt<V,E,W,Double>>();  // priority queue used to determine the lowest tentative value of vertices' distance
		HashSet<V> checked = new HashSet<V>();  // set of vertices that have been checked 
		HashMap<V,VertexInt<V,E,W,Double>> map = new HashMap<V,VertexInt<V,E,W,Double>>();  
		VertexInt<V,E,W,Double> sourceInfo = new VertexInt<V,E,W,Double>(source,0.0);
		map.put(source, sourceInfo);
		queue.add(sourceInfo);
		while(!queue.isEmpty() && !sink.equals(queue.peek().vertex)) {
			sourceInfo = queue.poll(); //pop next vertexInfo
			checked.add(sourceInfo.vertex);  // add vertex to checked list
			for(E edge: graph.getOutgoingEdges(sourceInfo.vertex)) {  // for each outgoing edge
				V nextV = edge.getOpposingVertex(sourceInfo.vertex);  // get opposing vertex
				if(!map.containsKey(nextV)){ //if vertex hasn't been discovered
					VertexInt<V,E,W,Double> next = new VertexInt<V,E,W,Double>(nextV,(sourceInfo.val+edge.getWeight().doubleValue()),edge); // create new record with updated weights and edge
					map.put(nextV,next); // put in map
					queue.add(next);	// add to queue
				}
				else if(!checked.contains(nextV)) {  // if vertex has been discoverd and it hasn't been checked
					VertexInt<V,E,W,Double> next = map.get(nextV);  // get record from map
					if((sourceInfo.val+edge.getWeight().doubleValue()) <next.val ) {  // and if new value is less than established value
						queue.remove(next);  // remove from queue (must reinsert)
						next.edge = edge;  // update info 
						next.val = sourceInfo.val + edge.getWeight().doubleValue();
						queue.add(next);  // add back to queue
					}
				}
			}
		}
		if(!queue.isEmpty()&&sink.equals(queue.peek().vertex)) { //aggregate results
			V current = queue.poll().vertex;
			while (!source.equals(current)) {
				path.add(map.get(current).edge);
				current = path.get(path.size()-1).getOpposingVertex(current);
			}
			Collections.reverse(path);
		}
		return path;
	}
	
	private static class VertexInt<V, E extends WeightedEdge<V,W>,W extends Number & Comparable<W>,T extends Number & Comparable<T>> implements Comparable<VertexInt<V,E,W,T>>{
		V vertex;
		T val;
		E edge;
		VertexInt(V vertex, T val){
			this.vertex = vertex;
			this.val = val;
			this.edge = null;
		}
		VertexInt(V vertex, T val,E edge) {
			this.vertex = vertex;
			this.val = val;
			this.edge = edge;
		}
		@Override
		public int compareTo(VertexInt<V,E,W,T> o) {
			// TODO Auto-generated method stub
			return val.compareTo(o.val);
		}
	}
	
}
