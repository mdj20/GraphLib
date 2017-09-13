package algorithms;

import main.WeightedAdjacencyListDiGraph;

public class DikstraTest {
	int source[] = {0,0
	int sink[] ={2,3
			int w[]= {
	}
	WeightedAdjacencyListDiGraph<Character,Integer> graph; 
	
	
	public static void main(String args[]){

	}

	public WeightedAdjacencyListDiGraph<Character,Integer> buildGraph(int verts[], int source[], int sink[], int weights[]){
		WeightedAdjacencyListDiGraph<Character,Integer> build = new WeightedAdjacencyListDiGraph<Character,Integer>();
		if(source.length == sink.length && source.length == weights.length){
			for (int i = 0 ;i < 7 ; i++){
				build.addVertex(tc(i));
			}
			for(int i = 0 ; i < source.length ; i++){
				build.addEdge(tc(source[i]), tc(sink[i]), weights[i]);
			}
		}
		else {
			throw new IllegalArgumentException("edges and weights must be equal length");
		}
	
		
	}
	public char tc(int i){
		return (char) (i+65);
	}
	
	
}
