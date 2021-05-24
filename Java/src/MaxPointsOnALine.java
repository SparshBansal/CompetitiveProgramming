import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MaxPointsOnALine {

    static class Solution {
        private class SlopeIntercept {
            double slope, intercept;

            public SlopeIntercept(double slope, double intercept) {
                this.slope = slope;
                this.intercept = intercept;
            }

            @Override
            public boolean equals(Object object) {
                if (this == object) return true;
                if (object == null || getClass() != object.getClass()) return false;
                SlopeIntercept that = (SlopeIntercept) object;
                return Double.compare(that.slope, slope) == 0 &&
                        Double.compare(that.intercept, intercept) == 0;
            }

            @Override
            public int hashCode() {
                return Objects.hash(slope, intercept);
            }
        }

        public int maxPoints(int[][] points) {
            // 1. find slopes and intercepts
            // 2. sort by slopes and intercepts
            // 3. all points with same slope and intercept lie on the same line
            Map<SlopeIntercept, Integer> cnts = new HashMap<>();

            int ans = 0;

            // for all possible pairs
            for (int i = 0; i < points.length - 1; i++) {
                for (int j = i + 1; j < points.length; j++) {
                    int[] pointA = points[i];
                    int[] pointB = points[j];

                    // find the slope
                    final double slope = getSlope(pointA, pointB);

                    // find the intercept
                    final double intercept = getIntercept(pointA, pointB);

                    SlopeIntercept slopeIntercept = new SlopeIntercept(slope, intercept);
                    final int val = cnts.computeIfAbsent(slopeIntercept, si -> 0);
                    cnts.put(slopeIntercept, val + 1);
                    ans = Math.max(ans, val + 1);
                }
            }

            final int root = (int) Math.sqrt(ans);
            int linearPoints = 0;
            for (int i=1; i<=root+1; i++) {
                if (((i) * (i+1))/2 == ans) {
                    linearPoints = i+1;
                }
            }

            return linearPoints;
        }

        private double getIntercept(int[] pointA, int[] pointB) {
            final double slope = getSlope(pointA, pointB);
            if (Double.compare(slope, Double.MAX_VALUE) == 0) {
                // if slope is inf , return x intercept as the intercept
                return pointA[0];
            }
            // else we compute the slope
            return (pointA[1] - (slope * pointA[0]));
        }

        private double getSlope(int[] pointA, int[] pointB) {
            final double deltaY = pointA[1] - pointB[1];
            final double deltaX = pointA[0] - pointB[0];
            if (deltaX == 0) {
                return Double.MAX_VALUE;
            }
            return deltaY / deltaX;
        }
    }

    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int[][] points = new int[n][2];

            for (int i=0; i<n; i++) {
                points[i][0] = scanner.nextInt();
                points[i][1] = scanner.nextInt();
            }

            Solution solution = new Solution();
            System.out.println(solution.maxPoints(points));
        }
    }
}
