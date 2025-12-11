package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Hylde;
import model.Lager;
import model.Reol;
import java.util.ArrayList;
import java.util.List;

public class VælgFadVindue extends Stage {

    public VælgFadVindue(String titel) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(titel);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }
    private final ListView<Lager> lWLager = new ListView<>();
    private final ListView<Reol> lWReol = new ListView<>();
    private final ListView<Hylde> lWHylde = new ListView<>();
    private final Button bVælg = new Button("Vælg");
    public List<Reol> tommeReoler = new ArrayList<>();
    public List<Hylde> tommeHylder = new ArrayList<>();
    public Hylde hylde;

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lFlytFad = new Label("Vælg Fad");
        pane.add(lFlytFad,0,0);

        Label lLager = new Label("Lager");
        pane.add(lLager,0,1);
        pane.add(lWLager,0,2);
        lWLager.getItems().setAll(Controller.getLager());
        ChangeListener<Lager> listenerLager = (ov, o, n) -> this.selectedLagerChanged();
        lWLager.getSelectionModel().selectedItemProperty().addListener(listenerLager);

        Label lReol = new Label("Tomme reoler");
        pane.add(lReol,1,1);
        pane.add(lWReol,1,2);
        ChangeListener<Reol> listenerReol = (ov, o, n) -> this.selectedReolChanged();
        lWReol.getSelectionModel().selectedItemProperty().addListener(listenerReol);

        Label lHylde = new Label("Tomme Hylder");
        pane.add(lHylde,2,1);
        pane.add(lWHylde,2,2);
        ChangeListener<Hylde> listenerHylde = (ov, o, n) -> this.selectedHyldeChanged();
        lWHylde.getSelectionModel().selectedItemProperty().addListener(listenerHylde);

        pane.add(bVælg,0,3,1,3);
        bVælg.setDisable(true);
        bVælg.setOnAction(event -> bVælg());
    }

    public Hylde getHylde() {
        return hylde;
    }

    private void selectedHyldeChanged() {
        if(lWHylde.getSelectionModel().getSelectedItem() !=null){
            bVælg.setDisable(false);
        }else{
            bVælg.setDisable(true);
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
            bVælg.setDisable(true);
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
                return true;
            }
        }
        return false;
    }

    public void bVælg(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure thats the correct position for the fad?");
        alert.showAndWait();
        if(alert.getResult().equals(ButtonType.OK)){
            hylde=lWHylde.getSelectionModel().getSelectedItem();
            this.hide();
            alert.hide();
        }else{
            alert.hide();
        }
    }
}
