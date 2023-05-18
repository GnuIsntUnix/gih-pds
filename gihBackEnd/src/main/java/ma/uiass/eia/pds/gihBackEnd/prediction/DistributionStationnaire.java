package ma.uiass.eia.pds.gihBackEnd.prediction;

import java.util.Arrays;

public class DistributionStationnaire {

    public static double[] getMat(double[] pi, double[][] P) {
        double[][] mat = {pi};

//        for (int i = 1; i < 1000; i += 10) {
//            double[][] power = matrixPower(P, i);
//            double[] dotProduct = dotProduct(pi, power);
//            mat = Arrays.copyOf(mat, mat.length + 1);
//            mat[mat.length - 1] = dotProduct;
//        }

        for (int i = 1; i < 10000; i += 10) {
            double[][] power = matrixPower(P, i);
            double[][] result = matrixMultiplication(mat, power);
            mat = Arrays.copyOf(mat, mat.length + 1);
            mat[mat.length - 1] = result[0];
        }

        return mat[mat.length - 1];
    }

    public static double[][] matrixMultiplication(double[][] A, double[][] B) {
        int m = A.length;
        int n = B[0].length;
        int o = B.length;
        double[][] C = new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < o; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    public static double[][] matrixPower(double[][] matrix, int power) {
        double[][] result = matrix;
        for (int i = 1; i < power; i++) {
            result = matrixMultiply(result, matrix);
        }
        return result;
    }



    public static double[][] matrixMultiply(double[][] a, double[][] b) {
        int n = a.length;
        int m = a[0].length;
        int p = b[0].length;
        double[][] result = new double[n][p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                double sum = 0;
                for (int k = 0; k < m; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    public static double[] dotProduct(double[] a, double[][] b) {
        int n = a.length;
        int m = b[0].length;
        double[] result = new double[m];
        for (int j = 0; j < m; j++) {
            double sum = 0;
            for (int i = 0; i < n; i++) {
                sum += a[i] * b[i][j];
            }
            result[j] = sum;
        }
        return result;
    }


}