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
    private Button btnCommandes;

    @FXML
    private Button btnUsers;
    @FXML
    private Button btnDMs;
    @FXML
    private Button btnCommandesDM;

    @FXML
    private Button btnDashboard;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private BorderPane centerPane;


    public void onDashboardClick(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("menuDashboard.fxml"));
        centerPane.setCenter(fxmlLoader);
    }

    public void onDMsClick(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("dms.fxml"));
        centerPane.setCenter(fxmlLoader);
    }

    public void onCommandeDMClick(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("livraison.fxml"));
        centerPane.setCenter(fxmlLoader);

    }

    public void onLitClick(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("affichageLits.fxml"));
        centerPane.setCenter(fxmlLoader);
    }
    public void onCommandeClick(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("commandes.fxml"));
        centerPane.setCenter(fxmlLoader);
    }
}
