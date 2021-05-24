import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringDeletion {
    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
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


    public int solve(String s) {
        final char removed = '#';
        int operationPointer = 0;
        int duplicatePointer = 1;

        char[] ar = s.toCharArray();
        boolean exhaustedDuplicates = false;

        int ans = 0;
        while (operationPointer < ar.length) {
            ans++;
            // we have to do the first operation
            // find the first duplicate from the last
            if (duplicatePointer < operationPointer) {
                duplicatePointer = operationPointer + 1;
            }
            while (duplicatePointer < ar.length &&
                    ar[duplicatePointer] != ar[duplicatePointer - 1]) {
                duplicatePointer++;
            }
            // now check if we have exhausted all duplicates
            if (duplicatePointer == ar.length) {
                exhaustedDuplicates = true;
            }

            // if we have not exhausted duplicates we do not incur length cost for this operation
            if (!exhaustedDuplicates) {
                duplicatePointer++;
            } else {
                // operation pointer has to move to the next elements
                operationPointer = moveOperationPointer(operationPointer, ar);
            }
            operationPointer = moveOperationPointer(operationPointer, ar);
        }
        return ans;
    }


    private int moveOperationPointer(int operationPointer, char[] ar) {
        while (operationPointer < ar.length) {
            if (operationPointer == ar.length - 1) {
                // last element , just increment and return
                return operationPointer+1;
            }
            if (ar[operationPointer] == ar[operationPointer + 1]) {
                operationPointer++;
            } else {
                break;
            }
        }
        return operationPointer + 1;
    }


    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int t = scanner.nextInt();

        StringDeletion stringDeletion = new StringDeletion();
        while (t-- > 0) {
            int n = scanner.nextInt();
            System.out.println(stringDeletion.solve(scanner.nextLine()));
        }
    }

}
