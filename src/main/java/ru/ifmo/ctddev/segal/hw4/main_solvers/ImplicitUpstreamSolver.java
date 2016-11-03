package ru.ifmo.ctddev.segal.hw4.main_solvers;

import ru.ifmo.ctddev.segal.hw4.solvers.TridiagonalSolver;

/**
 * Created by Aleksei Latyshev on 03.11.2016.
 */

public class ImplicitUpstreamSolver extends UsualSolver {
    @Override
    double[] step(double[] cur, double dx, double dt, double u, double kappa) {
        int n = cur.length;
        double[][] matrix = new double[cur.length][];
        double r = kappa * dt / (dx * dx);
        double s = u * dt / dx;
        double prevT = -s - r;
        double curT = 1 + s + 2 * r;
        double nextT = -r;
        matrix[0] = new double[]{prevT + curT, nextT};
        for (int k = 1; k < n - 1; k++) {
            matrix[k] = new double[]{prevT, curT, nextT};
        }
        matrix[n - 1] = new double[]{prevT, curT + nextT};
        return TridiagonalSolver.solve(matrix, cur);
    }
}