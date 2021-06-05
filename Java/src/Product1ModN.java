import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Product1ModN {
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

    void dfs(int current, Set<Integer> mSet, int prod, int n) {
        if (current == n) {
            if (prod % n == 1) {
                if (mSet.isEmpty()) {
                    return;
                }
                System.out.println(mSet.toString());
            }
            return;
        }

        // two possibilities include, current or not
        int newProd = (prod * current) % n;
        mSet.add(current);
        dfs(current + 1, mSet, newProd, n);
        mSet.remove(current);
        dfs(current + 1, mSet, prod, n);
    }

    public int bruteForce(int n) {
        // need to check
        dfs(1, new HashSet<>(), 1, n);
        return -1;
    }

    public int _gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return _gcd(b, a % b);
    }

    public void solve(int n) {
        int numFactors = 0;
        List<Integer> relativePrimes = new ArrayList<>();


        if (n == 2) {
            System.out.println(1);
            System.out.println(1);
            return;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                numFactors++;
            }
            if (_gcd(n, i) == 1) {
                relativePrimes.add(i);
            }
        }
        if (numFactors == 0) {
            System.out.println((n - 2));
            System.out.println(IntStream.range(1, n - 1).mapToObj(String::valueOf)
                    .collect(Collectors.joining(" ")));
        } else {
            System.out.println(relativePrimes.size());
            System.out.println(relativePrimes.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int n = scanner.nextInt();
        new Product1ModN().solve(n);
    }
}
