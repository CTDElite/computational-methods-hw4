package ru.ifmo.ctddev.segal.hw4.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.ifmo.ctddev.segal.hw4.main_solvers.Solver;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PlotController implements Initializable {

    private double dx;
    private double dt;
    private double kappa;
    private int size;
    private double u;
    private double[][] ans;

    private List<Solver> solvers;

    @FXML
    private Slider tSlider;
    @FXML
    public GridPane tPane;

    public void initializeData(double dx, double dt, double kappa, int size, double u, List<Solver> solvers) {

        this.solvers = solvers;
        this.dx = dx;
        this.dt = dt;
        this.kappa = kappa;
        this.size = size;
        this.u = u;

        tSlider.setMax(size - 1);

        Solver solver = solvers.get(0);
        ans = solver.solve(new double[] {279, 300, 330, 340, 350, 300, 273}, dx, dt, u, kappa, size);
    }

    public int[] getColors(double[] t) {
        double min = Arrays.stream(t).min().getAsDouble();
        double max = Arrays.stream(t).max().getAsDouble();
        int[] ans = new int[t.length];
        Arrays.setAll(ans, i -> (int) (255 * (t[i] - min) / (max - min)));
        return ans;
    }

    public void draw(int[] colors) {
        tPane.getChildren().clear();
        double squareSide = tPane.getWidth() / colors.length;
        for (int i = 0; i < colors.length; i++) {
            Rectangle rectangle = new Rectangle(squareSide, squareSide, Color.rgb(255, 255 - colors[i], 255 - colors[i]));
            GridPane.setRowIndex(rectangle, 0);
            GridPane.setColumnIndex(rectangle, i);
            tPane.getChildren().add(rectangle);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            draw(getColors(ans[newValue.intValue()]));
        });
    }
}
