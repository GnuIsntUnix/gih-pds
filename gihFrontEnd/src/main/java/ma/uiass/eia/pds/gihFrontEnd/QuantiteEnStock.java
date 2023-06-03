package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class QuantiteEnStock implements Initializable {
    @FXML
    private TableView<DM> table;
    @FXML
    private ComboBox<TypeDM> type;
    @FXML
    private TableColumn<DM,Integer> id;
    @FXML
    private TableColumn<DM,String> code;
    @FXML
    private TableColumn<DM,String> nom;
    @FXML
    private TableColumn<DM,Integer> quan;
    @FXML
    private Button consulter;
    OkHttpClient okHttpClient=new OkHttpClient();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(new PropertyValueFactory<DM,Integer>("id"));
        code.setCellValueFactory(new PropertyValueFactory<DM,String>("code"));
        nom.setCellValueFactory(new PropertyValueFactory<DM,String>("nom"));
        quan.setCellValueFactory(data -> {
            DM dm = data.getValue();
            int quantity=0;
            if (dm instanceof DMwithExemplaire){
                System.out.println(((DMwithExemplaire) dm).getExemplaireDmList().size());
                try {
                    quantity = getExemByStockDM(dm.getId()).size();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                quantity=((DMwithQuantity) dm).getQuantite();
            }

            return new SimpleIntegerProperty(quantity).asObject();
        });
        type.setItems(FXCollections.observableArrayList(getType()));

    }
    public void onClick(ActionEvent event)throws IOException{
        TypeDM typeDM=type.getValue();
        table.setItems(FXCollections.observableArrayList(getDM(typeDM.getIdType())));
        table.refresh();
    }
    public List<DM> getDM(int idTypeDM){
        Request request = new Request.Builder().url("http://localhost:9998/dm/getdms/bytype/"+idTypeDM).build();
        ObjectMapper mapper = new ObjectMapper();
        Response response = null;
        List<DM> dms = null;
        try {
            response = okHttpClient.newCall(request).execute();
            dms = mapper.readValue(response.body().charStream(), new TypeReference<List<DM>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dms;
    }
    public List<TypeDM> getType(){
        Request request = new Request.Builder().url("http://localhost:9998/typedm/gettypes").build();
        ObjectMapper mapper = new ObjectMapper();
        Response response = null;
        List<TypeDM> typeDMS = null;
        try {
            response = okHttpClient.newCall(request).execute();
            typeDMS = mapper.readValue(response.body().charStream(), new TypeReference<List<TypeDM>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return typeDMS;
    }
    public List<ExemplaireDm> getExemByStockDM(int id) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Request request = new Request.Builder()
                .url("http://localhost:9998/exemplairedm/getexbystockdm/"+id)
                .build();
        Response response = null;
        List<ExemplaireDm> list = null;
        try {
            response = okHttpClient.newCall(request).execute();
            list = mapper.readValue(response.body().charStream(), new TypeReference<List<ExemplaireDm>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
