/* 	Shlomo Stept
 * "I pledge my honor that I have abided by the Stevens Honor System"
 *  
 *  CS284: Lecture A; Recitation A
 *  
 *  Homework 3: Rolodex
 */

package rolodex;

import java.util.ArrayList;
import java.util.Arrays;

public class Rolodex {

//---------------------------------------------------------------------------------------------------------------------
	// Data Fields
//---------------------------------------------------------------------------------------------------------------------
	private Entry cursor;
	private final Entry[] index;

//---------------------------------------------------------------------------------------------------------------------
	//Constructors
//---------------------------------------------------------------------------------------------------------------------

		//  --> Constructor # 1
			/* -> Create an array  Entry[] with Separators A-Z, where each separator connects
			 			to the separator (A) before and (B) After, (note: z connects to A)
					by:
				(0) initializing the array of type Entry with size { 26 }
				(1) Then connect all the independent Separator  by:
						first linking the "Edge-Case" separator (A)
						then linking all the separators to the previous and next separators
						Lastly linking the final "Edge-Case" separator (Z)
				(2) then linking each entry to the previous and next entry
					(note that the linking of separators is different than linking all Entry objects)
		*/
	//-------------------------------------------------------------------------------------
	Rolodex() {
	    super();
			// Step ----> (0)
	    this.index = new Entry[26];


			// Step ----> (1)
		this.index[0] = new Separator(index[25], index[1], 'A');

		for (int i =1; i<25; i++)
		{
			char[] Alphabet = new char[]{'A','B', 'C', 'D', 'E','F', 'G', 'H', 'I', 'J','K', 'L', 'M', 'N', 'O','P', 'Q', 'R', 'S', 'T','U', 'V', 'W', 'X', 'Y', 'Z'};
			this.index[i] = new Separator(index[i-1], index[i+1],Alphabet[i] );
		}

		this.index[25] = new Separator(index[24], index[0], 'Z');

		// Step ----> (2)
		Entry current = index[0];
		for (int i =1; i<26; i++)
		{
			current.next = index[i];
			current= current.next;
		}
		current.next = index[0];

	}


//---------------------------------------------------------------------------------------------------------------------
	// Methods: Part 1 : Rolodex operations
//---------------------------------------------------------------------------------------------------------------------

