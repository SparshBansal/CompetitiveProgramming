import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class TCTCTOE {

    static Map<String, Integer> states = new HashMap<>();

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

    public static void recurse(char[][] board, boolean xTurn) {
        // check if terminal state has reached
        String state = getState(board);
        if (isTerminal(board)) {
            states.put(state, 1);
            return;
        }

        // if not terminal this state is reachable
        states.put(state, 2);

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (board[i][j] == '_') {
                    board[i][j] = xTurn ? 'X' : 'O';
                    recurse(board, !xTurn);
                    board[i][j] = '_';
                }
            }
        }
    }

    private static String getState(char[][] board) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++){
                stringBuilder.append(board[i][j]);
            }
        }
        return stringBuilder.toString();
    }

    private static boolean isTerminal(char[][] board) {
        // check if a winner is present or not
        return isWinner(board) || isDraw(board);
    }

    private static boolean isDraw(char[][] board) {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (board[i][j] == '_')
                    return false;
            }
        }
        return true;
    }

    private static boolean isWinner(char[][] board) {
        // horizontal
        for (int i=0; i<3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '_')
                return true;
        }

        // vertical
        for (int i=0; i<3; i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != '_')
                return true;
        }

        // diagonal
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '_')
            return true;
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != '_')
            return true;

        return false;
    }


    public static void precompute() {
        // initialize a 3 X 3 board with underscores
        char[][] board = new char[3][3];
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                board[i][j] = '_';
            }
        }
        recurse(board, true);
    }


    private int solve(char[][] board) {
        String state = getState(board);
        if (states.containsKey(state)) {
            return states.get(state);
        }
        return 3;
    }

    public static void main(String[] args) throws FileNotFoundException {
        precompute();

        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            char[][] board = new char[3][3];
            for (int i = 0; i < 3; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < 3; j++) {
                    board[i][j] = line.charAt(j);
                }
            }
            System.out.println(new TCTCTOE().solve(board));
        }
    }
}
