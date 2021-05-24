import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class VPATH {

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


    private Pair<Long> dfs(List<Integer>[] tree, int root, int p) {
        // now get the list from
        List<Integer> children = tree[root];
        List<Pair<Long>> answers = new ArrayList<>();
        for (int child : children) {
            if (child == p)
                continue;
            answers.add(dfs(tree, child, root));
        }
        if (answers.isEmpty()) {
            return new Pair<>(1L, 1L);
        }
        long totalPaths = 0;
        long pss = 0;

        for (Pair<Long> answer : answers) {
            totalPaths += (answer._2() % MOD);
            pss += (answer._1() % MOD);
        }
        // create ans with all pairs
        totalPaths = ((2 * totalPaths) + 1) % MOD;

        // solve for pss
        for (Pair<Long> childPair : answers) {
            pss += (childPair._2() % MOD);
        }

        long[] suffix = new long[answers.size()];
        suffix[answers.size() - 1] = (answers.get(answers.size() - 1)._2()) % MOD;
        for (int i = answers.size() - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + (answers.get(i)._2() % MOD);
        }

        for (int i = 0; i < answers.size() - 1; i++) {
            pss += (2 * ((answers.get(i)._2() % MOD) * (suffix[i+1] % MOD)))%MOD;
        }
        return new Pair<>(pss + 1, totalPaths);
    }

    public long solve(List<Integer>[] tree, int n) {
        final int root = 1;
        Pair<Long> answer = dfs(tree, 1, -1);
        return answer._1();
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            List<Integer>[] tree = createTree(n);
            for (int i = 0; i < n - 1; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                tree[u].add(v);
                tree[v].add(u);
            }
            System.out.println(new VPATH().solve(tree, n));
        }
    }

    private static List<Integer>[] createTree(int n) {
        List<Integer>[] tree = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            tree[i] = new ArrayList<>();
        }
        return tree;
    }
}
