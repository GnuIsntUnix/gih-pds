package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import ma.uiass.eia.pds.gihBackEnd.model.DisponibiliteLit;

import ma.uiass.eia.pds.gihBackEnd.model.Espace;
import ma.uiass.eia.pds.gihBackEnd.model.EtatLit;

import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.net.URL;
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

    OkHttpClient okHttpClient = new OkHttpClient();
    public void getLitUpdate(Lit lit){
        lblMarque.setText(lit.getMarque().toString());
        lblType.setText(lit.getTypeLit().toString());
        lblService.setText(lit.getEspace().getBatiment().getService().toString());
        lblBatiment.setText(lit.getEspace().getBatiment().toString());
        cboxDisponibilite.setPromptText(lit.getDisponibiliteLit().toString());
        cboxEspace.setPromptText(lit.getEspace().getBatiment().getEspaces().toString());
        cboxEtat.setPromptText(lit.getEtat().toString());
        Request request = new Request.Builder().url("http://localhost:9998/").build();
        Call call = okHttpClient.newCall(request);
        ObjectMapper mapper = new ObjectMapper();
        cboxDisponibilite.setItems(FXCollections.observableArrayList(DisponibiliteLit.values()));
        cboxEtat.setItems(FXCollections.observableArrayList(EtatLit.values()));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
