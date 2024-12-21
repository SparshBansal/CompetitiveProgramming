import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PlaceWordInCrossword {
    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
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

    public class Result {
        private boolean canFit;
        private int idx;

        public Result(boolean canFit, int idx) {
            this.canFit = canFit;
            this.idx = idx;
        }

        public boolean canFit() {
            return this.canFit;
        }

        public int indx() {
            return this.idx;
        }
    }
    
    public boolean isProperFit(char[][] board, int startRow, int startCol, String val) {
        int numCols = board[0].length;

        if (startCol + val.length() > numCols) {
            return false;
        }
        if (startCol + val.length() == numCols) {
            return true;
        }
        if (board[startRow][startCol+ val.length()] != '#') {
            return false;
        }
        return true;
    }

    public Result canFitOnRow(char[][] board, int row, int col, String val) {
        // check if it can fit on the row starting with col indx 
        int numCols = board[0].length;
        if (board[row][col] != ' ') {
            return new Result(false, col);
        }
        // now starting from row we need to find whether it can fit entirely or not, if not return the value of the first defaulter position
        int filled = 0;
        for (int i=col; i<numCols && filled < val.length(); i++) {
            if (board[row][i] == ' ' || board[row][i] == val.charAt(filled)) {
                filled++;
            } else {
                // this is the first defaulter position
                return new Result(false, i);
            }
        }
        // if we have filled everythign
        if (filled == val.length()) {
            // if no other trailing ' ' is left, return true
            if (!isProperFit(board, row, col, val)) {
                // cannot fit, return the value of the first non ' ' char
                for (int i=col + filled; i<numCols; i++) {
                    if (board[row][i] != '#') {
                        continue;
                    }
                    return new Result(false, i);
                }
                return new Result(false, numCols);
            }
            return new Result(true, -1);
        }
        return new Result(false, col + filled);
    }

    public boolean canPlace(char[][] board, String val) {
        // do a row by row scan
        int numRows = board.length;
        int numCols = board[0].length;

        for (int row=0; row<numRows; row++) {
            // check if the current character is empty
            for (int i=0;i < numCols;) {
                if (board[row][i] == ' ' || board[row][i] == val.charAt(0)) {
                    Result result = canFitOnRow(board, row, i, val);
                    if (result.canFit()) {
                        return true;
                    }
                    i = result.indx();
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    public char[][] transpose(char[][] board) {
        int numRows = board.length;
        int numCols = board[0].length;
        char[][] transpose = new char[numCols][numRows];
        for (int i=0; i<numRows; i++) {
            for (int j=0; j<numCols; j++) {
                transpose[j][i] = board[i][j];
            }
        }
        return transpose;
    }
    
    public char[][] reverse(char[][] board) {
        int numRows = board.length;
        int numCols = board[0].length; 
        char[][] reverse = new char[numRows][numCols];
        for (int i=0; i<numRows; i++) {
            for (int j=0; j<numCols; j++) {
                reverse[i][numCols - 1 - j] = board[i][j];
            }
        }
        return reverse;
    }

    public boolean canPlaceAllDirs(char[][] board, String val) {
        return canPlace(board, val) || canPlace(reverse(board), val) || canPlace(transpose(board), val) || canPlace(reverse(transpose(board)), val);
    }

    public static void main(String[] args) {
        System.out.println(new PlaceWordInCrossword().canPlaceAllDirs(new char[][]{
            {'#', ' ', '#'},
            {' ', ' ', '#'},
            {'#', ' ', 'c'}
        }, "ca"));
    }
}