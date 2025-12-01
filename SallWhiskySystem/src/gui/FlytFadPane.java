package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Fad;
import model.Hylde;
import model.Lager;
import model.Reol;

import java.util.ArrayList;
import java.util.List;

public class FlytFadPane extends Stage {

    public FlytFadPane(String titel, Fad fad) {
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
    private final Button bFlyt = new Button("Flyt");
    public List<Reol> tommeReoler = new ArrayList<>();

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lFlytFad = new Label("Flyt Fad");
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

        Label lHylde = new Label("Tomme Hylder");
        pane.add(lHylde,2,1);
        pane.add(lWHylde,2,2);

        pane.add(bFlyt,0,3,1,3);

    }

    private void selectedLagerChanged() {
        if(lWLager.getSelectionModel().getSelectedItem() !=null){
            opdaterLWReol();
        }
    }

    private void opdaterLWReol(){
        if(lWLager.getSelectionModel().getSelectedItem() != null){
            tommeReoler.clear();
            lWLager.getSelectionModel().getSelectedItem().getReoler().forEach((key ,reol)-> {if(chekOmHarTomme(reol))tommeReoler.add(reol);});
            lWReol.getItems().setAll(tommeReoler);
        }
    }


    // funktion der chekker om en reol har en eller flere tomme hylder
    private boolean chekOmHarTomme(Reol reol){
        // TODO
        return true;
    }

    private void opdaterLWHylder(){
        //TODO
    }



}
