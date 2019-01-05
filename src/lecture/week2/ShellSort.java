package lecture.week2;

import java.util.Arrays;

/**
 * Shell Sort implementation
 */
public class ShellSort {
    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        // use 3x + 1 gap sequence
        while (h < n / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h /= 3;
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] unsorted = new Integer[]{7, 1, 0, 2, -1, -1, 6, 2, 4, -1};
        InsertionSort.sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
        Integer[] sorted = new Integer[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 6, 7, 8};
        InsertionSort.sort(sorted);
        System.out.println(Arrays.toString(sorted));
        Integer[] reversed = new Integer[]{9, 9, 8, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        InsertionSort.sort(reversed);
        System.out.println(Arrays.toString(reversed));
    }

}

