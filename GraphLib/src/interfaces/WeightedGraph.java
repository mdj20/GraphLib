package interfaces;

public interface WeightedGraph<V,E extends WeightedEdge<>, W> extends Graph<V,E> {
	public void addEdge(V vertex1, V vertex2, W weight);
}
