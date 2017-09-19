package interfaces;
import java.util.List;

public interface Edge<V> {
	public List<V> getVertices();
	public V getVertex(int index);
	public V getOpposingVertex(V vertex);
	public boolean isReciprical(Edge<V> edge);
}
