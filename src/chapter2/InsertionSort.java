package chapter2;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 6, 1, 3};
        System.out.println(Arrays.toString(arr));
        insertionSortDecrement(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void insertionSortIncrement(int[] arr) {
        for(int i = 1; i < arr.length; i++){
            int key = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] > key){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static void insertionSortDecrement(int[] arr) {
        for(int i = 1; i < arr.length; i++){
            int key = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] < key){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
