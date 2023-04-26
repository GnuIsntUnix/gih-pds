package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.DemandeDm;
import ma.uiass.eia.pds.gihBackEnd.model.EtatDemandeDM;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DemandesDmController implements Initializable {

    @FXML
    private TableColumn<DemandeDm, Void> actionColumn;

    @FXML
    private TableColumn<DemandeDm, LocalDate> dateColumn;

    @FXML
    private TableColumn<DemandeDm, Integer> demandeColumn;

    @FXML
    private TableColumn<DemandeDm, EtatDemandeDM> etatColumn;
    @FXML
    private TableView<DemandeDm> tableDemandes;

    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //tableDemandes.setItems(getDemandes());

        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etatDemande"));
        demandeColumn.setCellValueFactory(new PropertyValueFactory<>("idDemande"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateDemande"));



    }

    public List<DemandeDm> getDemandes(int id){
        Request request = new Request.Builder().url("http://localhost:9998/demande/getdemandes/byservice/"+ id).build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<DemandeDm> demandeDms = null;
        try {
            response = okHttpClient.newCall(request).execute();
            demandeDms = mapper.readValue(response.body().charStream(), new TypeReference<List<DemandeDm>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return demandeDms;
    }
}
