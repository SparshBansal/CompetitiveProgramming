//import java.io.*;
//import java.util.List;
//import java.util.StringTokenizer;
//
//public class VANDH {
//    static class FastReader {
//        private static BufferedReader br;
//        private static StringTokenizer st;
//
//        public FastReader(String[] args) throws FileNotFoundException {
//            this(args.length > 0 ? new FileInputStream(new File(args[0])) : System.in);
//        }
//
//        public FastReader(InputStream inputStream) {
//            br = new BufferedReader(new
//                    InputStreamReader(inputStream));
//        }
//
//        public String next() {
//            while (st == null || !st.hasMoreElements()) {
//                try {
//                    st = new StringTokenizer(br.readLine());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            return st.nextToken();
//        }
//
//        public int nextInt() {
//            return Integer.parseInt(next());
//        }
//
//        public long nextLong() {
//            return Long.parseLong(next());
//        }
//
//        public double nextDouble() {
//            return Double.parseDouble(next());
//        }
//
//        public String nextLine() {
//            String str = "";
//            try {
//                str = br.readLine();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return str;
//        }
//    }
//
//    public void addValley(List<Character> buffer) {
//        if (buffer.isEmpty()) {
//            buffer.add('1');
//        }
//        Character lastCharacter = buffer.get(buffer.size() - 1);
//        if (lastCharacter == '0') {
//            buffer.add('1');
//        }
//        buffer.add('0');
//        buffer.add('1');
//    }
//
//    public void addHill(List<Character> buffer) {
//        if (buffer.isEmpty()) {
//            buffer.add('0');
//        }
//        Character lastCharacter = buffer.get(buffer.size() - 1);
//        if (lastCharacter == '1') {
//            buffer.add('0');
//        }
//        buffer.add('1');
//        buffer.add('0');
//    }
//
//
//    public String solve(int n_h, int n_v) {
//        // we need to find the number of hills and number of valleys
//        if (n_h < n_v) {
//            int hillsExtra = n_h - n_v;
//
//        }
//    }
//
//    public static void main(String[] args) throws FileNotFoundException {
//        FastReader scanner = new FastReader(args);
//
//    }
//}
