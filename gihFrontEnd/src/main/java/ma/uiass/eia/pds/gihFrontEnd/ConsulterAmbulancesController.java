package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.EtatAmbulance;
import ma.uiass.eia.pds.gihBackEnd.model.Historique;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterAmbulancesController implements Initializable {

    @FXML
    private TableView<Ambulance> tblAmbulances;
    @FXML
    private TableColumn<Ambulance, String> immatriculCol;

    @FXML
    private Button btnHistorique;

    @FXML
    private Button btnRevisions;

    @FXML
    private TableColumn<Ambulance, LocalDate> dateCol;

    @FXML
    private TableColumn<Ambulance, EtatAmbulance> etatCol;

    @FXML
    private TableColumn<Ambulance, Integer> idCol;

    @FXML
    private Button btnEffectRevision;

    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateMiseEnCirculation"));
        immatriculCol.setCellValueFactory(new PropertyValueFactory<>("immatriculation"));
        etatCol.setCellValueFactory(table ->
                new SimpleObjectProperty<>(getLast(table.getValue().getId()).getEtatAmbulance()));

        tblAmbulances.setItems(FXCollections.observableList(getAmbulance()));

        tblAmbulances.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    btnHistorique.setDisable(false);
                    btnRevisions.setDisable(false);
                }
            }
        });

    }



    @FXML
    void onHistorique(ActionEvent event) {
        ConsulterHistoriquesController.setAmbulance(tblAmbulances.getSelectionModel().getSelectedItem());
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("consulterHistoriques.fxml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Scene scene = new Scene(fxmlLoader);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void onRevisions(ActionEvent event) {



    }
    @FXML
    void onEffectRevisions(ActionEvent event) {

        Popup popup = new Popup();


        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Court durée", "Longe durée");

        Button confirmBtn = new Button("Valider");
        confirmBtn.setOnAction(even -> {
            popup.hide();
            showConfirmation();
        });

        VBox popupContent = new VBox(10, new Label("Selectionner un type de revision:"), comboBox, confirmBtn);
        popupContent.setAlignment(Pos.CENTER);
        popupContent.setPadding(new Insets(10));

        // Create a custom Region as the background
        Region background = new Region();
        background.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(5), Insets.EMPTY)));
        background.setEffect(new javafx.scene.effect.DropShadow(10, Color.GRAY));
        background.setPadding(new Insets(20));

        // Add the popup content as a child of the background
        StackPane stackPane = new StackPane(background, popupContent);


        popup.getContent().add(stackPane);
        popup.setAutoHide(true);
        popup.show(getPrimaryStage());

    }

    private Stage getPrimaryStage() {
        return (Stage) btnRevisions.getScene().getWindow();
    }

    private void showConfirmation() {
        Popup confirmationPopup = new Popup();

        Label label = new Label("Revision Confirmée !");

        VBox popupContent = new VBox(label);
        popupContent.setAlignment(Pos.CENTER);
        popupContent.setPadding(new Insets(10));

        confirmationPopup.getContent().add(popupContent);
        confirmationPopup.setAutoHide(true);
        confirmationPopup.show(getPrimaryStage());


    }

    public List<Historique> getHistorique(int id){
        Request request = new Request.Builder().url("http://localhost:9998/historique/gethistoriquesforambulance/" + id).build();
        Call call = okHttpClient.newCall(request);
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Historique> historiques = null;
        try {
            response = okHttpClient.newCall(request).execute();
            historiques = mapper.readValue(response.body().charStream(), new TypeReference<List<Historique>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return historiques;
    }

    public List<Ambulance> getAmbulance(){
        Request request = new Request.Builder().url("http://localhost:9998/ambulance/getambulances").build();
        ObjectMapper mapper = new ObjectMapper();
        Response response = null;
        List<Ambulance> ambulances = null;
        try {
            response = okHttpClient.newCall(request).execute();
            ambulances = mapper.readValue(response.body().charStream(), new TypeReference<List<Ambulance>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ambulances;
    }

    public Historique getLast(int id){
        List<Historique> historiques = getHistorique(id);
        Historique last = new Historique();
        if (historiques.size() > 0){
            last = historiques.get(historiques.size()-1);
        }
        return last;
    }


}
