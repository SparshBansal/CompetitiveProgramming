import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WaffleChoppers {
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

    private boolean solve(char[][] ar, int r, int c, int h, int v) {
        // need to check if possible
        int count = 0;
        int[] rowSum = new int[r];
        int[] colSum = new int[c];
        // compute cumulative sum
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (ar[i][j] == '@') {
                    rowSum[i]++;
                    colSum[j]++;
                    count++;
                }
            }
        }
        if ((count % (h + 1)) != 0 || (count % (v + 1) != 0)) {
            return false;
        }

        if (count == 0) {
            return true;
        }

        // now we just need to find the number of cookies in each part
        int perHorizontal = count / (h + 1);
        int perVertical = count / (v + 1);
        // now we try to split the rowSum and colSum arrays
        boolean possible = true;
        int currentSum = 0;
        List<Integer> hCuts = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            currentSum += rowSum[i];
            if (currentSum == perHorizontal) {
                currentSum = 0;
                hCuts.add(i);
            } else if (currentSum > perHorizontal) {
                // not possible
                return false;
            }
        }

        currentSum = 0;
        List<Integer> vCuts = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            currentSum += colSum[i];
            if (currentSum == perVertical) {
                currentSum = 0;
                vCuts.add(i);
            } else if (currentSum > perVertical) {
                // not possible
                return false;
            }
        }

        // number of slices
        int[] pieceCount = new int[(h+1) * (v+1)];

        int hCutTracker = 0;
        int vCutTracker = 0;
        for (int i=0; i<r; i++) {
            vCutTracker = 0;
            if (i > hCuts.get(hCutTracker)) {
                hCutTracker++;
            }
            for (int j=0; j<c; j++) {
                if (j > vCuts.get(vCutTracker)) {
                    vCutTracker++;
                }
                if (ar[i][j] == '@') {
                    // add 1 to the slice
                    pieceCount[(hCutTracker * (v + 1)) + vCutTracker]++;
                }
            }
        }
        // check the piece count, all piece counts should be equal
        int pieceVal = pieceCount[0];
        for (int i=1; i<pieceCount.length; i++) {
            if (pieceCount[i] != pieceVal) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(new FileInputStream(new File("input.txt")));
        int t = scanner.nextInt();
        int q = 0;
        while (t-- > 0) {
            q++;
            int r = scanner.nextInt();
            int c = scanner.nextInt();
            int h = scanner.nextInt();
            int v = scanner.nextInt();

            List<String> arr = new ArrayList<>();
            for (int i=0; i<r; i++) {
                arr.add(scanner.nextLine());
            }

            char[][] ar = new char[r][c];
            for (int i=0; i<r; i++) {
                for (int j=0; j<c; j++) {
                    ar[i][j] = arr.get(i).charAt(j);
                }
            }
            boolean ans = new WaffleChoppers().solve(ar, r, c, h, v);
            System.out.print("Case #" + q + ": ");
            if (ans) {
                System.out.println("POSSIBLE");
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }
    }
}
