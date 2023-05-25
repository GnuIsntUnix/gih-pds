package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import ma.uiass.eia.pds.gihBackEnd.prediction.PredictionAmbulance;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ConsulterAmbulancesController implements Initializable {

    @FXML
    private TableView<Ambulance> tblAmbulances;
    @FXML
    private TableColumn<Ambulance,Void> action;
    @FXML
    private TableColumn<Ambulance, String> immatriculCol;
    @FXML
    private TableColumn<Ambulance, TypeAmbulance> type;

    @FXML
    private TableColumn<Ambulance,Integer> km;

    @FXML
    private Button btnRevisions;
    @FXML
    TableColumn<Ambulance, State> state;


    @FXML
    private TableColumn<Ambulance, LocalDate> dateCol;

    @FXML
    private Button btnRetour;


    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        type.setCellValueFactory(new PropertyValueFactory<>("typeAmbulance"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateMiseEnCirculation"));
        immatriculCol.setCellValueFactory(new PropertyValueFactory<>("immatriculation"));
        km.setCellValueFactory(new PropertyValueFactory<Ambulance,Integer>("kilometrage"));

        tblAmbulances.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {

                        Parent fxmlLoader = null;
                        EffectuerRevisionController.setAmbulance(tblAmbulances.getSelectionModel().getSelectedItem());
                        try {
                            fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("popUpEffectuerRevision.fxml"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
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

            }
        });
        state.setCellValueFactory(new PropertyValueFactory<Ambulance,State>("state"));
        state.setCellFactory(ComboBoxTableCell.forTableColumn(new F(), new NFCD(), new NFLD()));

        tblAmbulances.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    btnRevisions.setDisable(false);
                }
            }
        });
        action.setCellFactory(col -> new TableCell<>() {
            private final FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            private final FontAwesomeIconView modifyIcon = new FontAwesomeIconView(FontAwesomeIcon.EDIT);


            private final Button deleteBtn = new Button("", deleteIcon);
            private final Button updateBtn = new Button("", modifyIcon);

            private final HBox hBox = new HBox(deleteBtn, updateBtn);

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);

                }
                else {
                    deleteBtn.setOnAction(event -> {
                        int row = getIndex();
                        try {
                            deleteAmbulance(tblAmbulances.getItems().get(row).getId());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        initialize(null, null);

                    });
                    updateBtn.setOnAction(event -> {tblAmbulances.setEditable(true);});
                    hBox.setSpacing(5);
                    setGraphic(hBox);

                }
            }


        });
        tblAmbulances.setItems(FXCollections.observableList(getAmbulance()));

    }

    @FXML
    void onRevisions(ActionEvent event) {
        ConsulterRevisionsController.setAmbulance(tblAmbulances.getSelectionModel().getSelectedItem());
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("consulterRevisions.fxml"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Scene scene = new Scene(fxmlLoader);
        Stage stage = new Stage();
        stage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                initialize(null, null);
            }
        });
        stage.setScene(scene);
        stage.showAndWait();
    }

    public List<Ambulance> getAmbulance(){
        Request request = new Request.Builder().url("http://localhost:9998/ambulance/getambulances").build();
        ObjectMapper mapper = new ObjectMapper();
        Response response = null;
        List<Ambulance> ambulances = null;
        try {
            response = okHttpClient.newCall(request).execute();
            ambulances = mapper.readValue(response.body().charStream(), new TypeReference<List<Ambulance>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ambulances;
    }
    public void deleteAmbulance(int id) throws IOException{
        Request request = new Request.Builder()
                .url("http://localhost:9998/ambulance/delete/"+ id)
                .delete()
                .build();

        Response response = okHttpClient.newCall(request).execute();
    }

    public void onBtnRetour(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();
        changeScene(stage,"accueil.fxml");
    }

    private void changeScene(
            Stage stage,
            String fxmlResourceName
    ) throws IOException {
        Parent parent = FXMLLoader.load(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResource(
                                fxmlResourceName
                        )
                )
        );
        Scene scene = new Scene(parent);

        stage.setScene(scene);
        stage.setTitle(fxmlResourceName);
        stage.show();
        stage.centerOnScreen();
    }
}
