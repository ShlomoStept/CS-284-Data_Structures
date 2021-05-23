package lists;

import java.util.Arrays;

public class MyList<E> {
	// data fields
	private E[] data;
	private int free;
	
	public MyList(int size) {
		super();
		this.data = ((E[]) new Object[size]);
		this.free = 0;
	}

	// Methods
	/**
	 * Inserts an element at the beginning of the list
	 * @param item The element to insert
	 * @throws IllegalStateException If the list is full
	 */
	public void addFirst(E item) {
		addAt(item,0);
	}
	
	/**
	 * Inserts the specified item at the specified index
	 * @param item Element to be inserted
	 * @param index Index where the new element will be inserted
	 * @throws IllegalStateException If the list is full
	 * @throws IllegalArgumentException If the index is out of bounds
	 */
	public void addAt(E item, int index) {
		if (free==data.length) {
			throw new IllegalStateException("List is full");
		}
		if (index<0 || index>free) {
			throw new IllegalArgumentException("Index out of bounds");
		}
		
		for (int i=free; i>index; i--) {
			data[i] = data[i-1];
		}
		data[index]=item;
		free++;
	}
	
	public E getFirst() {
		return null;
	}
	
	public E getAt(int index) {
		return null;
	}
		
	public E removeFirst() {
		return null;
	}
	
	public E removeAt(int index) {
		return null;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("[");
		for (int i=0; i<free; i++) {
			s.append(data[i].toString()+ ",");
		}
		s.append("]");
		return s.toString();
	}
	
	
	public static void main(String[] args) {
		MyList<String> l = new MyList<>(20);
		
		l.addFirst("hello");
		l.addFirst("bye");
		l.addAt("hi", 0);
		l.addAt("howdy", 2);
		l.addAt("hey", 4);
//		l.addAt("cho", 12);
		 
		
		System.out.println(l);
	}
	
	
}
