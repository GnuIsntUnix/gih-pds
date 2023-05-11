package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.EtatAmbulance;
import ma.uiass.eia.pds.gihBackEnd.model.Historique;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterHistoriquesController implements Initializable {

    @FXML
    private TableColumn<Historique, LocalDate> debutCol;

    @FXML
    private TableColumn<Historique, EtatAmbulance> etatCol;

    @FXML
    private TableColumn<Historique, Integer> idCol;

    @FXML
    private TableColumn<Historique, LocalDate> revCol;

    @FXML
    private TableView<Historique> tblHistoriques;


    private static Ambulance ambulance;

    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etatAmbulance"));
        debutCol.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        revCol.setCellValueFactory(new PropertyValueFactory<>("dateProchaineRevision"));
        tblHistoriques.setItems(FXCollections.observableList(getHistorique(ConsulterHistoriquesController.getAmbulance().getId())));
    }


    public static Ambulance getAmbulance() {
        return ambulance;
    }

    public static void setAmbulance(Ambulance ambulance) {
        ConsulterHistoriquesController.ambulance = ambulance;
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
}
