package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Destillering;

import java.time.LocalDate;

public class DestilleringPane extends GridPane {

    public DestilleringPane() {
        FirstSection();

        SecondSection();
    }

    // Private fields for First Section
    private final TextField txfNewMakeId = new TextField();
    private final TextField txfFadNr = new TextField();
    private final TextField txfKornSort = new TextField();
    private final DatePicker dpStarDato = new DatePicker();
    private final TextField txfMaengdeLiter = new TextField();
    private final TextField txfAlkoholProcent = new TextField();
    private final TextField txfRøg = new TextField();
    private final TextField txfBatchID = new TextField();
    private final TextArea txaKommentar = new TextArea();
    private final Button btnCreate = new Button("Create");
    private final Label lblDestillering = new Label("Destillering");

    private void FirstSection() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.add(lblDestillering, 0, 0, 2, 1);
        lblDestillering.setStyle("-fx-font-size: 24px");

        this.add(new Label("New Make ID: "), 0, 1);
        this.add(txfNewMakeId, 1, 1);

        this.add(new Label("Fad Nr.:"), 0, 2);
        this.add(txfFadNr, 1, 2);

        this.add(new Label("Kornsort: "), 0, 3);
        this.add(txfKornSort, 1, 3);

        this.add(new Label("Start Dato: "), 0, 4);
        this.add(dpStarDato, 1, 4);

        this.add(new Label("Mængde Liter: "), 0, 5);
        this.add(txfMaengdeLiter, 1, 5);

        this.add(new Label("Alkohol %: "), 0, 6);
        this.add(txfAlkoholProcent, 1, 6);

        this.add(new Label("Røg: "), 0, 7);
        this.add(txfRøg, 1, 7);

        this.add(new Label("IsSingleMalt: "), 0, 8);
        HBox hBox1 = new HBox();
        RadioButton rbnTrueSingleMalt = new RadioButton("True");
        RadioButton rbnFalseSingleMalt = new RadioButton("False");
        hBox1.getChildren().addAll(rbnTrueSingleMalt, rbnFalseSingleMalt);
        this.add(hBox1, 1, 8);
        hBox1.setSpacing(10);

        this.add(new Label("IsHeart: "), 0, 9);
        HBox hBox2 = new HBox();
        RadioButton rbnTrueHeart = new RadioButton("True");
        RadioButton rbnFalseHeart = new RadioButton("False");
        hBox2.getChildren().addAll(rbnTrueHeart, rbnFalseHeart);
        this.add(hBox2, 1, 9);
        hBox2.setSpacing(10);

        this.add(new Label("Batch ID: "), 0, 10);
        this.add(txfBatchID, 1, 10);

        this.add(new Label("Kommentar: "), 0, 11);
        this.add(txaKommentar, 1, 11);
        txaKommentar.setPrefSize(50, 100);

        this.add(btnCreate, 0, 12);
    }

    // Private fields for Second Section
    private final Button btnUpdate = new Button("Update");
    private final Button btnDelete = new Button("Delete");

    private void SecondSection() {
        // Table View
        TableView<Destillering> tvDestilleringer = new TableView<>();

        // Created Columns
        TableColumn<Destillering, Integer> colNewMakeId = new TableColumn<>("New Make ID");
        colNewMakeId.setCellValueFactory(new PropertyValueFactory<>("newMakeId"));

        TableColumn<Destillering, LocalDate> colStartDato = new TableColumn<>("StartDato");
        colStartDato.setCellValueFactory(new PropertyValueFactory<>("startDato"));

        TableColumn<Destillering, LocalDate> colSlutDato = new TableColumn<>("SlutDato");
        colSlutDato.setCellValueFactory(new PropertyValueFactory<>("slutDato"));

        TableColumn<Destillering, Double> colMaengdeLiter = new TableColumn<>("Mængde Liter");
        colMaengdeLiter.setCellValueFactory(new PropertyValueFactory<>("mængdeLiter"));

        TableColumn<Destillering, Double> colAlkoholProcent = new TableColumn<>("Alkohol %");
        colAlkoholProcent.setCellValueFactory(new PropertyValueFactory<>("alkohol%"));


        // Add columns to table
        tvDestilleringer.getColumns().add(colNewMakeId);
        tvDestilleringer.getColumns().add(colStartDato);
        tvDestilleringer.getColumns().add(colSlutDato);
        tvDestilleringer.getColumns().add(colMaengdeLiter);
        tvDestilleringer.getColumns().add(colAlkoholProcent);

        // Test to see it's working
        tvDestilleringer.setItems(getData());

        // position in Tab
        this.add(tvDestilleringer, 3, 1, 10, 13);

        // Buttons TODO: Ret positionerne af knapperne
        HBox hbo3 = new HBox();
        hbo3.getChildren().addAll(btnUpdate, btnDelete);
        this.add(hbo3, 3, 14);
        hbo3.setAlignment(Pos.BASELINE_CENTER);
        hbo3.setSpacing(50);

        // TODO Rettelser af fejl fra Terminalen, når man kører App (AKA hvorfor er der mange røde linjer)
    }

    // Test to see if it's working
    private ObservableList<Destillering> getData() {
        return FXCollections.observableArrayList(
                new Destillering(1, LocalDate.of(2025,11,14), LocalDate.of(2025,11,16), 150, 60, "Test Kommentar 1"),
                new Destillering(2, LocalDate.of(2025,12,14), LocalDate.of(2025,12,20), 180, 55, "Test Kommentar 2")
        );
    }
}
