package gui;

import controller.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Destilat;
import model.Destillering;
import model.Fad;
import model.Medarbejder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DestilleringPane extends GridPane {

    public DestilleringPane() {
        FirstSection();
    }

    // Private fields for First Section
    private final TextField txfNewMakeId = new TextField();
    private final TextField txfFadNr = new TextField();
    private final TextField txfKornSort = new TextField();
    private final DatePicker dpStarDato = new DatePicker();
    private final DatePicker dpSlutDato = new DatePicker();
    private final TextField txfMaengdeLiter = new TextField();
    private final TextField txfAlkoholProcent = new TextField();
    private final TextField txfRøg = new TextField();
    private final ComboBox<Medarbejder> cbMedarbjder = new ComboBox<>();
    private final TextArea txaKommentar = new TextArea();
    private final Button btnCreate = new Button("Create");
    private final Label lblDestillering = new Label("Destillering");
    private final ComboBox<Fad> cbFadNr = new ComboBox<>();
    private final ListView<Destillering> lWDestilleringer = new ListView<>();
    private final Button bSlet = new Button("Slet");
    // ToggleGroup to use for true false RadioButtons
    ToggleGroup tg1 = new ToggleGroup(); // For isSingleMalt
    ToggleGroup tg2 = new ToggleGroup(); // For IsHeart
    RadioButton rbnTrueSingleMalt = new RadioButton("True");
    RadioButton rbnFalseSingleMalt = new RadioButton("False");
    RadioButton rbnTrueHeart = new RadioButton("True");
    RadioButton rbnFalseHeart = new RadioButton("False");



    private void FirstSection() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.add(lblDestillering, 0, 0, 2, 1);
        lblDestillering.setStyle("-fx-font-size: 24px");

        this.add(new Label("Fad Nr.:"), 0, 1);
        cbFadNr.setPromptText("Vælg fad (valgfrit)");
        cbFadNr.getItems().setAll(Controller.getEmptyFad());
        this.add(cbFadNr, 1, 1);

        //this.add(txfFadNr, 1, 2);

        this.add(new Label("Kornsort: "), 0, 2);
        this.add(txfKornSort, 1, 2);

        this.add(new Label("Start Dato: "), 0, 3);
        this.add(dpStarDato, 1, 3);

        this.add(new Label("Slut Dato: "), 0, 4);
        this.add(dpSlutDato,1,4);

        this.add(new Label("Mængde Liter: "), 0, 5);
        this.add(txfMaengdeLiter, 1, 5);

        this.add(new Label("Alkohol %: "), 0, 6);
        this.add(txfAlkoholProcent, 1, 6);

        this.add(new Label("Røg: "), 0, 7);
        this.add(txfRøg, 1, 7);



        // RadioButtons for isSingleMalt
        this.add(new Label("IsSingleMalt: "), 0, 8);
        HBox hBox1 = new HBox();

        hBox1.getChildren().addAll(rbnTrueSingleMalt, rbnFalseSingleMalt);
        this.add(hBox1, 1, 8);
        hBox1.setSpacing(10);
        rbnTrueSingleMalt.setToggleGroup(tg1);
        rbnFalseSingleMalt.setToggleGroup(tg1);

        // Radiobuttons for isHeart
        this.add(new Label("IsHeart: "), 0, 9);
        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(rbnTrueHeart, rbnFalseHeart);
        this.add(hBox2, 1, 9);
        hBox2.setSpacing(10);
        rbnTrueHeart.setToggleGroup(tg2);
        rbnFalseHeart.setToggleGroup(tg2);

        this.add(new Label("Medarbejder: "), 0, 10);
        this.add(cbMedarbjder, 1, 10);
        cbMedarbjder.getItems().setAll(Controller.getMedarbejderne());

        this.add(new Label("Kommentar: "), 0, 11);
        this.add(txaKommentar, 1, 11);
        txaKommentar.setPrefSize(50, 100);

        this.add(btnCreate, 0, 12);
        btnCreate.setOnAction(event -> createDestilat());

        this.add(bSlet,3,12);

        this.add(lWDestilleringer,3,1,1,11);
        lWDestilleringer.getItems().setAll(getFrieDestileringer());
    }

    public void updateDestilleringer(){
        lWDestilleringer.getItems().setAll(getFrieDestileringer());
    }



    public void updateLedigeFad(){
        cbFadNr.getItems().setAll(Controller.getEmptyFad());
    }

    private void createDestilat() {

        Destillering temp = new Destillering(dpStarDato.getValue(),dpSlutDato.getValue(),Double.parseDouble(txfMaengdeLiter.getText()),Double.parseDouble(txfAlkoholProcent.getText()),cbMedarbjder.getSelectionModel().getSelectedItem());
        Controller.opretDestilat(Double.parseDouble(txfMaengdeLiter.getText()),rbnTrueSingleMalt.isSelected(),rbnTrueHeart.isSelected(),temp);
        updateLedigeFad();
        updateDestilleringer();
    }
    private ArrayList<Destillering> getFrieDestileringer(){
        System.out.println("test 2");
        ArrayList<Destillering> frieDestilleringer = new ArrayList<>();
        for (Destilat destilat : Controller.getDestilater()) {
            System.out.println("test 3");
            if(!destilat.getUsed()){
                System.out.println("test 4");
                frieDestilleringer.add(destilat.getDestillering());
            }
        }
        return frieDestilleringer;
    }

}
