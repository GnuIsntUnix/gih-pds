package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.util.converter.NumberStringConverter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.*;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AmbulanceCreationController implements Initializable {


    @FXML
    TableView<Ambulance> table;
    @FXML
    TableColumn<Ambulance,TypeAmbulance> typeCol;
    @FXML
    ComboBox<TypeAmbulance> typeCombo;
    @FXML
    TableColumn<Ambulance,String> immatriculation;
    @FXML
    TableColumn<Ambulance, LocalDate> dateDeMiseEnCirculation;
    @FXML
    private TableColumn<Ambulance, String> km;
    @FXML
    private TableColumn<Ambulance, Void> action;
    @FXML
    TextField immText;
    @FXML
    TextField kmText;
    @FXML
    DatePicker date;
    @FXML
    Button create;
    @FXML
    Button confirmB;
    @FXML
    private Button open;
    List<Ambulance> lesAmbulances=new ArrayList<>();
    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        table.setEditable(true);
        //id.setCellValueFactory(new PropertyValueFactory<Ambulance,Integer>("id"));
        immatriculation.setCellValueFactory(new PropertyValueFactory<Ambulance,String>("immatriculation"));
        dateDeMiseEnCirculation.setCellValueFactory(new PropertyValueFactory<Ambulance,LocalDate>("dateMiseEnCirculation"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Ambulance,TypeAmbulance>("typeAmbulance"));
        typeCombo.setItems(FXCollections.observableArrayList(TypeAmbulance.B,TypeAmbulance.U,TypeAmbulance.I));
        typeCol.setCellFactory(column -> {
                    return new ComboBoxTableCell<>(TypeAmbulance.B,TypeAmbulance.U,TypeAmbulance.I);
                }
        );
        typeCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Ambulance,TypeAmbulance>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Ambulance,TypeAmbulance> event) {
                        lesAmbulances.remove(event.getRowValue());
                        event.getRowValue().setTypeAmbulance(event.getNewValue());
                        lesAmbulances.add(event.getRowValue());
                    }
                }
        );

        km.setCellValueFactory((new PropertyValueFactory<Ambulance,String>("kilometrage")));

        km.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKilometrage()));
        km.setCellFactory(TextFieldTableCell.forTableColumn());
        km.setOnEditCommit(event -> {
            TablePosition<Ambulance, String> pos = event.getTablePosition();
            int row = pos.getRow();
            Ambulance ambulance = event.getTableView().getItems().get(row);
            /*if(Integer.parseInt(event.getNewValue())<=Integer.parseInt(event.getOldValue()))){
//                Notifications.create()
//                        .title("warning")
//                        .position(Pos.CENTER)
//                        .text("Le kilométrage ne peut pas etre inferieure a la valeur origine!")
//                        .showWarning();
//                kmText.setText(event.getOldValue());
//                table.refresh();
            }else {
                lesAmbulances.remove(ambulance);
                ambulance.setKm(event.getNewValue());
                lesAmbulances.add(ambulance);
            }*/
            if(!event.getNewValue().matches("\\d*") || event.getNewValue()==null) {
                event.getTableView().getItems().get(row).setKilometrage(event.getOldValue());
                table.refresh();
                showAlert("Veuillez saisir des chiffres");
            } else {
                lesAmbulances.remove(ambulance);
                ambulance.setKilometrage(event.getNewValue());
                lesAmbulances.add(ambulance);
            }
        });

        immatriculation.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getImmatriculation()));
        immatriculation.setCellFactory(TextFieldTableCell.forTableColumn());
        immatriculation.setOnEditCommit(event -> {
            TablePosition<Ambulance, String> pos = event.getTablePosition();
            int row = pos.getRow();
            Ambulance ambulance = event.getTableView().getItems().get(row);
            if (event.getNewValue()==null){
                table.refresh();
                showAlert("Veuillez remplir tous les champs");
            }
            else{
                lesAmbulances.remove(ambulance);
                ambulance.setImmatriculation(event.getNewValue());
                lesAmbulances.add(ambulance);
            }
        });

        action.setCellFactory(col -> new TableCell<>() {
            private final FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

            private final Button deleteBtn = new Button("", deleteIcon);

            private final HBox hBox = new HBox(deleteBtn);

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);

                }
                else {
                    deleteBtn.setOnAction(event -> {
                        int row = getIndex();
                        lesAmbulances.remove(table.getItems().get(row).getId());
                        //deleteAmbulance(table.getItems().get(row).getId());
                        initialize(null, null);

                    });
                    hBox.setSpacing(5);
                    setGraphic(hBox);

                }
            }


        });

//        LocalDate minDate=LocalDate.now();
//        date.setDayCellFactory(picker -> new DateCell() {
//            @Override
//            public void updateItem(LocalDate date, boolean empty) {
//                super.updateItem(date, empty);
//                setDisable(date.isBefore(minDate)); // désactiver les dates antérieures
//                if (date.isBefore(minDate)) {
//                    setStyle("-fx-background-color: #ffc0cb;");
//                }
//         }});


        table.setItems(FXCollections.observableArrayList(lesAmbulances));
        table.getSelectionModel().selectFirst();
        table.requestLayout();
        kmText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                if (!newValue.matches("\\d*")) {
                    kmText.setText(newValue.replaceAll("[^\\d]", ""));
                    showAlert("Veuillez saisir uniquement des chiffres");
                }
            }

        });
        date.setEditable(false);

