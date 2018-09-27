package chapter4;

import java.util.Arrays;

/**
 * @Description 分治思想矩阵乘法
 * @Author Stringing
 * @Date 9/27/18 6:37 PM
 */
public class SquareMatrixMultiplyRecursive {
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4}, {5, 6 ,7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] b = {{16, 15, 14, 13}, {12, 11, 10, 9}, {8, 7, 6, 5}, {4, 3, 2, 1}};
        int[][] c = squareMatrixMultiplyRecursive(a, b);
        System.out.println(Arrays.deepToString(c));
    }

    private static int[][] squareMatrixMultiplyRecursive(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n];
        if(n == 1){
            c[0][0] = a[0][0] * b[0][0];
        }else{
            //将矩阵A和B分割成8个均匀子矩阵
            int[][] a11 = partitionMatrix(a, 0, n / 2, 0, n / 2);
            int[][] a12 = partitionMatrix(a, 0, n / 2, n / 2, n);
            int[][] a21 = partitionMatrix(a, n / 2, n, 0, n / 2);
            int[][] a22 = partitionMatrix(a, n / 2, n, n / 2, n);

            int[][] b11 = partitionMatrix(b, 0, n / 2, 0, n / 2);
            int[][] b12 = partitionMatrix(b, 0, n / 2, n / 2, n);
            int[][] b21 = partitionMatrix(b, n / 2, n, 0, n / 2);
            int[][] b22 = partitionMatrix(b, n / 2, n, n / 2, n);

            int[][] c11 = partitionMatrix(c, 0, n / 2, 0, n / 2);
            int[][] c12 = partitionMatrix(c, 0, n / 2, n / 2, n);
            int[][] c21 = partitionMatrix(c, n / 2, n, 0, n / 2);
            int[][] c22 = partitionMatrix(c, n / 2, n, n / 2, n);

            //A和B的子矩阵相加凑成C
            addMatrix(squareMatrixMultiplyRecursive(a11, b11), squareMatrixMultiplyRecursive(a12, b21), c11);
            addMatrix(squareMatrixMultiplyRecursive(a11, b12), squareMatrixMultiplyRecursive(a12, b22), c12);
            addMatrix(squareMatrixMultiplyRecursive(a21, b11), squareMatrixMultiplyRecursive(a22, b21), c21);
            addMatrix(squareMatrixMultiplyRecursive(a21, b12), squareMatrixMultiplyRecursive(a22, b22), c22);

            c = mergeMatrix(c11, c12, c21, c22);
        }
        return c;
    }

    private static int[][] mergeMatrix(int[][] m11, int[][] m12, int[][] m21, int[][] m22){
        int n = m11.length;
        int l = 2 * n;
        int[][] m = new int[l][l];
        mergeMatrix(m11, m, 0, n, 0, n);
        mergeMatrix(m12, m, 0, n, n, l);
        mergeMatrix(m21, m, n, l, 0, n);
        mergeMatrix(m22, m, n, l, n, l);
        return m;
    }

    private static void mergeMatrix(int[][] sub, int[][] sup, int startRow, int endRow, int startColumn, int endColumn){
        int row = 0;
        for(int i = startRow; i < endRow; i++){
            int column = 0;
            for(int j = startColumn; j < endColumn; j++){
                sup[i][j] = sub[row][column];
                column++;
            }
            row++;
        }
    }

    private static void addMatrix(int[][] an, int[][] bn, int[][] c) {
        int n = an.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                c[i][j] = an[i][j] + bn[i][j];
            }
        }
    }

    private static int[][] partitionMatrix(int[][] matrix, int startRow, int endRow, int startColumn, int endColumn) {
        int[][] subMatrix = new int[matrix.length / 2][matrix.length / 2];
        int row = 0;
        for(int i = startRow; i < endRow; i++){
            int column = 0;
            for(int j = startColumn; j < endColumn; j++){
                subMatrix[row][column++] = matrix[i][j];
            }
            row++;
        }
        return subMatrix;
    }

}
