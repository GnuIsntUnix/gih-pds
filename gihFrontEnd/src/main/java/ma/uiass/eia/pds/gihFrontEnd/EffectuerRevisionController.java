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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

    @FXML
    void annuler(ActionEvent event) {

    }

    @FXML
    void valider(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        revisionTypeGroup = new ToggleGroup();
        typeUrgenteButton.setToggleGroup(revisionTypeGroup);
        typeSimpleButton.setToggleGroup(revisionTypeGroup);
        typeApprofondieButton.setToggleGroup(revisionTypeGroup);
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
