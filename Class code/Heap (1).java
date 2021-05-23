package heaps;

import java.util.ArrayList;
import java.util.Random;

public class Heap<E> {
	// data fields
	private ArrayList<E> data;
	
	Heap(int capacity) {
		data = new ArrayList<>(capacity);
	}
	
	public void add(E item) {
		
	}
	
	public void remove() {
		
	}
	
	public E min() {
		if (data.size()==0) {
			throw new IllegalStateException("min: heap is empty");
		}
		return data.get(0);
	}
	
	public String toString() {
		return data.toString();
	}
	
	public static void main(String[] args) {
		Heap<Integer> h = new Heap<>(50);
		Random r = new Random();
		
		for (int i=0; i<20; i++) {
		    h.add(r.nextInt(100));
		}
		
		System.out.println(h);
		
	
	}
	
}
