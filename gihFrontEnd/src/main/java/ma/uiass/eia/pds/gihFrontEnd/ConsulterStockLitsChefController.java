package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.model.Stock;
import ma.uiass.eia.pds.gihBackEnd.model.TypeLit;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConsulterStockLitsChefController implements Initializable {


    private OkHttpClient okHttpClient = new OkHttpClient();


    @FXML
    private TableColumn<TypeLit, Integer> colNbr;

    @FXML
    private TableColumn<TypeLit, String> colType;

    @FXML
    private TableView<TypeLit> tblStock;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colType.setCellValueFactory(new PropertyValueFactory<>("nomTypeLit"));
        colNbr.setCellValueFactory(table ->
                new SimpleIntegerProperty(getLitsByType(table.getValue().getIdType())).asObject());
        tblStock.setItems(FXCollections.observableList(getTypesLits()));
        System.out.println(MenuControllerChefService.getService().getStock());

    }

    public Integer getLitsByType(int idType){
        Stock stock = getStock();
        Request request = new Request.Builder().url("http://localhost:9998/lit/getlits/stock/"+ stock.getIdEspace() +"/bytype/" + idType).build();
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



    private Stock getStock(){
        Request request = new Request.Builder().url("http://localhost:9998/stock/getstock/byservice/"+MenuControllerChefService.getService().getIdService()).build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        Stock stock = null;
        try {
            response = okHttpClient.newCall(request).execute();
            stock = mapper.readValue(response.body().charStream(), new TypeReference<Stock>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stock;
    }


}
