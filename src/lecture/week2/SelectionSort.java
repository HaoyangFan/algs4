package lecture.week2;

import java.util.Arrays;

/**
 * Selection Sort
 */
public class SelectionSort {

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
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
        Integer[] unsorted = new Integer[]{5, 1, 0, 3, -1, -1, 6, 2, 4, -1};
        SelectionSort.sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
        Integer[] sorted = new Integer[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 6, 7, 8};
        SelectionSort.sort(sorted);
        System.out.println(Arrays.toString(sorted));
        Integer[] reversed = new Integer[]{9, 9, 8, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        SelectionSort.sort(reversed);
        System.out.println(Arrays.toString(reversed));
    }
}
