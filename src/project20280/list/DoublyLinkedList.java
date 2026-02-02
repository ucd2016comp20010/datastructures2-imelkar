package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        Node<E> new_node = new Node<E>(e, pred, succ);
        pred.next = new_node;
        succ.prev = new_node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        Node<E> current = head;
        for(int j = 0; j<i+1; ++j){
            current = current.getNext();
            if(current == null){
                return null;
            }
        }
        return current.getData();
    }

    @Override
    public void add(int i, E e) {
        if(i == 0){
            addFirst(e);
            return;
        }
        if(i == size - 1){
            addLast(e);
            return;
        }
        Node<E> current = head;
        for(int j = 0; j<i; ++j){
            current = current.getNext();
            if(current == null){
                return;
            }
        }
        addBetween(e, current, current.getNext());
        ++size;
    }

    @Override
    public E remove(int i) {
        if(i == 0){
            return removeFirst();
        }
        if(i == size - 1){
            return removeLast();
        }
        Node<E> current = head;
        for(int j = 0; j<i+1; ++j){
            current = current.getNext();
            if(current == null){
                throw new IndexOutOfBoundsException();
            }
        }
        return remove(current);
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.next;

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
        return new DoublyLinkedListIterator<E>();
    }

    private E remove(Node<E> n) {
        n.getPrev().next = n.getNext();
        n.getNext().prev = n.getPrev();
        --size;
        return n.getData();
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.next.getData();
    }

    public E last() {
        return tail.getPrev().getData();
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }
        return remove(head.getNext());
    }

    @Override
    public E removeLast() {
        if(isEmpty()){
            return null;
        }
        return remove(tail.getPrev());
    }

    @Override
    public void addLast(E e) {
        addBetween(e, tail.getPrev(), tail);
        ++size;
    }

    @Override
    public void addFirst(E e) {
        addBetween(e, head, head.getNext());
        ++size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addLast(-1);
        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }
    }
}