import java.io.*;
import java.util.StringTokenizer;

class COLGLF4 {
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


   public long solve(long N, long E, long H, long A, long B, long C) {
       long a = 0, b = 0, c = 0;

       long ans = Long.MAX_VALUE;
       // fix c
       for (int i = 0; i<=N; i++) {
           c = i;
           // check if we have all ingredients to make current c
           if (E < c || H < c) {
               break;
           }

           // we have sufficient to make c, now remaining ingredient
           long e = E - c, h = H - c;

           long maxA = e/2;
           long maxB = h/3;

           if (A < B) {
               // required would be
               a = Math.min(N - c, maxA);
               b = Math.min(N - c - a, maxB);

           } else {
               // b is cheaper than A
               b = Math.min(N - c, maxB);
               a = Math.min(N - c - b, maxA);
           }
           // check if enough items can be produced
           if (a + b + c == N) {
               ans = Math.min(ans, (a*A + b*B + c*C));
           }
       }
       if (ans == Long.MAX_VALUE) {
           return -1;
       }
       return ans;
   }

   public static void main(String[] args) {
       FastReader scanner = new FastReader(System.in);

       int t = scanner.nextInt();

       while (t-- > 0) {
           int N = scanner.nextInt();
           int E = scanner.nextInt();
           int H = scanner.nextInt();
           int A = scanner.nextInt();
           int B = scanner.nextInt();
           int C = scanner.nextInt();

           System.out.println(new COLGLF4().solve(N, E, H, A, B, C));
       }
   }
}
