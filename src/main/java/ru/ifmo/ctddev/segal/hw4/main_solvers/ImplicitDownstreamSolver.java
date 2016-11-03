package ru.ifmo.ctddev.segal.hw4.main_solvers;

import ru.ifmo.ctddev.segal.hw4.solvers.TridiagonalSolver;

import java.util.Arrays;

/**
 * Created by Aleksei Latyshev on 03.11.2016.
 */

public class ImplicitDownstreamSolver extends UsualSolver {

    @Override
    double[] step(double[] cur, double dx, double dt, double u, double cappa) {
        double[][] matrix = new double[cur.length][];
        double r = cappa * dt / (dx * dx);
        double s = u * dt / dx;
        Arrays.setAll(matrix, i -> new double[]{-r, 1 - s + 2 * r, s - r});
        matrix[0] = new double[] {1 - s + r, s - r};
        matrix[matrix.length - 1] = new double[] {-r, 1 + r};
        return TridiagonalSolver.solve(matrix, cur);
    }
}