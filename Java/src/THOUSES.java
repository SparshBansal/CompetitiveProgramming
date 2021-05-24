import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class THOUSES {

    private static final long MOD = 1000000007;

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

//
//    private class Multiplier {
//        long modCount = 0;
//        long value;
//
//        public Multiplier(long value) {
//            if (value > MOD) {
//                this.value = value % MOD;
//                this.modCount++;
//            } else {
//                this.value = value;
//            }
//        }
//
//        public void multiply(long multiplier) {
//            long totalValue = value *
//        }
//    }


    public long getMultiplier(List<Integer>[] tree, int root, int p) {
        List<Integer> children = tree[root];
        List<Long> multipliers = new ArrayList<>();

        for (int child : children) {
            if (child == p) {
                continue;
            }
            multipliers.add(getMultiplier(tree, child, root));
        }
        // now sort the multipliers
        multipliers.sort(Comparator.reverseOrder());

        // now we add the multipliers
        long multiplier = 1;
        long factor = 1;
        for (long m : multipliers) {
            multiplier = (multiplier) + (((m) * ((factor++))));
        }
        return multiplier;
    }

    public long solve(List<Integer>[] tree, int n, int x) {
        final int root = 1;
        // now get the multiplier for
        long multiplier = getMultiplier(tree, root, -1);
        return ((multiplier%MOD) * (x%MOD))%MOD;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int x = scanner.nextInt();

            List<Integer>[] tree = createTree(n);
            for (int i=0; i<n-1; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                tree[u].add(v);
                tree[v].add(u);
            }
            System.out.println(new THOUSES().solve(tree, n, x));
        }
    }


    private static List<Integer>[] createTree(int n) {
        List<Integer>[] tree = new List[n+1];
        for (int i=0; i<n+1; i++) {
            tree[i] = new ArrayList<Integer>();
        }
        return tree;
    }

}
