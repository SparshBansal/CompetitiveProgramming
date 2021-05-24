import javax.swing.text.Segment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Fancy {
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

    private static final int MAX_LEN = 100001;
    private static final int MOD = 1000000007;

    int n;
    private final int numSegments;

    private final int segmentSz;

    private final Long ar[];
    private final Segment segments[];

    private boolean initialized = false;

    public Fancy() {
        this.n = 0;
        this.numSegments = (int) (Math.sqrt(MAX_LEN) + 1);
        this.segmentSz = (int) Math.sqrt(MAX_LEN);

        ar = new Long[MAX_LEN];
        segments = new Segment[this.numSegments];
        initialized = true;
    }

    private int getSegmentIdx(int arIdx) {
        return arIdx / segmentSz;
    }

    public void append(int val) {
        if (!initialized) return;
        // if we are going to append the element , first find the idx
        final int segmentIdx = getSegmentIdx(this.n);
        // check if this is a new segment
        if (segments[segmentIdx] == null) {
            // initialize a new segment
            segments[segmentIdx] = new Segment(this.n, this.n + segmentSz - 1);
            // since this is a new segment we just need to append and return
            ar[n++] = (long) val;
            return;
        }

        // since this segment is already present, check if updates are needed
        Segment segment = segments[segmentIdx];
        if (upToDate(segment)) {
            // just append the element and return
            ar[n++] = (long) val;
            return;
        }

        // else we might need to apply updates to the segment
        applyUpdates(segment);
        // now append the element and return
        ar[n++] = (long) val;
    }

    private void applyUpdates(Segment segment) {

        if (upToDate(segment)) {
            return;
        }

        int li = segment.startIdx;
        int ui = Math.min(this.n - 1, segment.endIdx);

        for (int i = li; i <= ui; i++)
            ar[i] = (((ar[i] * segment.multiplier())%MOD) + segment.additiveFactor())%MOD;

        // reset the segment now
        segment.reset();
    }

    private boolean upToDate(Segment segment) {
        return segment.additiveFactor() == 0 && segment.multiplier() == 1;
    }

    public void addAll(int inc) {
        if (!initialized) return;
        // add all
        if (inc == 0) {
            return;
        }

        if (n == 0) {
            return;
        }
        // else non identity update, just update all segments
        // get segment idx
        int segmentIdx = getSegmentIdx(this.n - 1);
        for (int i = 0; i <= segmentIdx; i++) {
            segments[i].addToSegmentData(inc);
        }
    }

    public void multAll(int m) {
        if (!initialized) return;
        if (m == 1) {
            return;
        }

        if (n == 0) {
            return;
        }
        // else non identity update, just update all segments
        // get segment idx
        int segmentIdx = getSegmentIdx(this.n - 1);
        for (int i = 0; i <= segmentIdx; i++) {
            segments[i].multiplySegmentData(m);
        }
    }

    public int getIndex(int idx) {
        if (!initialized) return -1;
        if (idx >= this.n) {
            return -1;
        }

        // else apply updates to the segment
        Segment segment = segments[getSegmentIdx(idx)];
        applyUpdates(segment);
        return Math.toIntExact(ar[idx]);
    }


    private class Segment {

        int startIdx, endIdx, segmentSz;
        private SegmentData data;

        public Segment(int startIdx, int endIdx) {
            this.startIdx = startIdx;
            this.endIdx = endIdx;
            this.data = new SegmentData();
            this.segmentSz = this.endIdx - this.startIdx + 1;
        }

        public long multiplier() {
            return data.mulVal;
        }

        public long additiveFactor() {
            return data.addVal;
        }

        public void reset() {
            this.data.reset();
        }

        public void addToSegmentData(int val) {
            data.add(val);
        }

        public void multiplySegmentData(int mul) {
            data.multiply(mul);
        }

        private class SegmentData {
            long addVal, mulVal;

            public SegmentData() {
                this.addVal = 0;
                this.mulVal = 1;
            }

            public void add(long val) {
                this.addVal = (this.addVal + val)%MOD;
            }

            public void multiply(long val) {
                this.addVal = (this.addVal * val)%MOD;
                this.mulVal = (this.mulVal * val)%MOD;
            }

            public void reset() {
                this.addVal = 0;
                this.mulVal = 1;
            }
        }

    }

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int q = scanner.nextInt();

        Fancy fancy = null;

        while (q-- > 0) {
            String operation = scanner.nextLine();

            if ("Fancy".equals(operation)) {
                fancy = new Fancy();
            } else if ("append".equals(operation)) {
                if (fancy != null) {
                    fancy.append(scanner.nextInt());
                }
            } else if ("addAll".equals(operation)) {
                if (fancy != null) {
                    fancy.addAll(scanner.nextInt());
                }
            } else if ("multAll".equals(operation)) {
                if (fancy != null) {
                    fancy.multAll(scanner.nextInt());
                }
            } else if ("getIndex".equals(operation)) {
                if (fancy != null) {
                    System.out.println(fancy.getIndex(scanner.nextInt()));
                }
            }
        }
    }

}
