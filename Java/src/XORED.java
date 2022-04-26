import sun.jvm.hotspot.memory.Universe;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class XORED {
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


    public int[] solve(int n, int xor) {
        if (n == 1) {
            return new int[]{xor};
        }

        int[] array = new int[n];
        int currentXor = 0;
        int currentNumber = 1;
        Set<Integer> mSet = new HashSet<>();
        for (int i = 0; i < n-2; i++) {
            if ((currentXor ^ currentNumber) == xor) {
                currentNumber++;
            }
            array[i] = currentNumber;
            currentXor = currentXor ^ currentNumber;
            mSet.add(currentNumber);
            currentNumber++;
        }

        for (int i = currentNumber; i<500000; i++) {
            int partial = currentXor ^ i;
            if (!mSet.contains(xor ^ partial) && (partial ^ xor) < 500000)  {
                array[n-2] = i;
                array[n-1] = (xor ^ partial);
                break;
            }
        }
        return array;
        // inorder to maintain uniqueness, we can now double the last set number
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int n = scanner.nextInt();
        Random random = new Random();
        while (n-- > 0) {
            int[] ans = new XORED().solve(scanner.nextInt(), scanner.nextInt());
            System.out.println(Arrays.stream(ans).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
//            int elements = random.nextInt(100000);
//            int xor = random.nextInt(500000);
//            int[] ans = new XORED().solve(elements, xor);
//            Validator.Result result = new Validator().validate(ans, xor);
//            if (!result.status) {
//                System.out.println("Failed for " + elements + " " + xor + "; " + result.reason);
//                System.out.println(Arrays.stream(ans).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
//            }
        }
    }

    public static class Validator {

        public static class Result {
            boolean status;
            String reason;

            public Result(boolean status, String reason) {
                this.status = status;
                this.reason = reason;
            }
        }

        public Result validate(int[] array, int val) {
            Set<Integer> mSet = new HashSet<>();
            int xor = 0;
            String reason = "";
            for (int i : array) {
                if (i > 500000) {
                    reason = "Limit Breached";
                    return new Result(false, reason);
                }
                if (mSet.contains(i)) {
                    reason = "Duplicate element : " + i;
                    return new Result(false, reason);
                }
                mSet.add(i);
                xor = xor ^ i;
            }
            if (xor != val) {
                reason = "Xor mismatch";
                return new Result(false, reason);
            }
            return new Result(true, null);
        }
    }
}
