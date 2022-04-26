import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MEXIMUM {

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

    public List<Integer> solve(int n, int[] input) {
        Map<Integer, Stack<Integer>> nextIndex = new HashMap<>();
        for (int i=n-1; i>=0; i--) {
            nextIndex.computeIfAbsent(input[i], val -> new Stack<>()).push(i);
        }
        // now we create a tree set representing the missing elements
        TreeSet<Integer> missing = new TreeSet<>();
        for (int i=0; i<=n+1; i++) {
            missing.add(i);
        }

        List<Integer> solution = new ArrayList<>();

        int forward = 0;
        int back = 0;
        while (forward < n) {
            // we find the next missing element
            // remove the current element from the missing tree
            missing.remove(input[forward]);
            nextIndex.get(input[forward]).pop();
            // now we look for the next missing element
            int nextMissing = missing.first();
            // we check if it exists
            Stack<Integer> indices = nextIndex.get(nextMissing);
            while (indices != null && indices.size() > 0) {
                int nextMissingElementIndex = indices.peek();
                // we will move the forward pointer to the next missing element index
                while (++forward <= nextMissingElementIndex) {
                    missing.remove(input[forward]);
                    nextIndex.get(input[forward]).pop();
                }
                nextMissing = missing.first();
                indices = nextIndex.get(nextMissing);

                // forward needs to be decremented by 1
                forward--;
            }
            // at this point we have no next missing possible
            solution.add(nextMissing);
            // also we need to move the back pointer to match the front
            while (back != forward) {
                missing.add(input[back]);
                back++;
            }
            missing.add(input[back]);
            forward++;
        }
        return solution;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);

        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] ar = new int[n];
            for (int i = 0; i<n ; i++) ar[i] = scanner.nextInt();
            List<Integer> ans = new MEXIMUM().solve(n, ar);
            System.out.println(ans.size());
            System.out.println(ans.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        }
    }
}
