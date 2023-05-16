package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;
import ma.uiass.eia.pds.gihBackEnd.model.TypeRevision;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceAmbulance;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class EffectuerRevisionController implements Initializable {
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
        ObjectMapper mapper=new ObjectMapper();
        RequestBody body= RequestBody.create(MediaType.parse("application/json"), mapper.writeValueAsString(revision));
        Request request = new Request.Builder()
                .url("http://localhost:9998/revision/save")
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        initialize(null,null);
        annuler(null);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Révision enregistrée !");
        alert.showAndWait();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        revisionTypeGroup = new ToggleGroup();
        typeUrgenteButton.setToggleGroup(revisionTypeGroup);
        typeSimpleButton.setToggleGroup(revisionTypeGroup);
        typeApprofondieButton.setToggleGroup(revisionTypeGroup);
        numeroField.setText(EffectuerRevisionController.ambulance.getImmatriculation());
        System.out.println("initialize");

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
