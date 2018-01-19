package algorithms;

import java.util.List;

import interfaces.Edge;

public class SimplePath<V,E extends Edge<V>> extends AbstractPath<V,E> implements Path<V,E> {
	public SimplePath(V source, V sink, List<E> edgeList) {
		super(source, sink, edgeList);
	}
}
