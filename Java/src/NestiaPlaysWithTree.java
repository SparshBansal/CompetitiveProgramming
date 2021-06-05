import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class NestiaPlaysWithTree {
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

    public int dfs(List<Integer>[] tree, int root, int p) {
        int total = 0;
        for (int child : tree[root]) {
            if (child == p) continue;
            total += dfs(tree, child, root);
        }
        total += Math.max(0, tree[root].size() - 2);
        return total;
    }

    public int solve(List<Integer>[] tree, int n) {
        // assume root is 1
        int ans = 0;
        for (int child : tree[1]) {
            ans += dfs(tree, child, 1);
        }
        ans += Math.max(tree[1].size() - 2, 0);
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            List<Integer>[] tree = new List[n + 1];
            for (int i=0; i<=n; i++) tree[i] = new ArrayList<>();
            for (int i = 0; i < n - 1; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                tree[u].add(v);
                tree[v].add(u);
            }
            System.out.println(new NestiaPlaysWithTree().solve(tree, n));
        }
    }
}
