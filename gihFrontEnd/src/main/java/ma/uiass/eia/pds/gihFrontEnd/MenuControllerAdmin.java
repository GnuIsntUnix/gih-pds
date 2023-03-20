package ma.uiass.eia.pds.gihFrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MenuControllerAdmin {

    @FXML
    private Button buttonLit;

    @FXML
    private BorderPane centerBorder;

    public void onLitClick(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("affichageLits.fxml"));
        centerBorder.setCenter(fxmlLoader);
    }
}
