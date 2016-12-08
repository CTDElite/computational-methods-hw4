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

        final double s = u * dt / dx;
        final double r = ϰ * dt / (dx * dx);
        for (int n = 0; n < iterations; n++) {
            for (int k = 0; k < start.length; k++) {
                final double prevLeft = prevIter[Math.max(0, k - 1)];
                final double prevRight = prevIter[Math.min(start.length - 1, k + 1)];
                ans[n][k] = preprevIter[k] - (s + 2 * r) * prevRight + (s - 2 * r) * prevLeft + 4 * r * prevIter[k];
            }
            preprevIter = prevIter;
            prevIter = ans[n];
        }

        return ans;
    }
}
