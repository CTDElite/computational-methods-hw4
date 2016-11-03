package ru.ifmo.ctddev.segal.hw4.main_solvers;

/**
 * Created by Aleksei Latyshev on 03.11.2016.
 */
public abstract class UsualSolver implements Solver {
    abstract double[] step(double[] cur, double dx, double dt, double u, double cappa);

    @Override
    public double[][] solve(double[] start, double dx, double dt, double u, double cappa, int size) {
        // todo: alex700
        return null;
    }
}
