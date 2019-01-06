package week3; // DON'T FORGET TO REMOVE IT FOR SUBMISSION!

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Program that examines 4 points at a time and checks whether they all lie on the same
 * line segment, returning all such line segments.
 *
 * @author HaoyangFan
 * @version 1.0
 * @see <a href="http://coursera.cs.princeton.edu/algs4/assignments/collinear.html">Assignment Spec</a>
 * @since 01-05-2019
 */
public class FastCollinearPoints {
    private final LineSegment[] segments;

    /**
     * Find all line segments containing 4 points.
     *
     * @param points input to be examined
     * @throws IllegalArgumentException if input array in null or any of points is null
     *                                  or there is a repeated point
     */
    public FastCollinearPoints(Point[] points) {
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
        /*
         * throw IllegalArgumentException if the argument to the constructor is null
         * or if any point in the array is null
         * or if the argument to the constructor contains a repeated point
         */
        if (!isValid(points)) {
            throw new IllegalArgumentException("input points are invalid");
        }

        List<LineSegment> temp = new ArrayList<>();
        search(points, temp);
        segments = temp.toArray(new LineSegment[0]);
    }

    private void search(Point[] points, List<LineSegment> list) {
        // for each iteration, find all line segments that can be made with i
        for (int i = 0; i < points.length - 3; i++) { // O(n)
            // at start of each iteration, re-sort the points based on their natural order
            // in order to ensure we won't re-compute everything again on the same point
            if (i != 0) {
                Arrays.sort(points); // O(nlogn)
            }
            Point p = points[i];

            // get the comparator based on point[i]
            Comparator<Point> baseComp = p.slopeOrder();

            // extend this comparator to break the tie when multiple point has
            // the same slope with respect to points[i]. In that case, the point
            // that is "largest" will be put at the end, and the point that is
            // "smallest" will be at the front (with respect to their natural order)
            Comparator<Point> comp = new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    int rst = baseComp.compare(o1, o2);
                    return rst == 0 ? o1.compareTo(o2) : rst;
                }
            };

            // sort all points based on their slopes with respect to points[i]
            Arrays.sort(points, comp); // O(nlogn)

            // get the slope of first element with respect to points[i]
            double temp = p.slopeTo(points[0]);
            int startIdx = 0;
            for (int j = 1; j < points.length; j++) {
                // compare the slope of current point w/r to points[i] with temp
                // in case it is equal, just continue
                if (Double.compare(p.slopeTo(points[j]), temp) == 0) {
                    continue;
                }

                // in case current slope is not equal to previous slope
                // only add if point at startIdx is greater than p
                if (j - startIdx >= 3 && points[startIdx].compareTo(p) > 0) {
                    list.add(new LineSegment(p, points[j - 1]));
                }
                temp = p.slopeTo(points[j]);
                startIdx = j;
            }
            if (points.length - startIdx >= 3 && points[startIdx].compareTo(p) > 0) {
                list.add(new LineSegment(p, points[points.length - 1]));
            }
        }
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
        return segments.clone();
    }
}
