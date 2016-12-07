package ru.ifmo.ctddev.segal.hw4.main_solvers;

/**
 * Created by Aleksei Latyshev on 03.11.2016.
 */

public class LeapfrogSolver implements Solver {

    @Override
    public double[][] solve(double[] start, double dx, double dt, double u, double ϰ, int iterations) {
        final double[][] ans = new double[iterations][start.length];
        double[] prevIter = start;
        double[] preprevIter = start;

        for (int k = 0; k < iterations; k++) {
            for (int n = 0; n < start.length; n++) {
                final double prevLeft = prevIter[Math.max(0, n - 1)];
                final double prevRight = prevIter[Math.min(start.length - 1, n + 1)];
                ans[k][n] = dt * preprevIter[n] - (dx * dx * (prevRight - prevLeft) - 4 * dt * ϰ * prevIter[n])
                                                / (u * dx + 2 * ϰ);
            }
            preprevIter = prevIter;
            prevIter = ans[k];
        }
//        for (int n = 0; n < iterations; n++) {
//            for (int k = 0; k < start.length; k++) {
//                final double prevLeft = prevIter[Math.max(0, k - 1)];
//                final double prevRight = prevIter[Math.min(start.length - 1, k + 1)];
//                ans[n][k] = preprevIter[k] - dt * (u*dx*(prevRight - prevLeft) + 2*ϰ*(prevLeft + prevRight - 2*prevIter[k])) / (dx * dx);
//            }
//            preprevIter = prevIter;
//            prevIter = ans[n];
//        }

        return ans;
    }
}
