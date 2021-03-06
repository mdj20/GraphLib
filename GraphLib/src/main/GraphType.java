package main;

public enum GraphType implements GraphParameters {
	// (weighted,directed)
	SIMPLE(false,false),
	WEIGHTED(true,false),
	DIRECTED(false,true),
	WEIGHTED_DIRECTED(true,true);
	
	private boolean weighted= false;
	private boolean directed= false;
	
	private GraphType(boolean weighted, boolean directed){
		this.weighted = weighted;
		this.directed = directed;
	}

	@Override
	public boolean isWeighted() {
		return weighted;
	}

	@Override
	public boolean isDirected() {
		return directed;
	}
}
