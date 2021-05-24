import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ComplicatedComputations {
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

    public int solve(int ar[]) {
        int n = ar.length;

        int countOfOne = 0;
        for (int value : ar) {
            if (value == 1) {
                countOfOne++;
            }
        }
        if (countOfOne == 0 || countOfOne == n) {
            return 1;
        }

        int lb = 2;
        int ub = n + 2;
        int ans = ub;

        while (lb < ub) {
            // check if mex can be formed from the middle
            int mid = lb + (ub - lb) / 2;
            // check if mid can contain in the possible mex set
            if (isMexPossible(ar, mid)) {
                // move the counter to the right half
                lb = mid + 1;
            } else {
                // if mex is not possible check if mex-1 is possible
                if (isMexPossible(ar, mid - 1)) {
                    ans = mid;
                    break;
                }
                // otherwise move the pointer to left half
                ub = mid - 1;
            }
        }
        return ans;
    }

    private boolean isMexPossible(int[] ar, int mex) {
        int uLimit = mex - 1;

        // elements to remove
        // elements array

        Map<Integer, Integer> counts = new HashMap<>();

        // consider rolling windows
        int lp = 0, up = 0;

        if (ar[up] <= uLimit) {
            counts.put(ar[up], 1);
        }

        // check if mex got satisfied
        if (counts.size() == uLimit) {
            return true;
        }

        while (up < ar.length - 1) {
            up = up + 1;
            if (ar[up] <= uLimit) {
                counts.put(ar[up], counts.computeIfAbsent(ar[up], k -> 0) + 1);
            }
            if ((up - lp + 1) > uLimit) {
                if (ar[lp] <= uLimit) {
                    if (counts.get(ar[lp]) == 1) {
                        // remove the key
                        counts.remove(ar[lp]);
                    } else {
                        counts.put(ar[lp], counts.get(ar[lp]) - 1);
                    }
                }
                lp++;
            }
            if (counts.size() == uLimit) {
                return true;
            }
        }
        return false;
    }


    private boolean contains(int[] ar, int val) {
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] == val) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int n = scanner.nextInt();
        int[] ar = new int[n];

        for (int i = 0; i < n; i++)
            ar[i] = scanner.nextInt();

        System.out.println(new ComplicatedComputations().solve(ar));
    }
}
