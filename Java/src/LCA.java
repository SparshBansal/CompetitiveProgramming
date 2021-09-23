import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class LCA {
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

    private int lca(int[][] lift, int log, int[] d, int u, int v) {
        if (d[u] < d[v]) {
            v = move(lift, log, v, d[v] - d[u]);
        } else if (d[v] < d[u]) {
            u = move(lift, log, u, d[u] - d[v]);
        }
        if (u == v) {
            return u;
        }
        // else
        for (int i = log; i >= 0; i--) {
            if (lift[u][i] != lift[v][i]) {
                // move
                u = lift[u][i];
                v = lift[v][i];
            }
        }
        return lift[u][0];
    }

    private void memo(List<Integer>[] tree, int[][] lift, int log, int r, int p) {
        // assuming lift array is set to -1
        lift[r][0] = p;
        for (int i = 1; i <= log; i++) {
            lift[r][i] = lift[lift[r][i - 1]][i - 1];
        }
        for (int c : tree[r]) {
            if (c == p) continue;
            memo(tree, lift, log, c, r);
        }
    }

    private int move(int[][] lift, int log, int r, int k) {
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

    public void depth(List<Integer>[] tree, int[] d, int p[], int r, int par, int cD) {
        p[r] = par;
        d[r] = cD;

        for (int c : tree[r]) {
            if (c == par) continue;
            depth(tree, d, p, c, r, cD + 1);
        }
    }

    public static class Pair<T> {
        private T _1, _2;

        public T _1() {
            return _1;
        }

        public T _2() {
            return _2;
        }

        public Pair(T _1, T _2) {
            this._1 = _1;
            this._2 = _2;
        }
    }


    public void solve(List<Integer>[] tree, int n, List<Pair<Integer>> queries) {
        int log = (int) Math.ceil(Math.log(n));
        int[][] lift = new int[n + 1][log + 1];
        int[] p = new int[n + 1];
        int[] d = new int[n + 1];
        Pair<Integer>[] subtree = new Pair[n + 1];
        Pair<Integer>[] except = new Pair[n + 1];
        Pair<Integer>[] all = new Pair[n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= log; j++) {
                lift[i][j] = -1;
            }
        }

        // couple of precomputations are needed for this
        depth(tree, d, p, 1, 1, 0);
        memo(tree, lift, log, 1, 1);
        subtree(tree, subtree, 1, 1);
        _except(tree, except, subtree, p, 1, 1);
        _all(tree, except, subtree, all, 1, 1);

        for (Pair<Integer> q : queries) {
            int a = q._1();
            int b = q._2();

            int _lca = lca(lift, log, d, a, b);
            // now find the mid point
            if (d[a] == d[b]) {
                // mid point = lca
                // we need some special handling here
                int distance = d[_lca] - d[a];
                int jA = move(lift, log, a, distance - 1);
                int jB = move(lift, log, b, distance - 1);

                int ans = all[a]._2() + all[b]._2() - ((subtree[jA]._2() + (subtree[jA]._1() * (distance + 1)))) -
                        ((subtree[jB]._2() + (subtree[jB]._1() * (distance + 1)))) - distance;
                System.out.println(ans);
            } else {
                // different depths, keep a at lower depth than b
                if (d[a] > d[b]) {
                    a = a ^ b;
                    b = a ^ b;
                    a = a ^ b;
                }

                // now mid point will lie in the side where b is present
                // find mid point and mid point + 1
                int pathLength = d[a] - d[_lca] + d[b] - d[_lca];
                int mid = move(lift, log, b, pathLength / 2);
                int pMid = lift[mid][0];

                // now we only need to calculate
                int ans = all[a]._2() + all[b]._2() - (subtree[mid]._2() + ((d[a] - d[_lca] + d[mid] - d[_lca]) * subtree[mid]._1())) - (except[mid]._2() + ((d[b] - d[mid] + 1) * except[mid]._1()));
                System.out.println(ans);
            }
        }
    }

    private void _all(List<Integer>[] tree, Pair<Integer>[] except, Pair<Integer>[] subtree, Pair<Integer>[] all, int r, int p) {
        int sum = 0;
        int count = 0;
        count = subtree[r]._1() + except[r]._1();
        sum = subtree[r]._2() + except[r]._2() + except[r]._1();
        all[r] = new Pair<>(count, sum);

        for (int c : tree[r]) {
            if (c == p) continue;
            _all(tree, except, subtree, all, c, r);
        }
    }

    private void _except(List<Integer>[] tree, Pair<Integer>[] except, Pair<Integer>[] subtree, int[] parent, int r, int p) {
        if (r == 1) {
            except[r] = new Pair<>(0, 0);
            // call on child right away
            for (int c : tree[r]) {
                if (c == p) continue;
                _except(tree, except, subtree, parent, c, r);
            }
            return;
        }

        int sum = 0;
        int count = 0;

        for (int b : tree[p]) {
            if (b == r || b == parent[p]) continue;
            sum += (subtree[b]._2() + subtree[b]._1());
            count += subtree[b]._1();
        }
        sum += (except[p]._1() + except[p]._2());
        count += except[p]._1() + 1;
        except[r] = new Pair<>(count, sum);

        for (int c : tree[r]) {
            if (c == p) continue;
            _except(tree, except, subtree, parent, c, r);
        }
    }

    private void subtree(List<Integer>[] tree, Pair<Integer>[] subtree, int r, int p) {
        int pathLenghtSum = 0;
        int pathCount = 1;
        for (int c : tree[r]) {
            if (c == p) continue;
            subtree(tree, subtree, c, r);
            pathCount += subtree[c]._1();
            pathLenghtSum += subtree[c]._2() + subtree[c]._1();
        }
        subtree[r] = new Pair<>(pathCount, pathLenghtSum);
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int q = scanner.nextInt();

            List<Integer>[] tree = makeTree(n);

            for (int i = 0; i < n - 1; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                tree[u].add(v);
                tree[v].add(u);
            }

            List<Pair<Integer>> queries = new ArrayList<>();
            for (int i = 0; i < q; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                queries.add(new Pair<>(a, b));
            }
            new LCA().solve(tree, n, queries);
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
