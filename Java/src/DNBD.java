import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class DNBD {
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

    public boolean solve(String s) {
        Set<Character> found = new HashSet<>();
        char[] ar = s.toCharArray();
        found.add(ar[0]);
        char prev = ar[0];
        for (int i = 1; i < ar.length; i++) {
            char current = ar[i];
            if (current == prev) {
            } else {
                // different character than previous
                if (found.contains(current)) {
                    return false;
                }
                found.add(current);
                prev = current;
            }
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            String in = scanner.nextLine();
            if (new DNBD().solve(in)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
