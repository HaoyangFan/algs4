package week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * takes an integer k as a command-line argument;
 * reads in a sequence of strings from standard input using StdIn.readString();
 * and prints exactly k of them, uniformly at random. Print each item from the sequence at most once.
 *
 * @author HaoyangFan
 * @version 1.0
 * @since 12-26-2018
 */
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> r = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            r.enqueue(StdIn.readString());
        }
        int i = 0;
        Iterator<String> iter = r.iterator();
        while (iter.hasNext() && i++ < k) {
            StdOut.println(iter.next());
        }
    }
}
