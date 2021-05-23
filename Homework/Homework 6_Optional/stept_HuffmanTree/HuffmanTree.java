package HuffmanTree;

import java.util.Hashtable;
import java.util.PriorityQueue;

/*
 * Instructions: 
 * First: Read through the assignment specification, make sure you understand what the assignment is asking for.
 * Second: There are number of "TODO" instructions within this code, make sure to complete all of them fully.
 * Third: Test you code.
 */


// TODO: Name and Pledge

// Pledge: I pledge my honor that I have abided by the Stevens Honor System.
// Name: Shlomo Stept


/**
 * HW6 CS284 Spring 2021
 * Implements a Huffman encoding tree.
 * The included code has been commented for the student's benefit, feel free to read through it.
 */
public class HuffmanTree {

//---------------------------------------------------------------------------------------------------------------------
	// Start --> Code for Node   // Start of Stub Code /
//---------------------------------------------------------------------------------------------------------------------

	//-----------------------------------------------------------------------------------------------------------------
			//  --> 1. Regular Node
		//-------------------------------------------------------------------------------------
    /** Node<E> is an inner class and it is abstract.
     * There will be two kinds
     * of Node, one for leaves and one for internal nodes. */
    abstract static class Node implements Comparable<Node>
	{
        /** The frequency of all the items below this node */
        protected int frequency;
        
        public Node(int freq)
		{
        	this.frequency = freq;
        }
        
		/** Needed for the Minimum Heap used later in this stub. */
		public int compareTo(Node other)
		{
			return this.frequency - other.frequency;
		}
    }


	//-----------------------------------------------------------------------------------------------------------------
			//  --> 2. LeafNode
		//-------------------------------------------------------------------------------------
    /** Leaves of a Huffman tree contain the data items */
    protected static class LeafNode extends Node
	{
        // Data Fields
        /** The data in the node */
        protected char data;
        /** Constructor to create a leaf node (i.e. no children) */
        public LeafNode(char data, int freq) {
            super(freq);
            this.data = data;
        }
        /** toString method */
        public String toString()
		{
            return "[value= "+this.data + ",freq= "+frequency+"]";
        }
    }


	//-----------------------------------------------------------------------------------------------------------------
			//  --> 2. InternalNode
		//-------------------------------------------------------------------------------------
    /** Internal nodes contain no data,
     * just references to left and right subtrees */
    protected static class InternalNode extends Node {
        /** A reference to the left child */
        protected Node left;
        /** A reference to the right child */
        protected Node right;

        /** Constructor to create an internal node */
        public InternalNode(Node leftC, Node rightC) {
            super(leftC.frequency + rightC.frequency);
            left = leftC; right = rightC;
        }
        public String toString() {
            return "(freq= "+frequency+")";
        }
    }
//---------------------------------------------------------------------------------------------------------------------
	// End ----> Code for Node  <----- End.
//---------------------------------------------------------------------------------------------------------------------

	//********************************************************************************************************************
	//********************************************************************************************************************
	//********************************************************************************************************************

//---------------------------------------------------------------------------------------------------------------------
	// Data Fields:
//---------------------------------------------------------------------------------------------------------------------
	// Enough space to encode all "extended ascii" values
	// This size is probably overkill (since many of the values are not "printable" in the usual sense)
	private static final int codex_size = 256;
	
	/* Data Fields for Huffman Tree */
	private Node root;


//---------------------------------------------------------------------------------------------------------------------
	//Constructors: Huffman Tree
//---------------------------------------------------------------------------------------------------------------------
	public HuffmanTree(String s) {
		root = buildHuffmanTree(s);
	}


//---------------------------------------------------------------------------------------------------------------------
	// Methods: Part 1 : Given Methods in the stub
//---------------------------------------------------------------------------------------------------------------------

	//  --> 1.0 frequency
	//-------------------------------------------------------------------------------------
	/**
	 * Returns the frequencies of all characters in s.
	 * @param s
	 * @return
	 */
	public static int[] frequency(String s) {
		int[] freq = new int[codex_size];
		for (char c: s.toCharArray()) {
			freq[c]++;
		}
		return freq;
	}

