package CoordinateGraph;

import java.util.Collection;
import java.util.NavigableMap;
import java.util.Set;

import doubleMap.DoubleIndex;
import doubleMap.DoubleMap;
import doubleMap.ExtensibleMatrix;
import interfaces.DiGraph;
import interfaces.WeightedDirectionalEdge;
import interfaces.WeightedGraph;
import main.WeightedAdjacencyListDiGraph;

public class SimpleWeightedCoordinateDiGraph<V, W> extends WeightedAdjacencyListDiGraph<V, W>
	implements WeightedCoodinateDiGraph<V,W>, DiGraph<V,WeightedDirectionalEdge<V,W>>, WeightedGraph<V,WeightedDirectionalEdge<V,W>,W>,  DoubleMap<Integer,V>  {
	
	private static int xLimit =10; // xLimit will 
	private static int yLimit =10; // yLimit will
	
	public SimpleWeightedCoordinateDiGraph(int x, int y) { // Constructor allows for initial 
		xLimit = x; 
		yLimit = y;
		extensibleMatrix = new ExtensibleMatrix<V>();
	}
	
	
	ExtensibleMatrix<V> extensibleMatrix ;

	@Override
	public V put(Integer k1, Integer k2, V v) {
		V eMatrixReturn = extensibleMatrix.put(k1, k2, v);  // add to extensibleMatrix
		if (eMatrixReturn!=null){   // if extensibleMatrix contains value at coordinate, remove old value and replace. 
			super.removeVertex(v);
		}
		return 	eMatrixReturn;
	}

	@Override
	public V get(Integer k1, Integer k2) {
		return extensibleMatrix.get(k1, k2);
	}

	@Override
	public Set<Integer> keySet1() {
		return extensibleMatrix.keySet1();
	}

	@Override
	public Set<Integer> keySet2() {
		return extensibleMatrix.keySet2();
	}

	@Override
	public V remove(Integer k1, Integer k2) {
		V eMatrixRemovedValue = extensibleMatrix.get(k1, k2);
		if (eMatrixRemovedValue != null){
			super.removeVertex(eMatrixRemovedValue);
			extensibleMatrix.remove(k1, k2);
		}
		return eMatrixRemovedValue;
	}

	@Override
	public Collection<V> getX(Integer x) {
		return extensibleMatrix.getX(x);
	}

	@Override
	public Collection<V> getY(Integer y) {
		return extensibleMatrix.getY(y);
	}

	@Override
	public NavigableMap<Integer, V> getXMappedValues(Integer x) {
		return extensibleMatrix.getXMappedValues(x);
	}

	@Override
	public NavigableMap<Integer, V> getYMappedValues(Integer y) {
		return extensibleMatrix.getYMappedValues(y);
	}

	@Override
	public Set<V> getAdjacentVertices(int x, int y) {
		return null;
	}

	@Override
	public Set<WeightedDirectionalEdge<V, W>> getConnectingEdges(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<V> getOutgoingVertices(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<V> getIncomingVertices(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<WeightedDirectionalEdge<V, W>> getOutgoingEdges(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<WeightedDirectionalEdge<V, W>> getIncomingEdges(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private boolean checkMatrixContains(int k1, int k2){
		V value = extensibleMatrix.get(k1, k2);
		return value!=null;
	}
	
}
