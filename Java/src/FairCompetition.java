import java.io.*;
import java.util.*;

public class FairCompetition {
    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

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

    private long solve(int n, List<Pair<Integer>> relations) {
        // create arr
        int ar[] = new int[n+1];
        int sz[] = new int[n+1];

        for (int i=0; i<=n; i++) {
            ar[i] = i;
            sz[i] = 1;
        }

        // now for each relation merge
        for (Pair<Integer> relation : relations) {
            // merge
            merge(ar, sz, relation._1(), relation._2());
        }
        // find distinct friend circles
        Map<Integer, Integer> circles = new HashMap<>();

        for (int i=1; i<=n; i++) {
            int p = getParent(ar, i);
            circles.put(p, getSz(sz, p));
        }
        long ans = 0;
        for (Map.Entry<Integer, Integer> entry : circles.entrySet()) {
            ans += ((entry.getValue()) * (n - entry.getValue()));
        }
        return ans;
    }

    private int getParent(int[] ar, int el) {
        while (ar[el] != el) {
            el = ar[el];
        }
        return el;
    }

    private int getSz(int[] sz, int el) {
        return sz[el];
    }

    public void merge(int[] ar, int[] sz, int s1, int s2) {
        // merge sets s1, and s2
        int p1 = getParent(ar, s1);
        int p2 = getParent(ar, s2);
        if (p1 == p2) {
            return;
        }
        // else we need to merge
        if (getSz(sz, p1) < getSz(sz, p2)) {
            // merge p1 into p2
            ar[p2] = p1;
            sz[p1] += sz[p2];
        } else {
            ar[p1] = p2;
            sz[p2] += sz[p1];
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            List<Pair<Integer>> mList = new ArrayList<>();

            for (int i=0; i<m; i++) {
                mList.add(new Pair<>(scanner.nextInt(), scanner.nextInt()));
            }
            System.out.println(new FairCompetition().solve(n, mList));
        }
    }
}
