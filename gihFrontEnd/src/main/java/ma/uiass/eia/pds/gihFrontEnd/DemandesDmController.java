package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DemandesDmController implements Initializable {

    @FXML
    private TableColumn<DemandeDm, Void> actionColumn;
    @FXML
    private TableColumn<DetailDemandeDm, DM> dm;
    @FXML
    private TableColumn<DetailDemandeDm, Integer> qte;
    @FXML
    private TableView<DetailDemandeDm> tableDetail;

    @FXML
    private TableColumn<DemandeDm, LocalDate> dateColumn;

    @FXML
    private TableColumn<DemandeDm, Integer> demandeColumn;

    @FXML
    private TableColumn<DemandeDm, EtatDemandeDM> etatColumn;
    @FXML
    private TableView<DemandeDm> tableDemandes;

    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(MenuControllerChefService.getService().getNomService());

        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etatDemande"));
        demandeColumn.setCellValueFactory(new PropertyValueFactory<>("idDemande"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateDemande"));
        dm.setCellValueFactory(new PropertyValueFactory<>("dm"));
        qte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        //////////////////////////////////////////////////////////////////////////////////////////////////
        actionColumn.setCellFactory(col -> new TableCell<>() {
            private final FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

            private final Button deleteBtn = new Button("", deleteIcon);

            private final HBox hBox = new HBox(deleteBtn);

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);

                }
                else {
                    deleteBtn.setOnAction(event -> {
                        System.out.println("trash");
                        int row = getIndex();
                        DemandeDm demande = tableDemandes.getItems().get(row);
                        if (demande.getEtatDemande() == EtatDemandeDM.INITIALISE) {
                            int numdemande = tableDemandes.getItems().get(row).getIdDemande();
                            System.out.println(numdemande);
                            //tableDemandes.getItems().remove(row);
                            try {
                                deleteDemande(numdemande);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            initialize(null, null);
                        } else {
                            Notifications.create()
                                    .position(Pos.CENTER)
                                    .title("warning")
                                    .text("Demande déja" + demande.getEtatDemande())
                                    .showWarning();

                        }
                    });
                    hBox.setSpacing(5);
                    setGraphic(hBox);

                }
            }


        });
        System.out.println(getDemandes());
        tableDemandes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                tableDetail.setItems((FXCollections.observableArrayList(tableDemandes.getSelectionModel().getSelectedItem().getDetailDemandeDms())));
            }
        });
        tableDemandes.setItems(FXCollections.observableList(getDemandes()));
    }


        public void deleteDemande(int id) throws IOException {
//            Request request = new Request.Builder().url("http://localhost:9998/demande/delete/"+ id).delete().build();
//
//            Response response = null;
//            try {
//                response = okHttpClient.newCall(request).execute();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            Request request = new Request.Builder()
                    .url("http://localhost:9998/demande/delete/"+ id)
                    .delete()
                    .build();

            Response response = okHttpClient.newCall(request).execute();

        }

    public List<DemandeDm> getDemandes(){
        Request request = new Request.Builder().url("http://localhost:9998/demande/getdemandes/byservice/"+ MenuControllerChefService.getService().getIdService()).build();
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
}
