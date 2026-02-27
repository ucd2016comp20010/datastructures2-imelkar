package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int position) {
        if(position >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<E> last_node = head;
        for(int i=0; i<position; ++i){
            last_node = last_node.getNext();
            if(last_node == null){
                return null;
            }
        }
        return last_node.element;
    }

    @Override
    public void add(int position, E e) {
        if(this.isEmpty()){
            head = new Node<E>(e, null);
            ++size;
            return;
        }
        ++size;
        Node<E> last_node = head;
        for(int i=0; i<position-1; ++i){
            if(last_node.getNext() == null){
                break;
            }
            last_node = last_node.getNext();
        }
        Node<E> new_node = new Node<E>(e, last_node.getNext());
        last_node.setNext(new_node);
    }


    @Override
    public void addFirst(E e) {
        ++size;
        head = new Node<E>(e, head);
    }

    @Override
    public void addLast(E e) {
        Node<E> new_node = new Node<E>(e, null);
        if(this.isEmpty()) {
            head = new_node;
            ++size;
            return;
        }
        ++size;
        Node<E> last_node = head;
        while(last_node.getNext() != null){
            last_node = last_node.getNext();
        }
        last_node.setNext(new_node);
    }

    @Override
    public E remove(int position) {
        --size;
        if(position == 0){
            return removeFirst();
        }
        if(head == null){
            return null;
        }
        Node<E> last_node = head;
        for(int i=0; i<position-1; ++i){
            last_node = last_node.getNext();
            if(last_node == null){
                return null;
            }
        }
        Node<E> nodeToRemove = last_node.getNext();
        E removed = nodeToRemove.getElement();
        last_node.setNext(nodeToRemove.getNext());
        return removed;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }
        --size;
        E removed = head.getElement();
        head = head.getNext();
        return removed;
    }

    @Override
    public E removeLast() {
        if(isEmpty()){
            return null;
        }
        --size;
        if(head.getNext() == null){
            E removed = head.getElement();
            head = null;
            return removed;
        }
        Node<E> last_node = head;
        while(last_node.getNext().getNext() != null){
            last_node = last_node.getNext();
        }
        E removed = last_node.getNext().getElement();
        last_node.setNext(null);
        return removed;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    // Q9
    public SinglyLinkedList<E> sortedMerge(SinglyLinkedList<E> s1, SinglyLinkedList<E> s2){
        if(!(s1.get(0) instanceof Comparable)){
            throw new IllegalArgumentException();
        }
        SinglyLinkedList<E> result = new SinglyLinkedList<E>();
        int final_size = s1.size()+s2.size();
        for(int i=0; i<final_size; ++i){
            if(s1.size() == 0 && s2.size() == 0){
                break;
            }
            if(s1.size() == 0){
                result.addLast(s2.removeFirst());
            }
            else if(s2.size() == 0){
                result.addLast(s1.removeFirst());
            }
            else if(((Comparable<E>) s1.get(0)).compareTo(s2.get(0)) < 0){
                result.addLast(s1.removeFirst());
            }
            else{
                result.addLast(s2.removeFirst());
            }
        }
        return result;
    }

    // Q10
//    public void reverse(){
//        if(isEmpty() || size == 1){
//            return;
//        }
//        Node<E> last = head;
//        Node<E> curr = head.next;
//        Node<E> next;
//        last.next = null;
//        for(int i=0; i<size-1; ++i){
//            next = curr.next;
//            curr.next = last;
//            last = curr;
//            curr = next;
//        }
//        head = last;
//    }

    // Wk6 Q6
    public void reverse() {
        if(head != null) {
            head = reverseHelper(null, head);
        }
    }
    private Node<E> reverseHelper(Node<E> curr, Node<E> succ) {
        Node<E> n = succ.next;
        succ.next = curr;
        if(n == null) {
            return succ;
        }
        return reverseHelper(succ, n);
    }

    // Wk6 Q7
    public SinglyLinkedList<E> recursiveCopy(){
        SinglyLinkedList<E> copy = new SinglyLinkedList<E>();
        recursiveCopyHelper(copy, this.head);
        return copy;
    }

    public void recursiveCopyHelper(SinglyLinkedList<E> copy, Node<E> cur){
        if(cur == null){
            return;
        }
        copy.addLast(cur.getElement());
        recursiveCopyHelper(copy, cur.getNext());
    }

    // Q11
    public SinglyLinkedList<E> clone(){
        SinglyLinkedList<E> cloned = new SinglyLinkedList<E>();
        Node<E> curr = head;
        for(int i=0; i<size; ++i){
            cloned.addLast(curr.getElement());
            curr = curr.getNext();
        }
        return cloned;
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

        for(Integer el : ll){
            System.out.println(el);
        }

        ll.reverse();

        System.out.println(ll.toString());

        SinglyLinkedList<Integer> clone = ll.clone();
        System.out.println(clone);
        clone.addLast(6);
        clone.add(3, 11);
        System.out.println(clone);

        System.out.println(clone.recursiveCopy());

    }
//    public static void main(String[] args) {
//        final Integer[] l1 = {2, 6, 20, 24};
//        final Integer[] l2 = {1, 3, 5, 8, 12, 19, 25};
//        SinglyLinkedList<Integer> s1 = new SinglyLinkedList<Integer>();
//        SinglyLinkedList<Integer> s2 = new SinglyLinkedList<Integer>();
//
//        for(Integer i: l1){
//            s1.addLast(i);
//        }
//        for(Integer i: l2){
//            s2.addLast(i);
//        }
//
//        System.out.println(s1.sortedMerge(s1, s2).toString());
//    }
}
