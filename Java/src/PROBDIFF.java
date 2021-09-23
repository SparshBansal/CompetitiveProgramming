import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

class PROBDIFF {
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


    public int solve(int a1, int a2, int a3, int a4) {
        // now we need to solve
        // how many different problem sets we can create with
        // pick a1
        int[] ar = new int[]{a1, a2, a3, a4};
        Arrays.sort(ar);

        HashSet<Integer> set = new HashSet<>();

        for (int el : ar) {
            set.add(el);
        }

        int sz = set.size();

        // if all are equal we cannot create any problem set
        if (sz == 1) return 0;
        if (sz == 4) return 2;

        // now either if we have 3 equal elements
        if ((ar[0] == ar[1] && ar[1] == ar[2]) || (ar[1] == ar[2] && ar[2] == ar[3])) return 1;
        return 2;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();

        while (t-- > 0) {
            System.out.println(new PROBDIFF().solve(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                    scanner.nextInt()));
        }
    }
}
