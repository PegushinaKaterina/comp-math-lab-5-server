package com.example.compmathlab5server;

import com.example.compmathlab5server.dto.LagrangeResponse;
import com.example.compmathlab5server.dto.NewtonResponse;
import com.example.compmathlab5server.entity.Point;

public class Interpolator {
    public static LagrangeResponse getInterpolationByLagrange(Point[] points, double x) {
        double l = 0;
        double[] cArr =  new double[points.length];
        for (int i = 0; i < points.length; i++) {
            double cProduct = 1;
            double product = 1;
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    cProduct *= (points[i].getX() - points[j].getX());
                    product *= (x - points[j].getX()) / (points[i].getX() - points[j].getX());
                }
            }
            cArr[i] = points[i].getY() / cProduct;
            l += points[i].getY() * product;
        }

        StringBuilder function = new StringBuilder();
        for (int i = 0; i < points.length; i++) {
            if(i != 0) {
                if (cArr[i] >= 0){
                    function.append("+");
                }
            }
            function.append(String.valueOf(cArr[i]));
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    function.append("(x - ").append(points[j].getX()).append(")");
                }
            }
        }
        return new LagrangeResponse(function.toString(), l);
    }
    public static NewtonResponse getInterpolationByNewton(Point[] points, double x) {
        double[] splitDifferences = getSplitDifferences(points);
        double n = 0;
        StringBuilder function = new StringBuilder();
        for (int i = 0; i < points.length; i++) {
            if(i != 0) {
                if (splitDifferences[i] >= 0){
                    function.append("+");
                }
            }
            function.append(splitDifferences[i]);
            double product = 1;
            for (int j = 0; j < i; j++) {
                product *= (x - points[j].getX());
                function.append("(x - ").append(points[j].getX()).append(")");
            }
            n += splitDifferences[i] * product;
        }
        double[][] finiteDifferences = getFiniteDifferences(points);
        return new NewtonResponse(function.toString(), n, finiteDifferences);

    }

    private static double[] getSplitDifferences(Point[] points){
        int n = points.length;
        double[][] splitDifferences = new double[n][n];

        for (int i = 0; i < n; i++) {
            splitDifferences[i][0] = points[i].getY();
        }

        int k = 1;
        while (k <= n) {
            for (int i = 0; i < n - k; i++) {
                splitDifferences[i][k] = (splitDifferences[i + 1][k - 1] - splitDifferences[i][k - 1]) / (points[i + k].getX() - points[i].getX());
            }
            k++;
        }

        double[] necessarySplitDifferences = new double[n];
        for (int i = 0; i < n; i++) {
            necessarySplitDifferences[i] = splitDifferences[0][i];
        }
        return necessarySplitDifferences;
    }
    private static double[][] getFiniteDifferences(Point[] points){
        int n = points.length;
        double[][] finiteDifferences = new double[n + 1][n];

        for (int i = 0; i < n; i++) {
            finiteDifferences[0][i] = points[i].getX();
            finiteDifferences[1][i] = points[i].getY();
        }

        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < n + 1 - i; j++) {
                finiteDifferences[i][j] = finiteDifferences[i - 1][j + 1] - finiteDifferences[i - 1][j];
            }
        }

        return finiteDifferences;
    }
}
