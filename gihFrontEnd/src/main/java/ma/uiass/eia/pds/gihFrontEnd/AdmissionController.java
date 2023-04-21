package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<Admission, LocalDate> dateDebutColumn;

    @FXML
    private TableColumn<Admission, LocalDate> dateFinColumn;

    @FXML
    private TableColumn<Admission, Integer> idAdmission;

    @FXML
    private TableColumn<Admission, Lit> litColumn;

    @FXML
    private Button bttnAjouter;

    @FXML
    private Button bttnmodifier;

    @FXML
    private ComboBox<Batiment> cbBatiment;

    @FXML
    private ComboBox<Espace> cbEspace;

    @FXML
    private ComboBox<Lit> cbLit;

    @FXML
    private TableView<Admission> lstAdmissions;
    OkHttpClient okHttpClient = new OkHttpClient();


    @FXML
    public void onBtnAjouter(ActionEvent event) throws IOException {
        ObjectMapper mapper = new ObjectMapper();



        Admission admission = new Admission(LocalDate.now(), cbLit.getValue());
        System.out.println(admission);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(admission));

        Request request = new Request.Builder()
                .url("http://localhost:9998/admission/save")
                .post(body)
                .build();


        Call call = okHttpClient.newCall(request);
        Response response = call.execute();


        initialize(null, null);
    }


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            cbBatiment.setItems(FXCollections.observableArrayList(getBatiments()));
            cbBatiment.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                if (newValue != null) {
                    cbEspace.setItems(FXCollections.observableList(newValue.getEspaces()));
                    cbEspace.getSelectionModel().clearSelection();
                    cbLit.getSelectionModel().clearSelection();
                } else {
                    cbEspace.setItems(null);
                    cbLit.setItems(null);
                }
            });
            cbEspace.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                if (newValue != null) {
                    cbLit.setItems(FXCollections.observableArrayList(newValue.getLits()));
                    cbLit.getSelectionModel().clearSelection();
                } else {
                    cbLit.setItems(null);
                }
            });
            dateDebutColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDateDebut()));
            litColumn.setCellValueFactory(new PropertyValueFactory<>("lit"));
            idAdmission.setCellValueFactory(new PropertyValueFactory<>("idAdmission"));
            dateFinColumn.setCellValueFactory(new PropertyValueFactory<>(null));
            lstAdmissions.setItems(FXCollections.observableList(getAdmissions()));


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
    public List<Lit> getLits(){
        Request request = new Request.Builder().url("http://localhost:9998/lit/getlits").build();
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
        Request request = new Request.Builder().url("http://localhost:9998/batiment/getbatiments").build();
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

}
