package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ma.uiass.eia.pds.gihBackEnd.model.Fournisseur;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import okhttp3.*;

import java.io.IOException;

public class AjouterFournisseurController {
    @FXML
    private Button btnAjouter;

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtTel;

    OkHttpClient okHttpClient = new OkHttpClient();


    @FXML
    void onBtnAjouter(ActionEvent event) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String nomF = txtNom.getText();
        String emailF = txtEmail.getText();
        String adressF = txtAdress.getText();
        String telF = txtTel.getText();


        Fournisseur fournisseur = new Fournisseur(nomF, emailF, adressF, telF);
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(fournisseur));

        Request request = new Request.Builder()
                .url("http://localhost:9998/fournisseur/save")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        txtNom.setText(null);
        txtEmail.setText(null);
        txtAdress.setText(null);
        txtTel.setText(null);



    }
}
