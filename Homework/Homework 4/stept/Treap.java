/*
 * 	Shlomo Stept
 * 	CS284 Lecture A Recitation A
 * 
 * I pledge my honor that I have abided by the Stevens Honor System. 
 * 
 * Homework 4: Treap
*/


package Treap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import java.util.Stack;

public class Treap<E extends Comparable<E>> {


	//---------------------------------------------------------------------------------------------------------------------
	    // Start --> Code for Node
	//---------------------------------------------------------------------------------------------------------------------
	    private static class Node<E extends Comparable<E>>
	    {
	        //---------------------------------------------------------------------------------------------------------------------
	            // Data Fields: Node
	        //---------------------------------------------------------------------------------------------------------------------
	        public E data; // key for the search
	        public int priority; // random heap priority
	        public Node <E> left;
	        public Node <E> right;

	        //---------------------------------------------------------------------------------------------------------------------
	            //Constructors: Node
	        //---------------------------------------------------------------------------------------------------------------------
	        /**
	         * Node is a Constructor that instantiates a new instance of Node
	         * 1. sets the values of the data fields: data and priority to the given
	         * 2. and the left and right nodes to null
	         */
	        public Node(E data, int priority)
	        {
	            if(data == null)
	            {
	                throw new IllegalArgumentException("Node: data is null");
	            }
	            this.data = data;
	            this.priority = priority;
	            this.left = null;
	            this.right = null;
	        }


	        //---------------------------------------------------------------------------------------------------------------------
	            // Methods: For Node
	        //---------------------------------------------------------------------------------------------------------------------

	            //  --> 1. rotateRight
	        //-------------------------------------------------------------------------------------
	        /**
	         * rotateRight is a method used to rotate the node structure
	         * --> 1. the top node is rotated to the right
	         * --> 2. the left node is placed in the top position
	         * --> 3. if there existed a right node its placed to the right side of the new right
	         * --> 4. if there existed a left.left node its placed as the new left node
	         * --> 5. if there existed a left.right node its placed to the left side of the new right
	         * @return a copy of the new node structure
	         */
	        private Node<E> rotateRight() {
	            // Case 1 no children
	            if (this.left == null) {
	                throw new IllegalArgumentException("rotateRight: left node is null;");
	            }

	            // 1. swap the data in left and root
	            E rootdata= this.data;
	            int rootpriority= this.priority;

	            this.data= left.data;
	            this.priority = left.priority;

	            left.data = rootdata;
	            left.priority = rootpriority;

	            // 2. swap left and right (make sure right exists)
	            //swap(this.left, this.right);
	            if(this.right== null)
	            {
	                this.right=this.left;
	                this.left = null;
	            }
	            else {
	                Node<E> temp = this.right;
	                this.right= this.left;
	                this.left= temp;
	            }

	            // 3. Swap Right.right and right.leftL
	            //swap(this.right.right, this.right.left);
	            if (this.right != null)
	            {
	                if(this.right.right == null && this.right.left != null)
	                {
	                    this.right.right=this.right.left;
	                    this.right.left = null;
	                }
	                else if(this.right.right != null && this.right.left == null)
	                {
	                    this.right.left=this.right.right;
	                    this.right.right = null;
	                }
	                else if(this.right.right!= null && this.right.left !=null)
	                {
	                    Node<E> temp = this.right.left;
	                    this.right.left = this.right.right;
	                    this.right.right= temp;
	                }

	            }
	            // 4. Swap right and L.L
	            //swap(this.left, this.right.right);
	            if(this.left != null && this.right.right != null)
	            {
	                Node<E> temp = this.left;
	                this.left= this.right.right;
	                this.right.right=temp;
	            }
	            else if(this.left !=null && this.right.right == null)
	            {
	                this.right.right = this.left;
	                this.left=null;
	            }
	            else if(this.left ==null && this.right.right != null)
	            {
	                this.left= this.right.right;
	                this.right.right = null;
	            }

	            // 5. create a copy of new node and send back
	            Node<E> copy = new Node<E>(this.data, this.priority);
	            copy.right= this.right;
	            copy.left= this.left;
	            return copy;
	        }

