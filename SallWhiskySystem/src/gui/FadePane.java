package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import model.Fad;
import model.Lager;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class FadePane extends GridPane {
    private final Label lblFade = new Label("Fade");

    public FadePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        this.add(lblFade, 0, 0, 2, 1);
        lblFade.setStyle("-fx-font-size: 24px");

        FirstSection();

        SecondSection();

    }

    // fields for first section
    private final TextField txfFadSøgning = new TextField();
    private final Button btnSøg = new Button("Søg");
    private final Button btnUpdate = new Button("Update");
    private final Button btnSlet = new Button("Slet");


    private void FirstSection() {

        // Create TableView
        TableView<Fad> tvFade = new TableView<>();

        // Create Columns
        TableColumn<Fad, Integer> colFadID = new TableColumn<>("Fad ID");
        colFadID.setCellValueFactory(new PropertyValueFactory<>("fadId"));

        TableColumn<Fad, List<String>> colTidligereIndhold = new TableColumn<>("Tidligere Indhold");
        colTidligereIndhold.setCellValueFactory(new PropertyValueFactory<>("tidligereIndhold"));

        TableColumn<Fad, Double> colFadStørrelse = new TableColumn<>("Fadstørrelse");
        colFadStørrelse.setCellValueFactory(new PropertyValueFactory<>("fadstørrelse"));

        TableColumn<Fad, String> colMateriale = new TableColumn<>("Materiale");
        colMateriale.setCellValueFactory(new PropertyValueFactory<>("materiale"));

        TableColumn<Fad, String> colLeverandør = new TableColumn<>("Leverandør");
        colLeverandør.setCellValueFactory(new PropertyValueFactory<>("leverandør"));

        TableColumn<Fad, LocalDate> colKøbtDato = new TableColumn<>("Købt Dato");
        colKøbtDato.setCellValueFactory(new PropertyValueFactory<>("købtdato"));

        // TODO Hvordan adder man fra en anden klasse?
        /*
        TableColumn<Lager, String> colLokation = new TableColumn<>("Lokation");
        colLokation.setCellValueFactory(new PropertyValueFactory<>("lokation"));
         */

        tvFade.getColumns().add(colFadID);
        tvFade.getColumns().add(colTidligereIndhold);
        tvFade.getColumns().add(colFadStørrelse);
        tvFade.getColumns().add(colMateriale);
        tvFade.getColumns().add(colLeverandør);
        tvFade.getColumns().add(colKøbtDato);
        //tvFade.getColumns().add(colLokation);

        // Test for om det virker TODO Rettelser
        //tvFade.setItems();

        // Add it to the pane
        this.add(tvFade, 0, 1, 2, 11);

        // buttons + search field
        this.add(txfFadSøgning, 0, 12);
        this.add(btnSøg, 1, 12);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(btnUpdate, btnSlet);
        this.add(hBox, 0, 13);
        hBox.setSpacing(10);
        /*
        this.add(btnUpdate, 0, 12);
        this.add(btnSlet, 1, 12);
         */

    }

    // Private Fields for second section
    private final TextField txfFadID = new TextField();
    private final TextField txfHistorik = new TextField();
    private final TextField txfFadstørrelse = new TextField();
    private final TextField txfMateriale = new TextField();
    private final TextField txfLeverandør = new TextField();
    private final DatePicker dpKøbtDato = new DatePicker();
    private final TextField txfLager = new TextField();
    private final TextField txfReol = new TextField();
    private final TextField txfHylde = new TextField();

    private final Button btnOpret = new Button("Opret");
    private final Button btnCancel = new Button("Cancel");

    private void SecondSection() {
        this.add(new Label("Fad ID:"), 2, 1);
        this.add(txfFadID, 3, 1);

        this.add(new Label("Historik:"), 2, 2);
        this.add(txfHistorik, 3, 2);

        this.add(new Label("Fadstørrelse:"), 2, 3);
        this.add(txfFadstørrelse, 3, 3);

        this.add(new Label("Materiale: "), 2, 4);
        this.add(txfMateriale, 3, 4);

        this.add(new Label("Leverandør:"), 2, 5);
        this.add(txfLeverandør, 3, 5);

        this.add(new Label("Købt Dato:"), 2, 6);
        this.add(dpKøbtDato, 3, 6);

        this.add(new Label("Lager:"), 2, 7);
        this.add(txfLager, 3, 7);

        this.add(new Label("Reol:"), 2, 8);
        this.add(txfReol, 3, 8);

        this.add(new Label("Hylde:"), 2, 9);
        this.add(txfHylde, 3, 9);

        /*
        HBox hBox = new HBox();
        hBox.getChildren().addAll(btnOpret, btnCancel);
        this.add(hBox, 1, 11);
        hBox.setSpacing(50);
         */

        this.add(btnOpret, 2, 10);
        this.add(btnCancel, 3, 10);
    }

}
