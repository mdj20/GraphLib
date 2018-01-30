package traverse;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Random;
import java.util.Set;

import interfaces.WeightedDirectionalEdge;
import main.WeightedAdjacencyListDiGraph;
import main.WeightedAdjacencyListGraph;
import testutilities.FastGraphBuilder;
import testutilities.TestGraphData;

public class WeightedPathFindeSmokerTest {
	
	
	static WeightedAdjacencyListDiGraph<Character, Integer> graph0;
	static WeightedAdjacencyListDiGraph<Character, Integer> graph1;
	static int nVertex = 25;
	static int nEdge = 50;
	static int lowerWeight = 1;
	static int upperWeight = 15;
	static Random rando = new Random(System.nanoTime());

	
	public static void main(String args[]) throws FileNotFoundException, UnsupportedEncodingException{
		
		
		//Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("false.txt", "UTF-8")))
		//PrintWriter pw = new PrintWriter("false.txt", "UTF-8");
		
		
		Class<Character> charclass = Character.class;
		
		graph0 = FastGraphBuilder.getWeightedDiGraph(TestGraphData.TestGraph0);
		graph1 = FastGraphBuilder.getWeightedDiGraph(TestGraphData.TestGraph1);
		WeightedPathFinder<WeightedAdjacencyListDiGraph<Character,Integer>,
			WeightedDirectionalEdge<Character,Integer>,Character,
			Integer> 
			pathfinder0 = WeightedPathFinder.getWeightedPathFinder(graph0);
		
		WeightedPathFinder<WeightedAdjacencyListDiGraph<Character,Integer>,
			WeightedDirectionalEdge<Character,Integer>,
			Character,
			Integer> 
			pathfinder1 = WeightedPathFinder.getWeightedPathFinder(graph1);
		
		Path<Character,WeightedDirectionalEdge<Character,Integer>> dikpath0 = pathfinder0.dikstrasIntPath('C', 'A');
		Path<Character,WeightedDirectionalEdge<Character,Integer>> bmpath0 = pathfinder0.bellmanFordIntPath('C', 'A');
		boolean equals = dikpath0.getEdgeList().equals(bmpath0.getEdgeList());
		System.out.println(equals);
		
		WeightedAdjacencyListDiGraph<Character, Integer> randograph0 = FastGraphBuilder.buildRandomDiGraph(nVertex, nEdge, lowerWeight, upperWeight);
		
		WeightedPathFinder<WeightedAdjacencyListDiGraph<Character,Integer>,
		WeightedDirectionalEdge<Character,Integer>,Character,
		Integer> 
		randoPathFinder = WeightedPathFinder.getWeightedPathFinder(randograph0);
		
		 
		Path<Character,WeightedDirectionalEdge<Character,Integer>> randoDPath = randoPathFinder.dikstrasIntPath('C', 'A');
		Path<Character,WeightedDirectionalEdge<Character,Integer>> randoBMPath = randoPathFinder.bellmanFordIntPath('C', 'A');
		equals = randoDPath.getEdgeList().equals(randoBMPath.getEdgeList());
		System.out.println(equals);
		
		boolean master = true;
		
		for(int i = 0 ; i < 10000 ; i++){
			randograph0 = FastGraphBuilder.buildRandomDiGraph(nVertex, nEdge, lowerWeight, upperWeight);
			randoPathFinder = WeightedPathFinder.getWeightedPathFinder(randograph0);
			Character source = 'C', sink = 'A';
			Set<Character> verts = randograph0.getVertices();
			int sourceIndex = rando.nextInt(verts.size());
			int sinkIndex = rando.nextInt(verts.size());
			while(sourceIndex == sinkIndex){
				sourceIndex = rando.nextInt(verts.size());
				sinkIndex = rando.nextInt(verts.size());
			}
			int j=0;
			for(Character c : verts){
				if(j==sourceIndex){
					source = c;
				}
				if(j == sinkIndex){
					sink = c;
				}
				j++;
			}
			System.out.println(i+": "+source  +" -> "+sink);
			randoDPath = randoPathFinder.dikstrasIntPath(source,sink);
			randoBMPath = randoPathFinder.bellmanFordIntPath(source, sink);
			equals = randoDPath.getEdgeList().equals(randoBMPath.getEdgeList());
			
			System.out.println(equals);
			;
			if(!equals){
				//pw.println(i);
				int sumd = 0, sumb = 0;
				for(WeightedDirectionalEdge<Character,Integer> wde : randoDPath.getEdgeList()){
					sumd+=wde.getWeight();
				}
				for(WeightedDirectionalEdge<Character,Integer> wde : randoBMPath.getEdgeList()){
					sumb+=wde.getWeight();
				}
				master = master && (sumd == sumb);
				/* commenting out print to file for development purposes.
				 * printToFile(pw,randograph0,randoDPath,randoBMPath);
				 */
			}
			
			
		}
		
		System.out.println("Master "+master);
		//pw.close();
		
		
	}
	
	public static void printToFile(PrintWriter pw,
			WeightedAdjacencyListDiGraph<Character,Integer> graph,
			Path<Character,WeightedDirectionalEdge<Character,Integer>> pathD,
			Path<Character,WeightedDirectionalEdge<Character,Integer>> pathBM){
		for(Character c: graph.getVertices()){
			pw.print(c+", ");
		}
		pw.println();
		pw.println();
		for(WeightedDirectionalEdge<Character,Integer> wde: graph.getEdges()){
			pw.println(wde.getVertex(0)+" -> "+wde.getVertex(1)+" : "+wde.getWeight());
		}
		pw.println();
		printPathMeta(pw,pathD,"PathDik");
		printPathMeta(pw,pathBM,"PathBM ");
		printPathEdges(pw,pathD,"PathDik");
		printPathEdges(pw,pathBM,"PathBM ");
		
		pw.println();
		pw.println();
		pw.println();
		
	}
	
	public static void printPathMeta(PrintWriter pw, Path<Character,WeightedDirectionalEdge<Character,Integer>> path, String header){
		pw.println(header+": "+path.getSource()+"->"+path.getSink()+" ; SIZE: "+path.getEdgeList().size());
	}
	
	public static void printPathEdges(PrintWriter pw, Path<Character,WeightedDirectionalEdge<Character,Integer>> path, String header){
		pw.print(header+": ");
		for(WeightedDirectionalEdge<Character,Integer> wde:path.getEdgeList()){
			pw.print(wde.getSource()+" --"+wde.getWeight()+"-> "+wde.getSink()+", ");
		}
		pw.println();
	}
}
