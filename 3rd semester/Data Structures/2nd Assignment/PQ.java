import java.util.Comparator;

public class PQ<T>{
	private T[] heap;
	private int size;
	protected Comparator <T> cmp;
	
	/*public PQ(int capacity){
		this(capacity, new DefaultComparator());
	}*/
	
	public PQ(int Capacity, Comparator<T> cmp){
		if(Capacity < 1) throw new IllegalArgumentException();
		this.heap = (T[]) new Object [Capacity+1];
		this.size = 0;
		this.cmp = cmp;
	}
	
	public void insert(T object){
		if(object == null) throw new IllegalArgumentException();
		if(size == this.heap.length-1) throw new IllegalArgumentException();
		if(size>= (this.heap.length-1)*75/100) resize(heap, size);
		heap[++size] = object;
		swim(size);
	}
	
	public T max(){
		if(size == 0) throw new IllegalStateException();
		return heap[1];
	}
	
	
	public T getMax(){
		if(size == 0) throw new IllegalStateException();
		T object = heap[1];
		if(size > 1) heap[1] = heap[size];
		heap[size--] = null;
		sink(1);
		return object;
	}
	
	private void swim(int i){
		while(i > 1){
			int p = i/2;
			int result = cmp.compare(heap[i], heap[p]);
			if (result<=0) return;
			swap(i,p);
			i = p;
		}
	}
	
	private void sink(int i) {
		int left = 2*i, right = left+1, max = left ;
		while (left <= size){
			if(right <= size){
				max = cmp.compare(heap[left],heap[right]) < 0 ? right : left;
			}
			if(cmp.compare(heap[i],heap[max]) >= 0) return;
			swap(i,max);
			i = max; left = 2*i; right = left+1; max = left;
		}
	}
	
	public T remove( int id ){
		T removed = heap[id];
		if(id > heap.length || id==0){ 
			throw new IllegalArgumentException();
		}
		if(id == heap.length){
			heap[id]=null;
		}
		else if(id == 1){
			getMax();
		}
		else{
			heap[id] = null;
			sink(id);
		}
		System.out.println(heap.length);
		return removed;
		
	}
			
	
	private void swap(int i, int j){
		T tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
	}
	
	public void resize( T[] heap, int x ) {
		T[] heap2 = (T []) new Object[x*2];
		for(int i=0; i<x*2; i++){
			heap2[i] = heap[i];
		}
		heap = heap2;
	}

	
	public void print(){
		for (int i=1; i<=size; i++){
			System.out.println(heap[i]);
		}
		System.out.println(heap.length);
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
}
	
	
	
	
	