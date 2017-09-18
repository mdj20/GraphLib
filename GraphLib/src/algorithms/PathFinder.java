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
import test.BuildWeightedDiGraph;

public class PathFinder {

	public static <V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> 
		List<WeightedEdge<V,W>> lazyDikstras(WeightedGraph<V,E,W> graph, V source, V sink){
		
		
			ArrayList<WeightedEdge<V,W>> path = new ArrayList<>();  // return path;
			PriorityQueue<VertexInt<V,Integer,W>> queue = new PriorityQueue<VertexInt<V,Integer,W>>();  // priority queue used to determine the lowest tentative value of vertices' distance
			HashSet<V> checked = new HashSet<V>();  // set of vertices that have been checked 
			HashMap<V,VertexInt<V,Integer,W>> map = new HashMap<V,VertexInt<V,Integer,W>>();  
			VertexInt<V,Integer, W> sourceInfo = new VertexInt<V,Integer,W>(source,0);
		
			map.put(source, sourceInfo);
			queue.add(sourceInfo);
			while(!queue.isEmpty() && !sink.equals(queue.peek().vertex)) {
				sourceInfo = queue.poll(); //pop next vertexInfo
				checked.add(sourceInfo.vertex);  // add vertex to checked list
				for(E edge: graph.getOutgoingEdges(sourceInfo.vertex)) {  // for each outgoing edge
					V nextV = edge.getOpposingVertex(sourceInfo.vertex);  // get opposing vertex
					if(!map.containsKey(nextV)){ //if vertex hasn't been discovered
						VertexInt<V,Integer,W> next = new VertexInt<V,Integer,W>(nextV,(sourceInfo.val+edge.getWeight().intValue()),edge); // create new record with updated weights and edge
						map.put(nextV,next); // put in map
						queue.add(next);	// add to queue
					}
					else if(!checked.contains(nextV)) {  // if vertex has been discoverd and it hasn't been checked
						VertexInt<V,Integer,W> next = map.get(nextV);  // get record from map
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
	
	
	public static <V,E extends WeightedEdge<V,W>, W extends Number & Comparable<W>> 
		List<WeightedEdge<V,W>> lazyDikstrasDouble(WeightedGraph<V,E,W> graph, V source, V sink){
	
	
		ArrayList<WeightedEdge<V,W>> path = new ArrayList<>();  // return path;
		PriorityQueue<VertexInt<V,Double,W>> queue = new PriorityQueue<VertexInt<V,Double,W>>();  // priority queue used to determine the lowest tentative value of vertices' distance
		HashSet<V> checked = new HashSet<V>();  // set of vertices that have been checked 
		HashMap<V,VertexInt<V,Double,W>> map = new HashMap<V,VertexInt<V,Double,W>>();  
		VertexInt<V,Double, W> sourceInfo = new VertexInt<V,Double,W>(source,0.0);
	
		map.put(source, sourceInfo);
		queue.add(sourceInfo);
		while(!queue.isEmpty() && !sink.equals(queue.peek().vertex)) {
			sourceInfo = queue.poll(); //pop next vertexInfo
			checked.add(sourceInfo.vertex);  // add vertex to checked list
			for(E edge: graph.getOutgoingEdges(sourceInfo.vertex)) {  // for each outgoing edge
				V nextV = edge.getOpposingVertex(sourceInfo.vertex);  // get opposing vertex
				if(!map.containsKey(nextV)){ //if vertex hasn't been discovered
					VertexInt<V,Double,W> next = new VertexInt<V,Double,W>(nextV,(sourceInfo.val+edge.getWeight().doubleValue()),edge); // create new record with updated weights and edge
					map.put(nextV,next); // put in map
					queue.add(next);	// add to queue
				}
				else if(!checked.contains(nextV)) {  // if vertex has been discoverd and it hasn't been checked
					VertexInt<V,Double,W> next = map.get(nextV);  // get record from map
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
	
	

	public static void main(String args[]) {
		WeightedAdjacencyListDiGraph<Character,Integer> graph = BuildWeightedDiGraph.getWeightedDiGraph();
		/*lazyDikstras(graph,'A','F');
		System.out.println();
		lazyDikstras(graph,'C','A');
		System.out.println();
		lazyDikstras(graph,'G','C');
		*/
		
		
		for (int i = 0 ; i <50 ; i++) {
			graph = BuildWeightedDiGraph.buildRandomGraph(5, 7, 1, 15);
			lazyDikstrasDouble(graph,'A','E');
			System.out.println();
		}
	}


	private static class VertexInt<V,T extends Number & Comparable<T>,W> implements Comparable<VertexInt<V,T,W>>{
		
		V vertex;
		T val;
		WeightedEdge<V,W> edge;
		VertexInt(V vertex, T val){
			this.vertex = vertex;
			this.val = val;
			this.edge = null;
		}
		VertexInt(V vertex, T val,WeightedEdge<V,W> edge) {
			this.vertex = vertex;
			this.val = val;
			this.edge = edge;
		}
		
		

		@Override
		public int compareTo(VertexInt<V,T,W> o) {
			// TODO Auto-generated method stub
			return val.compareTo(o.val);
		}
	}
	
}
