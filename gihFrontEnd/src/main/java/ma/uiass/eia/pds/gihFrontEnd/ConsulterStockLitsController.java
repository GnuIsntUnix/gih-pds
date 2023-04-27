package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterStockLitsController implements Initializable {

    @FXML
    private TableColumn<TypeLit, Integer> colNbr;

    @FXML
    private TableColumn<TypeLit, String> colType;

    @FXML
    private TableView<TypeLit> tblStock;

    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colType.setCellValueFactory(new PropertyValueFactory<>("nomTypeLit"));
        colNbr.setCellValueFactory(table ->
                new SimpleIntegerProperty(getLitsByType(table.getValue().getIdType())).asObject());
        tblStock.setItems(FXCollections.observableList(getTypesLits()));

    }

    public Integer getLitsByType(int idType){
        Request request = new Request.Builder().url("http://localhost:9998/lit/getlits/stock/1/bytype/" + idType).build();
        Call call = okHttpClient.newCall(request);
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Lit> lits = null;
        try {
            response = okHttpClient.newCall(request).execute();
            lits = mapper.readValue(response.body().charStream(), new TypeReference<List<Lit>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lits.size();
    }

    public List<TypeLit> getTypesLits(){
        Request request = new Request.Builder().url("http://localhost:9998/typeslits/gettypeslits").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<TypeLit> types = null;
        try {
            response = okHttpClient.newCall(request).execute();
            types = mapper.readValue(response.body().charStream(), new TypeReference<List<TypeLit>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return types;
    }


}
