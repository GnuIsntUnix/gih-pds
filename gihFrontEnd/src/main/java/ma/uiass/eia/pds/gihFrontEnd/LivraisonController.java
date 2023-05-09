package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LivraisonController implements Initializable {



    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnValider;

    @FXML
    private ComboBox<TypeDM> cboxTypedm;
    @FXML
    private ComboBox<DM> cboxdm;

    @FXML
    private ComboBox<Fournisseur> cboxFournisseur;

    @FXML
    private TextField txtQte;

    @FXML
    private TableColumn<DetailLivraison, DM> clDM;

    @FXML
    private TableColumn<DetailLivraison, Fournisseur> clFournisseur;

    @FXML
    private TableColumn<DetailLivraison, Integer> clQte;

    @FXML
    private TableView<DetailLivraison> tbvLivraison;

    @FXML
    private Tab tabConsulter;

    @FXML
    private Tab tabDM;

    @FXML
    private Tab tabFournisseur;
    @FXML
    private Tab tabAffecter;

    @FXML
    void onSelectionConsulter(Event event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("consulterLivraison.fxml"));
        tabConsulter.setContent(fxmlLoader);

    }


    @FXML
    void onSelectionFournisseur(Event event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("fournisseur.fxml"));
        tabFournisseur.setContent(fxmlLoader);

    }

    @FXML
    void onAffecter(Event event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("AffecterDM.fxml"));
        tabAffecter.setContent(fxmlLoader);

    }

    @FXML
    void onDMs(Event event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("dms.fxml"));
        tabDM.setContent(fxmlLoader);

    }



    OkHttpClient okHttpClient = new OkHttpClient();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clDM.setCellValueFactory(new PropertyValueFactory<>("dm"));
        clQte.setCellValueFactory(new PropertyValueFactory<>("Qte"));
        clFournisseur.setCellValueFactory(new PropertyValueFactory<>("Fournisseur"));

        cboxdm.setDisable(true);
        cboxTypedm.setItems(FXCollections.observableArrayList(getTypeDMS()));
        cboxTypedm.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                cboxdm.setItems(FXCollections.observableArrayList((getDmByType(newValue.getIdType()))));
                cboxdm.setDisable(false);
                cboxdm.getSelectionModel().clearSelection();
            } else {
                cboxdm.setItems(null);
            }
        });

        cboxFournisseur.setItems(FXCollections.observableArrayList(getFournisseur()));
//        cboxFournisseur.getItems().add(new Fournisseur("Ajouter un nouveau fournisseur..."));
//        cboxFournisseur.setOnAction(e ->{
//            String fournisseur = "";
//            try {
//                fournisseur = cboxFournisseur.getSelectionModel().getSelectedItem().toString();
//            }
//            catch (Exception ignore){
//
//            }
//            System.out.println(fournisseur);
//            String finalFournisseur = fournisseur;
//            Platform.runLater(() -> {
//                if ("Ajouter un nouveau fournisseur...".equals(Objects.requireNonNull(finalFournisseur))) {
//                    Parent fxmlLoader = null;
//                    try {
//                        fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("ajouterFournisseur.fxml"));
//                    } catch (IOException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                    Scene scene = new Scene(fxmlLoader);
//                    Stage stage = new Stage();
//                    stage.setScene(scene);
//                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//                        @Override
//                        public void handle(WindowEvent windowEvent) {
//                            initialize(null, null);
//                            stage.close();
//                        }
//                    });
//                    stage.showAndWait();                }
//            });
//
//        });
    }




    public List<DM> getDmByType(int id){
        Request request = new Request.Builder().url("http://localhost:9998/dm/getdms/bytype/"+ id).build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<DM> DMS = null;
        try {
            response = okHttpClient.newCall(request).execute();
            DMS = mapper.readValue(response.body().charStream(), new TypeReference<List<DM>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return DMS;
    }

    public List<Fournisseur> getFournisseur(){
        Request request = new Request.Builder().url("http://localhost:9998/fournisseur/getfournisseurs").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Fournisseur> fournisseurs = null;
        try {
            response = okHttpClient.newCall(request).execute();
            fournisseurs = mapper.readValue(response.body().charStream(), new TypeReference<List<Fournisseur>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fournisseurs;
    }
    @FXML
    void onBtnAjouter(ActionEvent event) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        int qte = Integer.parseInt(txtQte.getText());
        DM dm = cboxdm.getSelectionModel().getSelectedItem();
        Fournisseur fournisseur = cboxFournisseur.getSelectionModel().getSelectedItem();
        Stock stock = getStock();

        DetailLivraison detailLivraison = new DetailLivraison(dm, qte,fournisseur);
        tbvLivraison.getItems().add(detailLivraison);

        List<ExemplaireDm> exemplaireDms = new ArrayList<>();

        for (int i = 0; i<qte; i++){
            exemplaireDms.add(new ExemplaireDm(dm, stock));
        }

        dm.setExemplaireDmList(exemplaireDms);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(dm));

        System.out.println(mapper.writeValueAsString(dm));

        Request request = new Request.Builder()
                .url("http://localhost:9998/dm/save/merge")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();



    }


    @FXML
    void onBtnValider(ActionEvent event) throws IOException {

        Stock stock = getStock();

        ObjectMapper mapper = new ObjectMapper();

        List<DetailLivraison> list = tbvLivraison.getItems().stream().toList();
        Livraison livraison = new Livraison();
        livraison.setDate(LocalDate.now());

        for (DetailLivraison d : list){
            d.setLivraison(livraison);

        }
        livraison.setDetailLivraisonList(list);

        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(livraison));

        System.out.println(mapper.writeValueAsString(livraison));

        Request request = new Request.Builder()
                .url("http://localhost:9998/livraison/save")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        response.close();
        initialize(null, null);
        tbvLivraison.setItems(FXCollections.observableList(new ArrayList<>()));
    }

    public List<TypeDM> getTypeDMS(){
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

    public Stock getStock(){
        Request request = new Request.Builder().url("http://localhost:9998/stock/getstock/byservice/1").build();
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
