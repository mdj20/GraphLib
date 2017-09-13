package main;
import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

import interfaces.DirectionalEdge;

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
		for(Character c:DFS(tc(1),tc(3))){
			System.out.println(c);
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
	
	private ArrayList<Character> DFS(Character start, Character target){
		ArrayList<Character> path = new ArrayList<Character>();
		ArrayList<Character> checked = new ArrayList<Character>();
		ArrayList<Character> ret = new ArrayList<>();
		if(DFS(start,target,path,checked)){
			ret= new ArrayList<Character>(path.size());
			for(int i = 0; i < path.size();i++){
				ret.add(i,path.get((path.size()-1)-i));
			}
		}
		return ret;
	}
	
	private boolean DFS(Character start, Character target, ArrayList<Character> path, ArrayList<Character> checked){
		boolean ret = false;
		if(target==start){
			path.add(start);
			ret = true;
		}
		else if (target!=start){
			checked.add(start);
			for(Character c: graph.getOutgoingVertices(start)){
				if(!checked.contains(c)){
					if(DFS(c,target,path,checked)){
						path.add(start);
						ret = true;
						break;
					}
				}
			}
		}
		return ret;
	}
}
