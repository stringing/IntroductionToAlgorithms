package chapter4;

import java.util.Arrays;

/**
 * @Description 矩阵乘法
 * @Author Stringing
 * @Date 9/26/18 9:37 PM
 */
public class SquareMatrixMultiply {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4}, {5, 6 ,7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] b = {{16, 15, 14, 13}, {12, 11, 10, 9}, {8, 7, 6, 5}, {4, 3, 2, 1}};
        System.out.println(Arrays.deepToString(squareMatrixMultiply(a, b)));
    }

    private static int[][] squareMatrixMultiply(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b[0].length];
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < b[0].length; j++){
                int sum = 0;
                for(int k = 0; k < a[0].length; k++){
                    sum += a[i][k] * b[k][j];
                }
                c[i][j] = sum;
            }
        }
        return c;
    }
}
