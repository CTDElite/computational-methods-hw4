package ru.ifmo.ctddev.segal.hw4.main_solvers;

/**
 * Created by Aleksei Latyshev on 03.11.2016.
 */
public interface Solver {
    double[][] solve(double[] start, double dx, double dt, double u, double kappa, int size);
}
