import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ROME {
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

    public int solve(int[] A, int[] B, int n) {
        // construct 2 graphs, normal and inverted
        List<Integer>[] graph = new List[n+1];
//        List<Integer>[] inv = new List[n];
//        int[] indegree = new int[n];
        int[] outdegree = new int[n+1];

        for (int i=0; i<=n; i++) {
            graph[i] = new ArrayList<>();
//            inv[i] = new ArrayList<>();
        }

        for (int i=0; i<n; i++) {
            graph[A[i]].add(B[i]);
//            indegree[B[i]]++;
            outdegree[A[i]]++;
        }
        // find the node with 0 outdegree and count
        int cnt = 0, node = -1;
        for (int i=0; i<=n; i++)
            if (outdegree[i] == 0) {
                cnt++;
                node = i;
            }
        if (cnt != 1) {
            return -1;
        }
        // else check if all nodes are reachable from this node
        return node;
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int n = scanner.nextInt();
        int[] A = new int[n];
        int[] B = new int[n];
        for (int i=0; i<n; i++)
            A[i] = scanner.nextInt();
        for (int i=0; i<n; i++)
            B[i] = scanner.nextInt();
        System.out.println(new ROME().solve(A, B, n));
    }
}
