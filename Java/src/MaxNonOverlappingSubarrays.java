import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxNonOverlappingSubarrays {

    private int getDp(int[] dp, int idx) {
        if (idx < 0) {
            return 0;
        }
        return dp[idx];
    }

    public int maxNonOverlapping(int[] nums, int target) {
        final int n = nums.length;
        int[] dp = new int[n];
        
        Map<Integer, Integer> last = new HashMap<>();
        int currSum = 0;
        last.put(0, -1);
        for (int i=0; i<n; i++) {
            currSum += nums[i];
            if (nums[i] == target) {
                dp[i] = (i > 0 ? dp[i-1] : 0) + 1;
            } else {
                if (last.containsKey(currSum - target)) {
                    dp[i] = getDp(dp, last.get(currSum - target)) + 1;
                }
                dp[i] = Math.max(i > 0 ? dp[i-1] : 0, dp[i]);
            }
            last.put(currSum, i);
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(new MaxNonOverlappingSubarrays().maxNonOverlapping(new int[]{-2,6,6,3,5,4,1,2,8}, 10));
    }
}
