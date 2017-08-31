import java.util.Random;

import Interfaces.DirectionalEdge;

public class DiGraphTest {
	
	SimpleAdjacencyListDiGraph<Character> graph = new SimpleAdjacencyListDiGraph<Character>();
	int maxV = 25; 
	Random rando = new Random(System.currentTimeMillis());
	
	private void smoke(){
		for (int i = 0 ; i < maxV; i++){
			graph.addVertex(tc(i));
		}
		for (char c:graph.getVertices()){
			System.out.println(c);
			for(Character c2:graph.getAdjacentVertices(c)){
				System.out.println(c2);
			}
		}
		addRandomEdges(50);
		for(DirectionalEdge<Character> de: graph.getEdges()){
			System.out.println(de.getSource()+" "+de.getSink());
		}
		System.out.println();
		graph.removeVertex(tc(3));
		for(DirectionalEdge<Character> de: graph.getEdges()){
			System.out.println(de.getSource()+" "+de.getSink());
		}
		for (char c:graph.getVertices()){
			System.out.print(c+": ");
			for(Character c2:graph.getOutgoingVertices(c)){
				System.out.print(c2+" ");
			}
			System.out.println();
		}
		
		
	}
	
	private void addRandomEdges(int n){
		int j,k;
		for (int i = 0 ; i<n;i++){
			j = rando.nextInt(maxV);
			k = rando.nextInt(maxV);
			graph.addEdge(tc(j), tc(k));
		}
	}
	
	private char tc(int i){
		return (char) (i+65);
	}
	
	public static void main(String args[]){
		DiGraphTest test = new DiGraphTest();
		test.smoke();
	}
	
	
}
