package ru.ifmo.ctddev.segal.hw4.main_solvers;

/**
 * Created by Aleksei Latyshev on 03.11.2016.
 */

public class ExplicitUpstreamSolver extends UsualSolver {

    @Override
    double[] step(double[] cur, double dx, double dt, double u, double cappa) {
        int n = cur.length;
        double[] res = new double[cur.length];
        double r = cappa * dt / (dx * dx);
        double s = u * dt / dx;
        res[0] = (1 - r) * cur[0] + r * cur[1];
        for (int k = 1; k < n - 1; k++) {
            res[k] = (r + s) * cur[k - 1] + (1 - 2 * r - s) * cur[k] + r * cur[k + 1];
        }
        res[n - 1] = (r + s) * cur[n - 2] + (1 - r - s) * cur[n - 1];
        return res;
    }
}