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
import javafx.scene.control.TextField;
import ma.uiass.eia.pds.gihBackEnd.model.Commande;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.model.TypeLit;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CommandesController implements Initializable {
    @FXML
    private Button btnAjouter;

    @FXML
    private ComboBox<TypeLit> cbTypeLit;

    @FXML
    private ComboBox<Service> cboxServices;

    @FXML
    private TextField txtQte;

    @FXML
    private ListView<Commande> lstCommandes;
    OkHttpClient okHttpClient = new OkHttpClient();

    @FXML
    public void onBtnAjouter(ActionEvent event) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        int qte = Integer.parseInt(txtQte.getText());
        TypeLit typeLit = cbTypeLit.getSelectionModel().getSelectedItem();
        Service service = cboxServices.getSelectionModel().getSelectedItem();

        Commande commande = new Commande(service, typeLit, qte);
        System.out.println(commande);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(commande));

        Request request = new Request.Builder()
                .url("http://localhost:9998/commande/save")
                .post(body)
                .build();


        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        txtQte.setText(null);
        initialize(null, null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTypeLit.setItems(FXCollections.observableArrayList(getTypeLits()));
        cboxServices.setItems(FXCollections.observableArrayList(getServices()));
        lstCommandes.setItems(FXCollections.observableArrayList(getCommandes()));
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

    public List<TypeLit> getTypeLits(){
        Request request = new Request.Builder().url("http://localhost:9998/typeslits/gettypeslits").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<TypeLit> typeLits = null;
        try {
            response = okHttpClient.newCall(request).execute();
            typeLits = mapper.readValue(response.body().charStream(), new TypeReference<List<TypeLit>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return typeLits;
    }

    public List<Service> getServices(){
        Request request = new Request.Builder().url("http://localhost:9998/service/getservices").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Service> services = null;
        try {
            response = okHttpClient.newCall(request).execute();
            services = mapper.readValue(response.body().charStream(), new TypeReference<List<Service>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return services;
    }
}
