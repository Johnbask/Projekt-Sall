package gui;

import com.sun.javafx.scene.control.DoubleField;
import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.*;
import storage.Storage;

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
    private final DoubleField dFMaengdeLiter = new DoubleField();
    private final DoubleField dFAlkoholProcent = new DoubleField();
    private final TextField txfRøg = new TextField();
    private final ComboBox<Medarbejder> cbMedarbjder = new ComboBox<>();
    private final TextArea txaKommentar = new TextArea();
    private final Button btnCreate = new Button("Create");
    private final Label lblDestillering = new Label("Destillering");
    private final ComboBox<Vand> cbVand = new ComboBox<>();
    private final ListView<Destillering> lWDestilleringer = new ListView<>();
    private final Button bSlet = new Button("Slet");
    // ToggleGroup to use for true false RadioButtons
    ToggleGroup tg1 = new ToggleGroup(); // For isSingleMalt
    ToggleGroup tg2 = new ToggleGroup(); // For IsHeart
    RadioButton rbnTrueSingleMalt = new RadioButton("True");
    RadioButton rbnFalseSingleMalt = new RadioButton("False");
    RadioButton rbnTrueHeart = new RadioButton("True");
    RadioButton rbnFalseHeart = new RadioButton("False");
    private final Label lError = new Label();



    private void FirstSection() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.add(lblDestillering, 0, 0, 2, 1);
        lblDestillering.setStyle("-fx-font-size: 24px");

        this.add(new Label("Råvare "), 0, 1);
        this.add(txfKornSort, 1, 1);

        this.add(new Label("Start Dato: "), 0, 2);
        this.add(dpStarDato, 1, 2);

        this.add(new Label("Slut Dato: "), 0, 3);
        this.add(dpSlutDato,1,3);

        this.add(new Label("Mængde Liter: "), 0, 4);
        this.add(dFMaengdeLiter, 1, 4);

        this.add(new Label("Alkohol %: "), 0, 5);
        this.add(dFAlkoholProcent, 1, 5);

        this.add(new Label("Røg: "), 0, 6);
        this.add(txfRøg, 1, 6);

        // RadioButtons for isSingleMalt
        this.add(new Label("IsSingleMalt: "), 0, 7);
        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(rbnTrueSingleMalt, rbnFalseSingleMalt);
        this.add(hBox1, 1, 7);
        hBox1.setSpacing(10);
        rbnTrueSingleMalt.setToggleGroup(tg1);
        rbnFalseSingleMalt.setToggleGroup(tg1);
        rbnTrueSingleMalt.setSelected(true);

        // Radiobuttons for isHeart
        this.add(new Label("IsHeart: "), 0, 8);
        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(rbnTrueHeart, rbnFalseHeart);
        this.add(hBox2, 1, 8);
        hBox2.setSpacing(10);
        rbnTrueHeart.setToggleGroup(tg2);
        rbnFalseHeart.setToggleGroup(tg2);
        rbnTrueHeart.setSelected(true);

        this.add(new Label("Medarbejder: "), 0, 9);
        this.add(cbMedarbjder, 1, 9);
        cbMedarbjder.getItems().setAll(Controller.getMedarbejderne());

        this.add(new Label("Kommentar: "), 0, 10);
        this.add(txaKommentar, 1, 10);
        txaKommentar.setPrefSize(50, 100);

        this.add(new Label("VandKilde"), 0, 11);
        cbVand.setPromptText("Vælg vandkilde");
        cbVand.getItems().setAll(Controller.getVands());
        this.add(cbVand, 1, 11);


        this.add(btnCreate, 0, 12);
        btnCreate.setOnAction(event -> createDestilat());


        this.add(lError,0,13,2,1);

        this.add(lWDestilleringer,3,1,1,11);
        lWDestilleringer.setMinWidth(600);
        lWDestilleringer.getItems().setAll(getDestileringer());


        dpStarDato.setValue(LocalDate.now().minusDays(8));
        dpSlutDato.setValue(LocalDate.now().minusDays(1));
        cbMedarbjder.getSelectionModel().selectFirst();
        cbVand.getSelectionModel().selectFirst();
        lError.setMinWidth(250);





    }

    public void updateDestilleringer(){
        lWDestilleringer.getItems().setAll(getDestileringer());
    }


    private void createDestilat() {

        if(txfKornSort.getText().isBlank()){
            lError.setText("ERROR Pls Input Råvare");
            lError.setStyle("-fx-text-fill: red;");
        }else if(dpStarDato == null){
            lError.setText("ERROR Pls Input en start dato");
            lError.setStyle("-fx-text-fill: red;");
        } else if (dpSlutDato == null) {
            lError.setText("ERROR Pls Input en slut dato");
            lError.setStyle("-fx-text-fill: red;");  
        } else if (dFMaengdeLiter == null) {
            lError.setText("ERROR Pls Input mængde destilleret");
            lError.setStyle("-fx-text-fill: red;");
        } else if (dFAlkoholProcent == null) {
            lError.setText("ERROR Pls Input Alcohol %");
            lError.setStyle("-fx-text-fill: red;");
        } else if (cbMedarbjder.getSelectionModel().isEmpty()) {
            lError.setText("ERROR Pls vælg en medarbejder");
            lError.setStyle("-fx-text-fill: red;");
        } else if (cbVand.getSelectionModel().isEmpty()) {
            lError.setText("ERROR Pls vælg en vandKilde");
            lError.setStyle("-fx-text-fill: red;");
        } else if (dpSlutDato.getValue().isBefore(dpStarDato.getValue())) {
            lError.setText("ERROR Slut er sat før start");
            lError.setStyle("-fx-text-fill: red;");
        } else if (dFMaengdeLiter.getValue() <= 0.0) {
            lError.setText("ERROR pls input en valid mængde i mængde liter");
            lError.setStyle("-fx-text-fill: red;");
        } else if (dFAlkoholProcent.getValue() <= 0 || dFAlkoholProcent.getValue() >= 100) {
            lError.setText("ERROR alkohol procent skal være over 0");
            lError.setStyle("-fx-text-fill: red;");
        } else{
            Destillering destillering = new Destillering(dpStarDato.getValue(),dpSlutDato.getValue(),dFMaengdeLiter.getValue(),dFAlkoholProcent.getValue(),cbMedarbjder.getSelectionModel().getSelectedItem(),txfKornSort.getText(),txfRøg.getText(),txaKommentar.getText(),cbVand.getSelectionModel().getSelectedItem());
            Controller.opretDestilat(dFMaengdeLiter.getValue(),rbnTrueSingleMalt.isSelected(),rbnTrueHeart.isSelected(),destillering);
            updateDestilleringer();
            Controller.writeStorage();
            lError.setText("");


        }


    }
    private ArrayList<Destillering> getDestileringer(){
        ArrayList<Destillering> Destilleringer = new ArrayList<>();
        ArrayList<Integer> ider = new ArrayList<>();
        for (Destilat destilat : Controller.getDestilater()) {
            if(!ider.contains(destilat.getDestillering().getNewMakeId())){
                Destilleringer.add(destilat.getDestillering());
                ider.add(destilat.getDestillering().getNewMakeId());
            }
        }
        return Destilleringer;
    }

}
