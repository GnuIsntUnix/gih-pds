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
import javafx.scene.control.TextField;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DMController implements Initializable {

    @FXML
    private Button btnAjouter;

    @FXML
    private ComboBox<TypeDM> cboxType;

    @FXML
    private ComboBox<Service> cboxService;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtNom;

    OkHttpClient okHttpClient = new OkHttpClient();

    @FXML
    void onBtnAjouter(ActionEvent event) throws IOException {
        TypeDM typeDM = cboxType.getSelectionModel().getSelectedItem();
        String code = txtCode.getText();
        String nom = txtNom.getText();
        Stock stock = cboxService.getSelectionModel().getSelectedItem().getStock();

        ObjectMapper mapper = new ObjectMapper();

        DM dm = new DM(code, nom, stock, typeDM);
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(dm));

        Request request = new Request.Builder()
                .url("http://localhost:9998/dm/save")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        initialize(null, null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cboxType.setItems(FXCollections.observableArrayList(getTypeDMS()));
        cboxService.setItems(FXCollections.observableArrayList(getServices()));
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