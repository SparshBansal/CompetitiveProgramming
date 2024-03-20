import java.util.Arrays;
import java.util.stream.Collectors;

public class PlusOne {
    public int[] plusOne(int[] val) {
        int carry = 0;
        for (int i = val.length - 1; i>=0; i--) {
            int sum = val[i] + carry;
            if (i == val.length - 1) {
                sum += 1;
            }
            val[i] = sum % 10;
            carry = sum / 10;
        }
        // now if the carry is not zero, allocate a new array 
        if (carry > 0) {
            int[] ret = new int[val.length + 1];
            ret[0] = carry;
            for (int i=0, j=1; i < val.length; i++) {
                ret[j] = val[i];
            }
            return ret;
        }
        return val;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.stream(new PlusOne().plusOne(new int[]{9})).mapToObj(val -> String.valueOf(val)).collect(Collectors.joining(" ")));
    }
    
}
