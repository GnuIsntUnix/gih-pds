package com.example.demo;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AffichageLitsController implements Initializable {

    @FXML
    private TableView<String> tblLits;


    @FXML
    private TableColumn<String, Void> actionsColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        actionsColumn = new TableColumn<>("Actions");

// Set the cell factory for the column to create custom cells with two buttons
        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            private final FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.EDIT);

            private final Button deleteButton = new Button("", deleteIcon);
            private final Button editButton = new Button("", editIcon);

            // Override the updateItem method to set the button actions and display them in the cell
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // Set the delete button action
                    deleteButton.setOnAction(event -> {
                        // Handle the delete button action here
                    });

                    // Set the edit button action
                    editButton.setOnAction(event -> {
                        // Handle the edit button action here
                    });

                    // Display both buttons in the cell
                    HBox hbox = new HBox(deleteButton, editButton);
                    hbox.setSpacing(5);
                    setGraphic(hbox);
                }
            }
        });

// Add the column to the TableView
        tblLits.getColumns().add(actionsColumn);
    }
}
