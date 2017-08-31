package Interfaces;

public interface WeightedGraph<V,E extends Weighted<W> & Edge<V>, W> extends Graph<V,E> {
	public void addEdge(V vertex1, V vertex2, W weight);
}
