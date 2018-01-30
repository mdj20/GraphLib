package traverse;

import java.util.List;


/**
 * @author Matthew D. Jeffreys
 */

import interfaces.Edge;

public class SimplePath<V,E extends Edge<V>> extends AbstractPath<V,E> implements Path<V,E> {
	public SimplePath(V source, V sink, List<E> edgeList) {
		super(source, sink, edgeList);
	}
}
