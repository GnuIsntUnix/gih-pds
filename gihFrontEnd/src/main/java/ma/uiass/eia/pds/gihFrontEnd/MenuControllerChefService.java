package ma.uiass.eia.pds.gihFrontEnd;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ma.uiass.eia.pds.gihBackEnd.model.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuControllerChefService implements Initializable {


    private static Service service;

    @FXML
    private Button btnLits;


    @FXML
    private Button btnUsers;
    @FXML
    private Button btnDMs;
    @FXML
    private Button btnRetour;

    @FXML
    private Button btnAmbulances;

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
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("demandesDm.fxml"));
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

    public static Service getService() {
        return service;
    }

    public static void setService(Service service) {
        MenuControllerChefService.service = service;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //btnRetour.setOnAction( new SceneChangeEventHandler("accueil.fxml"));
        lblService.setDisable(false);
        lblService.setText(MenuControllerChefService.service.getNomService());
    }

    class SceneChangeEventHandler implements EventHandler<ActionEvent> {
        private final String fxmlResourceName;

        public SceneChangeEventHandler(String fxmlResourceName) {
            this.fxmlResourceName = fxmlResourceName;
        }

        @Override
        public void handle(ActionEvent event) {
            try {
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();

                changeScene(stage, fxmlResourceName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void changeScene(
            Stage stage,
            String fxmlResourceName
    ) throws IOException {
        Parent parent = FXMLLoader.load(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource(
                                fxmlResourceName
                        )
                )
        );
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.setTitle(fxmlResourceName);
        stage.show();
        stage.centerOnScreen();
    }

    public void onBtnRetour(ActionEvent event) throws IOException {
        MenuControllerChefService.setService(null);
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();
        changeScene(stage,"accueil.fxml");
    }

    public void onBtnAmbulances(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("AmbulanceCreation.fxml"));
        centerPane.setCenter(fxmlLoader);
    }
}
