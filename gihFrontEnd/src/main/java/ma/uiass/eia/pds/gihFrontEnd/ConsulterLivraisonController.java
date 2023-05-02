package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ma.uiass.eia.pds.gihBackEnd.model.Admission;
import ma.uiass.eia.pds.gihBackEnd.model.Fournisseur;
import ma.uiass.eia.pds.gihBackEnd.model.Livraison;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterLivraisonController implements Initializable {

    @FXML
    private TableColumn<Livraison, LocalDate> colDate;


    @FXML
    private TableColumn<Livraison, Integer> colLivraison;

    @FXML
    private TableView<Livraison> tableLivraison;

    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableLivraison.setItems(FXCollections.observableArrayList(getLivraison()));
        tableLivraison.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {

                    System.out.println("double");
                    Parent fxmlLoader = null;
                    try {
                        fxmlLoader = FXMLLoader.load(getClass().getClassLoader().getResource("popupDetailsLivraison.fxml"));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    PopupDetailsLivraisonController.setLivraison(tableLivraison.getSelectionModel().getSelectedItem());
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

        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colLivraison.setCellValueFactory(new PropertyValueFactory<>("Id"));

    }
    public List<Livraison> getLivraison(){
        Request request = new Request.Builder().url("http://localhost:9998/livraison/getlivraisons").build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Livraison> livraisons = null;
        try {
            response = okHttpClient.newCall(request).execute();
            livraisons = mapper.readValue(response.body().charStream(), new TypeReference<List<Livraison>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return livraisons;
    }

}