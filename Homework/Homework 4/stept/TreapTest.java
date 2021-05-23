package Treap;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;



class TreapTest{
	
	// tring to add a null key results in an illegal argument exception 
	@Test
	void first1()
		{
		Treap testTree2 = new Treap <String>();
		testTree2.add("q",9);
		testTree2.add("t",8);
		testTree2.add("a",2);
		testTree2.add("r",0);
		testTree2.add("u",7);
		assertThrows(IllegalArgumentException.class, () -> {
			testTree2.add(null,7);
			;
		});
		try {
			testTree2.add(null,7);
			;
			fail("bad");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	
	// adding a highest order priority results in proper invariant correction
	@Test
	void second()
	{
		Treap testTree2 = new Treap <String>();
		testTree2.add("t",8);
		testTree2.add("a",2);
		testTree2.add("r",0);
		testTree2.add("u",7);
		testTree2.add("q",9);
		String S = testTree2.toString();
		assertEquals( 'q', S.charAt(7));
	}
	
	// adding a second highest order priority results in proper invariant correction
		@Test
		void secondthird()
		{
			Treap testTree = new Treap <String>();
			testTree.add(4,19);
	        testTree.add(2,31);
	        testTree.add(6,70);
	        testTree.add(1,84);
	        testTree.add(3,12);
	        testTree.add(5,83);
	        testTree.add(7,26);
			String S = testTree.toString();
			assertEquals( '8', S.charAt(55));
		}
	
	//adding a key not in the Treap returns true
	@Test
	void third()
	{
		Treap testTree2 = new Treap <String>();
		assertEquals( testTree2.add("q",9), true);
	}

	// trying to add akey already present returns false
	@Test
	void Fourth() 
	{
		
		Treap testTree2 = new Treap <String>();
		testTree2.add("q",9);
		assertEquals( testTree2.add("q",9), false);
	}

	// deleting a key that is present returns true
	@Test
	void Fifth()
	{
		Treap testTree2 = new Treap <String>();
		testTree2.add("q",9);
		assertEquals( testTree2.delete("q"), true);
			
	}


	// trying to delete a key when treap is null throws an illegal State exception
	@Test
	void sixth()
	{
		Treap testTree2 = new Treap <String>();
		assertThrows(IllegalStateException.class, () -> {
			testTree2.delete("q")
			;
		});
		try {
			testTree2.delete("q")
			;
			fail("bad");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	// trying to delete a null key throws an illegal argument exception
	@Test
	void seventh()
	{
		Treap testTree2 = new Treap <String>();
		testTree2.add("q",17);
		assertThrows(IllegalArgumentException.class, () -> {
			testTree2.delete(null);
			;
		});
		try {
			testTree2.delete(null);
			;
			fail("bad");
		} catch (Exception e) {
			assertTrue(true);
		}	
	}
	
	// trying to find a null key throws an illegal argument exception
	@Test
	void eigth()
	{
		Treap testTree2 = new Treap <String>();
		testTree2.add("q",17);
		assertThrows(IllegalArgumentException.class, () -> {
			testTree2.find(null);
			;
		});
		try {
			testTree2.find(null);
			;
			fail("bad");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	// find for a key that is not present returns false
	@Test
	void ninth()
	{
				
		Treap testTree2 = new Treap <String>();
		 testTree2.add("t",8);
	     testTree2.add("a",2);
		assertEquals( testTree2.find("q"), false);
	}
	
	// is able to find after deletion happened 
	@Test
	void tenth()
	{
				
		Treap testTree2 = new Treap <String>();
		testTree2.add("q",9);
        testTree2.add("t",8);
        testTree2.add("a",2);
        testTree2.add("r",0);
        testTree2.add("u",7);
        testTree2.delete("t");
        testTree2.add("t",8);
       
		assertEquals( testTree2.find("t"), true);
	}
	
	//find with empty tree throws illegal state exception
	@Test
	void Eleventh()
	{
		Treap testTree2 = new Treap <String>();
		assertThrows(IllegalStateException.class, () -> {
			testTree2.find("q");
			;
		});
		try {
			testTree2.find("q");
			;
			fail("bad");
		} catch (Exception e) {
			assertTrue(true);
		}	
	}

		
}

