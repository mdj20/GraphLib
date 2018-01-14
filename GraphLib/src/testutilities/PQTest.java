package testutilities;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class PQTest {
	public static void main(String args[]) {
		PriorityQueue<TwoVal> pq = new PriorityQueue<TwoVal>();
		ArrayList<TwoVal> array = new ArrayList<>();
		for(int i = 0 ; i<10;i++) {
			TwoVal tv = new TwoVal(i,i);
			array.add(tv);
		}
	
		for(int i = 0; i<array.size();i++) {
			pq.add(array.get((array.size()-1)-i));
		}
		
		Random rando = new Random(System.currentTimeMillis());
		for(TwoVal tv:array) {
			pq.remove(tv);
			tv.comp = rando.nextInt(500);
			pq.add(tv);
		}
		
		
		
		
		while(!pq.isEmpty()) {
			TwoVal tv = pq.poll();
			System.out.println(tv.id+" "+tv.comp);
		}
		
	}
	
	static class TwoVal implements Comparable<TwoVal>{
		int comp;
		int id;
		TwoVal(int id, int comp){
			this.id = id;
			this.comp = comp;
		}
		@Override
		public int compareTo(TwoVal o) {
			// TODO Auto-generated method stub
			return this.comp-o.comp;
		}
		
	}
	
}
