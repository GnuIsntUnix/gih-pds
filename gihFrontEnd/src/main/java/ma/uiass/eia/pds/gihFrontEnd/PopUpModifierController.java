package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import ma.uiass.eia.pds.gihBackEnd.model.DisponibiliteLit;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import okhttp3.Call;
import okhttp3.Request;

import java.net.URL;
import java.util.ResourceBundle;

public class PopUpModifierController implements Initializable {
    @FXML
    private ComboBox<?> cboxDisponibilite;

    @FXML
    private ComboBox<?> cboxEspace;

    @FXML
    private ComboBox<?> cboxEtat;

    @FXML
    private Label lblBatiment;

    @FXML
    private Label lblMarque;

    @FXML
    private Label lblService;

    @FXML
    private Label lblType;

    public void getLitUpdate(Lit lit){
        lblMarque.setText(lit.getMarque().toString());
        lblType.setText(lit.getTypeLit().toString());
        lblService.setText(lit.getEspace().getBatiment().getService().toString());
        lblBatiment.setText(lit.getEspace().getBatiment().toString());
        cboxDisponibilite.setPromptText(lit.getDisponibiliteLit().toString());
        cboxEspace.setPromptText(lit.getEspace().toString());
        cboxEtat.setPromptText(lit.getEtat().toString());
        Request request = new Request.Builder().url("http://localhost:9998/").build();
        Call call = okHttpClient.newCall(request);
        ObjectMapper mapper = new ObjectMapper();
        cboxDisponibilite.setItems(FXCollections.observableArrayList(DisponibiliteLit.values()));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
