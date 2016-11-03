package ru.ifmo.ctddev.segal.hw4.main_solvers;

/**
 * Created by Aleksei Latyshev on 03.11.2016.
 */

public class ExplicitDownstreamSolver extends UsualSolver {

    @Override
    double[] step(double[] cur, double dx, double dt, double u, double kappa) {
        double r = kappa * dt / (dx * dx);
        double s = u * dt / dx;
        double[] ans = new double[cur.length];
        for (int i = 0; i < ans.length; i++) {
            double prev = i > 0 ? cur[i - 1] : cur[i];
            double next = i < cur.length - 1 ? cur[i + 1] : cur[i];
            ans[i] = prev * r + cur[i] * (1 + s - 2 * r) + next * (r - s);
        }
        return ans;
    }
}