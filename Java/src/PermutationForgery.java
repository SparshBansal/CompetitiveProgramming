import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class PermutationForgery {
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

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            List<Integer> mList = new ArrayList<>();
            for (int i=0; i<n; i++) {
                mList.add(scanner.nextInt());
            }

            Collections.reverse(mList);
            String ans = mList.stream().map(Object::toString).collect(Collectors.joining(" "));
            System.out.println(ans);
        }
    }
}
