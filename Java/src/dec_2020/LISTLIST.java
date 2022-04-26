package dec_2020;

import java.io.*;
import java.util.*;

public class LISTLIST {
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

    public int solve(int[] ar, int n) {
        if (n == 1) {
            return 0;
        }
        // solve for this array
        Map<Integer, Integer> counts = new HashMap<>();
        for (int el : ar) {
            counts.put(el, counts.getOrDefault(el, 0) + 1);
        }
        Optional<Integer> maxValue = counts.values().stream().max(Comparator.comparingInt(l -> l));
        // if only 1 occurence of each number
        if (maxValue.get() == 1) {
            return -1;
        }
        if (maxValue.get() == n) {
            return 0;
        }
        Optional<Integer> key = counts.entrySet().stream().filter(entry -> entry.getValue().equals(maxValue.get()))
                .findFirst().map(Map.Entry::getKey);
        int operations = counts.values().stream().mapToInt(val -> val).sum() - counts.get(key.get());
        return operations + 1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] ar = new int[n];
            for (int i = 0; i<n; i++) {
                ar[i] = scanner.nextInt();
            }
            System.out.println(new LISTLIST().solve(ar, n));
        }
    }
}
