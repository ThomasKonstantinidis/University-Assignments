import java.util.*;
import java.io.PrintStream;

public class IntQueueImpl implements IntQueue {
	private Node head, tail;
	private int n;
	private class Node {
		int item;
		Node next;
		Node(int item) {
			this.item = item;
		    next = null;
		}
	}
	public IntQueueImpl(){
		this.head = this.tail = null;
		n=0;
	}
	public boolean isEmpty() {
		return (this.head == null) ; 
	}
	public void put(int item) {
		Node node = this.tail; 
		head = new Node(item);
		if (isEmpty()==true) {
			this.tail = this.head = node;
		}
		else {
			this.tail.next = node;
			this.tail = node;
		}
		n++;
	}
	public int get() throws NoSuchElementException {
		if(this.isEmpty()==true){
			throw new NoSuchElementException();
		}
		int deletedItem= this.head.item;
		if(this.head==this.tail){
			this.head = this.tail = null;
		}
		else{
			this.head = this.head.next;
		}
		n--;
		return deletedItem;
	}
	public int peek() throws NoSuchElementException {
		if(this.isEmpty()==true){
			throw new NoSuchElementException();
		}
		return this.head.item;
	}
	public int size(){
		return n;
	}
	public void printQueue(PrintStream stream){
		for( Node i=head; i.next==tail.next; i=i.next){
			stream.println(i.item);
		}
	}
}
	
		

	
	
	