public class SearchInsertPosition {

    public int findTarget(int[] nums, int target) {
        int l = 0, u = nums.length - 1;
        while (l <= u) {
            int m = l + (u - l) / 2;
            // if the middle is the element
            if (nums[m] == target) {
                return m;
            }
            if (nums[m] < target) {
                l = m + 1;
            } else {
                u = m - 1;
            }
        }
        // the moment the l and b cross , is where it should have been inserted
        return l;
    }

    public static void main(String[] args) {
        System.out.println(new SearchInsertPosition().findTarget(new int[] { 3, 4, 7, 8, 9, 10, 11}, 5));
    }

}
