package week3; // DON'T FORGET TO REMOVE IT FOR SUBMISSION!

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Program that examines 4 points at a time and checks whether they all lie on the same
 * line segment, returning all such line segments.
 * To check whether the 4 points p, q, r, and s are collinear, check whether
 * the three slopes between p and q, between p and r, and between p and s are all equal
 *
 * @author HaoyangFan
 * @version 1.0
 * @since 01-05-2019
 */
public class BruteCollinearPoints {
    private final LineSegment[] segments;

    /**
     * Find all line segments containing 4 points.
     *
     * @param points input to be examined
     * @throws IllegalArgumentException if input array in null or any of points is null
     *                                  or there is a repeated point
     */
    public BruteCollinearPoints(Point[] points) {
        // first is to ensure points cannot be null
        if (points == null) {
            throw new IllegalArgumentException("input points are invalid");
        }
        // then we need to ensure each point cannot be null
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("input points are invalid");
            }
        }
        points = points.clone();
        Arrays.sort(points);

        // throw IllegalArgumentException if the argument to the constructor contains a repeated point
        if (!isValid(points)) {
            throw new IllegalArgumentException("input points are invalid");
        }

        List<LineSegment> temp = new ArrayList<>();
        search(points, temp);
        segments = temp.toArray(new LineSegment[0]);
    }

    private boolean isValid(Point[] points) {
        // iterate through array to check if there is a duplicate point
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return false;
            }
        }
        return true;
    }

    private void search(Point[] points, List<LineSegment> list) {
        // assume: i, j, x, y are four points of line segment
        // i is the smallest point, j is the next smallest .. and y is the largest point
        double temp;
        // find the starting point
        for (int i = 0; i < points.length - 3; i++) {
            // find the second point
            for (int j = i + 1; j < points.length - 2; j++) {
                // calculate the slope between i and j: slope(i, j)
                temp = points[i].slopeTo(points[j]);
                for (int x = j + 1; x < points.length - 1; x++) {
                    // compare the slope between i and x with slope between i and j
                    if (points[i].slopeTo(points[x]) != temp) {
                        continue;
                    }
                    for (int y = x + 1; y < points.length; y++) {
                        // compare the slope between i and y with slope between i and j
                        if (points[i].slopeTo(points[y]) != temp) {
                            continue;
                        }
                        // add i,j,x,y to the final result, using i as start point and y as the end point
                        list.add(new LineSegment(points[i], points[y]));
                    }
                }
            }
        }
    }

    /**
     * Get the number of line segments that contains 4 points.
     *
     * @return the number of line segments with 4 points
     */
    public int numberOfSegments() {
        return segments.length;
    }


    /**
     * Get the array that include each line segment that contains 4 points.
     *
     * @return the array containing all line segments with 4 points
     */
    public LineSegment[] segments() {
//         NOTE: array clone in Java: it will allocate new space on Heap (recall
//         that java array is object) with the same size as the one being cloned.
//         If the array being cloned is primitive type, it works exactly as deep
//         copy. However, if the array being cloned is reference type (Object),
//         then array.clone will only copy the reference instead of clone each
//         individual object as Python's copy.deepcopy
        return segments.clone();
    }
}