	        //-----------------------------------------------------------------------------------------------------------------
	                //  --> 2. rotateleft
	            //-------------------------------------------------------------------------------------
	        /**
	         * rotateleft is a method used to rotate the node structure
	         * --> 1. the top node is rotated to the left
	         * --> 2. the right node is placed in the top position
	         * --> 3. if there existed a left node its placed to the left side of the new left
	         * --> 4. if there existed a right.right node its placed as the new right node
	         * --> 5. if there existed a right.left node its placed to the right side of the new left
	         * @return a copy of the new node structure
	         */
	        private Node<E> rotateLeft()
	        {
	            // Case 1 no children
	            if (this.right == null) {
	                throw new IllegalArgumentException("rotateLeft: Right node is null;");
	            }

	            // 1. swap the data in right and root
	            E rootdata= this.data;
	            int rootpriority= this.priority;

	            this.data= right.data;
	            this.priority = right.priority;

	            right.data = rootdata;
	            right.priority = rootpriority;

	            // 2. swap right and left
	            //swap(this.right, this.left);
	            if(this.left == null)
	            {
	                this.left = this.right;
	                this.right =null;
	            }
	            else {
	                Node<E> temp = this.right;
	                this.right= this.left;
	                this.left= temp;
	            }

	            // 3. swap l.l and l.r
	            //swap(this.left.left, this.left.right);
	            if(this.left != null)
	            {
	                if(this.left.left == null && this.left.right != null)
	                {
	                    this.left.left = this.left.right;
	                    this.left.right=null;
	                }
	                else if(this.left.left!= null && this.left.right == null)
	                {
	                  this.left.right= this.left.left;
	                  this.left.left =null;
	                }
	                else if(this.left.left!= null && this.left.right !=null)
	                {
	                    Node<E> temp = this.left.left;
	                    this.left.left = this.left.right;
	                    this.left.right= temp;
	                }

	            }
	            // 4. swap right and left.left
	            //swap(this.right, this.left.left);
	            if(this.right != null && this.left.left != null)
	            {
	                Node<E> temp = this.right;
	                this.right= this.left.left;
	                this.left.left= temp;
	            }
	            else if(this.right==null && this.left.left != null)
	            {
	                this.right= this.left.left;
	                this.left.left= null;
	            }
	            else if(this.right!=null && this.left.left == null)
	            {
	                this.left.left= this.right;
	                this.right=null;
	            }

	            // 5. create a copy of new node and send back
	            Node<E> copy = new Node<E>(this.data, this.priority);
	            copy.right= this.right;
	            copy.left= this.left;
	            return copy;
	        }
	        //-----------------------------------------------------------------------------------------------------------------
	                //  --> 3. toString
	            //-------------------------------------------------------------------------------------
	        /**
	         * toString is a method that prints the (1) key and (2) priority of a Node
	         * @return a string of form "( key = , priority = )" with the corresponding values of the given Node
	         */
	        public String toString()
	        {
	            StringBuilder s = new StringBuilder();
	            s.append("(key = "+ this.data + " , priority = " + this.priority +")");
	            return s.toString();
	        }

	    }

	//---------------------------------------------------------------------------------------------------------------------
	    // End ----> Code for Node  <----- End.
	//---------------------------------------------------------------------------------------------------------------------


	    //********************************************************************************************************************
	    //********************************************************************************************************************
	    //********************************************************************************************************************


	//---------------------------------------------------------------------------------------------------------------------
	    // Start -->  Code for Treap -->
	//---------------------------------------------------------------------------------------------------------------------


	//---------------------------------------------------------------------------------------------------------------------
	    // Data Fields: Treap
	//---------------------------------------------------------------------------------------------------------------------
	    private Random priorityGenerator;
	    private Node <E> root;


	//---------------------------------------------------------------------------------------------------------------------
	    // Constructors: Treap
	//---------------------------------------------------------------------------------------------------------------------

	    /**
	     * Treap is a Constructor that instantiates a new instance of Treap
	     * 1. sets the root to null and instantiates the randomized priority generator
	     */
	    public Treap()
	    {
	        //super();
	        this.root=null;
	        this.priorityGenerator = new Random();
	    }
	    /**
	     * Treap is a Constructor that instantiates a new instance of Treap
	     * 1. sets the root to null and instantiates the randomized priority generator with given seed
	     */
	    public Treap(long seed)
	    {
	        //super();
	        this.root=null;
	        this.priorityGenerator = new Random(seed);
	    }


	//---------------------------------------------------------------------------------------------------------------------
	    // Methods: Part 1 : Rolodex operations
	//---------------------------------------------------------------------------------------------------------------------

	        //  --> 1.0 add
	    //-------------------------------------------------------------------------------------
	    /**
	     * Add is a method that adds a new node into the Treap
	     *    --> Note: this add method generates a random priority and then utilizes the add(key, priority) to actually add the node.
	     * @param key The key of the new node
	     * @return true if the card is successfully added and false if not ( this occurs when a node with the given key already exists)
	     */
	    public Boolean add(E key)
	    {
	        if(key == null)
	        {
	            throw new IllegalArgumentException("add: key is null");
	        }
	        int priority = priorityGenerator.nextInt();
	        return add(key, priority);
	    }

