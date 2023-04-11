package ma.uiass.eia.pds.gihFrontEnd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static void main( String[] args )
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent loader = FXMLLoader.load(getClass().getClassLoader().getResource("admissionScene.fxml"));
        Scene scene = new Scene(loader);
        stage.setTitle("Test");
        stage.setScene(scene);
        stage.show();
        loader.requestFocus();
    }
}
