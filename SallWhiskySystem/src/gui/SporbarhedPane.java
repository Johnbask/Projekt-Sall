package gui;

import controller.Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Destilat;
import model.Destillering;
import model.Fad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SporbarhedPane extends GridPane {

    private TableView<Fad> tvFade; // Oversigt af Fade
    private ObservableList<Fad> allFadeData; // For Searching
    private final TextArea txaHistorie = new TextArea(); // For Fad History

    // private fields to create searches
    private final TextField txfFadNr = new TextField();
    private final TextField txfNewMakeID = new TextField();
    private final TextField txfTidligereIndhold = new TextField();
    private final DatePicker dpDestilleringDato = new DatePicker();

    public SporbarhedPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // Title af Tab
        Label lblTitle = new Label("Sporbarhed");
        this.add(lblTitle, 0, 0);
        lblTitle.setStyle("-fx-font-size: 24px");

        // Launch Sections
        initializeTableView();
        setupSearchFields();
        setupHistorie();

        // Update initial data (Show data on TableView)
        loadFadeDate();
    }

    // Vis TableView
    private void initializeTableView() {
        // Create Tableview
        tvFade = new TableView<>();
        tvFade.setEditable(false);
        tvFade.setPrefWidth(391);

        // Create columns

        // Fad Id.
        TableColumn<Fad, Integer> colFadNr = new TableColumn<>("Fad Nr.");
        colFadNr.setCellValueFactory(new PropertyValueFactory<>("fadId"));

        // New Make ID
        TableColumn<Fad, Integer> colNewMakeID = new TableColumn<>("New Make ID");
        colNewMakeID.setCellValueFactory(cell -> {
            Fad fad = cell.getValue();
            Destilat destilat = fad.getDestilat();
            if (destilat==null){
                return null;
            }
            if (destilat != null && destilat.getDestillering() != null) {
                int newMakeId = fad.getDestilat().getDestillering().getNewMakeId();
                return new SimpleIntegerProperty(newMakeId).asObject();
            }

            return new SimpleIntegerProperty(0).asObject();
        });


        // Tidligere Indhold
        TableColumn<Fad, String> colTidligereIndhold = new TableColumn<>("Tidligere Indhold");
        colTidligereIndhold.setCellValueFactory(cell -> {
           Fad fad = cell.getValue();
           List<String> indhold = fad.getTidligereIndhold();

           if (indhold != null && !indhold.isEmpty()) {
               return new SimpleStringProperty(String.join(", ", indhold));
           }

           return new SimpleStringProperty("Ingen");
        });


        // Destillerings Dato
        TableColumn<Fad, String> colDestilleringsDato = new TableColumn<>("Destillerings Dato");
        colDestilleringsDato.setCellValueFactory(cell -> {
            Fad fad = cell.getValue();
            Destilat destilat = fad.getDestilat();

            if (destilat != null && destilat.getDestillering() != null) {
                LocalDate startDato = destilat.getDestillering().getStartDato();
                return new SimpleStringProperty(startDato.toString());
            }
            return new SimpleStringProperty("Ikke destilleret");
        });

        // Adds the columns to the TableView
        tvFade.getColumns().add(colFadNr);
        tvFade.getColumns().add(colNewMakeID);
        tvFade.getColumns().add(colTidligereIndhold);
        tvFade.getColumns().add(colDestilleringsDato);

        // Updates txaHistorie with clicked Fad
        tvFade.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        displayFadHistorie(newValue);
                    }
                }
        );

        this.add(tvFade, 0, 1, 2, 7);
    }

    // Vis Information til TableView
    private void loadFadeDate() {
        try {
            List<Fad> fade = Controller.getFade(); // Henter alle Fade

            allFadeData = FXCollections.observableArrayList(fade); // Gør dem observable til søgning

            tvFade.setItems(allFadeData); // viser alle fade inde i TableView

            // Hvis der ingen fad at vise
            if (fade.isEmpty()) {
                ShowAlert("Ingen data", "Der er ingen fad at vise.");
            }

            // Fanger fejl hvis det sker
        } catch (Exception e) {
            ShowAlert("Fejl ved indlæsning", "Kunne ikke indlæse fad data: " + e.getMessage());
        }
    }

    // Indsæt felter til søgning
    private void setupSearchFields() {
        Label lblSearch = new Label("Søg");
        this.add(lblSearch, 2, 1);
        lblSearch.setStyle("-fx-Font-Size: 16px");

        // TextField til søgning af Fad nr.
        this.add(new Label("Fad nr."), 2, 2);
        this.add(txfFadNr, 3, 2);
        txfFadNr.textProperty().addListener(
                (obs, o, n) -> search()
        );

        // TextField til søgning af New Make ID
        this.add(new Label("New Make ID:"), 2, 3);
        this.add(txfNewMakeID, 3, 3);
        txfNewMakeID.textProperty().addListener(
                (obs, o, n) -> search()
        );

        // TextField til søgning af Tidligere Indhold (Fadets Historik)
        this.add(new Label("Tidligere indhold"), 2, 4);
        this.add(txfTidligereIndhold, 3, 4);
        txfTidligereIndhold.textProperty().addListener(
                (obs, o, n) -> search()
        );

        // DatePicker til søgning via Destillerings startDato
        this.add(new Label("Destillerings dato"), 2, 5);
        this.add(dpDestilleringDato, 3, 5);
        dpDestilleringDato.valueProperty().addListener(
                (obs, o, n) -> search()
        );
    }

    // Gør Felterne søgebare i TableView
    private void search() {
        List<Fad> result = new ArrayList<>();

        for (Fad fad : allFadeData) {
            boolean matches = true;

            // Fad ID
            if (!txfFadNr.getText().isEmpty()) {
                if (!(String.valueOf(fad.getFadId()).contains(txfFadNr.getText()))) {
                    matches = false;
                }
            }

            // New Make ID
            if (!txfNewMakeID.getText().isEmpty()) {
                Destilat destilat = fad.getDestilat();

                if (destilat == null || destilat.getDestillering() == null ||
                        !String.valueOf(destilat.getDestillering().getNewMakeId()).contains(txfNewMakeID.getText())
                ) {
                    matches = false;
                }
            }

            // Tidligere Indhold
            if (!txfTidligereIndhold.getText().isEmpty()) {
                boolean found = false;

                if (fad.getTidligereIndhold() != null) {
                    for (String s : fad.getTidligereIndhold()) {
                        if (s.toLowerCase().contains(txfTidligereIndhold.getText().toLowerCase())) {
                            found = true;
                        }
                    }
                }
                if (!found) {
                    matches = false;
                }
            }

            // Dato
            if (dpDestilleringDato.getValue() != null) {
                Destilat destilat = fad.getDestilat();

                if (destilat == null || destilat.getDestillering() == null ||
                        !dpDestilleringDato.getValue().equals(destilat.getDestillering().getStartDato())) {
                    matches = false;
                }
            }

            if (matches) result.add(fad);

        }

        // Opdatere TableView med søgning
        tvFade.setItems(FXCollections.observableList(result));
    }

    // Lav TextArea til Fad Historie
    private void setupHistorie() {
        Label lblHistorie = new Label("Fad Historie");
        lblHistorie.setStyle("-fx-font-size: 16px");
        this.add(lblHistorie, 0, 8);

        txaHistorie.setPrefSize(300, 250);
        txaHistorie.setEditable(false);
        this.add(txaHistorie, 0, 9);
    }

    // Vis Fad Historie
    // TODO Check om alt er der som skal være der
    private void displayFadHistorie(Fad fad) {
        StringBuilder historie = new StringBuilder();

        // Fad Detaljer
        historie.append("=== Fad Detaljer ===\n");
        historie.append("Fad ID: ").append(fad.getFadId()).append("\n");
        historie.append("Fad Størrelse: ").append(String.format("%.1f L", fad.getLiter())).append("\n");
        historie.append("Materiale: ").append(fad.getMateriale()).append("\n");
        historie.append("Leverandør: ").append(fad.getLeverandør()).append("\n");
        historie.append("Status: ").append(fad.isTom() ? "Tomt" : "Fyldt").append("\n\n");

        // Tidligere Indhold
        List<String> tidligereIndhold = fad.getTidligereIndhold();
        if (tidligereIndhold != null && !tidligereIndhold.isEmpty()) {
            historie.append("=== Tidligere Indhold ===\n");
            for (String indhold : tidligereIndhold) {
                historie.append("- ").append(indhold).append("\n");
            }
            historie.append("\n");
        }

        // Nuværende destillation info
        Destilat destilat = fad.getDestilat();
        if (destilat != null) {
            historie.append("=== Aktuelt Indhold ===\n");
            historie.append("Mængde Liter: ").append(String.format("%.1f L", destilat.getLiter())).append("\n");
            historie.append("Single Malt: ").append(destilat.getSingleMalt() ? "Ja" : "Nej").append("\n");
            historie.append("Heart Cut: ").append(destilat.getHeart() ? "Ja" : "Nej").append("\n");

            if (destilat.getRøgmateriale() != null) {
                historie.append("Røgmateriale: ").append(destilat.getRøgmateriale()).append("\n");
            }

            Destillering destillering = destilat.getDestillering();
            if (destillering != null) {
                historie.append("\n=== Destillerings Info ===\n");
                historie.append("New Make ID: ").append(destillering.getNewMakeId()).append("\n");
                historie.append("Start Dato: ").append(destillering.getStartDato()).append("\n");
                historie.append("Slut Dato: ").append(destillering.getSlutDato()).append("\n");
                historie.append("Alkohol %: ").append(String.format("%.2f%%", destillering.getAlkoholProcent())).append("\n");
                historie.append("Mængde produceret: ").append(String.format("%.1f L", destillering.getMængdeProduceret())).append("\n");

                if (destillering.getKommentar() != null && !destillering.getKommentar().isEmpty()) {
                    historie.append("Kommentar: ").append(destillering.getKommentar()).append("\n");
                }

                historie.append("Destilleret af: ").append(destillering.getMedarbejder().getNavn()).append("\n");
            }
        }

        txaHistorie.setText(historie.toString());
    }

    // Alerts
    private void ShowAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
