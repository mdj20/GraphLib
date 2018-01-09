package CoordinateGraph;

import java.util.Set;

import doubleMap.DoubleIndex;
import doubleMap.DoubleMap;
import interfaces.DiGraph;
import interfaces.WeightedDirectionalEdge;
import interfaces.WeightedGraph;

public interface WeightedCoodinateDiGraph<V,W> extends DiGraph<V,WeightedDirectionalEdge<V,W>>,	WeightedGraph<V,WeightedDirectionalEdge<V,W>,W>,DoubleMap<Integer,V> {
	public Set<V> getAdjacentVertices(int x, int y);
	public Set<WeightedDirectionalEdge<V,W>> getConnectingEdges(int x, int y);
	public Set<V> getOutgoingVertices(int x, int y);
	public Set<V> getIncomingVertices(int x, int y);
	public Set<WeightedDirectionalEdge<V,W>> getOutgoingEdges(int x, int y);
	public Set<WeightedDirectionalEdge<V,W>> getIncomingEdges(int x, int y);
}
