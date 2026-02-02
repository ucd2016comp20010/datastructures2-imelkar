package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        return getNode(i).getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        if(i==0){
            addFirst(e);
            return;
        }
        if(i == size - 1){
            addLast(e);
            return;
        }
        if(i >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<E> last_node = tail;
        for(int j=0; j<i; ++j){
            last_node = last_node.getNext();
        }
        Node<E> new_node = new Node<E>(e, last_node.getNext());
        last_node.setNext(new_node);
        ++size;
    }

    private Node<E> getNode(int i){
        if(isEmpty() || i >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<E> last_node = tail.getNext();
        for(int j = 0; j<i; ++j){
            last_node = last_node.getNext();
            if(last_node == null){
                throw new IndexOutOfBoundsException();
            }
        }
        return last_node;
    }

    @Override
    public E remove(int i) {
        if(i == 0){
            return removeFirst();
        }
        if(i == size - 1){
            return removeLast();
        }
        if(i >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<E> node_before = getNode(i-1);
        Node<E> nodeToRemove = node_before.getNext();
        E removed = nodeToRemove.getData();
        node_before.setNext(nodeToRemove.getNext());
        --size;
        return removed;
    }

    public void rotate() {
        if(isEmpty()){
            return;
        }
        tail = tail.getNext();
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }
        E removed = tail.getNext().getData();
        tail.next = tail.getNext().getNext();
        --size;
        return removed;
    }

    @Override
    public E removeLast() {
        if(isEmpty()){
            return null;
        }
        Node<E> before = getNode(size-1);
        E removed = tail.getData();
        before.next = before.getNext().getNext();
        tail = before;
        --size;
        return removed;
    }

    @Override
    public void addFirst(E e) {
        if(isEmpty()){
            tail = new Node<E>(e, null);
            tail.next = tail;
            ++size;
            return;
        }
        tail.next = new Node<E>(e, tail.getNext());
        ++size;
    }

    @Override
    public void addLast(E e) {
        if(isEmpty()){
            tail = new Node<E>(e, null);
            tail.next = tail;
            ++size;
            return;
        }
        Node<E> new_node = new Node<E>(e, tail.getNext());
        tail.next = new_node;
        tail = new_node;
        ++size;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
// Used for further testing:
//    public static void main(String[] args){
//        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
//        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
//        //LinkedList<Integer> ll = new LinkedList<Integer>();
//
//        ll.addFirst(0);
//        ll.addFirst(1);
//        ll.addFirst(2);
//        ll.addFirst(3);
//        ll.addFirst(4);
//        ll.addLast(-1);
//        //ll.removeLast();
//        //ll.removeFirst();
//        //System.out.println("I accept your apology");
//        //ll.add(3, 2);
//        System.out.println(ll);
//        ll.remove(5);
//        System.out.println(ll);
//    }
}
