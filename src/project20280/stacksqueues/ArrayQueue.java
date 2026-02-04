package project20280.stacksqueues;

import project20280.interfaces.Queue;

import java.util.Arrays;

public class ArrayQueue<E> implements Queue<E> {

    private static final int CAPACITY = 1000;
    private E[] data;
    private final int front = 0;
    private int size = 0;

    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];

    }

    public ArrayQueue() {
        this(CAPACITY);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(){
        if(data.length == CAPACITY){
            throw new IndexOutOfBoundsException();
        }
        int new_length = size*2+1;
        if(new_length > CAPACITY){
            new_length = CAPACITY;
        }
        data = Arrays.copyOf(data, new_length);
    }
    @Override
    public void enqueue(E e) {
        if(size >= data.length){
            resize();
        }
        data[size++] = e;
    }

    @Override
    public E first() {
        return isEmpty() ? null : data[front];
    }

    @Override
    public E dequeue() {
        E result = data[0];
        for(int i=1; i<size; ++i){
            data[i-1]=data[i];
        }
        --size;
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; ++i) {
            E res = data[(front + i) % CAPACITY];
            sb.append(res);
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> qq = new ArrayQueue<>();
        System.out.println(qq);

        int N = 10;
        for (int i = 0; i < N; ++i) {
            qq.enqueue(i);
        }
        System.out.println(qq);

        for (int i = 0; i < N / 2; ++i) qq.dequeue();
        System.out.println(qq);

    }
}
