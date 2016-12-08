package ru.ifmo.ctddev.segal.hw4.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.ifmo.ctddev.segal.hw4.main_solvers.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextField dxValue;
    @FXML
    private TextField dtValue;
    @FXML
    private TextField kappaValue;
    @FXML
    private TextField sizeValue;
    @FXML
    private TextField uValue;

    @FXML
    private CheckBox explicitDownstreamSolver;
    @FXML
    private CheckBox explicitUpstreamSolver;
    @FXML
    private CheckBox implicitDownstreamSolver;
    @FXML
    private CheckBox implicitUpstreamSolver;
    @FXML
    private CheckBox leapfrogSolver;

    private double dx;
    private double dt;
    private double kappa;
    private int size;
    private double u;

    private List<Solver> solvers = new ArrayList<>();

    public void update() {
        dx = Double.parseDouble(dxValue.getText());
        dt = Double.parseDouble(dtValue.getText());
        kappa = Double.parseDouble(kappaValue.getText());
        size = Integer.parseInt(sizeValue.getText());
        u = Double.parseDouble(uValue.getText());

        solvers.clear();
        if (explicitDownstreamSolver.isSelected()) {
            solvers.add(new ExplicitDownstreamSolver());
        }
        if (implicitDownstreamSolver.isSelected()) {
            solvers.add(new ImplicitDownstreamSolver());
        }
        if (explicitUpstreamSolver.isSelected()) {
            solvers.add(new ExplicitUpstreamSolver());
        }
        if (implicitUpstreamSolver.isSelected()) {
            solvers.add(new ImplicitUpstreamSolver());
        }
        if (leapfrogSolver.isSelected()) {
            solvers.add(new LeapfrogSolver());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dxValue.addEventFilter(KeyEvent.KEY_TYPED, new OnlyDouble());
        dxValue.setText("0.001");
        dtValue.addEventFilter(KeyEvent.KEY_TYPED, new OnlyDouble());
        dtValue.setText("0.001");
        kappaValue.addEventFilter(KeyEvent.KEY_TYPED, new OnlyDouble());
        kappaValue.setText("0");
        uValue.addEventFilter(KeyEvent.KEY_TYPED, new OnlyDouble());
        uValue.setText("1");
        sizeValue.addEventFilter(KeyEvent.KEY_TYPED, new OnlyInt());
        sizeValue.setText("100");
    }

    @FXML
    private void build(ActionEvent event) throws IOException {
        update();

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Plot.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(root));

        PlotController controller = loader.getController();
        controller.initializeData(dx, dt, kappa, size, u, solvers);

        stage.show();
    }

    private class OnlyDouble implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            if (!event.getCharacter().matches("[0-9. \n-]")) {
                event.consume();
            }
        }
    }

    private class OnlyInt implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            if (!event.getCharacter().matches("[0-9\n]")) {
                event.consume();
            }
        }
    }
}
