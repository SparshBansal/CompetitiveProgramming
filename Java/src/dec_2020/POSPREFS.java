package dec_2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class POSPREFS {
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

    static FastReader scanner = new FastReader();

    public static void solve(int n, int k) {
        // k is the number of positives
        int positives = k;
        int negatives = n - k;

        int multiplier = 1;
        int count = positives;
        if (positives > negatives) {
            // then check the start sign
            multiplier = -1;
            count = negatives;
        }

        // now generate the numbers for the lesser of the two
        List<Integer> ans = generate(n, count, multiplier);
        System.out.println(ans.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static List<Integer> generate(int n, int k, int multiplier) {
        int currentGenerated = 0;
        List<Integer> ans = new ArrayList<>();
        int val = 1;

        int currentMultiplier = multiplier;
        while (currentGenerated < k) {
            ans.add(currentMultiplier * (val++));
            if (currentMultiplier == multiplier) {
                currentGenerated++;
            }
            currentMultiplier *= -1;
        }

        currentMultiplier = -1 * multiplier;
        for (int i=ans.size()+1; i<=n; i++) {
            ans.add(currentMultiplier * i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();

            solve(n, k);
        }
    }
}
