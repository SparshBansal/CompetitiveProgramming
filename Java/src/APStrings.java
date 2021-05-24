import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class APStrings {
    static class Scanner {
        BufferedReader br;
        StringTokenizer st;

        public Scanner() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
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
        Scanner scanner = new Scanner();
        int t = scanner.nextInt();

        while(t-- > 0) {
            String input = scanner.nextLine();
            // compute char values
            int[] charVals = new int[26];
            Arrays.fill(charVals, 0);
            int distinct = 0;

            for (int i=0; i<input.length(); i++) {
                if (charVals[input.charAt(i) - 'a'] == 0) {
                    distinct++;
                }
                charVals[input.charAt(i) - 'a']++;
            }

            if (distinct == 1) {
                System.out.println(-1);
                continue;
            }

            for (int i=0; i<charVals.length; i++) {
                if (charVals[i] > 0) {
                    for (int j=0; j<charVals[i]; j++) {
                        System.out.print((char) (i + 'a'));
                    }
                }
            }
            System.out.println();
        }
    }
}
