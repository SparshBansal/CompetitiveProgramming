import java.util.*;

public class Barrels {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            Long[] arr = new Long[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextLong();
            }
            long sum = 0;
            Arrays.sort(arr);
            for (int i = n - k - 1; i <= n - 2; i++) {
                sum += arr[i];
            }
            System.out.println(sum + arr[n - 1]);
        }
    }
}