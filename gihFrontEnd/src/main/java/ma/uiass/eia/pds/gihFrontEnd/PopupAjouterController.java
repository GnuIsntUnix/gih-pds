package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PopupAjouterController implements Initializable {

    @FXML
    private Button btnAjouter;

    @FXML
    private ComboBox<Batiment> cboxBat;

    @FXML
    private ComboBox<Espace> cboxEsp;

    @FXML
    private ComboBox<Marque> cboxMar;

    @FXML
    private ComboBox<Service> cboxServices;

    @FXML
    private ComboBox<TypeLit> cboxTyp;


    OkHttpClient okHttpClient = new OkHttpClient();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        cboxServices.setItems(FXCollections.observableArrayList(services));

        request = new Request.Builder().url("http://localhost:9998/marque/getmarques").build();

        response = null;
        List<Marque> marques = null;
        try {
            response = okHttpClient.newCall(request).execute();
            marques = mapper.readValue(response.body().charStream(), new TypeReference<List<Marque>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cboxMar.setItems(FXCollections.observableArrayList(marques));

        request = new Request.Builder().url("http://localhost:9998/typeslits/gettypeslits").build();

        response = null;
        List<TypeLit> typeLits = null;
        try {
            response = okHttpClient.newCall(request).execute();
            typeLits = mapper.readValue(response.body().charStream(), new TypeReference<List<TypeLit>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cboxTyp.setItems(FXCollections.observableArrayList(typeLits));


        cboxBat.setDisable(true);
        cboxServices.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                cboxBat.setDisable(false);
                cboxBat.setItems(FXCollections.observableArrayList(newValue.getBatiments()));
            } else {
                cboxBat.setDisable(true);
                cboxBat.getItems().clear();
            }
        });
        cboxEsp.setDisable(true);
        cboxBat.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                cboxEsp.setDisable(false);
                cboxEsp.setItems(FXCollections.observableArrayList(newValue.getEspaces()));
            } else {
                cboxEsp.setDisable(true);
                cboxEsp.getItems().clear();
            }
        });
    }

    public void onValider(ActionEvent event) throws IOException {


        Espace espace = cboxEsp.getSelectionModel().getSelectedItem();
        TypeLit typeLit = cboxTyp.getSelectionModel().getSelectedItem();
        EtatLit etatLit = EtatLit.O;
        Marque marque = cboxMar.getSelectionModel().getSelectedItem();
        DisponibiliteLit disponibiliteLit = DisponibiliteLit.Di;

        ObjectMapper mapper = new ObjectMapper();

        Lit lit = new Lit(etatLit, disponibiliteLit, marque, typeLit, espace);
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(lit));

        Request request = new Request.Builder()
                .url("http://localhost:9998/lit/save")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        System.out.println(response.code());
    }
}

