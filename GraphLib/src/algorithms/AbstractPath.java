package algorithms;

import java.util.List;

import interfaces.Edge;

public class AbstractPath<V, E extends Edge<V>> implements Path<V,E>{
	
	protected List<E> edgeList;
	protected List<V> vertexList;
	protected V source;
	protected V sink;
	
	protected AbstractPath(V source, V sink, List<E> edgeList  ) {
		this.source = source;
		this.sink = sink;
		this.edgeList = edgeList;
	}

	@Override
	public List<E> getEdgeList() {
		return edgeList;
	}

	@Override
	public V getSource() {
		return source;
	}

	@Override
	public V getSink() {
		return sink;
	}

	@Override
	public V getVertexAt(int i) {
		return vertexList.get(i);
	}

	protected V inferSource(List<E> edgeList) {
		V ret = null;
		if(edgeList.size()>1) {
			E firstEdge = edgeList.get(0);
			V vert0 = firstEdge.getVertex(0);
			V vert1 = firstEdge.getVertex(1);
			E nextEdge = edgeList.get(1);
			List<V> nextVerts = nextEdge.getVertices();
			if(nextVerts.indexOf(firstEdge.getVertex(0))) 
		}
		
		
	}
	
}
