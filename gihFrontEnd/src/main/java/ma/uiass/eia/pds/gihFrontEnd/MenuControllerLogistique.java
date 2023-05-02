package ma.uiass.eia.pds.gihFrontEnd;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuControllerLogistique {

    @FXML
    private Button btnLits;


    @FXML
    private Button btnUsers;
    @FXML
    private Button btnDMs;

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
    @FXML
    private Button btnRetour;


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
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();
        changeScene(stage,"accueil.fxml");
    }
}
