package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import ma.uiass.eia.pds.gihFrontEnd.Serializers.DMJsonSerializer;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    @FXML
    private TableView<DM> tblDms;

    @FXML
    private TextField txtExemp;

    @FXML
    private TableColumn<DM, String> codeCol;

    @FXML
    private TableColumn<DM, Integer> exempCol;

    @FXML
    private TableColumn<DM, Integer> idCol;

    @FXML
    private TableColumn<DM, String> nomCol;

    OkHttpClient okHttpClient = new OkHttpClient();

    @FXML
    void onBtnAjouter(ActionEvent event) throws IOException {
        TypeDM typeDM = cboxType.getSelectionModel().getSelectedItem();
        String code = txtCode.getText();
        String nom = txtNom.getText();
        Stock stock = getStock(cboxService.getSelectionModel().getSelectedItem().getIdService());
        int nbr = Integer.parseInt(txtExemp.getText());
        System.out.println(stock);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(DM.class, new DMJsonSerializer());
        mapper.registerModule(simpleModule);

        DM dm = new DM(code, nom, typeDM);

        List<ExemplaireDm> exemplaireDms = new ArrayList<>();

        for (int i = 0; i<nbr; i++){
            exemplaireDms.add(new ExemplaireDm(dm, stock));
        }

        dm.setExemplaireDmList(exemplaireDms);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(dm));

        System.out.println(mapper.writeValueAsString(dm));

        Request request = new Request.Builder()
                .url("http://localhost:9998/dm/save/persist")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        txtExemp.setText(null);
        txtCode.setText(null);
        txtNom.setText(null);
        initialize(null, null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cboxType.setItems(FXCollections.observableArrayList(getTypeDMS()));
        cboxService.setItems(FXCollections.observableArrayList(getServices()));

        Request request = new Request.Builder().url("http://localhost:9998/dm/getdms").build();
        Call call = okHttpClient.newCall(request);
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<DM> dms = null;
        try {
            response = okHttpClient.newCall(request).execute();
            dms = mapper.readValue(response.body().charStream(), new TypeReference<List<DM>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        exempCol.setCellValueFactory(table ->
                new SimpleIntegerProperty(getExemplaires(table.getValue())).asObject());

        tblDms.setItems(FXCollections.observableList(dms));
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

    public int getExemplaires(DM dm){
        Request request = new Request.Builder().url("http://localhost:9998/exemplairedm/getexemplaires/" + dm.getId()).build();
        Call call = okHttpClient.newCall(request);
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<ExemplaireDm> dms = null;
        try {
            response = okHttpClient.newCall(request).execute();
            dms = mapper.readValue(response.body().charStream(), new TypeReference<List<ExemplaireDm>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dms.size();

    }

    public Stock getStock(int idService){
        Request request = new Request.Builder().url("http://localhost:9998/stock/getstock/"+idService).build();
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