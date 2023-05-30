package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import ma.uiass.eia.pds.gihFrontEnd.Serializers.DMJsonSerializer;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DMController implements Initializable {

    @FXML
    private Button btnAjouter;

    @FXML
    private ComboBox<TypeDM> cboxType;


    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtNom;

    @FXML
    private TableView<DM> tblDms;


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

        ObjectMapper mapper = new ObjectMapper();
       /* SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(DM.class, new DMJsonSerializer());
        mapper.registerModule(simpleModule);*/
        DM dm =null;
        if(typeDM.getNomType().equalsIgnoreCase("dm connectés")||typeDM.getNomType().equalsIgnoreCase("Equipement Leger")
                ||typeDM.getNomType().equalsIgnoreCase("dm lourd")|| typeDM.getNomType().equalsIgnoreCase("Mobilier") ) {
            dm=new DMwithExemplaire(code,nom,typeDM);

        }else{
            dm = new DMwithQuantity(1, code, nom, typeDM, getStock(1));
        }



        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(dm));

        System.out.println(mapper.writeValueAsString(dm));

        Request request = new Request.Builder()
                .url("http://localhost:9998/dm/save/persist")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        txtCode.setText(null);
        txtNom.setText(null);
        initialize(null, null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cboxType.setItems(FXCollections.observableArrayList(getTypeDMS()));

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

        tblDms.setItems(FXCollections.observableList(dms));

        cboxType.getItems().add(new TypeDM("Ajouter un nouveau type..."));
        cboxType.setOnAction(e ->{
            String typeDM = "";
            try {
                typeDM = cboxType.getSelectionModel().getSelectedItem().toString();
            }
            catch (Exception ignore){

            }
            System.out.println(typeDM);
            String finalTypeDM = typeDM;
            Platform.runLater(() -> {
                if ("Ajouter un nouveau type...".equals(Objects.requireNonNull(finalTypeDM))) {
                    System.out.println("Nice");
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setOnCloseRequest(ev -> initialize(null, null));
                    dialog.setContentText("Inserer un nom de type");
                    dialog.showAndWait().ifPresent(text -> {
                        TypeDM typeDM1 = new TypeDM(text);
                        try {
                            saveTypeDM(typeDM1);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                }
            });

        });
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
        Request request = new Request.Builder().url("http://localhost:9998/stock/getstock/byservice/"+idService).build();
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

    public void saveTypeDM(TypeDM typeDM) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(typeDM));

        System.out.println(mapper.writeValueAsString(typeDM));

        Request request = new Request.Builder()
                .url("http://localhost:9998/typedm/save")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        initialize(null, null);
    }

}