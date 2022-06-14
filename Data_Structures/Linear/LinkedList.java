/**
 * LinkedList implementation
 * 
 * @author Akhila Jayanthi
 * 
 */
public class LinkedList<T> {

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
	head = null;
	tail = null;
    }

    static class Node<T> {
	private T data;
	private Node<T> next;

	Node(T data) {
	    this.data = data;
	}

	@Override
	public String toString() {
	    return this.data.toString();
	}

    }

    public void clear() {
	Node<T> trav = head;
	while (trav != null) {
	    Node<T> next = trav.next;
	    trav.next = null;
	    trav.data = null;
	    trav = next;
	}
	head = tail = trav = null;
	size = 0;
    }

    public int size() {
	return size;
    }

    public boolean isEmpty() {
	return size == 0;
    }

    public void add(T element) {
	addLast(element);
    }

    public void addLast(T ele) {
	if (isEmpty()) {
	    head = tail = new Node<T>(ele);
	} else {
	    tail.next = new Node<T>(ele);
	    tail = tail.next;
	}
	size++;
    }

    public void addFirst(T ele) {
	Node<T> newNode = new Node<T>(ele);
	if (isEmpty()) {
	    head = tail = newNode;
	} else {
	    newNode.next = head;
	    head = newNode;
	}
	size++;
    }

    public void addAt(int index, T ele) {
	if (index < 0 || index > size) {
	    new IllegalArgumentException("Illegal index");
	}

	if (index == 0) {
	    addFirst(ele);
	    return;
	} else if (index == size) {
	    addLast(ele);
	    return;
	}

	Node<T> newNode = new Node<T>(ele);
	Node<T> trav = head;
	Node<T> prev = null;
	for (int i = 0; i < index; i++) {
	    prev = trav;
	    trav = trav.next;
	}
	prev.next = newNode;
	newNode.next = trav;
	size++;
    }

    public T peekFirst() {
	if (isEmpty())
	    new IllegalArgumentException("cannot peak an empty list");
	return head.data;
    }

    public T peekLast() {
	if (isEmpty())
	    new IllegalArgumentException("cannot peak an empty list");
	return tail.data;
    }

    public T removeFirst() {
	if (isEmpty())
	    new IllegalArgumentException("cannot remove from empty list");
	T data = head.data;
	head = head.next;
	--size;

	if (isEmpty())
	    tail = null;

	return data;
    }

    public T removeLast() {
	if (isEmpty())
	    new IllegalArgumentException("cannot remove from empty list");

	T data = tail.data;
	Node<T> trav = head;

	for (int i = 0; i < size - 2; i++) {
	    trav = trav.next;
	}

	tail = trav;
	tail.next = null;

	--size;

	if (isEmpty())
	    head = null;

	return data;
    }

    public void removeAt(int index) {
	if (index < 0 || index > size) {
	    new IllegalArgumentException("Illegal index");
	}

	if (index == 0) {
	    removeFirst();
	} else if (index == size) {
	    removeLast();
	}

	Node<T> trav = head;
	Node<T> prev = null;
	for (int i = 0; i < index; i++) {
	    prev = trav;
	    trav = trav.next;
	}

	prev.next = trav.next;
	--size;
    }

    public int indexOf(Object obj) {
	int index = 0;
	Node<T> trav = head;

	if (obj == null) {
	    for (; trav != null; trav = trav.next, index++) {
		if (trav.data == null) {
		    return index;
		}
	    }
	} else {
	    for (; trav != null; trav = trav.next, index++) {
		if (obj.equals(trav.data)) {
		    return index;
		}
	    }
	}
	return -1;
    }

    public boolean contains(Object obj) {
	return indexOf(obj) != -1;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("[");
	Node<T> trav = head;
	while (trav != null) {
	    sb.append(trav.data);
	    if (trav.next != null) {
		sb.append(",");
	    }
	    trav = trav.next;
	}
	sb.append("] ");
	return sb.toString();
    }

    public static void main(String[] args) {
	LinkedList<Integer> list = new LinkedList<>();
	list.add(1);
	list.add(2);
	list.addLast(3);
	list.addFirst(4);
	list.addAt(2, 5);
	list.add(6);
	list.add(7);
	list.add(8);
	list.add(9);
	System.out.println(list.peekFirst());
	System.out.println(list.peekLast());
	System.out.println(list.toString());
	list.removeFirst();
	System.out.println(list.toString());
	list.removeLast();
	System.out.println(list.toString());
	list.removeAt(2);
	System.out.println(list.toString());
	System.out.println(list.indexOf(3));
	System.out.println(list.contains(6));
	System.out.println(list.contains(4));

    }
}
