package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AmbulanceCreationController implements Initializable {
    @FXML
    TableView<Ambulance> table;
    @FXML
    TableColumn<Ambulance,Integer> id;
    @FXML
    TableColumn<Ambulance,String> immatriculation;
    @FXML
    DatePicker date;
    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<Ambulance,Integer>("id"));
        immatriculation.setCellValueFactory(new PropertyValueFactory<Ambulance,String>("immatriculation"));
        table.setItems(FXCollections.observableArrayList(getAmbulance()));

    }
    public List<Ambulance> getAmbulance(){
        Request request = new Request.Builder().url("http://localhost:9998/demande/getdemandes").build();
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
