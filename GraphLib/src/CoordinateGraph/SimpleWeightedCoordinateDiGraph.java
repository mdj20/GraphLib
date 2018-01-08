package CoordinateGraph;

import java.util.Collection;
import java.util.NavigableMap;
import java.util.Set;

import doubleMap.DoubleMap;
import doubleMap.ExtensibleMatrix;
import interfaces.DiGraph;
import interfaces.WeightedDirectionalEdge;
import interfaces.WeightedGraph;
import main.WeightedAdjacencyListDiGraph;

public class SimpleWeightedCoordinateDiGraph<V, W> extends WeightedAdjacencyListDiGraph<V, W>
	implements DiGraph<V,WeightedDirectionalEdge<V,W>>, WeightedGraph<V,WeightedDirectionalEdge<V,W>,W>,  DoubleMap<Integer,V>  {
	
	
	public SimpleWeightedCoordinateDiGraph(int x, int y) { // Constructor allows for initial 
		
	}
	
	
	ExtensibleMatrix<V> coodinateGraph ;

	@Override
	public V put(Integer k1, Integer k2, V v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(Integer k1, Integer k2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Integer> keySet1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Integer> keySet2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(Integer k1, Integer k2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> getX(Integer x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> getY(Integer y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableMap<Integer, V> getXMappedValues(Integer x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableMap<Integer, V> getYMappedValues(Integer y) {
		// TODO Auto-generated method stub
		return null;
	}

}
