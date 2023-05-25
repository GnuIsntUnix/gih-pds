package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.Admission;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ConsulterAdmission implements Initializable {

    private static Lit lit;

    public static Lit getLit() {
        return lit;
    }

    @FXML
    private TableColumn<Admission, Integer> colNumAdmission;

    @FXML
    private TableColumn<Admission, LocalDate> colDatedebut;

    @FXML
    private TableColumn<Admission, LocalDate> colDateFin;

    @FXML
    private TableView<Admission> tblAdmissions;

    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblAdmissions.setItems(FXCollections.observableList(getAdmissions()));
        colNumAdmission.setCellValueFactory(new PropertyValueFactory<>("idAdmission"));
        colDatedebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        colDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
    }

    public List<Admission> getAdmissions() {
        Request request = new Request.Builder().url("http://localhost:9998/admission/getadmissionsonlit/" + ConsulterAdmission.getLit().getN_lit()).build();
        ObjectMapper mapper = new ObjectMapper();

        Response response = null;
        List<Admission> admissions = null;
        try {
            response = okHttpClient.newCall(request).execute();
            admissions = mapper.readValue(response.body().charStream(), new TypeReference<List<Admission>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(admissions);
        return admissions;
    }

    public static void setLit(Lit lit) {
        ConsulterAdmission.lit = lit;
    }
}
