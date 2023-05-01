package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ma.uiass.eia.pds.gihBackEnd.model.Fournisseur;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FournisseurController implements Initializable {

    @FXML
    private Button btnAjouter;

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtTel;

    @FXML
    private TableColumn<Fournisseur, Void > colAction;

    @FXML
    private TableColumn<Fournisseur, String> colAdresse;

    @FXML
    private TableColumn<Fournisseur, String> colEmail;

    @FXML
    private TableColumn<Fournisseur, String> colNom;

    @FXML
    private TableColumn<Fournisseur, String> colTel;

    @FXML
    private TableView<Fournisseur> tableFournisseur;

    OkHttpClient okHttpClient = new OkHttpClient();


    @FXML
    void onBtnAjouter(ActionEvent event) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String nomF = txtNom.getText();
        String emailF = txtEmail.getText();
        String adressF = txtAdress.getText();
        String telF = txtTel.getText();


        Fournisseur fournisseur = new Fournisseur(nomF, emailF, adressF, telF);
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(fournisseur));

        Request request = new Request.Builder()
                .url("http://localhost:9998/fournisseur/save")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        txtNom.setText(null);
        txtEmail.setText(null);
        txtAdress.setText(null);
        txtTel.setText(null);
        initialize(null,null);

    }

    public void saveFournisseur(Fournisseur fournisseur) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(fournisseur));

        System.out.println(mapper.writeValueAsString(fournisseur));

        Request request = new Request.Builder()
                .url("http://localhost:9998/fournisseur/save")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        initialize(null, null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableFournisseur.setItems(FXCollections.observableArrayList(getFournisseur()));

        colAdresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        colAction.setCellFactory(param -> new TableCell<>() {
            private final FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            private final FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.EDIT);

            private final Button deleteButton = new Button("", deleteIcon);
            private final Button editButton = new Button("", editIcon);

            // Override the updateItem method to set the button actions and display them in the cell
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // Set the delete button action
                    deleteButton.setOnAction(event -> {
                        System.out.println("2");
                        int row = getIndex();
                        int id = tableFournisseur.getItems().get(row).getId();
                        Request request = new Request.Builder().url("http://localhost:9998/fournisseur/delete/"+id).delete().build();
                        try {
                            Response response= okHttpClient.newCall(request).execute();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        // Handle the delete button action here
                        initialize(null, null);
                    });

//                    // Set the edit button action
//                    editButton.setOnAction(event -> {
//                        // Handle the edit button action here
//                        int row = getIndex();
//                        Fournisseur selectedFournisseur = tableFournisseur.getItems().get(row);
//                        TextField nomField = new TextField(selectedFournisseur.getNom());
//                        TextField adresseField = new TextField(selectedFournisseur.getAdresse());
//                        TextField telField = new TextField(selectedFournisseur.getTel());
//                        TextField emailField = new TextField(selectedFournisseur.getEmail());
//
//
//
//                    });

                    editButton.setOnAction(event -> {
                        // Get the selected Fournisseur object
                        Fournisseur selectedFournisseur = tableFournisseur.getSelectionModel().getSelectedItem();

                        // Check if a Fournisseur is selected before displaying the edit dialog
                        if (selectedFournisseur == null) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("No Fournisseur Selected");
                            alert.setContentText("Please select a Fournisseur to edit.");
                            alert.showAndWait();
                            return;
                        }

                        // Create the edit dialog
                        Dialog<Fournisseur> dialog = new Dialog<>();
                        dialog.setTitle("Edit Fournisseur");

                        // Set the button types
                        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

                        // Set up the dialog fields (e.g. text fields for each property of the Fournisseur object)
                        GridPane gridPane = new GridPane();
                        gridPane.setHgap(10);
                        gridPane.setVgap(10);
                        gridPane.setPadding(new Insets(20, 150, 10, 10));

                        TextField nomTextField = new TextField(selectedFournisseur.getNom());
                        TextField adresseTextField = new TextField(selectedFournisseur.getAdresse());
                        TextField telTextField = new TextField(selectedFournisseur.getTel());
                        TextField emailTextField = new TextField(selectedFournisseur.getEmail());

                        gridPane.add(new Label("Nom:"), 0, 0);
                        gridPane.add(nomTextField, 1, 0);
                        gridPane.add(new Label("Adresse:"), 0, 1);
                        gridPane.add(adresseTextField, 1, 1);
                        gridPane.add(new Label("Tel:"), 0, 2);
                        gridPane.add(telTextField, 1, 2);
                        gridPane.add(new Label("Email:"), 0, 3);
                        gridPane.add(emailTextField, 1, 3);

                        dialog.getDialogPane().setContent(gridPane);

                        // Set the initial field values to the current values of the selected Fournisseur
                        nomTextField.setText(selectedFournisseur.getNom());
                        adresseTextField.setText(selectedFournisseur.getAdresse());
                        telTextField.setText(selectedFournisseur.getTel());
                        emailTextField.setText(selectedFournisseur.getEmail());

                        // Convert the result to a Fournisseur object when the save button is clicked
                        dialog.setResultConverter(dialogButton -> {
                            if (dialogButton == saveButtonType) {
                                Fournisseur editedFournisseur = new Fournisseur(
                                        nomTextField.getText(),
                                        emailTextField.getText(),
                                        adresseTextField.getText(),
                                        telTextField.getText()
                                );
                                editedFournisseur.setId(selectedFournisseur.getId());
                                return editedFournisseur;
                            }
                            return null;
                        });

                        // Display the edit dialog and handle the save button action
                        Optional<Fournisseur> result = dialog.showAndWait();
                        result.ifPresent(editedFournisseur -> {
                            // Set the properties of the editedFournisseur object from the dialog fields
                            // ...
                            ObjectMapper mapper = new ObjectMapper();

                            RequestBody body = null;
                            try {
                                body = RequestBody.create(
                                        MediaType.parse("application/json"), mapper.writeValueAsString(editedFournisseur));
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }

                            Request request = new Request.Builder()
                                    .url("http://localhost:9998/fournisseur/update")
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


                    });

                    // Display both buttons in the cell
                    HBox hbox = new HBox(deleteButton, editButton);
                    hbox.setSpacing(5);
                    setGraphic(hbox);
                }
            }
        });

    }

    public List<Fournisseur> getFournisseur(){
        Request request = new Request.Builder().url("http://localhost:9998/fournisseur/getfournisseurs").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Fournisseur> fournisseurs = null;
        try {
            response = okHttpClient.newCall(request).execute();
            fournisseurs = mapper.readValue(response.body().charStream(), new TypeReference<List<Fournisseur>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fournisseurs;
    }


}
