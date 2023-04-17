module gihFrontEnd {
    requires javafx.controls;
    requires org.controlsfx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires okhttp3;
    requires gihBackEnd;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.materialicons;
    requires com.fasterxml.jackson.jaxrs.base;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires java.logging;

    opens ma.uiass.eia.pds.gihFrontEnd to javafx.fxml;
    exports ma.uiass.eia.pds.gihFrontEnd;
}