package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import interfaces.Edge;
import main.SimpleEdge;

public class RecipricalTest {
	ArrayList<Edge<Integer>> edges = new ArrayList<>();
	Random rando;
	
	RecipricalTest(){
		rando = new Random(System.nanoTime());
	}
	
	private List<Edge<Integer>> fillList(List<Edge<Integer>> edges, int n){
		return this.fillList(edges, n, n);
	}
	private List<Edge<Integer>> fillList(List<Edge<Integer>> edges, int n, int range ){
		int j,k;
		for(int i =0;i<n;i++){
			j = rando.nextInt(range);
			k = rando.nextInt(range);
			edges.add(new SimpleEdge<Integer>(j,k));
		}
		return edges;
	}
	private void print(List<Edge<Integer>> edges){
		for(Edge<Integer> e: edges){
			print(e);
		}
	}
	private List<Edge<Integer>> checkRec(List<Edge<Integer>> edges){
		LinkedList<Edge<Integer>> ret = new LinkedList<Edge<Integer>>();
		for(int i = 0 ; i<edges.size()-1;i++){
			println(edges.get(i));
			for(int j = 1; j<edges.size();j++){
				if(edges.get(i).isReciprical(edges.get(j))){
					ret.add(edges.get(i));
					ret.add(edges.get(j));
				}
				System.out.print(edges.get(i).isReciprical(edges.get(j))+": ");
				println(edges.get(j));
			}
		}
		return ret;
	}
	private void println(Edge<Integer> e){
		System.out.println(e.getVertices().get(0)+" "+e.getVertices().get(1));
	}
	private void print(Edge<Integer> e){
		System.out.print(e.getVertices().get(0)+" "+e.getVertices().get(1));
	}
	public static void main(String args[]){
		RecipricalTest test = new RecipricalTest();
		test.smoke();
	}
	
	private void smoke(){
		print(fillList(edges,150,15));
		List<Edge<Integer>> results = checkRec(edges);
		System.out.println("TOTAL: "+results.size()/2);
		int i = 0, j =0;
		LinkedList<Integer> csums = new LinkedList<>();
		for(Edge<Integer> e:results){
			print(e);
			if(i%2!=0){
				j = -(e.getVertices().get(0)+e.getVertices().get(1));
				csums.add(j);
				System.out.println();
			}
			else{
				System.out.print(" <-> ");
				j = (e.getVertices().get(0)+e.getVertices().get(1));
				csums.add(j);
			}
			i++;
		}
		i = 0;
		for(Integer k: csums){
			if (k>=0){
				System.out.print(" ");
			}
			System.out.println(k+" "+(i+=k));
		}
		

	}
	
	
}
