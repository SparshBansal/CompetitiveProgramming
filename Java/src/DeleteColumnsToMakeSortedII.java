import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DeleteColumnsToMakeSortedII {
    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

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

    public static class Pair<T> {
        private T _1, _2;

        public T _1() {
            return _1;
        }

        public T _2() {
            return _2;
        }

        public Pair(T _1, T _2) {
            this._1 = _1;
            this._2 = _2;
        }
    }


    private int solve(String[] strs, int r, int c, int startCol, List<Pair<Integer>> ranges) {
        if (ranges.isEmpty() || startCol >= c) {
            return 0;
        }
        // solve for ranges starting from start col
        // 1. figure out if the current col is sorted in ranges,
        // 2. if yes split the ranges into furthur subranges, else
        // 3. recursively call on the subproblem with the same set of ranges
        if (isSorted(strs, r, c, startCol, ranges)) {
            // split into subranges
            List<Pair<Integer>> subranges = split(strs, r, c, startCol, ranges);
            // solve recursively
            return solve(strs, r, c, startCol+1, subranges);
        } else {
            // remove this columns and check recursively
            return 1 + solve(strs, r, c, startCol+1, ranges);
        }
    }

    private List<Pair<Integer>> split(String[] strs, int r, int c, int col, List<Pair<Integer>> ranges) {
        List<Pair<Integer>> subranges = new ArrayList<>();
        // we assume that the array is sortable in the specified range
        for (int i=0, j=0; j < ranges.size(); j++) {
            Pair<Integer> currentRange = ranges.get(j);
            while (i < currentRange._1()) {
                i++;
            }
            // iterate over this range and try to split it into subranges
            Pair<Integer> currentSubrange = new Pair<>(i, col);
            int prev = strs[i++].charAt(col);

            while (i <= currentRange._2()) {
                if (strs[i].charAt(col) != prev) {
                    if (((i-1) - currentSubrange._1()) > 0) {
                        // this can be extracted as a subrange
                        currentSubrange._2 = (i-1);
                        subranges.add(currentSubrange);
                    }
                    // now we can start a new subrange
                    currentSubrange = new Pair<>(i, col);
                }
                prev = strs[i++].charAt(col);
            }
            // this is for trailing subranges
            if (((i-1) - currentSubrange._1()) > 0) {
                // this can be extracted as a subrange
                currentSubrange._2 = (i-1);
                subranges.add(currentSubrange);
            }
        }
        return subranges;
    }

    private boolean isSorted(String[] strs, int r, int c, int col, List<Pair<Integer>> ranges) {
        // to check if this is sorted within the ranges
        for (int i=0, j=0; j < ranges.size(); j++) {
            // while current i is less than start of range
            Pair<Integer> currentRange = ranges.get(j);
            while (i < currentRange._1()) {
                i++;
            }
            int prev = strs[i++].charAt(col);
            while (i <= currentRange._2()) {
                if (strs[i].charAt(col) < prev) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    public int minDeletionSize(String[] strs) {
        // num rows
        int rows = strs.length;
        int cols = strs[0].length();

        List<Pair<Integer>> ranges = new ArrayList<>();
        ranges.add(new Pair<>(0, rows-1));
        return solve(strs, rows, cols, 0, ranges);
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(new FileInputStream(new File("input.txt")));
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            String[] ar = new String[n];

            for (int i=0; i<n; i++) {
                ar[i] = scanner.nextLine();
            }
            System.out.println(new DeleteColumnsToMakeSortedII().minDeletionSize(ar));
        }
    }
}
