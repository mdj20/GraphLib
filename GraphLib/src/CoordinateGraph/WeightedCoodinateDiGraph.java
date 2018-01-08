package CoordinateGraph;

import doubleMap.DoubleMap;
import interfaces.DiGraph;
import interfaces.WeightedDirectionalEdge;
import interfaces.WeightedGraph;

public interface WeightedCoodinateDiGraph<V,W> extends DiGraph<V,WeightedDirectionalEdge<V,W>>,WeightedGraph<V,WeightedDirectionalEdge<V,W>,W>,DoubleMap<Integer,V> {
	
	
	
	

}
