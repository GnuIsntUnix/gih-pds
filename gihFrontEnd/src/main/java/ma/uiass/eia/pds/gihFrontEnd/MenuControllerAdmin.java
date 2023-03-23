package ma.uiass.eia.pds.gihFrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuControllerAdmin {

    @FXML
    private Button btnLits;

    @FXML
    private Button btnServices;

    @FXML
    private Button btnUsers;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private BorderPane centerPane;


    public void onLitClick(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("affichageLits.fxml"));
        centerPane.setCenter(fxmlLoader);
    }
    public void onServiceClick(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("gererServices(Admin).fxml"));
        centerPane.setCenter(fxmlLoader);
    }
}
