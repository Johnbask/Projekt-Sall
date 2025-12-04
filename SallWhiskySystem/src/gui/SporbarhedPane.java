package gui;

import controller.Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.Destilat;
import model.Destillering;
import model.Fad;

import java.time.LocalDate;
import java.util.List;

import java.util.function.Predicate;

public class SporbarhedPane extends GridPane {

    private final Label lblTitle = new Label("Sporbarhed"); // Sporbarhed
    private TableView<Fad> tvFade; // For Sporbarhed
    private FilteredList<Fad> filteredFade; // For Searching
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

        this.add(lblTitle, 0, 0);
        lblTitle.setStyle("-fx-font-size: 24px");

        // Launch Sections
        initializeTableView();
        setupSearchFields();
        setupHistorie();

        // Update initial data (Show data on TableView)
        loadFadeDate();

        // Make TableView Searchable
        setupFiltering();
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

        this.add(tvFade, 0, 1, 2, 6);
    }

    // Vis Information til TableView
    private void loadFadeDate() {
        try {
            List<Fad> fade = Controller.getFade();
            allFadeData = FXCollections.observableArrayList(fade);

            filteredFade = new FilteredList<>(allFadeData, p -> true);

            SortedList<Fad> sortedFade = new SortedList<>(filteredFade);

            sortedFade.comparatorProperty().bind(tvFade.comparatorProperty());

            tvFade.setItems(sortedFade);

            if (fade.isEmpty()) {
                ShowAlert("Ingen data", "Der er ingen fad at vise.");
            }

        } catch (Exception e) {
            ShowAlert("Fejl ved indlæsning", "Kunne ikke indlæse fad data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void setupFiltering() {
        Predicate<Fad> kombineretPredicate = fad -> {
            boolean matches = true;

            // Fad nr filter
            if (!txfFadNr.getText().isEmpty()) {
                String fadNrStr = String.valueOf(fad.getFadId());
                matches = matches && fadNrStr.contains(txfFadNr.getText());
            } else {
                matches = false;
            }

            // New Make ID
            if (!txfNewMakeID.getText().isEmpty()) {
                Destilat destilat = fad.getDestilat();
                if (destilat != null && destilat.getDestillering() != null) {
                    String newMakeID = String.valueOf(destilat.getDestillering().getNewMakeId());
                    matches = matches && newMakeID.contains(txfNewMakeID.getText());
                } else {
                    matches = false;
                }
            }

            // Tidligere Indhold filter
            if (!txfTidligereIndhold.getText().isEmpty()) {
                List<String> indhold = fad.getTidligereIndhold();
                if (indhold != null && !indhold.isEmpty()) {
                    boolean indholdMatcher = indhold.stream().anyMatch(content -> content.toLowerCase()
                            .contains(txfTidligereIndhold.getText().toLowerCase()));
                    matches = matches && indholdMatcher;
                } else {
                    matches = false;
                }
            }

            // Dato filter
            if (dpDestilleringDato.getValue() != null) {
                Destilat destilat = fad.getDestilat();
                if (destilat != null && destilat.getDestillering() != null) {
                    LocalDate startDato = destilat.getDestillering().getStartDato();
                    matches = matches && startDato != null && startDato.equals(dpDestilleringDato.getValue());
                } else {
                    matches = false;
                }
            }

            return matches;
        };

        txfFadNr.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredFade.setPredicate(kombineretPredicate);
        });

        txfNewMakeID.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredFade.setPredicate(kombineretPredicate);
        });

        txfTidligereIndhold.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredFade.setPredicate(kombineretPredicate);
        });

        dpDestilleringDato.valueProperty().addListener((observable, oldValue, newValue) -> {
            filteredFade.setPredicate(kombineretPredicate);
        });
    }

    private void setupSearchFields() {
        Label lblSearch = new Label("Søg");
        this.add(lblSearch, 2, 1);
        lblSearch.setStyle("-fx-Font-Size: 16px");

        this.add(new Label("Fad nr."), 2, 2);
        this.add(txfFadNr, 3, 2);


        this.add(new Label("New Make ID:"), 2, 3);
        this.add(txfNewMakeID, 3, 3);

        this.add(new Label("Tidligere indhold"), 2, 4);
        this.add(txfTidligereIndhold, 3, 4);

        this.add(new Label("Destillerings dato"), 2, 5);
        this.add(dpDestilleringDato, 3, 5);

        Button btnClearFilters = new Button("Ryd søgning");
        btnClearFilters.setOnAction(e -> {
            txfFadNr.clear();
            txfNewMakeID.clear();
            txfTidligereIndhold.clear();
            dpDestilleringDato.setValue(null);
            filteredFade.setPredicate(p -> true);
        });

        this.add(btnClearFilters, 3, 6);
    }

    private void setupHistorie() {
        Label lblHistorie = new Label("Fad Historie");
        lblHistorie.setStyle("-fx-font-size: 16px");
        this.add(lblHistorie, 0, 7);

        txaHistorie.setPrefSize(300, 250);
        txaHistorie.setEditable(false);
        this.add(txaHistorie, 0, 8);
    }

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
            historie.append("Heart Cut:").append(destilat.getHeart() ? "Ja" : "Nej").append("\n");

            if (destilat.getRøgmateriale() != null) {
                historie.append("Røgmateriale: ").append(destilat.getRøgmateriale()).append("\n");
            }

            Destillering destillering = destilat.getDestillering();
            if (destillering != null) {
                historie.append("\n=== Destillerings Info ===\n");
                historie.append("New Make ID: ").append(destillering.getNewMakeId()).append("\n");
                historie.append("Start Dato:").append(destillering.getStartDato()).append("\n");
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

    private void ShowAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
