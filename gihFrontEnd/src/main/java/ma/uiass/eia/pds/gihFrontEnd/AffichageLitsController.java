package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AffichageLitsController implements Initializable {

    private static Service service = MenuControllerChefService.getService();

    @FXML
    private TableColumn<Lit, Espace> espaceCol;
    @FXML
    private ComboBox<Batiment> cbBatiment;

    @FXML
    private TableColumn<Lit, EtatLit> etatCol;

    @FXML
    private TableColumn<Lit, Integer> idCol;

    @FXML
    private TableColumn<Lit, Marque> marqueCol;

    @FXML
    private TableView<Lit> tblLits;

    @FXML
    private TableColumn<Lit, TypeLit> typeCol;

    @FXML
    private TableColumn<Lit, DisponibiliteLit> dispCol;

    @FXML
    private Tab tabAdmissions;

    @FXML
    private Tab tabCommandes;

    @FXML
    private Tab tabLits;

    @FXML
    private Tab tabStock;

    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tblLits.setEditable(true);

        idCol.setCellValueFactory(new PropertyValueFactory<>("n_lit"));
        etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        marqueCol.setCellValueFactory(new PropertyValueFactory<>("marque"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("typeLit"));
        espaceCol.setCellValueFactory(new PropertyValueFactory<>("espace"));
        dispCol.setCellValueFactory(new PropertyValueFactory<>("disponibiliteLit"));

        cbBatiment.setItems(FXCollections.observableArrayList(getBatiments()));

        cbBatiment.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                tblLits.setItems(FXCollections.observableList(getLitsbyBatiment(newValue.getIdBatiment())));
            }
        });

        dispCol.setCellFactory(column -> {
                    return new ComboBoxTableCell<>(DisponibiliteLit.Di, DisponibiliteLit.O);
                }
        );
        dispCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Lit, DisponibiliteLit>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Lit, DisponibiliteLit> event) {
                        event.getRowValue().setDisponibiliteLit(event.getNewValue());
                        try {
                            updateLit(event.getRowValue());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    private void updateLit(Lit rowValue) throws IOException {
                        ObjectMapper mapper = new ObjectMapper();

                        RequestBody body = RequestBody.create(
                                MediaType.parse("application/json"), mapper.writeValueAsString(rowValue));

                        System.out.println(mapper.writeValueAsString(rowValue));

                        Request request = new Request.Builder()
                                .url("http://localhost:9998/lit/update")
                                .put(body)
                                .build();

                        Call call = okHttpClient.newCall(request);
                        Response response = call.execute();
                    }
                }
        );



    }

    public void ajouterPopup(ActionEvent actionEvent) throws IOException {

        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("popupAjouter.fxml"));
        Scene scene = new Scene(fxmlLoader);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                initialize(null, null);
                stage.close();
            }
        });
        stage.showAndWait();

    }

    public List<Lit> getLitsbyBatiment(int id){
        Request request = new Request.Builder().url("http://localhost:9998/lit/getlits/bybatiment/"+id).build();
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

    public List<Batiment> getBatiments(){
        Request request = new Request.Builder().url("http://localhost:9998/batiment/getbatiments/byservice/"+service.getIdService()).build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Batiment> batiments = null;
        try {
            response = okHttpClient.newCall(request).execute();
            batiments = mapper.readValue(response.body().charStream(), new TypeReference<List<Batiment>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return batiments;
    }

    @FXML
    void onStockLits(Event event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("consulterStockLitsChef.fxml"));
        tabStock.setContent(fxmlLoader);
    }

    @FXML
    void onAdmissions(Event event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("admissionScene.fxml"));
        tabAdmissions.setContent(fxmlLoader);
    }

    @FXML
    void onCommandes(Event event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("commandes.fxml"));
        tabCommandes.setContent(fxmlLoader);
    }
}
