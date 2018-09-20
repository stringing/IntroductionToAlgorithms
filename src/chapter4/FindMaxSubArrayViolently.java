package chapter4;

/**
 * @Description 暴力查找最大子数组
 * @Author Stringing
 * @Date 9/20/18 9:03 PM
 */
public class FindMaxSubArrayViolently {
    public static void main(String[] args) {
        int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        TribleInfo<Integer,Integer,Integer> maxSubarrayInfo = findMaximumSubarrayVilently(arr);
        System.out.println(maxSubarrayInfo);
    }

    private static TribleInfo<Integer, Integer, Integer> findMaximumSubarrayVilently(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int left = 0;
        int right = 0;
        for(int i = 0; i < arr.length; i++){
            int sum = arr[i];
            for(int j = i + 1; j < arr.length; j++){
                if(sum > maxSum){
                    maxSum = sum;
                    left = i;
                    right = j - 1;
                }
                sum += arr[j];
            }
        }
        return new TribleInfo<>(left, right, maxSum);
    }
}
