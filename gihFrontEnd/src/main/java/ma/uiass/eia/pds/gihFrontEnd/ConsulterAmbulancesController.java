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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.State;
import ma.uiass.eia.pds.gihBackEnd.model.TypeAmbulance;
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
    private TableColumn<Ambulance, TypeAmbulance> type;

    @FXML
    private TableColumn<Ambulance,Integer> km;

    @FXML
    private Button btnRevisions;
    @FXML
    TableColumn<Ambulance, State> state;

    @FXML
    private TableColumn<Ambulance, LocalDate> dateCol;


    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.setCellValueFactory(new PropertyValueFactory<>("typeAmbulance"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateMiseEnCirculation"));
        immatriculCol.setCellValueFactory(new PropertyValueFactory<>("immatriculation"));
        km.setCellValueFactory(new PropertyValueFactory<Ambulance,Integer>("km"));
        tblAmbulances.setItems(FXCollections.observableList(getAmbulance()));
        state.setCellValueFactory(new PropertyValueFactory<Ambulance,State>("state"));

        tblAmbulances.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    btnRevisions.setDisable(false);
                }
            }
        });

    }

    @FXML
    void onRevisions(ActionEvent event) {
        ConsulterRevisionsController.setAmbulance(tblAmbulances.getSelectionModel().getSelectedItem());
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("consulterRevisions.fxml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Scene scene = new Scene(fxmlLoader);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
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
}
