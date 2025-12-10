package gui;

import com.sun.javafx.scene.control.IntegerField;
import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Fad;
import model.Hylde;
import model.Lager;
import model.Reol;

import java.util.ArrayList;
import java.util.List;

public class LagerCreationPane extends Stage {

    public LagerCreationPane(String titel) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(titel);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }
    public Fad fadet;
    private final ListView<Lager> lWLager = new ListView<>();
    private final ListView<Reol> lWReol = new ListView<>();
    private final ListView<Hylde> lWHylde = new ListView<>();
    public List<Reol> tommeReoler = new ArrayList<>();
    public List<Hylde> tommeHylder = new ArrayList<>();
    private final  Button bLager = new Button("Opret Lager");
    private final  Button bReol = new Button("Opret Reol");
    private final Button bHylde = new Button("Opret Hylde");
    private final TextField txfLagerNavn = new TextField();
    private final TextField txfLagerAdresse = new TextField();
    private final HBox createButtons = new HBox();
    private final IntegerField integerField = new IntegerField();

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lFlytFad = new Label("Flyt Fad");
        pane.add(lFlytFad,0,0);

        Label lLager = new Label("Lager");
        pane.add(lLager,0,1);
        pane.add(lWLager,0,2,2,1);
        lWLager.getItems().setAll(Controller.getLager());
        ChangeListener<Lager> listenerLager = (ov, o, n) -> this.selectedLagerChanged();
        lWLager.getSelectionModel().selectedItemProperty().addListener(listenerLager);


        Label lReol = new Label("Tomme reoler");
        pane.add(lReol,2,1);
        pane.add(lWReol,2,2,2,1);
        ChangeListener<Reol> listenerReol = (ov, o, n) -> this.selectedReolChanged();
        lWReol.getSelectionModel().selectedItemProperty().addListener(listenerReol);

        Label lHylde = new Label("Tomme Hylder");
        pane.add(lHylde,4,1);
        pane.add(lWHylde,4,2,2,1);




        createButtons.getChildren().add(bLager);
        createButtons.getChildren().add(bReol);
        createButtons.getChildren().add(bHylde);
        pane.add(createButtons,2,3);
        createButtons.setSpacing(5);
        bLager.setOnAction(actionEvent -> OpretLager());
        bReol.setOnAction(actionEvent -> opretReol());
        bHylde.setOnAction(actionEvent -> opretHylde());

        pane.add(integerField,3,3);

        txfLagerNavn.setPromptText("Whisky lageret");
        txfLagerAdresse.setPromptText("Jesper Kleins Gade 1");

        pane.add(txfLagerNavn,0,3);
        pane.add(txfLagerAdresse,1,3);

    }

    private void opretHylde() {
        if (lWReol.getSelectionModel().isEmpty()||integerField.getValue()==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Vælg en reol og et antal reoler");
            alert.showAndWait();
        }else {
            Controller.addHylderTilReol(lWReol.getSelectionModel().getSelectedItem(),integerField.getValue());
           opdaterLWHylde();
        }
    }

    private void opretReol() {
        if (lWLager.getSelectionModel().isEmpty()||integerField.getValue()==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Vælg et lager og et antal reoler");
            alert.showAndWait();
        }else {
        Controller.addReolerTilLager(lWLager.getSelectionModel().getSelectedItem(),integerField.getValue());
        opdaterLWReol();
        }

    }

    private void OpretLager() {
        if (txfLagerAdresse.getText().isBlank()||txfLagerNavn.getText().isBlank()||txfLagerAdresse.getText().length()<5){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ingen blanke felter");
            alert.showAndWait();

        }else {
            Lager lager = Controller.opretLager(txfLagerAdresse.getText(),txfLagerNavn.getText());
            lWLager.getItems().add(lager);

        }

    }



    private void selectedReolChanged() {
        opdaterLWHylde();
    }

    private void opdaterLWHylde(){
        if(lWReol.getSelectionModel().getSelectedItem() !=null){
            tommeHylder.clear();
            lWReol.getSelectionModel().getSelectedItem().getHylder().forEach((key,hylde)-> {if(hylde.getFad()==null) tommeHylder.add(hylde);});
            lWHylde.getItems().setAll(tommeHylder);
        }else{
            lWHylde.getItems().removeAll();
        }
    }

    private void selectedLagerChanged() {
        if(lWLager.getSelectionModel().getSelectedItem() !=null){
            opdaterLWReol();
        }
    }

    private void opdaterLWReol(){
        if(lWLager.getSelectionModel().getSelectedItem() != null){
            tommeReoler.clear();
            lWLager.getSelectionModel().getSelectedItem().getReoler().forEach((key ,reol) -> {if(chekOmHarTomme(reol))tommeReoler.add(reol);});
            lWReol.getItems().setAll(tommeReoler);
        }

    }

    // funktion der chekker om en reol har en eller flere tomme hylder
    private boolean chekOmHarTomme(Reol reol){
        ArrayList<Hylde> hylder = new ArrayList<>();
        hylder.addAll(reol.getHylder().values());
        for (Hylde hylde : hylder) {
            if(hylde.getFad()== null){
                return false;
            }
        }
        return true;
    }

}
