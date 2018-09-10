package chapter2;

import java.util.Arrays;

/**
 * @Description 归并排序
 * @Author Stringing
 * @Date 9/9/18 4:51 PM
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 7, 1, 2, 3, 6};
        System.out.println(Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    private static void mergeSort(int[] arr, int start, int end) {
        if(start < end){
            int mid = (int)Math.floor((start + end) >> 1);
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            //merge(arr, start, mid, end);
            mergeWithoutSentry(arr, start, mid, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int[] left = new int[mid - start + 2];
        int[] right = new int[end - mid + 1];
        for(int x = start; x <= mid; x++){
            left[x - start] = arr[x];
        }
        for(int y = mid + 1 ; y <= end; y++){
            right[y - mid - 1] = arr[y];
        }
        //两个哨兵
        left[left.length - 1] = Integer.MAX_VALUE;
        right[right.length - 1] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for(int k = start; k <= end; k++){
            //当左数组右数组其中一个游标到了哨兵，由于任何数都会比max小，所以就会是没到哨兵的那个数组开始一直放数到arr中了
            if(left[i] <= right[j]){
                arr[k] = left[i];
                i++;
            }else{
                arr[k] = right[j];
                j++;
            }
        }
    }

    //无哨兵的实现
    //ex2.3-2
    private static void mergeWithoutSentry(int arr[], int start, int mid, int end){
        int[] left = new int[mid - start + 1];
        int[] right = new int[end - mid];
        for(int x = start; x <= mid; x++){
            left[x - start] = arr[x];
        }
        for(int y = mid + 1 ; y <= end; y++){
            right[y - mid - 1] = arr[y];
        }
        int i = 0;
        int j = 0;
        for(int k = start; k <= end; k++){
            if(i >= left.length){
                for(int w = j; w < right.length; w++){
                    arr[k++] = right[w];
                }
                break;
            }
            if(j >= right.length){
                for(int w = i; w < left.length; w++){
                    arr[k++] = left[w];
                }
                break;
            }
            if(left[i] <= right[j]){
                arr[k] = left[i];
                i++;
            }else{
                arr[k] = right[j];
                j++;
            }
        }
    }
}