	//-----------------------------------------------------------------------------------------------------------------
	        //  --> 1.5 add
	    //-------------------------------------------------------------------------------------
	    /**
	     * Add is a method that adds a new node into the Treap
	     * @param key The key of the new node
	     * @return true if the card is successfully added and false if not ( this occurs when a node with the given key already exists)
	     */
	    public boolean add(E key, int priority)
	    {
	        if(key == null)
	        {
	            throw new IllegalArgumentException("add: key is null");
	        }
	        Stack<Node<E>> parentstack = new Stack<Node<E>>();

	        if(this.root==null)
	        {
	            this.root = new Node<>(key, priority);
	            return true;
	        }
	        else {
	            Node<E> current = this.root;
	            boolean done = false;
	            while (current != null&& !done) {
	                int i = current.data.compareTo(key);

	                if (i == 0) {
	                    return false;
	                }

	                if (i < 0) {
	                    parentstack.push(current);
	                    if(current.right == null)
	                    {
	                        current.right = new Node<>(key, priority);
	                        done = true;
	                    }
	                        current = current.right;

	                } else {
	                    parentstack.push(current);
	                    if(current.left == null)
	                    {
	                        current.left = new Node<>(key, priority);
	                        done = true;
	                    }
	                        current = current.left;
	                }
	            }

	            if (!parentstack.isEmpty()) {
	                reheap(current, parentstack);
	            }

	            return true;
	        }
	    }

	    //-----------------------------------------------------------------------------------------------------------------
	    //    ::   HELPER FUNCTIONS  ::
	    //        --> 0.99 helper function for 1.
	    //-------------------------------------------------------------------------------------
	    /**
	     * reheap is a method that restores the tree invariant
	     * @param current The node that has just been added to the tree
	     * @param parentstack A stack containing all the parent, grandparent... nodes which were passed along the journey down the tree to add the new node
	     * fixing the invariant is carried out by "bubbling up" the added node continually until its parent's priority is less than its priority.
	     */
	    private void reheap(Node<E> current, Stack parentstack)
	    {
	        boolean needsReheap = true;
	        while(!parentstack.isEmpty()&& needsReheap)
	        {
	            Node<E> parent = (Node<E>) parentstack.pop();
	            if(current.priority> parent.priority)
	            {
	                if(parent.right != null && parent.right.priority == current.priority)
	                {
	                    /// do i need to set parent equal to the child??
	                    current =parent.rotateLeft();
	                }
	                else if(parent.left!= null && parent.left.priority== current.priority)
	                {
	                    current =parent.rotateRight();
	                }
	            }
	            else {
	                needsReheap = false;
	            }
	        }
	    }



	//-----------------------------------------------------------------------------------------------------------------
	        //  --> 2.0 delete
	    //-------------------------------------------------------------------------------------
	    /**
	     * delete is a method that deletes a node from the Treap, if a node containing the given key exists
	     * @param key The key of the node to be deleted
	     * @return true if the card is successfully deleted and false if no node with the key exists
	     */
	    public boolean delete(E key)
	    {
	        if(key == null)
	        {
	            throw new IllegalArgumentException("delete: key is null");
	        }
	        if(root==null)
	        {
	            throw new IllegalStateException("delete: root is null");
	        }
	        
	        Node<E> current = root;
	        Stack<Node<E>> parentstack = new Stack<Node<E>>();
	        parentstack.push(current);


	        // 1. find the node to be deleted
	        //  ---> if the key is more than the left key go right if not go left
	        while(current.data != key)
	        {
	            if(current.left == null && current.right == null)
	            {
	                return false;
	            }
	            else if(current.left == null && current.right != null)
	            {
	                current = current.right;
	                parentstack.push(current);
	            }
	            else if(current.right == null && current.left != null )
	            {
	                current = current.left;
	                parentstack.push(current);
	            }
	            else
	            {
	                int left= current.left.data.compareTo(key);
	                int right= current.right.data.compareTo(key);
	                if(left > 0 || left == 0)
	                {
	                    current = current.left;
	                    parentstack.push(current);
	                }
	                else if(right < 0 || right ==0)
	                {
	                    current = current.right;
	                    parentstack.push(current);
	                }
	                else{
	                    return false;
	                }
	            }
	        }

	        // 2. rotate the node down the tree until its a leaf
	        //  --> if it has two children rotate the higher priority child upwards
	        //   (while children exist rotate)
	        while(current.left != null || current.right != null)
	        {
	            // both children exist
	            if (current.left != null &&  current.right != null)
	            {
	                if(current.left.priority > current.right.priority)
	                {
	                    current = current.rotateRight();
	                    current= current.right;
	                    parentstack.push(current);
	                }
	                else if(current.right.priority> current.left.priority)
	                {
	                    current= current.rotateLeft();
	                    current = current.left;
	                    parentstack.push(current);
	                }
	            }
	            else if(current.left == null && current.right != null)
	            {
	                current = current.rotateLeft();
	                current = current.left;
	                parentstack.push(current);
	            }
	            else if(current.right == null && current.left !=null)
	            {
	                current = current.rotateRight();
	                current = current.right;
	                parentstack.push(current);
	            }
	        }

	        // 3. using the stack of parents. (A) Via the parent cut the tie to the delete node
	        if(current.left == null && current.right==null && parentstack.size()!= 1)
	        {
	            Node<E> parent= parentstack.pop();
	            while(parent.data == key)
	            {
	                parent= parentstack.pop();
	            }
	            if(parent.right != null && parent.right.data == current.data)
	            {
	                parent.right = null;
	            }
	            else if (parent.left != null && parent.left.data == current.data)
	            {
	                parent.left = null;
	            }
	        }

	        return true;
	    }


