import Interfaces.DiGraph;
import Interfaces.WeightedDirectionalEdge;
import Interfaces.WeightedGraph;

public class WeightedAdjacencyListDiGraph<V, W> extends AbstractAdjacencyListDiGraph<V,WeightedDirectionalEdge<V,W>> 
	implements DiGraph<V,WeightedDirectionalEdge<V,W>>, WeightedGraph<V,WeightedDirectionalEdge<V,W>,W> {

	@Override
	public WeightedDirectionalEdge<V, W> createEdge(V vertex1, V vertex2) {
		return new WeightedDiEdge<V,W>(vertex1,vertex2);
	}

	@Override
	public void addEdge(V vertex1, V vertex2, W weight) {
		if(checkVertices(vertex1,vertex2)){
			WeightedDirectionalEdge<V,W> edge = new WeightedDiEdge<V,W>(vertex1,vertex2,weight); 
			addEdgeToGraph(vertex1,edge);
			edges.add(edge);
		}
	}

}
