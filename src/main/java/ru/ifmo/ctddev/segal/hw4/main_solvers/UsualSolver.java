package ru.ifmo.ctddev.segal.hw4.main_solvers;

/**
 * Created by Aleksei Latyshev on 03.11.2016.
 */
public abstract class UsualSolver implements Solver {
    abstract double[] step(double[] cur, double dx, double dt, double u, double cappa);

    @Override
    public double[][] solve(double[] start, double dx, double dt, double u, double cappa, int size) {
        double[][] ans = new double[size][];
        ans[0] = new double[start.length];
        System.arraycopy(start, 0, ans[0], 0, ans[0].length);
        for (int i = 1; i < ans.length; i++) {
            ans[i] = step(ans[i - 1], dx, dt, u, cappa);
        }
        return ans;
    }
}
