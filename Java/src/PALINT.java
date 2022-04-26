import java.io.*;
import java.util.*;

public class PALINT {
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

    private class Solution {
        int val, switches;

        public Solution(int val, int switches) {
            this.val = val;
            this.switches = switches;
        }
    }

    public Solution solve(int[] a, int n, int x) {
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int xor = a[i] ^ x;
            left.put(a[i], left.computeIfAbsent(a[i], k -> 0) + 1);
            right.put(xor, right.computeIfAbsent(xor, k -> 0) + 1);
        }

        if (x == 0) {
            // we can then just return the max value with 0
            return new Solution(left.values().stream().max(Comparator.comparingInt(val -> val)).orElse(0),
                    0);
        }
        // we can find the number which will be occurring the maximum number of times
        Set<Integer> keySet = new HashSet<>(left.keySet());
        keySet.addAll(right.keySet());

        // now we have an exhaustive keyset
        int maxVal = 0, maxKey = 0;
        for (int key : keySet) {
            int total = left.getOrDefault(key, 0) + right.getOrDefault(key, 0);
            if (total > maxVal) {
                maxVal = total;
                maxKey = key;
            }
        }
        // if max key is also 0, that means we cannot get more than 1 switch possible
        if (maxVal == 1) {
            return new Solution(1, 0);
        }
        // so we need to find the number of switches needed to be done
        return new Solution(maxVal, right.getOrDefault(maxKey, 0));
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int x = scanner.nextInt();
            int[] ar = new int[n];
            for (int i = 0; i < n; i++) ar[i] = scanner.nextInt();
            Solution solution = new PALINT().solve(ar, n , x);
            System.out.println(solution.val + " " + solution.switches);
        }
    }
}
