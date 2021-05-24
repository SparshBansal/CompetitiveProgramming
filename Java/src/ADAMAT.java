import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ADAMAT {

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
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[][] mat = new int[n][n];
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    mat[i][j] = scanner.nextInt();
                }
            }

            int ans = 0;
            // start from l = n-1 and go uptil 0
            for (int l=n-1; l >=0; l--) {
                // check if already row major
                if (!isRowMajor(mat, l, n)) {
                    // transpose
                    transpose(mat, l);
                    ans++;
                }
            }
            System.out.println(ans);
        }
    }

    private static void transpose(int[][] mat, int submatIdx) {
        int maxCol = 0;
        for (int row = 0; row <= submatIdx; row++) {
            for (int col = 0; col <= maxCol; col++) {
                int temp = mat[row][col];
                mat[row][col] = mat[col][row];
                mat[col][row] = temp;
            }
            maxCol++;
        }
    }


    private static boolean isRowMajor(int[][] mat, int submatIdx, int n) {
        // check row and column
        for(int i=submatIdx; i>=0; i--) {
            if (mat[submatIdx][i] != ((submatIdx * n) + (i + 1)))  {
                return false;
            }
        }
        return true;
    }
}
