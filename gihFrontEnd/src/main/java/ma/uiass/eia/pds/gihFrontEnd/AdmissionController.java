package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.LineTo;
import javafx.stage.Stage;
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
    private Button bttnHistorique;
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
        Lit selectedLit = tableLIts.getSelectionModel().getSelectedItem();


        // Validate dateDebut
        LocalDate dateDebut = LocalDate.now();
        if (selectedLit == null) {
            showAlert("Veuillez sélectionner un Lit.");
            return;
        }

        LocalDate dateFin = null; // Set the initial value to null
        if (dateFin != null && dateFin.isBefore(dateDebut)) {
            showAlert("La date de fin doit être postérieure à la date de début.");
            return;
        }

        Admission admission = new Admission(dateDebut, selectedLit);
        admission.setDateFin(dateFin);


        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(admission));

        System.out.println(mapper.writeValueAsString(admission));

        Request request = new Request.Builder()
                .url("http://localhost:9998/admission/save")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        tableLIts.setItems(FXCollections.observableList(getLits(cbEspace.getValue().getIdEspace())));
    }

    @FXML
    public void onBtnLiberer(ActionEvent event) throws IOException {
        Lit selectedLit = tableLIts.getSelectionModel().getSelectedItem();
        Admission admission = selectedLit.getAdmission();

        if (admission == null) {
            showAlert("Aucune admission n'est associée à ce Lit.");
            return;
        }

        LocalDate dateFin = LocalDate.now();
        if (admission.getDateFin() != null) {
            showAlert("Ce Lit est déjà libéré.");
            return;
        }


        admission.setDateFin(dateFin);

        // Update the admission
        ObjectMapper mapper = new ObjectMapper();
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(admission));

        Request request = new Request.Builder()
                .url("http://localhost:9998/admission/update")
                .put(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

        selectedLit.setAdmission(null);
        tableLIts.refresh();
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    public void onBtnHistorique(ActionEvent event) throws IOException {
        Lit selectedLit = tableLIts.getSelectionModel().getSelectedItem();

        if (selectedLit == null) {
            showAlert("Veuillez sélectionner un Lit.");
            return;
        }

        ConsulterAdmission.setLit(selectedLit);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ConsulterAdmission.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
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
                            alert.setContentText("Date debut : is not set" + " Date fin : is not set");
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
