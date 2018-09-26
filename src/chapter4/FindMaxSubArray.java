package chapter4;

/**
 * @Description 分治寻找最大子数组
 * @Author Stringing
 * @Date 9/20/18 5:01 PM
 */
public class FindMaxSubArray {
    public static void main(String[] args) {
        int[] arr = {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        TribleInfo<Integer,Integer,Integer> maxSubarrayInfo = findMaximumSubarray(arr, 0, arr.length - 1);
        System.out.println(maxSubarrayInfo);
    }

    private static TribleInfo<Integer, Integer, Integer> findMaximumSubarray(int[] arr, int low, int high) {
        if(low == high)
            return new TribleInfo<>(low, high, arr[low]);
        int mid = (low + high) / 2;
        TribleInfo<Integer,Integer,Integer> leftInfo = findMaximumSubarray(arr, low, mid);
        TribleInfo<Integer,Integer,Integer> rightInfo = findMaximumSubarray(arr, mid + 1, high);
        TribleInfo<Integer,Integer,Integer> crossInfo = findMaximumCrossingSubarray(arr, low, mid, high);
        if(leftInfo.sum > rightInfo.sum && leftInfo.sum > crossInfo.sum)
            return leftInfo;
        if(rightInfo.sum > leftInfo.sum && rightInfo.sum > crossInfo.sum)
            return rightInfo;
        return crossInfo;
    }

    //查找过中点的情况
    private static TribleInfo<Integer, Integer, Integer> findMaximumCrossingSubarray(int[] arr, int low, int mid, int high){
        //找左边最大的
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = -1;
        for(int i = mid; i >= low; i--){
            sum += arr[i];
            if(sum > leftSum){
                leftSum = sum;
                maxLeft = i;
            }
        }
        //找右边最大的
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = -1;
        //mid + 1 就体现出它不能适用在只有一个元素的情况
        for(int i = mid + 1; i <= high; i++){
            sum += arr[i];
            if(sum > rightSum){
                rightSum = sum;
                maxRight = i;
            }
        }
        return new TribleInfo<>(maxLeft, maxRight, leftSum + rightSum);
    }
}

//三元信息对象，表示最大连续子数组的左界右界和总和
class TribleInfo<L,R,S>{
    L left;
    R right;
    S sum;

    public TribleInfo(L left, R right, S sum) {
        this.left = left;
        this.right = right;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "TribleInfo{" +
                "left=" + left +
                ", right=" + right +
                ", sum=" + sum +
                '}';
    }
}