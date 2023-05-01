package ma.uiass.eia.pds.gihFrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MenuControllerLogistique {

    @FXML
    private Button btnLits;


    @FXML
    private Button btnUsers;
    @FXML
    private Button btnDMs;
    @FXML
    private Button btnRetour;

    @FXML
    private Label lblService;


    @FXML
    private Button btnDashboard;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private BorderPane centerPane;

    @FXML
    private TabPane tabPane;


    public void onDashboardClick(ActionEvent event) throws IOException {

    }

    public void onDMsClick(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("livraison.fxml"));
        centerPane.setCenter(fxmlLoader);
    }

    public void onLitClick(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("gererCommandes.fxml"));
        centerPane.setCenter(fxmlLoader);
    }
}
