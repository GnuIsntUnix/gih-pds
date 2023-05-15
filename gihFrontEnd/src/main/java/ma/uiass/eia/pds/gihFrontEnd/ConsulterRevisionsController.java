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
import ma.uiass.eia.pds.gihBackEnd.model.Historique;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterRevisionsController implements Initializable {

    private static Ambulance ambulance;
    @FXML
    private TableColumn<Revision, LocalDate> dR;

    @FXML
    private TableColumn<Revision, Integer> idR;

    @FXML
    private TableView<Revision> table;
    private OkHttpClient okHttpClient = new OkHttpClient();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idR.setCellValueFactory(new PropertyValueFactory<Revision, Integer>("id"));
        dR.setCellValueFactory(new PropertyValueFactory<Revision,LocalDate>("dateRevision"));
        table.setItems(FXCollections.observableArrayList(getRevisions(ConsulterRevisionsController.getAmbulance().getId())));

    }
    public List<Revision> getRevisions(int id){
        Request request = new Request.Builder().url("http://localhost:9998/revision/getrevisionsonambulance/" + id).build();
        Call call = okHttpClient.newCall(request);
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Revision> revisions = null;
        try {
            response = okHttpClient.newCall(request).execute();
            revisions = mapper.readValue(response.body().charStream(), new TypeReference<List<Revision>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return revisions;
    }

    public static Ambulance getAmbulance() {
        return ambulance;
    }

    public static void setAmbulance(Ambulance ambulance) {
        ConsulterRevisionsController.ambulance = ambulance;
    }

}
