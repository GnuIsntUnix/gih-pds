package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GererServicesController implements Initializable {

    @FXML
    private TableView<Service> tblServices;

    @FXML
    private TableColumn<Service, Void> actionsCol;

    @FXML
    private TableColumn<Service, String> codeCol;

    @FXML
    private TableColumn<Service, String> nomCol;
    @FXML
    private Button btnAjouter;

    OkHttpClient okHttpClient = new OkHttpClient();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actionsCol.setCellFactory(param -> new TableCell<>() {
            private final FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

            private final Button deleteButton = new Button("", deleteIcon);

            // Override the updateItem method to set the button actions and display them in the cell
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // Set the delete button action
                    deleteButton.setOnAction(event -> {
                        int row = getIndex();
                        int id = tblServices.getItems().get(row).getIdService();
                        Request request = new Request.Builder().url("http://localhost:9998/service/delete/"+id).delete().build();
                        Response response = null;
                        try {
                            response = okHttpClient.newCall(request).execute();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        initialize(null, null);

                    });


                    // Display both buttons in the cell
                    HBox hbox = new HBox(deleteButton);
                    hbox.setSpacing(5);
                    setGraphic(hbox);
                }
            }
        });


        codeCol.setCellValueFactory(new PropertyValueFactory<>("codeS"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nomService"));

        tblServices.setItems(FXCollections.observableList(getServices()));

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

    public void ajouterPopup(ActionEvent actionEvent) throws IOException {

        Parent fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("popupAjouterService.fxml"));
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
