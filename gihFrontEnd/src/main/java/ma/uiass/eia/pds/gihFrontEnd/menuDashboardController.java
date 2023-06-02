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

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Pair;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.*;
import org.controlsfx.control.PopOver;

import java.io.File;
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

    @FXML
    private Button deleteBtn;

    @FXML
    private Button modifyBtn;


    @FXML
    void onDeleteBtn(ActionEvent event) {
        Request request = new Request.Builder().url("http://localhost:9998/service/delete/"+menuDashboardController.getService().getIdService()).delete().build();
        try {
            Response response= okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        initialize(null, null);
    }

    @FXML
    void onModifyButton(ActionEvent event) {
        // Get the selected Fournisseur object
        Service service = menuDashboardController.getService();

        // Check if a Fournisseur is selected before displaying the edit dialog
        if (service == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Service Selected");
            alert.setContentText("Please select a Service to edit.");
            alert.showAndWait();
            return;
        }

        // Create the edit dialog
        Dialog<Service> dialog = new Dialog<>();
        dialog.setTitle("Edit Service");

        // Set the button types
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // Set up the dialog fields (e.g. text fields for each property of the Fournisseur object)
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        TextField nomTextField = new TextField(service.getNomService());
        TextField codeTextField = new TextField(service.getCodeS());

        gridPane.add(new Label("Nom:"), 0, 0);
        gridPane.add(nomTextField, 1, 0);
        gridPane.add(new Label("Adresse:"), 0, 1);
        gridPane.add(codeTextField, 1, 1);

        dialog.getDialogPane().setContent(gridPane);

        // Set the initial field values to the current values of the selected Fournisseur
        nomTextField.setText(service.getNomService());
        codeTextField.setText(service.getCodeS());

        // Convert the result to a Fournisseur object when the save button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                Service editedService = new Service(
                        codeTextField.getText(),
                        nomTextField.getText()
                );
                editedService.setIdService(service.getIdService());
                return editedService;
            }
            return null;
        });

        // Display the edit dialog and handle the save button action
        Optional<Service> result = dialog.showAndWait();
        result.ifPresent(editedService -> {
            // Set the properties of the editedFournisseur object from the dialog fields
            // ...
            ObjectMapper mapper = new ObjectMapper();

            RequestBody body = null;
            try {
                body = RequestBody.create(
                        MediaType.parse("application/json"), mapper.writeValueAsString(editedService));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            Request request = new Request.Builder()
                    .url("http://localhost:9998/service/update")
                    .put(body)
                    .build();

            Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Refresh the table view with the updated data
            initialize(null, null);
        });
    }

    private static Service service;
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
            vBox.setPadding(new Insets(10));
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(lblService, hBox);
            Button button = new Button();
            button.setGraphic(vBox);
            button.getStylesheets().clear();
            File file = new File("gihFrontEnd/src/main/resources/css/services.css");
            button.getStylesheets().add("file:///" + file.getAbsolutePath().replace("\\", "/"));
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    VBox vBox1 = (VBox) button.getGraphic();
                    menuDashboardController.setService(s);
                }
            });
            // calculate the column and row indices of the cell
            int colIndex = i % numColumns;
            int rowIndex = i / numColumns;

            // add node to the cell
            GridPane.setConstraints(button, colIndex, rowIndex);
            gridPane.getChildren().add(button);
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

    public static Service getService() {
        return service;
    }

    public static void setService(Service service) {
        menuDashboardController.service = service;
    }
}
