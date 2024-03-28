import java.util.Arrays;

public class MaxCandyDistributed {


    public boolean isPossible(int[] candies, int val, long k) {
        long piles = 0;
        for (int candyPile : candies) {
            piles += (candyPile / val);
        }
        return piles >= k;
    }

    public int maximumCandies(int[] candies, long k) {
        int ub = Arrays.stream(candies).max().getAsInt();   
        int lb = 1;

        int maxCandy = 0;
        // binary search
        while (lb <= ub) {
            int m = lb + (ub - lb)/2;
            if (this.isPossible(candies, m, k)) {
                maxCandy = Math.max(maxCandy, m);
                lb = m+1;
            } else {
                ub = m-1;
            }
        }
        return maxCandy;
    }

    public static void main(String[] args) {
        System.out.println(new MaxCandyDistributed().maximumCandies(new int[]{2,5}, 11));
    }

}
