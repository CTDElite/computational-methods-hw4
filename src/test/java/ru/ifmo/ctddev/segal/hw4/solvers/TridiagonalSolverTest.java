package ru.ifmo.ctddev.segal.hw4.solvers;

import org.junit.Test;

import static org.junit.Assert.*;

public class TridiagonalSolverTest {

    @Test
    public void testSimple() {
        assertArrayEquals(
                TridiagonalSolver.solve(new double[][]{{1, 2}, {1, 1, 1}, {3, 4}}, new double[]{8, 9, 25}),
                new double[]{2, 3, 4},
                0.001
        );
    }
}