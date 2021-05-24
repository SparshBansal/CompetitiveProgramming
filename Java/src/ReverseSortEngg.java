import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ReverseSortEngg {
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

    public int[] solve(int n, int c) {
        int MIN_ANS = n-1;
        int MAX_ANS = ((n * (n+1))/2) - 1;

        if (c > MAX_ANS || c < MIN_ANS) {
            return null;
        }
        // now create a sorted array
        int ar[] = new int[n];
        for (int i=1; i <=n; i++) {
            ar[i-1] = i;
        }

        // now check what is the cost
        boolean flipFromStart = true;
        int s = 0;
        int e = n - 1;

        int currentCost = n - 1;
        while (currentCost < c) {
            // check if full reversal is required
            if (currentCost + (e - s) <= c) {
                // full reversal is required
                // update the current cost
                currentCost += (e - s);
                // reverse inclusive
                reverse(ar, s, e);
                // now update the start and end-endpoints based on flip direction
                if (flipFromStart) {
                    e--;
                } else {
                    s++;
                }
                flipFromStart = !flipFromStart;

            }
            else {
                // full reversal not required
                // get the difference
                int diff = c - currentCost;
                // get the length of reversal
                int lenOfReversal = diff + 1;
                // reverse only as much as the length requires.
                if (flipFromStart) {
                    // length of flipping
                    int endPoint = s + lenOfReversal - 1;
                    reverse(ar, s, endPoint);
                } else {
                    int startPoint = e - lenOfReversal + 1;
                    reverse(ar, startPoint, e);
                }
                break;
            }
        }
        return ar;
    }

    private void reverse(int[] ar, int i, int j) {
        // inplace reverse
        for (int s = i, e = j; s < e; s++, e--) {
            int tmp = ar[s];
            ar[s] = ar[e];
            ar[e] = tmp;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
//        FastReader scanner = new FastReader(new FileInputStream(new File("input.txt")));
        FastReader scanner = new FastReader(System.in);
        int t = scanner.nextInt();
        int q = 0;
        while (t-- > 0) {
            q++;
            int n = scanner.nextInt();
            int c = scanner.nextInt();
            int ar[] = new ReverseSortEngg().solve(n, c);
            String ans;
            if (ar == null) {
                ans = "IMPOSSIBLE";
            } else {
                ans = Arrays.stream(ar).boxed().map(String::valueOf).collect(Collectors.joining(" "));
            }

            System.out.println("Case #" + q + ": " + ans);
        }
    }
}
