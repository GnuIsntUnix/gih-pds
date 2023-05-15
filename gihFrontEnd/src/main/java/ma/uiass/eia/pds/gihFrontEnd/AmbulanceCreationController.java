package ma.uiass.eia.pds.gihFrontEnd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import ma.uiass.eia.pds.gihBackEnd.model.*;
import okhttp3.*;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class AmbulanceCreationController implements Initializable {
    @FXML
    TableView<Ambulance> table;
    @FXML
    TableColumn<Ambulance,Integer> id;
    @FXML
    TableColumn<Ambulance,String> immatriculation;
    @FXML
    TableColumn<Ambulance, LocalDate> dateDeMiseEnCirculation;
    @FXML
    private TableColumn<Ambulance, LocalDate> dateDeCreation;
    @FXML
    private TableColumn<Ambulance, Void> action;
    @FXML
    TextField immText;
    @FXML
    DatePicker date;
    @FXML
    Button create;
    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        table.setEditable(true);
        id.setCellValueFactory(new PropertyValueFactory<Ambulance,Integer>("id"));
        immatriculation.setCellValueFactory(new PropertyValueFactory<Ambulance,String>("immatriculation"));
        dateDeMiseEnCirculation.setCellValueFactory(new PropertyValueFactory<Ambulance,LocalDate>("dateMiseEnCirculation"));
        dateDeCreation.setCellValueFactory((new PropertyValueFactory<Ambulance,LocalDate>("dateDeCreation")));

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
                        try {
                            deleteAmbulance(table.getItems().get(row).getId());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        initialize(null, null);

                    });
                    hBox.setSpacing(5);
                    setGraphic(hBox);

                }
            }


        });

        LocalDate minDate=LocalDate.now();
        date.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(minDate)); // désactiver les dates antérieures
                if (date.isBefore(minDate)) {
                    setStyle("-fx-background-color: #ffc0cb;");
                }
         }});

        table.setItems(FXCollections.observableArrayList(getAmbulance()));
        table.getSelectionModel().selectFirst();
        table.requestLayout();

//        dateDeMiseEnCirculation.setCellFactory(col -> new DateEditingCell());
//        dateDeMiseEnCirculation.setOnEditCommit((TableColumn.CellEditEvent<Ambulance, LocalDate> event) -> {
//            TablePosition<Ambulance, LocalDate> pos = event.getTablePosition();
//            LocalDate newDate = event.getNewValue();
//            int row = pos.getRow();
//            Ambulance ambulance = event.getTableView().getItems().get(row);
//            ambulance.setDateMiseEnCirculation(newDate);
//            try {
//                updateData(ambulance);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        });
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

    public void onCreate(ActionEvent event) throws IOException{
        Ambulance ambulance=new Ambulance(immText.getText(),date.getValue());
        ObjectMapper mapper=new ObjectMapper();
        RequestBody body= RequestBody.create(MediaType.parse("application/json"), mapper.writeValueAsString(ambulance));
        Request request = new Request.Builder()
                .url("http://localhost:9998/ambulance/ambulancecreation")
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        initialize(null,null);
    }
    public void deleteAmbulance(int id) throws IOException{
        Request request = new Request.Builder()
                .url("http://localhost:9998/ambulance/delete/"+ id)
                .delete()
                .build();

        Response response = okHttpClient.newCall(request).execute();
    }

    /*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (service != null) {
            action.setCellFactory(col -> new TableCell<>() {
                private final FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                private final FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
                private final Button deleteBtn = new Button("", deleteIcon);
                private final Button editBtn = new Button("", editIcon);
                private final HBox hBox = new HBox(editBtn, deleteBtn);

                {
                    TableViewD.setEditable(true);
                    deleteBtn.setOnAction(event -> {
                        int row = getIndex();
                        DetailDemandeDM detail = TableViewD.getItems().get(row);
                        System.out.println("hello3");
                        String codedetail = detail.getCode();
                        System.out.println("hello3");
                        dmDS.deleteDetail(codedetail);
                        System.out.println("hello3");
                        TableViewD.getItems().remove(detail);
                        TableViewD.refresh();
                    });

                    editBtn.setOnAction(event -> {
                        int row = getIndex();
                        DetailDemandeDM detail = TableViewD.getItems().get(row);
                        String codedetail = detail.getCode();

                        Stage dialog = new Stage();
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.setTitle("Modifier quantité");

                        TextField newquantiteT = new TextField(String.valueOf(detail.getQuantite()));
                        Label newquantiteL = new Label("Nouvelle quantité");
                        HBox newquantiteH = new HBox(newquantiteL, newquantiteT);
                        newquantiteH.setAlignment(Pos.CENTER);

                        Button updateButton = new Button("Modifier");
                        updateButton.setOnAction(event1 -> {
                            int quantiteN = Integer.parseInt(newquantiteT.getText());
                            dmDS.updateQuantiteDetailDM(codedetail, quantiteN);
                            detail.setQuantite(quantiteN);
                            TableViewD.refresh();
                            dialog.close();
                        });

                        VBox dialogVbox = new VBox(newquantiteH, updateButton);
                        dialogVbox.setAlignment(Pos.CENTER);
                        dialogVbox.setSpacing(10);
                        dialogVbox.setPadding(new Insets(10));

                        Scene dialogScene = new Scene(dialogVbox);
                        dialog.setScene(dialogScene);
                        dialog.showAndWait();
                    });

                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(hBox);
                    }
                }
            });
            ObservableList<TypeDM> TypeDMDtoObservableList = FXCollections.observableList(tdm.getAll());
            List<String> typedms = new ArrayList<>();
            for (TypeDM t : TypeDMDtoObservableList) {
                typedms.add(t.getNomType());
            }
            ObservableList<String> typetoObservableList = FXCollections.observableList(typedms);
            ObservableList<Service> ServiceDtoObservableList = FXCollections.observableList(ss.getAll());
            ColonneQuantite.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
            ColonneDM.setCellValueFactory(new PropertyValueFactory<>("dispositif"));
            //serviceBox1.setItems(ServiceDtoObservableList);
            typeDMBox.setItems(typetoObservableList);
            quantité.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2000, 1));
            typeDMBox.getSelectionModel().selectedItemProperty().addListener(((observableValue, olType, newType) -> remplirDM()));
            remplirDM();
            }

    */
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    class DateEditingCell extends TableCell<Ambulance, LocalDate> {
        private final DatePicker datePicker;


        public DateEditingCell() {
            this.datePicker = new DatePicker();
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
            this.datePicker.setEditable(true);
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
