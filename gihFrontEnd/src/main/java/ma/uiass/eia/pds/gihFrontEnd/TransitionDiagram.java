package ma.uiass.eia.pds.gihFrontEnd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.Arrays;

public class TransitionDiagram extends Application {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final String[] STATES = {"disponible", "occupé", "défectueux", "opérationnel"};
    private static final Color[] STATE_COLORS = {Color.GREEN, Color.YELLOW, Color.RED, Color.BLUE};
    private static final Color TRANSITION_COLOR = Color.BLACK;
    private static final int STATE_RADIUS = 50;
    private static final double STATE_SPACING = 1.2 * STATE_RADIUS;

    private double[][] transitionMatrix;

    public TransitionDiagram(double[][] transitionMatrix) {
        this.transitionMatrix = transitionMatrix;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();

        // Dessin des cercles pour les états
        Circle[] circles = new Circle[STATES.length];
        for (int i = 0; i < STATES.length; i++) {
            Circle circle = new Circle(STATE_RADIUS + (i % 2) * STATE_SPACING, STATE_RADIUS + (i / 2) * STATE_SPACING, STATE_RADIUS);
            circle.setFill(STATE_COLORS[i]);
            circles[i] = circle;
            root.getChildren().add(circle);
        }

        // Dessin des lignes pour les transitions
        for (int i = 0; i < STATES.length; i++) {
            for (int j = 0; j < STATES.length; j++) {
                if (transitionMatrix[i][j] > 0) {
                    Line line = new Line(circles[i].getCenterX(), circles[i].getCenterY(), circles[j].getCenterX(), circles[j].getCenterY());
                    line.setStroke(TRANSITION_COLOR);
                    root.getChildren().add(line);
                }
            }
        }

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        double[][] transitionMatrix = {{0.5, 0.25, 0.0, 0.25},
                {0.1, 0.8, 0.0, 0.1},
                {0.0, 0.0, 1.0, 0.0},
                {0.2, 0.1, 0.0, 0.7}};

        TransitionDiagram diagram = new TransitionDiagram(transitionMatrix);
        diagram.start(new Stage());
    }
}
