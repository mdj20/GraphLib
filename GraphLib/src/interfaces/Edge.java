package interfaces;
import java.util.List;

public interface Edge<V> {
	public List<V> getVertices();
	public V getOpposingVertex(V vertex);
	public boolean isReciprical(Edge<V> edge);
}
