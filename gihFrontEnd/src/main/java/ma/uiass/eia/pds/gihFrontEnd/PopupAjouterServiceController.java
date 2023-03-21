package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

public class PopupAjouterServiceController {

    @FXML
    private Button btnValider;

    @FXML
    private TextField txtfCode;

    @FXML
    private TextField txtfNom;

    OkHttpClient okHttpClient = new OkHttpClient();
    public void onValider(ActionEvent event) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        String nomService = txtfNom.getText();
        String codeService = txtfCode.getText();

        Service service = new Service(codeService, nomService);
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(service));

        Request request = new Request.Builder()
                .url("http://localhost:9998/service/save")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        txtfCode.setText(null);
        txtfNom.setText(null);
    }


}

