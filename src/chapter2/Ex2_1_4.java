package chapter2;

import java.util.Arrays;

/**
 * @Description 习题2.1-4
 * @Author Stringing
 * @Date 9/4/18 4:41 PM
 */
public class Ex2_1_4 {
    public static void main(String[] args) {
        int[] b1 = {1, 1, 1, 1, 1, 1};
        int[] b2 = {1, 1, 1, 1, 1, 1};
        int[] sum = new int[b1.length + 1];
        binaryAdd(b1, b2, sum);
        System.out.println(Arrays.toString(sum));
    }

    private static void binaryAdd(int[] b1, int[] b2, int[] sum) {
        int c = 0;
        for(int i = b1.length - 1; i >= 0; i--){
            int tmp = b1[i] + b2[i] + c;
            if(tmp == 2){
                sum[i + 1] = 0;
                c = 1;
            }else if(tmp == 3){
                sum[i + 1] = 1;
                c = 1;
            }else{
                sum[i + 1] = tmp;
                c = 0;
            }
        }
        sum[0] = c;
    }
}
