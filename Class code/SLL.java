package lists;

public class SLL<E> {

	public static class Node<F> {
		// data fields
		private F data;
		private Node<F> next;
		
		public Node(F data, Node<F> next) {
			super();
			this.data = data;
			this.next = next;
		}

		public Node(F data) {
			super();
			this.data = data;
			this.next = null;
		}
		
	}
	
	// data fields
	private Node<E> head;
	private int size;
	
	SLL() {
		head=null;
		size=0;
	}
	
	public void addFirst(E item) {
		head = new Node<>(item,head);
		size++;
	}
	
	
	public void add(E item, int index) {
		if (index<0 || index>size) {
			throw new IllegalArgumentException("add: index out of bounds");
		}
		if (index==0) {
			addFirst(item);
		} else {
			
			Node<E> current = head;
			for (int i=0; i<index-1; i++) {
				current=current.next;
			}
			
			current.next = new Node<>(item, current.next);
			size++;
			
		}
		
	}
	
	public E getAt(int index) {
		if (index<0 || index>size) {
			throw new IllegalArgumentException("getAt: index out of bounds");
		}
		Node<E> current = head;
		for (int i=0; i<index; i++) {
			current=current.next;
		}
		
		return current.data;
	}	
	
	public void removeFirst() {
		if (head==null) {
			throw new IllegalStateException("removeFirst: list is empty");
		}
		head = head.next;
		size--;
	}
	
	public void remove(int index) {
		if (index<0 || index>size) {
			throw new IllegalArgumentException("remove: index out of bounds");
		}
		if (index==0) {
			removeFirst();
		} else {
			Node<E> current = head;
			for (int i=0; i<index-1; i++) {
				current=current.next;
			}

			current.next = current.next.next;
			size--;
		}
	}


	/** 
	 * Determines whether item belongs to the list
	 * @param item The item we are looking for
	 * @return True if the item is in the list, false otherwise
	 */
	public Boolean mem(E item) {
		Node<E> current = head;
		
		while (current!=null && !current.data.equals(item)) {
			current = current.next;
		}
		
		return current!=null;
	}
	
	
	/** Use previously defined SLL operations */
	public SLL<E> clone() {
		SLL<E> result = new SLL<>();
		
		for (int i=0; i<size; i++) {
			result.add(getAt(i), i);
		}
				
		return result;
	}

	public SLL<E> clone2() {
		SLL<E> result = new SLL<>();
		Node<E> current = head;
		
		for (int i=0; i<size; i++) {
			result.add(current.data, i);
			current = current.next;
		}
				
		return result;
	}
	
	/** Do NOT use previously defined SLL operations */
	public SLL<E> clone3() {
		SLL<E> result = new SLL<>();
		
		Node<E> current = head;
		Node<E> newCurrent = new Node<>(null);
		Node<E> dummy = newCurrent;
		
		while (current!=null) {
			newCurrent.next =  new Node<>(current.data);
			current=current.next;
			newCurrent=newCurrent.next;
		}
		
		result.head = dummy.next;
		result.size = size;
		return result;
	}

	/**
	 * Drops the suffix of the recipient list starting from the node at index count
	 * If count is greater than the size of the list, nothing is dropped
	 * @param count
	 */
	public void take(int count) {
		if (count<0) {
			throw new IllegalArgumentException("take: negative argument");
		}
		if (count==0 || head==null) {
			head=null;
			size=0;
			return;
		}
		// count>0 and the list is not empty
		
		Node<E> current = head;
		int i = 1;
		while (current.next!=null && i<count) {
			current=current.next;
			i++;
		}
		
		current.next=null;
		size = i;
		
	
	}

	
	/**
	 * Drops the prefix of the recipient list starting from the node at index 0 and up to and including
	 * the node at index count-1.
	 * If count is greater than the size of the list, the resulting list is empty
	 * @param count
	 */
	public void drop(int count) {
		if (count<0) {
			throw new IllegalArgumentException("drop: negative count");
		}
		if (count==0 || head==null) {
			return;
		}
		// count>0 and the list is not empty
		
		int i=0;
		while (i<count && head!=null) {
			removeFirst();
			i++;
		}

		
	}
	
	/**
	 * Checks to see if item belongs the linked-list that starts with the node local
	 * @param item
	 * @param local
	 * @return
	 */
	private Boolean mem(E item, Node<E> local) {
		return null;
	}

	/**
	 * Determines whether the recipient list has duplicate elements.
	 * @return
	 */
	public Boolean hasDuplicates() {
		return null;
	}

	public void removeAll(E item) {
		
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		Node<E> current = head;
		s.append("[");
		while (current!=null) {
			s.append(current.data.toString()+";");
			current = current.next;
		}
		s.append("]");
		return s.toString();
		
	}
	
	public static void main(String[] args) {
		SLL<String> l = new SLL<>();
		
		l.addFirst("hello");
		l.addFirst("howdy");
		l.addFirst("hi");
		
		l.add("hey", 3);
		
		System.out.println(l);
//		System.out.println(l.getAt(0));
//		System.out.println(l.getAt(2));
//		

//		
//		System.out.println(l.mem("hello"));
//		System.out.println(l.mem("hey there"));

//		SLL<String> l2 = l.clone();
//		
//		System.out.println(l2);
//		l2.removeFirst();
//		System.out.println(l);
//		System.out.println(l2);
		
		l.drop(200);

		System.out.println(l);
//
//		
	}
	
	
}
