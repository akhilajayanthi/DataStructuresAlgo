/**
 * DoublyLinkedList Implementation
 * @Author Akhila Jayanthi
 */
public class DoublyLinkedList<T> {

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    public DoublyLinkedList() {
	head = null;
	tail = null;
    }

    static class Node<T> {
	private T data;
	private Node<T> prev, next;

	Node(T data, Node<T> prev, Node<T> next) {
	    this.data = data;
	    this.prev = prev;
	    this.next = next;
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
	    trav.data = null;
	    trav.prev = trav.next = null;
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

    public void add(T ele) {
	addLast(ele);
    }

    public void addFirst(T ele) {
	if (isEmpty()) {
	    head = tail = new Node<T>(ele, null, null);
	} else {
	    head.prev = new Node<T>(ele, null, head);
	    head = head.prev;
	}
	size++;
    }

    public void addLast(T ele) {
	if (isEmpty()) {
	    head = tail = new Node<T>(ele, null, null);
	} else {
	    tail.next = new Node<T>(ele, tail, null);
	    tail = tail.next;
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

	Node<T> trav = head;
	for (int i = 0; i < index - 1; i++) {
	    trav = trav.next;
	}
	Node<T> newNode = new Node<T>(ele, trav, trav.next);
	trav.next.prev = newNode;
	trav.next = newNode;
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

    public void removeFirst() {
	if (isEmpty())
	    new IllegalArgumentException("cannot remove from empty list");
	head = head.next;
	--size;

	if (isEmpty())
	    tail = null;
	else
	    head.prev = null;
    }

    public void removeLast() {
	if (isEmpty())
	    new IllegalArgumentException("cannot remove from empty list");
	tail = tail.prev;
	--size;

	if (isEmpty())
	    head = null;
	else
	    tail.next = null;
    }

    public void removeAt(int index) {
	if (index < 0 || index > size) {
	    new IllegalArgumentException("Illegal index");
	}
	Node<T> temp = null;
	if (index < size / 2) {
	    temp = head;
	    for (int i = 0; i < index; i++) {
		temp = temp.next;
	    }
	} else {
	    temp = tail;
	    for (int i = size - 1; i > index; i--) {
		temp = temp.prev;
	    }
	}

	temp.prev.next = temp.next;
	temp.next.prev = temp.prev;
	temp.data = null;
	temp = temp.prev = temp.next = null;
	--size;
    }

    public int indexOf(Object obj) {
	int index = 0;
	if (isEmpty())
	    new IllegalArgumentException("Empty List");

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
	sb.append("]");
	return sb.toString();
    }

    public static void main(String[] args) {
	System.out.println("Hello World!");
	DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
	list.add(1);
	list.add(2);
	list.addFirst(4);
	list.add(6);
	list.add(7);
	list.add(8);
	list.add(9);
	list.addLast(3);
	System.out.println(list.toString());
	System.out.println(list.peekFirst());
	System.out.println(list.peekLast());
	list.removeFirst();
	list.removeLast();
	System.out.println(list.toString());
	list.removeAt(3);
	System.out.println(list.toString());
	System.out.println(list.isEmpty());
	System.out.println(list.indexOf(6));
	System.out.println(list.contains(10));

    }
}
