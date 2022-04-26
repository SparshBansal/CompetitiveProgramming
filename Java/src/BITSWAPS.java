import java.io.*;
import java.util.*;

public class BITSWAPS {
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

    public String solve(int[] ar, int n) {

        // for each element we can create a disjoint set union of positions reachable
        int[] par = new int[n];
        for (int i=0; i<n; i++) {
            par[i] = i;
        }

        int[] bitCalcArray = Arrays.copyOf(ar, n);

        for (int i = 0; i < 31; i++) {
            int previous = -1;
            // find the last bits for each number and if the last bit is 1 these positions are reachable
            for (int j=0; j<n; j++) {
                // merge the previous bits
                if (bitCalcArray[j] % 2 == 1) {
                    if (previous == -1) {
                        previous = j;
                        bitCalcArray[j] /= 2;
                        continue;
                    }
                    // else we merge
                    merge(previous, j, par);
                }
                bitCalcArray[j] /= 2;
            }
        }
        // so now we will have the reachable nodes
        // now what we need to do is simple
        // sort the array and find the target position of each element

        int[] target = new int[n];

        // create a copy of the original array
        int[] copy = Arrays.copyOf(ar, n);
        Arrays.sort(copy);

        // now we find the target position for each element
        Map<Integer, Queue<Integer>> targets = new HashMap<>();
        for (int i = 0; i<n; i++) {
            targets.computeIfAbsent(copy[i], k -> new LinkedList<>()).add(i);
        }

        // now we find the targets
        for (int i = 0; i < n ; i++) {
            int targetPosition = targets.get(ar[i]).poll();
            target[i] = targetPosition;
        }

        // now we have the to and from position
        for (int i = 0; i<n; i++) {
            if (getRoot(par, i) != getRoot(par, target[i])) return "NO";
        }
        return "YES";
    }

    private void merge(int previous, int j, int[] par) {
        int rootP = getRoot(par, previous);
        int rootJ = getRoot(par, j);
        if (rootP == rootJ) {
            return;
        }
        // else
        par[rootJ] = rootP;
    }

    private int getRoot(int[] par, int val) {
        while(par[val] != val) {
            val = par[val];
        }
        return val;
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] ar = new int[n];
            for (int i=0; i<n; i++) {
                ar[i] = scanner.nextInt();
            }
            System.out.println(new BITSWAPS().solve(ar, n));
        }
    }
}
