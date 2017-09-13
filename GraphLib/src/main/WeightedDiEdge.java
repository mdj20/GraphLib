package main;
import interfaces.Weighted;
import interfaces.WeightedDirectionalEdge;

public class WeightedDiEdge<V, W> extends DiEdge<V> implements WeightedDirectionalEdge<V,W> {

	private W weight;
	
	WeightedDiEdge(V source, V sink){
		this(source, sink, null);
	}
	
	WeightedDiEdge(V source, V sink, W weight) {
		super(source, sink);
		this.weight = weight;
	}

	@Override
	public W getWeight() {
		return weight;
	}

	@Override
	public void setWeight(W weight) {
		this.weight = weight;
	}

}
