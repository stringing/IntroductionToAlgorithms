package chapter4;

/**
 * @Description 动态规划线性时间内查找最大子数组
 * @Author Stringing
 * @Date 9/26/18 9:22 PM
 */
public class FindMaxSubArrayLinearly {
    public static void main(String[] args) {
        int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        System.out.println(findMaxSubArrayLinearly(arr));
    }

    private static TribleInfo<Integer,Integer,Integer> findMaxSubArrayLinearly(int[] arr) {
        int start = 0;
        int end = 0;
        int tmp = 0;
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int maxSum = dp[0];
        for(int i = 1; i < arr.length; i++){
            if(dp[i - 1] <= 0){
                dp[i] = arr[i];
                tmp = i;
            }else{
                dp[i] = dp[i - 1] + arr[i];
            }
            if(dp[i] > maxSum){
                maxSum = dp[i];
                start = tmp;
                end = i;
            }
        }
        return new TribleInfo<>(start, end, maxSum);
    }
}
