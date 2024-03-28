import java.util.TreeMap;
import java.util.TreeSet;

public class IsIdealPermutation {

    public class Fenwick {
        int[] data;
        public Fenwick(int length) {
            this.data = new int[length + 1];
        }

        public int query(int idx) {
            // add 1 to idx
            int qIdx = idx + 1;
 
            int sum = 0;
            while(qIdx > 0) {
                sum += this.data[qIdx];
                qIdx = qIdx - (qIdx & (-qIdx));
            }
            return sum;
        }

        public void add(int delta, int idx) {
            idx = idx + 1;
            while (idx < data.length) {
                this.data[idx] += delta;
                idx = idx + (idx & (-idx));
            }
        }
    }

    public boolean isIdealPermutation(int[] nums) {
        Fenwick fenwick = new Fenwick(nums.length);
        // now we just keep adding
        int global = 0;
        int local = 0;
        for (int i=0; i<nums.length; i++) {
            global += (i - fenwick.query(nums[i]));
            if (i > 0) {
                if (nums[i-1] > nums[i]) {
                    local++;
                }
            }
            fenwick.add(1, nums[i]);
        }
        return local == global;
    }

    public static void main(String[] args) {
        System.out.println(new IsIdealPermutation().isIdealPermutation(new int[]{1,2,0}));
    }
    
}
