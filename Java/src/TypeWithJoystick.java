import java.io.*;
import java.util.*;

public class TypeWithJoystick {
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

    public static class Pair<T> {
        private T _1, _2;

        public T _1() {
            return _1;
        }

        public T _2() {
            return _2;
        }

        public Pair(T _1, T _2) {
            this._1 = _1;
            this._2 = _2;
        }

        public static <T> Pair<T> make(T _1, T _2) {
            return new Pair<>(_1, _2);
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Pair<?> pair = (Pair<?>) object;
            return Objects.equals(_1, pair._1) &&
                    Objects.equals(_2, pair._2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_1, _2);
        }
    }


    public Pair<Integer>[] solve(String s) {
        int[][] mappings = new int[26][26];
        for (int i = 1; i < s.length(); i++) {
            int prev = s.charAt(i - 1) - 'a';
            int curr = s.charAt(i) - 'a';

            mappings[prev][curr]++;
            mappings[curr][prev]++;
        }

        Set<Pair<Integer>> candidates = new HashSet<>();
        Set<Pair<Integer>> allotted = new HashSet<>();

        HashMap<Integer, Pair<Integer>> positions = new HashMap<>();

        // allocate first pair and then loop
//        Pair<Character> first = null;
//        int maxCount = -1;
//        for (int i=0; i<25; i++) {
//            for (int j=i+1; j<26; j++) {
//                if (mappings[i][j] > maxCount) {
//                    maxCount = mappings[i][j];
//                    first = new Pair<>((char) ('a' + i), (char) ('a' + j));
//                }
//            }
//        }

        // now allocate the pairs, and add next candidates to candidate set
//        positions.put(first._1())
        candidates.add(new Pair<>(0, 0));
        while (positions.size() < 26) {
            // find candidate to allot
            int maxPairs = -1;
            int nextCandidate = 0;
            for (int i = 0; i < 26; i++) {
                if (positions.containsKey(i)) {
                    continue;
                }
                // possible candidate, find pairs with all allocated
                int pairs = 0;
                for (Integer other : positions.keySet()) {
                    pairs += mappings[i][other];
                }
                if (pairs > maxPairs) {
                    maxPairs = pairs;
                    nextCandidate = i;
                }
            }

            int minCost = Integer.MAX_VALUE;
            Pair<Integer> bestPosition = null;
            // find the best position for this candidate
            for (Pair<Integer> x : candidates) {
                // find the best candidate position for this character
                int totalCost = 0;
                for (Integer other : positions.keySet()) {
                    Pair<Integer> y = positions.get(other);
                    totalCost += ((manhattan(x, y)) * mappings[nextCandidate][other]);
                }
                if (totalCost < minCost) {
                    minCost = totalCost;
                    bestPosition = x;
                }
            }
            // assign next Candidate to best position
            positions.put(nextCandidate, bestPosition);
            allotted.add(bestPosition);

            candidates.remove(bestPosition);
            // add new positions to candidate set
            addPositions(candidates, bestPosition, allotted);
        }
        // now we will have all the mappings
        Pair<Integer>[] ans = new Pair[26];
        for (int i = 0; i < 26; i++) {
            ans[i] = positions.get(i);
        }
        return ans;
    }


    private void addPositions(Set<Pair<Integer>> candidates, Pair<Integer> center, Set<Pair<Integer>> allotted) {
        Pair<Integer> up = Pair.make(center._1() + 1, center._2());
        Pair<Integer> down = Pair.make(center._1() - 1, center._2());
        Pair<Integer> left = Pair.make(center._1(), center._2() - 1);
        Pair<Integer> right = Pair.make(center._1(), center._2() + 1);

        if (!allotted.contains(up)) {
            candidates.add(up);
        }
        if (!allotted.contains(down)) {
            candidates.add(down);
        }
        if (!allotted.contains(left)) {
            candidates.add(left);
        }
        if (!allotted.contains(right)) {
            candidates.add(right);
        }
    }


    private int manhattan(Pair<Integer> x, Pair<Integer> y) {
        return Math.abs(x._1 - y._1) + Math.abs(x._2() - y._2());
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        String input = scanner.nextLine();
        Pair<Integer>[] ans = new TypeWithJoystick().solve(input);

        for (Pair<Integer> coord : ans) {
            System.out.println(coord._1() + " " + coord._2());
        }
    }
}
