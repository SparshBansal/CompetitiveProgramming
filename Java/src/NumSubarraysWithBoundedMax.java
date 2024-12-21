import java.util.*;
public class NumSubarraysWithBoundedMax {

    public class ValIndx {
        private int value, index;
        public ValIndx(int val, int index) {
            this.value = val;
            this.index = index;
        }
    }

    public int solve(int[] nums, int l, int r) {
        Stack<ValIndx> mStack = new Stack<>();
        final int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = 0;
        mStack.push(new ValIndx(nums[0], 0));

        for (int i=1; i<n; i++) {
            while(!mStack.empty() && mStack.peek().value < )
        }
    }
    public static void main(String[] args) {
        
    }
    
}
