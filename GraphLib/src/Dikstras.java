import java.util.ArrayList;

public class Dikstras {
	public static void main(String args[]) {
		AbstractAdjacencyListGraph<Character,Integer> alg = new AbstractAdjacencyListGraph<Character,Integer>();
		ArrayList<Character> chars = new ArrayList<Character>();
		for (int i =0 ; i < 8 ; i++ ) {
			chars.add((char) (i+65));
		}
		alg.addVertices(chars);
		for (WeightedEdge we: getEdges()) {
			System.out.println(we.getSource() + " "+ we.getSink() +" "+ we.getWeight());
		}
		alg.addEdge(getEdges());
		
		
	}
	
	private static ArrayList<WeightedEdge<Character,Integer>> getEdges(){
		 ArrayList<WeightedEdge<Character,Integer>> ret = new  ArrayList<WeightedEdge<Character,Integer>>();
		 ret.add(new WeightedEdge<Character,Integer>('A','B',4));
		 ret.add(new WeightedEdge<Character,Integer>('A','E',7));
		 ret.add(new WeightedEdge<Character,Integer>('A','D',2));
		 ret.add(new WeightedEdge<Character,Integer>('B','E',2));
		 ret.add(new WeightedEdge<Character,Integer>('C','E',4));
		 ret.add(new WeightedEdge<Character,Integer>('D','G',1));
		 ret.add(new WeightedEdge<Character,Integer>('D','H',4));
		 ret.add(new WeightedEdge<Character,Integer>('G','H',2));
		 ret.add(new WeightedEdge<Character,Integer>('H','E',5));
		 ret.add(new WeightedEdge<Character,Integer>('H','F',1));
		 ret.add(new WeightedEdge<Character,Integer>('E','F',2));
		 ret.add(new WeightedEdge<Character,Integer>('F','C',1));
		 return ret;
		 
	}
}
