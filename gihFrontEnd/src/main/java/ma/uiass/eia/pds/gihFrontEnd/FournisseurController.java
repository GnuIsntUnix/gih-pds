package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ma.uiass.eia.pds.gihBackEnd.model.Fournisseur;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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

                    // Set the edit button action
                    editButton.setOnAction(event -> {
                        // Handle the edit button action here
                        int row = getIndex();
                        Fournisseur selectedFournisseur = tableFournisseur.getItems().get(row);
                        TextField nomField = new TextField(selectedFournisseur.getNom());
                        TextField adresseField = new TextField(selectedFournisseur.getAdresse());
                        TextField telField = new TextField(selectedFournisseur.getTel());
                        TextField emailField = new TextField(selectedFournisseur.getEmail());



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
