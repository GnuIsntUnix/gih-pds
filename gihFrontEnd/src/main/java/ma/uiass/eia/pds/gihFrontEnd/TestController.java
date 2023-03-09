package ma.uiass.eia.pds.gihFrontEnd;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import ma.uiass.eia.pds.gihBackEnd.dto.LitDto;
import ma.uiass.eia.pds.gihBackEnd.model.Chambre;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.util.Instances;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TestController implements Initializable {

    @FXML
    private Accordion accordion;
    @FXML
    private TreeView<Instances> tView;

    @FXML
    private ListView<Lit> lstLits;

    @FXML
    private TableColumn<LitDto, Integer> clChambre;

    @FXML
    private TableColumn<LitDto, Integer> clCode;

    @FXML
    private TableColumn<LitDto, Integer> clEtage;

    @FXML
    private TableColumn<LitDto, String> clEtat;

    @FXML
    private TableColumn<LitDto, String> clMarque;

    @FXML
    private TableColumn<LitDto, String> clBatiment;

    @FXML
    private TableColumn<LitDto, String> clType;

    @FXML
    private TableView<LitDto> tblLits;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        clChambre.setCellValueFactory(new PropertyValueFactory<LitDto, Integer>("chambre"));
//        clCode.setCellValueFactory(new PropertyValueFactory<LitDto, Integer>("id"));
//        clEtage.setCellValueFactory(new PropertyValueFactory<LitDto, Integer>("etage"));
//        clType.setCellValueFactory(new PropertyValueFactory<LitDto, String>("type"));
//        clEtat.setCellValueFactory(new PropertyValueFactory<LitDto, String>("etat"));
//        clMarque.setCellValueFactory(new PropertyValueFactory<LitDto, String>("marque"));
//        clBatiment.setCellValueFactory(new PropertyValueFactory<LitDto, String>("batiment"));



        OkHttpClient okHttpClient = new OkHttpClient();
        Gson gson = new Gson();
        Request request = new Request.Builder().url("http://localhost:9998/service/getservices").build();
        String jsonString;
        try {
            jsonString = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Service> services = List.of(gson.fromJson(jsonString, Service[].class));
        TreeItem<Instances> rootItem = new TreeItem<>(new Instances());
        tView.setRoot(rootItem);
        services.forEach(service -> {
            TreeItem<Instances> serviceLeaf = new TreeItem<>(service);
            rootItem.getChildren().add(serviceLeaf);
            service.getBatiments().forEach(batiment -> {
                TreeItem<Instances> batimentLeaf = new TreeItem<>(batiment);
                serviceLeaf.getChildren().add(batimentLeaf);
                batiment.getChambres().forEach(chambre -> {
                    TreeItem<Instances> chambreLeaf = new TreeItem<>(chambre);
                    batimentLeaf.getChildren().add(chambreLeaf);
                });
            });
        });
        tView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (tView.getSelectionModel().getSelectedItem() != null && tView.getSelectionModel().getSelectedItem().getValue() instanceof Chambre){
                    lstLits.setItems((FXCollections.observableList(((Chambre) tView.getSelectionModel().getSelectedItem().getValue()).getLits())));
                }
            }
        });
//
//        tView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<Instances>>() {
//            @Override
//            public void changed(ObservableValue<? extends TreeItem<Instances>> observableValue, TreeItem<Instances> instancesTreeItem, TreeItem<Instances> t1) {
//                if (instancesTreeItem != null && t1.getValue() instanceof Chambre)
//                    lstLits.getItems().setAll((FXCollections.observableList(((Chambre) t1.getValue()).getLits())));
//            }
//        });


//
//        try {
//            services = Arrays.asList(gson.fromJson(call.execute().body().string(),Service[].class));
//            System.out.println(services);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        TreeItem<String> rootItem = new TreeItem<>("Services");
//        tView.setRoot(rootItem);
//        for (Service s: services){
//            TreeItem<String> serviceLeaf = new TreeItem<>(s.getNomService());
//            rootItem.getChildren().add(serviceLeaf);
//            s.getBatiments().forEach(batiment -> {
//                TreeItem<String> batLeaf = new TreeItem<>(batiment.getNomBatiment());
//                serviceLeaf.getChildren().add(batLeaf);
//                batiment.getChambres().forEach(chambre -> {
//                    TreeItem<String> chambreLeaf = new TreeItem<>("Chambre " + chambre.getNumChambre());
//                    batLeaf.getChildren().add(chambreLeaf);
//                    tView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                        @Override
//                        public void handle(MouseEvent) {
//                            Request request = new Request.Builder().url("http://localhost:9998/chambre/lits/"+chambre.getIdChambre()).build();
//                            Call call = okHttpClient.newCall(request);
//                            List<Lit> lits = null;
//                            try {
//                                lits = Arrays.asList(gson.fromJson(call.execute().body().string(),Lit[].class));
//                            } catch (IOException e) {
//                                throw new RuntimeException(e);
//                            }
//                            lstLits.setItems(FXCollections.observableList(lits));
//                        }
//                    });
//                });
//            });
//        }

    }
}
