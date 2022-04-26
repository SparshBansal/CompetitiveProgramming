import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MAXBRIDGE {
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

    public static class Edge {
        private int from;
        private int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }
    }

    public List<Edge> solve(int n, int m) {
        // we need to add m edges to the graph of n nodes
        // it's guaranteed that m >= n-1
        // create a basic chain of nodes
        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i <= n - 1; i++) {
            edges.add(new Edge(i, i + 1));
        }

        // find the number where the edges will be satisfied
        int requiredNodes = -1;
        for (int i = 2; i <= n; i++) {
            int possibleEdges = (i * (i - 1)) / 2;
            if (possibleEdges + (n - i) >= m) {
                requiredNodes = i;
                break;
            }
        }

        // this will set the required number of nodes, fully connect the first requiredNodes nodes
        int remainingEdges = m - (n-1);
//        for (int i = requiredNodes; i<n; i++) edges.add(new Edge(i, i+1));
        // now we start fully connecting the edges
        if (remainingEdges == 0) return edges;

        for (int i=1; i<=requiredNodes-2; i++) {
            for (int j=i+2; j<=requiredNodes; j++) {
                edges.add(new Edge(i, j));
                remainingEdges--;
                if (remainingEdges == 0) return edges;
            }
        }
        return edges;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            List<Edge> solution = new MAXBRIDGE().solve(n, m);
            for (Edge ed : solution) {
                System.out.println(ed.from + " " + ed.to);
            }
        }
    }
}
