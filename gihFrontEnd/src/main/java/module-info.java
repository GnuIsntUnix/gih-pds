module gihFrontEnd {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires okhttp3;
    requires gihBackEnd;
    requires de.jensd.fx.glyphs.fontawesome;

    opens ma.uiass.eia.pds.gihFrontEnd to javafx.fxml;
    exports ma.uiass.eia.pds.gihFrontEnd to javafx.graphics;
}