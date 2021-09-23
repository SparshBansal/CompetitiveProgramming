import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class RemoveIslands {
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

    void dfs(int[][] visited, int[][] input, int x, int y) {
        int r = input.length;
        int c = input[0].length;

        if (x < 0 || x >= r) {
            return;
        }
        if (y < 0 || y >= c) {
            return;
        }
        // now check if white or already visited
        if (input[x][y] == 0 || visited[x][y] == 1) {
            return;
        }

        // else mark this node visited
        visited[x][y] = 1;
        dfs(visited, input, x+1, y);
        dfs(visited, input, x-1, y);
        dfs(visited, input, x, y-1);
        dfs(visited, input, x, y+1);
    }

    public int[][] removeIslands(int[][] input) {
        // so we need to remove black pixel islands that are not reachable from edges
        int r = input.length;
        int c = input[0].length;

        int[][] visited = new int[r][c];

        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                visited[i][j] = 0;
            }
        }

        // now we can start visting the black islands from all the vertices
        // move across the top row, we need around the borders
        for (int i=0; i<c; i++) {
            dfs(visited, input, 0, i);
            dfs(visited, input, r-1, i);
        }

        for (int i=0; i<r; i++) {
            dfs(visited, input, i, 0);
            dfs(visited, input, i, c-1);
        }
        // now the blacks that are not visited need to be removed
        for (int i=1; i<r-1; i++) {
            for (int j=1; j<c-1; j++) {
                if (input[i][j] == 1 && visited[i][j] != 1) {
                    // remove this pixel
                    input[i][j] = 0;
                }
            }
        }
        return input;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int r = scanner.nextInt();
        int c = scanner.nextInt();

        int[][] input = new int[r][c];
        for (int i=0; i<r; i++) {
            for (int j=0; j<c; j++) {
                input[i][j] = scanner.nextInt();
            }
        }
        int[][] solution = new RemoveIslands().removeIslands(input);
        for (int i=0; i<r; i++) {
            System.out.println(Arrays.stream(solution[i]).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
