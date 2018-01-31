package traverse.algorithm;

import interfaces.WeightedEdge;
import interfaces.WeightedGraph;

public class AlgorithmUtility {
	
	/** Determines whether any of a weighted graph's edges are negative. 
	 * 
	 * @param graph  
	 * @return true if the graph has a negative edge
	 */
	
	
	public static <V,E extends WeightedEdge<V,W>, W extends Number> boolean hasNegativeEdge(WeightedGraph<V,E,W> graph){
		boolean ret = false;
		Double zero = Double.valueOf(0);
		for(WeightedEdge<V,W> we: graph.getEdges()) {
			if(zero.compareTo(we.getWeight().doubleValue())>0) {
				ret = true;
				break;
			}
		}
		return ret;
	}
}