		//  --> 1. contains
	//-------------------------------------------------------------------------------------
	/**
	 * contains is a method used to Check if a given name currently has a card in the Rolodex
	 * @param name The name to search for
	 * @return The true if the name has a card in the Rolodex, and false if it does not
	 */
	public Boolean contains(String name) {
		Entry current = this.index[0];

		while (current.next != this.index[0]) {
			if (current.isSeparator())
			{
				current=current.next;
			}
			else
			{
				if(current.getName().equalsIgnoreCase(name))
				{
					return true;
				}
				else
				{
					current=current.next;
				}
			}
		}

		return false;
	}

//-----------------------------------------------------------------------------------------------------------------
		//  --> 2. size
	//-------------------------------------------------------------------------------------
	/**
	 * size is a method used to get the size/number of cards in a Rolodex
	 * @return The number of individual cards in the Rolodex (note: Separators are ignored)
	 */
	public int size() {
		Entry current = this.index[0];
		int count = 0;
		while (current.next != this.index[0]) {
			if (current.isSeparator())
			{
				current=current.next;
			}
			else
			{
				count++;
				current=current.next;
			}
		}

		return count;
	}

//-----------------------------------------------------------------------------------------------------------------
		//  --> 3. lookup
	//-------------------------------------------------------------------------------------
	/**
	 * lookup is a method used to determine the numbers associated with a given name
	 * @param name The name to lookup associated numbers
	 * @return The numbers associated with the given name
	 */
	public ArrayList<String> lookup(String name) {

		if (!contains(name)) {
			throw new IllegalArgumentException("lookup: Name not Found");
		}

		ArrayList<String> lookup = new ArrayList<String>();

		Entry current = this.index[0];
		while (current.next != this.index[0]) {
			if (current.isSeparator())
			{
				current=current.next;
			}
			else
			{
				if( current.getName().equalsIgnoreCase(name) )
				{
					Card tempcell = (Card) current;
					lookup.add(tempcell.getCell());
				}
				current=current.next;
			}
		}

		return lookup;
	}

//-----------------------------------------------------------------------------------------------------------------
		//  --> 4. addCard
	//-------------------------------------------------------------------------------------
	/**
	 * addCard is a method used to add a card to the Rolodex
	 * @param name The name to add to the Rolodex, in combination with
	 * @param cell The Cell to add to the Rolodex
	 */
	public void addCard(String name, String cell) {

			// --> (1) select the proper separator to start from
		int separatorNum = getSeparatorNumber(name);
		int next_index = separatorNum+1;
		if (separatorNum + 1 == 26)
		{
			next_index = 0;
		}

			// --> (2) make sure the card doesn't already exist
		Entry current1 = this.index[separatorNum].next;
		while (current1 != this.index[next_index] )
		{
			Card test = (Card) current1;
			if (test.getCell() ==  cell)
			{
				throw new IllegalArgumentException("addCard: duplicate entry");
			}
			current1 = current1.next;
		}

			// --> (3) Add the card in the right place
		Entry current = this.index[separatorNum];
		while (!current.next.isSeparator() && !ISNameBefore(name,current.next.getName()) )
		{
				current = current.next;
		}
		current.next = new Card(current, current.next, name, cell);
	}



//-----------------------------------------------------------------------------------------------------------------
		//  --> 5. removeCard
	//-------------------------------------------------------------------------------------
	/**
	 * removeCard A method used to Remove A/the card, with the input name & cell (number), from the Rolodex
	 * @param name The name of the 1 card to remove, from the Rolodex
	 * @param cell The Cell of the 1 card to remove, from the Rolodex
	 *             Note: Both the Cell and name must match for the card to be removed
	 */
	public void removeCard(String name, String cell) {

			// --> (1) select the proper separator to start from
		int separatorNum = getSeparatorNumber(name);
		int next_index = separatorNum+1;
		if (separatorNum + 1 == 26)
		{
			next_index = 0;
		}

			// --> (2) Check to see if the card with the name exists
		if (!this.contains(name) )
		{
			throw new IllegalArgumentException("removeCard:  name does not exist");
		}

			// --> (3) Find the right card,
				// -->  or if the card name exist but the number does not throw an illegal argument
		boolean listContainsCell = false;
		Entry current1 = this.index[separatorNum];
		while (current1.next != this.index[next_index] && !current1.next.isSeparator())
		{


			Card test = (Card) current1.next;
			if (test.getCell() ==  cell && test.getName().equalsIgnoreCase(name))
			{
				current1.prev = current1.prev.prev;
				current1.next = current1.next.next;
				listContainsCell = true;
			}
			current1 = current1.next;
		}
		if(!listContainsCell)
		{
			throw new IllegalArgumentException("removeCard: Cell for that name does not exist");
		}
	}



//-----------------------------------------------------------------------------------------------------------------
		//  --> 6. removeAllCards
	//-------------------------------------------------------------------------------------
	/**
	 * removeAllCards A method used to Remove All cards, Associated with the input name, from the Rolodex
	 * @param name The name on the card's to remove, from the Rolodex
	 */
	public void removeAllCards(String name) {

			// --> (1) Obtain the proper separator ('s index number) to start from
		int separatorNum = getSeparatorNumber(name);
		int next_index = separatorNum+1;
		if (separatorNum + 1 == 26)
		{
			next_index = 0;
		}

			// --> (2) Check to see if the card with the name exists
		if (!this.contains(name) )
		{
			throw new IllegalArgumentException("removeAllCard:  name does not exist");
		}

			// --> (3) Find the right card, or if the card name exist but the number does not throw an illegal argument
		Entry current1 = this.index[separatorNum];
		while (current1.next != this.index[next_index] && !current1.next.isSeparator())
		{
			Card test = (Card) current1.next;

			if (test.getName().equalsIgnoreCase(name))
			{
				current1.next = current1.next.next;
			}
			else
			{
			current1 = current1.next;
			}

		}
	}


//-----------------------------------------------------------------------------------------------------------------
	//  --> 7. String
	//-------------------------------------------------------------------------------------
	/**
	 *	toString A toString method
	 */
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





//---------------------------------------------------------------------------------------------------------------------
	// Methods: Part 2 : Cursor operations
//---------------------------------------------------------------------------------------------------------------------


