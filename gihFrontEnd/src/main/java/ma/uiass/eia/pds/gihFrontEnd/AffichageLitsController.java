package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AffichageLitsController implements Initializable {

    @FXML
    private Button btnAjouter;

    @FXML
    private TableColumn<Lit, Espace> espaceCol;

    @FXML
    private TableColumn<Lit, EtatLit> etatCol;
    @FXML
    private TableColumn<Lit, DisponibiliteLit> disponibiliteCol;
    @FXML
    private TableColumn<Lit, Integer> idCol;

    @FXML
    private TableColumn<Lit, Marque> marqueCol;

    @FXML
    private TableView<Lit> tblLits;

    @FXML
    private TableColumn<Lit, TypeLit> typeCol;

    @FXML
    private TableColumn<Lit, Void> actionsCol;
    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //actionsCol = new TableColumn<>("Actions");

// Set the cell factory for the column to create custom cells with two buttons
        actionsCol.setCellFactory(param -> new TableCell<>() {
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
                        int row = getIndex();
                        int id = tblLits.getItems().get(row).getN_lit();
                        Request request = new Request.Builder().url("http://localhost:9998/lit/delete/"+id).build();
                        try {
                            Response response= okHttpClient.newCall(request).execute();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        // Handle the delete button action here

                    });

                    // Set the edit button action
                    editButton.setOnAction(event -> {
                        // Handle the edit button action here
                        int row = getIndex();
                        Lit lit = tblLits.getItems().get(row);
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("popupModifier.fxml"));
                        try {
                            Parent root = loader.load();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        PopUpModifierController controller = loader.getController();
                        controller.getLitUpdate(lit);
                        Parent fxmlLoader = null;
                        try {
                            fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("popupModifier.fxml"));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Scene scene = new Scene(fxmlLoader);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.showAndWait();


                    });

                    // Display both buttons in the cell
                    HBox hbox = new HBox(deleteButton, editButton);
                    hbox.setSpacing(5);
                    setGraphic(hbox);
                }
            }
        });

        // Add the column to the TableView
        //tblLits.getColumns().add(actionsCol);



        Request request = new Request.Builder().url("http://localhost:9998/lit/getlits").build();
        Call call = okHttpClient.newCall(request);
        ObjectMapper mapper = new ObjectMapper();

//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(Espace.class, new EspaceDeserializer())
//                .create();
        Response response = null;
        List<Lit> lits = null;
        try {
            response = okHttpClient.newCall(request).execute();
            lits = mapper.readValue(response.body().charStream(), new TypeReference<List<Lit>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //List<Lit> lits = List.of(gson.fromJson(response.body().charStream(), Lit[].class));

        idCol.setCellValueFactory(new PropertyValueFactory<>("n_lit"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        disponibiliteCol.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        marqueCol.setCellValueFactory(new PropertyValueFactory<>("marque"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("typeLit"));
        espaceCol.setCellValueFactory(new PropertyValueFactory<>("espace"));

        tblLits.setItems(FXCollections.observableList(lits));


    }

    public void ajouterPopup(ActionEvent actionEvent) throws IOException {

        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("popupAjouter.fxml"));
        Scene scene = new Scene(fxmlLoader);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();


    }
}
