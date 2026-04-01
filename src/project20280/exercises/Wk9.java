package project20280.exercises;

import project20280.hashtable.ChainHashMap;
import project20280.interfaces.Entry;
import project20280.priorityqueue.HeapPriorityQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Wk9 {
    public static void Q5() throws FileNotFoundException {
        File f = new File("/home/imelkar/VS project files/DS/datastructures2-imelkar/src/project20280/exercises/sample_text.txt"); // check the path to the file
        ChainHashMap<String, Integer> counter = new ChainHashMap<String, Integer>();
        // use a Scanner to read words from the file
        Scanner scanner = new Scanner(f);
        while(scanner.hasNext()) { // read the file word at a time
            String word = scanner.next();
            System.out.println("word:" + word);

            // if word is not in the hashmap, add it with count=1
            // otherwise, find the entry for this word and increment by 1
            Integer curCount = counter.get(word);
            if(curCount == null){
                curCount = 0;
            }
            ++curCount;
            counter.put(word, curCount);
        }

        // sort the key, values...
        // Can you sort the Entries by the value?
        int len = counter.size();
        int k = 10, i = 0;
        HeapPriorityQueue<Integer, String> h = new HeapPriorityQueue<>();
        Iterable<Entry<String, Integer>> it = counter.entrySet();
        for(Entry<String, Integer> e: it){
            if(i < k){
                h.insert(e.getValue(), e.getKey());
            }
            else if(e.getValue() > h.min().getKey()){
                h.removeMin();
                h.insert(e.getValue(), e.getKey());
            }
            ++i;
        }
        while(!h.isEmpty()){
            Entry<Integer, String> temp = h.removeMin();
            System.out.println(temp.getKey() + " - " + temp.getValue());
        }
    }

    public static void Q6() throws FileNotFoundException {
        File f = new File("/home/imelkar/VS project files/DS/datastructures2-imelkar/src/project20280/exercises/words.txt");
        ChainHashMap<Integer, Boolean> counterA = new ChainHashMap<Integer, Boolean>();
        ChainHashMap<Integer, Boolean> counterB = new ChainHashMap<Integer, Boolean>();
        ChainHashMap<Integer, Boolean> counterC = new ChainHashMap<Integer, Boolean>();
        ChainHashMap[] counterD = new ChainHashMap[32];
        for(int i=0; i<32; ++i){
            counterD[i] = new ChainHashMap<Integer, Boolean>();
        }
        ChainHashMap<Integer, Boolean> counterE = new ChainHashMap<Integer, Boolean>();
        int collCounterA = 0, collCounterB = 0, collCounterC = 0, collCounterE = 0;
        int[] collCounterD = new int[32];
        for(int i=0; i<32; ++i){
            collCounterD[i] = 0;
        }
        Scanner scanner = new Scanner(f);

        while(scanner.hasNext()) {
            String word = scanner.next();

            int resA = hash_poly(word, 41);

            if(counterA.get(resA) == null){
                counterA.put(resA, true);
            }
            else{
                ++collCounterA;
            }

            int resB = hash_poly(word, 17);

            if(counterB.get(resB) == null){
                counterB.put(resB, true);
            }
            else{
                ++collCounterB;
            }

            int resC = hash_cyclic(word, 7);
            if(counterC.get(resC) == null){
                counterC.put(resC, true);
            }
            else{
                ++collCounterC;
            }

            for(int j = 0; j < 32; ++j){
                int resD = hash_cyclic(word, j);
                if(counterD[j].get(resD) == null){
                    counterD[j].put(resD, true);
                }
                else{
                    ++collCounterD[j];
                }
            }

            int resE= hashCode(word);
            if(counterE.get(resE) == null){
                counterE.put(resE, true);
            }
            else{
                ++collCounterE;
            }
        }

        System.out.println("(a) Number of collisions: " + collCounterA);
        System.out.println("(b) Number of collisions: " + collCounterB);
        System.out.println("(c) Number of collisions: " + collCounterC);
        System.out.println("(d) Number of collisions: ");
        for(int i=0; i<32; ++i) {
            System.out.println(i + " - " + collCounterD[i]);
        }
        System.out.println("(e) Number of collisions: " + collCounterE);
    }

    public static int hashCode(String s) {
        int hash = 0;
        int skip = Math.max(1, s.length() / 8);
        for (int i = 0; i < s.length(); i += skip)
            hash = (hash * 37) + s.charAt(i);
        return hash;
    }

    public static int hash_poly(String s, int a) {
        int h = 0;
        int n = s.length();
        for(int i=0; i<n; i++) {
            char s_i = (char) s.charAt(i);
            int v = s_i * ((int) Math.pow(a, n - i - 1));
            h += v;
        }
        return h;
    }

    public static int hash_cyclic(String s, int shift) {
        int h = 0;
        for(int i = 0; i < s.length(); ++i) {
            h = (h << shift) | (h >>> (32 - shift));
            h += (int) s.charAt(i);
        }
        return h;
    }

    static public void main() throws FileNotFoundException {
        Wk9.Q5();
//        Wk9.Q6();
    }
}
