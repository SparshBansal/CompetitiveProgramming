import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

class KPATHQUERY {
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

    private static class Query {
        int k;
        List<Integer> vs;

        public Query(int k, List<Integer> vs) {
            this.k = k;
            this.vs = vs;
        }
    }

    public void _lift(List<Integer>[] tree, int[][] lift, int d[], int log, int r, int p) {
        lift[r][0] = p;
        d[r] = d[p] + 1;
        for (int i = 1; i <= log; i++) {
            lift[r][i] = lift[lift[r][i - 1]][i - 1];
        }
        for (int child : tree[r]) {
            if (child == p) continue;
            _lift(tree, lift, d, log, child, r);
        }
        return;
    }

    // move distance from root
    public int move(int[][] lift, int r, int k, int log) {
        int power = 1 << log;
        int anc = r;
        for (int i = log; i >= 0; i--) {
            if (k >= power) {
                anc = lift[anc][i];
                k -= power;
            }
            power /= 2;
        }
        return anc;
    }


    // find lca of u and v
    public int lca(int[][] lift, int[] d, int u, int v, int log) {
        if (d[u] > d[v]) {
            u = u ^ v;
            v = u ^ v;
            u = u ^ v;
        }
        // move v by remaining distance
        v = move(lift, v, d[v] - d[u], log);

        if (u == v) {
            return u;
        }

        // now both are at the same distance now, we figure how to move the remaining distance
        for (int i = log; i >= 0; i--) {
            if (lift[u][i] != lift[v][i]) {
                // move
                u = lift[u][i];
                v = lift[v][i];
            }
        }
        // return lca
        return lift[u][0];
    }

    List<Boolean> solve(List<Integer>[] tree, final int n, List<Query> queries) {
        int log = (int) Math.ceil(Math.log(n));
        int[][] lift = new int[n + 1][log + 1];
        int[] d = new int[n + 1];
        d[0] = -1;
        // need to calculate lift
        _lift(tree, lift, d, log, 1, 1);

        // now we need to calculate the basic depth of each node and lca function
        // now we can iterate over each query and solve
        List<Boolean> solution = new ArrayList<>();

        for (Query query : queries) {
            List<Integer> vs = query.vs;
            if (vs.size() < 3) {
                solution.add(Boolean.TRUE);
                continue;
            }

            // sort the elements by depth
            vs.sort((left, right) -> d[right] - d[left]);

            // take l to be the node with max depth,
            // need to find endpoints of the two branches of simple path
            int l = vs.get(0);
            int r = -1;
            int lcaBranch = -1;

            boolean onSimplePath = Boolean.TRUE;
            for (int i = 1; i < vs.size(); i++) {
                int current = vs.get(i);
                // find lca
                int lcaL = lca(lift, d, l, current, log);
                if (lcaL == current) {
                    // lies on the path from l to lca
                    if (r == -1) {
                        // this can be replaced as new l
                        l = current;
                    } else {
                        int lcaR = lca(lift, d, current, r, log);
                        onSimplePath = onSimplePath && lcaR == lcaBranch;
                    }
                } else if (r == -1) {
                    r = current;
                    lcaBranch = lcaL;
                } else {
                    int lcaR = lca(lift, d, current, r, log);
                    onSimplePath = onSimplePath && lcaR == current && lcaL == lcaBranch;
                }
            }
            solution.add(onSimplePath);
        }
        return solution;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            List<Integer>[] tree = makeTree(n);

            for (int i = 0; i < n - 1; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                tree[u].add(v);
                tree[v].add(u);
            }

            List<Query> queries = new ArrayList<>();
            int q = scanner.nextInt();
            while (q-- > 0) {
                int k = scanner.nextInt();
                List<Integer> vs = new ArrayList<>();
                IntStream.range(0, k).forEach(val -> vs.add(scanner.nextInt()));
                queries.add(new Query(k, vs));
            }
            List<Boolean> solution = new KPATHQUERY().solve(tree, n, queries);
            for (Boolean ans : solution) {
                System.out.println(ans ? "YES" : "NO");
            }
        }
    }

    private static List<Integer>[] makeTree(int n) {
        List<Integer>[] tree = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        return tree;
    }

}
