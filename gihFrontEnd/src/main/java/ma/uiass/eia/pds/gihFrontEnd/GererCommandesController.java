package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.Commande;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.model.TypeLit;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GererCommandesController implements Initializable {

    @FXML
    private TableView<Commande> tblCommandes;
    @FXML
    private TableColumn<Commande, Integer> idCol;

    @FXML
    private TableColumn<Commande, Integer> qteCol;

    @FXML
    private TableColumn<Commande, Service> serviceCol;

    @FXML
    private TableColumn<Commande, TypeLit> typeLitCol;

    @FXML
    private TableColumn<Commande, Boolean> valideCol;


    @FXML
    private Tab tabGererCommandes;

    @FXML
    private Tab tabStockLits;

    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblCommandes.setEditable(true);
        idCol.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        qteCol.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        serviceCol.setCellValueFactory(new PropertyValueFactory<>("service"));
        typeLitCol.setCellValueFactory(new PropertyValueFactory<>("typeLit"));
        valideCol.setCellValueFactory(new PropertyValueFactory<>("valide"));

        valideCol.setCellFactory(column -> {
            return new ComboBoxTableCell<>(true, false);
        }
        );
        valideCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Commande, Boolean>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Commande, Boolean> event) {
                        event.getRowValue().setValide(event.getNewValue());
                        try {
                            updateValide(event.getRowValue());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

//        valideCol.setCellFactory(new Callback<TableColumn<Commande, Boolean>, TableCell<Commande, Boolean>>() {
//            @Override
//            public TableCell<Commande, Boolean> call(TableColumn<Commande, Boolean> param) {
//                return new ComboBoxTableCell<>(new StringConverter<Boolean>() {
//                    @Override
//                    public String toString(Boolean object) {
//                        return (object?"Oui":"Non");
//                    }
//
//                    @Override
//                    public Boolean fromString(String string) {
//                        return string.equalsIgnoreCase("Oui");
//                    }
//                });
//            }
//        });

        tblCommandes.setItems(FXCollections.observableList(getCommandes()));


    }

    public List<Commande> getCommandes(){
        Request request = new Request.Builder().url("http://localhost:9998/commande/getcommandes").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Commande> commandes = null;
        try {
            response = okHttpClient.newCall(request).execute();
            commandes = mapper.readValue(response.body().charStream(), new TypeReference<List<Commande>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return commandes;
    }

    public void updateValide(Commande commande) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(commande));

        System.out.println(mapper.writeValueAsString(commande));

        Request request = new Request.Builder()
                .url("http://localhost:9998/commande/merge")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
    }

    @FXML
    void onStockLits(Event event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("consulterStockLits.fxml"));
        tabStockLits.setContent(fxmlLoader);
    }
}
