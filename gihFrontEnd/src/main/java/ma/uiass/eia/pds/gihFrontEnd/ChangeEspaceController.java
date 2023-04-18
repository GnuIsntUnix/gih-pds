package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import ma.uiass.eia.pds.gihBackEnd.model.Commande;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.model.Stock;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChangeEspaceController implements Initializable {
    @FXML
    private Button traiter;
    @FXML
    private ListView<Commande> lc;
    OkHttpClient okHttpClient = new OkHttpClient();
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lc.setItems(FXCollections.observableArrayList(getCommandes()));
    }
    public void onTraiter(ActionEvent event) throws IOException{
        Commande commande=lc.getSelectionModel().getSelectedItem();
        ObjectMapper mapper = new ObjectMapper();
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(commande));

        Request request = new Request.Builder()
                .url("http://localhost:9998/lit/traitercommande")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
    }
    public List<Commande> getCommandes(){
        Request request = new Request.Builder().url("http://localhost:9998/commande/getcommandes").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Commande> commandes = null;
        try {
            response = okHttpClient.newCall(request).execute();
            commandes = mapper.readValue(response.body().charStream(), new TypeReference<List<Commande>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return commandes;
    }
}
