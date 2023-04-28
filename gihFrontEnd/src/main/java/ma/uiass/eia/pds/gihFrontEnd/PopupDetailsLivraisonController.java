package ma.uiass.eia.pds.gihFrontEnd;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.uiass.eia.pds.gihBackEnd.model.*;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupDetailsLivraisonController implements Initializable {

    private static Livraison livraison;

    public static Livraison getLivraison() {
        return livraison;
    }

    public static void setLivraison(Livraison livraison) {
        PopupDetailsLivraisonController.livraison = livraison;
    }

    @FXML
    private TableColumn<DetailLivraison, DM> colDm;

    @FXML
    private TableColumn<DetailLivraison, Fournisseur> colFournisseur;

    @FXML
    private TableColumn<DetailLivraison, Integer> colQte;

    @FXML
    private TableView<DetailLivraison> tableDetails;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableDetails.setItems(FXCollections.observableArrayList(PopupDetailsLivraisonController.getLivraison().getDetailLivraisonList()));
        colDm.setCellValueFactory(new PropertyValueFactory<>("dm"));
        colQte.setCellValueFactory(new PropertyValueFactory<>("qte"));
        colFournisseur.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));



    }
}
