package ma.uiass.eia.pds.gihFrontEnd;

import javafx.fxml.Initializable;
import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;

import java.net.URL;
import java.util.ResourceBundle;

public class ConsulterRevisionsController implements Initializable {

    private static Ambulance ambulance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    public static Ambulance getAmbulance() {
        return ambulance;
    }

    public static void setAmbulance(Ambulance ambulance) {
        ConsulterRevisionsController.ambulance = ambulance;
    }

}
