import java.util.*;
import java.io.PrintStream;

public class StringStackImpl implements StringStack {
	private Node head;
	private int n;
	private class Node {
		String item;
		Node next;
		Node(String item, Node next) {
			this.item = item;
		    this.next = next;
		}
	}
	public StringStackImpl(){
		this.head = null;
		n=0;
	}
	public boolean isEmpty() {
		return this.head==null;
	}
	public void push(String item) {
		if (isEmpty()) {
			Node node= new Node(item, null);
			this.head = node;
		}
		else {
			Node node= new Node(item, head);
			head = node;
		}
		n++;
	}
	public String pop() throws NoSuchElementException {
		if(isEmpty()){
			throw new NoSuchElementException();
			
		}
		String deletedItem= this.head.item;
		if(this.head.next == null){
			this.head = null;
		}
		else{
			this.head = this.head.next;
		}
		n--;
		return deletedItem;
	}
	public String peek() throws NoSuchElementException {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return this.head.item;
	}
	public int size(){
		return n;
	}
	public void printStack(PrintStream stream){
		for( Node i=head; i.next==null; i=i.next){
			stream.println(i.item);
		}
	}
}