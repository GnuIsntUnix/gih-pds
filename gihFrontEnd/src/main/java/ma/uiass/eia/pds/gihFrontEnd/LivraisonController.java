package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ma.uiass.eia.pds.gihBackEnd.model.DM;
import ma.uiass.eia.pds.gihBackEnd.model.TypeDM;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class LivraisonController implements Initializable {

    @FXML
    private Button btnAjouter;

    @FXML
    private ComboBox<TypeDM> cboxType;

    @FXML
    private ComboBox<DM> cboxdm;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextField txtFournisseur;

    @FXML
    private TextField txtQte;

    OkHttpClient okHttpClient = new OkHttpClient();


    @FXML
    void onBtnAjouter(ActionEvent event) throws IOException {

        TypeDM typeDM = cboxType.getSelectionModel().getSelectedItem();
        DM dm = cboxdm.getSelectionModel().getSelectedItem();
        int qte = Integer.valueOf(txtQte.getText());
        LocalDate date = dpDate.getValue();
        String fournisseur = txtFournisseur.getText();

        ObjectMapper mapper = new ObjectMapper();

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(dm));

        Request request = new Request.Builder()
                .url("http://localhost:9998/livraison/save")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        initialize(null, null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cboxType.setItems(FXCollections.observableArrayList(getTypeDMS()));


    }
    public List<TypeDM> getTypeDMS(){
        Request request = new Request.Builder().url("http://localhost:9998/typedm/gettypes").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<TypeDM> typeDMS = null;
        try {
            response = okHttpClient.newCall(request).execute();
            typeDMS = mapper.readValue(response.body().charStream(), new TypeReference<List<TypeDM>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return typeDMS;
    }


}
