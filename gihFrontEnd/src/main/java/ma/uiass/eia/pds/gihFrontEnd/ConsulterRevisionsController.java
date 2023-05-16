package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.Ambulance;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Revision;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterRevisionsController implements Initializable {

    private static Ambulance ambulance;


    @FXML
    private TableColumn<Revision, Void> actionCol;

    @FXML
    private TableColumn<Revision, LocalDate> dateEntreeCol;

    @FXML
    private TableColumn<Revision, LocalDate> dateSortieCol;

    @FXML
    private TableColumn<Revision, String> descriptionCol;

    @FXML
    private TableColumn<Revision, Integer> idCol;

    @FXML
    private TableColumn<Revision, String> kilometrageCol;

    @FXML
    private TableView<Revision> table;

    @FXML
    private TableColumn<Revision, String> typeRevisionCol;



    private OkHttpClient okHttpClient = new OkHttpClient();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(new PropertyValueFactory<Revision, Integer>("id"));
        dateEntreeCol.setCellValueFactory(new PropertyValueFactory<>("dateRevision"));
        dateSortieCol.setCellValueFactory(new PropertyValueFactory<>("dateSortie"));
        typeRevisionCol.setCellValueFactory(new PropertyValueFactory<>("typeRev"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        kilometrageCol.setCellValueFactory(new PropertyValueFactory<>("kilometrage"));

        actionCol.setCellFactory(col -> {
            TableCell<Revision, Void> cell = new TableCell<Revision, Void>() {

                private final Button sortirBtn = new Button("Sortir");

                {
                    sortirBtn.setOnAction(event -> {
                        Revision revision = getTableView().getItems().get(getIndex());
                        revision.setDateSortie(LocalDate.now());
                        try {
                            update(revision);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        initialize(null,null);
                    });

                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(sortirBtn);
                    }
                }
            };
            return cell;
        });

        table.setItems(FXCollections.observableArrayList(getRevisions(ConsulterRevisionsController.getAmbulance().getId())));

    }
    public List<Revision> getRevisions(int id){
        Request request = new Request.Builder().url("http://localhost:9998/revision/getrevisionsonambulance/" + id).build();
        Call call = okHttpClient.newCall(request);
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Revision> revisions = null;
        try {
            response = okHttpClient.newCall(request).execute();
            revisions = mapper.readValue(response.body().charStream(), new TypeReference<List<Revision>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(revisions);

        return revisions;



    }
 public void update(Revision rev) throws IOException {
     ObjectMapper mapper = new ObjectMapper();
     RequestBody body = RequestBody.create(
             MediaType.parse("application/json"), mapper.writeValueAsString(rev));

     Request request = new Request.Builder()
             .url("http://localhost:9998/revision/update")
             .put(body)
             .build();

     Call call = okHttpClient.newCall(request);
     Response response = call.execute();
 }


    public static Ambulance getAmbulance() {
        return ambulance;
    }

    public static void setAmbulance(Ambulance ambulance) {
        ConsulterRevisionsController.ambulance = ambulance;
    }

}
