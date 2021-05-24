import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class FEMA2 {
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

    int maxMagnetsSolve(String q, int[] sheetsPrefix, int l, int u, int k) {
        // solve for lower to upper range
        // base case
        if (l > u) {
            return 0;
        }

        // search for X
        int qx = u + 1;
        for (int i = l; i <= u; i++) {
            if (q.charAt(i) == 'X') {
                qx = i;
                break;
            }
        }
        // found the first qx solve for the later half
        int ans = solveNonBlocking(q, sheetsPrefix, l, qx - 1, k);
        ans += maxMagnetsSolve(q, sheetsPrefix, qx + 1, u, k);
        return ans;
    }


    int solveNonBlocking(String q, int[] sheetsPrefix, int l, int u, int k) {
        // for this we can apply two pointer method
        // create a list of indexes with irons and magnets
        List<Integer> irons = new ArrayList<>();
        List<Integer> magnets = new ArrayList<>();

        for (int i = l; i <= u; i++) {
            if (q.charAt(i) == 'I') {
                irons.add(i);
            } else if (q.charAt(i) == 'M') {
                magnets.add(i);
            }
        }

        // now we just need to iterate over both, if any list size is 0, return 0
        if (irons.size() == 0 || magnets.size() == 0) {
            return 0;
        }

        int ptrA = 0, ptrB = 0;
        int pairs = 0;
        while (ptrA < irons.size() && ptrB < magnets.size()) {
            // check if the current ptrs make a match
            final int ironIdx = irons.get(ptrA);
            final int magnetIdx = magnets.get(ptrB);

            if (canPair(q, sheetsPrefix, ironIdx, magnetIdx, k)) {
                // increment pointers
                ptrA++;
                ptrB++;
                pairs++;
            } else {
                // they cannot match, increment the lower idx
                if (ironIdx < magnetIdx) ptrA++;
                else ptrB++;
            }
        }
        return pairs;
    }

    private boolean canPair(String q, int[] sheetsPrefix, int i, int j, int k) {
        return (k + 1 - Math.abs(j - i) - getSheetsBetween(sheetsPrefix, Math.min(i, j), Math.max(i, j))) > 0;
    }

    int maximumMagnets(String q, int k) {
        int n = q.length();
        int[] sheetsPrefix = getSheetsPrefix(q);
        return maxMagnetsSolve(q, sheetsPrefix, 0, n-1, k);
    }

    private int[] getSheetsPrefix(String q) {
        int[] sheets = new int[q.length()];
        sheets[0] = q.charAt(0) == ':' ? 1 : 0;

        for (int i = 1; i < q.length(); i++) {
            sheets[i] = sheets[i - 1];
            if (q.charAt(i) == ':')
                sheets[i]++;
        }
        return sheets;
    }

    private int getSheetsBetween(int[] sheetsPrefix, int i, int j) {
        if (i > j) {
            return 0;
        }
        return sheetsPrefix[j] - (i > 0 ? sheetsPrefix[i - 1] : 0);
    }

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            String q = scanner.nextLine();
            System.out.println(new FEMA2().maximumMagnets(q, k));
        }
    }
}
