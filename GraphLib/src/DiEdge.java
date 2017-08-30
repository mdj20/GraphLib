import Interfaces.Directional;
import Interfaces.DirectionalEdge;
import Interfaces.Edge;

public class DiEdge<V> extends AbstractEdge<V> implements DirectionalEdge<V>, Edge<V> {

	DiEdge(V source, V sink) {
		super(source, sink);
		// TODO Auto-generated constructor stub
	}

	@Override
	public V getSource() {
		return super.v.get(0);
	}

	@Override
	public V getSink() {
		// TODO Auto-generated method stub
		return super.v.get(1);
	}
}
