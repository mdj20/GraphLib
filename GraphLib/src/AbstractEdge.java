import java.util.ArrayList;
import java.util.List;

import Interfaces.Edge;

public abstract class AbstractEdge<V> implements Edge<V> {
	ArrayList<V> v = new ArrayList<V>(2);
	AbstractEdge(V v0, V v1){
		v.add(0, v0);
		v.add(1, v1);
	}
	public List<V> getVertices() {
		return v; 
	}
	public V getOpposingVertex(V vertex) {
		V ret = null;
		if (v.get(0).equals(vertex)) {
			ret = v.get(1);
		}
		else if (v.get(1).equals(vertex)) {
			ret = v.get(0);
		}
		return ret;
	}
	

}