	//-----------------------------------------------------------------------------------------------------------------
	//  --> 2.0 buildHuffmanTree
	//-------------------------------------------------------------------------------------
	/**
	 * Builds the actual Huffman tree for that particular string.
	 * @param s
	 * @return
	 */
	private static Node buildHuffmanTree(String s) {
		int[] freq = frequency(s);
		
		// Create a minimum heap for creating the Huffman Tree
		// Note to students: You probably won't know what this data structure
		// is yet, and that is okay.
		PriorityQueue<Node> min_heap = new PriorityQueue<Node>();
		
		// Go through and create all the nodes we need
		// as in, all the nodes that actually appear in our string (have a frequency greater then 0)
		for(int i = 0; i < codex_size; i++) {
			if (freq[i] > 0) {
				// Add a new node (for that character) to the min_heap, notice we have to cast our int i into a char.
				min_heap.add(new LeafNode((char) i, freq[i]));
			}
		}
		
		// Edge case (string was empty)
		if (min_heap.isEmpty()) {
			throw new NullPointerException("Cannot encode an empty String");
		}
		
		// Now to create the actual Huffman Tree 
		// NOTE: this algorithm is a bit beyond what we cover in cs284, 
		// you'll see this in depth in cs385
		
		// Merge smallest subtrees together
		while (min_heap.size() > 1) {
			Node left = min_heap.poll();
			Node right = min_heap.poll();
			Node merged_tree = new InternalNode(left, right);
			min_heap.add(merged_tree);
			//////////System.out.println(merged_tree.toString());
		}
		
		// Return our structured Huffman Tree
		return min_heap.poll();
	}
	
	// ******************** End of Stub Code ******************** //
	// ********************************************************** //



//---------------------------------------------------------------------------------------------------------------------
	// Methods: Part 2 : Methods I implemented
//---------------------------------------------------------------------------------------------------------------------

			//  --> 1.0 bitsToString
	//-------------------------------------------------------------------------------------
	public static String bitsToString(Boolean[] encoding) {
		// -> 1. see if the array is null, and throw Exception if it is 
		if(encoding == null || encoding.length == 0 )
		{
			throw new IllegalArgumentException("bitsToString: The array entered is Empty/Null");
		}

		// -> 2. walk through the array and compute the binary representation of (true/false)
		StringBuilder s = new StringBuilder();
		for( int i = 0; i<encoding.length; i++ )
		{
			// (A) if its empty
			if(encoding[i]==null)
				continue;
			else if(encoding[i]==true)
			{
				s.append("1");
			}
			else if(encoding[i] == false)
			{
				s.append("0");
			}
		}

		return s.toString();
	}


	//-----------------------------------------------------------------------------------------------------------------
			//  --> 2: toString
			// 			 -->  test to see if its a leaf or its an Internal node
			//    			--> (A) if its a leaf then add it to the string and return it
			//   			--> (B) if its An Internal Node then send it to the helper function
		//-------------------------------------------------------------------------------------
	public String toString() {

		StringBuilder s = new StringBuilder();

		if( this.root instanceof LeafNode)
		{
			s.append( this.root.toString() );
		}
		else if ( this.root instanceof InternalNode)
		{
			toString_preorder( (InternalNode) this.root, 1, s);
		}

		return s.toString();
    }

	//-----------------------------------------------------------------------------------------------------------------
	//    ::   HELPER FUNCTIONS  ::
	//        --> 2.99 helper function for 2.
	//-------------------------------------------------------------------------------------
	public void toString_preorder(InternalNode Root, int level, StringBuilder s)
	{
		// 1 -> test to see if the Node passed in is Null
		if(Root == null)
		{
			s.append("\n");
			s.append(indentation(level));
			s.append("null");
		}

		// 2 -> if its not then add it to the Append it to the StringBuilder
		else{

			s.append("\n");
			s.append(indentation(level));
			s.append(Root.toString() );

			// 2.5 ->  test to see if its a leaf or its an Internal node
				// --> (A) if its an internal node then send to to the tostring_preorder()
				// --> (B) if its not then Add it to the StringBuilder
			Node left = Root.left;
			Node right = Root.right;
			if(left instanceof InternalNode )
			{
				toString_preorder((InternalNode) left, level +1, s  );
			}
			else if (left instanceof LeafNode)
			{
				s.append("\n");
				s.append(indentation(level+1));
				s.append(left.toString());
			}

			if(right instanceof InternalNode )
			{
				toString_preorder((InternalNode)right, level +1, s  );
			}
			else if(right instanceof LeafNode)
			{
				s.append("\n");
				s.append(indentation(level+1));
				s.append(right.toString());

			}
		}
	}

