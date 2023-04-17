package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import ma.uiass.eia.pds.gihBackEnd.model.Commande;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TraiterDemandes implements Initializable {
    @FXML
    private Button traiter;
    @FXML
    private ListView<Commande> demandes;

    OkHttpClient okHttpClient = new OkHttpClient();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        demandes.setItems(FXCollections.observableArrayList(getDemandes()));
    }
    public void onDemande(ActionEvent event)throws IOException {
        Commande commande=demandes.getSelectionModel().getSelectedItem();
        ObjectMapper mapper = new ObjectMapper();
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(commande));

        Request request = new Request.Builder()
                .url("http://localhost:9998/dm/traiterdemande")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

    }

    private List<Commande> getDemandes() {
        return new ArrayList<Commande>();
    }
}
