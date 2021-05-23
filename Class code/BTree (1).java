package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class BTree<E> {

	public static class Node<F> {
		// data fields
		protected F data;
		protected Node<F> left;
		protected Node<F> right;
		public Node(F data, Node<F> left, Node<F> right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
		public Node(F data) {
			super();
			this.data = data;
		}
		
		
	}
	
	// data fields
	protected Node<E> root;
	protected int size;
	
	BTree() {
		root=null;
		size=0;
	}
	
	BTree(E data) {
		root = new Node<>(data);
		size=1;
	}
	
	BTree(E data, BTree<E> left, BTree<E> right) {
		root = new Node<>(data,left.root,right.root);
		size = 1 + left.size + right.size;
		
	}
	

	private int noOfLeaves(Node<E> current) {
		if (current==null) {
			return 0;
		}
		if (current.left==null && current.right==null) {
			return 1;
		}
		// current is not null and has at least one child
		return noOfLeaves(current.left) + noOfLeaves(current.right);
	}
	
	public int noOfLeaves() {
		return noOfLeaves(root);
	}

	public int noOfLeaves2() {

		int r = 0;
		Stack<Node<E>> s = new Stack<>();

		s.push(root);

		while (!s.empty()) {
			Node<E> current = s.pop();
			if (current!=null && current.left==null && current.right==null) {
				r++;
			}
			if (current!=null) {
				s.push(current.left);
				s.push(current.right);
			}
		}

		return r;
	}

	
	private ArrayList<E> level(int l, Node<E> current) {
		ArrayList<E> result = new ArrayList<>();
		if (current==null) {
			return result;
		}
		if (l==1) {
			result.add(current.data);
			return result;
		}
		// current is not null and l>1
		result.addAll(level(l-1,current.left));
		result.addAll(level(l-1,current.right));
		return result;
	}
	
	public ArrayList<E> level(int l) {
		if (l<0) {
			throw new IllegalArgumentException("level: negative level");
		}
		return level(l,root);
	}
	
	
	private int height(Node<E> current) {
		if (current==null) {
			return 0;
		} else {
			return 1 + Math.max(height(current.left), height(current.right));
		}
	}
	
	public int height() {
		return height(root);
	}
	
	private String toString(Node<E> current, int level) {
		StringBuilder s = new StringBuilder();
		for (int i=0; i<level;i++) {
			s.append("-");
		}
		if (current==null) {
			s.append("null\n");
		} else {
			s.append(current.data.toString()+"\n");
			s.append(toString(current.left, level+1));
			s.append(toString(current.right,level+1));
		}
		return s.toString();
		
	}
	public String toString() {
		return toString(root,0);
	}
	
//	public static void main(String[] args) {
//		BTree<Integer> t1 = new BTree<>(7,new BTree<>(), new BTree<>());
//		BTree<Integer> t2 = new BTree<>(33,new BTree<>(27,new BTree<>(), new BTree<>()),new BTree<>(44,new BTree<>(), new BTree<>()));
//		BTree<Integer> t3 = new BTree<>(23,t1,t2);
//		
//		System.out.println(t3);
////		System.out.println(t3.height());
////		System.out.println(t3.noOfLeaves2());
//		
//		System.out.println(t3.level(300));
//	}
}
