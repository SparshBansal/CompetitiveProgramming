import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StrangePrinter {
    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
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

    public static class Pair {
        private Integer l, r;

        public Pair(Integer l, Integer r) {
            this.l = l;
            this.r = r;
        }

        public Integer l() {
            return l;
        }
        public Integer r() {
            return r;
        }

        @Override
        public boolean equals(Object obj) {
            // TODO Auto-generated method stub
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Pair)) {
                return false;
            }
            Pair other = (Pair)obj;
            return other.l() == this.l() && other.r() == this.r();
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.l(), this.r());
        }
    }

    public List<Pair> getUnfilledRanges(String input, Pair range, char c) {
        List<Pair> ranges = new ArrayList<>();
        int len = range.r() - range.l() + 1;
        boolean rangeStarted = false;
        int startOfRange = -1;
        for (int i=0; i<len; i++) {
            char in = input.charAt(range.l() + i);
            if (in == c && rangeStarted) {
                rangeStarted = false;
                ranges.add(new Pair(startOfRange, range.l() + i - 1));
                continue;
            }
            if (in != c && (!rangeStarted)) {
                // start a new range
                rangeStarted = !rangeStarted;
                startOfRange = range.l() + i;
            }
        }

        if (rangeStarted) {
            ranges.add(new Pair(startOfRange, range.l() + len - 1));
        }
        return ranges;
    }

    public int solve(String input, Map<Pair, Integer> dp, Pair range) {
        // System.out.println("Solving for (l, r) = " + range.l() + " " + range.r());
        Integer val = dp.get(range);
        if (val != null) {
            return val;
        }
        // now we need to solve this
        if (range.r() - range.l() + 1 == 1) {
            dp.put(new Pair(range.r(), range.l()), 1);
            return 1;
        }
        int minimum = Integer.MAX_VALUE;
        // now we find the ranges for each split
        for (char c = 'a'; c <= 'z'; c++) {
            // check if we should solve for this char
            // fill in characters
            // check if the character is not even in this range
            boolean contains = false;
            for (int i=range.l(); i<= range.r(); i++) {
                if (input.charAt(i) == c) {
                    contains = true;
                    break;
                }
            }
            if (!contains) {
                continue;
            }
            List<Pair> missedRanges = this.getUnfilledRanges(input, range, c);
            int total = 1;
            for (Pair missedRange: missedRanges) {
                total += solve(input, dp, missedRange);
            }
            minimum = Math.min(minimum, total);
        }
        dp.put(range, minimum);
        return minimum;
    }

    public int solve(String input) {
        // now we need to create a dp map
        Map<Pair, Integer> dp = new HashMap<>();   
        return this.solve(input, dp, new Pair(0, input.length() - 1));
    }

    public static void main(String[] args) {
        StrangePrinter.FastReader reader = new StrangePrinter.FastReader();
        System.out.println(new StrangePrinter().solve(reader.nextLine()));
    }
}
