import com.sun.org.apache.bcel.internal.generic.ARETURN;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class DAREA {
    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

        public FastReader(String[] args) throws FileNotFoundException {
            this(args.length > 0 ? new FileInputStream(new File(args[0])) : System.in);
        }

        public FastReader(InputStream inputStream) {
            br = new BufferedReader(new
                    InputStreamReader(inputStream));
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

    public static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public long solve(List<Point> points, int n) {
        if (n < 3) {
            return 0;
        }
        // we can split them horizontally or vertically
        long minAreaHorizontalSplit = splitHorizontally(points, n);
        long minAreaVerticalSplit = splitVertically(points, n);
        return Math.min(minAreaHorizontalSplit, minAreaVerticalSplit);
    }

    private class Tuple {
        long _1, _2, _3;

        public Tuple(long _1, long _2, long _3) {
            this._1 = _1;
            // min
            this._2 = _2;
            // max
            this._3 = _3;
        }
    }

    private long splitVertically(List<Point> points, int n) {
        // sort by x
        points.sort(Comparator.comparingLong(l -> l.y));
        List<Tuple> condensed = new ArrayList<>();

        Tuple prev = new Tuple(points.get(0).y, points.get(0).x, points.get(0).x);
        for (int i=1; i<n; i++) {
            final Point current = points.get(i);
            if (current.y == prev._1) {
                prev._2 = Math.min(prev._2, current.x);
                prev._3 = Math.max(prev._3, current.x);
            } else {
                condensed.add(prev);
                prev = new Tuple(current.y, current.x, current.x);
            }
        }
        condensed.add(prev);
        return minMinAreaSplit(condensed);
    }

    private long splitHorizontally(List<Point> points, int n) {

        // sort by x
        points.sort(Comparator.comparingLong(l -> l.x));
        List<Tuple> condensed = new ArrayList<>();

        Tuple prev = new Tuple(points.get(0).x, points.get(0).y, points.get(0).y);
        for (int i=1; i<n; i++) {
            final Point current = points.get(i);
            if (current.x == prev._1) {
                prev._2 = Math.min(prev._2, current.y);
                prev._3 = Math.max(prev._3, current.y);
            } else {
                condensed.add(prev);
                prev = new Tuple(current.x, current.y, current.y);
            }
        }
        condensed.add(prev);
        return minMinAreaSplit(condensed);
    }

    private long minMinAreaSplit(List<Tuple> condensed) {
        int sz = condensed.size();

        if (sz == 1) {
            return 0;
        }

        Tuple[] suffix = new Tuple[sz];

        suffix[sz-1] = condensed.get(sz-1);
        for (int i=sz-2; i>=0; i--) {
            final Tuple current = condensed.get(i);
            suffix[i] = new Tuple(current._1, Math.min(current._2, suffix[i+1]._2), Math.max(current._3, suffix[i+1]._3));
        }

        long start = condensed.get(0)._1;
        long minEl = condensed.get(0)._2;
        long maxEl = condensed.get(0)._3;

        long minAns = Long.MAX_VALUE;

        for (int i = 0; i < sz - 1; i++) {
            Tuple current = condensed.get(i);
            // assume the current point is split of first
            long end = current._1;
            minEl = Math.min(minEl, current._2);
            maxEl = Math.max(maxEl, current._3);

            // area1
            long areaLeft = (end - start) * (maxEl - minEl);
            long areaRight = (condensed.get(sz - 1)._1 - condensed.get(i + 1)._1) * (suffix[i+1]._3 - suffix[i+1]._2);
            minAns = Math.min(minAns, areaLeft + areaRight);
        }
        return minAns;
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            List<Point> points = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                long x = scanner.nextLong();
                long y = scanner.nextLong();
                points.add(new Point(x, y));
            }
            System.out.println(new DAREA().solve(points, n));
        }
    }
}
