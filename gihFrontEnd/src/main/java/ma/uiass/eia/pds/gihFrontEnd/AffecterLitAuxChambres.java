package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.*;
import okhttp3.internal.tls.OkHostnameVerifier;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AffecterLitAuxChambres implements Initializable {
    @FXML
    private TableColumn<Lit, Espace> espaceCol;

    @FXML
    private TableColumn<Lit, EtatLit> etatCol;

    @FXML
    private TableColumn<Lit, Integer> idCol;

    @FXML
    private TableColumn<Lit, Marque> marqueCol;

    @FXML
    private TableView<Lit> tblLits;

    @FXML
    private TableColumn<Lit, TypeLit> typeCol;

    @FXML
    private TableColumn<Lit, DisponibiliteLit> dispCol;
    @FXML
    private Button affecter;
    @FXML
    private ComboBox<Espace> espace;
    @FXML
    private ComboBox<Batiment> bat;
    OkHttpClient okHttpClient=new OkHttpClient();
    public void onAffecter(ActionEvent event)throws IOException {
        Lit lit = tblLits.getSelectionModel().getSelectedItem();
        Espace target = espace.getValue();
        lit.setEspace(target);
        ObjectMapper mapper = new ObjectMapper();
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(lit));
        System.out.println(mapper.writeValueAsString(lit));
        Request request = new Request.Builder()
                .url("http://localhost:9998/lit/merge")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        System.out.println(mapper.writeValueAsString(lit));
        initialize(null,null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblLits.setEditable(true);
        espace.setVisible(false);
        idCol.setCellValueFactory(new PropertyValueFactory<>("n_lit"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        marqueCol.setCellValueFactory(new PropertyValueFactory<>("marque"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("typeLit"));
        espaceCol.setCellValueFactory(new PropertyValueFactory<>("espace"));
        dispCol.setCellValueFactory(new PropertyValueFactory<>("disponibiliteLit"));
        tblLits.setItems(FXCollections.observableList(getLitsbyService(MenuControllerChefService.getService().getIdService())));
        List<Espace> list=new ArrayList<>();

        bat.setItems(FXCollections.observableArrayList(getBatiments()));
        bat.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                espace.setVisible(true);
                espace.setItems(FXCollections.observableArrayList(getEspaces(newValue.getIdBatiment())));

            }
        });
    }
    public List<Lit> getLitsbyService(int id){
        Request request = new Request.Builder().url("http://localhost:9998/lit/getlits/byservice/"+id).build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Lit> lits = null;
        try {
            response = okHttpClient.newCall(request).execute();
            lits = mapper.readValue(response.body().charStream(), new TypeReference<List<Lit>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lits;
    }
    public List<Batiment> getBatiments(){
        Request request = new Request.Builder().url("http://localhost:9998/batiment/getbatiments/byservice/"+ MenuControllerChefService.getService().getIdService()).build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Batiment> batiments = null;
        try {
            response = okHttpClient.newCall(request).execute();
            batiments = mapper.readValue(response.body().charStream(), new TypeReference<List<Batiment>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(batiments);
        return batiments;
    }
    public List<Espace> getEspaces(int id){
        Request request = new Request.Builder().url("http://localhost:9998/espace/getespaces/batiment/"+id).build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Espace> espaces = null;
        try {
            response = okHttpClient.newCall(request).execute();
            espaces = mapper.readValue(response.body().charStream(), new TypeReference<List<Espace>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(espaces);
        return espaces;
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

    public List<Espace> getEspaces2(int id){
        Request request = new Request.Builder().url("http://localhost:9998/espace/getespaces/semiall/"+id).build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Espace> espaces = null;
        try {
            response = okHttpClient.newCall(request).execute();
            System.out.println("---------------------");
            System.out.println(mapper);
            System.out.println("---------------------");
            espaces = mapper.readValue(response.body().charStream(), new TypeReference<List<Espace>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(espaces);
        return espaces;
    }
}
