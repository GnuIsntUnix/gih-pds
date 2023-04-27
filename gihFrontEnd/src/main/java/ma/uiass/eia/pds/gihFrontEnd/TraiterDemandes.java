package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.Commande;
import ma.uiass.eia.pds.gihBackEnd.model.DemandeDm;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TraiterDemandes implements Initializable {
    @FXML
    private Button traiter;
    //@FXML
    //private ListView<DemandeDm> demandes;
    @FXML
    private TableView<DemandeDm> table;
    @FXML
    private TableColumn<DemandeDm, Service> ser;
    @FXML
    private TableColumn<DemandeDm,Integer> id;
    @FXML
    private TableColumn<DemandeDm, Date> date;
    @FXML
    private TableColumn<DemandeDm,Boolean> valide;

    OkHttpClient okHttpClient = new OkHttpClient();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setEditable(true);
        id.setCellValueFactory(new PropertyValueFactory<DemandeDm,Integer>("idDemande"));
        ser.setCellValueFactory(new PropertyValueFactory<DemandeDm,Service>("service"));
        date.setCellValueFactory(new PropertyValueFactory<DemandeDm,Date>("dateDemande"));
        valide.setCellValueFactory(new PropertyValueFactory<DemandeDm,Boolean>("valide"));
        table.setItems(FXCollections.observableArrayList(getDemandes()));
        table.getSelectionModel().selectFirst();
        table.requestLayout();
        //demandes;
    }
    public void onDemande(ActionEvent event)throws IOException {
        DemandeDm dm=table.getSelectionModel().getSelectedItem();
        ObjectMapper mapper = new ObjectMapper();

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(dm));
        System.out.println(mapper.writeValueAsString(dm));

        Request request = new Request.Builder()
                .url("http://localhost:9998/demande/traiter")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        initialize(null,null);
    }

    private List<DemandeDm> getDemandes() {
        Request request = new Request.Builder().url("http://localhost:9998/demande/getdemandes").build();
        ObjectMapper mapper = new ObjectMapper();
        Response response = null;
        List<DemandeDm> dms = null;
        try {
            response = okHttpClient.newCall(request).execute();
            dms = mapper.readValue(response.body().charStream(), new TypeReference<List<DemandeDm>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dms;
    }
}
