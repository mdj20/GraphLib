package Interfaces;
import java.util.List;

public interface Edge<V> {
	public List<V> getVertices();
	public V getOpposingVertex(V vertex);
}
