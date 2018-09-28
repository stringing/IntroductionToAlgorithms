package chapter4;

import java.util.Arrays;

/**
 * @Description Stranssen算法求解矩阵乘法
 * @Author Stringing
 * @Date 9/28/18 7:34 PM
 */
public class Strassen {
    private static SquareMatrixMultiplyRecursive tool = new SquareMatrixMultiplyRecursive();
    
    public static void main(String[] args) {
        int[][] A = {{1, 2, 3, 4}, {5, 6 ,7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] B = {{16, 15, 14, 13}, {12, 11, 10, 9}, {8, 7, 6, 5}, {4, 3, 2, 1}};
        int[][] C = new int[A.length][B.length];
        C = strassen(A, B, C);
        System.out.println(Arrays.deepToString(C));
    }

    private static int[][] strassen(int[][] a, int[][] b, int[][] c) {
        int n = a.length;
        if(n == 1){
            c[0][0] = a[0][0] * b[0][0];
        }else {
            //步骤1：将方阵A，B和C分解成n/2*n/2的矩阵

            int[][] a11 = tool.partitionMatrix(a, 0, n / 2, 0, n / 2);
            int[][] a12 = tool.partitionMatrix(a, 0, n / 2, n / 2, n);
            int[][] a21 = tool.partitionMatrix(a, n / 2, n, 0, n / 2);
            int[][] a22 = tool.partitionMatrix(a, n / 2, n, n / 2, n);

            int[][] b11 = tool.partitionMatrix(b, 0, n / 2, 0, n / 2);
            int[][] b12 = tool.partitionMatrix(b, 0, n / 2, n / 2, n);
            int[][] b21 = tool.partitionMatrix(b, n / 2, n, 0, n / 2);
            int[][] b22 = tool.partitionMatrix(b, n / 2, n, n / 2, n);

            int[][] c11 = tool.partitionMatrix(c, 0, n / 2, 0, n / 2);
            int[][] c12 = tool.partitionMatrix(c, 0, n / 2, n / 2, n);
            int[][] c21 = tool.partitionMatrix(c, n / 2, n, 0, n / 2);
            int[][] c22 = tool.partitionMatrix(c, n / 2, n, n / 2, n);

            //步骤2：创建10个n/2*n/2的矩阵S1...S10，每个矩阵保存步骤1中所的矩阵的和或差
            int[][] s1 = new int[n / 2][n / 2];
            int[][] s2 = new int[n / 2][n / 2];
            int[][] s3 = new int[n / 2][n / 2];
            int[][] s4 = new int[n / 2][n / 2];
            int[][] s5 = new int[n / 2][n / 2];
            int[][] s6 = new int[n / 2][n / 2];
            int[][] s7 = new int[n / 2][n / 2];
            int[][] s8 = new int[n / 2][n / 2];
            int[][] s9 = new int[n / 2][n / 2];
            int[][] s10 = new int[n / 2][n / 2];

            s1 = subtractMatrix(b12, b22);
            s2 = tool.addMatrix(a11, a12);
            s3 = tool.addMatrix(a21, a22);
            s4 = subtractMatrix(b21, b11);
            s5 = tool.addMatrix(a11, a22);
            s6 = tool.addMatrix(b11, b22);
            s7 = subtractMatrix(a12, a22);
            s8 = tool.addMatrix(b21, b22);
            s9 = subtractMatrix(a11, a21);
            s10 = tool.addMatrix(b11, b12);

            //步骤3：用步骤1和2中的矩阵，递归计算7个矩阵积P1...P7
            int[][] p1 = new int[n / 2][n / 2];
            int[][] p2 = new int[n / 2][n / 2];
            int[][] p3 = new int[n / 2][n / 2];
            int[][] p4 = new int[n / 2][n / 2];
            int[][] p5 = new int[n / 2][n / 2];
            int[][] p6 = new int[n / 2][n / 2];
            int[][] p7 = new int[n / 2][n / 2];

            p1 = strassen(a11, s1, p1);
            p2 = strassen(s2, b22, p2);
            p3 = strassen(s3, b11, p3);
            p4 = strassen(a22, s4, p4);
            p5 = strassen(s5, s6, p5);
            p6 = strassen(s7, s8, p6);
            p7 = strassen(s9, s10, p7);
            //步骤4：通过Pi矩阵的不同组合进行加减运算，计算出矩阵C的结果矩阵C11,C12,C21和C22
            c11 = tool.addMatrix(subtractMatrix(tool.addMatrix(p5, p4), p2), p6);
            c12 = tool.addMatrix(p1, p2);
            c21 = tool.addMatrix(p3, p4);
            c22 = subtractMatrix(subtractMatrix(tool.addMatrix(p5, p1), p3), p7);

            c = tool.mergeMatrix(c11, c12, c21, c22);
        }
        return c;
    }

    private static int[][] subtractMatrix(int[][] m1, int[][] m2){
        int n = m1.length;
        int[][] m3 = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                m3[i][j] = m1[i][j] - m2[i][j];
            }
        }
        return m3;
    }


}
