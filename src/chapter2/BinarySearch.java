package chapter2;

/**
 * @Description 二分查找
 * @Author Stringing
 * @Date 9/12/18 4:24 PM
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60, 70};
        int position = binarySearch(arr, 50);
        System.out.println(position);
    }

    private static int binarySearch(int[] arr, int num) {
        int mid = arr.length / 2;
        int left = 0;
        int right = arr.length;
        int target = arr[mid];
        while(target != num){
            if(target < num){
                left = mid;
                mid = (mid + right) / 2;
            }else{
                right = mid;
                mid = (left + mid) / 2;
            }
            target = arr[mid];
        }
        return mid;

    }
}
