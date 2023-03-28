package ma.uiass.eia.pds.gihFrontEnd;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import ma.uiass.eia.pds.gihBackEnd.model.Lit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StockController {
    @FXML
    private Button affecter;

    @FXML
    private Button ajouter;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private TableColumn<?, ?> etatDemande;

    @FXML
    private TableColumn<?, ?> marque;

    @FXML
    private Button modifier;

    @FXML
    private TableColumn<?, ?> numDemande;

    @FXML
    private TableColumn<?, ?> qtte;

    @FXML
    private Button quitter;

    @FXML
    private Button supprimer;


    public void afficherLis() {
        try {
            URL url = new URL("http://localhost:9998/lit/getlits"); // URL de l'API backend
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                // convertir le JSON en objet Java
                Gson gson = new Gson();
                Lit lit = gson.fromJson(output, Lit.class);
                // afficher le nom du lit dans la console JavaFX
                System.out.println(lit.getN_lit());
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