//        date.valueProperty().addListener(event -> {
//            LocalDate selectedDate = date.getValue();
//            if (selectedDate == null) {
//                showAlert("Veuillez choisir une date");
//            } else {
//                // Perform additional validation or processing with the selected date
//                boolean isValidDate = isValidDate(selectedDate);
//                if (!isValidDate) {
//                    showAlert("Date est invalid");
//                }
//            }
//        });

//        date.setOnAction(event -> {
//            LocalDate selectedDate = date.getValue();
//            if (selectedDate == null) {
//                showAlert("Veuillez choisir une date");
//            } else {
//                // Perform additional validation or processing with the selected date
//                boolean isValidDate = isValidDate(selectedDate);
//                if (!isValidDate) {
//                    showAlert("Date est invalid");
//                }
//            }
//        });
        dateDeMiseEnCirculation.setCellFactory(col -> new DateEditingCell());
        dateDeMiseEnCirculation.setOnEditCommit((TableColumn.CellEditEvent<Ambulance, LocalDate> event) -> {
            TablePosition<Ambulance, LocalDate> pos = event.getTablePosition();

            LocalDate newDate = event.getNewValue();
            int row = pos.getRow();
            Ambulance ambulance = event.getTableView().getItems().get(row);
            lesAmbulances.remove(ambulance);
            ambulance.setDateMiseEnCirculation(newDate);
            lesAmbulances.add(ambulance);
        });
        open.setOnAction(event -> {
            Parent loader;
            try {
                loader = FXMLLoader.load(getClass().getClassLoader().getResource("consulterAmbulances.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            Scene scene = new Scene(loader);
            stage.setTitle("Test");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            loader.requestFocus();
        });
    }
    private void updateEtat(Ambulance ambulance) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(ambulance));
        System.out.println(mapper.writeValueAsString(ambulance));
        Request request = new Request.Builder()
                .url("http://localhost:9998/ambulance/merge")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();

    }
    private boolean isValidDate(LocalDate date) {
        // Perform your custom date validation logic here
        // Return true if the date is considered valid, otherwise false
        // Example: Check if the selected date is in the past or within a certain range
        LocalDate today = LocalDate.now();
        return !date.isBefore(today);
    }

    private void updateData(Ambulance ambulance) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json"), mapper.writeValueAsString(ambulance));
        System.out.println(mapper.writeValueAsString(ambulance));
        Request request = new Request.Builder()
                .url("http://localhost:9998/ambulance/merge")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
    }

    public List<Ambulance> getAmbulance(){
        Request request = new Request.Builder().url("http://localhost:9998/ambulance/getambulances").build();
        ObjectMapper mapper = new ObjectMapper();
        Response response = null;
        List<Ambulance> ambulances = null;
        try {
            response = okHttpClient.newCall(request).execute();
            ambulances = mapper.readValue(response.body().charStream(), new TypeReference<List<Ambulance>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ambulances;
    }
    private List<String> listofImm=new ArrayList<>();
    public String generateRandomWord(int wordLength) {
        Random r = new Random(); // Intialize a Random Number Generator with SysTime as the seed
        StringBuilder sb = new StringBuilder(wordLength);
        for(int i = 0; i < wordLength; i++) { // For each letter in the word
            char tmp = (char) ('a' + r.nextInt('z' - 'a')); // Generate a letter between a and z
            sb.append(tmp); // Add it to the String
        }
        if(!listofImm.contains(sb.toString()))
            return sb.toString();
        return generateRandomWord(wordLength);
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    int i = 0;
    public void onCreate(ActionEvent event) throws IOException{
        if (immText.getText()!=null && kmText.getText()!=null && date.getValue()!=null && typeCombo.getValue()!=null){
            for (Ambulance a:getAmbulance()) {
                if (immText.getText().equals(a.getImmatriculation())) {
                    showAlert("L'immatriculation saisie est déjà assignée à une autre ambulance, veuillez saisir une nouvelle immatriculation");
                    immText.setText(null);
                    table.refresh();
                    return;
                }

            }
            if(Integer.parseInt(kmText.getText())<=0){
                Notifications.create()
                        .title("warning")
                        .position(Pos.CENTER)
                        .text("Le kilométrage ne peut pas etre négatif, veuillez choisir un nombre strictement positif!")
                        .showWarning();
                immText.setText(null);
                kmText.setText(null);
                date.setValue(null);
                typeCombo.setValue(null);
                return;
            }

            Alert confirmationDialog = new Alert(Alert.AlertType.NONE);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText("Please confirm your choices:");
            confirmationDialog.setContentText(
                    "Immatriculation: " + immText.getText() + "\n" +
                            "Kilométrage: " + kmText.getText() + "\n" +
                            "Date: " + date.getValue() + "\n" +
                            "Type: " + typeCombo.getValue()
            );

            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            ButtonType modifyButton = new ButtonType("Modify");
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

            confirmationDialog.getButtonTypes().setAll(cancelButton, modifyButton, okButton);

            // Show the confirmation dialog and wait for user response
            Optional<ButtonType> result = confirmationDialog.showAndWait();

            if (result.isPresent()) {
                ButtonType selectedButton = result.get();
                if (selectedButton == cancelButton) {
                    // User canceled, clear all fields and start again
                    clearFields();
                } else if (selectedButton == modifyButton) {
                    // User wants to modify, do nothing and let them edit the fields
                } else if (selectedButton == okButton) {
                    // User confirmed, proceed with the code
                    Ambulance ambulance = new Ambulance(immText.getText(), date.getValue(), kmText.getText(), typeCombo.getValue());
                    lesAmbulances.add(ambulance);
                    clearFields();
                    initialize(null, null);
                }
            }
//            Ambulance ambulance=new Ambulance(immText.getText(),date.getValue(), kmText.getText(), typeCombo.getValue());
//            ObjectMapper mapper=new ObjectMapper();
//            RequestBody body= RequestBody.create(MediaType.parse("application/json"), mapper.writeValueAsString(ambulance));
//            Request request = new Request.Builder()
//                    .url("http://localhost:9998/ambulance/ambulancecreation")
//                    .post(body)
//                    .build();
//            Call call = okHttpClient.newCall(request);
//            Response response = call.execute();
//            initialize(null,null);
        }
        else{
            Notifications.create()
                    .title("warning")
                    .position(Pos.CENTER)
                    .text("Veuillez remplir tous les champs !")
                    .showWarning();
            immText.setText(null);
            kmText.setText(null);
            date.setValue(null);
            typeCombo.setValue(null);
            return;
        }

    }
    public void onConfirm(ActionEvent event)throws IOException{
        System.out.println(lesAmbulances.size());
        for (Ambulance ambulance:lesAmbulances){
            System.out.println(ambulance);
            if (ambulance.getImmatriculation().equals("")||ambulance.getKilometrage().equals("")){
                showAlert("Veuillez remplir tous les champs avant de confirmer");
                return;
            }
        }
        for(Ambulance ambulance:lesAmbulances){
            ObjectMapper mapper = new ObjectMapper();
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), mapper.writeValueAsString(ambulance));
            Request request = new Request.Builder()
                    .url("http://localhost:9998/ambulance/ambulancecreation")
                    .post(body)
                    .build();
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            initialize(null,null);
        }
        lesAmbulances=new ArrayList<>();
        table.setItems(FXCollections.observableArrayList(lesAmbulances));
        table.refresh();
        Notifications.create()
                .title("")
                .position(Pos.CENTER)
                .text("Ambulances crées avec succès")
                .showInformation();
        System.out.println(lesAmbulances.size());
    }
    private void clearFields() {
        immText.setText(null);
        kmText.setText(null);
        date.setValue(null);
        typeCombo.setValue(null);
    }
    public void deleteAmbulance(int id) throws IOException{
        Request request = new Request.Builder()
                .url("http://localhost:9998/ambulance/delete/"+ id)
                .delete()
                .build();

        Response response = okHttpClient.newCall(request).execute();
    }
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    class DateEditingCell extends TableCell<Ambulance, LocalDate> {
        private final DatePicker datePicker;


        public DateEditingCell() {
            this.datePicker = new DatePicker();
            LocalDate minDate=LocalDate.now();
            datePicker.setEditable(false);
            this.datePicker.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(date.isBefore(minDate)); // désactiver les dates antérieures
                    if (date.isBefore(minDate)) {
                        setStyle("-fx-background-color: #ffc0cb;");
                    }
                }});
            this.datePicker.setConverter(new StringConverter<LocalDate>() {
                @Override
                public String toString(LocalDate date) {
                    if (date != null) {
                        return dateFormatter.format(date);
                    } else {
                        return "";
                    }
                }

                @Override
                public LocalDate fromString(String string) {
                    if (string != null && !string.isEmpty()) {
                        return LocalDate.parse(string, dateFormatter);
                    } else {
                        return null;
                    }
                }
            });
            setContentDisplay(ContentDisplay.TEXT_ONLY);
            setAlignment(Pos.CENTER);
            this.datePicker.setEditable(false);
            this.datePicker.setPromptText("dd/MM/yyyy");
            this.datePicker.setOnAction(event -> {
                commitEdit(this.datePicker.getValue());
            });
            setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !isEmpty()) {
                    startEdit();
                }
            });
        }

        @Override
        protected void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                setText(dateFormatter.format(date));
                setGraphic(null);
            }
        }

        @Override
        public void startEdit() {
            super.startEdit();
            final LocalDate date = getItem();
            if (date != null) {
                this.datePicker.setValue(date);
            }
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            setGraphic(this.datePicker);
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }

        @Override
        public void commitEdit(LocalDate newValue) {
            super.commitEdit(newValue);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
    }

}
