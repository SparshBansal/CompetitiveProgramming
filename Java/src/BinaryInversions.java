import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BinaryInversions {

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

    int[] solve(long n, long a, long x) {
        // find the max number of inversions
        long numZeros = a;
        long numOnes = n - a;

        // max inversions occur when all ones come before all 0s
        long maxInversions = (n - a) * a;
        long minInversions = 0;
        if (x < minInversions || x > maxInversions) {
            return null;
        }
        // for each 0 moved to the end we can have n-a inversions
        long numZerosInLast = x / numOnes;
        // now we also need to move one zero inbetween the ones
        long remainingInversions = x % numOnes;

        // now generate the array
        long numZerosInBeginning = numZeros - numZerosInLast - (remainingInversions > 0 ? 1 : 0);
        int[] ans = new int[(int)n];
        int idx = 0;
        for (; idx < numZerosInBeginning; idx++) {
            ans[idx] = 0;
        }
        // now for the partial one
        long numOnesRemaining = numOnes;
        if (remainingInversions > 0) {
            for (int i = 0; i < remainingInversions; i++)
                ans[idx++] = 1;
            ans[idx++] = 0;
            numOnesRemaining -= remainingInversions;
        }
        for (int i = 0; i < numOnesRemaining; i++)
            ans[idx++] = 1;

        for (int i = 0; i < numZerosInLast; i++)
            ans[idx++] = 0;
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int x = scanner.nextInt();
        int[] ans = new BinaryInversions().solve(n, a, x);
        if (ans == null) {
            System.out.println(-1);
        } else {
            System.out.println(Arrays.stream(ans).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
