package project20280.exercises;

import java.util.HashMap;

public class Wk6 {
    private static HashMap<Long, Long> known = new HashMap<Long, Long>();

    public static long FibonacciNoMemorisation(long n){
        if(n < 2){
            return n;
        }
        return FibonacciNoMemorisation(n-2) + FibonacciNoMemorisation(n-1);
    }

    public static long FibonacciMemorisation(long n){
        if(n < 2){
            return n;
        }
        if(known.containsKey(n)){
            return known.get(n);
        }
        long res = FibonacciMemorisation(n-2) + FibonacciMemorisation(n-1);
        known.put(n, res);
        return res;
    }

    public static void emptyKnown(){
        known.clear();
    }

    public static long Tribonacci(long n){
        if(n <= 0){
            return 0;
        }
        if(n <= 2){
            return 1;
        }
        return Tribonacci(n-3)+Tribonacci(n-2)+Tribonacci(n-1);
    }

    public static void main(String[] args){
//        long start, timeTaken;
//        long res;
//        for(int i=0; i<=10000; i+=20) {
//            System.out.println("i = " + i);
//            emptyKnown();
//            start = System.currentTimeMillis();
//            res = FibonacciMemorisation(i);
//            timeTaken = System.currentTimeMillis()-start;
//            System.out.println("Memorisation: res = " + res + ", time taken = " + timeTaken);
//
//            start = System.currentTimeMillis();
//            res = FibonacciNoMemorisation(i);
//            timeTaken = System.currentTimeMillis()-start;
//            System.out.println("No Memorisation: res = " + res + ", time taken = " + timeTaken);
//        }

        System.out.println(Tribonacci(9));
    }
}
