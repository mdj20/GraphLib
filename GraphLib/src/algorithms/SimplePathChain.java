package algorithms;

import interfaces.Edge;

class SimplePathChain<V, E extends Edge<V>, I extends Number & Comparable<I>> implements Comparable<SimplePathChain<V,E,I>> {
	
	V vertex;
	E edge;
	I val;
	
	public V getVertex() {
		return vertex;
	}

	public E getEdge() {
		return edge;
	}

	public I getVal() {
		return val;
	}

	SimplePathChain(V vertex, E edge, I intermediate){
		this.vertex = vertex;
		this.edge = edge;
		this.val = intermediate;
	}
	
	@Override
	public int compareTo(SimplePathChain<V,E,I> o) {
		return val.compareTo(o.val);
	}

}