	//-----------------------------------------------------------------------------------------------------------------
	        //  --> 3.0 find
	    //-------------------------------------------------------------------------------------
	    /**
	     * find is a method that finds a node from the Treap, if a node containing the given key exists
	     * @param root The Root Node of the treap to search
	     * @param key The key of the node wanted
	     * @return true if the card is successfully found and false if no node with the key exists in the given treap
	     */
	    private boolean find(Node<E> root, E key)
	    {
	        if(key == null)
	        {
	            throw new IllegalArgumentException("find: key is null");
	        }
	        if(root == null)
	        {
	        	throw new IllegalStateException("find: root is null");
	        }

	        Node<E> current = root;
	        // if the key is more than the left key go right if not go left
	        while(current.data != key)
	        {
	            if(current.left == null && current.right == null)
	            {
	                return false;
	            }
	            else if(current.left == null && current.right != null)
	            {
	                current = current.right;
	            }
	            else if(current.right == null && current.left != null )
	            {
	                current = current.left;
	            }
	            else
	            {
	                int left= current.left.data.compareTo(key);
	                int right= current.right.data.compareTo(key);
	                if(left > 0 || left == 0)
	                {
	                    current = current.left;
	                }
	                else if(right < 0 || right ==0)
	                {
	                    current = current.right;
	                }
	                else{
	                    return false;
	                }
	            }
	        }
	        return true;
	    }


	//-----------------------------------------------------------------------------------------------------------------
	        //  --> 3.5 find
	    //-------------------------------------------------------------------------------------
	    /**
	     * find is a method that finds a node from the Treap, if a node containing the given key exists
	     *   --> this One Parameter find utilizes the root of the entire Treap and searches its entirety
	     * @param key The key of the node wanted
	     * @return The true if the card is successfully found and false if no node with the key exists
	     */
	    public boolean find(E key)
	    {
	        if(key == null)
	        {
	            throw new IllegalArgumentException("find: key is null");
	        }
	        return find(this.root, key);
	    }

	//-----------------------------------------------------------------------------------------------------------------
	        //  --> 4.0 toString
	    //-------------------------------------------------------------------------------------
	    /**
	     * toString A method that prints out the preorder traversal of a Treap starting from the root
	     * @return The preorder traversal of a Treap
	     */
	    public String toString()
	    {
	        return toString(this.root, 0);
	    }
	    /**
	     * toString A helper method that prints out the preorder traversal
	     * @return The preorder traversal of a Treap
	     */
	    private String toString(Node<E> current, int level)
	    {
	        StringBuilder treap = new StringBuilder();
	        for (int currentlevel=0; currentlevel<level; currentlevel++)
	        {
	            treap.append(" ");
	            //treap.append("-");
	            //if(currentlevel+1 == level)
	            //{
	            //    treap.append(">");
	            //}
	        }
	        if (current==null)
	        {
	            treap.append("null\n");
	        }
	        else
	        {
	            treap.append(current.toString()+"\n");
	            treap.append(toString(current.left, level+1));
	            treap.append(toString(current.right,level+1));
	        }
	        return treap.toString();
	    }

	//---------------------------------------------------------------------------------------------------------------------
	//  ::   MAIN   ::
	//---------------------------------------------------------------------------------------------------------------------
	    public static void main(String[] args)
	    {
	    
	    }



	}
