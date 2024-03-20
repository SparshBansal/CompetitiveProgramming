import java.util.Scanner;

public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int endPosn = nums.length - 1;
        for (int i = 0; i < endPosn;) {
            if (nums[i] == val) {
                // swap nums[i] with end position
                int t = nums[i];
                nums[i] = nums[endPosn];
                nums[endPosn] = t;

                endPosn--;
            } else {
                i++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                return i;
            }
        }
        return nums.length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int val = scanner.nextInt();
        int nums[] = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        int rem = new RemoveElement().removeElement(nums, val);
        for (int i = 0; i < rem; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

}
