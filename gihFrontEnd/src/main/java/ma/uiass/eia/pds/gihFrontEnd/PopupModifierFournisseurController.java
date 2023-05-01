package ma.uiass.eia.pds.gihFrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ma.uiass.eia.pds.gihBackEnd.model.Fournisseur;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupModifierFournisseurController implements Initializable {


    private Fournisseur fournisseur;

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    @FXML
    private Button btnAjouter;

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtTel;

    @FXML
    void onBtnAjouter(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
