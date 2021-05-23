package rolodex;

import java.util.ArrayList;

public class Rolodex {
	private Entry cursor;
	private final Entry[] index;

	// Constructor

	Rolodex() {
	    // TODO
	}

	public Boolean contains(String name) {
	    // TODO


	}
	
	public int size() {
		    // TODO

	}

	public ArrayList<String> lookup(String name) {
		    // TODO

	}


	public String toString() {
		Entry current = index[0];

		StringBuilder b = new StringBuilder();
		while (current.next!=index[0]) {
			b.append(current.toString()+"\n");
			current=current.next;
		}
		b.append(current.toString()+"\n");		
		return b.toString();
	}




	public void addCard(String name, String cell) {
			    // TODO

	}

	public void removeCard(String name, String cell) {
			    // TODO


	}
	
	public void removeAllCards(String name) {
		    // TODO

	}

	// Cursor operations

	public void initializeCursor() {
		    // TODO

	}

	public void nextSeparator() {
		    // TODO


	}

	public void nextEntry() {
		    // TODO

	}

	public String currentEntryToString() {
			    // TODO

	}


	public static void main(String[] args) {

		Rolodex r = new Rolodex();


		System.out.println(r);

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");
		r.addCard("Cris", "5");
		//		r.addCard("Cris", "4");
		r.addCard("Maddie", "23");

		System.out.println(r);

		System.out.println(r.contains("Albert"));

		r.removeAllCards("Cris");

		System.out.println(r);

		r.removeAllCards("Chad");
		r.removeAllCards("Chloe");

		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");

		System.out.println(r);




	}

}