	//--------
	//    ::   HELPER FUNCTIONS  ::
	//        --> 2.995 helper function for 2.
	//-------------------------------------------------------------------------------------
	public String indentation(int Level)
	{
		StringBuilder Spaces = new StringBuilder();
		for( int i = 1; i< Level; i++)
		{
			Spaces.append("  ");
		}
		return Spaces.toString();
	}
	
	
	

	//-----------------------------------------------------------------------------------------------------------------
			//  --> 3: decode
		//-------------------------------------------------------------------------------------
	
	public String decode(Boolean[] coding) {
		if(coding==null)
			throw new IllegalArgumentException("decode: array is null");

		if(this.root == null)
			throw new IllegalArgumentException("decode: the root is null");

		String code = bitsToString(coding);

		// WHAT HAPPENS IF THE THIS>ROOT IS A LEAF!!!!!!!!!!!!!
		// ----> ask the CA!!!!!
		if(isLeaf(this.root))
			throw new IllegalArgumentException("decode: Invalid encoding");


		InternalNode currentInternal = (InternalNode) this.root;
		StringBuilder decoded = new StringBuilder();
		int Index_Counter = 0;

		while(Index_Counter < code.length())
		{
			// case 1: go to the left and left is not a leaf
			if(code.charAt(Index_Counter) == '0' && !isLeaf(currentInternal.left) )
			{
				currentInternal = (InternalNode) currentInternal.left;
				Index_Counter++;
			}
			// case 2: go to the left and left is a leaf
			else if(code.charAt(Index_Counter) == '0' && isLeaf(currentInternal.left))
			{
				LeafNode next_char = (LeafNode) currentInternal.left;
				decoded.append(next_char.data);
				currentInternal = (InternalNode) this.root;
				Index_Counter++;
			}

			// case 3: go to the right and right is not a leaf
			else if(code.charAt(Index_Counter) == '1' && !isLeaf(currentInternal.right) )
			{
				currentInternal = (InternalNode) currentInternal.right;
				Index_Counter++;
			}
			// case 2: go to the right and right is a leaf
			else if(code.charAt(Index_Counter) == '1' && isLeaf(currentInternal.right))
			{
				LeafNode next_char = (LeafNode) currentInternal.right;
				decoded.append(next_char.data);
				currentInternal = (InternalNode) this.root;
				Index_Counter++;
			}
		}
		return decoded.toString();
	}

	//-----------------------------------------------------------------------------------------------------------------
	//    ::   HELPER FUNCTIONS  ::
	//        --> 3.99 helper function for 3.
	//-------------------------------------------------------------------------------------
	public Boolean isLeaf(Node Test)
	{
		if(Test == null)
			throw new IllegalArgumentException("decode: Invalid Encoding");
		if( Test instanceof LeafNode) {
			//System.out.println(true);
			return true;
		}
		else {
			//System.out.println(false);
			return false;
		}
	}



	//-----------------------------------------------------------------------------------------------------------------
			//  --> 4: encode: Naive Method
			// 		--> A. search tree for letter
			// 		--> B. send back the encoding "o and 1's to get to the letter
			// 		--> C. go letter by letter an d do this
			// 		--> D. then use a new helper function to turn the bits ('o0' and "1"'s into an array of true/false
		//-------------------------------------------------------------------------------------
	public Boolean[] encode(String inputText) {
		if(inputText == null)
			throw new IllegalArgumentException("encode: string is null");

		StringBuilder word_encoding = new StringBuilder();

		for(int i =0; i< inputText.length(); i++)
		{
			StringBuilder realpath = new StringBuilder();
			getEncoding(this.root, inputText.charAt(i), "", "", realpath);

			// test to see if the letter was in the huffman tree
			if(realpath.length()==0)
				throw new IllegalArgumentException("encode: letter not in tree");

			word_encoding.append( realpath.toString() );
		}
		Boolean[] encodings;
		encodings = bitsToBoolean(word_encoding.toString());
		return encodings;

	}

