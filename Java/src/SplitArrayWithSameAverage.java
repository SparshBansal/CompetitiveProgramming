import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SplitArrayWithSameAverage {
    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
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

    void subsetSums(Fraction[] A, int l, int r, int idx, Map<Fraction, List<Integer>> sums, Fraction currentSum, int currentCount) {
        if (l >= A.length || r < 0) {
            return;
        }
        // base case
        if (idx < l || idx > r) {
            return;
        }

        // now either we add this element or don't
        // if we add this element
        subsetSums(A, l, r, idx + 1, sums, currentSum, currentCount);
        Fraction nSum = addFractions(currentSum, A[idx]);
        sums.computeIfAbsent(nSum, k -> new ArrayList<>()).add(currentCount + 1);
        subsetSums(A, l, r, idx + 1, sums, nSum, currentCount + 1);
    }


    public boolean splitArraySameAverage(int[] A) {
        int sum = Arrays.stream(A).sum();
        int n = A.length;

        if (A.length == 1) {
            return false;
        }

        Fraction avg = new Fraction(sum, n);

        Fraction[] qArr = new Fraction[A.length];

        // subtract fractions
        for (int i = 0; i < n; i++) {
            qArr[i] = addFractions(new Fraction(A[i], 1), avg.additiveInverse());
        }

        // now we apply meet in the middle
        Map<Fraction, List<Integer>> left = new HashMap<>();
        Map<Fraction, List<Integer>> right = new HashMap<>();

        int mid = (A.length - 1) / 2;
        subsetSums(qArr, 0, mid, 0, left, new Fraction(0, 1), 0);
        subsetSums(qArr, mid + 1, A.length - 1, mid + 1, right, new Fraction(0, 1), 0);

        // now we find if there exists 0 in any subset
        if (containsZero(left.keySet()) || containsZero(right.keySet())) {
            return true;
        }

        // else iterate over any one keyset and look for keys in the other set
        for (Fraction key : left.keySet()) {
            if (right.containsKey(key.additiveInverse())) {
                // check the sum of num elements
                if (left.get(key).size() > 1 || right.get(key.additiveInverse()).size() > 1) {
                    return true;
                }
                if (left.get(key).get(0) + right.get(key.additiveInverse()).get(0) < A.length) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsZero(Collection<Fraction> collection) {
        return collection.stream().anyMatch(Fraction::isZero);
    }


    private static long computeGCD(long a, long b) {
        if (a % b == 0) {
            return b;
        }
        return computeGCD(b, a % b);
    }

    Fraction addFractions(Fraction a, Fraction b) {
        // check if any are undefined
        if (a.equals(Fraction.UNDEFINED) || b.equals(Fraction.UNDEFINED)) {
            return Fraction.UNDEFINED;
        }

        // else
        return new Fraction(a.num * b.den + b.num * a.den, a.den * b.den);
    }

    public static class Fraction {
        long num, den;

        public static Fraction UNDEFINED = new Fraction(0, 0);

        public Fraction(long num, long den) {
            if (den == 0) {
                // this fraction is undefined
                this.num = 0;
                this.den = 0;
                return;
            }

            boolean negative = false;
            if (num * den < 0) {
                negative = true;
            }

            long gcd = 1;
            if (Math.abs(num) > 0 && Math.abs(den) > 0) {
                // get the gcd of these elements
                gcd = computeGCD(Math.abs(num), Math.abs(den));
            } else {
                gcd = Math.max(Math.abs(num), Math.abs(den));
            }

            // reduce to base form
            this.num = (negative ? -1 : 1) * (Math.abs(num) / gcd);
            this.den = Math.abs(den) / gcd;
        }

        public Fraction additiveInverse() {
            if (this.equals(UNDEFINED)) {
                return UNDEFINED;
            }
            if (this.num == 0) {
                return this.copy();
            } else return new Fraction(-1 * this.num, this.den);
        }

        protected Fraction copy() {
            return new Fraction(this.num, this.den);
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Fraction fraction = (Fraction) object;
            // given they have same signs, check for equality
            return this.num == fraction.num && this.den == fraction.den;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num, den);
        }


        public boolean isZero() {
            if (num == 0 && den != 0) {
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int n = scanner.nextInt();

        int ar[] = new int[n];

        for (int i = 0; i < n; i++)
            ar[i] = scanner.nextInt();

        System.out.println(new SplitArrayWithSameAverage().splitArraySameAverage(ar));
    }
}
