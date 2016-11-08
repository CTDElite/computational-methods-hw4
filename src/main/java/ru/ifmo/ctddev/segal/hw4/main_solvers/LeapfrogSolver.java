package ru.ifmo.ctddev.segal.hw4.main_solvers;

/**
 * Created by Aleksei Latyshev on 03.11.2016.
 */

public class LeapfrogSolver implements Solver {

    @Override
    public double[][] solve(double[] start, double dx, double dt, double u, double ϰ, int size) {
        final double[][] ans = new double[size][start.length];
        double[] prevIter = start;
        double[] preprevIter = start;

        for (int k = 0; k < size; k++) {
            for (int n = 0; n < start.length; n++) {
                final double prevLeft = prevIter[Math.max(0, n - 1)];
                final double prevRight = prevIter[Math.min(start.length - 1, n + 1)];
                ans[k][n] = preprevIter[n] - (dx * (prevRight - prevLeft) / dt + 2 * prevIter[n] * (2 * ϰ / dx))
                                           / (u + 2 * ϰ / dx);
            }
            preprevIter = prevIter;
            prevIter = ans[k];
        }

        return ans;
    }
}
