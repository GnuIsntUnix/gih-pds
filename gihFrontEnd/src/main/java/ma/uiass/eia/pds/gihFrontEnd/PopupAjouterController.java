package ma.uiass.eia.pds.gihFrontEnd;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import ma.uiass.eia.pds.gihBackEnd.model.*;

public class PopupAjouterController {

    @FXML
    private Button btnAjouter;

    @FXML
    private ComboBox<Batiment> cboxBat;

    @FXML
    private ComboBox<Espace> cboxEsp;

    @FXML
    private ComboBox<Marque> cboxMar;

    @FXML
    private ComboBox<Service> cboxServices;

    @FXML
    private ComboBox<TypeLit> cboxTyp;

}

