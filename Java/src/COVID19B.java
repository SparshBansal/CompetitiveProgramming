import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class COVID19B {

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

    private static class IntHolder {
        int val;

        public IntHolder(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner();
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int vel[] = new int[n];

            IntStream.range(0, n).forEach(idx -> {
                vel[idx] = scanner.nextInt();
            });

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int infected = getInfected(vel, i);
                min = Math.min(min, infected);
                max = Math.max(max, infected);
            }
            System.out.println(min + " " + max);
        }
    }

    private static void dfs(int[] vel,
                            int idx,
                            IntHolder infectedVal,
                            double t,
                            Set<Integer> visited,
                            Map<Integer, Double> infectTime) {
       if (idx == -1) {
           return;
       }

        visited.add(idx);
        infectTime.remove(idx);

        if (vel[idx] == 0) {
            return;
        }

        // figure out the times when this person will infect others
        for (int i = 0; i < vel.length; i++) {
            if (i == idx) {
                continue;
            }
            double time = -1;
            double posI = (i + 1) + (t * vel[i]);
            double posIdx = (idx + 1) + (t * vel[idx]);

            if (vel[idx] > vel[i]) {
                time = (posI - posIdx) / (vel[idx] - vel[i]);
            } else {
                time = (posIdx - posI) / (vel[i] - vel[idx]);
            }
            if (time > 0) {
                // this person will be infected by idx
                if (visited.contains(i)) {
                } else if (infectTime.containsKey(i) && infectTime.get(i) < t + time) {
                } else {
                    if (!infectTime.containsKey(i)) {
                        infectedVal.val++;
                    }
                    infectTime.put(i, t + time);
                }
            }
        }

        // now identify the lowest time infection and pass that
        double lowestTime = Double.MAX_VALUE;
        int child = -1;

        for (int i : infectTime.keySet()) {
            if (infectTime.get(i) < lowestTime) {
                lowestTime = infectTime.get(i);
                child = i;
            }
        }
        dfs(vel, child, infectedVal, lowestTime, visited, infectTime);
    }

    private static int getInfected(int[] vel, int idx) {
        Map<Integer, Double> infectTime = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        infectTime.put(idx, 0.0);
        IntHolder infectCount = new IntHolder(1);
        dfs(vel, idx, infectCount, 0.0, visited, infectTime);
        return infectCount.val;
    }
}
