package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VisualisationAmbulancesController implements Initializable {
    @FXML
    private BarChart<String, Number> barChart;

    private OkHttpClient okHttpClient = new OkHttpClient();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        XYChart.Series<String, Number> seriesF = new XYChart.Series<>();
        seriesF.setName("Fonctionnel");
        seriesF.getData().add(new XYChart.Data<>("Fonctionnel", getAmbulances(1).size()));

        XYChart.Series<String, Number> seriesNFCD = new XYChart.Series<>();
        seriesNFCD.setName("Non fonctionnel CD");
        seriesNFCD.getData().add(new XYChart.Data<>("Non fonctionnel CD", getAmbulances(2).size()));

        XYChart.Series<String, Number> seriesNFLD = new XYChart.Series<>();
        seriesNFLD.setName("Non fonctionnel LD");
        seriesNFLD.getData().add(new XYChart.Data<>("Non fonctionnel LD", getAmbulances(3).size()));

        barChart.getData().addAll(seriesF, seriesNFCD, seriesNFLD);
    }

    public List<Ambulance> getAmbulances(int id){
        Request request = new Request.Builder().url("http://localhost:9998/ambulance/getambulances/bystate/" + id).build();
        Call call = okHttpClient.newCall(request);
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Ambulance> ambulances = null;
        try {
            response = okHttpClient.newCall(request).execute();
            ambulances = mapper.readValue(response.body().charStream(), new TypeReference<List<Ambulance>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ambulances;
    }
}
