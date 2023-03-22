package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import ma.uiass.eia.pds.gihBackEnd.model.*;

import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PopUpModifierController implements Initializable {
    @FXML
    private ComboBox<DisponibiliteLit> cboxDisponibilite;

    @FXML
    private ComboBox<Espace> cboxEspace;

    @FXML
    private ComboBox<EtatLit> cboxEtat;

    @FXML
    private Label lblBatiment;

    @FXML
    private Label lblMarque;

    @FXML
    private Label lblService;

    @FXML
    private Label lblType;

    @FXML
    private Button btnValider;

    OkHttpClient okHttpClient = new OkHttpClient();

    private static Lit lit = null;
    public void getLitUpdate(Lit lit){
        lblMarque.setText(lit.getMarque().toString());
        lblType.setText(lit.getTypeLit().toString());
        lblService.setText(lit.getEspace().getBatiment().getService().toString());
        lblBatiment.setText(lit.getEspace().getBatiment().toString());
        cboxDisponibilite.setValue(lit.getDisponibiliteLit());
        cboxEspace.setValue(lit.getEspace());
        cboxEtat.setValue(lit.getEtat());
        Request request = new Request.Builder().url("http://localhost:9998/").build();
        Call call = okHttpClient.newCall(request);
        ObjectMapper mapper = new ObjectMapper();
        cboxDisponibilite.setItems(FXCollections.observableArrayList(DisponibiliteLit.values()));
        cboxEtat.setItems(FXCollections.observableArrayList(EtatLit.values()));
        cboxEspace.setItems(FXCollections.observableArrayList(lit.getEspace().getBatiment().getEspaces()));
        PopUpModifierController.lit = lit;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onValider(ActionEvent actionEvent) throws IOException {
        lit.setDisponibiliteLit(cboxDisponibilite.getValue());
        lit.setEtat(cboxEtat.getValue());
        lit.setEspace(cboxEspace.getValue());

        ObjectMapper mapper = new ObjectMapper();

        RequestBody body = RequestBody.create(
                mapper.writeValueAsString(lit), MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url("http://localhost:9998/lit/update/"+lit.getN_lit())
                .put(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
    }

}
