package anagrams;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;



class AnagramsTest{
	
	// test 1: that the building of letterTable is done correctly
		@Test
		void test1() {
			Anagrams a = new Anagrams();
			Integer  j = (Integer)29;
			assertEquals( j, a.letterTable.get('j'));
		}
	
	//// test 2: that the building of letterTable is done correctly
		@Test
		void test2() {
			Anagrams a = new Anagrams();
			String s = "{a=2, b=3, c=5, d=7, e=11, f=13, g=17, h=19, i=23, j=29, k=31, l=37, m=41, n=43, o=47, p=53, q=59, r=61, s=67, t=71, u=73, v=79, w=83, x=89, y=97, z=101}";
			assertEquals( a.letterTable.toString(), s );
		}
		
		
		//test that two anagrams compute the same hashcode
		@Test
		void test3() {
			Anagrams a = new Anagrams();
			assertEquals(a.myHashCode("alerts"), a.myHashCode("alters"));
		
		}
		
		
		//test If myhashcode () is given a null string that an illegal argument exception is thrown
		@Test
		void test4() {
			Anagrams a = new Anagrams();
			assertThrows(IllegalArgumentException.class, () -> {
				a.myHashCode(null);
				;
			});
			try {
				a.myHashCode(null);
				;
				fail("bad");
			} catch (Exception e) {
				assertTrue(true);
			}
		}
		
		
		// Test that add word throws an error is the word is already in the anagram table 
		@Test
		void test5() {
			Anagrams a = new Anagrams();
			a.addWord("apple");
			assertThrows(IllegalArgumentException.class, () -> {
				a.addWord("apple");
				;
			});
			try {
				a.addWord("apple");
				;
				fail("bad");
			} catch (Exception e) {
				assertTrue(true);
			}
		}
		
		//  Test that add word throws an error if its given a null/ empty string
		@Test
		void test6() {
			Anagrams a = new Anagrams();
			assertThrows(IllegalArgumentException.class, () -> {
				a.addWord(null);
				;
			});
			try {
				a.addWord(null);
				;
				fail("bad");
			} catch (Exception e) {
				assertTrue(true);
			}
		}
		
		
		// test that get max entries throws an exception if the anagram table is null
		@Test
		void test7() {
			Anagrams a = new Anagrams();
			assertThrows(IllegalStateException.class, () -> {
				a.getMaxEntries();
				;
			});
			try {
				a.getMaxEntries();
				;
				fail("bad");
			} catch (Exception e) {
				assertTrue(true);
			}
		
		}
		
		
		//  Test that get max entries works correctly for the "words_alpha.txt" file 
		@Test
		void test8() {
			Anagrams a = new Anagrams();
			try {
				a.processFile("words_alpha.txt");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
			ArrayList s1 = new ArrayList<String>();
			s1 =maxEntries.get(0).getValue();
			
			assertEquals( s1.get(0),"alerts");
			
		}
		
	
		
		

		
}

