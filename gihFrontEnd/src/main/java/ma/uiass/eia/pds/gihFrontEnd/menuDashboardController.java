package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Pair;
import ma.uiass.eia.pds.gihBackEnd.model.DisponibiliteLit;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.model.TypeDM;
import okhttp3.*;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class menuDashboardController implements Initializable {
    @FXML
    private BorderPane borderPane;

    @FXML
    private Button ajouterBtn;
    @FXML
    private Button btnRetour;
    OkHttpClient okHttpClient = new OkHttpClient();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnRetour.setOnAction( new SceneChangeEventHandler("accueil.fxml"));
        int numColumns = 4; // define the number of columns
        List<Service> services = getServices();
        int numItems = services.size(); // define the number of items

        GridPane gridPane = new GridPane();
        gridPane.setHgap(40); // set the horizontal gap between columns
        gridPane.setVgap(10); // set the vertical gap between rows
        gridPane.setAlignment(Pos.CENTER);
        System.out.println(services);
        int i = 0;
        for (Service s:services) {
            System.out.println(s);
            VBox vBox = new VBox();
            HBox hBox = new HBox();
            Label lblService = new Label(s.getNomService());
            Label lblDispo = new Label(String.valueOf(getDisp(s.getIdService()).size()));
            lblDispo.setStyle("-fx-background-color:green");
            lblDispo.setPadding(new Insets(5));
            Label lblOccup = new Label(String.valueOf(getOccup(s.getIdService()).size()));
            lblOccup.setStyle("-fx-background-color:red");
            lblOccup.setPadding(new Insets(5));
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);
            hBox.getChildren().addAll(lblDispo, lblOccup);
            vBox.setStyle("-fx-border-color:black");
            vBox.setPadding(new Insets(10));
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(lblService, hBox);
            // calculate the column and row indices of the cell
            int colIndex = i % numColumns;
            int rowIndex = i / numColumns;

            // add node to the cell
            GridPane.setConstraints(vBox, colIndex, rowIndex);
            gridPane.getChildren().add(vBox);
            i++;
        }
        borderPane.setCenter(gridPane);

        Label lblName = new Label("John Doe");
        Label lblStreet = new Label("123 Hello Street");
        Label lblCityStateZip = new Label("MadeUpCity, XX 55555");
        VBox vBox = new VBox(lblName, lblStreet, lblCityStateZip);
        //Create PopOver and add look and feel
        Popup popup = new Popup();
        popup.getContent().add(new Label("3"));


    }
    public List<Service> getServices(){
        Request request = new Request.Builder().url("http://localhost:9998/service/getservices").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Service> services = null;
        try {
            response = okHttpClient.newCall(request).execute();
            services = mapper.readValue(response.body().charStream(), new TypeReference<List<Service>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return services;
    }
    public List<Lit> getLitByService(int id){
        Request request = new Request.Builder().url("http://localhost:9998/lit/getlits/byservice/"+id).build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Lit> lits = null;
        try {
            response = okHttpClient.newCall(request).execute();
            lits = mapper.readValue(response.body().charStream(), new TypeReference<List<Lit>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lits;
    }

    public List<Lit> getDisp(int id){
        List<Lit> lits = getLitByService(id);
        List<Lit> dispo = new ArrayList<>();
        lits.forEach(lit -> {
            if (lit.getDisponibiliteLit() == DisponibiliteLit.Di){
                dispo.add(lit);
            }
        });
        return dispo;
    }public List<Lit> getOccup(int id){
        List<Lit> lits = getLitByService(id);
        List<Lit> occup = new ArrayList<>();
        lits.forEach(lit -> {
            if (lit.getDisponibiliteLit() == DisponibiliteLit.O){
                occup.add(lit);
            }
        });
        return occup;
    }

    public void onAjouterBtn(ActionEvent event){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Ajouter");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField code = new TextField();
        code.setPromptText("Code");
        TextField nom = new TextField();
        nom.setPromptText("Nom");

        gridPane.add(code, 0, 0);
        gridPane.add(nom, 0, 1);

        dialog.getDialogPane().setContent(gridPane);

        // Request focus on the username field by default.
        Platform.runLater(code::requestFocus);

        // Convert the result nom a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(code.getText(), nom.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(pair -> {
            try {
                saveService(new Service(pair.getKey(), pair.getValue()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            initialize(null,null);
        });
    }

    public void saveService(Service service) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(service));

        Request request = new Request.Builder()
                .url("http://localhost:9998/service/save")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
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
}
