public class WaysToMakeFairArray {

    public int makeFair(int[] nums) {
        // create odd even matrix from the end
        final int n = nums.length;
        int[] altSum = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (i + 2 > n - 1) {
                altSum[i] = nums[i];
            } else {
                altSum[i] = nums[i] + altSum[i + 2];
            }
        }
        int even = 0, odd = 0, cnt = 0;
        for (int i = 0; i <= n - 1; i++) {
            int evenSum = even;
            int oddSum = odd;
            // if we omit this one whats the total
            if (i + 1 < n) {
                if ((i + 1) % 2 == 0) {
                    oddSum += altSum[i + 1];
                } else {
                    evenSum += altSum[i + 1];
                }
            }
            if (i + 2 < n) {
                if ((i + 1) % 2 == 0) {
                    evenSum += altSum[i + 2];
                } else {
                    oddSum += altSum[i + 2];
                }
            }
            if (evenSum == oddSum) {
                cnt++;
            }
            // based on i add to the respective running sum
            if (i % 2 == 0) even += nums[i];
            else odd += nums[i];
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(new WaysToMakeFairArray().makeFair(new int[]{1,2,3}));
    }
}
