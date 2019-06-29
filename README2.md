# algs4
Coursera Algorithms Part I by Princeton University

## Programming Assignments

- week 1, grade: 100:
    - [x] [Percolation](./src/week1/Percolation.java)
    - [x] [PercolationStats](./src/week1/PercolationStats.java)
    
- week 2, grade: 100:
    - [x] [Deque](./src/week2/Deque.java)
    - [x] [RandomizedQueue](./src/week2/RandomizedQueue.java)
    - [x] [Permutation](./src/week2/Permutation.java)
    
- week 3, grade: 100:
    - [x] [Point](./src/week3/Point.java)
    - [x] [FastCollinearPoints](./src/week3/FastCollinearPoints.java)
    - [x] [BruteCollinearPoints](./src/week3/BruteCollinearPoints.java)

## Lecture Code

- week 2:
    - [x] [ArrayStack](./src/lecture/week2/ArrayStack.java)
    - [x] [LinkedQueue](./src/lecture/week2/LinkedQueue.java)
    - [x] [LinkedStack](./src/lecture/week2/LinkedStack.java)
    - [x] [Selection Sort](./src/lecture/week2/SelectionSort.java)
    - [x] [Insertion Sort](./src/lecture/week2/InsertionSort.java)
    - [x] [Shell Sort](./src/lecture/week2/ShellSort.java)
    - [ ] TODO: ArrayQueue 
    
- week 3:
    - [x] [Merge Sort](./src/lecture/week3/MergeSort.java)
    
## Study Notes

- week 3:
    - Merge sort is a `Divide and Conquer` algorithm and break the problem of sorting
    current array into sub-problems of sorting two subarrays. It will recursively 
    sort the subarrays and eventually merge them back together into one single sorted
    array.
    - Merge sort takes `O(nlogn)` comparisons at most. Proof is given in the lecture slides
    of merge sort
    - Merge sort will use extra memory space so it takes `O(n)` auxiliary space.
    - In theory, the lowest bound for any comparison-based sorting algorithm is 
    doing `O(nlogn)` comparisons. Since merge sort do `O(nlogn)` comparisons in 
    the worst case, it is one of the optimal sorting algorithms
    - [Comparator](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)
    allow us to customize sorting order for a data type so that it is possible for 
    us to define multiple sorting orders for the same data type.
    - NOTE: array's `clone()` in Java: it will allocate new space on Heap (recall 
    that java array is object) with the same size as the one being cloned.
    If the array being cloned is primitive type, it works exactly as deep
    copy. However, if the array being cloned is reference type (Object),
    then array.clone will only copy the reference instead of clone each
    individual object as Python's `copy.deepcopy`
    
    