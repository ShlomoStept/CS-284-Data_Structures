package trees;

public class BST<E extends Comparable<E>> extends BTree<E> {
	
	BST() {
		super();
	}
	
	BST(E data) {
		super(data);
	}
	
	BST(E data, BST<E> left, BST<E> right) {
		super(data,left,right);
	}
	
	/**
	 * Returns the largest element in a non-empty binary tree (not necessarily a BST)
	 * @param current
	 * @return
	 */
	private E max(Node<E> current) {
		return null;
	}
	
	
	/**
	 * Returns the smallest element in a non-empty binary tree (not necessarily a BST)
	 * @param current
	 * @return
	 */
	private E min(Node<E> current) {
		return null;
	}
	
	private Boolean isBst(Node<E> current) {
		if (current==null) {
			return true;
		}
		
		return null;
	}
	
	
	/**
	 * Determines whether the recipient tree is a BST or not.
	 * @return true if it is a BST, false otherwise.
	 */
	public Boolean isBst() {
		return isBst(root);
	}
	
	private Node<E> add(E item, Node<E> current) {
		if (current==null) {
			return new Node<>(item);
		}
		
		int i = current.data.compareTo(item);
		if (i==0) {
			throw new IllegalStateException("add: duplicate key");
		}
		if (i<0) {
			current.right = add(item,current.right);
			return current;
		} else {
			current.left = add(item,current.left);
			return current;
		}
		
	}
	
	public void add(E item) {
		root = add(item,root);
	}
	
	public static void main(String[] args) {
		BST<Integer> t1 = new BST<>(7,new BST<>(), new BST<>());
		BST<Integer> t2 = new BST<>(33,new BST<>(27,new BST<>(), new BST<>()),new BST<>(44,new BST<>(), new BST<>()));
		BST<Integer> t3 = new BST<>(23,t1,t2);
		
		System.out.println(t3);
//		System.out.println(t3.height());
//		System.out.println(t3.noOfLeaves2());
//		
//		System.out.println(t3.level(3));
		t3.add(55);
		System.out.println(t3);
	}
	
	

}
