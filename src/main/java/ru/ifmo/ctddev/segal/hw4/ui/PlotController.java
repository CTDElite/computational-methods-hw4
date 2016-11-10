package ru.ifmo.ctddev.segal.hw4.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class PlotController {

    private double dx;
    private double dt;
    private double kappa;
    private int size;
    private double u;

    @FXML
    private Slider tSlider;

    public void initializeData(double dx, double dt, double kappa, int size, double u) {
        this.dx = dx;
        this.dt = dt;
        this.kappa = kappa;
        this.size = size;
        this.u = u;
    }


}
