package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.*;
import okhttp3.internal.http2.Http2Connection;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GererEtatLitController implements Initializable {
//    @FXML
//    private Button ajouterbtn;
    @FXML
    private ComboBox<Service> serviceComboBox;
    @FXML
    private TableView<Lit> table;
    @FXML
    private TableColumn<Lit, Integer> idLit;
    @FXML
    private TableColumn<Lit,Marque> marqueTableColumn;
    @FXML
    private TableColumn<Lit, TypeLit> typeLitTableColumn;
    @FXML
    private TableColumn<Lit, EtatLit> etatLitTableColumn;
    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setEditable(true);
        serviceComboBox.setItems(FXCollections.observableArrayList(getServices()));
        idLit.setCellValueFactory(new PropertyValueFactory<Lit,Integer>("n_lit"));
        marqueTableColumn.setCellValueFactory(new PropertyValueFactory<Lit,Marque>("marque"));
        typeLitTableColumn.setCellValueFactory(new PropertyValueFactory<Lit,TypeLit>("typeLit"));
        etatLitTableColumn.setCellValueFactory(new PropertyValueFactory<Lit,EtatLit>("etat"));

        serviceComboBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue,old,newse) -> fillLit());
        fillLit();
        etatLitTableColumn.setCellFactory(column -> {
                    return new ComboBoxTableCell<>(EtatLit.N,EtatLit.G,EtatLit.O,EtatLit.P,EtatLit.D);
                }
        );
        etatLitTableColumn.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lit, EtatLit>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lit, EtatLit> event) {
                        event.getRowValue().setEtat(event.getNewValue());
                        try {
                            updateEtat(event.getRowValue());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
    }

    private void updateEtat(Lit lit) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(lit));
        System.out.println(mapper.writeValueAsString(lit));
        Request request = new Request.Builder()
                .url("http://localhost:9998/lit/merge")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

    }

    private void fillLit() {
        Service service=(Service) serviceComboBox.getValue();
        if(service!=null){
            Stock stock=findStock(service.getIdService());
            List<Lit> lits=findLits(stock.getIdEspace());
            table.setItems(FXCollections.observableArrayList(lits));
            table.getSelectionModel().selectFirst();
            table.requestLayout();
        }
    }

//    public void onChanger(ActionEvent event) throws IOException {
//        int id=table.getSelectionModel().getSelectedItem().getN_lit();
//        ObjectMapper mapper=new ObjectMapper();
//        Request request=new Request.Builder()
//                .url("http://localhost:9998/lit/changeEtatLit/"+id)
//                .build();
//        Call call = okHttpClient.newCall(request);
//        Response response = call.execute();
//       // initialize(null,null);
//        fillLit();
//    }

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
    public Stock findStock(int id){
        Request request = new Request.Builder().url("http://localhost:9998/stock/getstock/"+id).build();
        ObjectMapper mapper=new ObjectMapper();
        Response response=null;
        Stock stock=null;
        try{
            response = okHttpClient.newCall(request).execute();
            stock = mapper.readValue(response.body().charStream(), new TypeReference<Stock>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stock;
    }
    public List<Lit> findLits(int id){
        Request request = new Request.Builder().url("http://localhost:9998/lit/getlitsparstock/"+id).build();
        ObjectMapper mapper = new ObjectMapper();
        Response response = null;
        List<Lit> lits = null;
        try{
            response=okHttpClient.newCall(request).execute();
            lits = mapper.readValue(response.body().charStream(), new TypeReference<List<Lit>>() {});
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lits;
    }
}
