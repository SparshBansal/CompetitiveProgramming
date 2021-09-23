import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class PermutationSequence {
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

    public String solve(int n, int k) {
        // how do we find the kth sequence
        // num factorials we need to solve
        // calculate factorials are number of digits;
        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        // lets start with the outer digits and move to the inner digits
        int currentPointer = 0;
        List<Integer> currentNumber = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            int digitsRemaining = n - currentNumber.size();
            for (int j = 1; j <= n; j++) {
                if (getUsedDigits(currentNumber).contains(j)) continue;
                if (currentPointer + factorial[digitsRemaining - 1] < k)
                    currentPointer += factorial[digitsRemaining - 1];
                else {
                    currentNumber.add(j);
                    break;
                }
            }
        }
        // this should be the number
        long number = 0;
        for (int digit : currentNumber) {
            number = number * 10 + digit;
        }
        return String.valueOf(number);
    }


    public Set<Integer> getUsedDigits(List<Integer> usedDigits) {
        return new HashSet<>(usedDigits);
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while(t-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            System.out.println(new PermutationSequence().solve(n, k));
        }
    }
}
