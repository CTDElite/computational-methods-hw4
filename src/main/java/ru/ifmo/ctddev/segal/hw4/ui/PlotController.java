package ru.ifmo.ctddev.segal.hw4.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import ru.ifmo.ctddev.segal.hw4.main_solvers.Solver;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlotController implements Initializable {

    private double dx;
    private double dt;
    private double kappa;
    private int size;
    private double u;

    private List<Solver> solvers;

    @FXML
    private Slider tSlider;

    public void initializeData(double dx, double dt, double kappa, int size, double u, List<Solver> solvers) {
        this.dx = dx;
        this.dt = dt;
        this.kappa = kappa;
        this.size = size;
        this.u = u;

        tSlider.setMax(size - 1);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // TODO: implement
        });
    }
}
