/*
 * 	Shlomo Stept
 * 	CS284 Lecture A Recitation A
 *
 *  I pledge my honor that I have abided by the Stevens Honor System.
 *
 *  Homework 5: Anagrams
 */


package anagrams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



public class Anagrams {

	//---------------------------------------------------------------------------------------------------------------------
		// Data Fields: Anagrams
	//---------------------------------------------------------------------------------------------------------------------
	final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
									71, 73, 79, 83, 89, 97, 101};  // list of primes
	Map<Character,Integer> letterTable;  // map of characters -> unique prime number
	Map<Long,ArrayList<String>> anagramTable; // map of unique prime number's --> arraylist of words (corresponding to the unique prime number)



	//---------------------------------------------------------------------------------------------------------------------
		// Constructors: Anagrams
	//---------------------------------------------------------------------------------------------------------------------
	/**
	 * Anagrams is a Constructor that instantiates a new instance of Anagrams
	 * 1. builds a letter table --> a map that pairs each character in the alphabet to a unique prime number
	 * 2. created a new instance of the anagram table Hash map which
	 *  	-> (A) Maps unique prime multiples --> to Arrays of the words whose characters prime correspondants
	 *  			(multiplied together) create the unique prime Multiples
	 */
	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}


	//---------------------------------------------------------------------------------------------------------------------
		// Methods: Anagrams
	//---------------------------------------------------------------------------------------------------------------------


		//  --> 1: buildLetterTable
	//-------------------------------------------------------------------------------------
	/**
	 * buildLetterTable is a method that creates a map -- which pairs each character in the alphabet to a unique prime number
	 *    --> This is done by:
	 *    		(A) initializing the letter table map
	 *    		(B) looping through a list of primes and a character list and map them one by one
	 */
	public void buildLetterTable()
	{
		this.letterTable = new HashMap<Character,Integer>();
		Character[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		for(int i = 0; i< alphabet.length; i++)
		{
			this.letterTable.put(alphabet[i],primes[i]);
		}

	}

//-----------------------------------------------------------------------------------------------------------------
		//  --> 2:  addWord
	//-------------------------------------------------------------------------------------

	/**
	 * addWord is a method that adds a word to the anagram table, mapping it to its unique hashcode
	 *    --> Note: this method does not add duplicates, only unique values
	 *    --> this methods caries out the following process:
	 *    		// first check if An arraylist exists in the anagram table for this hashcode
	 * 				 1--> if it does not exist:
	 * 					 --A. create a new arraylist
	 * 					 --B. add the word to the arraylist
	 * 					 --C. and then in the anagram table map the new arraylist to the hashcode
	 * 				 2-> if an arraylist exists in the anagram table for this hashcode
	 * 					 --A. set old array = that arraylist
	 * 					 --B. test if the word is contained in it, .contains() and throw error if it does
	 * 					 --C. if it does not contain then add it to the arraylist and map it
	 * 	 @param s The word being added to the anagram table
	 */
	public void addWord(String s) {
		if(s==null || s.length() == 0)
		{
			throw new IllegalArgumentException("addWord: String s is null");
		}

		long sHash = myHashCode(s);
		if(anagramTable.get(sHash) != null)
		{
			ArrayList<String> oldArray = anagramTable.get(sHash);

			if(oldArray.contains(s))
			{
				throw new IllegalArgumentException("addWord: duplicate value");
			}
			else {
				oldArray.add(s);
				// do i need to do this ??
				// -------------->>>>>>>(or is it already mapped to it --->>>>>>>>>TEST
				//anagramTable.put(sHash, oldArray);
			}
		}
		else
		{
			ArrayList<String> newArray = new ArrayList<String>();
			newArray.add(s);
			anagramTable.put(sHash, newArray);
		}

	}


//-----------------------------------------------------------------------------------------------------------------
		//  --> 3: myHashCode
	//-------------------------------------------------------------------------------------
	/**
	 * myHashCode is a method that computes a unique hashcode for a given string
	 *    --> the unique hashcode is generated by:
	 *    		(A) going character by character and computing the product of each characters linked(mapped) prime number
	 *
	 * @param s The word the hashcode is generated for / from.
	 * @return The Unique hashcode for the given String s.
	 */
	public long myHashCode(String s) {

		if(s==null || s.length() ==0 )
		{
			throw new IllegalArgumentException("myHashCode: String s is null/empty");
		}

		long sHash = letterTable.get(s.charAt(0));

		for( int i=1; i< s.length(); i++ )
		{
			sHash = sHash * letterTable.get(s.charAt(i));
		}

		return sHash;
	}



//-----------------------------------------------------------------------------------------------------------------
		//  --> 4: processFile
	//-------------------------------------------------------------------------------------
	/**
	 * processFile is a method that processes the file:
	 * 		(A) accepts name of a text file containing words, and
	 * 		(B) Builds the hash table anagramTable
	 * @param s The name of the text file
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}


//-----------------------------------------------------------------------------------------------------------------
		//  --> 5: getMaxEntries
	//-------------------------------------------------------------------------------------
	/**
	 * getMaxEntries is a method that returns an Arraylist of the entries in the anagram table with the largest number of anagrams
	 *    --> Note: This process is carried out by:
	 *    		(A) going entry by entry through the anagram table
	 *    		(B) the largest number of anagrams starts at one
	 *    			(C1)  if the current entry's number of anagrams is less than the current max , go to next entry
	 *    			(C2)  if the current entry's number of anagrams is equal to the current max, add it to the max entry Arraylist
	 *    			(C3)  if the current entry's number of anagrams is greater than the current max, clear the max entry Arraylist, and add the new max to it
	 *    		(D) Once this process is complete the leading maximum(s) list is returned
	 * @return returns an Arraylist of the entries in the anagram table with the largest number
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		if(anagramTable == null || anagramTable.size() ==0)
		{
			throw new IllegalStateException("getMaxEntries: anagramTable is empty");
		}

		int max = 1;
		ArrayList<Map.Entry<Long,ArrayList<String>>> MaxEntries = new ArrayList<>();
		for (Map.Entry<Long,ArrayList<String>> temp: anagramTable.entrySet())
		{
			// case 1 - less
			  // do nothing
			// case 2 - equal
			if(temp.getValue().size() == max)
			{
				MaxEntries.add(temp);
			}
			// case 3 - greater
			if(temp.getValue().size() > max)
			{
				max = temp.getValue().size();
				MaxEntries.clear();
				MaxEntries.add(temp);
			}
		}

		return MaxEntries;
	}

	//---------------------------------------------------------------------------------------------------------------------
	//  ::   MAIN   ::
	//---------------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		Anagrams a = new Anagrams();

		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
