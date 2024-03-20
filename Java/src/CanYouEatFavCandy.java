import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;


public class CanYouEatFavCandy {

    public Boolean[] canEat(int[] candies, int[][] queries) {
        // calculate prefix and suffix sums
        final int n = candies.length;
        long[] preWithFirst = new long[n];

        long total = 0;
        for (int i = 0; i < n; i++) {
            preWithFirst[i] = total;
            total += candies[i];
        }
        // 48942,869704869,212630006
        // 2444553454, 
        int q = queries.length;
        Boolean[] result = new Boolean[q];

        for (int i = 0; i < q; i++) {
            long favCandy = queries[i][0];
            long favDay = queries[i][1];
            long cap = queries[i][2];
            // find the number of candies we have to eat to have atleast one candy on fav
            // day
            long toBeEaten = preWithFirst[(int) favCandy] + 1;
            long daysToEat = Math.max(favDay + 1, 0);

            BigInteger dayToEatBigInt = BigInteger.valueOf(daysToEat);
            BigInteger capBigInteger = BigInteger.valueOf(cap);
            BigInteger toBeEatenBigInteger = BigInteger.valueOf(toBeEaten);
            // now if we cannot eat them we return false, this is the upper bound check
            if (dayToEatBigInt.multiply(capBigInteger).compareTo(toBeEatenBigInteger) < 0) {
                result[i] = false;
                continue;
            }
            // lower bound check also if we have more days than candies
            if (daysToEat > preWithFirst[(int) favCandy] + candies[(int) favCandy]) {
                result[i] = false;
                continue;
            }

            result[i] = true;
        }

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // System.out.println(Arrays.stream(new CanYouEatFavCandy().canEat(new int[] { 5,2,6,4,1 }, new int[][] {
        //         {3,1,2},
        //         {4,10,3},
        //         {3,10,100},
        //         {4,100,30},
        //         {1,3,1}
        // })).map(bool -> String.valueOf(bool)).collect(Collectors.joining(" ")));
        
        FileInputStream fis = new FileInputStream("input.txt");
        Scanner scanner = new Scanner(fis);

        int len = scanner.nextInt();
        int[] candies = new int[len];
        for (int i=0; i<len; i++) {
            candies[i] = scanner.nextInt();
        }
        System.out.println(Arrays.stream(new CanYouEatFavCandy().canEat(candies, new int[][]{
            {48942,869704869,212630006}
        })).map(val -> String.valueOf(val)).collect(Collectors.joining(" ")));
    }

}
