package main;
import interfaces.Edge;
import interfaces.Weighted;

public class WeightedEdge<V,W> extends AbstractEdge<V> implements Edge<V>, Weighted<W> {

	WeightedEdge(V v0, V v1) {
		super(v0, v1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public W getWeight() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWeight(W weight) {
		// TODO Auto-generated method stub
		
	}

}
