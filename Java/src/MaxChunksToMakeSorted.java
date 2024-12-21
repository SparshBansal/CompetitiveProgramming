public class MaxChunksToMakeSorted {

    public int solve(int[] nums) {
        int n = nums.length;
        int[] minSuffix = new int[n];

        minSuffix[n - 1] = nums[n-1];
        for (int i=n-2 ; i>=0; i--) {
            minSuffix[i] = Math.min(nums[i], minSuffix[i+1]);
        }

        // now we can just start
        int totalSegments = 1;
        int currentSegmentMax = nums[0];

        for (int i=1; i<n; i++) {
            if (minSuffix[i] >= currentSegmentMax) {
                // we can end this segment
                totalSegments += 1;
                currentSegmentMax = nums[i];
            } else {
                currentSegmentMax = Math.max(currentSegmentMax, nums[i]);
            }
        }
        return totalSegments;
    }

    public static void main(String[] args) {
        System.out.println(new MaxChunksToMakeSorted().solve(new int[]{2,1,3,4,4}));
    }
}
