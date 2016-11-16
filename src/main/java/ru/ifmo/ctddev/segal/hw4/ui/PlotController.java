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

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class PlotController implements Initializable {

    private double dx;
    private double dt;
    private double kappa;
    private int size;
    private double u;
    private double[][] ans;
    private int[][] spectrum;

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

        spectrum = new int[255][3];
        for (int i = 0; i < spectrum.length; i++) {
            double tt = i;
            spectrum[i][0] = (int)(255d * (0.5d * (1d + abs(tt / 42d - 4d) - abs(tt / 42d - 3d))));
            spectrum[i][1] = (int)(255d * (0.5d * (1d + abs(tt / 84d - 1.5d) - abs(tt / 84d - 0.5d) +
                    (abs(1d - abs(tt / 42d - 4d)) + 1d - abs(tt / 42d - 4d)))));
            spectrum[i][2] = (int)(255d * (0.5d * (1d + abs(tt / 42d - 1d) - abs(tt / 42d) +
                    (abs(1d - abs(tt / 42d - 5d)) + 1d - abs(tt / 42d - 5d)))));
        }
    }

    public int[][] getColors(double[] t) {
        double min = Arrays.stream(t).min().getAsDouble();
        double max = Arrays.stream(t).max().getAsDouble();
        int[][] ans = new int[t.length][3];

        for (int i = 0; i < ans.length; i++) {
            int tt = (int)(((t[i] - min) / (max - min)) * 254.0);
            ans[i] = spectrum[tt];
        }
        return ans;
    }

    public void draw(int[][] colors) {
        tPane.getChildren().clear();
        int len = max(spectrum.length, colors.length);
        double width = (int)tPane.getWidth() / len;
        int cnt = (len / colors.length);
        for (int i = 0; i < cnt * colors.length; i++) {
            Rectangle rectangle = new Rectangle(width, width * 10, Color.rgb(colors[i / len][0], colors[i / cnt][1], colors[i / cnt][2]));
            GridPane.setRowIndex(rectangle, 0);
            GridPane.setColumnIndex(rectangle, i);
            tPane.getChildren().add(rectangle);
        }
        cnt = (len / spectrum.length);
        for (int i = 0; i < cnt * spectrum.length; i++) {
            Rectangle rectangle = new Rectangle(width, width * 10,
                    Color.rgb(spectrum[i / cnt][0], spectrum[i / cnt][1], spectrum[i / cnt][2]));
            GridPane.setRowIndex(rectangle, 1);
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
