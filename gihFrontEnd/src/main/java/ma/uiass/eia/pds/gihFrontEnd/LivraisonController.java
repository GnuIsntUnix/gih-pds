package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LivraisonController implements Initializable {

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnValider;


    @FXML
    private ComboBox<DM> cboxdm;

    @FXML
    private DatePicker dpDate;

    @FXML
    private ComboBox<Fournisseur> cboxFournisseur;

    @FXML
    private TextField txtQte;

    @FXML
    private ListView<DetailLivraison> lstDetails;

    OkHttpClient okHttpClient = new OkHttpClient();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cboxdm.setItems(FXCollections.observableArrayList(getDM()));
        cboxFournisseur.setItems(FXCollections.observableArrayList(getFournisseur()));


    }

    public List<DM> getDM(){
        Request request = new Request.Builder().url("http://localhost:9998/dm/getdms").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<DM> DMS = null;
        try {
            response = okHttpClient.newCall(request).execute();
            DMS = mapper.readValue(response.body().charStream(), new TypeReference<List<DM>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return DMS;
    }

    public List<Fournisseur> getFournisseur(){
        Request request = new Request.Builder().url("http://localhost:9998/fournisseur/getfournisseurs").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Fournisseur> fournisseurs = null;
        try {
            response = okHttpClient.newCall(request).execute();
            fournisseurs = mapper.readValue(response.body().charStream(), new TypeReference<List<Fournisseur>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fournisseurs;
    }
    @FXML
    void onBtnAjouter(ActionEvent event) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        int qte = Integer.parseInt(txtQte.getText());
        DM dm = cboxdm.getSelectionModel().getSelectedItem();
        Fournisseur fournisseur = cboxFournisseur.getSelectionModel().getSelectedItem();
        Stock stock = getStock();

        DetailLivraison detailLivraison = new DetailLivraison(dm, qte,fournisseur);
        lstDetails.getItems().add(detailLivraison);

        List<ExemplaireDm> exemplaireDms = new ArrayList<>();

        for (int i = 0; i<qte; i++){
            exemplaireDms.add(new ExemplaireDm(dm, stock));
        }

        dm.setExemplaireDmList(exemplaireDms);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(dm));

        System.out.println(mapper.writeValueAsString(dm));

        Request request = new Request.Builder()
                .url("http://localhost:9998/dm/save/merge")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();



    }


    @FXML
    void onBtnValider(ActionEvent event) throws IOException {

        LocalDate date = dpDate.getValue();
        Stock stock = getStock();

        ObjectMapper mapper = new ObjectMapper();

        List<DetailLivraison> list = lstDetails.getItems().stream().toList();
        Livraison livraison = new Livraison();
        livraison.setDate(date);

        for (DetailLivraison d : list){
            d.setLivraison(livraison);

        }
        livraison.setDetailLivraisonList(list);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(livraison));

        System.out.println(mapper.writeValueAsString(livraison));

        Request request = new Request.Builder()
                .url("http://localhost:9998/livraison/save")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        response.close();
        initialize(null, null);
    }

    public Stock getStock(){
        Request request = new Request.Builder().url("http://localhost:9998/stock/getstock/1").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        Stock stock = null;
        try {
            response = okHttpClient.newCall(request).execute();
            stock = mapper.readValue(response.body().charStream(), new TypeReference<Stock>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stock;
    }



}
