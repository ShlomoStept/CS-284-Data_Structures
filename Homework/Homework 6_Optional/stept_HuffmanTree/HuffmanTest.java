package HuffmanTree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HuffmanTest {

	// test 1: Tostring is done correctly 
		@Test
		void test1() {
			String s = "This string is used as a basis for setting up the Huffman tree";
			HuffmanTree t = new HuffmanTree(s);
			String S = "\n"
					+ "(freq= 62)\n"
					+ "  (freq= 25)\n"
					+ "    [value=  ,freq= 12]\n"
					+ "    (freq= 13)\n"
					+ "      (freq= 6)\n"
					+ "        (freq= 3)\n"
					+ "          [value= p,freq= 1]\n"
					+ "          (freq= 2)\n"
					+ "            [value= m,freq= 1]\n"
					+ "            [value= o,freq= 1]\n"
					+ "        [value= u,freq= 3]\n"
					+ "      (freq= 7)\n"
					+ "        [value= r,freq= 3]\n"
					+ "        (freq= 4)\n"
					+ "          (freq= 2)\n"
					+ "            [value= T,freq= 1]\n"
					+ "            [value= d,freq= 1]\n"
					+ "          [value= h,freq= 2]\n"
					+ "  (freq= 37)\n"
					+ "    (freq= 16)\n"
					+ "      [value= s,freq= 8]\n"
					+ "      (freq= 8)\n"
					+ "        (freq= 4)\n"
					+ "          [value= g,freq= 2]\n"
					+ "          (freq= 2)\n"
					+ "            [value= H,freq= 1]\n"
					+ "            [value= b,freq= 1]\n"
					+ "        [value= a,freq= 4]\n"
					+ "    (freq= 21)\n"
					+ "      (freq= 10)\n"
					+ "        [value= e,freq= 5]\n"
					+ "        [value= i,freq= 5]\n"
					+ "      (freq= 11)\n"
					+ "        [value= t,freq= 5]\n"
					+ "        (freq= 6)\n"
					+ "          [value= f,freq= 3]\n"
					+ "          [value= n,freq= 3]";
			assertEquals( t.toString(), S);
		}
		
	//// test 2: bitsToString works correctly when given a array of boolean values 
		@Test
		void test2() {
			String s = "This string is used as a basis for setting up the Huffman tree";
			HuffmanTree t = new HuffmanTree(s);
			Boolean[] test = {false, true, true};
			String result = t.bitsToString(test);
			assertEquals( result, "011" );
			}
			
		//bitsToString ignores null values within the array, provided the array itself is not null
		@Test
		void test3() {
			String s = "This string is used as a basis for setting up the Huffman tree";
			HuffmanTree t = new HuffmanTree(s);
			Boolean[] test = {false, true,null, true};
			String result = t.bitsToString(test);
			assertEquals( result, "011" );
			}
			
			
		//bitsToString throws an error when given an null array
		@Test
		void test4() {
			String s = "This string is used as a basis for setting up the Huffman tree";
			HuffmanTree t = new HuffmanTree(s);
			assertThrows(IllegalArgumentException.class, () -> {
				t.bitsToString(null);
				;
			});
			try {
				t.bitsToString(null);
				;
				fail("bad");
				} catch (Exception e) {
				assertTrue(true);
			}
		}
			
			
		// naive encoding works correctly 
		@Test
		void test5() {
			String s = "This string is used as a basis for setting up the Huffman tree";
			HuffmanTree t = new HuffmanTree(s);
			String result = t.bitsToString(t.encode("hinge"));
			assertEquals( result, "01111110111111101001100" );
			}
	
			
			//  naive encoding throws an error when the string is null
			@Test
			void test6() {
				String s = "This string is used as a basis for setting up the Huffman tree";
				HuffmanTree t = new HuffmanTree(s);
				assertThrows(IllegalArgumentException.class, () -> {
					t.encode(null);
					;
				});
				try {
					t.encode(null);
					;
					fail("bad");
				} catch (Exception e) {
					assertTrue(true);
				}
			}
			
			
			// naive encoding throws an error when one of the letters is not in the tree 
			@Test
			void test7() {
				String s = "This string is used as a basis for setting up the Huffman tree";
				HuffmanTree t = new HuffmanTree(s);
				assertThrows(IllegalArgumentException.class, () -> {
					t.encode("zinge");
					;
				});
				try {
					t.encode("zinge");
					;
					fail("bad");
				} catch (Exception e) {
					assertTrue(true);
				}
			
			}
			
			// efficent encoding works correctly 
			@Test
			void test13() {
				String s = "This string is used as a basis for setting up the Huffman tree";
				HuffmanTree t = new HuffmanTree(s);
				String result = t.bitsToString(t.efficientEncode("hinge"));
				assertEquals( result, "01111110111111101001100" );
				}
		
			
			//  Efficient encoding throws an error when the string is null
			@Test
			void test8() {
				String s = "This string is used as a basis for setting up the Huffman tree";
				HuffmanTree t = new HuffmanTree(s);
				assertThrows(IllegalArgumentException.class, () -> {
					t.efficientEncode(null);
					;
				});
				try {
					t.efficientEncode(null);
					;
					fail("bad");
				} catch (Exception e) {
					assertTrue(true);
				}
				
			}
			
			// Efficient encoding throws an error when one of the letters is not in the tree 
			@Test
			void test9() {
				String s = "This string is used as a basis for setting up the Huffman tree";
				HuffmanTree t = new HuffmanTree(s);
				assertThrows(IllegalArgumentException.class, () -> {
					t.efficientEncode("zinge");
				;
				});
				try {
					t.efficientEncode("zinge");
				;
				fail("bad");
				} catch (Exception e) {
				assertTrue(true);
				}
						
				}
			
			
			// decode works proprly 
			@Test
			void test10() {
				String s = "This string is used as a basis for setting up the Huffman tree";
				HuffmanTree t = new HuffmanTree(s);
				Boolean [] c = {true, true, false, true, true, false, true, false,false, true, true, true, true, true, true, true, false, true, true, true, true, false, true, true, false , false };
				String result = t.decode(c);
				assertEquals( result, "ignite");
				}
			

			// decode throws an illegal argument exception when the input is not a valid encoding 
			@Test
			void test11() {
				String s = "aaaaa";
				HuffmanTree t = new HuffmanTree(s);
				Boolean [] c = {true, true, false, true, true, false, true, false,false, true, true, true, true, true, true, true, false, true, true, true, true, false, true, true, false , false };
				assertThrows(IllegalArgumentException.class, () -> {
					t.decode(c);
				;
				});
				try {
					t.decode(c);
				;
				fail("bad");
				} catch (Exception e) {
				assertTrue(true);
				}
						
				}			
			
				

}
