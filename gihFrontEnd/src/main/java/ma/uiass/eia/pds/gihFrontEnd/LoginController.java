package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Utilisateur;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;

import java.io.IOException;
import java.util.List;

public class LoginController {
        @FXML
        private BorderPane rootPane;
        @FXML
        private VBox vboxLogin;

        @FXML
        private TextField usernameField;

        @FXML
        private PasswordField passwordField;

        @FXML
        private Button loginButton;
        @FXML
        private Hyperlink hyperInscription;

        @FXML
        public void handleLoginButtonAction(ActionEvent event) throws IOException {
            String username = usernameField.getText();
            String password = passwordField.getText();

//            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("menu(Chef).fxml"));
//            Scene scene = new Scene(fxmlLoader);
//            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url("http://localhost:9998/utilisateur/" +
                    username +
                    "/" +
                    password).build();
            Call call = okHttpClient.newCall(request);
            ObjectMapper mapper = new ObjectMapper();

//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(Espace.class, new EspaceDeserializer())
//                .create();
            Response response = null;
            Utilisateur user = null;
            try {
                response = okHttpClient.newCall(request).execute();
                user = mapper.readValue(response.body().charStream(), Utilisateur.class);
            } catch (IOException e) {
                System.out.println("Utilisateur ou Mot de Passe incorrect");;
            }

            if (user.getFonction().equalsIgnoreCase("admin")){
                System.out.println("admin login");
                Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("menu(Admin).fxml"));
                Scene scene = new Scene(fxmlLoader);
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }



            // TODO: Check the username and password against a database or other authentication system

            //if (/* username and password are valid */) {
                // TODO: Navigate to the main application screen
            //} else {
                // TODO: Display an error message to the user
            //}
        }

        public void handleHyperAction(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("inscriptionScene.fxml"));
            rootPane.setCenter(root);
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1300));
            fadeTransition.setNode(root);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();

        }
}