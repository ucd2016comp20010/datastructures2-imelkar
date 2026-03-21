package project20280.priorityqueue;

/*
 */

import project20280.interfaces.Entry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;


/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public HeapPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the priority queue
     */
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Creates a priority queue initialized with the respective key-value pairs. The
     * two arrays given will be paired element-by-element. They are presumed to have
     * the same length. (If not, entries will be created only up to the length of
     * the shorter of the arrays)
     *
     * @param keys   an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        super();
//        for(int i=0; i<keys.length && i<values.length; ++i){
//            insert(keys[i], values[i]);
//        }
        int kL = keys.length;
        int vL = values.length;
        for(int i=0; i<kL && i < vL; ++i){
            heap.addLast(new PQEntry<K, V>(keys[i], values[i]));
        }
        heapify();
    }

    // protected utilities
    protected int parent(int j) {
        return (j-1)/2;
    }

    protected int left(int j) {
        int left = 2*j+1;
        if(left >= size()){
            return -1;
        }
        return left;
    }

    protected int right(int j) {
        int right = 2*j+2;
        if(right >= size()){
            return -1;
        }
        return right;
    }

    protected boolean hasLeft(int j) {
        return left(j) != -1;
    }

    protected boolean hasRight(int j) {
        return right(j) != -1;
    }

    /**
     * Exchanges the entries at indices i and j of the array list.
     */
    protected void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Moves the entry at index j higher, if necessary, to restore the heap
     * property.
     */
    protected void upheap(int j) {
        int parent = parent(j);
        if(compare(heap.get(j), heap.get(parent)) < 0){
            swap(j, parent);
            upheap(parent);
        }
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    protected void downheap(int j) {
        int child = -1;
        if(hasLeft(j)){
            child = left(j);
        }
        if(hasRight(j)){
            int right = right(j);
            if(compare(heap.get(right), heap.get(child)) < 0){
                child = right;
            }
        }
        if(child == -1){
            return;
        }
        if(compare(heap.get(j), heap.get(child)) > 0){
            swap(j, child);
            downheap(child);
        }
    }

    protected void downheap(int j, int end) {
        if(j >= end){
            return;
        }
        int child = -1;
        if(hasLeft(j)){
            child = left(j);
        }
        if(hasRight(j)){
            int right = right(j);
            if(child == -1 || compare(heap.get(right), heap.get(child)) < 0){
                child = right;
            }
        }
//        System.out.println("child - " + child + "/" + heap.get(child) + " (parent = " + j + "/" + heap.get(j) + ")");
        if(child == -1 || child >= end){
            return;
        }
        if(compare(heap.get(j), heap.get(child)) > 0){
            swap(j, child);
            downheap(child, end);
        }
    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     */
    protected void heapify() {
        for(int i=heap.size()-1; i>=0; --i){
            downheap(i);
        }
    }

    // public methods

    /**
     * Returns the number of items in the priority queue.
     *
     * @return number of items
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     *
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K, V> min() {
        return heap.getFirst();
    }

    /**
     * Inserts a key-value pair and return the entry created.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        Entry<K, V> new_entry = new PQEntry<K, V>(key, value);
        heap.addLast(new_entry);
        upheap(heap.size()-1);
        return new_entry;
    }

    /**
     * Removes and returns an entry with minimal key.
     *
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K, V> removeMin() {
        swap(0, heap.size()-1);
        Entry<K, V> removed = heap.removeLast();
        downheap(0);
        return removed;
    }

    private void preorderTraversalPrintHelper(int j){
        System.out.print(heap.get(j).getValue() + " ");
        if(hasLeft(j)){
            preorderTraversalPrintHelper(left(j));
        }
        if(hasRight(j)){
            preorderTraversalPrintHelper(right(j));
        }
    }

    public void preorderTraversalPrint(){
        preorderTraversalPrintHelper(0);
        System.out.println();
    }

    private void postorderTraversalPrintHelper(int j){
        if(hasLeft(j)){
            postorderTraversalPrintHelper(left(j));
        }
        if(hasRight(j)){
            postorderTraversalPrintHelper(right(j));
        }
        System.out.print(heap.get(j).getValue() + " ");
    }

    public void postorderTraversalPrint(){
        postorderTraversalPrintHelper(0);
        System.out.println();
    }

    public String toString() {
        return heap.toString();
    }

    /**
     * Used for debugging purposes only
     */
    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            //System.out.println("-> " +left + ", " + j + ", " + right);
            Entry<K, V> e_left, e_right;
            e_left = left < heap.size() ? heap.get(left) : null;
            e_right = right < heap.size() ? heap.get(right) : null;
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0) {
                System.out.println("Invalid left child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0) {
                System.out.println("Invalid right child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
        }
    }

    public static <T> void PQsort(T[] arr){
        HeapPriorityQueue<T, T> pq = new HeapPriorityQueue<>(arr, arr);
        for(int i=0; i<arr.length; ++i){
            arr[i] = pq.removeMin().getValue();
        }
    }

    public static <T> void heapsort(T[] arr){
        HeapPriorityQueue<T, T> pq = new HeapPriorityQueue<>(arr, arr);
        for(int i = pq.heap.size()-1; i > 0; --i){
            pq.swap(0, i);
            pq.downheap(0, i);
        }
        pq.swap(0, 1);
        for(int i=0; i<arr.length; ++i){
            arr[i] = pq.heap.get(i).getValue();
        }
    }

    public static void main(String[] args) {
//        Integer[] rands = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
//        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(rands, rands);
//
//        System.out.println("elements: " + rands);
//        System.out.println("after adding elements: " + pq);
//
//        System.out.println("min element: " + pq.min());
//
//        pq.removeMin();
//        System.out.println("after removeMin: " + pq);
//        // [             1,
//        //        2,            4,
//        //   23,     21,      5, 12,
//        // 24, 26, 35, 33, 15]

        // Q1
        System.out.println("Q1");
        Integer[] arr = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>();
        for(Integer i: arr){
            pq.insert(i, i);
            System.out.println(pq);
        }

        // Q2
        System.out.println("Q2");
        pq.preorderTraversalPrint();

        // Q3
        System.out.println("Q3");
        pq.postorderTraversalPrint();

        // Q4 - yes to both

        // Q5
        System.out.println("Q5");
        Integer[] arr5 = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
        HeapPriorityQueue<Integer, Integer> pq5 = new HeapPriorityQueue<>(arr5, arr5);
        System.out.println(pq5);

        // Q6 - Assumption: O(nlogn)
        System.out.println("Q6");
        long start, result;
        Random rnd = new Random();
        rnd.setSeed(1024);
        int n_min = 1000, n_max = 1000000, n_samples = 80;
        double alpha = ( (Math.log(n_max) / Math.log(n_min)) - 1) / (n_samples-1);
        for(int i = 0; i < n_samples; ++i) {
            int n = (int) Math.pow(n_min, (1 + i * alpha));
            Integer[] arr6 = IntStream.rangeClosed(1, n).boxed().toArray(Integer[]::new);
            start = System.currentTimeMillis();
            PQsort(arr6);
            result = System.currentTimeMillis() - start;
            System.out.println(i + "\t" + n + "\t" + result + "\t" );
        }

        // Q7 - O(nlogn)
        System.out.println("Q7");
        Integer[] arr7 = {2, 5, 16, 4, 10, 23, 39, 18, 26, 15};
//        Integer[] arr7 = {35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        heapsort(arr7);
        for(int i: arr7){
            System.out.print(i + " ");
        }
        System.out.println();

        for(int i = 0; i < n_samples; ++i) {
            int n = (int) Math.pow(n_min, (1 + i * alpha));
            Integer[] arr6 = IntStream.rangeClosed(1, n).boxed().toArray(Integer[]::new);
            start = System.currentTimeMillis();
            heapsort(arr6);
            result = System.currentTimeMillis() - start;
            System.out.println(i + "\t" + n + "\t" + result + "\t" );
        }
    }
}
