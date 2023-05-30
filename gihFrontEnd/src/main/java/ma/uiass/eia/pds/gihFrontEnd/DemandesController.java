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
import org.controlsfx.control.PropertySheet;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class DemandesController implements Initializable {
    @FXML
    private Button bttnAjouter;

    @FXML
    private ComboBox<DM> cbDM;

    @FXML
    private ComboBox<TypeDM> cbTypeDm;

    @FXML
    private Spinner<Integer> qtteSpinner;


    @FXML
    private Button bttnvalider;
    @FXML
    private TableColumn<DetailDemandeDm, DM> DMColumn;

    @FXML
    private TableView<DetailDemandeDm> DemandesTable;
    @FXML
    private TableColumn<DetailDemandeDm, LocalDate> dateColumn;

    @FXML
    private TableColumn<DetailDemandeDm, Integer> qtteColumn;
    @FXML
    private Button bttnsupprimer;
    @FXML
            private Tab tabConsulter;


    OkHttpClient okHttpClient = new OkHttpClient();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbTypeDm.setItems(FXCollections.observableArrayList(getTypeDM()));
        cbDM.setDisable(true);
        cbTypeDm.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                cbDM.setDisable(false);
                System.out.println(newValue.getDms());
                cbDM.setItems(FXCollections.observableArrayList(getDM(newValue)));
            } else {
                cbDM.setDisable(true);
                cbDM.getItems().clear();
            }
        });
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
        qtteSpinner.setValueFactory(valueFactory);
        DemandesTable.setEditable(true);




        DMColumn.setCellValueFactory(new PropertyValueFactory<>("dm"));
        qtteColumn.setCellValueFactory(new PropertyValueFactory<>("qte"));
        qtteColumn.setCellFactory(col -> new IntegerEditingCell());
        /////////////////////////////////////////////////////////////////////////////////////////////////////////


    }
    @FXML
    public void onBtnAjouter(ActionEvent event) throws IOException {
        ObjectMapper mapper = new ObjectMapper();


        int qte = qtteSpinner.getValue();

        DM dm = cbDM.getSelectionModel().getSelectedItem();

        DetailDemandeDm detailDemande = new DetailDemandeDm(dm, qte);
        DemandesTable.getItems().add(detailDemande);
        cbTypeDm.valueProperty().set(null);
        cbDM.valueProperty().set(null);
        qtteSpinner.getValueFactory().setValue(1);


       // RequestBody body = RequestBody.create(
           //     MediaType.parse("application/json"), mapper.writeValueAsString(detailDemande));

       // Request request = new Request.Builder()
             //   .url("http://localhost:9998/detaildemandedm/save")
               // .post(body)
                //.build();

       // Call call = okHttpClient.newCall(request);
       // Response response = call.execute();
       // System.out.println(response.code());


    }

    class IntegerEditingCell extends TableCell<DetailDemandeDm, Integer> {

        private final TextField textField = new TextField();
        private final Pattern intPattern = Pattern.compile("-?\\d+");

        public IntegerEditingCell() {
            textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
                if (! isNowFocused) {
                    processEdit();
                }
            });
            textField.setOnAction(event -> processEdit());
        }

        private void processEdit() {
            String text = textField.getText();
            if (intPattern.matcher(text).matches()) {
                commitEdit(Integer.parseInt(text));
            } else {
                cancelEdit();
            }
        }

        @Override
        public void updateItem(Integer value, boolean empty) {
            super.updateItem(value, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else if (isEditing()) {
                setText(null);
                textField.setText(value.toString());
                setGraphic(textField);
            } else {
                setText(value.toString());
                setGraphic(null);
            }
        }

        @Override
        public void startEdit() {
            super.startEdit();
            Number value = getItem();
            if (value != null) {
                textField.setText(value.toString());
                setGraphic(textField);
                setText(null);
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getItem().toString());
            setGraphic(null);
        }

        // This seems necessary to persist the edit on loss of focus; not sure why:
        @Override
        public void commitEdit(Integer value) {
            super.commitEdit(value);
            ((DetailDemandeDm) this.getTableRow().getItem()).setQte(value.intValue());
        }
    }

    @FXML
    public void onBtnValider(ActionEvent event) throws IOException {
       // ObjectMapper mapper = JsonMapper.builder()
              //  .addModule(new JavaTimeModule())
                //.build();
        ObjectMapper mapper = new ObjectMapper();


        List<DetailDemandeDm> list = DemandesTable.getItems().stream().toList();
        System.out.println(list);


        DemandeDm demandeDm = new DemandeDm(MenuControllerChefService.getService(),LocalDate.now());
        System.out.println(demandeDm);
        for (DetailDemandeDm d : list){
            d.setDemandeDm(demandeDm);
        }
        demandeDm.setDetailDemandeDms(list);

        System.out.println(demandeDm.getDetailDemandeDms());


        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(demandeDm));
        System.out.println(mapper.writeValueAsString(demandeDm));

        Request request = new Request.Builder()
                .url("http://localhost:9998/demande/save")
                .post(body)
                .build();

        /*OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectionPool(new ConnectionPool(5, 30, TimeUnit.SECONDS));
        builder.addInterceptor(chain -> {
            Request req = chain.request();
            Response res = chain.proceed(req);
            res.close(); // close response to avoid connection leak
            return res;
        });


        OkHttpClient okHttpClient = builder.build();
        Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE); // enable logging

         */

        Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        DemandesTable.setItems(null);
        initialize(null, null);
    }
    @FXML
    public void deleteDetailList(ActionEvent event) {
        bttnsupprimer.setOnAction(e -> {
            DetailDemandeDm selectedItem = DemandesTable.getSelectionModel().getSelectedItem();
            DemandesTable.getItems().remove(selectedItem);
        });

        initialize(null,null);
    }




    public List<DemandeDm> getDemandes(){
        Request request = new Request.Builder().url("http://localhost:9998/demande/getdemandes").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<DemandeDm> demandeDms = null;
        try {
            response = okHttpClient.newCall(request).execute();
            demandeDms = mapper.readValue(response.body().charStream(), new TypeReference<List<DemandeDm>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return demandeDms;
    }
    public List<TypeDM> getTypeDM(){
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

    public List<DM> getDM(){
        Request request = new Request.Builder().url("http://localhost:9998/dm/getdms").build();
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

    public List<DM> getDM(TypeDM tDm){
        Request request = new Request.Builder().url("http://localhost:9998/dm/getdms/bytype/"+ tDm.getIdType()).build();
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

    public void onTabConsulter(Event event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("ConsultationDemandeDm.fxml"));
        tabConsulter.setContent(fxmlLoader);
    }
}
