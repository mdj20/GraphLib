package main;
import java.util.ArrayList;
import java.util.List;

import interfaces.Edge;

/**
 * Abstract Base class defines the behavior of Edge.
 * <p>
 * 
 * @author Matthew D. Jeffreys 
 * 
 *
 * @param <V> Vertex type
 */


public abstract class AbstractEdge<V> implements Edge<V> {
	ArrayList<V> v = new ArrayList<V>(2);
	AbstractEdge(V v0, V v1){
		v.add(0, v0);
		v.add(1, v1);
	}
	public List<V> getVertices() {
		return v; 
	}
	public V getVertex(int index){
		return v.get(index);
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
	public boolean isReciprical(Edge<V> edge){
		boolean ret = false;
		List<V> verts = edge.getVertices();
		if(v.get(0).equals(verts.get(1)) && v.get(1).equals(verts.get(0))){
			ret = true;
		}
		return ret;
	}
}
