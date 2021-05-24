import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class QuestionPaper2 {

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

    private static boolean isPositivePossible(int marks, int a, int b, int n) {
        // use equation ax-by = c;
        for ( int i=0 ; i <=a; i++) {
            if ((marks + (b*i))%a == 0) {
                int y = i;
                int x = (marks + b*i)/a;

                if (x+y <= n) {
                    // System.out.println("Found solution for marks " + marks + " at " + x + " " + y);
                    return true;
                }
                else
                    return false;
            }
        }
        return false;
    }

    private static boolean isNegativePossible(int marks, int a, int b, int n) {
        // use equation ax-by = -marks
        // => ax + marks = by
        for (int i=0; i<=b; i++) {
            if (((a*i) - marks)%b == 0) {
                int x = i;
                int y = (a*x - marks)/b;

                if (x+y <= n) {
                    // System.out.println("Found solution for marks " + marks + " at " + x + " " + y);
                    return true;
                }
                else
                    return false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int t = reader.nextInt();
        while (t-- > 0) {
            int n = reader.nextInt(), a = reader.nextInt(), b = reader.nextInt();
            int maxMarks = n*a, minMarks = -n*b;
            int distinct = 1;
            for ( int i=1; i<=maxMarks; i++) {
                if (isPositivePossible(i, a, b, n))
                    distinct++;
            }

            for (int i=-1; i>=minMarks; i--) {
                if (isNegativePossible(i, a, b, n))
                    distinct++;
            }

            System.out.println(distinct);
        }
    }
}
