package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AdmissionController implements Initializable {




    @FXML
    private TableView<Lit> tableLIts;


    @FXML
    private Button bttnAjouter;

    @FXML
    private Button bttnmodifier;
    @FXML
    private TableColumn<Lit, Espace> espaceCol;


    @FXML
    private TableColumn<Lit, Integer> idCol;

    @FXML
    private TableColumn<Lit, TypeLit> typeCol;
    @FXML
    private  TableColumn<Lit,Admission> admissionCol;

    @FXML
    private ComboBox<Batiment> cbBatiment;

    @FXML
    private ComboBox<Espace> cbEspace;






    OkHttpClient okHttpClient = new OkHttpClient();


    @FXML
    public void onBtnAjouter(ActionEvent event) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Admission admission = new Admission(LocalDate.now(), tableLIts.getSelectionModel().getSelectedItem());


        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(admission));

        System.out.println(mapper.writeValueAsString(admission));

        Request request = new Request.Builder()
                .url("http://localhost:9998/admission/save")
                .post(body)
                .build();


        Call call = okHttpClient.newCall(request);
        Response response = call.execute();


        //initialize(null, null);

        tableLIts.setItems(FXCollections.observableList(getLits(cbEspace.getValue().getIdEspace())));
    }

    @FXML
    public void onBtnLiberer(ActionEvent event) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Admission admission = tableLIts.getSelectionModel().getSelectedItem().getAdmission();
        admission.setDateFin(LocalDate.now());


        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(admission));

        System.out.println(mapper.writeValueAsString(admission));

        Request request = new Request.Builder()
                .url("http://localhost:9998/admission/update")
                .put(body)
                .build();


        Call call = okHttpClient.newCall(request);
        Response response = call.execute();


        //initialize(null, null);

        tableLIts.setItems(FXCollections.observableList(getLits(cbEspace.getValue().getIdEspace())));
    }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            cbBatiment.setItems(FXCollections.observableArrayList(getBatiments()));
            cbBatiment.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                if (newValue != null) {
                    cbEspace.setItems(FXCollections.observableList(newValue.getEspaces()));
                    cbEspace.getSelectionModel().clearSelection();
                } else {
                    cbEspace.setItems(null);
                }
            });
            cbEspace.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                if (newValue != null) {
                    tableLIts.setItems(FXCollections.observableList(getLits(newValue.getIdEspace())));
                }
            });
            idCol.setCellValueFactory(new PropertyValueFactory<>("n_lit"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("typeLit"));
            admissionCol.setCellValueFactory(table ->{
                    table.getValue().setAdmission(getAdmission(table.getValue().getN_lit()));
                return new SimpleObjectProperty<>(getAdmission(table.getValue().getN_lit()));
            });
            //admissionCol.setCellValueFactory(new PropertyValueFactory<>("admission"));
            tableLIts.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                        System.out.println("double");
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        Admission admission = tableLIts.getFocusModel().getFocusedItem().getAdmission();
                        System.out.println(tableLIts.getFocusModel().getFocusedItem());
                        try {
                            alert.setContentText("Date debut : " + admission.getDateDebut() + " Date fin : " + admission.getDateFin());
                        }
                        catch (Exception e ){
                            alert.setContentText("Date debut : " + " Date fin : ");
                        }
                        alert.showAndWait();

                    }
                }
            });


        }





    public List<Espace> getEspaces(){
        Request request = new Request.Builder().url("http://localhost:9998/espace/getespaces").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Espace> espaces = null;
        try {
            response = okHttpClient.newCall(request).execute();
            espaces = mapper.readValue(response.body().charStream(), new TypeReference<List<Espace>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return espaces;
    }
    public List<Lit> getLits(int id){
        Request request = new Request.Builder().url("http://localhost:9998/lit/getlits/byespace/"+ id).build();
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
        Request request = new Request.Builder().url("http://localhost:9998/batiment/getbatiments/byservice/"+MenuControllerChefService.getService().getIdService()).build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Batiment> batiments = null;
        try {
            response = okHttpClient.newCall(request).execute();
            batiments = mapper.readValue(response.body().charStream(), new TypeReference<List<Batiment>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return batiments;
    }

    public List<Admission> getAdmissions(){
        Request request = new Request.Builder().url("http://localhost:9998/admission/getadmissions").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Admission> admissions = null;
        try {
            response = okHttpClient.newCall(request).execute();
            admissions = mapper.readValue(response.body().charStream(), new TypeReference<List<Admission>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return admissions;
    }

    public Admission getAdmission(int idLit){
        Request request = new Request.Builder().url("http://localhost:9998/admission/getadmissiononlit/"+idLit).build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        Admission admission = null;
        try {
            response = okHttpClient.newCall(request).execute();
            admission = mapper.readValue(response.body().charStream(), new TypeReference<Admission>() {});
        } catch (Exception e) {
            return null;
        }
        return admission;
    }

}
