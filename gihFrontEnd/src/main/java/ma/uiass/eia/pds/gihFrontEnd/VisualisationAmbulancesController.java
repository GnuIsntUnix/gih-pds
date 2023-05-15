package ma.uiass.eia.pds.gihFrontEnd;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class VisualisationAmbulancesController implements Initializable {
    @FXML
    private BarChart<String, Number> barChart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series<String, Number> seriesF = new XYChart.Series<>();
        seriesF.setName("Fonctionnel");
        seriesF.getData().add(new XYChart.Data<>("Fonctionnel", 23));

        XYChart.Series<String, Number> seriesNFCD = new XYChart.Series<>();
        seriesNFCD.setName("Non fonctionnel CD");
        seriesNFCD.getData().add(new XYChart.Data<>("Non fonctionnel CD", 23));

        XYChart.Series<String, Number> seriesNFLD = new XYChart.Series<>();
        seriesNFLD.setName("Fonctionnel");
        seriesNFLD.getData().add(new XYChart.Data<>("Non fonctionnel LD", 23));


        barChart.getData().addAll(seriesF, seriesNFCD, seriesNFLD);
    }
}