		//  --> 1. initializeCursor
	//-------------------------------------------------------------------------------------
	/**
	 * initializeCursor A method used to point/initalize the cursor the  first Separator
	 */
	public void initializeCursor() {
		cursor = index[0];
	}


//-----------------------------------------------------------------------------------------------------------------
		//  --> 2. nextSeparator
	//-------------------------------------------------------------------------------------
	/**
	 * nextSeparator A method used to point the cursor the next Separator
	 */
	public void nextSeparator() {
		while(!cursor.isSeparator())
		{
			cursor =cursor.next;
		}
	}


//-----------------------------------------------------------------------------------------------------------------
		//  --> 3. nextEntry
	//-------------------------------------------------------------------------------------
	/**
	 * nextEntry A method used to point the cursor the the next Entry
	 * 		Note: the next Entry can be a Separator or A Card
	 */
	public void nextEntry() {
		cursor =cursor.next;
	}


//-----------------------------------------------------------------------------------------------------------------
		//  --> 4. currentEntryToString
	//-------------------------------------------------------------------------------------
	/**
	 * currentEntryToString A method used to Print out the current entry, the cursor is pointing too
	 * @return A String of the Current Entry the cursor is pointing too
	 */
	public String currentEntryToString() {
		if(cursor.isSeparator())
		{
			Separator temp = (Separator) cursor;
			return "Separator -> "+temp.getLetter();
		}
		else
		{
			Card temp = (Card) cursor;
			return "Card -> " + "Name: "+temp.getName()+", Cell: "+temp.getCell();
		}

	}




//---------------------------------------------------------------------------------------------------------------------
//  ::   HELPER FUNCTIONS  ::
//---------------------------------------------------------------------------------------------------------------------


	// --> Helper function 1: ISNameBefore
	// 		--> A method to determine If One Name Is Meant to be Before the other
	//----------------------------------------------------------------------------------
	/**
	 * ISNameBefore A method used to Determine if the new-Card-name Alphabetically comes prior to the current-Card-name in a particular location
	 */
	public static boolean ISNameBefore(String newEntry, String existingEntry)
	{
		String newEntry1 = newEntry.toLowerCase();
		String existingEntry1 = existingEntry.toLowerCase();
		int test =newEntry1.compareTo(existingEntry1);
		if (test < 0)
			return true;
		else
			return false;
	}
	//---------------------------------------------------------------------------------------------------

	//---------------------------------------------------------------------------------------------------
	// --> Helper function 2 :
	// 		--> Determines the correct separator to get based on the Name input, return the index number
	//----------------------------------------------------------------------------------
	/**
	 * getSeparatorNumber A method used to Determine, the index(list) number of the Separator for a given name
	 */
	public static int getSeparatorNumber(String name)
	{
		char firstCharinName = Character.toUpperCase(name.charAt(0));
		char[] Alphabet = new char[]{'A','B', 'C', 'D', 'E','F', 'G', 'H', 'I', 'J','K', 'L', 'M', 'N', 'O','P', 'Q', 'R', 'S', 'T','U', 'V', 'W', 'X', 'Y', 'Z'};
		int separatorNum =0;
		while(firstCharinName != Alphabet[separatorNum])
		{
			separatorNum++ ;
		}
		return separatorNum;
	}
	//---------------------------------------------------------------------------------------------------



//---------------------------------------------------------------------------------------------------------------------
//  ::   MAIN   ::
//---------------------------------------------------------------------------------------------------------------------

	public static void main(String[] args) {

		Rolodex r = new Rolodex ();
		System.out.println(r);

		/*Rolodex r = new Rolodex ();
		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");
		r.addCard("Cris", "5");
		r.addCard("Maddie", "23");
		System.out.println(r);*/


		/*Rolodex r = new Rolodex ();
		r.addCard("Chloe", "123");
		r.addCard("Chad", "23");
		r.addCard("Cris", "3");
		r.addCard("Cris", "4");
		r.addCard("Cris", "5");
		r.addCard("Maddie", "23");
		//r.removeAllCards("CriS");
		//System.out.println(r);
		r.addCard("Zander", "5");
		r.addCard("Arronris", "5");
		System.out.println(r);*/

		/*Rolodex r = new Rolodex ();
		r.initializeCursor();
		for (int i=0; i<12; i++) {
			System.out.println(r.currentEntryToString());
			r.nextEntry();
		}
		*/


	}


}
