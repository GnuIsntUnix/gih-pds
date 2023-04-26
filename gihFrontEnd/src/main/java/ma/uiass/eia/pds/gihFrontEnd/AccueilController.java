package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AccueilController implements Initializable {

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnLogistique;

    @FXML
    private ComboBox<Service> cboxServices;

    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAdmin.setOnAction( new SceneChangeEventHandler("menuDashboard.fxml"));
        cboxServices.setItems(FXCollections.observableArrayList(getServices()));
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
        List<Service> servicesSansLogistique = new ArrayList<>();
        services.forEach(service -> {
            if (!service.getCodeS().equalsIgnoreCase("logi"))
                servicesSansLogistique.add(service);
        });
        return servicesSansLogistique;
    }

    class SceneChangeEventHandler implements EventHandler<ActionEvent> {
        private final String fxmlResourceName;

        public SceneChangeEventHandler(String fxmlResourceName) {
            this.fxmlResourceName = fxmlResourceName;
        }

        @Override
        public void handle(ActionEvent event) {
            try {
                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();

                changeScene(stage, fxmlResourceName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    public void onCboxSelection(ActionEvent event) throws IOException {
        Service service = cboxServices.getSelectionModel().getSelectedItem();
        MenuControllerChefService.setService(service);
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();
        changeScene(stage,"menu(Chef).fxml");
    }


}
