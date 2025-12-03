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
import javafx.scene.layout.Priority;
import model.Destilat;
import model.Destillering;
import model.Fad;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.List;

public class SporbarhedPane extends GridPane {

    private final Label lblTitle = new Label("Sporbarhed"); // Sporbarhed
    private TableView<Fad> tvFade;
    private ObservableList<Fad> allFadeData;

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

        // Update initial aata;
        loadFadeDate();
    }

    private void initializeTableView() {
        // Create Tableview
        tvFade = new TableView<>();

        GridPane.setHgrow(tvFade, Priority.ALWAYS);
        GridPane.setVgrow(tvFade, Priority.ALWAYS);
        tvFade.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Create columns

        // Fad Id.
        TableColumn<Fad, Integer> colFadNr = new TableColumn<>("Fad Nr.");
        colFadNr.setCellValueFactory(new PropertyValueFactory<>("fadId"));
        colFadNr.setPrefWidth(100);

        // Batch Id
        TableColumn<Fad, Integer> colBatchId = new TableColumn<>("Batch/Dest. ID.");
        colBatchId.setCellValueFactory(cell -> {
            Fad fad = cell.getValue();

            Destilat destilat = fad.getDestilat();

            if (destilat != null && destilat.getDestillering() != null) {
                int batchId = fad.getDestilat().getBatchId();
                return new SimpleIntegerProperty(batchId).asObject();
            }

            return new SimpleIntegerProperty(0).asObject();
        });
        colBatchId.setPrefWidth(120);

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
        colTidligereIndhold.setPrefWidth(200);

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



        tvFade.getColumns().add(colFadNr);
        tvFade.getColumns().add(colBatchId);
        tvFade.getColumns().add(colTidligereIndhold);
        tvFade.getColumns().add(colDestilleringsDato);

        tvFade.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        displayFadHistorie(newValue);
                    }
                }
        );

        this.add(tvFade, 0, 1, 2, 6);

    }

    // private fields for searchFields()
    private final TextField txfFadNr = new TextField();
    private final TextField txfBatchId = new TextField();
    private final TextField txfTidligereIndhold = new TextField();
    private final DatePicker dpDestilleringDato = new DatePicker();

    private void setupSearchFields() {
        Label lblSearch = new Label("Søg");
        this.add(lblSearch, 2, 1);
        lblSearch.setStyle("-fx-Font-Size: 16px");

        this.add(new Label("Fad nr."), 2, 2);
        this.add(txfFadNr, 3, 2);

        this.add(new Label("Batch ID:"), 2, 3);
        this.add(txfBatchId, 3, 3);

        this.add(new Label("Tidligere indhold"), 2, 4);
        this.add(txfTidligereIndhold, 3, 4);

        this.add(new Label("Destillerings dato"), 2, 5);
        this.add(dpDestilleringDato, 3, 5);

    }

    private final TextArea txaHistorie = new TextArea();

    private void setupHistorie() {
        Label lblHistorie = new Label("Fad Historie");
        lblHistorie.setStyle("-fx-font-size: 16px");
        this.add(lblHistorie, 0, 7);

        txaHistorie.setPrefSize(300, 250);
        txaHistorie.setEditable(false);
        this.add(txaHistorie, 0, 8);

    }

    private void loadFadeDate() {
        try {
            List<Fad> fade = Controller.getFade();

            allFadeData = FXCollections.observableArrayList(fade);
            tvFade.setItems(allFadeData);

            if (fade.isEmpty()) {
                ShowAlert("Ingen data", "Der er ingen fad at vise.");
            }

        } catch (Exception e) {
            ShowAlert("Fejl ved indlæsning", "Kunne ikke indlæse fad data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void displayFadHistorie(Fad fad) {
        StringBuilder historie = new StringBuilder();

        // Fad Detaljer
        historie.append("=== Fad Detaljer ===\n");
        historie.append("Fad ID: ").append(fad.getFadId()).append("\n");
        historie.append("Fad Størrelse: ").append(String.format("%.1f L", fad.getLiter())).append("\n");
        historie.append("Materiale: ").append(fad.getMateriale()).append("\n");
        historie.append("Leverandør: ").append(fad.getLeverandør()).append("\n");
        historie.append("Status: ").append(fad.isEmpty() ? "Tomt" : "Fyldt").append("\n\n");

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

                //historie.append("Destilleret af: ").append(destillering.getMedarbejder().getNavn()).append("\n");
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
