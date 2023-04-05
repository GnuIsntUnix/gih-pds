package ma.uiass.eia.pds.gihFrontEnd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class menuDashboardController implements Initializable {
    @FXML
    private BorderPane borderPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(50);
        gridPane.setVgap(50);
        for (int i = 0; i <4;i++) {

            for (int j = 0; j < 4; j++) {
                VBox vbox = new VBox();
                Label lbl = new Label(String.valueOf(i));
                vbox.getChildren().add(lbl);
                gridPane.add(vbox, i, j);
            }
        }
        borderPane.setCenter(gridPane);
    }
}