	//-----------------------------------------------------------------------------------------------------------------
	//    ::   HELPER FUNCTIONS  ::
	//        --> 4.99 helper function for 4.
	//-------------------------------------------------------------------------------------
	public void getEncoding(Node Root, char seed, String pathSoFar, String Path, StringBuilder realpath )
	{
		// (A) test if root is null
		if(Root == null)
			throw new IllegalArgumentException("encode: root is null");

		// (B) add the past move ( ie if if the current root was right or left, from its parent)
		StringBuilder a = new StringBuilder();
		a.append(pathSoFar);
		if(Path == "right")
			a.append("1");
		else if(Path == "left")
			a.append("0");

		// (C) if Root is a leaf and its value is what we are looking for set the real path = to the path we took to get here
		if (Root instanceof LeafNode)
		{
			LeafNode Test = (LeafNode) Root;
			if(Test.data == seed)
			{
				realpath.append(a);
			}
		}
		// (D) if the root is an internal node, go left and right
		else if(Root instanceof InternalNode)
		{
			InternalNode Next = (InternalNode) Root;
			getEncoding(Next.left, seed, a.toString(), "left", realpath);
			getEncoding(Next.right, seed, a.toString(), "right", realpath);
		}


	}

	//-----------------------------------------------------------------------------------------------------------------
	//    ::   HELPER FUNCTIONS  ::
	//        -->  helper function for 4 and 5.
	//-------------------------------------------------------------------------------------
	public Boolean[] bitsToBoolean(String bits)
	{
		if(bits == null)
			throw new IllegalArgumentException("Word can not be created using the letters in the huffman tree");

		Boolean[] encoded = new Boolean[bits.length()];
		for( int i =0; i< bits.length(); i++)
		{
			char C = bits.charAt(i);
			if( C == '0')
			{
				encoded[i] = false;
			}
			else if( C == '1')
			{
				encoded[i] = true;
			}
		}

		return encoded;
	}

	//-----------------------------------------------------------------------------------------------------------------
	//  --> 5: efficientEncode
	// 		--> A. search tree for letter
	// 		--> B. along the way encode all paths to letter in a hashmap
	// 		--> D. then when you look up a letter just use the hashmap
	//-------------------------------------------------------------------------------------
	public Boolean[] efficientEncode(String inputText) {
		if(inputText ==null)
			throw new IllegalArgumentException("encode: string is null");

		StringBuilder word_encoding = new StringBuilder();
		Hashtable<Character, String> huffmanTree = new Hashtable<>();
		getEncoding2(this.root, inputText.charAt(0), "", "", huffmanTree );
		
		for(int i =0; i< inputText.length(); i++)
		{
			// test to see if the letter was in the huffman tree
			Character next = (Character) inputText.charAt(i);
			if(!huffmanTree.containsKey(next))
				throw new IllegalArgumentException("encode: letter not in tree");
			else
				word_encoding.append( huffmanTree.get(next) );
		}

		Boolean[] encodings;
		encodings = bitsToBoolean(word_encoding.toString());
		return encodings;
	}
	//-----------------------------------------------------------------------------------------------------------------
	//    ::   HELPER FUNCTIONS  ::
	//        --> 4.99 helper function for 4.
	//-------------------------------------------------------------------------------------
	public void getEncoding2(Node Root, char seed, String pathSoFar, String Path, Hashtable huffmanTree )
	{
		// (A) test if root is null
		if(Root == null)
			throw new IllegalArgumentException("encode: root is null");

		// (B) add the past move ( ie if if the current root was right or left, from its parent)
		StringBuilder a = new StringBuilder();
		a.append(pathSoFar);
		if(Path == "right")
			a.append("1");
		else if(Path == "left")
			a.append("0");

		// (C) if Root is a leaf and its value is what we are looking for set the real path = to the path we took to get here
		if (Root instanceof LeafNode)
		{
			LeafNode Test = (LeafNode) Root;

			huffmanTree.put(Test.data, a.toString());

		}
		// (D) if the root is an internal node, go left and right
		else if(Root instanceof InternalNode)
		{
			InternalNode Next = (InternalNode) Root;
			getEncoding2(Next.left, seed, a.toString(), "left", huffmanTree);
			getEncoding2(Next.right, seed, a.toString(), "right", huffmanTree);
		}
	}

	
	
	//---------------------------------------------------------------------------------------------------------------------
	//  ::   MAIN   ::
	//---------------------------------------------------------------------------------------------------------------------

		public static void main(String[] args) {
			// Code to see if stuff works...
			String s = "This string is used as a basis for setting up the Huffman tree";
			HuffmanTree t = new HuffmanTree(s); // Creates specific Huffman Tree for "s"
			// Now you can use encode, decode, and toString to interact with your specific Huffman Tree
			System.out.println(t);
	}
}
