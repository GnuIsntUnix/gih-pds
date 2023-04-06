package ma.uiass.eia.pds.gihFrontEnd;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ma.uiass.eia.pds.gihBackEnd.model.EtatLit;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;
import ma.uiass.eia.pds.gihBackEnd.model.Service;
import ma.uiass.eia.pds.gihBackEnd.model.Stock;
import ma.uiass.eia.pds.gihBackEnd.services.ServiceLits;

public class affectationScene  extends Application {

    private Stock stock = new Stock();

    @Override
    public void start(Stage primaryStage) {
        // Initialiser le stock
        //initStock();

        // Créer une grille pour afficher les éléments
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        // Créer une étiquette et une liste déroulante pour les services
        Label serviceLabel = new Label("Service :");
        gridPane.add(serviceLabel, 0, 0);
        ComboBox<ServiceLits> serviceComboBox = new ComboBox<>();
        serviceComboBox.getItems().addAll((ServiceLits) stock.getServices());
        gridPane.add(serviceComboBox, 1, 0);

        // Créer une étiquette et une liste déroulante pour les lits
        Label litLabel = new Label("Lit :");
        gridPane.add(litLabel, 0, 1);
        ComboBox<Lit> litComboBox = new ComboBox<>();
        gridPane.add(litComboBox, 1, 1);

        // Créer un bouton pour affecter le lit au service
        Button assignButton = new Button("Affecter");
        gridPane.add(assignButton, 1, 2);

        // Ajouter une action au bouton pour affecter le lit au service
        assignButton.setOnAction(event -> {
            ServiceLits service = serviceComboBox.getValue();
            Lit lit = litComboBox.getValue();
            if (service != null && lit != null) {
                service.add(lit);
                lit.setEtat(EtatLit.valueOf("Non disponible"));


            }
        });


    }

}
