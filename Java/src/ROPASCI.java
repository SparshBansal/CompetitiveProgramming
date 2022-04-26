import java.io.*;
import java.util.StringTokenizer;

public class ROPASCI {
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

    public String solve(String moves, int n) {
        char[] solution = new char[n];
        int[] nextRock = new int[n];
        int[] nextPaper = new int[n];
        int[] nextScissor = new int[n];

        nextRock[n-1] = n;
        nextPaper[n-1] = n;
        nextScissor[n-1] = n;

        switch(moves.charAt(n-1)) {
            case 'P':
                nextPaper[n-1] = n-1;
                break;
            case 'S':
                nextScissor[n-1] = n-1;
                break;
            case 'R':
                nextRock[n-1] = n-1;
                break;
        }

        for (int i = n-2; i>=0; i--) {
            switch (moves.charAt(i)) {
                case 'P':
                    nextPaper[i] = i;
                    nextRock[i] = nextRock[i+1];
                    nextScissor[i] = nextScissor[i+1];
                    break;
                case 'S':
                    nextScissor[i] = i;
                    nextPaper[i] = nextPaper[i+1];
                    nextRock[i] = nextRock[i+1];
                    break;
                case 'R':
                    nextRock[i] = i;
                    nextPaper[i] = nextPaper[i+1];
                    nextScissor[i] = nextScissor[i+1];
                    break;
            }
        }

        // now we can compute the required the value
        solution[n-1] = moves.charAt(n-1);
        for (int i = n-2; i>=0; i--) {
            char move = moves.charAt(i);
            switch(move) {
                case 'R':
                    solution[i] = nextPaper[i] == n ? 'R' : solution[nextPaper[i]];
                    break;
                case 'P':
                    solution[i] = nextScissor[i] == n ? 'P' : solution[nextScissor[i]];
                    break;
                case 'S':
                    solution[i] = nextRock[i] == n ? 'S' : solution[nextRock[i]];
                    break;
            }
        }
        return new String(solution);
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            String s = scanner.nextLine();

            System.out.println(new ROPASCI().solve(s, n));
        }
    }
}
