package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.F;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;
import ma.uiass.eia.pds.gihBackEnd.model.TypeRevision;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EffectuerRevisionController implements Initializable {


    @FXML
    private Button bttnAnnuler;

    @FXML
    private Button bttnValider;
    @FXML
    private DatePicker dateEntreePicker;

    @FXML
    private DatePicker dateSortiePicker;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private TextField kilometrageField;

    @FXML
    private TextField numeroField;

    @FXML
    private RadioButton typeApprofondieButton;

    @FXML
    private RadioButton typeSimpleButton;

    @FXML
    private RadioButton typeUrgenteButton;


    private ToggleGroup revisionTypeGroup;
    private static  Ambulance ambulance;
    OkHttpClient okHttpClient = new OkHttpClient();

    public static Ambulance getAmbulance() {
        return ambulance;
    }

    public static void setAmbulance(Ambulance ambulance) {
        EffectuerRevisionController.ambulance = ambulance;
    }

    @FXML
    void annuler(ActionEvent event) {
        numeroField.clear();
        dateEntreePicker.setValue(null);
        dateSortiePicker.setValue(null);
        descriptionArea.clear();
        kilometrageField.clear();
        typeSimpleButton.setSelected(true);
        revisionTypeGroup.selectToggle(null);

    }

    @FXML
    void valider(ActionEvent event) throws IOException {
    /*    Ambulance ambulance = EffectuerRevisionController.getAmbulance();

        if (ambulance == null || ambulance.getId() == 0) {
            showAlert("Invalid ambulance selection.");
            return;
        }*/

        LocalDate dateEntree = dateEntreePicker.getValue();
        LocalDate dateSortie = dateSortiePicker.getValue();

        String description = descriptionArea.getText();
        String kilometrage = kilometrageField.getText();

        TypeRevision typeRev = TypeRevision.SIMPLE;
        if (typeUrgenteButton.isSelected()) {
            typeRev = TypeRevision.URGENTE;
        } else if (typeApprofondieButton.isSelected()) {
            typeRev = TypeRevision.APPROFONDIE;
        }

        Revision revision = new Revision();
        revision.setAmbulance(ambulance);
        revision.setDateRevision(dateEntree);
        revision.setDateSortie(dateSortie);
        revision.setDescription(description);
        revision.setTypeRev(typeRev);
        revision.setKilometrage(kilometrage);
        revision.setState(new F());
        ObjectMapper mapper = new ObjectMapper();
        try {
            String requestBody = mapper.writeValueAsString(revision);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), requestBody);
            Request request = new Request.Builder()
                    .url("http://localhost:9998/revision/save")
                    .post(body)
                    .build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            if (response.isSuccessful()) {
                // Success
                initialize(null, null);
                annuler(null);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Révision enregistrée !");
                alert.showAndWait();
            } else {
                // Handle error response
                String errorMessage = response.body() != null ? response.body().string() : "Unknown error occurred.";
                showAlert("Failed to save the revision. ");
            }
        } catch (IOException e) {
            showAlert("An error occurred while processing the request.");
            e.printStackTrace();
        }
        System.out.println(mapper.writeValueAsString(revision));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        revisionTypeGroup = new ToggleGroup();
        typeUrgenteButton.setToggleGroup(revisionTypeGroup);
        typeSimpleButton.setToggleGroup(revisionTypeGroup);
        typeApprofondieButton.setToggleGroup(revisionTypeGroup);
        numeroField.setText(EffectuerRevisionController.ambulance.getImmatriculation());
        numeroField.setDisable(true);
        System.out.println("initialize");

        LocalDate minDate=LocalDate.now();
        dateEntreePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(minDate)); // désactiver les dates antérieures
                if (date.isBefore(minDate)) {
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }});
        dateSortiePicker.setDisable(true);
        dateEntreePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                LocalDate minDate2 = newValue; // Update minDate2 with the new value
                dateSortiePicker.setDisable(false);
                dateSortiePicker.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(date.isBefore(minDate2)); // désactiver les dates antérieures
                        if (date.isBefore(minDate2)) {
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                });
            }
        });
//        kilometrageField.textProperty().addListener((observable, oldValue, newValue) -> {
//            if (!newValue.matches("\\d*")) {
//                kilometrageField.setText(newValue.replaceAll("[^\\d]", ""));
//                showAlert("Veuillez saisir uniquement des chiffres");
//            }
//            if(Integer.parseInt(newValue)<1){
//                showAlert("Veuillez saisir un nombre positif");
//            }
//        });


    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public String getSelectedRevisionType() {
        RadioButton selectedButton = (RadioButton) revisionTypeGroup.getSelectedToggle();
        if (selectedButton != null) {
            return selectedButton.getText();
        } else {
            return null;
        }
    }


}
