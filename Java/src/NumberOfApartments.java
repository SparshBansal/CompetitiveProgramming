import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class NumberOfApartments {
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

    private static class ACounts {

        public static final ACounts INVALID = new ACounts(-1, -1, -1);

        int count3, count5, count7;

        public ACounts(int count3, int count5, int count7) {
            this.count3 = count3;
            this.count5 = count5;
            this.count7 = count7;
        }

        public int getCount3() {
            return count3;
        }

        public void setCount3(int count3) {
            this.count3 = count3;
        }

        public int getCount5() {
            return count5;
        }

        public void setCount5(int count5) {
            this.count5 = count5;
        }

        public int getCount7() {
            return count7;
        }

        public void setCount7(int count7) {
            this.count7 = count7;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            ACounts aCounts = (ACounts) object;
            return count3 == aCounts.count3 &&
                    count5 == aCounts.count5 &&
                    count7 == aCounts.count7;
        }

        @Override
        public int hashCode() {
            return Objects.hash(count3, count5, count7);
        }

        @Override
        public String toString() {
            if (this.equals(ACounts.INVALID)) {
                return "-1";
            }
            return count3 + " " + count5 + " " + count7;
        }
    }


    // check for top down
    private ACounts solveDP(Map<Integer, ACounts> dp, int windows) {
        // check if computed already
        if (dp.containsKey(windows)) {
            return dp.get(windows);
        }

        // compute base case
        if (windows == 3) {
            dp.put(windows, new ACounts(1, 0, 0));
            return dp.get(windows);
        }
        if (windows == 5) {
            dp.put(windows, new ACounts(0, 1, 0));
            return dp.get(windows);
        }
        if (windows == 7) {
            dp.put(windows, new ACounts(0, 0, 1));
            return dp.get(windows);
        }
        // now check for invalid base case
        if (windows < 3) {
            // not possible return
            dp.put(windows, ACounts.INVALID);
            return ACounts.INVALID;
        }

        // now we have 3 possible cases for windows
        dp.put(windows, getSolution(dp, windows));
        return dp.get(windows);
    }

    private ACounts getSolution(Map<Integer, ACounts> dp, int windows) {
        ACounts subproblem = solveDP(dp, windows - 3);
        if (!subproblem.equals(ACounts.INVALID)) {
            // we can just return this solution
            return new ACounts(subproblem.getCount3() + 1, subproblem.getCount5(), subproblem.getCount7());
        }
        subproblem = solveDP(dp, windows - 5);
        if (!subproblem.equals(ACounts.INVALID)) {
            // we can just return this solution
            return new ACounts(subproblem.getCount3(), subproblem.getCount5() + 1, subproblem.getCount7());
        }
        subproblem = solveDP(dp, windows - 7);
        if (!subproblem.equals(ACounts.INVALID)) {
            // we can just return this solution
            return new ACounts(subproblem.getCount3(), subproblem.getCount5(), subproblem.getCount7() + 1);
        }
        return ACounts.INVALID;
    }


    public ACounts solve(int n) {
        // create a simple dp lookup table
        Map<Integer, ACounts> dp = new HashMap<>();
        return solveDP(dp, n);
    }


    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            System.out.println(new NumberOfApartments().solve(n));
        }
    }
}
