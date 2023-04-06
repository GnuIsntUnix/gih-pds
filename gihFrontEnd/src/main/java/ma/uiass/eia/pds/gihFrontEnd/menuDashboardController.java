package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ma.uiass.eia.pds.gihBackEnd.model.DisponibiliteLit;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class menuDashboardController implements Initializable {
    @FXML
    private BorderPane borderPane;

    OkHttpClient okHttpClient = new OkHttpClient();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int numColumns = 4; // define the number of columns
        List<Service> services = getServices();
        int numItems = services.size(); // define the number of items

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // set the horizontal gap between columns
        gridPane.setVgap(10); // set the vertical gap between rows
        System.out.println(services);
        for (int i = services.get(0).getIdService(); i < services.get(0).getIdService() + numItems; i++) {
            System.out.println(services.get(i - services.get(0).getIdService()));
            VBox vBox = new VBox();
            Label lblService = new Label(services.get(i - services.get(0).getIdService()).getNomService());
            Label lblDispo = new Label(String.valueOf(getDisp(services.get(i - services.get(0).getIdService()).getIdService()).size()));
            Label lblOccup = new Label(String.valueOf(getOccup(services.get(i - services.get(0).getIdService()).getIdService()).size()));
            vBox.getChildren().addAll(lblService, lblDispo, lblOccup);
            // calculate the column and row indices of the cell
            int colIndex = i % numColumns;
            int rowIndex = i / numColumns;

            // add node to the cell
            GridPane.setConstraints(vBox, colIndex, rowIndex);
            gridPane.getChildren().add(vBox);
        }
        borderPane.setCenter(gridPane);
    }
    public List<Service> getServices(){
        Request request = new Request.Builder().url("http://localhost:9998/service/getservices").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Service> services = null;
        try {
            response = okHttpClient.newCall(request).execute();
            services = mapper.readValue(response.body().charStream(), new TypeReference<List<Service>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return services;
    }
    public List<Lit> getLitByService(int id){
        Request request = new Request.Builder().url("http://localhost:9998/lit/getlits/byservice/"+id).build();
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

    public List<Lit> getDisp(int id){
        List<Lit> lits = getLitByService(id);
        List<Lit> dispo = new ArrayList<>();
        lits.forEach(lit -> {
            if (lit.getDisponibiliteLit() == DisponibiliteLit.Di){
                dispo.add(lit);
            }
        });
        return dispo;
    }public List<Lit> getOccup(int id){
        List<Lit> lits = getLitByService(id);
        List<Lit> occup = new ArrayList<>();
        lits.forEach(lit -> {
            if (lit.getDisponibiliteLit() == DisponibiliteLit.O){
                occup.add(lit);
            }
        });
        return occup;
    }
}
